package com.example.reservationsystem.services;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.dao.ReservationDao;
import com.example.reservationsystem.database.dao.RoomDao;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.services.filters.RecordFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private <T> List<AbstractQueryCondition> getQueryConditions(List<RecordFilter> recordFilters, Class<T> clazz) {
        return recordFilters.stream()
                .filter(recordFilter -> recordFilter.isTargetClass(clazz))
                .map(RecordFilter::toQueryConditions)
                .flatMap(List::stream)
                .toList();
    }
}
