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

    }

    @Override
    public void updateById(Long id, Reservation entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
