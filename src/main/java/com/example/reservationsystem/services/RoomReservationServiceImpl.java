package com.example.reservationsystem.services;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.dao.ReservationDao;
import com.example.reservationsystem.database.dao.RoomDao;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.services.filters.RecordFilter;
import com.example.reservationsystem.services.filters.reservations.ReservationInRoomIdsFilter;
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
        // najpierw znaleźć pokoje według kryteriów
        List<AbstractQueryCondition> roomConditions = getQueryConditions(filters, Room.class);
        List<Room> rooms = roomDao.getAll(roomConditions);
        // potem pobrać z bazy danych wszystkie rezerwacje, które zawierają id tych pokojów
        List<Long> roomIds = rooms.stream()
                .map(Room::getId)
                .toList();
        filters.add(ReservationInRoomIdsFilter.of(roomIds));
        List<AbstractQueryCondition> reservationConditions = getQueryConditions(filters, Reservation.class);
        List<Long> occupiedRooms = reservationDao.getAll(reservationConditions).stream()
                .map(Reservation::getRoomId)
                .toList();
        // zwrócić wszystkie pokoje oprócz tych, które są zarezerwowane
        return rooms.stream().filter(room -> !occupiedRooms.contains(room.getId())).toList();

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
