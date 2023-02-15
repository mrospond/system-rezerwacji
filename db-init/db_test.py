import unittest
import psycopg2

class TestTriggerFunctions(unittest.TestCase):

    DB_NAME = 'reservation_system'
    DB_USER = 'admin'
    DB_PASSWORD = 'admin'
    DB_HOST = 'localhost'

    EMPLOYEE_ID = 'AS95842'

    def setUp(self):
        self.connection = psycopg2.connect(
            host=self.DB_HOST,
            database=self.DB_NAME,
            user=self.DB_USER,
            password=self.DB_PASSWORD
        )
        self.cursor = self.connection.cursor()

    def test_overlap_trigger(self):
        self.cursor.execute(f"INSERT INTO reservations (id, room_id, employee_id, start_time, end_time) VALUES (1, 1, '{self.EMPLOYEE_ID}', NOW() + interval \'1 hour\', NOW() + interval \'2 hour\')")
        self.cursor.execute(f"INSERT INTO reservations (id, room_id, employee_id, start_time, end_time) VALUES (2, 1, '{self.EMPLOYEE_ID}', NOW() + interval \'1.5 hour\', NOW() + interval \'2.5 hour\')")
        self.assertRaises(psycopg2.errors.RaiseException, self.connection.commit)

    def test_priority_trigger(self):
        self.cursor.execute(f"INSERT INTO reservations (id, room_id, employee_id, start_time, end_time) VALUES (3, 1, '{self.EMPLOYEE_ID}', NOW() + interval \'1 hour\', NOW() + interval \'2 hour\')")
        self.assertRaises(psycopg2.errors.RaiseException, self.connection.commit)

    def test_city_trigger(self):
        self.cursor.execute(f"INSERT INTO reservations (id, room_id, employee_id, start_time, end_time) VALUES (4, 1, '{self.EMPLOYEE_ID}', NOW() + interval \'1 hour\', NOW() + interval \'2 hour\')")
        self.assertRaises(psycopg2.errors.RaiseException, self.connection.commit)

    def test_status_trigger(self):
        self.cursor.execute('UPDATE rooms SET status = \'OUT_OF_SERVICE\' WHERE id = 1')
        self.cursor.execute(f"INSERT INTO reservations (id, room_id, employee_id, start_time, end_time) VALUES (5, 1, '{self.EMPLOYEE_ID}', NOW() + interval \'1 hour\', NOW() + interval \'2 hour\')")
        self.assertRaises(psycopg2.errors.RaiseException, self.connection.commit)
    
    def tearDown(self):
        self.cursor.execute('TRUNCATE reservations')
        self.connection.close()

if __name__ == '__main__':
    unittest.main()