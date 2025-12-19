import java.util.*;

public class MainTask {


    public static class Student {
        Long id;
        String fullName;

        public Student(Long id, String fullName) {
            this.id = id;
            this.fullName = fullName;
        }
    }

    private static final int TOTAL_ELEMENTS = 1_000_000;

    public static void main(String[] args) {

        ArrayList<Student> arrayContainer = new ArrayList<>();
        LinkedList<Student> linkedContainer = new LinkedList<>();
        HashSet<Student> hashSetContainer = new HashSet<>();
        HashMap<Long, Student> hashMapContainer = new HashMap<>();

        for (long idx = 0; idx < TOTAL_ELEMENTS; idx++) {
            Student s = new Student(idx, "Student_" + idx);
            arrayContainer.add(s);
            linkedContainer.add(s);
            hashSetContainer.add(s);
            hashMapContainer.put(idx, s);
        }

        System.out.println("=== ArrayList ===");
        measureListPerformance(arrayContainer);

        System.out.println("\n=== LinkedList ===");
        measureListPerformance(linkedContainer);

        System.out.println("\n=== HashSet ===");
        measureSetPerformance(hashSetContainer);

        System.out.println("\n=== HashMap ===");
        measureMapPerformance(hashMapContainer);
    }

    private static long executionTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }

    private static void measureListPerformance(List<Student> list) {
        System.out.println("Добавление в конец:   " + executionTime(() -> list.add(new Student(1000001L, "New"))));
        System.out.println("Добавление в начало:  " + executionTime(() -> list.add(0, new Student(1000002L, "New"))));
        System.out.println("Удаление последнего:  " + executionTime(() -> list.remove(list.size() - 1)));
        System.out.println("Удаление первого:     " + executionTime(() -> list.remove(0)));
        System.out.println("Получение среднего:   " + executionTime(() -> list.get(TOTAL_ELEMENTS / 2)));
        System.out.println("Получение последнего: " + executionTime(() -> list.get(TOTAL_ELEMENTS - 1)));
    }

    private static void measureSetPerformance(Set<Student> set) {
        Student s1 = new Student(1000001L, "A");
        Student s2 = new Student(1000002L, "B");

        System.out.println("Добавление элемента:  " + executionTime(() -> set.add(s1)));
        System.out.println("Добавление второго:   " + executionTime(() -> set.add(s2)));
        System.out.println("Удаление элемента:    " + executionTime(() -> set.remove(s1)));
        System.out.println("Удаление второго:     " + executionTime(() -> set.remove(s2)));
        System.out.println("Проверка среднего:    " + executionTime(() -> set.contains(s1)));
        System.out.println("Проверка последнего:  " + executionTime(() -> set.contains(s2)));
    }

    private static void measureMapPerformance(Map<Long, Student> map) {
        System.out.println("Добавление записи:          " + executionTime(() -> map.put(1000001L, new Student(1000001L, "Z"))));
        System.out.println("Добавление второй записи:   " + executionTime(() -> map.put(1000002L, new Student(1000002L, "Q"))));
        System.out.println("Удаление записи:            " + executionTime(() -> map.remove(1000001L)));
        System.out.println("Удаление второй записи:     " + executionTime(() -> map.remove(1000002L)));
        System.out.println("Получение среднего элемента:" + executionTime(() -> map.get(500_000L)));
        System.out.println("Получение последнего элемента:" + executionTime(() -> map.get(999_999L)));
    }
}