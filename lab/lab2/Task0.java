package lab2;

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

import lab2.models.Student;

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
        long t1 = System.nanoTime();
        arrayList.add(endStudent);
        long t2 = System.nanoTime();
        System.out.println("add end: " + (t2 - t1));

        t1 = System.nanoTime();
        arrayList.add(0, startStudent);
        t2 = System.nanoTime();
        System.out.println("add start: " + (t2 - t1));

        t1 = System.nanoTime();
        arrayList.remove(arrayList.size() - 1);
        t2 = System.nanoTime();
        System.out.println("remove last: " + (t2 - t1));

        t1 = System.nanoTime();
        arrayList.remove(0);
        t2 = System.nanoTime();
        System.out.println("remove first: " + (t2 - t1));

        t1 = System.nanoTime();
        arrayList.get(arrayList.size() / 2);
        t2 = System.nanoTime();
        System.out.println("get middle: " + (t2 - t1));

        t1 = System.nanoTime();
        arrayList.get(arrayList.size() - 1);
        t2 = System.nanoTime();
        System.out.println("get last: " + (t2 - t1));

        // LinkedList
        System.out.println("LinkedList:");
        t1 = System.nanoTime();
        linkedList.addLast(lastStudent);
        t2 = System.nanoTime();
        System.out.println("add end: " + (t2 - t1));

        t1 = System.nanoTime();
        linkedList.addFirst(startStudent);
        t2 = System.nanoTime();
        System.out.println("add start: " + (t2 - t1));

        t1 = System.nanoTime();
        linkedList.removeLast();
        t2 = System.nanoTime();
        System.out.println("remove last: " + (t2 - t1));

        t1 = System.nanoTime();
        linkedList.removeFirst();
        t2 = System.nanoTime();
        System.out.println("remove first: " + (t2 - t1));

        t1 = System.nanoTime();
        linkedList.get(linkedList.size() / 2);
        t2 = System.nanoTime();
        System.out.println("get middle: " + (t2 - t1));

        t1 = System.nanoTime();
        linkedList.get(linkedList.size() - 1);
        t2 = System.nanoTime();
        System.out.println("get last: " + (t2 - t1));

        // HashSet
        System.out.println("HashSet:");
        t1 = System.nanoTime();
        hashSet.add(endStudent);
        t2 = System.nanoTime();
        System.out.println("add end: " + (t2 - t1));

        t1 = System.nanoTime();
        hashSet.add(startStudent);
        t2 = System.nanoTime();
        System.out.println("add start: " + (t2 - t1));

        t1 = System.nanoTime();
        hashSet.remove(lastStudent);
        t2 = System.nanoTime();
        System.out.println("remove last: " + (t2 - t1));

        t1 = System.nanoTime();
        hashSet.remove(startStudent);
        t2 = System.nanoTime();
        System.out.println("remove first: " + (t2 - t1));

        t1 = System.nanoTime();
        hashSet.contains(middleStudent);
        t2 = System.nanoTime();
        System.out.println("get middle: " + (t2 - t1));

        t1 = System.nanoTime();
        hashSet.contains(lastStudent);
        t2 = System.nanoTime();
        System.out.println("get last: " + (t2 - t1));

        // HashMap
        System.out.println("HashMap:");
        t1 = System.nanoTime();
        hashMap.put(n + 1L, endStudent);
        t2 = System.nanoTime();
        System.out.println("add end: " + (t2 - t1));

        t1 = System.nanoTime();
        hashMap.put(0L, startStudent);
        t2 = System.nanoTime();
        System.out.println("add start: " + (t2 - t1));

        t1 = System.nanoTime();
        hashMap.remove(n);
        t2 = System.nanoTime();
        System.out.println("remove last: " + (t2 - t1));

        t1 = System.nanoTime();
        hashMap.remove(1L);
        t2 = System.nanoTime();
        System.out.println("remove first: " + (t2 - t1));

        t1 = System.nanoTime();
        hashMap.get(n / 2L);
        t2 = System.nanoTime();
        System.out.println("get middle: " + (t2 - t1));

        t1 = System.nanoTime();
        hashMap.get(n - 1L);
        t2 = System.nanoTime();
        System.out.println("get last: " + (t2 - t1));
    }
}