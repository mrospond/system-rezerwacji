package com.example.reservationsystem;

import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.RoomReservationService;
import com.example.reservationsystem.service.filters.DynamicFilter;
import com.example.reservationsystem.service.filters.RecordFilter;
import com.example.reservationsystem.service.filters.rooms.RoomBuildingIdFilter;
import com.example.reservationsystem.service.filters.rooms.RoomMoreThanXSeatsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ReservationSystemApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(ReservationSystemApplication.class, args);
        /*LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2L);

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startTime(start)
                .endTime(end)
                .employeeId("BM51693")
                .roomId(4L)
                .build();*/
        RoomReservationService service = context.getBean(RoomReservationService.class);
        //service.reserveRoom(reservation);

        List<RecordFilter> filters = new ArrayList<>(List.of(
                RoomMoreThanXSeatsFilter.of(4),
                RoomBuildingIdFilter.of(4L),
                DynamicFilter.create()
                        .setTargetClass(Room.class)
                        .addCondition(ConditionBuilder.key("floor").equalTo(3))
        ));

        List<Room> rooms = service.findAvailableRooms(filters);
        System.out.println();
    }
}
