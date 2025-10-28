/*      Создать класс Student с полями:
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
        6 Взятие (Get) последнего элемента (id = 9 999 999). */


import java.util.*;

public class StudentTask {

    public static class Student {

        private Long id;
        private String name;

        public Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    private static final int SIZE = 10000000;
    private static final Student middleStudent = new Student((long) SIZE / 2, "Middle");

    public static void main(String[] args) {

        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap();

        for (long i = 0; i < SIZE; i++) {
            Student student = new Student(i, "Student № " + i + 1);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }


        System.out.println("||| ArrayList |||");
        testLists(arrayList);

        System.out.println("\n||| LinkedList |||");
        testLists(linkedList);

        System.out.println("\n||| HashSet |||");
        testHashSet(hashSet);

        System.out.println("\n||| HashMap |||");
        testHashMap(hashMap);

    }

    private static long time(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }


    private static void testLists(List<Student> list) {
        System.out.println("Добавление в конец: " + time(() -> list.add(new Student(10000001L, "New"))));
        System.out.println("Добавление в начало: " + time(() -> list.add(0, new Student(1_000_002L, "New"))));
        System.out.println("Удаление последнего: " + time(() -> list.remove(list.size() - 1)));
        System.out.println("Удаление первого: " + time(() -> list.remove(0)));
        System.out.println("Получение среднего: " + time(() -> list.get(SIZE / 2)));
        System.out.println("Получение последнего: " + time(() -> list.get(SIZE - 1)));
    }

    private static void testHashSet(Set<Student> set) {

        Student last = new Student(10000002L, "l");
        System.out.println("Добавление элемента: " + time(() -> set.add(last)));
        System.out.println("Нельзя добавить в начало.");
        System.out.println("Удаление элемента: " + time(() -> set.remove(last)));
        System.out.println(" ");
        System.out.println("Поиск среднего: " + time(() -> set.contains(middleStudent)));
        System.out.println("Поиск последнего: " + time(() -> set.contains(last)));
    }

    private static void testHashMap(Map<Long, Student> map) {
        System.out.println("Добавление записи: " + time(() -> map.put(10000001L, new Student(10000001L, "L"))));
        System.out.println("Нельзя добавить в начало.");
        System.out.println("Удаление записи: " + time(() -> map.remove(10000001L)));
        System.out.println(" ");
        System.out.println("Поиск среднего элемента: " + time(() -> map.get(5000000L)));
        System.out.println("Поиск последнего элемента: " + time(() -> map.get(9999999L)));
    }
}
/*
||| ArrayList |||
Добавление в конец: 8083
Добавление в начало: 3286208
Удаление последнего: 5625
Удаление первого: 875375
Получение среднего: 2667
Получение последнего: 1084

||| LinkedList |||
Добавление в конец: 2541
Добавление в начало: 16375
Удаление последнего: 4292
Удаление первого: 1042
Получение среднего: 89961250
Получение последнего: 2125

||| HashSet |||
Добавление элемента: 9250
Нельзя добавить в начало.
Удаление элемента: 5250

Поиск среднего: 3125
Поиск последнего: 1083

||| HashMap |||
Добавление записи: 5875
Нельзя добавить в начало.
Удаление записи: 8792

Поиск среднего элемента: 6625
Поиск последнего элемента: 1583


 */