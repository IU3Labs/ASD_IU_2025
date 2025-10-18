package lab2.mainTask;

import lab2.utils.*;

import java.util.*;

public class mainTask {
    public static void main(String[] args) {
        Student student;
        ArrayList<Student> arrayList = new ArrayList<Student>();
        LinkedList<Student> linkedList = new LinkedList<Student>();
        HashSet<Student> hashSet = new HashSet<Student>();
        HashMap<Long, Student> hashMap = new HashMap<Long, Student>();
        Student studentNew1 = new Student(11111111, "Bob");
        Student studentNew2 = new Student(22222222, "Mike");
        long startTime, endTime;

        System.out.println("Добавление 10 000 000 записей в структуры");
        for (int i = 0; i < 10000000; i++) {
            student = new Student(i, utils.generateSequence());
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put((long) i, student);
        }

        System.out.println("\nТестирование ArrayList");
        startTime = System.nanoTime();
        arrayList.add(studentNew1);
        endTime = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в конец: " + (endTime - startTime) + " ns");
//        2000 ns
        startTime = System.nanoTime();
        arrayList.addFirst(studentNew2);
        endTime = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в начало: " + (endTime - startTime) + " ns");
//        5161100 ns
        startTime = System.nanoTime();
        arrayList.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " ns");
//        10900 ns
        startTime = System.nanoTime();
        arrayList.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " ns");
//        4864400 ns
        startTime = System.nanoTime();
        student = arrayList.get(5000000);
        endTime = System.nanoTime();
        System.out.println("Взятие (Get) центрального элемент: " + (endTime - startTime) + " ns");
//        11000 ns
        startTime = System.nanoTime();
        student = arrayList.getLast();
        endTime = System.nanoTime();
        System.out.println("Взятие (Get) последнего элемента: " + (endTime - startTime) + " ns");
//        3500 ns

        System.out.println("\nТестирование LinkedList");
        startTime = System.nanoTime();
        linkedList.add(studentNew1);
        endTime = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в конец: " + (endTime - startTime) + " ns");
//        3900 ns
        startTime = System.nanoTime();
        linkedList.addFirst(studentNew2);
        endTime = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента в начало: " + (endTime - startTime) + " ns");
//        29800 ns
        startTime = System.nanoTime();
        linkedList.removeLast();
        endTime = System.nanoTime();
        System.out.println("Удаление последнего элемента: " + (endTime - startTime) + " ns");
//        10500 ns
        startTime = System.nanoTime();
        linkedList.removeFirst();
        endTime = System.nanoTime();
        System.out.println("Удаление первого элемента: " + (endTime - startTime) + " ns");
//        6100 ns
        startTime = System.nanoTime();
        student = linkedList.get(5000000);
        endTime = System.nanoTime();
        System.out.println("Взятие (Get) центрального элемент: " + (endTime - startTime) + " ns");
//        101588100 ns
        startTime = System.nanoTime();
        student = linkedList.getLast();
        endTime = System.nanoTime();
        System.out.println("Взятие (Get) последнего элемента: " + (endTime - startTime) + " ns");
//        8000 ns

        System.out.println("\nТестирование HashSet");
//        Добавление в начало или конец в прямом смысле не возможно
        startTime = System.nanoTime();
        hashSet.add(studentNew1);
        endTime = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента: " + (endTime - startTime) + " ns");
//        8300 ns
//        Удалять элементы можно только по значению
        startTime = System.nanoTime();
        hashSet.remove(studentNew1);
        endTime = System.nanoTime();
        System.out.println("Удаление элемента: " + (endTime - startTime) + " ns");
//        10200 ns
        boolean is_element;
        student = arrayList.get(5000000);
        startTime = System.nanoTime();
        is_element = hashSet.contains(student);
        endTime = System.nanoTime();
        System.out.println("Проверка наличия элемента: " + (endTime - startTime) + " ns");
//        70900 ns
        startTime = System.nanoTime();
        // Напрямую взять элемент из hashSet нельзя
        List<Student> tempList = new ArrayList<>(hashSet);
        student = tempList.get(500000);
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемент (через преобразование в список): " + (endTime - startTime) + " ns");
//        429443500 ns

        startTime = System.nanoTime();
        tempList = new ArrayList<>(hashSet);
        student = tempList.getLast();
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента (через преобразование в список): " + (endTime - startTime) + " ns");
//        417673200 ns

        System.out.println("\nТестирование HashMap");
//        Добавление в начало или конец в прямом смысле не возможно
        startTime = System.nanoTime();
        hashMap.put((long) studentNew1.getID(), studentNew1);
        endTime = System.nanoTime();
        System.out.println("Добавление 1 несуществующего элемента: " + (endTime - startTime) + " ns");
//        16900 ns
//        Удалять элементы можно по ключу
        startTime = System.nanoTime();
        hashMap.remove((long)studentNew1.getID());
        endTime = System.nanoTime();
        System.out.println("Удаление элемента: " + (endTime - startTime) + " ns");
//        13400 ns

        startTime = System.nanoTime();
        is_element = hashMap.containsKey((long)5000000);;
        endTime = System.nanoTime();
        System.out.println("Проверка наличия элемента: " + (endTime - startTime) + " ns");
//        20900 ns
        startTime = System.nanoTime();
        // Напрямую взять элемент из hashMap нельзя
        List<Map.Entry<Long, Student>> entries = new ArrayList<>(hashMap.entrySet());
        student = entries.get(5000000).getValue();
        endTime = System.nanoTime();
        System.out.println("Взятие центрального элемента (через преобразование в список): " + (endTime - startTime) + " ns");
//        219283600 ns

        startTime = System.nanoTime();
        entries = new ArrayList<>(hashMap.entrySet());
        student = entries.getLast().getValue();
        endTime = System.nanoTime();
        System.out.println("Взятие последнего элемента (через преобразование в список): " + (endTime - startTime) + " ns");
//        188893900 ns

    }
}
