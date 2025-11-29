import java.util.*;
public class CollectionPerformanceTest {

    public static void main(String[] args) {
        int size = 10000000; // 10 000 000 объектов

        // 1. Создаем ArrayList
        ArrayList<Student> arrayList = new ArrayList<>();

        // 2. Создаем LinkedList
        LinkedList<Student> linkedList = new LinkedList<>();

        // 3. Создаем Set (HashSet)
        HashSet<Student> hashSet = new HashSet<>();

        // 4. Создаем HashMap
        HashMap<Long, Student> hashMap = new HashMap<>();

        // Добавление 10 000 000 объектов в каждую структуру
        for (long i = 0; i < size; i++) {
            Student student = new Student(i, "Student" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }

        // Тестирование ArrayList
        System.out.println("ArrayList results:");
        testArrayList(arrayList, size);

        // Тестирование LinkedList
        System.out.println("\nLinkedList results:");
        testLinkedList(linkedList, size);

        // Тестирование HashSet
        System.out.println("\nHashSet results:");
        testHashSet(hashSet, size);

        // Тестирование HashMap
        System.out.println("\nHashMap results:");
        testHashMap(hashMap, size);
    }

    public static void testArrayList(ArrayList<Student> list, int size) {
        // 1. Добавление в конец
        long start = System.nanoTime();
        list.add(new Student(10000001L, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("Add to end: " + (end - start) + " ns");

        // 2. Добавление в начало
        start = System.nanoTime();
        list.add(0, new Student(10000002L, "NewStudent2"));
        end = System.nanoTime();
        System.out.println("Add to start: " + (end - start) + " ns");

        // 3. Удаление последнего элемента
        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Remove last: " + (end - start) + " ns");

        // 4. Удаление первого элемента
        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("Remove first: " + (end - start) + " ns");

        // 5. Get центрального элемента (id = 5 000 000)
        start = System.nanoTime();
        Student middle = list.get(5000000);
        end = System.nanoTime();
        System.out.println("Get middle: " + (end - start) + " ns");

        // 6. Get последнего элемента (id = 9 999 999)
        start = System.nanoTime();
        Student last = list.get(9999999);
        end = System.nanoTime();
        System.out.println("Get last: " + (end - start) + " ns");
    }

    public static void testLinkedList(LinkedList<Student> list, int size) {
        // 1. Добавление в конец
        long start = System.nanoTime();
        list.addLast(new Student(10000001L, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("Add to end: " + (end - start) + " ns");

        // 2. Добавление в начало
        start = System.nanoTime();
        list.addFirst(new Student(10000002L, "NewStudent2"));
        end = System.nanoTime();
        System.out.println("Add to start: " + (end - start) + " ns");

        // 3. Удаление последнего элемента
        start = System.nanoTime();
        list.removeLast();
        end = System.nanoTime();
        System.out.println("Remove last: " + (end - start) + " ns");

        // 4. Удаление первого элемента
        start = System.nanoTime();
        list.removeFirst();
        end = System.nanoTime();
        System.out.println("Remove first: " + (end - start) + " ns");

        // 5. Get центрального элемента (id = 5 000 000)
        start = System.nanoTime();
        Student middle = list.get(5000000);
        end = System.nanoTime();
        System.out.println("Get middle: " + (end - start) + " ns");

        // 6. Get последнего элемента (id = 9 999 999)
        start = System.nanoTime();
        Student last = list.get(9999999);
        end = System.nanoTime();
        System.out.println("Get last: " + (end - start) + " ns");
    }

    public static void testHashSet(HashSet<Student> set, int size) {
        // 1. Добавление элемента
        long start = System.nanoTime();
        set.add(new Student(10000001L, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("Add: " + (end - start) + " ns");

        // 2. Удаление последнего элемента
        start = System.nanoTime();
        set.remove(new Student(9999999L, "Student9999999"));
        end = System.nanoTime();
        System.out.println("Remove last: " + (end - start) + " ns");

        // 3. Удаление первого элемента
        start = System.nanoTime();
        set.remove(new Student(0L, "Student0"));
        end = System.nanoTime();
        System.out.println("Remove first: " + (end - start) + " ns");

        // 4. Contains центрального элемента (id = 5 000 000)
        start = System.nanoTime();
        boolean containsMiddle = set.contains(new Student(5000000L, "Student5000000"));
        end = System.nanoTime();
        System.out.println("Contains middle: " + (end - start) + " ns");

        // 5. Contains последнего элемента (id = 9 999 999)
        start = System.nanoTime();
        boolean containsLast = set.contains(new Student(9999999L, "Student9999999"));
        end = System.nanoTime();
        System.out.println("Contains last: " + (end - start) + " ns");
    }

    public static void testHashMap(HashMap<Long, Student> map, int size) {
        // 1. Добавление элемента
        long start = System.nanoTime();
        map.put(10000001L, new Student(10000001L, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("Put: " + (end - start) + " ns");

        // 2. Добавление другого элемента
        start = System.nanoTime();
        map.put(10000002L, new Student(10000002L, "NewStudent2"));
        end = System.nanoTime();
        System.out.println("Put another: " + (end - start) + " ns");

        // 3. Удаление последнего элемента
        start = System.nanoTime();
        map.remove(9999999L);
        end = System.nanoTime();
        System.out.println("Remove last: " + (end - start) + " ns");

        // 4. Удаление первого элемента
        start = System.nanoTime();
        map.remove(0L);
        end = System.nanoTime();
        System.out.println("Remove first: " + (end - start) + " ns");

        // 5. Get центрального элемента (id = 5 000 000)
        start = System.nanoTime();
        Student middle = map.get(5000000L);
        end = System.nanoTime();
        System.out.println("Get middle: " + (end - start) + " ns");

        // 6. Get последнего элемента (id = 9 999 999)
        start = System.nanoTime();
        Student last = map.get(9999999L);
        end = System.nanoTime();
        System.out.println("Get last: " + (end - start) + " ns");
    }
}