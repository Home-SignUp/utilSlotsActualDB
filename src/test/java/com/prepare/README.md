[Markdown support](https://daringfireball.net/projects/markdown/syntax)

### [Java Collections Framework 2](https://jsehelper.blogspot.com/2016/01/java-collections-framework-2.html)

```
> Степень от 0,1,2,3
  10^0 = 1
  10^1 = 10
  10^2 = 100
  10^3 = 1000
 
> Логарифм от 1,10,100,1000
  Log(1) = 0
  Log(10) = 1
  Log(100) = 2
  Log(1000) = 3
```

![Нотация асимптотического роста](fd0c1c9ed7d949c2cd258b45302016ca.png)

![если алгоритм имеет сложность тогда его эффективность](17ca73d8dad367e1a60e3e20281e9d6d.png)
 
![...](3da386eed54c16ff73b647b383aea085.png)

[Сложности алгоритмов `O(1), O(N), O(log(N)), O(N*log(N)), O(N*N)`](https://habrahabr.ru/post/188010)
---
[f(x) = O(g(x)) означает, что при увеличении x отношение f(x)/g(x) остается ограниченным по величине](http://forum.sources.ru/index.php?showtopic=337205)

> Определить точное время выполнения алгоритма по этой нотации нельзя, но кое-какие выводы о росте времени получить можно.
>
>* `O(1)` - затраты времени не зависят от размера задачи
>* `O(n)` - при увеличении размера задачи в 2 раза, затраты времени возрастут тоже в два раза
>* `O(n^2)` - при увеличении размера задачи в 2 раза, затраты времени возрастут примерно в четыре раза
>* `O(h/n)` - при увеличении размера задачи в 2 раза, затраты времени возрастут прямо-пропорционально емкости хеша
>* `O(log(n))` - при увеличении размера задачи вдвое, затраты времени меняются на постоянную величину

>* `O(n*log(n))` - при увеличении задачи в два раза, затраты времени возрастут в два раза, плюс некоторая прибавка, относительный вклад которой уменьшается с ростом n. При малых n может вносить очень большой вклад. O(n*log(n)) начинает расти как квадрат при малых n, но потом рост замедляется почти до линейного
>* `O(n^p)` - полиномиальный алгоритмы, остающиеся мечтой для некоторых задач.
>* `O(a^n)`, `O(n!)`, `O(n^n)` - неполиномиальные алгоритмы, в порядке ускорения увеличения затрат времени

![График роста O — большое](195e1f6a1379554ca9025338301a78ed.png)

[Большое 'O' - производительность для общих функций некоторых коллекций](https://gist.github.com/psayre23/c30a821239f4818b0709)

---
List                 | Add  | Remove | Get  | Contains | Next | Data Structure
---------------------|------|--------|------|----------|------|---------------
ArrayList            | O(1) |  O(n)  | O(1) |   O(n)   | O(1) | Array
LinkedList           | O(1) |  O(1)  | O(n) |   O(n)   | O(1) | Linked List
CopyOnWriteArrayList | O(n) |  O(n)  | O(1) |   O(n)   | O(1) | Array

List                 | Add  | Add(E, index) | Remove | Iterator.remove() | Iterator.add(E) | Get  | Contains | Next | Data Structure
---------------------|------|---------------|--------|-------------------|-----------------|------|----------|------|---------------
ArrayList            | O(1) |  O(n)         |  O(n)  | O(n)              | O(n)            | O(1) |   O(n)   | O(1) | Array
LinkedList           | O(1) |  O(n)         |  O(1)  | O(1)              | O(1)            | O(n) |   O(n)   | O(1) | Linked List

Set                   |    Add   |  Remove  | Contains |   Next   | Size | Data Structure
----------------------|----------|----------|----------|----------|------|-------------------------
HashSet               | O(1)     | O(1)     | O(1)     | O(h/n)   | O(1) | Hash Table
LinkedHashSet         | O(1)     | O(1)     | O(1)     | O(1)     | O(1) | Hash Table + Linked List
EnumSet               | O(1)     | O(1)     | O(1)     | O(1)     | O(1) | Bit Vector
TreeSet               | O(log n) | O(log n) | O(log n) | O(log n) | O(1) | Red-black tree
CopyOnWriteArraySet   | O(n)     | O(n)     | O(n)     | O(1)     | O(1) | Array
ConcurrentSkipListSet | O(log n) | O(log n) | O(log n) | O(1)     | O(n) | Skip List

Queue                   |  Offer   | Peak |   Poll   | Remove | Size | Data Structure
------------------------|----------|------|----------|--------|------|---------------
PriorityQueue           | O(log n) | O(1) | O(log n) |  O(n)  | O(1) | Priority Heap
LinkedList              | O(1)     | O(1) | O(1)     |  O(1)  | O(1) | Array
ArrayDequeue            | O(1)     | O(1) | O(1)     |  O(n)  | O(1) | Linked List
ConcurrentLinkedQueue   | O(1)     | O(1) | O(1)     |  O(n)  | O(n) | Linked List
ArrayBlockingQueue      | O(1)     | O(1) | O(1)     |  O(n)  | O(1) | Array
PriorirityBlockingQueue | O(log n) | O(1) | O(log n) |  O(n)  | O(1) | Priority Heap
SynchronousQueue        | O(1)     | O(1) | O(1)     |  O(n)  | O(1) | None!
DelayQueue              | O(log n) | O(1) | O(log n) |  O(n)  | O(1) | Priority Heap
LinkedBlockingQueue     | O(1)     | O(1) | O(1)     |  O(n)  | O(1) | Linked List

Map                   |   Get    | ContainsKey |   Next   | Data Structure
----------------------|----------|-------------|----------|-------------------------
HashMap               | O(1)     |   O(1)      | O(h / n) | Hash Table
LinkedHashMap         | O(1)     |   O(1)      | O(1)     | Hash Table + Linked List
IdentityHashMap       | O(1)     |   O(1)      | O(h / n) | Array
WeakHashMap           | O(1)     |   O(1)      | O(h / n) | Hash Table
EnumMap               | O(1)     |   O(1)      | O(1)     | Array
TreeMap               | O(log n) |   O(log n)  | O(log n) | Red-black tree
ConcurrentHashMap     | O(1)     |   O(1)      | O(h / n) | Hash Tables
ConcurrentSkipListMap | O(log n) | O(log n) | O(1) | Skip List

https://www.programcreek.com/2013/09/top-10-questions-for-java-collections
---
List              | Arraylist | LinkedList
------------------|-----------|-----------
get(index)        |    O(1)   |   O(n)
add(E)            |    O(n)   |   O(1)
add(E, index)     |    O(n)   |   O(n)
remove(index)     |    O(n)   |   O(n)
Iterator.remove() |    O(n)   |   O(1)
Iterator.add(E)   |    O(n)   |   O(1)

##Структуры данных
![Структуры данных](9a5f72788d9e0e5ac0d0e585e3b3632f.png)

##Поиск
![Поиск](f54446a54f3d52d20e95ba5c5495644f.png)

##Сортировка
![Сортировка](b911bcca9ca9f9d8b0fa781a49118553.png)

![Кучи](3736d44e79e3bf542e2a847bbedcf86d.png)











https://stackoverflow.com/questions/559839/big-o-summary-for-java-collections-framework-implementations
https://www.programcreek.com/2013/09/top-10-questions-for-java-collections/
https://habrahabr.ru/post/237043

https://www.programcreek.com/2013/09/top-10-questions-for-java-collections
http://info.javarush.ru/translation/2014/06/26/Топ-10-вопросов-о-коллекциях-в-Java.html

