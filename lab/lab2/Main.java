
import java.util.*;

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

public class Main {

    public static void main(String[] args) {

        int N = 10000000;

        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>();
        HashMap<Long, Student> hashMap = new HashMap<>();

        //Создание объектов

        for (int i = 1; i <= N; i++) {
            Student s = new Student((long) i, "Student " + i);
            arrayList.add(s);
            linkedList.add(s);
            hashSet.add(s);
            hashMap.put(s.id, s);
        }

        Student newStudentEnd = new Student((long) (N + 1), "New End");
        Student newStudentStart = new Student((long) (N + 2), "New Start");

        System.out.println("ArrayList");

        long t1, t2;

        // 1. Добавление 1 несуществующего элемента в конец
        t1 = System.nanoTime();
        arrayList.add(newStudentEnd);
        t2 = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в конец: " + (t2 - t1) + " ns");

        // 2. Добавление 1 несуществующего элемента в начало
        t1 = System.nanoTime();
        arrayList.add(0, newStudentStart);
        t2 = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в начало: " + (t2 - t1) + " ns");

        // 3. Удаление последнего элемента
        t1 = System.nanoTime();
        arrayList.remove(arrayList.size() - 1);
        t2 = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (t2 - t1) + " ns");

        // 4. Удаление первого элемента
        t1 = System.nanoTime();
        arrayList.remove(0);
        t2 = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (t2 - t1) + " ns");

        // 5. Взятие (Get) центрального элемента
        t1 = System.nanoTime();
        arrayList.get(N / 2);
        t2 = System.nanoTime();
        System.out.println("Взятие (Get) центрального элемента: " + (t2 - t1) + " ns");

        // 6. Взятие (Get) последнего элемента
        t1 = System.nanoTime();
        arrayList.get(arrayList.size() - 1);
        t2 = System.nanoTime();
        System.out.println("Взятие (Get) последнего элемента: " + (t2 - t1) + " ns");


        System.out.println("LinkedList");

        // 1. Добавление 1 несуществующего элемента в конец
        t1 = System.nanoTime();
        linkedList.add(newStudentEnd);
        t2 = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в конец: " + (t2 - t1) + " ns");

        // 2. Добавление 1 несуществующего элемента в начало
        t1 = System.nanoTime();
        linkedList.addFirst(newStudentStart);
        t2 = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в начало: " + (t2 - t1) + " ns");

        // 3. Удаление последнего элемента
        t1 = System.nanoTime();
        linkedList.removeLast();
        t2 = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (t2 - t1) + " ns");

        // 4. Удаление первого элемента
        t1 = System.nanoTime();
        linkedList.removeFirst();
        t2 = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (t2 - t1) + " ns");

        // 5. Взятие (Get) центрального элемента
        t1 = System.nanoTime();
        linkedList.get(N / 2);
        t2 = System.nanoTime();
        System.out.println("Взятие (Get) центрального элемента: " + (t2 - t1) + " ns");

        // 6. Взятие (Get) последнего элемента
        t1 = System.nanoTime();
        linkedList.get(linkedList.size() - 1);
        t2 = System.nanoTime();
        System.out.println("Взятие (Get) последнего элемента: " + (t2 - t1) + " ns");


        System.out.println("HashSet");

        // 1. Add
        t1 = System.nanoTime();
        hashSet.add(newStudentEnd);
        t2 = System.nanoTime();
        System.out.println("Add: " + (t2 - t1) + " ns");

        System.out.println("(удаление и доступ по индексу невозможно в Set)\n");


        System.out.println("HashMap");

        // 1. Put
        t1 = System.nanoTime();
        hashMap.put(newStudentEnd.id, newStudentEnd);
        t2 = System.nanoTime();
        System.out.println("Put: " + (t2 - t1) + " ns");

        // 2. Взятие (Get) центрального элемента
        t1 = System.nanoTime();
        hashMap.get((long) (N / 2));
        t2 = System.nanoTime();
        System.out.println("Взятие (Get) центрального элемента: " + (t2 - t1) + " ns");

        // 3. Взятие (Get) последнего элемента
        t1 = System.nanoTime();
        hashMap.get((long) (N - 1));
        t2 = System.nanoTime();
        System.out.println("Взятие (Get) последнего элемента: " + (t2 - t1) + " ns");
    }
}
//ArrayList
//Добавление 1 несуществующего элемента в конец: 1600 ns
//Добавление 1 несуществующего элемента в начало: 79179100 ns
//Удаление последнего элемента: 14600 ns
//Удаление первого элемента: 66772700 ns
//Взятие (Get) центрального элемента: 9000 ns
//Взятие (Get) последнего элемента: 800 ns
//
//LinkedList
//Добавление 1 несуществующего элемента в конец: 1800 ns
//Добавление 1 несуществующего элемента в начало: 9300 ns
//Удаление последнего элемента: 4400 ns
//Удаление первого элемента: 2300 ns
//Взятие (Get) центрального элемента: 16145200 ns
//Взятие (Get) последнего элемента: 6700 ns
//
//HashSet
//Add: 3800 ns
//(удаление и доступ по индексу невозможно в Set)
//
//HashMap
//Put: 1400 ns
//Взятие (Get) центрального элемента: 13400 ns
//Взятие (Get) последнего элемента: 700 ns