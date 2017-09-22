[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))



https://habrahabr.ru/post/137543
https://doszhan.com/ru/2015/06/23/tutorial-kak-sozdat-veb-servis-axis-cherez-intellij-idea-chast-1-servis
http://spring-projects.ru/guides/producing-web-service
https://habrahabr.ru/post/148403
http://quality-lab.ru/soap-api-testing
https://gist.github.com/asilchev/3742724
http://ru.khroliz.com/2013/07/java-soap.html

* `SOAP` — это протокол для работы публичных веб-сервисов (чтобы обеспечить удобство и надёжность обмена, и чтобы клиент не парился на чём написан сервер, или как паковать в строки параметры...);
>           Есть 2-е версии протокола: `WSDL 1.1` и `WSDL 1.2`
* `WSDL` — это язык который используется для описания веб-сервисов (в WSDL-документе содержится информация о местонахождении сервиса и доступных методах)

* `Axis` — это движок для создания веб сервисов;
            (последний стабильный релиз Axis-а был опубликован в апреле 2006 г)
            (в данное время вместо Axis выпустили Axis2 — он имеет больше возможностей...)
            Генерируем классы для работы с веб-сервисом на основе WSDL:
```bash
wsimport -d d:\Idea\Mine\SoapBlogPost\src\ http://speller.yandex.net/services/spellservice?WSDL -Xnocompile
```

* `JAX-WS` — это стандарт;
* `CXF` (apache) — это какрас который имплементирует JAX-WS;
* `Spring-WS` — это SOAP web-сервис с использованием Spring;
>                Точки выхода сервиса:
>                `@Endpoint` — регистрирует класс Spring-WS как потенциальный кандидат для обработки входящих SOAP сообщений;
>                `@PayloadRoot` — используется Spring-WS для выбора метода обработчика на основе `namespace` и `localPart` сообщения;
>                `@RewuestPayload` — указывает что входящее сообщение будет сопоставлено параметру request-метода;
>                `@ResponsePayload` — создает соответствующее значение возвращаемому значению полезной части ответа; 

Расширения SOAP:
* `WS-Security` — позволяющий работать с шифрованием и электронными подписями;
* `WS-Policy` — с помощью которого можно управлять правами на использование сервиса;



