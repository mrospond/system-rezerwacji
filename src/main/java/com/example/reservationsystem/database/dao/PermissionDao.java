package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.DBSession;
import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.domain.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class PermissionDao implements Dao<Long, Permissions> {
    private final DBSession dbSession;

    @Override
    public Permissions getById(Long id) {
        return dbSession.queryOne("selectPermissionById", Permissions.class, id);
    }

    @Override
    public void insert(Permissions entity) {

    }

    @Override
    public void updateById(Long id, Permissions entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Permissions> getAll(List<AbstractQueryCondition> conditions) {
        return null;
    }
}
