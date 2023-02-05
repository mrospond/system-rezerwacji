package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.DBSession;
import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.domain.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoomDao implements Dao<Long, Room> {
    private final DBSession dbSession;

    @Override
    public Room getById(Long id) {
        return null;
    }

    @Override
    public void insert(Room entity) {

    }

    @Override
    public void updateById(Long id, Room entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Room> getAll(List<AbstractQueryCondition> conditions) {
        return dbSession.queryMultiple("selectAllFromRooms", Room.class, conditions);
    }
}
