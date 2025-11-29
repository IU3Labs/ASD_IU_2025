package org.example.lab2.core;

import org.example.lab2.util.Stopwatch;

import java.util.*;

/**
 * Основной бенчмарк коллекций.
 * Замеряет все операции, указанные в задании.
 */
public class CollectionsBenchmark {
    public static final int DEFAULT_N = 10_000_000; // можно поднять до 10_000_000

    private final List<Student> arrayList = new ArrayList<>();
    private final LinkedList<Student> linkedList = new LinkedList<>();
    private final Set<Student> hashSet = new HashSet<>();
    private final Map<Long, Student> hashMap = new HashMap<>();

    private long middleId;
    private long lastId;

    public void fillCollections(int n) {
        arrayList.clear();
        linkedList.clear();
        hashSet.clear();
        hashMap.clear();

        for (int i = 1; i <= n; i++) {
            Student s = new Student((long) i, "S" + i);
            arrayList.add(s);
            linkedList.add(s);
            hashSet.add(s);
            hashMap.put((long) i, s);
        }

        middleId = n / 2L;
        lastId = n;
    }

    public long addToEndArrayList() {
        Student s = new Student(lastId + 1, "S" + (lastId + 1));
        return Stopwatch.measure(() -> arrayList.add(s));
    }

    public long addToStartLinkedList() {
        Student s = new Student(0L, "S0");
        return Stopwatch.measure(() -> linkedList.addFirst(s));
    }

    public long removeLastArrayList() {
        return Stopwatch.measure(() -> arrayList.remove(arrayList.size() - 1));
    }

    public long removeFirstLinkedList() {
        return Stopwatch.measure(() -> linkedList.removeFirst());
    }

    public long getMiddleFromArrayList() {
        final Student[] tmp = new Student[1];
        return Stopwatch.measure(() -> tmp[0] = arrayList.get(arrayList.size() / 2));
    }

    public long getLastFromHashMap() {
        final Student[] tmp = new Student[1];
        return Stopwatch.measure(() -> tmp[0] = hashMap.get(lastId));
    }
}