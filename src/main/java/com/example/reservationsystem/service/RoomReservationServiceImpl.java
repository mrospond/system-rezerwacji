package com.example.reservationsystem.service;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.dao.ReservationDao;
import com.example.reservationsystem.database.dao.RoomDao;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.exceptions.InvalidReservationException;
import com.example.reservationsystem.service.filters.RecordFilter;
import com.example.reservationsystem.service.reservation.ReservationValidator;
import com.example.reservationsystem.service.reservation.ReservationValidatorImpl;
import com.example.reservationsystem.service.reservation.ValidationResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {
    private final RoomDao roomDao;
    private final ReservationDao reservationDao;
    private final EmployeeService employeeService;
    private ReservationValidator reservationValidator;

    @PostConstruct
    private void constructValidator() {
        reservationValidator = new ReservationValidatorImpl(employeeService, this, roomDao);
    }

    @Override
    public List<Room> findAvailableRooms(List<RecordFilter> filters) {
        List<AbstractQueryCondition> roomConditions = getQueryConditions(filters, Room.class);
        return roomDao.getAll(roomConditions);
    }

    @Override
    public void reserveRoom(Reservation reservation) {
        ValidationResponse validationResponse = reservationValidator.validate(reservation);
        if (validationResponse.isValid()) {
            reservationDao.insert(reservation);
        } else {
            throw new InvalidReservationException("Invalid reservation", validationResponse.getErrors());
        }
    }

    @Override
    public boolean isTimeSlotFree(long roomId, LocalDateTime from, LocalDateTime to) {
        return reservationDao.getCountWithinTimePeriod(roomId, from, to) == 0;
    }

    @Override
    public List<Reservation> getReservations(List<RecordFilter> filters) {
        List<AbstractQueryCondition> conditions = getQueryConditions(filters, Reservation.class);
        return reservationDao.getAll(conditions);
    }

    private <C> List<AbstractQueryCondition> getQueryConditions(List<RecordFilter> recordFilters, Class<C> clazz) {
        return recordFilters.stream()
                .filter(recordFilter -> recordFilter.isTargetClass(clazz))
                .map(RecordFilter::toQueryConditions)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
