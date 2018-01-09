

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




SELECT TNAME, KOL, PRICE, "=" AS EQU, KOL*PRICE AS SUMMA
  FROM Tbl;

SELECT PNUM, DNUM, VOLUME
  FROM Tbl
  ORDER BY DNUM;

SELECT PNUM, DNUM, VOLUME
  FROM Tbl
  ORDER BY DNUM ASC, VOLUME DESC;