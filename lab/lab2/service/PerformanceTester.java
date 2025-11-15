
package service;
import model.Student;
import java.util.*;

public class PerformanceTester {
    public static void addStudentsToArrayList(ArrayList<Student> studentList) {
        for (int i = 0; i < 10000000; i++) {
            studentList.add(new Student((long) i + 1, ""));
        }
    }

    public static void measureArrayListOperations(ArrayList<Student> studentList) {
        long beginTime, endTime;

        System.out.println();
        System.out.println("ArrayList");
        System.out.println();

        beginTime = System.nanoTime();
        studentList.add(new Student(10000001L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в конец: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        studentList.add(0, new Student(0L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в начало: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        studentList.remove(studentList.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        studentList.remove(0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        studentList.get(5000000);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        studentList.get(9999999);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - beginTime) + " нс");
    }
    public static void addStudentsToLinkedList(LinkedList<Student> linkedList) {
        for (int i = 0; i < 10000000; i++) {
            linkedList.add(new Student((long) i + 1, ""));
        }
    }

    public static void measureLinkedListOperations(LinkedList<Student> linkedList) {
        long beginTime, endTime;

        System.out.println();
        System.out.println("LinkedList");
        System.out.println();

        beginTime = System.nanoTime();
        linkedList.addLast(new Student(10000001L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в конец: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        linkedList.addFirst(new Student(0L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в начало: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        linkedList.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        linkedList.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        linkedList.get(5000000);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        linkedList.get(9999999);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - beginTime) + " нс");
    }
    public static void addStudentsToHashSet(HashSet<Student> hashSet) {
        for (int i = 0; i < 10000000; i++) {
            hashSet.add(new Student((long) i + 1, ""));
        }
    }

    public static void measureHashSetOperations(HashSet<Student> hashSet) {
        long beginTime, endTime;

        System.out.println();
        System.out.println("HashSet");
        System.out.println();

        beginTime = System.nanoTime();
        hashSet.add(new Student(10000001L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashSet.remove(new Student(10000001L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashSet.remove(new Student(1L, ""));
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashSet.contains(new Student(5000000L, ""));
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashSet.contains(new Student(10000000L, ""));
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - beginTime) + " нс");
    }

    public static void addStudentsToHashMap(HashMap<Long, Student> hashMap) {
        for (int i = 0; i < 10000000; i++) {
            hashMap.put((long) i + 1, new Student((long) i + 1, ""));
        }
    }

    public static void measureHashMapOperations(HashMap<Long, Student> hashMap) {
        long beginTime, endTime;

        System.out.println();
        System.out.println("HashMap");
        System.out.println();

        beginTime = System.nanoTime();
        hashMap.put(10000001L, new Student(10000001L, "Кирилл"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashMap.remove(1L);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashMap.remove(10000001L);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashMap.get(5000000L);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - beginTime) + " нс");

        beginTime = System.nanoTime();
        hashMap.get(9999999L);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - beginTime) + " нс");
    }
}
//Тестирования производительности коллекций
//==================================================
//
//ArrayList
//
//Добавление элемента в конец: 10000 нс
//Добавление элемента в начало: 8019900 нс
//Удаление последнего элемента: 18500 нс
//Удаление первого элемента: 4981000 нс
//Взятие центрального элемента: 8900 нс
//Взятие последнего элемента: 800 нс
//
//        LinkedList
//
//Добавление элемента в конец: 10700 нс
//Добавление элемента в начало: 11900 нс
//Удаление последнего элемента: 5700 нс
//Удаление первого элемента: 3100 нс
//Взятие центрального элемента: 95713200 нс
//Взятие последнего элемента: 7000 нс
//
//        HashSet
//
//Добавление элемента: 75100 нс
//Удаление последнего элемента: 38400 нс
//Удаление первого элемента: 5700 нс
//Взятие центрального элемента: 26600 нс
//Взятие последнего элемента: 3700 нс
//
//        HashMap
//
//Добавление элемента: 2400 нс
//Удаление первого элемента: 9800 нс
//Удаление последнего элемента: 2700 нс
//Взятие центрального элемента: 7500 нс
//Взятие последнего элемента: 800 нс
//==================================================
//Тестирование завершено!
