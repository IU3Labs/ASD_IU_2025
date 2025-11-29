
package lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Student {
    private Long id;
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {

        // Задаю initialCapacity с запасом, чтобы расширение коллекции не влияло на время добавления
        ArrayList<Student> arrayList = new ArrayList<>(100_010);
        LinkedList<Student> linkedList = new LinkedList<>();
        HashSet<Student> hashSet = new HashSet<>(100_010);
        HashMap<Long, Student> hashMap = new HashMap<>(100_010);

        for (long i = 1; i < 100_001; i++) {
            arrayList.add(new Student(i, "Aria"));
            linkedList.add(new Student(i, "Layla"));
            hashSet.add(new Student(i, "Stella"));
            hashMap.put(i, new Student(i, "Maya"));
        }

        // Заранее создаю всех студентов, чтобы создание объектов не влияло на время добавления
        Student firstAria = new Student(0L, "Aria");
        Student lastAria = new Student(100_001L, "Aria");
        Student firstLayla = new Student(0L, "Layla");
        Student lastLayla = new Student(100_001L, "Layla");
        Student firstStella = new Student(0L, "Stella");
        Student lastStella = new Student(100_001L, "Stella");
        Student firstMaya = new Student(0L, "Maya");
        Student lastMaya = new Student(100_001L, "Maya");

        System.out.println("Добавление 1 несуществующего элемента в конец:");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.addLast(lastAria)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.addLast(lastLayla)));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.add(lastStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.put(100_001L, lastMaya)));

        System.out.println("\nДобавление 1 несуществующего элемента в начало:");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.addFirst(firstAria)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.addFirst(firstLayla)));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.add(firstStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.put(0L, firstMaya)));

        System.out.println("\nУдаление последнего элемента:");
        System.out.println(" ArrayList: " + runMeasuring(arrayList::removeLast));
        System.out.println("LinkedList: " + runMeasuring(linkedList::removeLast));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.remove(lastStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.remove(100_001L)));

        System.out.println("\nУдаление первого элемента:");
        System.out.println(" ArrayList: " + runMeasuring(arrayList::removeFirst));
        System.out.println("LinkedList: " + runMeasuring(linkedList::removeFirst));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.remove(firstStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.remove(0L)));

        System.out.println("\nВзятие центрального элемента:");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.get(49_999)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.get(49_999)));
        System.out.println("   HashSet: " + "<операция не предусмотрена>");
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.get(50_000L)));

        System.out.println("\nВзятие последнего элемента:");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.get(99_999)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.get(99_999)));
        System.out.println("   HashSet: " + "<операция не предусмотрена>");
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.get(100_000L)));
    }

    private static long runMeasuring(Runnable function) {
        long startTime = System.nanoTime();
        function.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /*
        HashSet не является упорядоченной коллекцией,
        поэтому не предусматривает добавление элементов в начало или конец.
        Для того чтобы технически добавить элемент "в начало" хэш-таблицы,
        я переопределяю метод hashCode(), чтобы хеш студента был его индексом.
    */
    @Override
    public int hashCode() {
        return id.intValue();
    }
}

/*
    Данные для 100 000 элементов

    Добавление 1 несуществующего элемента в конец:
     ArrayList: 7400
    LinkedList: 5000
       HashSet: 3400
       HashMap: 2900

    Добавление 1 несуществующего элемента в начало:
     ArrayList: 44900
    LinkedList: 8500
       HashSet: 2700
       HashMap: 3200

    Удаление последнего элемента:
     ArrayList: 5100
    LinkedList: 4000
       HashSet: 12400
       HashMap: 8100

    Удаление первого элемента:
     ArrayList: 43600
    LinkedList: 4000
       HashSet: 4800
       HashMap: 4400

    Взятие центрального элемента:
     ArrayList: 4700
    LinkedList: 638800
       HashSet: <операция не предусмотрена>
       HashMap: 11100

    Взятие последнего элемента:
     ArrayList: 3900
    LinkedList: 2700
       HashSet: <операция не предусмотрена>
       HashMap: 3200
*/
