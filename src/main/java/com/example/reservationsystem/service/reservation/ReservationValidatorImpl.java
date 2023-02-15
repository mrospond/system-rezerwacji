package com.example.reservationsystem.service.reservation;

import com.example.reservationsystem.database.dao.RoomDao;
import com.example.reservationsystem.domain.Permissions;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.EmployeeService;
import com.example.reservationsystem.service.RoomReservationService;
import com.example.reservationsystem.service.filters.RecordFilter;
import com.example.reservationsystem.service.filters.reservations.ReservationTimePeriodEmployeeIdFilter;
import lombok.AllArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ReservationValidatorImpl implements ReservationValidator {
    private final EmployeeService employeeService;
    private final RoomReservationService roomReservationService;
    private final RoomDao roomDao;

    @Override
    public boolean validate(Reservation reservation) {
        return isTimeSlotFree(reservation) && userHasRequiredPermissions(reservation);
    }

    private boolean isTimeSlotFree(Reservation reservation) {
        return roomReservationService.isTimeSlotFree(
                reservation.getRoomId(), reservation.getStartTime(), reservation.getEndTime());
    }

    private boolean userHasRequiredPermissions(Reservation reservation) {
        Permissions permissions = employeeService.getLoggedInUserPermissions();

        boolean durationTimeValid = ChronoUnit.HOURS.between(reservation.getStartTime(), reservation.getEndTime())
                <= permissions.getMaxReservationTimeHours();

        Room room = roomDao.getById(reservation.getRoomId());
        boolean roomSizeValid = room.getSeats() <= permissions.getMaxRoomSize();

        List<RecordFilter> filters = new ArrayList<>();
        filters.add(
                ReservationTimePeriodEmployeeIdFilter.builder()
                        .employeeId(employeeService.getLoggedInUserDetails().getId())
                        .from(reservation.getStartTime().toLocalDate().atStartOfDay())
                        .to(reservation.getEndTime().toLocalDate().atTime(23, 59, 59))
                        .build()
        );
        boolean reservationsNumberValid =
                roomReservationService.getReservations(filters).size() <= permissions.getMaxReservationsPerDay();

        return durationTimeValid && roomSizeValid && reservationsNumberValid;
    }
}
