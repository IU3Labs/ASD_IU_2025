package lab2;

import java.util.*;

public class RequiredTask {

    public static void main(String[] args) {

        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap<>();

        long size = 1_000_000L;

        for (long i = 1; i <= size; i++) {
            Student student = new Student(i, "Student" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }

        System.out.println("--- ArrayList ---");
        testArrayList(arrayList);

        System.out.println("--- LinkedList ---");
        testLinkedList(linkedList);

        System.out.println("--- HashSet ---");
        testHashSet(hashSet);

        System.out.println("--- HashMap ---");
        testHashMap(hashMap);
    }

    //ArrayList
    private static void testArrayList(ArrayList<Student> list) {

        long start, finish;

        // 1 — Добавление в конец
        start = System.nanoTime();
        list.add(new Student(10_000_001L, "Student10000001"));
        finish = System.nanoTime();
        System.out.println("добавление в конец: " + (finish - start) + " нс");
        list.remove(list.size() - 1);

        // 2 — Добавление в начало
        start = System.nanoTime();
        list.add(0, new Student(10_000_002L, "Student10000002"));
        finish = System.nanoTime();
        System.out.println("добавление в начало: " + (finish - start) + " нс");
        list.remove(0);

        // 3 — Удаление последнего
        start = System.nanoTime();
        list.remove(list.size() - 1);
        finish = System.nanoTime();
        System.out.println("удаление последнего: " + (finish - start) + " нс");

        list.add(new Student(10_000_003L, "Student10000003"));

        // 4 — Удаление первого
        start = System.nanoTime();
        list.remove(0);
        finish = System.nanoTime();
        System.out.println("удаление первого: " + (finish - start) + " нс");

        list.add(0, new Student(10_000_004L, "Student10000004"));

        // 5 — Получение центрального
        start = System.nanoTime();
        Student middle = list.get(list.size() / 2);
        finish = System.nanoTime();
        System.out.println("получение середины: " + (finish - start) + " нс");

        // 6 — Получение последнего
        start = System.nanoTime();
        Student last = list.get(list.size() - 1);
        finish = System.nanoTime();
        System.out.println("получение последнего: " + (finish - start) + " нс");
    }


    //LinkedList
    private static void testLinkedList(LinkedList<Student> list) {

        long start, finish;

        // 1 — Добавление в конец
        start = System.nanoTime();
        list.addLast(new Student(10_000_001L, "Student10000001"));
        finish = System.nanoTime();
        System.out.println("добавление в конец: " + (finish - start) + " нс");
        list.removeLast();

        // 2 — Добавление в начало
        start = System.nanoTime();
        list.addFirst(new Student(10_000_002L, "Student10000002"));
        finish = System.nanoTime();
        System.out.println("добавление в начало: " + (finish - start) + " нс");
        list.removeFirst();

        // 3 — Удаление последнего
        start = System.nanoTime();
        list.removeLast();
        finish = System.nanoTime();
        System.out.println("удаление последнего: " + (finish - start) + " нс");

        list.addLast(new Student(10_000_003L, "Student10000003"));

        // 4 — Удаление первого
        start = System.nanoTime();
        list.removeFirst();
        finish = System.nanoTime();
        System.out.println("удаление первого: " + (finish - start) + " нс");

        list.addFirst(new Student(10_000_004L, "Student10000004"));

        // 5 — Получение середины
        start = System.nanoTime();
        Student middle = list.get(list.size() / 2);
        finish = System.nanoTime();
        System.out.println("получение середины: " + (finish - start) + " нс");

        // 6 — Получение последнего
        start = System.nanoTime();
        Student last = list.getLast();
        finish = System.nanoTime();
        System.out.println("получение последнего: " + (finish - start) + " нс");
    }


    //HashSet
    private static void testHashSet(HashSet<Student> set) {

        long start, finish;

        // 1 — Добавление
        start = System.nanoTime();
        set.add(new Student(10_000_001L, "Student10000001"));
        finish = System.nanoTime();
        System.out.println("добавление: " + (finish - start) + " нс");

        Student check = new Student(500_000L, "Student500000");

        // 2 — contains
        start = System.nanoTime();
        boolean exists = set.contains(check);
        finish = System.nanoTime();
        System.out.println("contains: " + (finish - start) + " нс");

        // 3 — Удаление
        start = System.nanoTime();
        set.remove(check);
        finish = System.nanoTime();
        System.out.println("удаление: " + (finish - start) + " нс");

        // 4 — Итерация
        start = System.nanoTime();
        for (Student s : set) { break; }
        finish = System.nanoTime();
        System.out.println("итерация: " + (finish - start) + " нс");
    }


    //HashMap
    private static void testHashMap(Map<Long, Student> map) {

        long start, finish;

        long newKey = 1_000_001L;

        // 1 — Добавление нового ключа
        start = System.nanoTime();
        map.put(newKey, new Student(newKey, "Student10000001"));
        finish = System.nanoTime();
        System.out.println("добавление ключа: " + (finish - start) + " нс");
        map.remove(newKey);

        // 2 — Удаление ключа
        start = System.nanoTime();
        map.remove(1L);
        finish = System.nanoTime();
        System.out.println("удаление ключа: " + (finish - start) + " нс");
        map.put(1L, new Student(1L, "Student1"));

        // 3 — Получение центрального ключа
        long mid = map.size() / 2;
        start = System.nanoTime();
        Student middle = map.get(mid);
        finish = System.nanoTime();
        System.out.println("получение середины: " + (finish - start) + " нс");

        // 4 — Получение последнего
        start = System.nanoTime();
        Student last = map.get(map.size() - 1L);
        finish = System.nanoTime();
        System.out.println("получение последнего: " + (finish - start) + " нс");

        // 5 — Итерация
        start = System.nanoTime();
        for (Map.Entry<Long, Student> entry : map.entrySet()) {
            break;
        }
        finish = System.nanoTime();
        System.out.println("итерация: " + (finish - start) + " нс");
    }
}
