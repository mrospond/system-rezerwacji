import csv
import math
import random
import json
from hashlib import sha256

geo_file_name = 'baza_rooms_-_Arkusz1.csv'
rooms_file_name = 'room_names.csv'
employee_names_file_name = 'employee_names.csv'
employee_surnames_file_name = 'employee_surnames.csv'
launches_file_name = 'launches.json'
greek_names_file_name = 'greek_names.json'

regions = []
countries = []
cities = []
buildings = []
rooms = []
employees = []
room_names = []
employee_names = []
employee_surnames = []


def main():
    read_regions_countries_cities()
    read_csv_names(rooms_file_name, room_names)
    read_json_names()
    read_json_launches()
    read_csv_names(employee_names_file_name, employee_names)
    read_csv_names(employee_surnames_file_name, employee_surnames)
    generate_buildings()
    generate_rooms()
    generate_employees()
    save_csv(regions, 'output/regions.csv')
    save_csv(countries, 'output/countries.csv')
    save_csv(cities, 'output/cities.csv')
    save_csv(buildings, 'output/buildings.csv')
    save_csv(rooms, 'output/rooms.csv')
    save_csv(employees, 'output/employees.csv')


def read_regions_countries_cities():
    with open(geo_file_name) as file:
        reader = csv.reader(file, delimiter=',')
        i = 0
        header = {}
        for row in reader:
            if i == 1:
                header = row
            elif i > 1:
                if row[0]:
                    regions.append({
                        header[0]: row[0],
                        header[1]: row[1]
                    })
                if row[2]:
                    countries.append({
                        header[2]: row[2],
                        header[3]: row[3],
                        header[4]: row[4],
                        header[5]: row[5]
                    })
                if row[6]:
                    cities.append({
                        header[6]: row[6],
                        header[7]: row[7],
                        header[8]: row[8]
                    })
            i += 1


def read_csv_names(file_path, result_list):
    with open(file_path) as file:
        reader = csv.reader(file, delimiter=',')
        header_skipped = False
        for row in reader:
            if header_skipped:
                result_list.append(row[0])
            header_skipped = True


def generate_buildings():
    building_id = 1
    current_building_in_country = 1
    previous_country_id = 1
    floors_per_building_min = 1
    floors_per_building_max = 4
    for city in cities:
        if previous_country_id != city["country_id"]:
            previous_country_id = city["country_id"]
            current_building_in_country = 1
        # 1 - 50% / 2 - 30% / 3 - 20%
        num_of_buildings = get_random_value([1, 2, 3], [50, 30, 20])
        country_code = get_country_code_for_city(city)
        for i in range(num_of_buildings):
            buildings.append({
                "id": building_id,
                "name": country_code + f"{current_building_in_country:04d}",
                "floors": random.randint(floors_per_building_min, floors_per_building_max),
                "city_id": city["id"]
            })
            current_building_in_country += 1
            building_id += 1


def generate_rooms():
    room_id = 1
    rooms_per_floor_min = 8
    rooms_per_floor_max = 16
    for building in buildings:
        current_room_names = room_names.copy()
        for floor in range(1, building["floors"] + 1):
            room_num = random.randint(rooms_per_floor_min, rooms_per_floor_max)
            for i in range(room_num):
                # 1 - 10% / 2 - 20% / 3 - 20% / 4 - 20% / 5 - 30%
                priority = get_random_value([1, 2, 3, 4, 5], [10, 20, 20, 20, 30])
                # depending on priority:
                # 1 - 32 seats / 2 - 16 seats / 3 - 8 seats / 4 - 4 seats / 5 - 2 seats
                seats = 2 ** (6 - priority)
                status = get_random_value(["AVAILABLE", "OUT_OF_SERVICE"], [99, 1])
                name = current_room_names.pop(random.randrange(len(current_room_names)))
                rooms.append({
                    "id": room_id,
                    "building_id": building["id"],
                    "priority": priority,
                    "status": status,
                    "floor": floor,
                    "name": name,
                    "seats": seats,
                    "video_conference_holder": get_random_value([False, True], [30, 70])
                })
                room_id += 1


def generate_employees():
    for building in buildings:
        for floor in range(building['floors']):
            employees_num = random.randint(50, 100)
            for i in range(employees_num):
                generate_employee(building)


def generate_employee(building):
    employee_id = random.randrange(10000)
    name = employee_names[random.randrange(len(employee_names))]
    surname = employee_surnames[random.randrange(len(employee_surnames))]
    email = name.lower() + '.' + surname.lower() + f"{employee_id:05d}" + '@example.com'
    employee_db_id = name[0] + surname[0] + f"{employee_id:05d}"
    city_id = building['city_id']
    delegation = get_random_value([True, False], [5, 95])
    del_city_id = None
    if delegation:
        del_city_id = city_id
        while del_city_id == city_id:
            del_city_id = cities[random.randrange(len(cities))]['id']
    # 1 - 10% / 2 - 20% / 3 - 20% / 4 - 20% / 5 - 30%
    priority = get_random_value([1, 2, 3, 4, 5], [10, 20, 20, 20, 30])
    password_hash = sha256(name.encode('utf-8')).hexdigest()
    employees.append({
        "id": employee_db_id,
        "city_id": city_id,
        "delegation_city_id": del_city_id,
        "first_name": name,
        "last_name": surname,
        "email": email,
        "password_hash": password_hash,
        "priority": priority
    })


def read_json_names():
    with open(greek_names_file_name) as file:
        result = json.load(file)
        persons = result['persons']
        for person in persons:
            room_names.append(person['name'])


def read_json_launches():
    with open(launches_file_name) as file:
        result = json.load(file)
        for launch in result:
            room_names.append(launch['mission_name'])


def get_country_code_for_city(city):
    for country in countries:
        if country["id"] == city["country_id"]:
            return country["country_code"]


# len(distribution) == len(values)
def get_random_value(values, distribution):
    ranges = []
    previous = 0
    for p in distribution:
        ranges.append(p + previous)
        previous += p
    number = math.floor(random.random() * 100)
    index = 0
    for bound in ranges:
        if number < bound:
            index = ranges.index(bound)
            break
    return values[index]


def save_csv(data, file_name):
    with open(file_name, 'w', newline='') as file:
        writer = csv.DictWriter(file, delimiter=',', fieldnames=data[0].keys())
        writer.writeheader()
        for entry in data:
            writer.writerow(entry)


if __name__ == '__main__':
    main()
