[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))


### [Оператор SQL HAVING](http://2sql.ru/novosti/sql-having)
* [Оператор SQL HAVING](http://2sql.ru/novosti/sql-having)
* http://www.firststeps.ru/sql/oracle/r.php?36
* https://technet.microsoft.com/ru-ru/library/ms184262(v=sql.105).aspx
* http://www.sql-tutorial.ru/ru/book_having_clause.html
* [Оператор SQL LIMIT](http://2sql.ru/novosti/sql-limit)
* http://theory.phphtml.net/sql/having.html

Оператор SQL HAVING является указателем на результат выполнения агрегатных функций.

Агрегатной функцией в языке SQL называется функция, возвращающая какое-либо одно значение по набору значений столбца.
Такими функциями являются: `COUNT()`, `MIN()`, `MAX()`, `AVG()`, `SUM()`
`HAVING aggregate_function(column_name) operator value`

```
Ниже приведен порядок обработки предложений в операторе SELECT:
    FROM
    WHERE
    GROUP BY
    HAVING
    SELECT
    ORDER BY 
```

Имеется следующая таблица Artists:

Singer         | Album                           | Year | Sale
---------------|---------------------------------|------|--------
The Prodigy    | Invaders Must Die               | 2008 | 1200000
Drowning Pool  | Sinner                          | 2001 | 400000
Massive Attack | Mezzanine                       | 1998 | 2300000
The Prodigy    | Fat of the Land                 | 1997 | 600000
The Prodigy    | Music For The Jilted Generation | 1994 | 1500000
Massive Attack | 100th Window                    | 2003 | 1200000
Drowning Pool | Full Circle                      | 2007 | 800000
Massive Attack | Danny The Dog                   | 2004 | 1900000
Drowning Pool | Resilience                       | 2013 | 500000


Пример 1. Используя оператор SQL HAVING вывести название исполнителей (Singer) число продаж альбомов (Sale) которого больше 2000000:

```sql
select Singer, SUM(Sale)
FROM Artist
GROUP BY Singer
HAVING SUM(Sale) > 2000000;
```

Результат:

Singer         | Sum(Sale)
---------------|---------
Massive Attack | 54000000
The Prodigy    | 33000000


Пример 2. Используя оператор SQL HAVING вывести название исполнителя, который исполнялся еще до 1995 года:

```sql
SELECT Singer, MIN(Year)
FROM Artist
GROUP BY Singer
HAVING MIN(Year) < 1995;
```

Результат:

Singer      | MIN(Year)
------------|---------
The Prodigy | 1994





Для каждого офиса, в котором работают 2-а и более человек, вычислить общий плановый и фактический объем продаж для всех служащих офиса. 

```sql
SELECT city, SUM(quota), SUM(salesreps.sales)
FROM offices, salesreps
WHERE offices = rep_office
GROUP BY city
HAVING COUNT(*) >= 2;
```

CITY                           SUM(QUOTA) SUM(SALESREPS.SALES)
------------------------------ ---------- --------------------
Контрогайка                           700              835,915
Красный Мотоцикл                      575              692,637
Чугуевск                             1175             1121,084

```
Здесь хорошо видно, что присутствуют оба выражения и WHERE и HAVING, каждый выполняет свою функцию в запросе:
1. Объединяются таблицы OFFICES и SALESREPS для того, чтобы найти город, в котором работает служащий.
2. Группируются строки объединенной таблицы по офисам.
3. Исключаются группы, содержащие две или менее строки - это те строки, которые не удовлетворяют критерию предложения HAVING.
4. Вычисляются общие плановые и фактические объемы продаж для каждой группы. 
```



Показать цену, количество на складе и общее количество заказанных единиц для каждого наименования товара, если для него общее количество заказанных единиц превышает 75 процентов от количества товара на складе.

```sql
SELECT DESCRIPTION, PRICE, QTY_ON_HAND, SUM(QTY)
FROM PRODUCTS, ORDERS
WHERE MFR = MFR_ID
GROUP BY MFR_ID, PRODUCT_ID, DESCRIPTION, PRICE, QTY_ON_HAND
HAVING SUM(QTY) > (0.75 * QTY_ON_HAND)
ORDER BY QTY_ON_HAND DESC;
```

DESCRIPTION             PRICE    QTY_ON_HAND   SUM(QTY)
------------------ ---------- -------------- ----------
Лампа настольная           55            277        223
Рейка деревянная          107            207        223
Носки черные               76            167        223
Рейка пластмассовая       117            139        223
Труба алюминиевая         355             38         32
Карандаш простой           25             37        223
Подушка ватная            177             37         32
Нож специальный           475             32         30
Монитор LG               2500             28        223
Наушники SONY             975             28         30
Коробка картонная        2,75             25        223
Рубероид рулоны           250             24         30
Электродвигатель          243             15         26
Бочка пластмассовая       350             14         60
Доска профильная         4500             12         60
Телевизор SAMSUNG        4500             12         60
Профиль специальный      1875              9         30
Осветитель ртутный       1425              5         30
Тарелка фарфоровая        180              0          2

19 rows selected




Cat(name, ago, owner_id);
Owner(id);
ManyToOne

```sql
SELECT owner_id, MAX(name)
FROM Cat, Owner
GROUP BY owner_id;
```
