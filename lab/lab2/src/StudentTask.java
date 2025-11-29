/*
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
Помимо кода решение должно содержать цифры, полученные при
тестах. При невозможности работать с 10 000 000 записей позволительно
несколько сократить количество объектов.
В дополнительных заданиях необходимо создать структуры данных. Для
каждого вида списка реализовать следующие функции:
1 добавление элемента (начало, середина, конец);
2 удаление элемента;
3 подсчет числа элементов в списке;
4 печать списка в прямом и обратном порядке (по возможности).
Подготовить демонстрацию работы каждой из структур данных.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

public class StudentTask {
    private static final int STUDENTS_NUM = 10_000_000;

    public static void main(String[] args) {
        List<Student> arrayList = createArrayList(STUDENTS_NUM);
        LinkedList<Student> linkedList = createLinkedList(STUDENTS_NUM);
        HashMap<Long, Student> hashMap = createHashMap(STUDENTS_NUM);
        Set<Student> set = createSet(STUDENTS_NUM);
        
        Student start = createStudent(0);
        Student end = createStudent(STUDENTS_NUM + 1);

        testArrayList(arrayList, start, end);
        testLinkedList(linkedList, start, end);
        testHashMap(hashMap, start, end);
        testSet(set, start, end);
    }

    private static ArrayList<Student> createArrayList(int num) {
        ArrayList<Student> arrayList = new ArrayList<>();
        for (long i = 1; i <= num; i++) {
            arrayList.add(createStudent(i));
        }
        return arrayList;
    }

    private static LinkedList<Student> createLinkedList(int num) {
        LinkedList<Student> linkedList = new LinkedList<>();
        for (long i = 1; i <= num; i++) {
            linkedList.add(createStudent(i)); 
        }

        return linkedList;
    }

    private static HashMap<Long, Student> createHashMap(int num) {
        HashMap<Long, Student> hashMap = new HashMap<>();
        for (long i = 1; i <= num; i++) {
            hashMap.put(i, createStudent(i));
        }

        return hashMap;
    }

    private static Set<Student> createSet(int num) {
        Set<Student> set = new HashSet<>();
        for (long i = 1; i <= num; i++) {
            set.add(createStudent(i)); 
        }

        return set;
    }

    private static void testArrayList(List<Student> arrayList, Student start, Student end) {
        System.out.println("ArrayList:");
        System.out.printf("Добавление элемента в конец: %,d ns\n", 
            measureTime(() -> arrayList.add(end)));

        System.out.printf("Добавление элемента в начало: %,d ns\n", 
            measureTime(() -> arrayList.addFirst(start)));

        System.out.printf("Удаление последнего элемента: %,d ns\n", 
            measureTime(() -> arrayList.removeLast()));

        System.out.printf("Удаление первого элемента: %,d ns\n", 
            measureTime(() -> arrayList.removeFirst()));

        System.out.printf("Получение центрального элемента: %,d ns\n", 
            measureTime(() -> { Student _ = arrayList.get(STUDENTS_NUM / 2); }));

        System.out.printf("Получение последнего элемента: %,d ns\n", 
            measureTime(() -> { Student _ = arrayList.getLast(); }));
    }

    private static void testLinkedList(LinkedList<Student> linkedList, Student start, Student end) {
        System.out.println("LinkedList:");
        System.out.printf("Добавление элемента в конец: %,d ns\n", 
            measureTime(() -> linkedList.add(end)));

        System.out.printf("Добавление элемента в начало: %,d ns\n", 
            measureTime(() -> linkedList.addFirst(start)));

        System.out.printf("Удаление последнего элемента: %,d ns\n", 
            measureTime(() -> linkedList.removeLast()));

        System.out.printf("Удаление первого элемента: %,d ns\n", 
            measureTime(() -> linkedList.removeFirst()));

        System.out.printf("Получение центрального элемента: %,d ns\n", 
            measureTime(() -> { Student _ = linkedList.get(STUDENTS_NUM / 2); }));

        System.out.printf("Получение последнего элемента: %,d ns\n", 
            measureTime(() -> { Student _ = linkedList.getLast(); }));
    }

    private static void testHashMap(HashMap<Long, Student> hashMap, Student start, Student end) {
        System.out.println("HashMap:");
        System.out.printf("Добавление элемента c id %d: %,d ns\n", end.getId(),
            measureTime(() -> hashMap.put(end.getId(), end)));

        System.out.printf("Добавление элемента c id %d: %,d ns\n", start.getId(),
            measureTime(() -> hashMap.put(start.getId(), start)));

        System.out.printf("Удаление элемента c id %d: %,d ns\n", end.getId(),
            measureTime(() -> hashMap.remove(end.getId())));
        
        System.out.printf("Получение центрального элемента: %,d ns\n",
            measureTime(() -> hashMap.get((long) STUDENTS_NUM / 2)));

        System.out.printf("Удаление элемента c id %d: %,d ns\n", start.getId(),
            measureTime(() -> hashMap.remove(start.getId())));
        
        System.out.printf("Получение последнего элемента: %,d ns\n", 
            measureTime(() -> { Student _ = hashMap.get((long) STUDENTS_NUM); }));
    }

    public static void testSet(Set<Student> set, Student start, Student end) {
        System.out.println("Set:");
        System.out.printf("Добавление элемента c id %d: %,d ns\n", end.getId(),
            measureTime(() -> set.add(end)));

        System.out.printf("Добавление элемента c id %d: %,d ns\n", start.getId(),
            measureTime(() -> set.add(start)));

        System.out.printf("Удаление элемента c id %d: %,d ns\n", end.getId(),
            measureTime(() -> set.remove(end)));
        
        Student targetMid = createStudent((long) 1);
        Student targetLast = createStudent((long) STUDENTS_NUM);

        System.out.printf("Получение центрального элемента: %,d ns\n",
            measureTime(() -> set.contains(targetMid)));
        
        System.out.printf("Получение последнего элемента: %,d ns\n", 
            measureTime(() -> set.contains(targetLast)));
    }


    private static Student createStudent(long num) {
        return new Student(num, "Student_" + num);
    }

    private static long measureTime(Runnable operation) {
        long startTime = System.nanoTime();
        operation.run();
        return System.nanoTime() - startTime;
    }
}


/*  
ArrayList:
Добавление элемента в конец: 124 400 ns
Добавление элемента в начало: 72 266 200 ns
Удаление последнего элемента: 134 500 ns
Удаление первого элемента: 61 907 900 ns
Получение центрального элемента: 10 600 ns
Получение последнего элемента: 36 100 ns

LinkedList:
Добавление элемента в конец: 144 900 ns
Добавление элемента в начало: 155 900 ns
Удаление последнего элемента: 13 800 ns
Удаление первого элемента: 6 800 ns
Получение центрального элемента: 171 389 900 ns
Получение последнего элемента: 11 700 ns

HashMap:
Добавление элемента c id 10000001: 46 800 ns
Добавление элемента c id 0: 6 100 ns
Удаление элемента c id 10000001: 218 300 ns
Получение центрального элемента: 37 800 ns
Удаление элемента c id 0: 70 500 ns
Получение последнего элемента: 6 100 ns

Set:
Добавление элемента c id 10000001: 135 500 ns
Добавление элемента c id 0: 26 800 ns
Удаление элемента c id 10000001: 72 400 ns
Получение центрального элемента: 185 300 ns
Получение последнего элемента: 95 000 ns
*/
