import java.util.*;

public class MainTask {
    static final int COUNT = 10_000_000;

    static class Student {
        Long id;
        String name;

        public Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(id, student.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        System.out.println("Генерация данных (" + COUNT + " элементов)...");

        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap<>();

        for (long i = 0; i < COUNT; i++) {
            Student s = new Student(i, "Student" + i);
            arrayList.add(s);
            linkedList.add(s);
            hashSet.add(s);
            hashMap.put(i, s);
        }

        System.out.println("Заполнение завершено. Начало тестов (время в наносекундах).");
        System.out.println("-------------------------------------------------------------");

        Student newStudent = new Student(COUNT + 1L, "New");

        measure("ArrayList Add End", () -> arrayList.add(newStudent));
        measure("LinkedList Add End", () -> linkedList.add(newStudent));
        measure("HashSet Add", () -> hashSet.add(newStudent));
        measure("HashMap Add", () -> hashMap.put(newStudent.id, newStudent));

        Student startStudent = new Student(COUNT + 2L, "Start");
        measure("ArrayList Add Start", () -> arrayList.add(0, startStudent));
        measure("LinkedList Add Start", () -> linkedList.addFirst(startStudent));

        measure("ArrayList Remove Last", () -> arrayList.remove(arrayList.size() - 1));
        measure("LinkedList Remove Last", () -> linkedList.removeLast());
        measure("HashSet Remove", () -> hashSet.remove(newStudent));
        measure("HashMap Remove", () -> hashMap.remove(newStudent.id));

        measure("ArrayList Remove First", () -> arrayList.remove(0));
        measure("LinkedList Remove First", () -> linkedList.removeFirst());

        int midIndex = COUNT / 2;
        long midId = (long) midIndex;
        measure("ArrayList Get Mid ", () -> arrayList.get(midIndex));
        measure("LinkedList Get Mid", () -> linkedList.get(midIndex)); // Будет очень медленно
        measure("HashMap Get Mid", () -> hashMap.get(midId));

        measure("ArrayList Get Last", () -> arrayList.get(arrayList.size() - 1));
        measure("LinkedList Get Last", () -> linkedList.getLast());
        measure("HashMap Get Last", () -> hashMap.get((long)COUNT - 1));
    }

    static void measure(String operation, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        System.out.printf("%-25s: %d ns%n", operation, (end - start));
    }
}
