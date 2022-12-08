import psycopg2
import csv

DB_NAME = 'reservation_system'
DB_USER = 'admin'
DB_PASSWORD = 'admin'
DB_HOST = 'localhost'

INIT_FILE = 'init.sql'
CSV_DIR = '../csv-init/output/'
TABLE_NAMES = ['regions', 'countries', 'cities', 'buildings', 'rooms', 'employees']


def init_database():
    connection = None
    try:
        print('Connecting to database')
        connection = psycopg2.connect(
            host=DB_HOST,
            database=DB_NAME,
            user=DB_USER,
            password=DB_PASSWORD)
        cursor = connection.cursor()
        print('Creating tables')
        cursor.execute(load_sql_init())
        for table in TABLE_NAMES:
            print(f'Updating table: {table}')
            load_csv_to_db(table, cursor)
            connection.commit()
        cursor.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if connection is not None:
            connection.close()
            print('Database connection closed')


def load_sql_init():
    script = None
    with open(INIT_FILE, "r") as file:
        script = file.read()
    return script


def load_csv_to_db(table_name, cursor):
    rows = []
    with open(CSV_DIR + table_name + '.csv') as csv_file:
        reader = csv.reader(csv_file, delimiter=',')
        header_skipped = False
        for row in reader:
            for i in range(len(row)):
                if row[i] == '':
                    row[i] = None
            if header_skipped:
                rows.append(row)
            else:
                header_skipped = True
        statement = "INSERT INTO " + table_name + " VALUES(" + (len(rows[0]) - 1) * "%s," + "%s);"
        duplicates, distinct_rows = check_for_duplicates(rows)
        print(f'Dropped {duplicates} duplicate values for table: {table_name}')
        cursor.executemany(statement, distinct_rows)


def check_for_duplicates(rows):
    distinct = set()
    new_rows = []
    duplicates = 0
    for row in rows:
        if not distinct.__contains__(row[0]):
            distinct.add(row[0])
            new_rows.append(row)
        else:
            duplicates += 1
    return duplicates, new_rows


def main():
    init_database()


if __name__ == '__main__':
    main()
