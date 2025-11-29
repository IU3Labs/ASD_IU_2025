package lab2.src;

import lab2.models.Student;
import java.util.*;

public class Task0 {
    private static final int SIZE = 10_000_000;

    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList<>(SIZE);
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>(SIZE);
        HashMap<Long, Student> hashMap = new HashMap<>(SIZE);

        System.out.println("Заполнение структур " + SIZE + " элементами...");

        long fillStart = System.nanoTime();
        for (long i = 0; i < SIZE; i++) {
            Student student = new Student(i, "Student" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }
        long fillTime = System.nanoTime() - fillStart;
        System.out.println("Заполнение завершено за: " + fillTime + " нс\n");

        testArrayList(arrayList);
        testLinkedList(linkedList);
        testHashSet(hashSet);
        testHashMap(hashMap);

        System.out.println("\nДоп. задание");
        demonstrateArrayListOperations();
        demonstrateLinkedListOperations();
    }

    private static void testArrayList(ArrayList<Student> list) {
        System.out.println("Array list");

        long start = System.nanoTime();
        list.add(new Student((long)SIZE, "NewEnd"));
        long end = System.nanoTime();
        System.out.println("1. Добавление в конец: " + (end - start) + " нс");

        start = System.nanoTime();
        list.add(0, new Student(-1L, "NewStart"));
        end = System.nanoTime();
        System.out.println("2. Добавление в начало: " + (end - start) + " нс");

        start = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("3. Удаление последнего: " + (end - start) + " нс");

        start = System.nanoTime();
        Student removedFirst = list.remove(0);
        end = System.nanoTime();
        System.out.println("4. Удаление первого: " + (end - start) + " нс");

        start = System.nanoTime();
        Student middle = list.get(list.size() / 2);
        end = System.nanoTime();
        System.out.println("5. Получение центрального: " + (end - start) + " нс");

        start = System.nanoTime();
        Student last = list.get(list.size() - 1);
        end = System.nanoTime();
        System.out.println("6. Получение последнего: " + (end - start) + " нс");
    }

    private static void testLinkedList(LinkedList<Student> list) {
        System.out.println("\nLinked list");

        long start = System.nanoTime();
        list.addLast(new Student((long)SIZE, "NewEnd"));
        long end = System.nanoTime();
        System.out.println("1. Добавление в конец: " + (end - start) + " нс");

        start = System.nanoTime();
        list.addFirst(new Student(-1L, "NewStart"));
        end = System.nanoTime();
        System.out.println("2. Добавление в начало: " + (end - start) + " нс");

        start = System.nanoTime();
        Student removedLast = list.removeLast();
        end = System.nanoTime();
        System.out.println("3. Удаление последнего: " + (end - start) + " нс");

        start = System.nanoTime();
        Student removedFirst = list.removeFirst();
        end = System.nanoTime();
        System.out.println("4. Удаление первого: " + (end - start) + " нс");

        start = System.nanoTime();
        Student middle = list.get(list.size() / 2);
        end = System.nanoTime();
        System.out.println("5. Получение центрального: " + (end - start) + " нс");

        start = System.nanoTime();
        Student last = list.getLast();
        end = System.nanoTime();
        System.out.println("6. Получение последнего: " + (end - start) + " нс");
    }

    private static void testHashSet(HashSet<Student> set) {
        System.out.println("\nHashSet");

        Student newStudent = new Student((long)SIZE, "NewStudent");
        Student firstStudent = new Student(0L, "Student0");
        Student middleStudent = new Student((long)SIZE/2, "MiddleStudent");
        Student lastStudent = new Student((long)SIZE-1, "LastStudent");

        long start = System.nanoTime();
        set.add(newStudent);
        long end = System.nanoTime();
        System.out.println("1. Добавление элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        set.add(firstStudent);
        end = System.nanoTime();
        System.out.println("2. Добавление существующего: " + (end - start) + " нс");

        start = System.nanoTime();
        set.remove(lastStudent);
        end = System.nanoTime();
        System.out.println("3. Удаление элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        set.remove(new Student(999999L, "NonExistent"));
        end = System.nanoTime();
        System.out.println("4. Удаление несуществующего: " + (end - start) + " нс");

        start = System.nanoTime();
        boolean foundMiddle = set.contains(middleStudent);
        end = System.nanoTime();
        System.out.println("5. Поиск центрального: " + (end - start) + " нс");

        start = System.nanoTime();
        boolean foundNonExistent = set.contains(new Student(999999L, "NonExistent"));
        end = System.nanoTime();
        System.out.println("6. Поиск несуществующего: " + (end - start) + " нс");
    }

    private static void testHashMap(HashMap<Long, Student> map) {
        System.out.println("\nHashMap");

        long start = System.nanoTime();
        map.put((long)SIZE, new Student((long)SIZE, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("1. Добавление элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        map.put(0L, new Student(0L, "UpdatedStudent"));
        end = System.nanoTime();
        System.out.println("2. Обновление существующего: " + (end - start) + " нс");

        start = System.nanoTime();
        Student removed = map.remove((long)SIZE-1);
        end = System.nanoTime();
        System.out.println("3. Удаление элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        Student removedNonExistent = map.remove(999999L);
        end = System.nanoTime();
        System.out.println("4. Удаление несуществующего: " + (end - start) + " нс");

        start = System.nanoTime();
        Student middle = map.get((long)SIZE/2);
        end = System.nanoTime();
        System.out.println("5. Получение центрального: " + (end - start) + " нс");

        start = System.nanoTime();
        Student nonExistent = map.get(999999L);
        end = System.nanoTime();
        System.out.println("6. Получение несуществующего: " + (end - start) + " нс");
    }

    private static void demonstrateArrayListOperations() {
        System.out.println("\n----- ДЕМОНСТРАЦИЯ ARRAYLIST -----");
        ArrayList<Student> list = new ArrayList<>();

        System.out.println("1. Добавление элементов:");
        list.add(new Student(1L, "First")); // В конец
        list.add(0, new Student(0L, "New First")); // В начало
        list.add(list.size()/2, new Student(5L, "Middle")); // В середину

        System.out.println("   Размер списка: " + list.size());

        System.out.println("2. Прямой порядок:");
        for (Student student : list) {
            System.out.println("   " + student);
        }

        System.out.println("3. Обратный порядок:");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println("   " + list.get(i));
        }

        System.out.println("4. Удаление элементов:");
        list.remove(0); // Удаление первого
        list.remove(list.size() - 1); // Удаление последнего
        System.out.println("   Размер после удаления: " + list.size());
    }

    private static void demonstrateLinkedListOperations() {
        System.out.println("\n----- ДЕМОНСТРАЦИЯ LINKEDLIST -----");
        LinkedList<Student> list = new LinkedList<>();

        System.out.println("1. Добавление элементов:");
        list.addLast(new Student(2L, "Last"));
        list.addFirst(new Student(0L, "First"));
        list.add(1, new Student(1L, "Middle"));

        System.out.println("   Размер списка: " + list.size());

        System.out.println("2. Прямой порядок:");
        for (Student student : list) {
            System.out.println("   " + student);
        }

        System.out.println("3. Обратный порядок:");
        Iterator<Student> descIterator = list.descendingIterator();
        while (descIterator.hasNext()) {
            System.out.println("   " + descIterator.next());
        }

        System.out.println("4. Удаление элементов:");
        list.removeFirst();
        list.removeLast();
        System.out.println("   Размер после удаления: " + list.size());
    }
}