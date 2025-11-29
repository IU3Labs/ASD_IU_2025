package service;

import model.Student;
import util.TimeMeasurement;

import java.util.*;

/**
 * Класс для тестирования производительности различных коллекций.
 * Выполняет операции добавления, удаления и получения элементов,
 * измеряя время выполнения каждой операции в наносекундах.
 */
public class CollectionPerformanceTester {
    private final int dataSize;
    private List<Student> arrayList;
    private List<Student> linkedList;
    private Set<Student> hashSet;
    private Map<Long, Student> hashMap;

    public CollectionPerformanceTester(int dataSize) {
        this.dataSize = dataSize;
        initializeCollections();
    }

    /**
     * Инициализирует все коллекции тестовыми данными.
     */
    private void initializeCollections() {
        System.out.println("Инициализация коллекций с " + dataSize + " элементами...");

        // Создание ArrayList
        long time = TimeMeasurement.measureTime(() -> {
            arrayList = new ArrayList<>();
            for (long i = 0; i < dataSize; i++) {
                arrayList.add(new Student(i, "Student_" + i));
            }
        });
        System.out.println("ArrayList создан за: " + TimeMeasurement.formatNanos(time));

        // Создание LinkedList
        time = TimeMeasurement.measureTime(() -> {
            linkedList = new LinkedList<>();
            for (long i = 0; i < dataSize; i++) {
                linkedList.add(new Student(i, "Student_" + i));
            }
        });
        System.out.println("LinkedList создан за: " + TimeMeasurement.formatNanos(time));

        // Создание HashSet
        time = TimeMeasurement.measureTime(() -> {
            hashSet = new HashSet<>();
            for (long i = 0; i < dataSize; i++) {
                hashSet.add(new Student(i, "Student_" + i));
            }
        });
        System.out.println("HashSet создан за: " + TimeMeasurement.formatNanos(time));

        // Создание HashMap
        time = TimeMeasurement.measureTime(() -> {
            hashMap = new HashMap<>();
            for (long i = 0; i < dataSize; i++) {
                hashMap.put(i, new Student(i, "Student_" + i));
            }
        });
        System.out.println("HashMap создан за: " + TimeMeasurement.formatNanos(time));
    }

    /**
     * Запускает все тесты производительности для всех коллекций.
     */
    public void runAllTests() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("РЕЗУЛЬТАТЫ ТЕСТИРОВАНИЯ ПРОИЗВОДИТЕЛЬНОСТИ (в наносекундах)");
        System.out.println("=".repeat(80));

        testArrayList();
        testLinkedList();
        testHashSet();
        testHashMap();

    }

    /**
     * Тестирует операции для ArrayList.
     */
    private void testArrayList() {
        System.out.println("\n--- ArrayList Performance ---");

        long time;

        // 1. Добавление в конец
        time = TimeMeasurement.measureTime(() -> {
            arrayList.add(new Student((long) dataSize, "Student_" + dataSize));
        });
        System.out.println("1. Добавление в конец: " + TimeMeasurement.formatNanos(time));
        arrayList.remove(arrayList.size() - 1); // очистка

        // 2. Добавление в начало
        time = TimeMeasurement.measureTime(() -> {
            arrayList.add(0, new Student((long) dataSize + 1, "Student_" + (dataSize + 1)));
        });
        System.out.println("2. Добавление в начало: " + TimeMeasurement.formatNanos(time));
        arrayList.remove(0); // очистка

        // 3. Удаление последнего элемента
        time = TimeMeasurement.measureTime(() -> {
            Student removed = arrayList.remove(arrayList.size() - 1);
        });
        System.out.println("3. Удаление последнего элемента: " + TimeMeasurement.formatNanos(time));
        arrayList.add(new Student((long) dataSize - 1, "Student_" + (dataSize - 1))); // восстановление

        // 4. Удаление первого элемента
        time = TimeMeasurement.measureTime(() -> {
            Student removed = arrayList.remove(0);
        });
        System.out.println("4. Удаление первого элемента: " + TimeMeasurement.formatNanos(time));
        arrayList.add(0, new Student(0L, "Student_0")); // восстановление

        // 5. Получение центрального элемента
        time = TimeMeasurement.measureTime(() -> {
            Student middle = arrayList.get(dataSize / 2);
        });
        System.out.println("5. Получение центрального элемента: " + TimeMeasurement.formatNanos(time));

        // 6. Получение последнего элемента
        time = TimeMeasurement.measureTime(() -> {
            Student last = arrayList.get(arrayList.size() - 1);
        });
        System.out.println("6. Получение последнего элемента: " + TimeMeasurement.formatNanos(time));
    }

    /**
     * Тестирует операции для LinkedList.
     */
    private void testLinkedList() {
        System.out.println("\n--- LinkedList Performance ---");

        long time;

        // 1. Добавление в конец
        time = TimeMeasurement.measureTime(() -> {
            linkedList.add(new Student((long) dataSize, "Student_" + dataSize));
        });
        System.out.println("1. Добавление в конец: " + TimeMeasurement.formatNanos(time));
        linkedList.remove(linkedList.size() - 1); // очистка

        // 2. Добавление в начало
        time = TimeMeasurement.measureTime(() -> {
            linkedList.add(0, new Student((long) dataSize + 1, "Student_" + (dataSize + 1)));
        });
        System.out.println("2. Добавление в начало: " + TimeMeasurement.formatNanos(time));
        linkedList.remove(0); // очистка

        // 3. Удаление последнего элемента
        time = TimeMeasurement.measureTime(() -> {
            Student removed = linkedList.remove(linkedList.size() - 1);
        });
        System.out.println("3. Удаление последнего элемента: " + TimeMeasurement.formatNanos(time));
        linkedList.add(new Student((long) dataSize - 1, "Student_" + (dataSize - 1))); // восстановление

        // 4. Удаление первого элемента
        time = TimeMeasurement.measureTime(() -> {
            Student removed = linkedList.remove(0);
        });
        System.out.println("4. Удаление первого элемента: " + TimeMeasurement.formatNanos(time));
        linkedList.add(0, new Student(0L, "Student_0")); // восстановление

        // 5. Получение центрального элемента
        time = TimeMeasurement.measureTime(() -> {
            Student middle = linkedList.get(dataSize / 2);
        });
        System.out.println("5. Получение центрального элемента: " + TimeMeasurement.formatNanos(time));

        // 6. Получение последнего элемента
        time = TimeMeasurement.measureTime(() -> {
            Student last = linkedList.get(linkedList.size() - 1);
        });
        System.out.println("6. Получение последнего элемента: " + TimeMeasurement.formatNanos(time));
    }

    /**
     * Тестирует операции для HashSet.
     */
    private void testHashSet() {
        System.out.println("\n--- HashSet Performance ---");

        long time;

        // 1. Добавление элемента
        time = TimeMeasurement.measureTime(() -> {
            hashSet.add(new Student((long) dataSize, "Student_" + dataSize));
        });
        System.out.println("1. Добавление элемента: " + TimeMeasurement.formatNanos(time));
        hashSet.remove(new Student((long) dataSize, "Student_" + dataSize)); // очистка
    }

    /**
     * Тестирует операции для HashMap.
     */
    private void testHashMap() {
        System.out.println("\n--- HashMap Performance ---");

        long time;

        // 1. Добавление элемента
        time = TimeMeasurement.measureTime(() -> {
            hashMap.put((long) dataSize, new Student((long) dataSize, "Student_" + dataSize));
        });
        System.out.println("1. Добавление элемента: " + TimeMeasurement.formatNanos(time));
        hashMap.remove((long) dataSize); // очистка

        time = TimeMeasurement.measureTime(() -> {
            Student middle = hashMap.get((long) dataSize / 2);
        });
        System.out.println("5. Получение центрального элемента: " + TimeMeasurement.formatNanos(time));

        // 6. Получение элемента по ключу
        time = TimeMeasurement.measureTime(() -> {
            Student last = hashMap.get((long) dataSize - 1);
        });
        System.out.println("6. Получение последнего элемента: " + TimeMeasurement.formatNanos(time));
    }



    // Геттеры для доступа к коллекциям
    public List<Student> getArrayList() {
        return arrayList;
    }

    public List<Student> getLinkedList() {
        return linkedList;
    }

    public Set<Student> getHashSet() {
        return hashSet;
    }

    public Map<Long, Student> getHashMap() {
        return hashMap;
    }
}