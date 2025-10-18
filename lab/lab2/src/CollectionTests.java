import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class CollectionTests {
    private static long startTime;
    private static long finishTime;
    private static long resultTime;

    public static void testArrayList(ArrayList<Student> list) {
        System.out.println("!!!Test ArrayList:!!!");

        startTime = System.nanoTime();
        list.add(new Student((long)(list.size() + 1), "Student " + list.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.add(0, new Student((long)(list.size() + 1), "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(0);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() / 2);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6. " + resultTime + " ns");
    }

    public static void testLinkedList(LinkedList<Student> list) {
        System.out.println("!!!Test LinkedList:!!!");

        startTime = System.nanoTime();
        list.add(new Student((long)(list.size() + 1), "Student " + list.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.add(0, new Student((long)(list.size() + 1), "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.remove(0);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() / 2);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5. " + resultTime + " ns");

        startTime = System.nanoTime();
        list.get(list.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6. " + resultTime + " ns");
    }

    public static void testHashSet(HashSet<Student> set) {
        System.out.println("!!!Test HashSet:!!!");
        startTime = System.nanoTime();
        set.add(new Student((long)(set.size() + 1), "Student " + set.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1. " + resultTime + " ns");

        startTime = System.nanoTime();
        set.add(new Student(0L, "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2. " + resultTime + " ns");

        startTime = System.nanoTime();
        set.remove(new Student((long)(set.size() - 1), "Student " + set.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3. " + resultTime + " ns");

        startTime = System.nanoTime();
        set.remove(new Student(0L, "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4. " + resultTime + " ns");

        startTime = System.nanoTime();
        set.contains(new Student(5000000L, "Student 5000000"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5. " + resultTime + " ns");

        startTime = System.nanoTime();
        set.contains(new Student((long)set.size(), "Stedent " + set.size()));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6. " + resultTime + " ns");
    }

    public static void testHashMap(HashMap<Long, Student> map) {
        System.out.println("!!!Test HashMap:!!!");

        startTime = System.nanoTime();
        map.put((long)map.size(), new Student((long)(map.size() + 1), "Student " + map.size() + "1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("1. " + resultTime + " ns");

        startTime = System.nanoTime();
        map.put((long)map.size(), new Student(0L, "New student 1"));
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("2. " + resultTime + " ns");

        startTime = System.nanoTime();
        map.remove(map.size() - 1);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("3. " + resultTime + " ns");

        startTime = System.nanoTime();
        map.remove(0);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("4. " + resultTime + " ns");

        startTime = System.nanoTime();
        map.get(5000000);
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("5. " + resultTime + " ns");

        startTime = System.nanoTime();
        map.get(map.size());
        finishTime = System.nanoTime();
        resultTime = finishTime - startTime;
        System.out.println("6. " + resultTime + " ns");
    }
}
