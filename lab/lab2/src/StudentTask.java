//Создать класс Student с полями:
//        1 Long id
//        2 String name
//        В методе main:
//        1 Создать ArrayList, который хранит объекты класса Student
//        (ArrayList<Student>).
//        2 Создать LinkedList, который хранит объекты класса Student
//        (LinkedList <Student>).
//        3 Создать Set, который хранит объекты класса Student (HashSet
//        <Student>).
//        4 Создать HashMap, который хранит объекты класса Student (HashMap
//        <Long, Student>).
//        В каждую структуру данных добавить 10 000 000 объектов.
//        После этого для каждой структуры данных измерить время в нс:
//        1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
//        2 Добавление 1 несуществующего элемента в начало.
//        3 Удаление последнего элемента
//        4 Удаление первого элемента
//        5 Взятие (Get) центрального элемента (id = 5 000 000)
//        6 Взятие (Get) последнего элемента (id = 9 999 999).

import java.util.*;

public class StudentTask{
    public static class Student {
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(id, student.id);
        }
    }

    private static final int SIZE = 5_000_000;

    private static final Student newEndStudent = new Student((long)SIZE, "New Last");
    private static final Student startStudent = new Student((long) -1, "First");
    private static final Student centerStudent = new Student((long) SIZE / 2,"Center");
    private static final Student endStudent = new Student((long) SIZE - 1,"Center");

    private static void testArrayListOperations(List<Student> list, int size) {


        // 1. Добавление в конец
        long startTime = System.nanoTime();
        list.addLast(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime));

        // 3. Удаление последнего элемента
        startTime = System.nanoTime();
        list.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime));

        // 2. Добавление в начало
        startTime = System.nanoTime();
        list.addFirst(startStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime));

        // 4. Удаление первого элемента
        startTime = System.nanoTime();
        list.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime));

        // 5. Получение центрального элемента
        startTime = System.nanoTime();
        Student center = list.get(size / 2);
        endTime = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (endTime - startTime));
        System.out.println("Его ID: " + center.getId());

        // 6. Получение последнего элемента
        startTime = System.nanoTime();
        Student last = list.get(size - 1);
        endTime = System.nanoTime();
        System.out.println("Получение последнего элемента: " + (endTime - startTime));
        System.out.println("Его ID: " + last.getId());
    }

    private static void testLinkedListOperations(List<Student> list, int size) {
        // 1. Добавление в конец
        long startTime = System.nanoTime();
        list.add(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime));

        // 3. Удаление последнего элемента
        startTime = System.nanoTime();
        list.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime));

        // 2. Добавление в начало
        startTime = System.nanoTime();
        list.addFirst(startStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime));

        // 4. Удаление первого элемента
        startTime = System.nanoTime();
        list.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime));

        // 5. Получение центрального элемента
        startTime = System.nanoTime();
        Student center = list.get(size / 2);
        endTime = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (endTime - startTime));
        System.out.println("Его ID: " + center.getId());

        // 6. Получение последнего элемента
        startTime = System.nanoTime();
        Student last = list.get(size - 1);
        endTime = System.nanoTime();
        System.out.println("Получение последнего элемента: " + (endTime - startTime));
        System.out.println("Его ID: " + last.getId());
    }

    private static void testHashSetOperations(Set<Student> set, int size) {
        // 1, 2. Добавление элемента
        long startTime = System.nanoTime();
        set.add(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime));


        // 3, 4. Удаление элемента
        startTime = System.nanoTime();
        set.remove(newEndStudent);
        endTime = System.nanoTime();
        System.out.println("Удаление элемента: " + (endTime - startTime));

        // 5. Поиск центрального элемента
        startTime = System.nanoTime();
        boolean contains = set.contains(centerStudent);
        endTime = System.nanoTime();
        System.out.println("Наличие центрального элемента: " + (endTime - startTime));

        // 6. Поиск последнего элемента
        startTime = System.nanoTime();
        contains = set.contains(endStudent);
        endTime = System.nanoTime();
        System.out.println("Наличие последнего элемента: " + (endTime - startTime));
    }

    private static void testHashMapOperations(Map<Long, Student> map, int size) {

        // 1, 2. Добавление элемента
        long startTime = System.nanoTime();
        map.put(newEndStudent.getId(), newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime));

        // 3, 4. Удаление элемента элемента
        startTime = System.nanoTime();
        map.remove(newEndStudent.getId());
        endTime = System.nanoTime();
        System.out.println("Удаление элемента: " + (endTime - startTime));

        // 5. Получение центрального элемента
        startTime = System.nanoTime();
        Student centerFromMap = map.get(centerStudent.getId());
        endTime = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (endTime - startTime));

        // 6. Получение последнего элемента
        startTime = System.nanoTime();
        Student lastFromMap = map.get(endStudent.getId());
        endTime = System.nanoTime();
        System.out.println("Получение последнего элемента: " + (endTime - startTime));
    }

        public static void main(String[] args)  {

            ArrayList<Student> arrayList = new ArrayList<>();
            LinkedList<Student> linkedList = new LinkedList<>();
            HashSet<Student> hashSet = new HashSet<>();
            HashMap<Long, Student> hashMap = new HashMap<>();
            for (long i = 0; i < SIZE; i++) {
                arrayList.add(new Student(i, "Student_" + i));
                linkedList.add(new Student(i, "Student_" + i));
                hashSet.add(new Student(i, "Student_" + i));
                hashMap.put(i, new Student(i, "Student_" + i));
            }

            System.out.println("\nArrayList - ");
            testArrayListOperations(arrayList, SIZE);

            System.out.println("\nLinkedList -");
            testLinkedListOperations(linkedList, SIZE);

            System.out.println("\nHashSet -");
            testHashSetOperations(hashSet, SIZE);

            System.out.println("\nHashMap -");
            testHashMapOperations(hashMap, SIZE);
        }
    }

//ArrayList - (в нс)
//Добавление в конец: 12200
//Удаление последнего элемента: 12000
//Добавление в начало: 4949300
//Удаление первого элемента: 4526600
//Получение центрального элемента: 19800
//Его ID: 2500000
//Получение последнего элемента: 3400
//Его ID: 4999999

//LinkedList - (в нс)
//Добавление в конец: 12100
//Удаление последнего элемента: 14100
//Добавление в начало: 10600
//Удаление первого элемента: 7700
//Получение центрального элемента: 40161500
//Его ID: 2500000
//Получение последнего элемента: 5000
//Его ID: 4999999

//HashSet - (в нс)
//Добавление элемента: 48400
//Удаление элемента: 20500
//Наличие центрального элемента: 29600
//Наличие последнего элемента: 8800

//HashMap - (в нс)
//Добавление элемента: 40300
//Удаление элемента: 14300
//Получение центрального элемента: 32200
//Получение последнего элемента: 2500

