package lab2;


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
//        Помимо кода решение должно содержать цифры, полученные при
//        тестах. При невозможности работать с 10 000 000 записей позволительно
//        несколько сократить количество объектов.


import java.util.*;

public class Student {
    public Long id;
    public String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}


class Main {
    public static void main(String[] args) {

        ArrayList<Student> arrayList = new ArrayList<>();
        addStudentsToArrayList(arrayList);
        measureArrayListOperations(arrayList);

        LinkedList<Student> linkedList = new LinkedList<>();
        addStudentsToLinkedList(linkedList);
        measureLinkedListOperations(linkedList);

        HashSet<Student> hashSet = new HashSet<>();
        addStudentsToHashSet(hashSet);
        measureHashSetOperations(hashSet);

        HashMap<Long, Student> hashMap = new HashMap<>();
        addStudentsToHashMap(hashMap);
        measureHashMapOperations(hashMap);
    }

    static void addStudentsToArrayList(ArrayList<Student> arrayList) {
        for (int i = 0; i < 10000000; i++) {
            arrayList.add(new Student((long) i + 1, ""));
        }
    }

    static void measureArrayListOperations(ArrayList<Student> arrayList) {
        long startTime, endTime;
        System.out.println();
        System.out.println("ArrayList");
        System.out.println();

        startTime = System.nanoTime();
        arrayList.addLast(new Student(10000001L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в конец: " + (endTime - startTime) + " нс"); //10100 нс

        startTime = System.nanoTime();
        arrayList.addFirst(new Student(0L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в начало: " + (endTime - startTime) + " нс"); //4000700 нс

        startTime = System.nanoTime();
        arrayList.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс"); //9500 нс

        startTime = System.nanoTime();
        arrayList.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс"); //3691900 нс

        startTime = System.nanoTime();
        arrayList.get(5000000);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс"); //6100 нс

        startTime = System.nanoTime();
        arrayList.get(9999999);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс"); //700 нс
    }

    static void addStudentsToLinkedList(LinkedList<Student> linkedList) {
        for (int i = 0; i < 10000000; i++) {
            linkedList.add(new Student((long) i + 1, ""));
        }
    }

    static void measureLinkedListOperations(LinkedList<Student> linkedList) {
        long startTime, endTime;

        System.out.println();
        System.out.println("LinkedList");
        System.out.println();


        startTime = System.nanoTime();
        linkedList.addLast(new Student(10000001L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в конец: " + (endTime - startTime) + " нс"); //15600 нс

        startTime = System.nanoTime();
        linkedList.addFirst(new Student(0L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента в начало: " + (endTime - startTime) + " нс"); //10700 нс

        startTime = System.nanoTime();
        linkedList.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс"); //4500 нс

        startTime = System.nanoTime();
        linkedList.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс"); //2800 нс

        startTime = System.nanoTime();
        linkedList.get(5000000);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс"); //52754400 нс

        startTime = System.nanoTime();
        linkedList.get(9999999);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс"); //3400 нс
    }

    static void addStudentsToHashSet(HashSet<Student> hashSet) {
        for (int i = 0; i < 10000000; i++) {
            hashSet.add(new Student((long) i + 1, ""));
        }
    }

    static void measureHashSetOperations(HashSet<Student> hashSet) {
        long startTime, endTime;

        System.out.println();
        System.out.println("HashSet");
        System.out.println();

        startTime = System.nanoTime();
        hashSet.add(new Student(10000001L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime) + " нс"); //63100 нс

        startTime = System.nanoTime();
        hashSet.remove(new Student(10000001L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс"); //18500 нс

        startTime = System.nanoTime();
        hashSet.remove(new Student(1L, ""));
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс"); //3200 нс

        startTime = System.nanoTime();
        hashSet.contains(new Student(5000000L, ""));
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс"); //18400 нс

        startTime = System.nanoTime();
        hashSet.contains(new Student(10000000L, ""));
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс"); //2000 нс
    }

    static void addStudentsToHashMap(HashMap<Long, Student> hashMap) {
        for (int i = 0; i < 10000000; i++) {
            hashMap.put((long) i + 1, new Student((long) i + 1, ""));
        }
    }

    static void measureHashMapOperations(HashMap<Long, Student> hashMap) {
        long startTime, endTime;

        System.out.println();
        System.out.println("HashMap");
        System.out.println();

        startTime = System.nanoTime();
        hashMap.put(10000001L, new Student(10000001L, "Руслан"));
        endTime = System.nanoTime();
        System.out.println("Добавление элемента: " + (endTime - startTime) + " нс"); //2700 нс

        startTime = System.nanoTime();
        hashMap.remove(1L);
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " нс"); //9200 нс

        startTime = System.nanoTime();
        hashMap.remove(10000001L);
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " нс"); //7200 нс

        startTime = System.nanoTime();
        hashMap.get(5000000L);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (endTime - startTime) + " нс"); //6100 нс

        startTime = System.nanoTime();
        hashMap.get(9999999L);
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (endTime - startTime) + " нс"); //1200 нс
    }
}
