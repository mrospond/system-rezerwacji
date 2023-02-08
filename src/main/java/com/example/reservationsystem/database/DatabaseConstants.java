package com.example.reservationsystem.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DatabaseConstants {
    public static final String ROOM_PREFIX = "r.";
    public static final String RESERVATION_PREFIX = "res.";
    public static final String EMPLOYEE_PREFIX = "e.";
    public static final String BUILDING_PREFIX = "b.";
}
