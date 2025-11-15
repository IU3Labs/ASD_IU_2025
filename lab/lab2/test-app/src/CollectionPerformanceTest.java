/*Основное задание. Задание оценивается в 0 баллов.
Создать класс Student с полями:
1 Long id
2 String name
В методе main:
1 Создать ArrayList, который хранит объекты класса Student
(ArrayList<Student>).
2 Создать LinkedList, который хранит объекты класса Student
(LinkedList <Student>).
3 Создать Set, который хранит объекты класса Student (HashSet
<Student>).
4 Создать HashMap, который хранит объекты класса Student (HashMap
<Long, Student>).
В каждую структуру данных добавить 10 000 000 объектов.
После этого для каждой структуры данных измерить время в нс:
1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
2 Добавление 1 несуществующего элемента в начало.
3 Удаление последнего элемента
4 Удаление первого элемента
5 Взятие (Get) центрального элемента (id = 5 000 000)
Основное задание. Задание оценивается в 0 баллов.
Создать класс Student с полями:
1 Long id
2 String name
В методе main:
1 Создать ArrayList, который хранит объекты класса Student
(ArrayList<Student>).
2 Создать LinkedList, который хранит объекты класса Student
(LinkedList <Student>).
3 Создать Set, который хранит объекты класса Student (HashSet
<Student>).
4 Создать HashMap, который хранит объекты класса Student (HashMap
<Long, Student>).
В каждую структуру данных добавить 10 000 000 объектов.
После этого для каждой структуры данных измерить время в нс:
1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
2 Добавление 1 несуществующего элемента в начало.
3 Удаление последнего элемента
4 Удаление первого элемента
5 Взятие (Get) центрального элемента (id = 5 000 000)
 */

import java.util.*;

public class CollectionPerformanceTest
{

    public static void main(String[] args)
    {
        final int SIZE = 10_000_000;

        System.out.println("ТЕСТИРОВАНИЕ ПРОИЗВОДИТЕЛЬНОСТИ");
        System.out.println("Размер тестовых данных: " + SIZE + " элементов\n");

        testArrayList(SIZE);

        testLinkedList(SIZE);

        testHashSet(SIZE);

        testHashMap(SIZE);
    }

    public static void testArrayList(final int size)
    {
        System.out.println("ArrayList Test");
        final ArrayList<Student> list = new ArrayList<>();

        long start = System.nanoTime();
        for (long i = 0; i < size; i++) {
            list.add(new Student(i, "Student" + i));
        }
        long fillTime = System.nanoTime() - start;
        System.out.println("Время заполнения: " + fillTime + " ns");

        measureOperation("Добавление в конец", () ->
                list.add(new Student(10_000_001L, "NewStudent")));

        measureOperation("Добавление в начало", () ->
                list.add(0, new Student(-1L, "FirstStudent")));

        measureOperation("Удаление последнего", () ->
                list.remove(list.size() - 1));

        measureOperation("Удаление первого", () ->
                list.remove(0));

        measureOperation("Получение среднего", () ->
                list.get(size / 2));

        measureOperation("Получение последнего", () ->
                list.get(list.size() - 1));

        System.out.println();
    }

    public static void testLinkedList(final int size)
    {
        System.out.println("LinkedList Test");
        final LinkedList<Student> list = new LinkedList<>();

        long start = System.nanoTime();
        for (long i = 0; i < size; i++) {
            list.add(new Student(i, "Student" + i));
        }
        long fillTime = System.nanoTime() - start;
        System.out.println("Время заполнения: " + fillTime + " ns");

        measureOperation("Добавление в конец", () ->
                list.addLast(new Student(10_000_001L, "NewStudent")));

        measureOperation("Добавление в начало", () ->
                list.addFirst(new Student(-1L, "FirstStudent")));

        measureOperation("Удаление последнего", () ->
                list.removeLast());

        measureOperation("Удаление первого", () ->
                list.removeFirst());

        measureOperation("Получение среднего", () ->
                list.get(size / 2));

        measureOperation("Получение последнего", () ->
                list.getLast());

        System.out.println();
    }

    public static void testHashSet(final int size)
    {
        System.out.println("HashSet Test");
        final HashSet<Student> set = new HashSet<>();

        long start = System.nanoTime();
        for (long i = 0; i < size; i++) {
            set.add(new Student(i, "Student" + i));
        }
        long fillTime = System.nanoTime() - start;
        System.out.println("Время заполнения: " + fillTime + " ns");

        measureOperation("Добавление нового", () ->
                set.add(new Student(10_000_001L, "NewStudent")));

        final Student firstStudent = set.iterator().next();
        measureOperation("Удаление первого", () ->
                set.remove(firstStudent));

        measureOperation("Поиск среднего", () ->
                set.contains(new Student((long)size/2, "")));

        System.out.println("Получение последнего: не применимо для HashSet");
        System.out.println();
    }

    public static void testHashMap(final int size)
    {
        System.out.println("HashMap Test");
        final HashMap<Long, Student> map = new HashMap<>();

        long start = System.nanoTime();
        for (long i = 0; i < size; i++) {
            map.put(i, new Student(i, "Student" + i));
        }
        long fillTime = System.nanoTime() - start;
        System.out.println("Время заполнения: " + fillTime + " ns");

        measureOperation("Добавление нового", () ->
                map.put(10_000_001L, new Student(10_000_001L, "NewStudent")));

        measureOperation("Удаление первого", () ->
                map.remove(0L));

        measureOperation("Получение среднего", () ->
                map.get((long)size/2));

        measureOperation("Получение последнего", () ->
                map.get((long)size - 1));

        System.out.println();
    }

    private static void measureOperation(String operationName, Runnable operation)
    {
        long start = System.nanoTime();
        operation.run();
        long duration = System.nanoTime() - start;
        System.out.println(operationName + ": " + duration + " ns");
    }
}