package com.example.reservationsystem.dto.mappers;

import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ReservationMapperImpl implements ReservationMapper {
    @Override
    public Reservation mapToReservation(ReservationDto dto) {
        LocalDateTime from = dto.getDate().atTime(dto.getFrom(), 0);
        LocalDateTime to = dto.getDate().atTime(dto.getTo(), 0);

        return Reservation.builder()
                .id((long) ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE))
                .roomId(dto.getRoomId())
                .startTime(from)
                .endTime(to)
                .build();
    }
}
