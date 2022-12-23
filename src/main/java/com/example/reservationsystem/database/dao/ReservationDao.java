package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.DBSession;
import com.example.reservationsystem.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ReservationDao implements Dao<Long, Reservation> {
    private final DBSession dbSession;

    @Override
    public Reservation getById(Long id) {
        return dbSession.queryOne("selectReservationById", Reservation.class, id);
    }

    @Override
    public void insert(Reservation entity) {
        dbSession.executeDml("insertIntoReservations", entity);
    }

    @Override
    public void updateById(Long id, Reservation entity) {
        // TODO: 23.12.2022 Add fields' values array as argument once ReflectionUtil is implemented
        dbSession.executeDml("updateReservationById", id);
    }

    @Override
    public void deleteById(Long id) {
        dbSession.executeDml("deleteReservationById", id);
    }
}
