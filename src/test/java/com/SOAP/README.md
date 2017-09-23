[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))



https://habrahabr.ru/post/137543
https://doszhan.com/ru/2015/06/23/tutorial-kak-sozdat-veb-servis-axis-cherez-intellij-idea-chast-1-servis
http://spring-projects.ru/guides/producing-web-service
https://habrahabr.ru/post/148403
http://quality-lab.ru/soap-api-testing
https://gist.github.com/asilchev/3742724
http://ru.khroliz.com/2013/07/java-soap.html
http://info.javarush.ru/eGarmin/2015/03/14/Веб-сервисы-Шаг-1-Что-такое-веб-сервис-и-как-с-ним-работать-.html
http://info.javarush.ru/eGarmin/2015/03/22/Веб-сервисы-Шаг-2-Как-упростить-написание-клиента-.html
[JAX-WS-HelloWorld-RPC-Example](https://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example)

[SOAP работает с операциями, а REST – с ресурсами](https://habrahabr.ru/post/131343)
---
* `SOAP` — это протокол для работы публичных веб-сервисов (чтобы обеспечить удобство и надёжность обмена, и чтобы клиент не парился на чём написан сервер, или как паковать в строки параметры...);
>           (все, что мы хотим куда-то отправить через HTTP, сначала превращается в XML описание SOAP, потом засовывается в HTTP пакет и посылается на другой компьютер в сети по TCP/IP)
>           Есть 2-е версии протокола: `WSDL 1.1` и `WSDL 1.2`
* `WSDL` — это язык который используется для описания веб-сервисов (в WSDL-документе содержится информация о местонахождении сервиса и доступных методах)
            (это есть веб-сервис, тоесть программа методы которой можно удаленно вызывать)

* `JAX-WS` — это стандарт;
>             Сервер – это и есть веб-сервис, еще его называют `endpoint` (как конечная точка куда доходят SOAP сообщения от клиента):
>             1. Описать интерфейс нашего веб-сервиса;
>             2. Реализовать этот интерфейс;
>             3. Запустить наш веб-сервис;
>             4. Написать клиента и удаленно вызвать нужный метод веб-сервиса;
* `Apache CXF` — это какрас который имплементирует JAX-WS;
* `Spring-WS` — это SOAP web-сервис с использованием Spring;
>                Точки выхода сервиса:
>                `@Endpoint` — регистрирует класс Spring-WS как потенциальный кандидат для обработки входящих SOAP сообщений;
>                `@PayloadRoot` — используется Spring-WS для выбора метода обработчика на основе `namespace` и `localPart` сообщения;
>                `@RewuestPayload` — указывает что входящее сообщение будет сопоставлено параметру request-метода;
>                `@ResponsePayload` — создает соответствующее значение возвращаемому значению полезной части ответа; 
* `Axis` — это движок для создания веб сервисов, чтобы упростить написание клиента (утилита `wsimport`);
            (последний стабильный релиз Axis-а был опубликован в апреле 2006 г)
            (в данное время вместо Axis выпустили Axis2 — он имеет больше возможностей...)
            Генерируем классы для работы с веб-сервисом на основе WSDL:
```bash
wsimport -d d:\Idea\Mine\SoapBlogPost\src\ http://speller.yandex.net/services/spellservice?WSDL -Xnocompile
```

Расширения SOAP:
* `WS-Security` — позволяющий работать с шифрованием и электронными подписями;
* `WS-Policy` — с помощью которого можно управлять правами на использование сервиса;




http://khpi-iip.mipk.kharkiv.edu/library/sotii/labs/lab_soap_create.html
http://info.javarush.ru/eGarmin/2015/03/28/Без-пафоса-Поговорим-о-JavaEE-сервлетах-и-их-контейнерах.html
[SOAP HelloWorld](http://javastudy.ru/web-services/soap-java-hello-world-example)
http://axis.apache.org/axis/java/user-guide.html
https://habrahabr.ru/post/131343
http://citforum.ru/internet/xml/xml_rpc

https://www.ibm.com/developerworks/ru/library/j-jws12/index.html
[cxf-example](https://github.com/ExampleDriven/cxf-example/blob/master/src/main/webapp/WEB-INF/web.xml)
[Как поднять CXF Servlet](http://dev-blogs.com/cxfservlet)


