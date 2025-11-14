package lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    public static final int SIZE = 1000000; // 1 млн для надежности
    public static void main() {
        ArrayList<Student> arrlist = new ArrayList<>();
        LinkedList<Student> linklist = new LinkedList<>();
        HashSet<Student> sett = new HashSet<>();
        HashMap<Long, Student> mapa = new HashMap<>();

        for (long i = 0; i < SIZE; i++) {
            Student student = new Student(i, "Bob");
            arrlist.add(student);
            linklist.add(student);
            sett.add(student);
            mapa.put(i, student);
        }
        TestArray(arrlist);
        TestLink(linklist);
        TestSet(sett);
        TestMap(mapa);

    }
    // ------------------------- Array List ------------------------------
    public static void TestArray(ArrayList<Student> arrlist) {
        System.out.println("-----Тестирование ArrayList-----");

        // Добавление 1 несуществующего элемента в конец
        long startTime = System.nanoTime();
        arrlist.add(new Student(10000001L, "New_Student")); // id = 10 000 001
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление 1 несуществующего элемента в начало.
        startTime = System.nanoTime();
        arrlist.addFirst(new Student(10000002L, "New_Student_Start"));
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        arrlist.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        arrlist.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого: " + (endTime - startTime) + " нс");

        // Взятие (Get) центрального элемента
        startTime = System.nanoTime();
        Student middleElement = arrlist.get(SIZE / 2);
        endTime = System.nanoTime();
        System.out.println("Получение центрального: " + (endTime - startTime) + " нс");

        // Взятие (Get) последнего элемента
        startTime = System.nanoTime();
        Student lastElement = arrlist.getLast();
        endTime = System.nanoTime();
        System.out.println("Получение последнего: " + (endTime - startTime) + " нс");
    }


// ------------------------- Link List ------------------------------
public static void TestLink(LinkedList<Student> linklist) {
    System.out.println("\n-----Тестирование LinkedList-----");

    // Добавление 1 несуществующего элемента в конец
    long startTime = System.nanoTime();
    linklist.add(new Student(10000001L, "New_Student")); // id = 10 000 001
    long endTime = System.nanoTime();
    System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

    // Добавление 1 несуществующего элемента в начало.
    startTime = System.nanoTime();
    linklist.addFirst(new Student(10000002L, "New_Student_Start"));
    endTime = System.nanoTime();
    System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

    // Удаление последнего элемента
    startTime = System.nanoTime();
    linklist.removeLast();
    endTime = System.nanoTime();
    System.out.println("Удаление последнего: " + (endTime - startTime) + " нс");

    // Удаление первого элемента
    startTime = System.nanoTime();
    linklist.removeFirst();
    endTime = System.nanoTime();
    System.out.println("Удаление первого: " + (endTime - startTime) + " нс");

    // Взятие (Get) центрального элемента
    startTime = System.nanoTime();
    Student middleElement = linklist.get(SIZE / 2);
    endTime = System.nanoTime();
    System.out.println("Получение центрального: " + (endTime - startTime) + " нс");

    // Взятие (Get) последнего элемента
    startTime = System.nanoTime();
    Student lastElement = linklist.getLast();
    endTime = System.nanoTime();
    System.out.println("Получение последнего: " + (endTime - startTime) + " нс");
    }

    // ------------------------- HashSet ------------------------------
    public static void TestSet (HashSet<Student> sett) {
        System.out.println("\n-----Тестирование HashSet-----");

        // Добавление 1 несуществующего элемента в конец
        long startTime = System.nanoTime();
        sett.add(new Student(10000001L, "New_Student")); // id = 10 000 001
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление 1 несуществующего элемента в начало.
        startTime = System.nanoTime();
        sett.add(new Student(10000002L, "New_Student_Start"));
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        sett.remove((long) SIZE-1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        sett.remove((long) 0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого: " + (endTime - startTime) + " нс");

        // Взятие (Get) центрального элемента
        startTime = System.nanoTime();
        sett.contains((long) SIZE/2);
        endTime = System.nanoTime();
        System.out.println("Получение центрального: " + (endTime - startTime) + " нс");

        // Взятие (Get) последнего элемента
        startTime = System.nanoTime();
        sett.contains((long) SIZE-1);
        endTime = System.nanoTime();
        System.out.println("Получение последнего: " + (endTime - startTime) + " нс");
    }

    // ------------------------- HashSet ------------------------------
    public static void TestMap (HashMap<Long, Student> mapa) {
        System.out.println("\n-----Тестирование HashMap-----");

        // Добавление 1 несуществующего элемента в конец
        long startTime = System.nanoTime();
        mapa.put((long) SIZE + 1, new Student(10000001L, "New_Student"));
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление 1 несуществующего элемента в начало.
        startTime = System.nanoTime();
        mapa.put((long) 0, new Student(10000002L, "New_Student"));
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        mapa.remove((long) SIZE-1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        mapa.remove((long) 0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого: " + (endTime - startTime) + " нс");

        // Взятие (Get) центрального элемента
        startTime = System.nanoTime();
        mapa.get((long) SIZE / 2);
        endTime = System.nanoTime();
        System.out.println("Получение центрального: " + (endTime - startTime) + " нс");

        // Взятие (Get) последнего элемента
        startTime = System.nanoTime();
        mapa.get((long) SIZE-1);
        endTime = System.nanoTime();
        System.out.println("Получение последнего: " + (endTime - startTime) + " нс");
    }

}
