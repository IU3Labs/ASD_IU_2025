package MainTask;
import java.util.*;

/**
 * Класс для тестирования производительности структур данных
 */
public class DataStructureTester {
    private static final int DATA_SIZE = 10000000;
    private static final long LAST_ID = DATA_SIZE - 1;
    private static final long MIDDLE_ID = DATA_SIZE / 2;
    private static final long NEW_ID = DATA_SIZE + 1;

    /**
     * Результаты тестирования одной структуры данных
     */
    private static class TestResults {
        long addEnd, addStart, removeLast, removeFirst, getMiddle, getLast;

        void printResults(String structureName) {
            System.out.println("\n=== Тестирование " + structureName + " ===");
            PerformanceTimer.printResult("Добавление в конец", addEnd);
            PerformanceTimer.printResult("Добавление в начало", addStart);
            PerformanceTimer.printResult("Удаление последнего элемента", removeLast);
            PerformanceTimer.printResult("Удаление первого элемента", removeFirst);
            PerformanceTimer.printResult("Получение центрального элемента", getMiddle);
            PerformanceTimer.printResult("Получение последнего элемента", getLast);
        }
    }

    /**
     * Создает список студентов для тестирования
     */
    private List<Student> createTestData() {
        List<Student> students = new ArrayList<>();
        for (long i = 0; i < DATA_SIZE; i++) {
            students.add(new Student(i, "Student_" + i));
        }
        return students;
    }

    /**
     * Общий метод для поиска студента по ID в любой коллекции
     */
    private void findStudentById(Iterable<Student> students, long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return;
            }
        }
    }

    /**
     * Общий метод тестирования операций для List
     */
    private TestResults testListOperations(List<Student> list) {
        TestResults results = new TestResults();
        PerformanceTimer timer = new PerformanceTimer();

        // 1. Добавление в конец
        timer.start();
        list.add(new Student(NEW_ID, "New_Student"));
        results.addEnd = timer.stop();

        // 2. Добавление в начало
        timer.start();
        list.addFirst(new Student(NEW_ID + 1, "New_Student_Start"));
        results.addStart = timer.stop();

        // 3. Удаление последнего элемента
        timer.start();
        list.removeLast();
        results.removeLast = timer.stop();

        // 4. Удаление первого элемента
        timer.start();
        list.removeFirst();
        results.removeFirst = timer.stop();

        // 5. Получение центрального элемента
        timer.start();
        findStudentById(list, MIDDLE_ID);
        results.getMiddle = timer.stop();

        // 6. Получение последнего элемента
        timer.start();
        findStudentById(list, LAST_ID);
        results.getLast = timer.stop();

        return results;
    }

    /**
     * Общий метод тестирования операций для Set
     */
    private TestResults testSetOperations(Set<Student> set) {
        TestResults results = new TestResults();
        PerformanceTimer timer = new PerformanceTimer();

        // 1. Добавление элемента
        timer.start();
        set.add(new Student(NEW_ID, "New_Student"));
        results.addEnd = timer.stop();

        // 2. Добавление в начало (не применимо)
        results.addStart = -1;

        // 3. Удаление последнего элемента (не применимо)
        results.removeLast = -1;

        // 4. Удаление первого элемента (не применимо)
        results.removeFirst = -1;

        // 5. Получение центрального элемента
        timer.start();
        findStudentById(set, MIDDLE_ID);
        results.getMiddle = timer.stop();

        // 6. Получение последнего элемента
        timer.start();
        findStudentById(set, LAST_ID);
        results.getLast = timer.stop();

        return results;
    }

    /**
     * Общий метод тестирования операций для Map
     */
    private TestResults testMapOperations(Map<Long, Student> map) {
        TestResults results = new TestResults();
        PerformanceTimer timer = new PerformanceTimer();

        // 1. Добавление элемента
        timer.start();
        map.put(NEW_ID, new Student(NEW_ID, "New_Student"));
        results.addEnd = timer.stop();

        // 2. Добавление в начало (не применимо)
        results.addStart = -1;

        // 3. Удаление последнего элемента
        timer.start();
        map.remove(LAST_ID);
        results.removeLast = timer.stop();

        // 4. Удаление первого элемента
        timer.start();
        map.remove(0L);
        results.removeFirst = timer.stop();

        // 5. Получение центрального элемента
        timer.start();
        map.get(MIDDLE_ID);
        results.getMiddle = timer.stop();

        // 6. Получение последнего элемента
        timer.start();
        map.get(LAST_ID - 1);
        results.getLast = timer.stop();

        return results;
    }

    /**
     * Тестирует ArrayList
     */
    public void testArrayList() {
        List<Student> arrayList = new ArrayList<>(createTestData());
        TestResults results = testListOperations(arrayList);
        results.printResults("ArrayList");
    }

    /**
     * Тестирует LinkedList
     */
    public void testLinkedList() {
        List<Student> linkedList = new LinkedList<>(createTestData());
        TestResults results = testListOperations(linkedList);
        results.printResults("LinkedList");
    }

    /**
     * Тестирует HashSet
     */
    public void testHashSet() {
        Set<Student> hashSet = new HashSet<>(createTestData());
        TestResults results = testSetOperations(hashSet);
        results.printResults("HashSet");
    }

    /**
     * Тестирует HashMap
     */
    public void testHashMap() {
        Map<Long, Student> hashMap = new HashMap<>();
        for (Student student : createTestData()) {
            hashMap.put(student.getId(), student);
        }

        TestResults results = testMapOperations(hashMap);
        results.printResults("HashMap");
    }

    /**
     * Запускает все тесты
     */
    public void runAllTests() {
        testArrayList();
        testLinkedList();
        testHashSet();
        testHashMap();
    }

    public static int getDataSize() {
        return DATA_SIZE;
    }
}