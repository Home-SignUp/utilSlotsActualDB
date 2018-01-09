

https://github.com/Home-GWT/TopLinkExample
https://github.com/Home-Java8/Java8.2
https://github.com/Home-Java8/Java8.2/blob/master/src/com/news/stream_api/README.md
https://github.com/Home-Java8/Java8.2/blob/master/src/com/news/lambda/README.md
https://github.com/Home-Java8/Java8/blob/master/src/com/lambda/Source1.java
https://github.com/Home-Java8/Java8/blob/master/src/com/lambda/Source0.java

http://citforum.ck.ua/database/dblearn/dblearn05.shtml
(sql операторы примеры)
http://lib.ru/SCHOOL/schoolsql.txt_with-big-pictures.html
http://2sql.ru/novosti/sql-where
http://2sql.ru/novosti/sql-in
(java 8 reduce примеры)
+ https://habrahabr.ru/company/luxoft/blog/270383
- https://habrahabr.ru/post/302628
. https://habrahabr.ru/post/216431
- https://easyjava.ru/java/language/java-8-stream-api-chast-vtoraya-map-reduce
+ https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
+ https://annimon.com/article/2778


--
-- http://citforum.ck.ua/database/dblearn/dblearn05.shtml
--

--
-- Операторы манипулирования данными:
--
-- SELECT - отобрать строки из таблиц
-- INSERT - добавить строки в таблицу
-- UPDATE - изменить строки в таблице
-- DELETE - удалить строки в таблице
-- COMMIT - зафиксировать внесенные изменения
-- ROLLBACK - откатить внесенные изменения

--
-- Операторы защиты и управления данными:
--
-- CREATE ASSERTION - создать ограничение
-- DROP ASSERTION - удалить ограничение
-- GRANT - предоставить привилегии пользователю или приложению на манипулирование объектами
-- REVOKE - отменить привилегии пользователя или приложения

-- ---------------------------------------------------------

SELECT *
  FROM Tbl;

INSERT INTO Tbl
  (PNUM, PNAME) VALUES (4, "Иванов");

UPDATE Tbl
  SET PNAME = "Пушников"
  WHERE PNUM = 1;

DELETE FROM Tbl
  WHERE PNUM = 1;



-- Использование скалярных выражений
SELECT TNAME, KOL, PRICE, "=" AS EQU, KOL*PRICE AS SUMMA
  FROM Tbl;

-- Упорядочение результатов запроса
SELECT PNUM, DNUM, VOLUME
  FROM Tbl
  ORDER BY DNUM;

-- Упорядочение результатов запроса по нескольким полям с возрастанием или убыванием
SELECT PNUM, DNUM, VOLUME
  FROM Tbl
  ORDER BY DNUM ASC, VOLUME DESC;

-- соединение таблиц
SELECT Tbl1.PNUM, Tbl1.PNAME, Tbl2.DNUM, Tbl2.VOLUME
  FROM Tbl1, Tbl2
  WHERE Tbl1.PNUM = Tbl2.PNUM;

-- Использование агрегатных функций в запросах
SELECT COUNT(*) AS N
  FROM Tbl;

SELECT
    SUM(VOLUME) AS SM,
    MAX(VOLUME) AS MX,
    MIN(VOLUME) AS MN,
    AVG(VOLUME) AS AV
  FROM Tbl;

-- Использование агрегатных функций с группировками
SELECT DNUM, SUM(VOLUME) AS SM
  FROM Tbl
  GROUP BY DNUM;

SELECT DNUM, SUM(VOLUME) AS SM
  FROM Tbl
  GROUP BY DNUM
  HAVING SUM(VOLUME) > 400;

-- Использование подзапросов
SELECT *
  FROM Tbl1
  WHERE Tbl1.PNUM IN
      (SELECT DISTINCT Tbl2.PNUM
       FROM Tbl2
       WHERE Tbl2.DNUM = 2);

-- объединение двух подзапросов
SELECT PNAME
  FROM Tbl
  WHERE STATUS > 3
UNION
  SELECT Tbl1.PNAME
  FROM Tbl1, Tbl2
  WHERE Tbl1.PNUM = Tbl2.PNUM AND Tbl2.DNUM = 2;






