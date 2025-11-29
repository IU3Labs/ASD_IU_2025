package lab2;
/*ArrayList
Добавление в конец: 2800 нс
Добавление в начало: 76585700 нс
Удаление последнего: 22200 нс
Удаление первого: 70008600 нс
Get центрального: 11600 нс
Get последнего: 2900 нс

 LinkedList
Добавление в конец: 3500 нс
Добавление в начало: 20500 нс
Удаление последнего: 8900 нс
Удаление первого: 2100 нс
Get центрального: 29407300 нс
Get последнего: 3500 нс

 HashSet
Добавление: 4700 нс
Удаление: 18200 нс
Поиск: 38000 нс

 HashMap
Добавление: 4600 нс
Удаление: 7400 нс
Get: 7300 нс
*/

import java.util.*;

public class Main {

    private static final int ELEMENT_COUNT = 10_000_000; // при нехватке памяти можно уменьшить
    private static final long NEW_ID = ELEMENT_COUNT + 1L;

    public static void main(String[] args) {
        List<Student> arrayList = new ArrayList<>();
        List<Student> linkedList = new LinkedList<>();
        Set<Student> hashSet = new HashSet<>();
        Map<Long, Student> hashMap = new HashMap<>();

        fillCollections(arrayList, linkedList, hashSet, hashMap);
        System.out.println("Все коллекции заполнены.");

        testListOperations("ArrayList", arrayList);
        testListOperations("LinkedList", linkedList);
        testSetOperations(hashSet);
        testMapOperations(hashMap);
    }

    private static void fillCollections(List<Student> arrayList,
                                        List<Student> linkedList,
                                        Set<Student> hashSet,
                                        Map<Long, Student> hashMap) {
        for (long i = 1; i <= ELEMENT_COUNT; i++) {
            Student student = new Student(i, "Student" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }
    }

    private static void testListOperations(String listName, List<Student> list) {
        System.out.println("\n " + listName);
        Student newStudent = new Student(NEW_ID, "NewStudent");

        long start;
        long end;

        // 1. Добавление в конец
        start = System.nanoTime();
        list.add(newStudent);
        end = System.nanoTime();
        System.out.println("Добавление в конец: " + (end - start) + " нс");

        // 2. Добавление в начало
        start = System.nanoTime();
        list.add(0, newStudent);
        end = System.nanoTime();
        System.out.println("Добавление в начало: " + (end - start) + " нс");

        // 3. Удаление последнего
        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Удаление последнего: " + (end - start) + " нс");

        // 4. Удаление первого
        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("Удаление первого: " + (end - start) + " нс");

        // 5. Получение центрального
        start = System.nanoTime();
        list.get(ELEMENT_COUNT / 2);
        end = System.nanoTime();
        System.out.println("Get центрального: " + (end - start) + " нс");

        // 6. Получение последнего
        start = System.nanoTime();
        list.get(ELEMENT_COUNT - 1);
        end = System.nanoTime();
        System.out.println("Get последнего: " + (end - start) + " нс");
    }

    private static void testSetOperations(Set<Student> set) {
        System.out.println("\n HashSet");
        Student newStudent = new Student(NEW_ID, "NewStudent");

        long start;
        long end;

        // Добавление
        start = System.nanoTime();
        set.add(newStudent);
        end = System.nanoTime();
        System.out.println("Добавление: " + (end - start) + " нс");

        // Удаление
        start = System.nanoTime();
        set.remove(newStudent);
        end = System.nanoTime();
        System.out.println("Удаление: " + (end - start) + " нс");

        // Поиск
        start = System.nanoTime();
        set.contains(new Student(ELEMENT_COUNT / 2L, "Student"));
        end = System.nanoTime();
        System.out.println("Поиск: " + (end - start) + " нс");
    }

    private static void testMapOperations(Map<Long, Student> map) {
        System.out.println("\n HashMap");
        Student newStudent = new Student(NEW_ID, "NewStudent");

        long start;
        long end;

        // Добавление
        start = System.nanoTime();
        map.put(newStudent.getId(), newStudent);
        end = System.nanoTime();
        System.out.println("Добавление: " + (end - start) + " нс");

        // Удаление
        start = System.nanoTime();
        map.remove(newStudent.getId());
        end = System.nanoTime();
        System.out.println("Удаление: " + (end - start) + " нс");

        // Поиск
        start = System.nanoTime();
        map.get(ELEMENT_COUNT / 2L);
        end = System.nanoTime();
        System.out.println("Get: " + (end - start) + " нс");
    }
}

class Student {

    private final Long id;
    private final String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
