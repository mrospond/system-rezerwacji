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
    public ValidationResponse validate(Reservation reservation) {
        ValidationResponse response = new ValidationResponse();
        boolean valid = checkForFreeTimeSlot(reservation, response) && checkForUserPermissions(reservation, response);
        response.setValid(valid);
        return response;
    }

    private boolean checkForFreeTimeSlot(Reservation reservation, ValidationResponse response) {
        boolean isTimeSlotFree = roomReservationService.isTimeSlotFree(
                reservation.getRoomId(), reservation.getStartTime(), reservation.getEndTime());
        if (!isTimeSlotFree) {
            response.addError("Selected time slot is not available");
        }
        return isTimeSlotFree;
    }

    private boolean checkForUserPermissions(Reservation reservation, ValidationResponse response) {
        Permissions permissions = employeeService.getLoggedInUserPermissions();

        boolean durationTimeValid = ChronoUnit.HOURS.between(reservation.getStartTime(), reservation.getEndTime())
                <= permissions.getMaxReservationTimeHours();

        if (!durationTimeValid) {
            response.addError("You don't have permission for this reservation duration");
        }

        Room room = roomDao.getById(reservation.getRoomId());
        boolean roomSizeValid = room.getSeats() <= permissions.getMaxRoomSize();

        if (!roomSizeValid) {
            response.addError("You don't have permission for this room size");
        }

        List<RecordFilter> filters = new ArrayList<>();
        filters.add(
                ReservationTimePeriodEmployeeIdFilter.builder()
                        .employeeId(employeeService.getLoggedInUserDetails().getId())
                        .from(reservation.getStartTime().toLocalDate().atStartOfDay())
                        .to(reservation.getEndTime().toLocalDate().atTime(23, 59, 59))
                        .build()
        );
        boolean reservationsNumberValid =
                roomReservationService.getReservations(filters).size() < permissions.getMaxReservationsPerDay();

        if (!reservationsNumberValid) {
            response.addError("You've reached the limit of daily reservations");
        }

        return durationTimeValid && roomSizeValid && reservationsNumberValid;
    }
}
