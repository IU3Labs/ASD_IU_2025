/*
Задание:
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
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;

public class Main {

    static final int SIZE = 10_000_000;

    public static void main(String[] args) {

        ArrayList<Student> A_list = new ArrayList<>(SIZE);
        LinkedList<Student> L_list = new LinkedList<>();
        HashSet<Student> H_Set = new HashSet<>(SIZE);
        HashMap<Long, Student> H_Map = new HashMap<>(SIZE);

        // заполнение через один цикл
        for (long i = 0; i < SIZE; i++) {
            Student st = new Student(i, i + "-й студент");
            A_list.add(st);
            L_list.add(st);
            H_Set.add(st);
            H_Map.put(i, st);
        }

        Student newStudent1 = new Student(10_000_001, "New Student 1");
        Student newStudent2 = new Student(10_000_002, "New Student 2");

        Student firstStudent = new Student(0, "0-й студент");
        Student centerStudent = new Student(5_000_000, "5_000_000-й студент");
        Student lastStudent = new Student(9_999_999, "9_999_999-й студент");

        System.out.println("Операции над ArrayList:");
        measureTime("Добавление несуществующего элемента в конец",
                () -> A_list.add(newStudent1));

        measureTime("Добавление несуществующего элемента в начало",
                () -> A_list.add(0, newStudent2));

        measureTime("Взятие центрального элемента",
                () -> {
                    Student s = A_list.get(5_000_000);
                });

        measureTime("Взятие последнего элемента",
                () -> {
                    Student s = A_list.get(9_999_999);
                });

        measureTime("Удаление последнего элемента",
                () -> A_list.remove(A_list.size() - 1));

        measureTime("Удаление первого элемента",
                () -> A_list.remove(0));

        System.out.println("Операции над LinkedList:");
        measureTime("Добавление несуществующего элемента в конец",
                () -> L_list.addLast(newStudent1));

        measureTime("Добавление несуществующего элемента в начало",
                () -> L_list.addFirst(newStudent2));

        measureTime("Взятие центрального элемента",
                () -> {
                    Student s = L_list.get(5_000_000);
                });

        measureTime("Взятие последнего элемента",
                () -> {
                    Student s = L_list.getLast();
                });

        measureTime("Удаление последнего элемента",
                () -> L_list.removeLast());

        measureTime("Удаление первого элемента",
                () -> L_list.removeFirst());

        System.out.println("Операции над HashSet:");
        measureTime("Добавление нового элемента",
                () -> H_Set.add(newStudent1));

        measureTime("Добавление второго нового элемента",
                () -> H_Set.add(newStudent2));

        measureTime("Проверка центрального элемента",
                () -> {
                    boolean b = H_Set.contains(centerStudent);
                });

        measureTime("Проверка последнего элемента",
                () -> {
                    boolean b = H_Set.contains(lastStudent);
                });

        measureTime("Удаление последнего элемента",
                () -> H_Set.remove(lastStudent));

        measureTime("Удаление первого элемента",
                () -> H_Set.remove(firstStudent));

        System.out.println("Операции над HashMap:");
        measureTime("Добавление элемента через put в конец",
                () -> H_Map.put(10_000_001L, newStudent1));

        measureTime("Добавление элемента через put в начало",
                () -> H_Map.put(10_000_002L, newStudent2));

        measureTime("Взятие центрального элемента",
                () -> {
                    Student s = H_Map.get(5_000_000L);
                });

        measureTime("Взятие последнего элемента",
                () -> {
                    Student s = H_Map.get(9_999_999L);
                });

        measureTime("Удаление последнего элемента",
                () -> H_Map.remove(9_999_999L));

        measureTime("Удаление первого элемента",
                () -> H_Map.remove(0L));
    }

    static void measureTime(String taskName, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        System.out.println(taskName + " заняло: " + (end - start) + " нс");
    }
}

class Student {
    private long id;
    private String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
