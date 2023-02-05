package com.example.reservationsystem.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reservation {
    private Long id;
    private Long roomId;
    private String employeeId;
    private Boolean reoccurring;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private String lastUpdateBy;
    private LocalDateTime lastUpdateTime;

    Reservation(Long id, Long roomId, String employeeId, Boolean reoccurring, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime createTime, String lastUpdateBy, LocalDateTime lastUpdateTime) {
        this.id = id;
        this.roomId = roomId;
        this.employeeId = employeeId;
        this.reoccurring = reoccurring;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Reservation() {

    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    public static class ReservationBuilder {
        private Long id;
        private Long roomId;
        private String employeeId;
        private Boolean reoccurring;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private LocalDateTime createTime;
        private String lastUpdateBy;
        private LocalDateTime lastUpdateTime;

        ReservationBuilder() {
        }

        public ReservationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReservationBuilder roomId(Long roomId) {
            this.roomId = roomId;
            return this;
        }

        public ReservationBuilder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public ReservationBuilder reoccurring(Boolean reoccurring) {
            this.reoccurring = reoccurring;
            return this;
        }

        public ReservationBuilder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public ReservationBuilder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public ReservationBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public ReservationBuilder lastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
            return this;
        }

        public ReservationBuilder lastUpdateTime(LocalDateTime lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
            return this;
        }

        public Reservation build() {
            return new Reservation(id, roomId, employeeId, reoccurring, startTime, endTime, createTime, lastUpdateBy, lastUpdateTime);
        }

        public String toString() {
            return "Reservation.ReservationBuilder(id=" + this.id + ", roomId=" + this.roomId + ", employeeId=" + this.employeeId + ", reoccurring=" + this.reoccurring + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", createTime=" + this.createTime + ", lastUpdateBy=" + this.lastUpdateBy + ", lastUpdateTime=" + this.lastUpdateTime + ")";
        }
    }
}
