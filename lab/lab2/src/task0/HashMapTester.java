package task0;

import java.util.HashMap;
import java.util.Map;

public class HashMapTester extends CollectionTester {

    public HashMapTester(int size) {
        super("HashMap", size);
    }

    private Map<Long, Student> hashMap;
    private long creationTime;

    @Override
    public void createCollection() {
        long startTime = System.nanoTime();
        hashMap = new HashMap<>();
        for (long i = 0; i < size; i++) {
            hashMap.put(i, new Student(i, "Student" + i));
        }
        creationTime = System.nanoTime() - startTime;
    }

    @Override
    public void testAddToEnd() {
        long startTime = System.nanoTime();
        hashMap.put((long) size + 1, new Student((long) size + 1, "NewStudent"));
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testAddToStart() {
        System.out.print("N/A "); // Для Map нет понятия "начала"
        testAddToEnd();
    }

    @Override
    public void testRemoveLast() {
        long startTime = System.nanoTime();
        hashMap.remove((long) size - 1);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testRemoveFirst() {
        long startTime = System.nanoTime();
        hashMap.remove(0L);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetMiddle() {
        long startTime = System.nanoTime();
        Student middle = hashMap.get((long) size / 2);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public void testGetLast() {
        long startTime = System.nanoTime();
        Student last = hashMap.get((long) size - 1);
        long duration = System.nanoTime() - startTime;
        System.out.println(duration + " нс");
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    public static void main(String[] args) {
        HashMapTester tester = new HashMapTester(100000);
        tester.createCollection();
        tester.runAllTests();
    }
}
/*
Тестирование HashMap
Результаты тестов (в наносекундах):
Добавление в конец: 437583 нс
Добавление в начало: N/A 581375 нс
Удаление последнего: 54208 нс
Удаление первого: 90083 нс
Получение центрального: 298792 нс
Получение последнего: 215250 нс
*/