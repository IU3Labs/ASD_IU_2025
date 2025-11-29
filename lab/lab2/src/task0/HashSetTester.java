package task0;

import java.util.HashSet;
import java.util.Set;

public class HashSetTester extends CollectionTester {
    private Set<Student> hashSet;
    private long creationTime;

    public HashSetTester(int size) {
        super("HashSet", size);
    }

    @Override
    public void createCollection() {
        long startTime = System.nanoTime();
        hashSet = new HashSet<>();
        for (long i = 0; i < size; i++) {
            hashSet.add(new Student(i, "Student" + i));
        }
        creationTime = System.nanoTime() - startTime;
    }

    @Override
    public void testAddToEnd() {
        long startTime = System.nanoTime();
        hashSet.add(new Student((long) size + 1, "NewStudent"));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testAddToStart() {
        System.out.print("N/A "); // Для Set нет понятия "начала"
        testAddToEnd();
    }

    @Override
    public void testRemoveLast() {
        long startTime = System.nanoTime();
        hashSet.remove(new Student((long) size - 1, ""));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testRemoveFirst() {
        long startTime = System.nanoTime();
        hashSet.remove(new Student(0L, ""));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetMiddle() {
        long startTime = System.nanoTime();
        boolean contains = hashSet.contains(new Student((long) size / 2, ""));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetLast() {
        long startTime = System.nanoTime();
        boolean contains = hashSet.contains(new Student((long) size - 1, ""));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    public static void main(String[] args) {
        HashSetTester tester = new HashSetTester(100000);
        tester.createCollection();
        tester.runAllTests();
    }
}

/*
Тестирование HashSet
Результаты тестов (в наносекундах):
Добавление в конец: 310959 нс
Добавление в начало: N/A 496375 нс
Удаление последнего: 127083 нс
Удаление первого: 156667 нс
Получение центрального: 295333 нс
Получение последнего: 114792 нс
*/
