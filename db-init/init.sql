DROP TABLE IF EXISTS user_permissions;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS buildings;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS regions;

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

create table reservations(
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

CREATE TABLE user_permissions(
    id INT,
    max_reservation_time_hours FLOAT,
    max_room_size INT,
    max_reservations_per_day INT,
    PRIMARY KEY(id)
);

INSERT INTO user_permissions(id, max_reservation_time_hours, max_room_size, max_reservations_per_day)
VALUES
(0, 3, 32, 10),
(1, 2.5, 32, 8),
(2, 2, 16, 5),
(3, 1.5, 8, 3),
(4, 1.5, 4, 2),
(5, 1, 2, 1)
