//Создать класс Student с полями:
//1 Long id
//2 String name
//В методе main:
//1 Создать ArrayList, который хранит объекты класса Student
//(ArrayList<Student>).
//2 Создать LinkedList, который хранит объекты класса Student
//(LinkedList <Student>).
//3 Создать Set, который хранит объекты класса Student (HashSet
//<Student>).
//4 Создать HashMap, который хранит объекты класса Student (HashMap
//<Long, Student>).
//В каждую структуру данных добавить 10 000 000 объектов.
//После этого для каждой структуры данных измерить время в нс:
//1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
//2 Добавление 1 несуществующего элемента в начало.
//3 Удаление последнего элемента
//4 Удаление первого элемента
//5 Взятие (Get) центрального элемента (id = 5 000 000)
//6 Взятие (Get) последнего элемента (id = 9 999 999).
//Помимо кода решение должно содержать цифры, полученные при
//тестах. При невозможности работать с 10 000 000 записей позволительно
//несколько сократить количество объектов.

import models.Student;

import java.util.*;

public class Task0 {
    public static void main(String[] args) {
        int n = 10_000_000;

        ArrayList<Student> arrayList = new ArrayList<>(n);
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>(n);
        HashMap<Long, Student> hashMap = new HashMap<>(n);

        for (long i = 1; i <= n; i++) {
            Student s = new Student(i, "Name" + i);
            arrayList.add(s);
            linkedList.add(s);
            hashSet.add(s);
            hashMap.put(i, s);
        }

        Student endStudent = new Student(n + 1L, "Name" + (n + 1));
        Student startStudent = new Student(0L, "Name0");
        Student middleStudent = new Student(n / 2L, "Name" + (n / 2L));
        Student lastStudent = new Student(n - 1L, "Name" + (n - 1L));

        // ArrayList
        System.out.println("ArrayList:");
        long timeStart = System.nanoTime();
        arrayList.add(endStudent);
        long timeFinish = System.nanoTime();
        System.out.println("add end: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        arrayList.add(0, startStudent);
        timeFinish = System.nanoTime();
        System.out.println("add start: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        arrayList.remove(arrayList.size() - 1);
        timeFinish = System.nanoTime();
        System.out.println("remove last: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        arrayList.remove(0);
        timeFinish = System.nanoTime();
        System.out.println("remove first: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        arrayList.get(arrayList.size() / 2);
        timeFinish = System.nanoTime();
        System.out.println("get middle: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        arrayList.get(arrayList.size() - 1);
        timeFinish = System.nanoTime();
        System.out.println("get last: " + (timeFinish - timeStart) + " ns");

        // LinkedList
        System.out.println("LinkedList:");
        timeStart = System.nanoTime();
        linkedList.addLast(lastStudent);
        timeFinish = System.nanoTime();
        System.out.println("add end: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        linkedList.addFirst(startStudent);
        timeFinish = System.nanoTime();
        System.out.println("add start: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        linkedList.removeLast();
        timeFinish = System.nanoTime();
        System.out.println("remove last: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        linkedList.removeFirst();
        timeFinish = System.nanoTime();
        System.out.println("remove first: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        linkedList.get(linkedList.size() / 2);
        timeFinish = System.nanoTime();
        System.out.println("get middle: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        linkedList.get(linkedList.size() - 1);
        timeFinish = System.nanoTime();
        System.out.println("get last: " + (timeFinish - timeStart) + " ns");

        // HashSet
        System.out.println("HashSet:");
        timeStart = System.nanoTime();
        hashSet.add(endStudent);
        timeFinish = System.nanoTime();
        System.out.println("add end: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashSet.add(startStudent);
        timeFinish = System.nanoTime();
        System.out.println("add start: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashSet.remove(lastStudent);
        timeFinish = System.nanoTime();
        System.out.println("remove last: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashSet.remove(startStudent);
        timeFinish = System.nanoTime();
        System.out.println("remove first: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashSet.contains(middleStudent);
        timeFinish = System.nanoTime();
        System.out.println("get middle: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashSet.contains(lastStudent);
        timeFinish = System.nanoTime();
        System.out.println("get last: " + (timeFinish - timeStart) + " ns");

        // HashMap
        System.out.println("HashMap:");
        timeStart = System.nanoTime();
        hashMap.put(n + 1L, endStudent);
        timeFinish = System.nanoTime();
        System.out.println("add end: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashMap.put(0L, startStudent);
        timeFinish = System.nanoTime();
        System.out.println("add start: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashMap.remove(n);
        timeFinish = System.nanoTime();
        System.out.println("remove last: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashMap.remove(1L);
        timeFinish = System.nanoTime();
        System.out.println("remove first: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashMap.get(n / 2L);
        timeFinish = System.nanoTime();
        System.out.println("get middle: " + (timeFinish - timeStart) + " ns");

        timeStart = System.nanoTime();
        hashMap.get(n - 1L);
        timeFinish = System.nanoTime();
        System.out.println("get last: " + (timeFinish - timeStart) + " ns");
    }
}