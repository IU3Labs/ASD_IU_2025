/*
Создать класс Student с полями:
1 Long id
2 String name
В методе main:
1 Создать ArrayList, который хранит объекты класса Student
(ArrayList<Student>).
2 Создать LinkedList, который хранит объекты класса Student
(LinkedList <Student>).
3 Создать Set, который хранит объекты класса Student (HashSet
<Student>).
4 Создать HashMap, который хранит объекты класса Student (HashMap
<Long, Student>).
В каждую структуру данных добавить 10 000 000 объектов.
После этого для каждой структуры данных измерить время в нс:
1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
2 Добавление 1 несуществующего элемента в начало.
3 Удаление последнего элемента
4 Удаление первого элемента
5 Взятие (Get) центрального элемента (id = 5 000 000)
6 Взятие (Get) последнего элемента (id = 9 999 999).
Помимо кода решение должно содержать цифры, полученные при
тестах.
 */

import java.util.*;

public class Students {

    public static void main(String[] args) {
        long size = 10_000_000;

        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap<>();

        for (long i = 0; i < size; i++) {
            Student student = new Student(i, "Student_" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }
        Student newStudent = new Student(size + 1, "newStudent");

        System.out.println("ArrayList");
        timeMeasurementLists(arrayList, newStudent);
        System.out.println("LinkedList");
        timeMeasurementLists(linkedList, newStudent);
        System.out.println("HashSet");
        timeMeasurementSet(hashSet, newStudent);
        System.out.println("HashMap");
        timeMeasurementMap(hashMap, newStudent);
    }

    private static void timeMeasurementLists (List<Student> list, Student newStudent) {
        long start, end;

        start = System.nanoTime();
        list.add(newStudent);
        end = System.nanoTime();
        System.out.println("Время добавления элемента в конец: " + (end - start) + " нс");

        start = System.nanoTime();
        list.add(0, newStudent);
        end = System.nanoTime();
        System.out.println("Время добавления элемента в начало: " + (end - start) + " нс");

        start = System.nanoTime();
        list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Время удаления последнего элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        list.remove(0);
        end = System.nanoTime();
        System.out.println("Время удаления первого элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        Student s = list.get(list.size()/2);
        end = System.nanoTime();
        System.out.println("Время взятия центрального элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        list.get(list.size() - 1);
        end = System.nanoTime();
        System.out.println("Время взятия последнего элемента: " + (end - start) + " нс");
    }

    private static void timeMeasurementSet (HashSet<Student> set, Student newStudent) {
        long start, end;

        start = System.nanoTime();
        set.add(newStudent);
        end = System.nanoTime();
        System.out.println("Время добавления элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        set.remove(newStudent);
        end = System.nanoTime();
        System.out.println("Время удаления элемента: " + (end - start) + " нс");

    }

    private static void timeMeasurementMap (HashMap<Long, Student> map, Student newStudent) {
        long start, end;

        start = System.nanoTime();
        map.put(newStudent.getId(), newStudent);
        end = System.nanoTime();
        System.out.println("Время добавление элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        map.remove(newStudent.getId());
        end = System.nanoTime();
        System.out.println("Время удаления элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        map.get(map.size()/2);
        end = System.nanoTime();
        System.out.println("Взятие центрального элемента: " + (end - start) + " нс");

        start = System.nanoTime();
        map.get(map.size() - 1);
        end = System.nanoTime();
        System.out.println("Взятие последнего элемента: " + (end - start) + " нс");
    }
}

/*
ArrayList
Время добавления элемента в конец: 8792 нс
Время добавления элемента в начало: 4870833 нс
Время удаления последнего элемента: 14333 нс
Время удаления первого элемента: 1630458 нс
Время взятия центрального элемента: 2333 нс
Время взятия последнего элемента: 625 нс
LinkedList
Время добавления элемента в конец: 5667 нс
Время добавления элемента в начало: 16542 нс
Время удаления последнего элемента: 4333 нс
Время удаления первого элемента: 1584 нс
Время взятия центрального элемента: 118958583 нс
Время взятия последнего элемента: 1875 нс
HashSet
Время добавления элемента: 24042 нс
Время удаления элемента: 28792 нс
HashMap
Время добавление элемента: 33333 нс
Время удаления элемента: 6417 нс
Взятие центрального элемента: 18458 нс
Взятие последнего элемента: 1125 нс
*/