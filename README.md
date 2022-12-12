# System rezerwacji sal w korporacji
## Lokalna baza danych:
1. Do wygenerowania pseudolosowych danych w formacie csv służy skrypt csv_init.py. Jeżeli dane są już obecne w folderze output, można pominąć ten krok i przejść od razu do punktu 2.
2. Należy stworzyć obraz bazy PostgreSQL w Dockerze następującą komendą:
```
docker run --name reservation_system -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=reservation_system -p 5432:5432 -d postgres
```
3. Do wypełnienia lokalnej bazy danych wierszami z wygenerowanych plików csv należy uruchomić skrypt db_init.py.

## Zasady zastosowane przy generowaniu danych:
### 1. Regions, Countries, Cities
- pobrane wcześniej z internetu
### 2. Buildings
- wygenerowane losowo w każdym wybranym mieście (name = country_tag + nr budynku w obrębie kraju)
- w każdym mieście losowana jest liczba budynków (od 1 do 3, rozkład: 1 - 50% szans, 2 - 30% szans, 3 - 20% szans)
- na każdy budynek losowana jest liczba pięter (od 1 do 4, rozkład jednostajny)
### 3. Rooms
- na każdym piętrze budynku losowana jest liczba pokoi (od 8 do 16, rozkład jednostajny)
- dla każdego z pokoi losuje się również priorytet (od 1 do 5, rozkład: 1 - 10% szans, 2 - 20% szans, 3 - 20% szans, 4 - 20% szans, 5 - 30% szans)
- na podstawie priorytetu każdemu z pokoi przypisuje się liczbę miejsc siedzących (1 - 32 miejsca, 2 - 16 miejsc, 3 - 8 miejsc, 4 - 4 miejsca, 5 - 2 miejsca)
- dla każdego pokoju losowany jest również status (AVAILABLE/OUT_OF_SERVICE, rozkład: AVAILABLE - 99% szans, OUT_OF_SERVICE - 1% szans)
- losowany jest także atrybut video_conference_holder wskazujący na to, czy w pokoju mogą odbywać się wideokonferencje (true/false, rozkład: true - 70% szans, false - 30% szans)
- nazwa pokoju to losowo wybrana wartość spośród danych z 3 plików zawierających: imiona z greckiej mitologii, nazwy misji Apollo, nazwy miast
### 4. Employees
- na każde piętro budynku losowana jest najpierw liczba pracowników (od 50 do 100, rozkład jednostajny)
- dla każdego pracownika losuje się imię (z dostępnej listy imion, rozkład jednostajny) oraz nazwisko (z dostępnej listy nazwisk, rozkład jednostajny)
- losowana jest liczba (od 0 do 99999, rozkład jednostajny), która razem z inicjałami pracownika tworzy id pracownika (np. MK12345)
- generowany jest email w formacie first_name + "." + last_name + id + "@example.com" (przykład: ivy.jacobs56433@example.com)
- pracownik może pracować w delegacji, wówczas pole delegation_city_id w pliku csv zawierać będzie id miasta, w którym aktualnie przebywa. W przeciwnym wypadku id miasta będzie puste. Dla każdego pracownika losowana jest szansa na bycie w delegacji (5% szans). Jeżeli pracownik jest w delegacji, losuje się mu id miasta (rozkład normalny) ze wszystkich dostępnych (za wyjątkiem tego, w którym obecnie się znajduje)
- każdemu pracownikowi również losowany jest priorytet odpowiadający priorytetom pokoi. Pracownik będzie mógł rezerwować tylko pokoje o takim samym lub niższym priorytecie niż jego własny. Losowanie odbywa się w taki sam sposób (od 1 do 5, rozkład: 1 - 10% szans, 2 - 20% szans, 3 - 20% szans, 4 - 20% szans, 5 - 30% szans)
- za pomocą algorytmu bcrypt generowany jest hash hasła (hasłem jest imię pracownika, loginem jego email)
### 5. Priorytety
- 1 oznacza najwyższy priorytet dla zwykłego usera, 5 oznacza najniższy
- 0 oznacza najwyższy ogólnie możliwy priorytet, ale jest dostępny tylko dla adminów (wybranych ręcznie userów)
