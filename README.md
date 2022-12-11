# System rezerwacji sal w korporacji
Lokalna baza danych:
1. Do wygenerowania pseudolosowych danych w formacie csv służy skrypt csv_init.py. Jeżeli dane są już obecne w folderze output, można pominąć ten krok i przejść od razu do punktu 2.
2. Należy stworzyć obraz bazy PostgreSQL w Dockerze następującą komendą:
```
docker run --name reservation_system -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=reservation_system -p 5432:5432 -d postgres
```
3. Do wypełnienia lokalnej bazy danych wierszami z wygenerowanych plików csv należy uruchomić skrypt db_init.py.
