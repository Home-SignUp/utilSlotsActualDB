[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))


[Java 8 Optional Example](https://examples.javacodegeeks.com/core-java/util/optional/java-8-optional-example)
---
     Optional — это контейнер объекта, который может содержать или не содержать ненулевое значение.
                 Класс Optional призванный помочь разработчикам в обработке NullPointerException.
                 Можно вообще запретить назначать тем или иным полям класса значения равные null.
                 Java не запрещает делать этого, но с Optional это становится немного удобнее и нагляднее.

Optional:

    ПРОМЕЖУТОЧНЫЕ МЕТОДЫ (возвращают Optional)
    --------------------[ статические ]
     1. empty() — создает пустой объект (типа обвертка)
     2. of(..) — создает объект (не пустой) со значением
     3. ofNullable(..) — создает объект с проверкой на NULL (или пустой или со значением)
     --------------------[ объектные ]
     4. filter(<FunctionalInterface>) — условие по которому выполняется поиск (проверка) объекта
     5. map(<FunctionalInterface>) — преобразовывает (связывает) объект с ключем и возвращает ключ этого объекта
      
    ТЕРМИНАЛЬНЫЕ МЕТОДЫ
    -------------------[ объектные ]
     6. get() — возвращает сам объект
     7. isPresent(..) — возвращает TRUE/FALSE по наличию или отсутствию объекта
     8. orElse(..) — возвращает какое-либо значение объекта либо (в случае отсутствия значения) значение по умолчанию 
     9. orElseThrow(..) — возвращает какое-либо значение объекта либо (в случае отсутствия значения) выбрасывает исключение
    10. <VOID> ifPresent(..) — выводит на печать значение объекта (только) по наличию объекта


* https://habrahabr.ru/post/225641
* https://habrahabr.ru/post/256057