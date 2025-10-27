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

class StudentsArray {
    public static class Student {

        private final Long id;
        private final String name;

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Student student = (Student) object;
            return Objects.equals(id, student.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        public Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }
    }

    static long N = 7000000;

    private static final Student addStudent = new Student( N, "add");
    private static final Student firstStudent = new Student((long)-1, "first");
    private static final Student middleStudent = new Student( N / 2, "middle");
    private static final Student lastStudent = new Student(N - 1, "last");

    static void arrayListPerformances(List<Student> list) {

        long start = System.nanoTime();
        list.addLast(addStudent);
        long end = System.nanoTime();
        System.out.println("Добавление последнего элемента: " + (end - start));

        start = System.nanoTime();
        list.removeLast();
        end = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (end - start));

        start = System.nanoTime();
        list.addFirst(firstStudent);
        end = System.nanoTime();
        System.out.println("Добавление первого элемента: " + (end - start));

        start = System.nanoTime();
        list.removeFirst();
        end = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (end - start));

        start = System.nanoTime();
        Student center = list.get((int)StudentsArray.N / 2);
        end = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (end - start));
        System.out.println("ID центрального элемента: " + center.getId());

        start = System.nanoTime();
        Student last = list.get((int)StudentsArray.N - 1);
        end = System.nanoTime();
        System.out.println("Получение последнего элемента: " + (end - start));
        System.out.println("ID последнего элемента: " + last.getId());
    }

    static void linkedListPerformance(List<Student> list) {

        long start = System.nanoTime();
        list.add(addStudent);
        long end = System.nanoTime();
        System.out.println("Добавление последнего элемента: " + (end - start));

        start = System.nanoTime();
        list.removeLast();
        end = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (end - start));

        start = System.nanoTime();
        list.addFirst(firstStudent);
        end = System.nanoTime();
        System.out.println("Добавление первого элемента: " + (end - start));

        start = System.nanoTime();
        list.removeFirst();
        end = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (end - start));

        start = System.nanoTime();
        Student center = list.get((int)StudentsArray.N / 2);
        end = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (end - start));
        System.out.println("ID центрального элемента: " + center.getId());

        start = System.nanoTime();
        Student last = list.get((int)StudentsArray.N - 1);
        end = System.nanoTime();
        System.out.println("Получение последнего элемента: " + (end - start));
        System.out.println("ID последнего элемента: " + last.getId());
    }

    static void setPerformance(Set<Student> set) {

        long start = System.nanoTime();
        set.add(addStudent);
        long end = System.nanoTime();
        System.out.println("Добавление элемента: " + (end - start));

        start = System.nanoTime();
        set.remove(addStudent);
        end = System.nanoTime();
        System.out.println("Удаление элемента: " + (end - start));

        start = System.nanoTime();
        boolean isMiddleStudent = set.contains(middleStudent);
        end = System.nanoTime();
        System.out.println("Проверка на наличие центрального элемента: " + (end - start));

        start = System.nanoTime();
        isMiddleStudent = set.contains(lastStudent);
        end = System.nanoTime();
        System.out.println("Проверка на наличие последнего элемента: " + (end - start));
    }

    static void mapPerformance(Map<Long, Student> map) {

        long start = System.nanoTime();
        map.put(addStudent.getId(), addStudent);
        long end = System.nanoTime();
        System.out.println("Добавление элемента: " + (end - start));

        start = System.nanoTime();
        map.remove(addStudent.getId());
        end = System.nanoTime();
        System.out.println("Удаление элемента: " + (end - start));

        start = System.nanoTime();
        map.get(middleStudent.getId());
        end = System.nanoTime();
        System.out.println("Получение центрального элемента: " + (end - start));

        start = System.nanoTime();
        map.get(lastStudent.getId());
        end = System.nanoTime();
        System.out.println("Получение последнего элемента: " + (end - start));
    }
}

public class Students{
    public static void main() {

        List<StudentsArray.Student> arrayList = new ArrayList<>();
        List<StudentsArray.Student> linkedList = new LinkedList<>();
        Set<StudentsArray.Student> hashSet = new HashSet<>();
        Map<Long, StudentsArray.Student> hashMap = new HashMap<>();
        for (long i = 0; i < StudentsArray.N; i++) {
            arrayList.add(new StudentsArray.Student(i, "Name"));
            linkedList.add(new StudentsArray.Student(i, "Name"));
            hashSet.add(new StudentsArray.Student(i, "Name"));
            hashMap.put(i, new StudentsArray.Student(i, "Name"));
        }

        System.out.println("\nArrayList:");
        StudentsArray.arrayListPerformances(arrayList);

        System.out.println("\nLinkedList:");
        StudentsArray.linkedListPerformance(linkedList);

        System.out.println("\nHashSet:");
        StudentsArray.setPerformance(hashSet);

        System.out.println("\nHashMap:");
        StudentsArray.mapPerformance(hashMap);
    }

}
//ArrayList:
//Добавление последнего элемента: 11700
//Удаление последнего элемента: 10000
//Добавление первого элемента: 77600600
//Удаление первого элемента: 76889800
//Получение центрального элемента: 14600
//ID центрального элемента: 3500000
//Получение последнего элемента: 1800
//ID последнего элемента: 6999999
//
//LinkedList:
//Добавление последнего элемента: 3000
//Удаление последнего элемента: 12900
//Добавление первого элемента: 6500
//Удаление первого элемента: 7100
//Получение центрального элемента: 42632600
//ID центрального элемента: 3500000
//Получение последнего элемента: 4400
//ID последнего элемента: 6999999
//
//HashSet:
//Добавление элемента: 5100
//Удаление элемента: 17300
//Проверка на наличие центрального элемента: 43600
//Проверка на наличие последнего элемента: 5000
//
//HashMap:
//Добавление элемента: 2900
//Удаление элемента: 11200
//Получение центрального элемента: 8900
//Получение последнего элемента: 2700