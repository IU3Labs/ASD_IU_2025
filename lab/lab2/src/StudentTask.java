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

    // Методы для тестирования LinkedList
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

    // Методы для тестирования HashSet
    private static void testHashSetOperations(Set<Student> set, int size) {
        // 1. Добавление элемента (аналог "в конец" - для Set нет понятия начала/конца)
        long startTime = System.nanoTime();
        set.add(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime));

        // 2. Добавление в начало - для Set не применимо

        // 3. Удаление последнего элемента - для Set не применимо
        // Вместо этого удалим произвольный элемент
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

    // Методы для тестирования HashMap
    private static void testHashMapOperations(Map<Long, Student> map, int size) {

        // 1. Добавление элемента
        long startTime = System.nanoTime();
        map.put(newEndStudent.getId(), newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime));

        // 2. Добавление в начало - для HashMap не применимо

        // 3. Удаление элемента элемента
        startTime = System.nanoTime();
        map.remove(newEndStudent.getId());
        endTime = System.nanoTime();
        System.out.println("Удаление элемента: " + (endTime - startTime));

        // 5. Получение центрального элемента
        startTime = System.nanoTime();
        map.get(centerStudent.getId());
        endTime = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (endTime - startTime));

        // 6. Получение последнего элемента
        startTime = System.nanoTime();
        map.get(endStudent.getId());
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

            // 1. ArrayList операции
            System.out.println("\n--- ArrayList ---");
            testArrayListOperations(arrayList, SIZE);

            // 2. LinkedList операции
            System.out.println("\n--- LinkedList ---");
            testLinkedListOperations(linkedList, SIZE);

            // 3. HashSet операции
            System.out.println("\n--- HashSet ---");
            testHashSetOperations(hashSet, SIZE);

            // 4. HashMap операции
            System.out.println("\n--- HashMap ---");
            testHashMapOperations(hashMap, SIZE);
        }
    }

//ArrayList:

//Добавление в конец: 7600
//Удаление последнего элемента: 6000
//Добавление в начало: 51846000
//Удаление первого элемента: 39433700
//Получение центрального элемента: 17800
//Его ID: 2500000
//Получение последнего элемента: 1100
//Его ID: 4999999

//LinkedList

//Добавление в конец: 1800
//Удаление последнего элемента: 7600
//Добавление в начало: 5600
//Удаление первого элемента: 3300
//Получение центрального элемента: 22842300
//Его ID: 2500000
//Получение последнего элемента: 2300
//Его ID: 4999999

//HashSet

//Добавление элемента: 14600
//Удаление элемента: 8200
//Наличие центрального элемента: 9100
//Наличие последнего элемента: 3300

//HashMap

//Добавление элемента: 7500
//Удаление элемента: 5400
//Получение центрального элемента: 12900
//Получение последнего элемента: 1000

