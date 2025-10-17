//Создать класс Student с полями:
//1 Long id
//2 String name
//В методе main:
//1 Создать ArrayList, который хранит объекты класса Student
//(ArrayList<Student>).
//2 Создать LinkedList, который хранит объекты класса Student
//(LinkedList <Student>).
//3 Создать Set, который хранит объекты класса Student (HashSet
//<Student>).
//4 Создать HashMap, который хранит объекты класса Student (HashMap
//<Long, Student>).
//В каждую структуру данных добавить 10 000 000 объектов.
//После этого для каждой структуры данных измерить время в нс:
//1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
//2 Добавление 1 несуществующего элемента в начало.
//3 Удаление последнего элемента
//4 Удаление первого элемента
//5 Взятие (Get) центрального элемента (id = 5 000 000)
//6 Взятие (Get) последнего элемента (id = 9 999 999).
//Помимо кода решение должно содержать цифры, полученные при
//тестах. При невозможности работать с 10 000 000 записей позволительно
//несколько сократить количество объектов.

package lab2;

import java.util.*;

public class Student {
    private Long id;
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько студентов создать?: ");
        int size = scanner.nextInt();

        System.out.println("Создаем " + size + " студентов...");

        List<Student> arrayList = new ArrayList<>();
        for (long i = 1; i <= size; i++) {
            arrayList.add(new Student(i, "Student" + i));
        }

        List<Student> linkedList = new LinkedList<>();
        for (long i = 1; i <= size; i++) {
            linkedList.add(new Student(i, "Student" + i));
        }

        Set<Student> hashSet = new HashSet<>();
        for (long i = 1; i <= size; i++) {
            hashSet.add(new Student(i, "Student" + i));
        }

        Map<Long, Student> hashMap = new HashMap<>();
        for (long i = 1; i <= size; i++) {
            hashMap.put(i, new Student(i, "Student" + i));
        }

        System.out.println("\n--- ArrayList ---");
        testListOperations(arrayList, size);

        System.out.println("\n--- LinkedList ---");
        testListOperations(linkedList, size);

        System.out.println("\n--- HashSet ---");
        testSetOperations(hashSet, size);

        System.out.println("\n--- HashMap ---");
        testMapOperations(hashMap, size);

        scanner.close();
    }

    // Тестирование операций для List
    private static void testListOperations(List<Student> list, int size) {
        // 1. Добавление в конец (id = size + 1)
        long start = System.nanoTime();
        list.add(new Student((long)size + 1, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("1. Добавление в конец: " + (end - start) + " нс");

        // 2. Добавление в начало (id = size + 2)
        start = System.nanoTime();
        list.add(0, new Student((long)size + 2, "NewStudent2"));
        end = System.nanoTime();
        System.out.println("2. Добавление в начало: " + (end - start) + " нс");

        // 3. Удаление последнего элемента
        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("3. Удаление последнего: " + (end - start) + " нс");

        // 4. Удаление первого элемента
        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("4. Удаление первого: " + (end - start) + " нс");

        // 5. Получение центрального элемента (id = size/2)
        start = System.nanoTime();
        Student middle = list.get(size / 2);
        end = System.nanoTime();
        System.out.println("5. Получение центрального: " + (end - start) + " нс");

        // 6. Получение последнего элемента (id = size)
        start = System.nanoTime();
        Student last = list.get(list.size() - 1);
        end = System.nanoTime();
        System.out.println("6. Получение последнего: " + (end - start) + " нс");
    }

    // Тестирование операций для Set
    private static void testSetOperations(Set<Student> set, int size) {
        // 1. Добавление элемента (id = size + 1)
        long start = System.nanoTime();
        set.add(new Student((long)size + 1, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("1. Добавление: " + (end - start) + " нс");

        // 2. Добавление другого элемента
        start = System.nanoTime();
        set.add(new Student((long)size + 2, "NewStudent2"));
        end = System.nanoTime();
        System.out.println("2. Добавление: " + (end - start) + " нс");

        // 3. Удаление элемента (id = size)
        start = System.nanoTime();
        set.remove(new Student((long)size, "Student" + size));
        end = System.nanoTime();
        System.out.println("3. Удаление: " + (end - start) + " нс");

        // 4. Удаление элемента (id = 1)
        start = System.nanoTime();
        set.remove(new Student(1L, "Student1"));
        end = System.nanoTime();
        System.out.println("4. Удаление: " + (end - start) + " нс");

        // 5. Поиск центрального элемента (id = size/2)
        start = System.nanoTime();
        boolean foundMiddle = set.contains(new Student((long)size/2, "Student" + size/2));
        end = System.nanoTime();
        System.out.println("5. Поиск центрального: " + (end - start) + " нс");

        // 6. Поиск элемента (id = size-1)
        start = System.nanoTime();
        boolean foundLast = set.contains(new Student((long)size-1, "Student" + (size-1)));
        end = System.nanoTime();
        System.out.println("6. Поиск элемента: " + (end - start) + " нс");
    }

    // Тестирование операций для Map
    private static void testMapOperations(Map<Long, Student> map, int size) {
        // 1. Добавление элемента (id = size + 1)
        long start = System.nanoTime();
        map.put((long)size + 1, new Student((long)size + 1, "NewStudent"));
        long end = System.nanoTime();
        System.out.println("1. Добавление: " + (end - start) + " нс");

        // 2. Добавление другого элемента
        start = System.nanoTime();
        map.put((long)size + 2, new Student((long)size + 2, "NewStudent2"));
        end = System.nanoTime();
        System.out.println("2. Добавление: " + (end - start) + " нс");

        // 3. Удаление элемента (id = size)
        start = System.nanoTime();
        map.remove((long)size);
        end = System.nanoTime();
        System.out.println("3. Удаление: " + (end - start) + " нс");

        // 4. Удаление элемента (id = 1)
        start = System.nanoTime();
        map.remove(1L);
        end = System.nanoTime();
        System.out.println("4. Удаление: " + (end - start) + " нс");

        // 5. Получение центрального элемента (id = size/2)
        start = System.nanoTime();
        Student middle = map.get((long)size/2);
        end = System.nanoTime();
        System.out.println("5. Получение центрального: " + (end - start) + " нс");

        // 6. Получение элемента (id = size-1)
        start = System.nanoTime();
        Student last = map.get((long)size-1);
        end = System.nanoTime();
        System.out.println("6. Получение элемента: " + (end - start) + " нс");
    }
}