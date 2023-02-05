package com.example.reservationsystem.services;

import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.services.filters.RecordFilter;

import java.util.List;

public interface RoomReservationService {
    List<Room> findAvailableRooms(List<RecordFilter> filters);
    void reserveRoom(Reservation reservation);
}
