package com.example.reservationsystem.service;

import com.example.reservationsystem.domain.Employee;
import com.example.reservationsystem.dto.RoomFilterDto;
import com.example.reservationsystem.dto.mappers.RoomFilterMapper;
import com.example.reservationsystem.security.EmployeeUser;
import com.example.reservationsystem.service.filters.RecordFilter;
import com.example.reservationsystem.service.filters.rooms.RoomCityIdFilter;
import com.example.reservationsystem.service.filters.rooms.RoomPriorityLowerThanFilter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FilterServiceImpl implements FilterService {
    private final EmployeeService employeeService;
    private final RoomFilterMapper roomFilterMapper;

    @Override
    public List<RecordFilter> createUserSpecificFilters() {
        List<RecordFilter> recordFilters = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeUser user = (EmployeeUser) authentication.getPrincipal();
        Employee employee = employeeService.findEmployeeByEmail(user.getEmail());

        addCityRecordFilter(recordFilters, employee);
        addMaxRoomPriorityRecordFilter(recordFilters, employee);

        return recordFilters;
    }

    @Override
    public void addRoomFilter(List<RecordFilter> filters, RoomFilterDto filterDto) {
        filters.add(roomFilterMapper.mapToRoomFilter(filterDto));
    }

    @Override
    public void enhanceFilterDto(RoomFilterDto dto) {
        List<Boolean> floors = new ArrayList<>();
        floors.add(dto.isFloor1());
        floors.add(dto.isFloor2());
        floors.add(dto.isFloor3());
        floors.add(dto.isFloor4());
        dto.setFloors(floors);
    }

    private void addCityRecordFilter(List<RecordFilter> recordFilters, Employee employee) {
        Long delegationCityId = employee.getDelegationCityId();
        Long cityId = employee.getCityId();
        recordFilters.add(new RoomCityIdFilter(cityId, delegationCityId));
    }

    private void addMaxRoomPriorityRecordFilter(List<RecordFilter> recordFilters, Employee employee) {
        int priority = employee.getPriority();
        recordFilters.add(RoomPriorityLowerThanFilter.ofPriority(priority));
    }
}
