package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.DBSession;
import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.reflection.ReflectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
        Object[] args = ReflectionUtil.extractFieldsAsValuesArray(entity);
        dbSession.executeDml("insertIntoReservations", args);
    }

    @Override
    public void updateById(Long id, Reservation entity) {
        Object[] entityFields = ReflectionUtil.extractFieldsAsValuesArray(entity);
        dbSession.executeDml("updateReservationById", entityFields, id);
    }

    @Override
    public void deleteById(Long id) {
        dbSession.executeDml("deleteReservationById", id);
    }

    @Override
    public List<Reservation> getAll(List<AbstractQueryCondition> conditions) {
        return dbSession.queryMultiple("selectAllFromReservations", Reservation.class, conditions);
    }

    public int getCountWithinTimePeriod(long roomId, LocalDateTime from, LocalDateTime to) {
        return dbSession.queryOne("countReservationsByRoomIdAndTimePeriod", roomId, to, from);
    }
}
