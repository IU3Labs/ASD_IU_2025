import java.util.*;

public class Task0 {
    public static class Student {
        Long id;
        String name;

        public Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private static final int TOTAL_STUDENTS = 1_000_000;

    public static void main(String[] args) {
        ArrayList<Student> list1 = new ArrayList<>();
        LinkedList<Student> list2 = new LinkedList<>();
        HashSet<Student> set1 = new HashSet<>();
        HashMap<Long, Student> map1 = new HashMap<>();

        for (long i = 0; i < TOTAL_STUDENTS; i++) {
            Student s = new Student(i, "Student_" + i);

            list1.add(s);
            list2.add(s);
            set1.add(s);
            map1.put(i, s);
        }

        // Тестируем производительность каждой коллекции
        System.out.println("\n=== HashSet Test ===");
        testSet(set1);

        System.out.println("\n=== ArrayList Test ===");
        testList(list1);

        System.out.println("\n=== HashMap Test ===");
        testMap(map1);

        System.out.println("\n=== LinkedList Test ===");
        testList(list2);
    }

    private static long Time(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        return System.nanoTime() - start;
    }

    // Тестирование ArrayList и LinkedList
    private static void testList(List<Student> list) {
        // 1. Добавление в конец
        System.out.println("Add to end: " + Time(() ->
                list.add(new Student(1_000_001L, "Extra_End"))) + " ns");

        // 2. Добавление в начало
        System.out.println("Add to start: " + Time(() ->
                list.add(0, new Student(1_000_002L, "Extra_Start"))) + " ns");

        // 3. Удаление последнего элемента
        System.out.println("Remove last: " + Time(() ->
                list.remove(list.size() - 1)) + " ns");

        // 4. Удаление первого элемента
        System.out.println("Remove first: " + Time(() ->
                list.remove(0)) + " ns");

        // 5. Получение среднего элемента
        System.out.println("Get middle: " + Time(() ->
                list.get(TOTAL_STUDENTS / 2)) + " ns");

        // 6. Получение последнего элемента
        System.out.println("Get last: " + Time(() ->
                list.get(TOTAL_STUDENTS - 1)) + " ns");
    }

    // Тестирование HashSet
    private static void testSet(Set<Student> set) {
        // Создаем тестовых студентов
        Student extra1 = new Student(1_000_001L, "Test1");
        Student extra2 = new Student(1_000_002L, "Test2");

        // 1. Добавление первого элемента
        System.out.println("Add first: " + Time(() ->
                set.add(extra1)) + " ns");

        // 2. Добавление второго элемента
        System.out.println("Add second: " + Time(() ->
                set.add(extra2)) + " ns");

        // 3. Удаление первого элемента
        System.out.println("Remove first: " + Time(() ->
                set.remove(extra1)) + " ns");

        // 4. Удаление второго элемента
        System.out.println("Remove second: " + Time(() ->
                set.remove(extra2)) + " ns");

        // 5. Проверка наличия среднего элемента
        System.out.println("Contains middle: " + Time(() ->
                set.contains(new Student(500_000L, "Student_500000"))) + " ns");

        // 6. Проверка наличия последнего элемента
        System.out.println("Contains last: " + Time(() ->
                set.contains(new Student(999_999L, "Student_999999"))) + " ns");
    }

    // Тестирование HashMap
    private static void testMap(Map<Long, Student> map) {
        // 1. Добавление первой записи
        System.out.println("Put first: " + Time(() ->
                map.put(10_000_001L, new Student(1_000_001L, "Extra_Map1"))) + " ns");

        // 2. Добавление второй записи
        System.out.println("Put second: " + Time(() ->
                map.put(10_000_002L, new Student(1_000_002L, "Extra_Map2"))) + " ns");

        // 3. Удаление первой записи
        System.out.println("Remove first: " + Time(() ->
                map.remove(1_000_001L)) + " ns");

        // 4. Удаление второй записи
        System.out.println("Remove second: " + Time(() ->
                map.remove(1_000_002L)) + " ns");

        // 5. Получение среднего элемента по ключу
        System.out.println("Get middle: " + Time(() ->
                map.get(500_000L)) + " ns");

        // 6. Получение последнего элемента по ключу
        System.out.println("Get last: " + Time(() ->
                map.get(999_999L)) + " ns");
    }
}
