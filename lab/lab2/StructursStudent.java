import java.util.*;

public class StructursStudent {

    public static void main(String[] args) {
        MyArrayList<Student> arrayList = new MyArrayList<>();
        MyLinkedList<Student> linkedList = new MyLinkedList<>();
        MyHashSet<Student> hashSet = new MyHashSet<>();
        MyHashMap<Long, Student> hashMap = new MyHashMap<>();

        initializeStructures(arrayList, linkedList, hashSet, hashMap);

        measureArrayListPerformance(arrayList);
        measureLinkedListPerformance(linkedList);
        measureHashSetPerformance(hashSet);
        measureHashMapPerformance(hashMap);
    }

    private static void initializeStructures(MyArrayList<Student> arrayList,
                                             MyLinkedList<Student> linkedList,
                                             MyHashSet<Student> hashSet,
                                             MyHashMap<Long, Student> hashMap) {
        for (long i = 0; i < 5000000; i++) {
            Student student = new Student(i, "Student " + i);
            arrayList.addLast(student);
            linkedList.addLast(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }
    }

    private static void measureArrayListPerformance(MyArrayList<Student> list) {
        System.out.println("=== MyArrayList Performance Test (время в наносекундах) ===");

        // Добавление в конец
        Student newEndStudent = new Student(5000000L, "New End Student");
        long startTime = System.nanoTime();
        list.addLast(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление в начало
        Student newStartStudent = new Student(5000001L, "New Start Student");
        startTime = System.nanoTime();
        list.addFirst(newStartStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        Student removedFirst = list.remove(0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        // Взятие центрального элемента
        int middleIndex = list.size() / 2;
        startTime = System.nanoTime();
        Student middleStudent = list.get(middleIndex);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        // Взятие последнего элемента
        startTime = System.nanoTime();
        Student lastStudent = list.get(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + list.size() + "\n");
    }

    private static void measureLinkedListPerformance(MyLinkedList<Student> list) {
        System.out.println("=== MyLinkedList Performance Test (время в наносекундах) ===");

        // Добавление в конец
        Student newEndStudent = new Student(5000000L, "New End Student");
        long startTime = System.nanoTime();
        list.addLast(newEndStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление в конец: " + (endTime - startTime) + " нс");

        // Добавление в начало
        Student newStartStudent = new Student(5000001L, "New Start Student");
        startTime = System.nanoTime();
        list.addFirst(newStartStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление в начало: " + (endTime - startTime) + " нс");

        // Удаление последнего элемента
        startTime = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс");

        // Удаление первого элемента
        startTime = System.nanoTime();
        Student removedFirst = list.remove(0);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс");

        // Взятие центрального элемента
        int middleIndex = list.size() / 2;
        startTime = System.nanoTime();
        Student middleStudent = list.get(middleIndex);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        // Взятие последнего элемента
        startTime = System.nanoTime();
        Student lastStudent = list.get(list.size() - 1);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + list.size() + "\n");
    }

    private static void measureHashSetPerformance(MyHashSet<Student> set) {
        System.out.println("=== MyHashSet Performance Test (время в наносекундах) ===");

        // Добавление несуществующего элемента
        Student newStudent = new Student(5000000L, "New Student");
        long startTime = System.nanoTime();
        boolean added = set.add(newStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление несуществующего элемента: " + (endTime - startTime) + " нс");

        // Добавление элемента
        Student anotherStudent = new Student(5000001L, "Another Student");
        startTime = System.nanoTime();
        boolean added2 = set.add(anotherStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление другого элемента: " + (endTime - startTime) + " нс");

        // Удаление последнего добавленного элемента
        startTime = System.nanoTime();
        boolean removedLast = set.remove(anotherStudent);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего добавленного элемента: " + (endTime - startTime) + " нс");

        // Удаление первого добавленного элемента
        Student firstStudent = new Student(0L, "Student 0");
        startTime = System.nanoTime();
        boolean removedFirst = set.remove(firstStudent);
        endTime = System.nanoTime();
        System.out.println("Удаление первого добавленного элемента: " + (endTime - startTime) + " нс");

        // Проверка наличия центрального элемента
        Student middleStudent = new Student(2500000L, "Student 5000000");
        startTime = System.nanoTime();
        boolean containsMiddle = set.contains(middleStudent);
        endTime = System.nanoTime();
        System.out.println("Проверка центрального элемента: " + (endTime - startTime) + " нс");

        // Проверка наличия последнего добавленного элемента
        startTime = System.nanoTime();
        boolean containsLast = set.contains(newStudent);
        endTime = System.nanoTime();
        System.out.println("Проверка последнего добавленного элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + set.size() + "\n");
    }

    private static void measureHashMapPerformance(MyHashMap<Long, Student> map) {
        System.out.println("=== MyHashMap Performance Test (время в наносекундах) ===");

        // Добавление несуществующего элемента в конец (по новому ключу)
        Student newStudent = new Student(5000000L, "New Student");
        long startTime = System.nanoTime();
        map.put(5000000L, newStudent);
        long endTime = System.nanoTime();
        System.out.println("Добавление несуществующего элемента: " + (endTime - startTime) + " нс");

        // Добавление элемента
        Student anotherStudent = new Student(5000001L, "Another Student");
        startTime = System.nanoTime();
        map.put(5000001L, anotherStudent);
        endTime = System.nanoTime();
        System.out.println("Добавление другого элемента: " + (endTime - startTime) + " нс");

        // Удаление последнего добавленного элемента
        startTime = System.nanoTime();
        Student removedLast = map.remove(5000001L);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего добавленного элемента: " + (endTime - startTime) + " нс");

        // Удаление первого добавленного элемента
        startTime = System.nanoTime();
        Student removedFirst = map.remove(0L);
        endTime = System.nanoTime();
        System.out.println("Удаление первого добавленного элемента: " + (endTime - startTime) + " нс");

        // Взятие центрального элемента
        startTime = System.nanoTime();
        Student middleStudent = map.get(2500000L);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс");

        // Взятие последнего добавленного элемента
        startTime = System.nanoTime();
        Student lastStudent = map.get(5000000L);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего добавленного элемента: " + (endTime - startTime) + " нс");

        System.out.println("Итоговый размер: " + map.size() + "\n");
    }


}

//Результаты для 5.000.000 строк данных:
//
//        === MyArrayList Performance Test (время в наносекундах) ===
//Добавление в конец: 106174000 нс
//Добавление в начало: 138141200 нс
//Удаление последнего элемента: 30700 нс
//Удаление первого элемента: 135670900 нс
//Взятие центрального элемента: 8700 нс
//Взятие последнего элемента: 1300 нс
//Итоговый размер: 5000000
//
//        === MyLinkedList Performance Test (время в наносекундах) ===
//Добавление в конец: 2600 нс
//Добавление в начало: 9000 нс
//Удаление последнего элемента: 6800 нс
//Удаление первого элемента: 1300 нс
//Взятие центрального элемента: 16182400 нс
//Взятие последнего элемента: 32560700 нс
//Итоговый размер: 5000000
//
//        === MyHashSet Performance Test (время в наносекундах) ===
//Добавление несуществующего элемента: 4700 нс
//Добавление другого элемента: 1400 нс
//Удаление последнего добавленного элемента: 71800 нс
//Удаление первого добавленного элемента: 5200 нс
//Проверка центрального элемента: 8800 нс
//Проверка последнего добавленного элемента: 1700 нс
//Итоговый размер: 5000000
//
//        === MyHashMap Performance Test (время в наносекундах) ===
//Добавление несуществующего элемента: 3500 нс
//Добавление другого элемента: 800 нс
//Удаление последнего добавленного элемента: 73300 нс
//Удаление первого добавленного элемента: 23500 нс
//Взятие центрального элемента: 8000 нс
//Взятие последнего добавленного элемента: 2900 нс
//Итоговый размер: 5000000