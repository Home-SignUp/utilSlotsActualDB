package com.java8;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Test;

    /**
     * @see https://examples.javacodegeeks.com/core-java/util/optional/java-8-optional-example/
     *      https://habrahabr.ru/post/256057
     *      https://habrahabr.ru/post/225641
     *      ********************************
     * Optional — это контейнер объекта, который может содержать или не содержать ненулевое значение.
     *             Класс Optional призванный помочь разработчикам в обработке NullPointerException.
     *
     *             Можно вообще запретить назначать тем или иным полям класса значения равные null.
     *             Java не запрещает делать этого, но с Optional это становится немного удобнее и нагляднее.
     */
public class OptionalTest {

    @SuppressWarnings("null")
    @Test
    public void testTypicalNullPointer(){
        String strNull = null;

        try {
            // typical NullPointerException, even the compiler is saying something: Null pointer
            // access: The variable strNull can only be null at this location
            System.out.println(strNull.contains("something"));
        } catch(NullPointerException e){
            System.err.println("Expected NullPointerException...");
        }
    }

    /**
     * Optional:
     * — empty()
     */
    @Test
    public void testEmptyOptionalCreation(){
        // empty
        Optional<String> emptyOptional = Optional.empty(); // Optional<?> EMPTY = new Optional<>();

        try {
            System.out.println(emptyOptional.get());
        } catch(NoSuchElementException e){
            System.err.println("Expected NoSuchElementException...");
        }
    }

    /**
     * Optional:
     * — of(..)
     */
    @Test
    public void testNonEmptyOptional(){
        // non empty
        Optional<String> nonEmptyOptional = Optional.of("string"); // new Optional<>(value)

        System.out.println(nonEmptyOptional.get());
    }

    /**
     * Optional:
     * — ofNullable(..)
     */
    @Test
    public void testNullableOptional(){
        String strNull = null;
        Optional<String> nullableOptional = Optional.ofNullable(strNull); // Optional<?> = new Optional<>() или Optional<>(value)

        try {
            System.out.println(nullableOptional.get());
        } catch(NoSuchElementException ex){
            System.err.println("Expected NoSuchElementException...");
        }
    }

    /**
     * Optional:
     * — empty()
     * — of(..)
     * — filter(..)
     * — ifPresent(..)
     */
    @Test
    public void testFilter(){
        // если значение отсутствует
        Optional<Car> carOptionalEmpty = Optional.empty();             // Optional<?> EMPTY = new Optional<>();
        // если значение присутствует
        Optional<Car> carOptional = Optional.of(new Car("250")); // new Optional<>(value)

        carOptionalEmpty
                .filter(x -> x.getPrice().equals("250")) // Optional<T> filter(Predicate<? super T> predicate)
                .ifPresent(System.out::print);           // .ifPresent(x -> System.out.print(x.getPrice())); // void ifPresent(Consumer<? super T> consumer

        // если фильтр не пропускает значение
        carOptional
                .filter(x -> x.getPrice().equals("3333")) // Optional<T> filter(Predicate<? super T> predicate)
                .ifPresent(System.out::print);            // void ifPresent(Consumer<? super T> consumer

        // фильтр пропускает значение
        carOptional
                .filter(x -> x.getPrice().equals("250")) // Optional<T> filter(Predicate<? super T> predicate)
                .ifPresent(System.out::print);           // void ifPresent(Consumer<? super T> consumer
    }

    /**
     * Optional:
     * — map(..)
     * — ofNullable(..)
     * — orElse(..)
     */
    @Test
    public void testMap(){
        // пустая строка с его длиной (получаем 0 как длину)
        Optional<String>   stringOptionalNull = Optional.ofNullable(null);              // Optional<?> = new Optional<>() или Optional<>(value)
        Optional<Integer> integerOptionalNull = stringOptionalNull.map(String::length); // Optional<Integer> integerOptionalNull = stringOptionalNull.map(x -> x.length()); // Optional<U> map(Function<? super T, ? extends U> mapper)
        // непустая строковая по длине
        Optional<String>   stringOptional = Optional.of("loooooooong string");  // new Optional<>(value)
        Optional<Integer> integerOptional = stringOptional.map(String::length); // Optional<T> ofNullable(T value) >>> Optional<?> = new Optional<>() или Optional<>(value)

        System.out.println("size of string " + integerOptionalNull.orElse(0)); // T orElse(T other) >>> value или other
        System.out.println("size of string " + integerOptional.orElse(0));     // T orElse(T other) >>> value или other

        Optional<Car> optional = Optional.ofNullable(new Car("1000"));
        Optional<Integer> integer = optional.map(Car::getYear);
    }

    /**
     * Optional:
     * — get()
     */
    @Test
    public void testGet() {
        String strNull = null;

        try {
            // значение не может быть передано и мы должны использовать Nullable
            Optional<String> optionalString = Optional.of(strNull);
            System.out.println( optionalString.get().contains("something") );
        } catch(NullPointerException ex){
            System.err.println("NullPointerException...");
        }
    }

    /**
     * Optional:
     * — isPresent(..)
     * — get()
     */
    @Test
    public void testIsPresent(){
        Optional<String> stringToUse = Optional.of("danibuiza1"); // new Optional<>(value)

        if(stringToUse.isPresent()){               // boolean isPresent()
            System.out.println(stringToUse.get()); // T get() или throw NoSuchElementException
        }
    }

    /**
     * Optional:
     * — orElse(..)
     */
    @Test
    public void testOrElse(){
        Car defaulted = new Car("250");

        // is empty
        Optional<Car> defaultOptionalCar = Optional.empty();           // Optional<?> EMPTY = new Optional<>();
        // value is there
        Optional<Car> optionalCar = Optional.of(new Car("500")); // new Optional<>(value)

        String defaultPrice = defaultOptionalCar
                .orElse(defaulted) // T orElse(T other) >>> value или other
                .getPrice();
        String price = optionalCar
                .orElse(defaulted) // T orElse(T other) >>> value или other
                .getPrice();

        System.out.println("(default) price = " + defaultPrice);
        System.out.println("          price = " + price);
    }

    /**
     * Optional:
     * — orElseThrow(..)
     */
    @Test
    public void testOrElseThrow() {
        Car                   carNull = null;
        Optional<Car> optionalCarNull = Optional.ofNullable(carNull); // Optional<?> = new Optional<>() или Optional<>(value)

        try {
            String price = optionalCarNull
                    .orElseThrow(IllegalStateException::new) // <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
                    .getPrice();
        } catch(IllegalStateException e){
            System.err.println("expected IllegalStateException");
        }
    }

    /**
     * Optional:
     * — ifPresent(..)
     */
    @Test
    public void testIfPresent(){
        // ifPresent
        Optional<String>     stringToUse = Optional.of("danibuiza2"); // new Optional<>(value)
        // if not present
        Optional<String> stringToUseNull = Optional.ofNullable(null); // Optional<?> = new Optional<>() или Optional<>(value)

        stringToUse
                .ifPresent(System.out::println); // void ifPresent(Consumer<? super T> consumer)
        stringToUseNull
                .ifPresent(System.out::println); // void ifPresent(Consumer<? super T> consumer)
    }
}

class Car {
    private String price;
    private Integer year;

    public Car(String price){
        this.price = price;
    }

    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return price;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return "this car costs " + price;
    }
}
