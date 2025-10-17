/*
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
6 Взятие (Get) последнего элемента (id = 9 999 999).
 */
import java.util.*;
public class StudentTask {
    public static class Student {
        private Long id;
        private String name;
        public Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }
        public Long getId() { return id; }
        public String getName() { return name; }
    }
    private static final int SIZE = 1000000;
    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap<>();
        for (long i = 0; i < SIZE; i++) {
            Student student = new Student(i, "Student_" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }
        System.out.println("ArrayList:");
        testList(arrayList);
        System.out.println("LinkedList:");
        testList(linkedList);
        System.out.println("HashSet:");
        testSet(hashSet);
        System.out.println("HashMap:");
        testMap(hashMap);
    }
    private static void testList(List<Student> list) {
        // 1. Добавление в конец
        long start = System.nanoTime();
        list.add(new Student(1000001L, "New"));
        long end = System.nanoTime();
        System.out.println("Добавление в конец: " + (end - start));
        // 2. Добавление в начало
        start = System.nanoTime();
        list.add(0, new Student(1000002L, "New"));
        end = System.nanoTime();
        System.out.println("Добавление в начало: " + (end - start));
        // 3. Удаление последнего
        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Удаление последнего: " + (end - start));
        // 4. Удаление первого
        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("Удаление первого: " + (end - start));
        // 5. Получение центрального
        start = System.nanoTime();
        Student center = list.get(SIZE / 2);
        end = System.nanoTime();
        System.out.println("Получение центрального: " + (end - start));
        // 6. Получение последнего
        start = System.nanoTime();
        Student last = list.get(SIZE - 1);
        end = System.nanoTime();
        System.out.println("Получение последнего: " + (end - start));
    }
    private static void testSet(Set<Student> set) {
        // 1. Добавление
        long start = System.nanoTime();
        set.add(new Student(1000001L, "New"));
        long end = System.nanoTime();
        System.out.println("Добавление: " + (end - start));
        // 2. Добавление другого
        start = System.nanoTime();
        set.add(new Student(1000002L, "New"));
        end = System.nanoTime();
        System.out.println("Добавление другого: " + (end - start));
        // 3. Удаление
        start = System.nanoTime();
        set.remove(new Student(1000001L, "New"));
        end = System.nanoTime();
        System.out.println("Удаление: " + (end - start));
        // 4. Удаление другого
        start = System.nanoTime();
        set.remove(new Student(1000002L, "New"));
        end = System.nanoTime();
        System.out.println("Удаление другого: " + (end - start));
        // 5. Поиск центрального
        start = System.nanoTime();
        boolean found = set.contains(new Student(500000L, ""));
        end = System.nanoTime();
        System.out.println("Поиск центрального: " + (end - start));
        // 6. Поиск последнего
        start = System.nanoTime();
        found = set.contains(new Student(999999L, ""));
        end = System.nanoTime();
        System.out.println("Поиск последнего: " + (end - start));
    }
    private static void testMap(Map<Long, Student> map) {
        // 1. Добавление
        long start = System.nanoTime();
        map.put(1000001L, new Student(1000001L, "New"));
        long end = System.nanoTime();
        System.out.println("Добавление: " + (end - start));
        // 2. Добавление другого
        start = System.nanoTime();
        map.put(1000002L, new Student(1000002L, "New"));
        end = System.nanoTime();
        System.out.println("Добавление другого: " + (end - start));
        // 3. Удаление
        start = System.nanoTime();
        map.remove(1000001L);
        end = System.nanoTime();
        System.out.println("Удаление: " + (end - start));
        // 4. Удаление другого
        start = System.nanoTime();
        map.remove(1000002L);
        end = System.nanoTime();
        System.out.println("Удаление другого: " + (end - start));
        // 5. Получение центрального
        start = System.nanoTime();
        Student center = map.get(500000L);
        end = System.nanoTime();
        System.out.println("Получение центрального: " + (end - start));
        // 6. Получение последнего
        start = System.nanoTime();
        Student last = map.get(999999L);
        end = System.nanoTime();
        System.out.println("Получение последнего: " + (end - start));
    }
}
/*
ArrayList:
Добавление в конец: 7541
Добавление в начало: 371375
Удаление последнего: 8875
Удаление первого: 86084
Получение центрального: 2333
Получение последнего: 375
LinkedList:
Добавление в конец: 1709
Добавление в начало: 11208
Удаление последнего: 4166
Удаление первого: 1333
Получение центрального: 14382875
Получение последнего: 1917
HashSet:
Добавление: 15625
Добавление другого: 584
Удаление: 11083
Удаление другого: 833
Поиск центрального: 3625
Поиск последнего: 625
HashMap:
Добавление: 6417
Добавление другого: 292
Удаление: 7708
Удаление другого: 1166
Получение центрального: 5958
Получение последнего: 375
 */