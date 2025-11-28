package lab2;

import java.util.*;

public class RequiredTask {

    public static void main(String[] args) {
        final int SIZE = 1_000_000; //

        ArrayList<Student> arrayList = new ArrayList<>(SIZE);
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>((int)(SIZE * 1.5));
        HashMap<Long, Student> hashMap = new HashMap<>((int)(SIZE * 1.5));

        for (int i = 0; i < SIZE; i++) {
            Student st = new Student((long) i, "Student" + i);
            arrayList.add(st);
            linkedList.add(st);
            hashSet.add(st);
            hashMap.put((long) i, st);
        }

        System.out.println("--- ArrayList ---");
        testArrayList(arrayList);

        System.out.println("\n--- LinkedList ---");
        testLinkedList(linkedList);

        System.out.println("\n--- HashSet ---");
        testHashSet(hashSet);

        System.out.println("\n--- HashMap ---");
        testHashMap(hashMap);
    }


    // ArrayList
    private static void testArrayList(ArrayList<Student> list) {
        long start, finish;
        int size = list.size();

        // 1 — добавление в конец
        start = System.nanoTime();
        list.add(new Student((long) size, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в конец: " + (finish - start));
        list.remove(list.size() - 1);

        // 2 — добавление в начало
        start = System.nanoTime();
        list.add(0, new Student((long) size + 1L, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в начало: " + (finish - start));
        list.remove(0);

        // 3 — удаление последнего
        Student lastSaved = list.get(list.size() - 1);
        start = System.nanoTime();
        list.remove(list.size() - 1);
        finish = System.nanoTime();
        System.out.println("Удаление последнего: " + (finish - start));
        list.add(lastSaved);

        // 4 — удаление первого
        Student firstSaved = list.get(0);
        start = System.nanoTime();
        list.remove(0);
        finish = System.nanoTime();
        System.out.println("Удаление первого: " + (finish - start));
        list.add(0, firstSaved);

        // 5 — получение середины
        start = System.nanoTime();
        Student mid = list.get(size / 2);
        finish = System.nanoTime();
        System.out.println("Получение середины: " + (finish - start));

        // 6 — получение последнего
        start = System.nanoTime();
        Student last = list.get(size - 1);
        finish = System.nanoTime();
        System.out.println("Получение последнего: " + (finish - start));
    }


    // LinkedList
    private static void testLinkedList(LinkedList<Student> list) {
        long start, finish;
        int size = list.size();

        // 1 — добавление в конец
        start = System.nanoTime();
        list.addLast(new Student((long) size, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в конец: " + (finish - start));
        list.removeLast();

        // 2 — добавление в начало
        start = System.nanoTime();
        list.addFirst(new Student((long) size + 1L, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в начало: " + (finish - start));
        list.removeFirst();

        // 3 — удаление последнего
        Student lastSaved = list.getLast();
        start = System.nanoTime();
        list.removeLast();
        finish = System.nanoTime();
        System.out.println("Удаление последнего: " + (finish - start));
        list.addLast(lastSaved);

        // 4 — удаление первого
        Student firstSaved = list.getFirst();
        start = System.nanoTime();
        list.removeFirst();
        finish = System.nanoTime();
        System.out.println("Удаление первого: " + (finish - start));
        list.addFirst(firstSaved);

        // 5 — получение середины
        start = System.nanoTime();
        Student mid = list.get(size / 2);
        finish = System.nanoTime();
        System.out.println("Получение середины: " + (finish - start));

        // 6 — получение последнего
        start = System.nanoTime();
        Student last = list.getLast();
        finish = System.nanoTime();
        System.out.println("Получение последнего: " + (finish - start));
    }


    // HashSet
    private static void testHashSet(HashSet<Student> set) {
        long start, finish;
        int size = set.size();

        // 1 — добавление в конец
        start = System.nanoTime();
        set.add(new Student((long) size, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в конец: " + (finish - start));
        set.remove(new Student((long) size, "x"));

        // 2 — добавление в начало
        start = System.nanoTime();
        set.add(new Student((long) size + 1L, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в начало: " + (finish - start));
        set.remove(new Student((long) size + 1L, "x"));

        // 3 — удаление последнего (по id=последнего)
        start = System.nanoTime();
        set.remove(new Student((long) size - 1L, "x"));
        finish = System.nanoTime();
        System.out.println("Удаление последнего: " + (finish - start));
        set.add(new Student((long) size - 1L, "x"));

        // 4 — удаление первого (id=0)
        start = System.nanoTime();
        set.remove(new Student(0L, "x"));
        finish = System.nanoTime();
        System.out.println("Удаление первого: " + (finish - start));
        set.add(new Student(0L, "x"));

        // 5 — получение середины
        start = System.nanoTime();
        set.contains(new Student((long) size / 2L, "x"));
        finish = System.nanoTime();
        System.out.println("Получение середины: " + (finish - start));

        // 6 — получение последнего
        start = System.nanoTime();
        set.contains(new Student((long) size - 1L, "x"));
        finish = System.nanoTime();
        System.out.println("Получение последнего: " + (finish - start));
    }


    // HashMap
    private static void testHashMap(HashMap<Long, Student> map) {
        long start, finish;
        int size = map.size();

        // 1 — добавление в конец
        start = System.nanoTime();
        map.put((long) size, new Student((long) size, "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в конец: " + (finish - start));
        map.remove((long) size);

        // 2 — добавление в начало
        start = System.nanoTime();
        map.put((long) (size + 1L), new Student((long) (size + 1L), "x"));
        finish = System.nanoTime();
        System.out.println("Добавление в начало: " + (finish - start));
        map.remove((long) (size + 1L));

        // 3 — удаление последнего
        start = System.nanoTime();
        map.remove((long) (size - 1L));
        finish = System.nanoTime();
        System.out.println("Удаление последнего: " + (finish - start));
        map.put((long) (size - 1L), new Student((long) (size - 1L), "x"));

        // 4 — удаление первого
        start = System.nanoTime();
        map.remove(0L);
        finish = System.nanoTime();
        System.out.println("Удаление первого: " + (finish - start));
        map.put(0L, new Student(0L, "x"));

        // 5 — получение середины
        start = System.nanoTime();
        map.get((long) (size / 2L));
        finish = System.nanoTime();
        System.out.println("Получение середины: " + (finish - start));

        // 6 — получение последнего
        start = System.nanoTime();
        map.get((long) (size - 1L));
        finish = System.nanoTime();
        System.out.println("Получение последнего: " + (finish - start));
    }
}
