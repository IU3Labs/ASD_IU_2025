//Основное задание. Задание оценивается в 0 баллов.
//Создать класс Student с полями:
//1. Long id
//2. String name
//В методе main:
//1. Создать ArrayList, который хранит объекты класса Student
//(ArrayList<Student>).
//2. Создать LinkedList, который хранит объекты класса Student
//(LinkedList <Student>).
//3. Создать Set, который хранит объекты класса Student (HashSet
//<Student>).
//4. Создать HashMap, который хранит объекты класса Student (HashMap
//<Long, Student>).
//В каждую структуру данных добавить 10 000 000 объектов.
//После этого для каждой структуры данных измерить время в нс:
//1. Добавление 1 несуществующего элемента в конец (id = 10 000 001).
//2. Добавление 1 несуществующего элемента в начало.
//3. Удаление последнего элемента
//4. Удаление первого элемента
//5. Взятие (Get) центрального элемента (id = 5 000 000)
//6. Взятие (Get) последнего элемента (id = 9 999 999).
//Помимо кода решение должно содержать цифры, полученные при
//тестах. При невозможности работать с 10 000 000 записей позволительно
//несколько сократить количество объектов.
import java.util.*;

public class Main {
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

    private static final int ELEMENTS_COUNT = 1_000_000;

    public static void main(String[] args) {
        System.out.println("Тестирование производительности коллекций");
        System.out.println("Количество элементов: " + ELEMENTS_COUNT);
        System.out.println();

        List<Student> arrayList = new ArrayList<>();
        List<Student> linkedList = new LinkedList<>();
        Set<Student> hashSet = new HashSet<>();
        Map<Long, Student> hashMap = new HashMap<>();

        for (long i = 0; i < ELEMENTS_COUNT; i++) {
            Student s = new Student(i, "Student" + i);
            arrayList.add(s);
            linkedList.add(s);
            hashSet.add(s);
            hashMap.put(s.id, s);
        }

        System.out.println("    Тестирование ArrayList    ");
        testArrayList(arrayList);

        System.out.println("\n    Тестирование LinkedList    ");
        testLinkedList(linkedList);

        System.out.println("\n    Тестирование HashSet    ");
        testHashSet(hashSet);

        System.out.println("\n    Тестирование HashMap    ");
        testHashMap(hashMap);
    }

    private static void testArrayList(List<Student> list) {
        long start, end;

        // 1. Добавление несуществующего элемента в конец (id = 1 000 001)
        Student newStudentEnd = new Student((long) ELEMENTS_COUNT + 1, "NewStudentEnd");
        start = System.nanoTime();
        list.add(newStudentEnd);
        end = System.nanoTime();
        System.out.println("1. Добавление в конец: " + (end - start) + " нс");

        // 2. Добавление несуществующего элемента в начало
        Student newStudentStart = new Student((long) ELEMENTS_COUNT + 2, "NewStudentStart");
        start = System.nanoTime();
        list.add(0, newStudentStart);
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
        System.out.println("4. Удаление первого:   " + (end - start) + " нс");

        // 5. Взятие центрального элемента (id = 500 000)
        int middleIndex = list.size() / 2;
        start = System.nanoTime();
        Student middleStudent = list.get(middleIndex);
        end = System.nanoTime();
        System.out.println("5. Get центрального:   " + (end - start) + " нс");

        // 6. Взятие последнего элемента (id = 999 999)
        int lastIndex = list.size() - 1;
        start = System.nanoTime();
        Student lastStudent = list.get(lastIndex);
        end = System.nanoTime();
        System.out.println("6. Get последнего:     " + (end - start) + " нс");
    }

    private static void testLinkedList(List<Student> list) {
        long start, end;

        // 1. Добавление несуществующего элемента в конец (id = 1 000 001)
        Student newStudentEnd = new Student((long) ELEMENTS_COUNT + 1, "NewStudentEnd");
        start = System.nanoTime();
        list.add(newStudentEnd);
        end = System.nanoTime();
        System.out.println("1. Добавление в конец: " + (end - start) + " нс");

        // 2. Добавление несуществующего элемента в начало
        Student newStudentStart = new Student((long) ELEMENTS_COUNT + 2, "NewStudentStart");
        start = System.nanoTime();
        list.add(0, newStudentStart);
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
        System.out.println("4. Удаление первого:   " + (end - start) + " нс");

        // 5. Взятие центрального элемента (id = 500 000)
        int middleIndex = list.size() / 2;
        start = System.nanoTime();
        Student middleStudent = list.get(middleIndex);
        end = System.nanoTime();
        System.out.println("5. Get центрального:   " + (end - start) + " нс");

        // 6. Взятие последнего элемента (id = 999 999)
        int lastIndex = list.size() - 1;
        start = System.nanoTime();
        Student lastStudent = list.get(lastIndex);
        end = System.nanoTime();
        System.out.println("6. Get последнего:     " + (end - start) + " нс");
    }

    private static void testHashSet(Set<Student> set) {
        long start, end;

        Student newStudent = new Student((long) ELEMENTS_COUNT + 1, "NewStudent");
        Student firstStudent = new Student(0L, "FirstStudent");
        Student middleStudent = new Student((long) ELEMENTS_COUNT / 2, "MiddleStudent");
        Student lastStudent = new Student((long) ELEMENTS_COUNT - 1, "LastStudent");

        // 1. Добавление несуществующего элемента в конец
        start = System.nanoTime();
        set.add(newStudent);
        end = System.nanoTime();
        System.out.println("1. Добавление элемента: " + (end - start) + " нс");

        // 2. Добавление несуществующего элемента в начало (для Set нет понятия начала)
        // Вместо этого добавление другого нового элемента
        Student anotherNewStudent = new Student((long) ELEMENTS_COUNT + 3, "AnotherNewStudent");
        start = System.nanoTime();
        set.add(anotherNewStudent);
        end = System.nanoTime();
        System.out.println("2. Добавление нового:   " + (end - start) + " нс");

        // 3. Удаление последнего элемента
        start = System.nanoTime();
        set.remove(lastStudent);
        end = System.nanoTime();
        System.out.println("3. Удаление последнего: " + (end - start) + " нс");

        // 4. Удаление первого элемента
        start = System.nanoTime();
        set.remove(firstStudent);
        end = System.nanoTime();
        System.out.println("4. Удаление первого:    " + (end - start) + " нс");

        // 5. Взятие центрального элемента (поиск)
        start = System.nanoTime();
        boolean containsMiddle = set.contains(middleStudent);
        end = System.nanoTime();
        System.out.println("5. Поиск центрального:  " + (end - start) + " нс");

        // 6. Взятие последнего элемента (поиск элемента с id = 999 998, т.к. 999 999 удалили)
        Student newLastStudent = new Student((long) ELEMENTS_COUNT - 2, "NewLastStudent");
        start = System.nanoTime();
        boolean containsLast = set.contains(newLastStudent);
        end = System.nanoTime();
        System.out.println("6. Поиск предпоследнего:" + (end - start) + " нс");
    }

    private static void testHashMap(Map<Long, Student> map) {
        long start, end;

        Long newKey = (long) ELEMENTS_COUNT + 1;
        Long firstKey = 0L;
        Long middleKey = (long) ELEMENTS_COUNT / 2;
        Long lastKey = (long) ELEMENTS_COUNT - 1;
        Long anotherNewKey = (long) ELEMENTS_COUNT + 3;

        // 1. Добавление несуществующего элемента в конец
        Student newStudent = new Student(newKey, "NewStudent");
        start = System.nanoTime();
        map.put(newKey, newStudent);
        end = System.nanoTime();
        System.out.println("1. Добавление элемента: " + (end - start) + " нс");

        // 2. Добавление несуществующего элемента в начало (для Map нет понятия начала)
        Student anotherNewStudent = new Student(anotherNewKey, "AnotherNewStudent");
        start = System.nanoTime();
        map.put(anotherNewKey, anotherNewStudent);
        end = System.nanoTime();
        System.out.println("2. Добавление нового:   " + (end - start) + " нс");

        // 3. Удаление последнего элемента
        start = System.nanoTime();
        map.remove(lastKey);
        end = System.nanoTime();
        System.out.println("3. Удаление последнего: " + (end - start) + " нс");

        // 4. Удаление первого элемента
        start = System.nanoTime();
        map.remove(firstKey);
        end = System.nanoTime();
        System.out.println("4. Удаление первого:    " + (end - start) + " нс");

        // 5. Взятие центрального элемента
        start = System.nanoTime();
        Student middleStudent = map.get(middleKey);
        end = System.nanoTime();
        System.out.println("5. Get центрального:    " + (end - start) + " нс");

        // 6. Взятие последнего элемента (элемента с id = 999 998, т.к. 999 999 удалили)
        Long newLastKey = (long) ELEMENTS_COUNT - 2;
        start = System.nanoTime();
        Student lastStudent = map.get(newLastKey);
        end = System.nanoTime();
        System.out.println("6. Get предпоследнего:  " + (end - start) + " нс");
    }
}