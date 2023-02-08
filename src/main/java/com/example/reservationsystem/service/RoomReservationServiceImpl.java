package com.example.reservationsystem.service;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.dao.ReservationDao;
import com.example.reservationsystem.database.dao.RoomDao;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {
    private final RoomDao roomDao;
    private final ReservationDao reservationDao;

    @Override
    public List<Room> findAvailableRooms(List<RecordFilter> filters) {
        List<AbstractQueryCondition> roomConditions = getQueryConditions(filters, Room.class);
        return roomDao.getAll(roomConditions);
    }

    @Override
    public void reserveRoom(Reservation reservation) {
        reservationDao.insert(reservation);
    }

    private <C> List<AbstractQueryCondition> getQueryConditions(List<RecordFilter> recordFilters, Class<C> clazz) {
        return recordFilters.stream()
                .filter(recordFilter -> recordFilter.isTargetClass(clazz))
                .map(RecordFilter::toQueryConditions)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
