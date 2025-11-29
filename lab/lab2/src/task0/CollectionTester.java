package task0;

import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class CollectionTester {
    protected String collectionName;
    protected int size;

    public CollectionTester(String collectionName, int size) {
        this.collectionName = collectionName;
        this.size = size;
    }

    public abstract void createCollection();
    public abstract void testAddToEnd();
    public abstract void testAddToStart();
    public abstract void testRemoveLast();
    public abstract void testRemoveFirst();
    public abstract void testGetMiddle();
    public abstract void testGetLast();
    public abstract long getCreationTime();

    public void runAllTests() {
        System.out.println("\nТестирование " + collectionName);

        System.out.println("Результаты тестов (в наносекундах):");

        System.out.print("Добавление в конец: ");
        testAddToEnd();

        System.out.print("Добавление в начало: ");
        testAddToStart();

        System.out.print("Удаление последнего: ");
        testRemoveLast();

        System.out.print("Удаление первого: ");
        testRemoveFirst();

        System.out.print("Получение центрального: ");
        testGetMiddle();

        System.out.print("Получение последнего: ");
        testGetLast();
    }

    // Статический метод для запуска всех тестов
    public static void runPerformanceTests() {
        int size = 1_000_000;

        System.out.println("ТЕСТИРОВАНИЕ КОЛЛЕКЦИЙ");
        System.out.println("Размер тестовых данных: " + size + " элементов");

        CollectionTester[] testers = {
                new ArrayListTester(size),
                new LinkedListTester(size),
                new HashSetTester(size),
                new HashMapTester(size)
        };

        for (CollectionTester tester : testers) {
            tester.createCollection();
            tester.runAllTests();
        }
    }
}
