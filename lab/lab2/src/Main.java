

import java.util.*;

class Student {
    private Long id;
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    // equals / hashCode по id, чтобы корректно работать в HashSet
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class Main {

    public static int readSize() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите количество элементов (рекомендуется 10000000): ");
        return scan.nextInt();
    }

    public static void fillList(List<Student> list, int size) {
        for (int i = 1; i <= size; i++) {
            list.add(new Student((long) i, "Student " + i));
        }
    }

    public static void fillSet(Set<Student> set, int size) {
        for (int i = 1; i <= size; i++) {
            set.add(new Student((long) i, "Student " + i));
        }
    }

    public static void fillMap(Map<Long, Student> map, int size) {
        for (int i = 1; i <= size; i++) {
            Student s = new Student((long) i, "Student " + i);
            map.put(s.getId(), s);
        }
    }

    public static void testArrayList(int size) {
        System.out.println("\nArrayList<Student>");
        List<Student> list = new ArrayList<>();
        fillList(list, size);

        // Get центрального
        int middleIndex = size / 2;
        long start = System.nanoTime();
        Student middle = list.get(middleIndex);
        long timeGetMiddle = System.nanoTime() - start;

        // Get последнего
        start = System.nanoTime();
        Student last = list.get(size - 1);
        long timeGetLast = System.nanoTime() - start;

        // Добавление в конец
        Student endStudent = new Student((long) (size + 1), "Student " + (size + 1));
        start = System.nanoTime();
        list.add(endStudent);
        long timeAddEnd = System.nanoTime() - start;

        // Добавление в начало
        Student beginStudent = new Student((long) (size + 2), "Student " + (size + 2));
        start = System.nanoTime();
        list.add(0, beginStudent);
        long timeAddBegin = System.nanoTime() - start;

        // Удаление последнего
        start = System.nanoTime();
        list.remove(list.size() - 1);
        long timeRemoveLast = System.nanoTime() - start;

        // Удаление первого
        start = System.nanoTime();
        list.remove(0);
        long timeRemoveFirst = System.nanoTime() - start;

        System.out.println("Добавление в конец: " + timeAddEnd + " нс");
        System.out.println("Добавление в начало: " + timeAddBegin + " нс");
        System.out.println("Удаление последнего: " + timeRemoveLast + " нс");
        System.out.println("Удаление первого: " + timeRemoveFirst + " нс");
        System.out.println("Get центрального элемента: " + timeGetMiddle + " нс");
        System.out.println("Get последнего элемента: " + timeGetLast + " нс");
    }

    public static void testLinkedList(int size) {
        System.out.println("\nLinkedList<Student>");
        LinkedList<Student> list = new LinkedList<>();
        fillList(list, size);

        int middleIndex = size / 2;

        long start = System.nanoTime();
        Student middle = list.get(middleIndex);
        long timeGetMiddle = System.nanoTime() - start;

        start = System.nanoTime();
        Student last = list.getLast();
        long timeGetLast = System.nanoTime() - start;

        Student endStudent = new Student((long) (size + 1), "Student " + (size + 1));
        start = System.nanoTime();
        list.addLast(endStudent);
        long timeAddEnd = System.nanoTime() - start;

        Student beginStudent = new Student((long) (size + 2), "Student " + (size + 2));
        start = System.nanoTime();
        list.addFirst(beginStudent);
        long timeAddBegin = System.nanoTime() - start;

        start = System.nanoTime();
        list.removeLast();
        long timeRemoveLast = System.nanoTime() - start;

        start = System.nanoTime();
        list.removeFirst();
        long timeRemoveFirst = System.nanoTime() - start;

        System.out.println("Добавление в конец: " + timeAddEnd + " нс");
        System.out.println("Добавление в начало: " + timeAddBegin + " нс");
        System.out.println("Удаление последнего: " + timeRemoveLast + " нс");
        System.out.println("Удаление первого: " + timeRemoveFirst + " нс");
        System.out.println("Get центрального элемента: " + timeGetMiddle + " нс");
        System.out.println("Get последнего элемента: " + timeGetLast + " нс");
    }

    public static void testHashSet(int size) {
        System.out.println("\n HashSet<Student>");
        Set<Student> set = new HashSet<>();
        fillSet(set, size);

        Student middle = new Student(5_000_000L, "Student 5000000");
        Student last = new Student(9_999_999L, "Student 9999999");

        long start = System.nanoTime();
        boolean existsMiddle = set.contains(middle);
        long timeGetMiddle = System.nanoTime() - start;

        start = System.nanoTime();
        boolean existsLast = set.contains(last);
        long timeGetLast = System.nanoTime() - start;

        Student endStudent = new Student(10_000_001L, "Student 10000001");
        start = System.nanoTime();
        set.add(endStudent);
        long timeAddEnd = System.nanoTime() - start;

        Student beginStudent = new Student(10_000_002L, "Student 10000002");
        start = System.nanoTime();
        set.add(beginStudent); // "начало" для множества условно
        long timeAddBegin = System.nanoTime() - start;

        start = System.nanoTime();
        set.remove(endStudent);
        long timeRemoveLast = System.nanoTime() - start;

        start = System.nanoTime();
        set.remove(beginStudent);
        long timeRemoveFirst = System.nanoTime() - start;

        System.out.println("Добавление в конец (условно): " + timeAddEnd + " нс");
        System.out.println("Добавление в начало (условно): " + timeAddBegin + " нс");
        System.out.println("Удаление последнего (условно): " + timeRemoveLast + " нс");
        System.out.println("Удаление первого (условно): " + timeRemoveFirst + " нс");
        System.out.println("Поиск центрального (contains): " + timeGetMiddle + " нс, найдено: " + existsMiddle);
        System.out.println("Поиск последнего (contains): " + timeGetLast + " нс, найдено: " + existsLast);
    }

    public static void testHashMap(int size) {
        System.out.println("\n HashMap<Long, Student>");
        Map<Long, Student> map = new HashMap<>();
        fillMap(map, size);

        long start = System.nanoTime();
        Student middle = map.get(5_000_000L);
        long timeGetMiddle = System.nanoTime() - start;

        start = System.nanoTime();
        Student last = map.get(9_999_999L);
        long timeGetLast = System.nanoTime() - start;

        Student endStudent = new Student(10_000_001L, "Student 10000001");
        start = System.nanoTime();
        map.put(endStudent.getId(), endStudent);
        long timeAddEnd = System.nanoTime() - start;

        Student beginStudent = new Student(10_000_002L, "Student 10000002");
        start = System.nanoTime();
        map.put(beginStudent.getId(), beginStudent);
        long timeAddBegin = System.nanoTime() - start;

        start = System.nanoTime();
        map.remove(endStudent.getId());
        long timeRemoveLast = System.nanoTime() - start;

        start = System.nanoTime();
        map.remove(beginStudent.getId());
        long timeRemoveFirst = System.nanoTime() - start;

        System.out.println("Добавление в \"конец\" (put): " + timeAddEnd + " нс");
        System.out.println("Добавление в \"начало\" (put): " + timeAddBegin + " нс");
        System.out.println("Удаление последнего (remove): " + timeRemoveLast + " нс");
        System.out.println("Удаление первого (remove): " + timeRemoveFirst + " нс");
        System.out.println("Get центрального по id=5000000: " + timeGetMiddle + " нс, " + (middle != null));
        System.out.println("Get последнего по id=9999999: " + timeGetLast + " нс, " + (last != null));
    }

    public static void main(String[] args) {
        int size = readSize();

        testArrayList(size);
        testLinkedList(size);
        testHashSet(size);
        testHashMap(size);
    }
}
/*
ArrayList<Student>
Добавление в конец: 2000 нс
Добавление в начало: 106110300 нс
Удаление последнего: 45700 нс
Удаление первого: 78923900 нс
Get центрального элемента: 27000 нс
Get последнего элемента: 700 нс

LinkedList<Student>
Добавление в конец: 10200 нс
Добавление в начало: 9100 нс
Удаление последнего: 4800 нс
Удаление первого: 3400 нс
Get центрального элемента: 269186400 нс
Get последнего элемента: 16900 нс

 HashSet<Student>
Добавление в конец (условно): 3000 нс
Добавление в начало (условно): 200 нс
Удаление последнего (условно): 9700 нс
Удаление первого (условно): 900 нс
Поиск центрального (contains): 117200 нс, найдено: true
Поиск последнего (contains): 2100 нс, найдено: true

 HashMap<Long, Student>
Добавление в "конец" (put): 2400 нс
Добавление в "начало" (put): 100 нс
Удаление последнего (remove): 9800 нс
Удаление первого (remove): 700 нс
Get центрального по id=5000000: 38100 нс, true
Get последнего по id=9999999: 700 нс, true
 */