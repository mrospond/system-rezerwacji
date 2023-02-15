DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS buildings;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS regions;
DROP TABLE IF EXISTS user_permissions;

CREATE TABLE regions(
    id INT,
    name varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE countries(
    id INT,
    country_code VARCHAR(5),
    name VARCHAR(30),
    region_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(region_id) REFERENCES regions(id)
);

CREATE TABLE cities(
    id INT,
    name VARCHAR(50),
    country_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES countries(id)
);

CREATE TABLE buildings(
    id INT,
    name VARCHAR(10),
    floors INT,
    city_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(city_id) REFERENCES cities(id)
);

CREATE TABLE user_permissions(
    id INT,
    max_reservation_time_hours FLOAT,
    max_room_size INT,
    max_reservations_per_day INT,
    PRIMARY KEY(id)
);

CREATE TABLE rooms(
    id INT,
    building_id INT,
    priority INT,
    status VARCHAR(30),
    floor INT,
    name VARCHAR(50),
    seats INT,
    video_conference_holder BOOLEAN,
    PRIMARY KEY(id),
    FOREIGN KEY(building_id) REFERENCES buildings(id),
    FOREIGN KEY(priority) REFERENCES user_permissions(id)
);

CREATE TABLE employees(
    id VARCHAR(10),
    city_id INT,
    delegation_city_id INT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    password_hash VARCHAR(128),
    priority INT,
    PRIMARY KEY(id),
    FOREIGN KEY(city_id) REFERENCES cities(id),
    FOREIGN KEY(delegation_city_id) REFERENCES cities(id),
    FOREIGN KEY(priority) REFERENCES user_permissions(id)
);

CREATE TABLE reservations(
    id INT,
    room_id INT,
    employee_id VARCHAR(10),
    reoccurring BOOLEAN,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    create_time TIMESTAMP,
    last_update_by VARCHAR(10),
    last_update_time TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(room_id) REFERENCES rooms(id),
    FOREIGN KEY(employee_id) REFERENCES employees(id)
);

CREATE FUNCTION TR_Reservations_CheckOverlap()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT * FROM reservations r
               WHERE r.room_id = NEW.room_id
               AND (NEW.start_time BETWEEN r.start_time AND r.end_time
               OR NEW.end_time BETWEEN r.start_time AND r.end_time)) THEN
        RAISE EXCEPTION 'Reservation overlaps with existing one';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_Reservations_CheckOverlap BEFORE INSERT ON reservations
FOR EACH ROW
EXECUTE PROCEDURE TR_Reservations_CheckOverlap();

CREATE FUNCTION TR_Reservations_CheckPriority()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT priority FROM rooms WHERE id = NEW.room_id) < (SELECT priority FROM employees WHERE id = NEW.employee_id) THEN
        RAISE EXCEPTION 'Priority not high enough to reserve this room';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_Reservations_CheckPriority BEFORE INSERT ON reservations
FOR EACH ROW
EXECUTE PROCEDURE TR_Reservations_CheckPriority();

--CREATE FUNCTION TR_Reservations_CheckDate()
--RETURNS TRIGGER AS $$
--BEGIN
--    IF NEW.start_time < CURRENT_TIMESTAMP THEN
--        RAISE EXCEPTION 'Cannot reserve a room for a date in the past';
--    END IF;
--    RETURN NEW;
--END;
--$$ LANGUAGE plpgsql;

--CREATE TRIGGER TR_Reservations_CheckDate BEFORE INSERT ON reservations
--FOR EACH ROW
--EXECUTE PROCEDURE TR_Reservations_CheckDate();

CREATE FUNCTION TR_Reservations_CheckCity()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT city_id FROM buildings WHERE id = (SELECT building_id FROM rooms WHERE id = NEW.room_id)) <> (SELECT city_id FROM employees WHERE id = NEW.employee_id) AND (SELECT delegation_city_id FROM employees WHERE id = NEW.employee_id) <> (SELECT city_id FROM buildings WHERE id = (SELECT building_id FROM rooms WHERE id = NEW.room_id)) THEN
        RAISE EXCEPTION 'Employee not allowed to reserve this room';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_Reservations_CheckCity BEFORE INSERT ON reservations
FOR EACH ROW
EXECUTE PROCEDURE TR_Reservations_CheckCity();

CREATE FUNCTION TR_Reservations_CheckStatus()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT status FROM rooms WHERE id = NEW.room_id) = 'OUT_OF_SERVICE' THEN
        RAISE EXCEPTION 'Room is out of service, reservation not allowed';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER TR_Reservations_CheckStatus BEFORE INSERT ON reservations
FOR EACH ROW
EXECUTE PROCEDURE TR_Reservations_CheckStatus();

INSERT INTO user_permissions(id, max_reservation_time_hours, max_room_size, max_reservations_per_day)
VALUES
(0, 3, 32, 10),
(1, 2.5, 32, 8),
(2, 2, 16, 5),
(3, 1.5, 8, 3),
(4, 1.5, 4, 2),
(5, 1, 2, 1);
