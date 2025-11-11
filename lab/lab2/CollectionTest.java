package lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class CollectionTest {
    private static long startTime;
    private static long finishTime;
    private static long resultTime;

    public static void testArrayList(ArrayList<Student> list) {
        System.out.println("Test ArrayList:");

        startTime = System.nanoTime();
        list.add(new Student((long)(list.size() + 1), "Student " + list.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1.Adding to the end: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.add(0, new Student((long)(list.size() + 1), "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2.Adding to the beginning: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3.Removing from the end: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(0);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4.Removing from the beginning: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() / 2);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5.Reading from the middle: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6.Reading from the end: " + resultTime + " ns");
    }

    public static void testLinkedList(LinkedList<Student> list) {
        System.out.println("Test LinkedList:");

        startTime = System.nanoTime();
        list.add(new Student((long)(list.size() + 1), "Student " + list.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1.Adding to the end: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.add(0, new Student((long)(list.size() + 1), "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2.Adding to the beginning: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3.Removing from the end: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(0);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4.Removing from the beginning: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() / 2);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5.Reading from the middle: " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6.Reading from the end: " + resultTime + " ns");
    }

    public static void testHashSet(HashSet<Student> set) {
        System.out.println("Test HashSet:");
        startTime = System.nanoTime();
        set.add(new Student((long)(set.size() + 1), "Student " + set.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1.Adding a new unique element: " + resultTime + " ns");

        startTime = System.nanoTime();
        set.add(new Student(0L, "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2.Adding another unique element:  " + resultTime + " ns");

        startTime = System.nanoTime();
        set.remove(new Student((long)(set.size() - 1), "Student " + set.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3.Removing an existing element by value: " + resultTime + " ns");

        startTime = System.nanoTime();
        set.remove(new Student(0L, "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4.Removing another existing element by value: " + resultTime + " ns");

        startTime = System.nanoTime();
        set.contains(new Student(5000000L, "Student 5000000"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5.Checking for existence of a non-existent element: " + resultTime + " ns");

        startTime = System.nanoTime();
        set.contains(new Student((long)set.size(), "Stedent " + set.size()));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6.Checking for existence of an existing element " + resultTime + " ns");
    }

    public static void testHashMap(HashMap<Long, Student> map) {
        System.out.println("Test HashMap:");

        startTime = System.nanoTime();
        map.put((long)map.size(), new Student((long)(map.size() + 1), "Student " + map.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1.Adding a new Key-Value pair: " + resultTime + " ns");

        startTime = System.nanoTime();
        map.put((long)map.size(), new Student(0L, "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2.Adding another new Key-Value pair: " + resultTime + " ns");

        startTime = System.nanoTime();
        map.remove(map.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3.Removing an existing entry by Key: " + resultTime + " ns");

        startTime = System.nanoTime();
        map.remove(0);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4.Removing another existing entry by Key: " + resultTime + " ns");

        startTime = System.nanoTime();
        map.get(5000000);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5.Getting a value using a non-existent Key: " + resultTime + " ns");

        startTime = System.nanoTime();
        map.get(map.size());
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6.Getting a value using an existing Key: " + resultTime + " ns");
    }
}