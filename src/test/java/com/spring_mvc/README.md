[Markdown support](https://daringfireball.net/projects/markdown/syntax)

[Хоткеи в IntelliJ-IDEA](https://juja.com.ua/java/ide/intellij-idea-hotkeys) ([+](http://eax.me/intellij-idea-hotkeys))


### [Введение в spring web mvc](https://github.com/wizardjedi/my-spring-learning/wiki/Введение-в-spring-web-mvc)
* https://github.com/wizardjedi/my-spring-learning/wiki/Введение-в-spring-web-mvc
* https://javatalks.ru/topics/14912
* https://habrahabr.ru/post/86433
* http://translatedby.com/you/introduction-to-the-spring-framework-2-5/into-ru/?page=5
* https://reversecoding.net/spring-mvc-requestparam-binding-request-parameters
* [Interceptor — перехват запросов в Spring MVC](http://www.seostella.com/ru/article/2012/04/27/interceptor-perehvat-zaprosov-v-spring-mvc.html)
* http://javastudy.ru/spring-mvc/spring-mvc-handler-mapping
* [Что такое Spring Interceptor?](http://o7planning.org/ru/11229/spring-mvc-interceptors-tutorial)


```java
/**
 * По умолчанию интерфейс HandlerMapping в Spring MVC реализуется классом RequestMappingHandlerMapping.
 * Существуют и другие реализации интерфейса, которые используют другие параметры для поиска контроллера, соответствующего запросу.
 * В Spring MVC вы можете встретить реализацию интерфейса, когда применяете аннотацию @RequestMapping:
 */
@RequestMapping(value = "/", method = RequestMethod.GET)
public ModelAndView main() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("userJSP", new User());
    modelAndView.setViewName("index");
    return modelAndView;
}
```

![DispatcherServlet](DispatcherServlet.png)

![4764724](4764724.png)

```text
Spring Interceptor:
Когда вы приходите в компанию и хотите встретиться с менеджером этой компании, вам нужно пройти через перехватчики (Interceptor), здесь ими могут являться охранник, ресепшионист,...
```

```java
/**
 * Эти перехватчики (Interceptor) позволяют выполнять задачи, которые являются общими для каждого запроса или набора запросов, без необходимости копировать код в каждом методе контроллера
 * Например, можно выполнять аутентификацию пользователя прежде, чем запрос достигнет Вашего контроллера и, в случае успеха, получить некоторые дополнительные данные о пользователе из базы данных, добавив их в объект HttpServletRequest
 */
public class StatsInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(StatsInterceptor.class);
	
	/**
	 * Метод preHandle вызывается перед тем как Front-контроллер передает управление контроллеру
	 * (Если метод возвращает значение true, обработка передается контроллеру, ответственному за этот запрос)
     */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("Client User-Agent: " + request.getHeader("User-Agent"));
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * Метод postHandle вызывается после того как контроллер отработал и вернул результат
     */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * Метод afterCompletion вызывается после того как ответ сформирован
     */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
```

```xml
<!-- Теперь нам надо зарегистрировать этот Interseptor. Делается это в контексте сервлета добавлением следующих строк: -->
<interceptors>
	<beans:bean class="com.seostella.spring.interceptor.StatsInterceptor" />

	<!-- Также есть возможность "ловить" не все запросы, а ограничиться лишь некоторыми, делается это так: -->
    <interceptor>
        <mapping path="/weather/*" />
        <beans:bean
            class="com.seostella.spring.interceptor.ExecutionTimeInterceptor" />
    </interceptor>
</interceptors>
```


