[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))



http://www.javenue.info/post/16
http://study-and-dev.com/blog/java_velocity_1


Velocity
---
`Это движок для обработки шаблонов который позволяет напрямую обращаться к методам и полям Java-классов.`
С помощью этого движка можно генерировать HTML-страницы, SQL-запросы, XML-документы и многое другое...
(В среде разработки Intellij IDEA есть встроенная поддержка `Velocity`)

    язык шаблонов VTL прост в изучении (в чем вы убедитесь дальше);
    отделяет Java-код от кода веб-страниц;
    служит отличной альтернативой для JSP, JSF, Freemarker и т.д.;
    может быть использован при создании библиотек тэгов для JSP;
    потенциально позволяет веб-дизайнеру и программисту работать раздельно.

* Переменные в VTL начинаются с символа `$`, например `$var`. В случае, если после переменной сразу идет текст, имя переменной берется в фигурные скобки - `${var}iable`. (Запись $variable указывает на совсем другой объект)
* Свойтва - это поля класса, к ним можно доступиться так - `$var.Property`. При выполнении этого кода, будет вызван метод `getProperty()` для соответсвующего Java-объекта.
* Методы в теплейтах можно вызывать таким образом: `$var.doSomething()`.

* Для инициализации переменной используется `конструкция-set`, например
```javascript
#set ($var = "value")
```

* есть конструкции `if-else`, `if-elseif`
```javascript
#if ($var > 5) <strong>greater</strong>
#elseif ($var < 5) <strong>lesser</strong>
#else <strong>equal</strong>
#end
```

* Велосити (VTL) предоставляет только один вариант цикла. Он реализуется через оператор `foreach`
```javascript
<ul> 
#foreach($var in $varArray)
<li>$var</li>
#end
</ul>
```

* Переменная varArray может представлять собой массив, вектор или хэш-таблицу
* Оператор `include` позволяет вставить содержимое текстового файла в темплейт
```javascript
#include("file.txt")
```

* Оператор `parse` необходим для вставки другого файла-шаблона VTL
```javascript
#parse("template.vm")
```

* Оператор `macro` используется для написания макросов
```javascript
#macro(br) <br /> 
#end
```




Для того, чтобы Intellij IDEA могла проводить анализ кода прямо в vm-файлах, можно воспользоваться такой конструкцией:
```javascript
#* @vtlvariable name="user" type="javenue.User" *#
```


