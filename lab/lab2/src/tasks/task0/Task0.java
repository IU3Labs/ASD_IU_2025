/**
 * Создать класс Student с полями:
 * 1 Long id
 * 2 String name
 * В методе main:
 * 1 Создать ArrayList, который хранит объекты класса Student
 * (ArrayList<Student>).
 * 2 Создать LinkedList, который хранит объекты класса Student
 * (LinkedList <Student>).
 * 3 Создать Set, который хранит объекты класса Student (HashSet
 * <Student>).
 * 4 Создать HashMap, который хранит объекты класса Student (HashMap
 * <Long, Student>).
 * В каждую структуру данных добавить 10 000 000 объектов.
 * После этого для каждой структуры данных измерить время в нс:
 * 1 Добавление 1 несуществующего элемента в конец (id = 10 000 001).
 * 2 Добавление 1 несуществующего элемента в начало.
 * 3 Удаление последнего элемента
 * 4 Удаление первого элемента
 * 5 Взятие (Get) центрального элемента (id = 5 000 000)
 * 6 Взятие (Get) последнего элемента (id = 9 999 999).
 * Помимо кода решение должно содержать цифры, полученные при
 * тестах. При невозможности работать с 10 000 000 записей позволительно
 * несколько сократить количество объектов.
 */
package tasks.task0;
import java.util.*;

public class Task0 {
    private static final int studentsAmount = 10000000;

    public static void task0() {
        List<Student> contingentArrayList = new ArrayList<Student>();

        for( int i = 0; i < studentsAmount; i++){
            contingentArrayList.add(new Student((long) i, "Vitalik"+i));
        }

        List<Student> contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        Set<Student> contingentHashSet = new HashSet<Student>(contingentArrayList);
        Map<Long, Student> contingentHashMap = new HashMap<>();
        for (Student vitalik: contingentArrayList){
            contingentHashMap.put(vitalik.getId(), vitalik);
        }

        Student lastStudent = contingentArrayList.getLast();
        Student firstStudent = contingentArrayList.getFirst();
        Student newStudentLast = new Student((long)studentsAmount+1, "Vitalik"+studentsAmount+1);
        Student newStudentFirst = new Student((long)studentsAmount+2, "Vitalik"+studentsAmount+2);
        Student centralStudent = contingentArrayList.get(studentsAmount/2);

        System.out.println("---------------------------------------------");
        System.out.println("ArrayList time test start...");
        System.out.println("---------------------------------------------");
        ArrayList<Student> arrayList = new ArrayList<>(contingentArrayList);
        long startTime = System.nanoTime();
        arrayList.addLast(newStudentLast);
        long resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        arrayList = new ArrayList<>(contingentArrayList);
        startTime = System.nanoTime();
        arrayList.addFirst(newStudentFirst);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to start position time: " + resultTime + " ns");

        arrayList = new ArrayList<>(contingentArrayList);
        startTime = System.nanoTime();
        arrayList.remove(lastStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove last element time: " + resultTime + " ns");

        arrayList = new ArrayList<>(contingentArrayList);
        startTime = System.nanoTime();
        arrayList.remove(firstStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove first element time: " + resultTime + " ns");

        arrayList = new ArrayList<>(contingentArrayList);
        startTime = System.nanoTime();
        Student centralVitalik = arrayList.get(studentsAmount/2);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get middle element time: " + resultTime + " ns");

        arrayList = new ArrayList<>(contingentArrayList);
        startTime = System.nanoTime();
        Student lastVitalik = arrayList.get(studentsAmount - 1);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element time: " + resultTime + " ns");

        System.out.println("---------------------------------------------");
        System.out.println("ArrayList time test END.");
        System.out.println("---------------------------------------------\n");

        System.out.println("---------------------------------------------");
        System.out.println("LinkedList time test start...");
        System.out.println("---------------------------------------------");
        startTime = System.nanoTime();
        contingentLinkedList.addLast(newStudentLast);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        startTime = System.nanoTime();
        contingentLinkedList.addFirst(newStudentFirst);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to start position time: " + resultTime + " ns");

        contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        startTime = System.nanoTime();
        contingentLinkedList.remove(lastStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove last element time: " + resultTime + " ns");

        contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        startTime = System.nanoTime();
        contingentLinkedList.remove(firstStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove first element time: " + resultTime + " ns");

        contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        startTime = System.nanoTime();
        centralVitalik = contingentLinkedList.get(studentsAmount/2);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get middle element time: " + resultTime + " ns");

        contingentLinkedList = new LinkedList<Student>(contingentArrayList);
        startTime = System.nanoTime();
        lastVitalik = contingentLinkedList.get(studentsAmount - 1);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element time: " + resultTime + " ns");

        System.out.println("---------------------------------------------");
        System.out.println("LinkedList time test END.");
        System.out.println("---------------------------------------------\n");

        System.out.println("---------------------------------------------");
        System.out.println("HashSet time test start...");
        System.out.println("---------------------------------------------");
        startTime = System.nanoTime();
        contingentHashSet.add(newStudentLast);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        contingentHashSet = new HashSet<Student>(contingentArrayList);
        startTime = System.nanoTime();
        contingentHashSet.remove(lastStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove last element time: " + resultTime + " ns");

        contingentHashSet = new HashSet<Student>(contingentArrayList);
        startTime = System.nanoTime();
        contingentHashSet.remove(firstStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove first element time: " + resultTime + " ns");

        contingentHashSet = new HashSet<Student>(contingentArrayList);
        startTime = System.nanoTime();
        boolean centralVitalikHash = contingentHashSet.contains(centralStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get middle element time: " + resultTime + " ns");

        contingentHashSet = new HashSet<Student>(contingentArrayList);
        startTime = System.nanoTime();
        boolean lastVitalikHash = contingentHashSet.contains(lastStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element time: " + resultTime + " ns");

        System.out.println("---------------------------------------------");
        System.out.println("HashSet time test END.");
        System.out.println("---------------------------------------------\n");


        System.out.println("---------------------------------------------");
        System.out.println("HashMap time test start...");
        System.out.println("---------------------------------------------");
        startTime = System.nanoTime();
        contingentHashMap.put(lastStudent.getId(), lastStudent);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Add to end position time: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentHashMap.get((long)studentsAmount/2);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get central element: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentHashMap.get((long)studentsAmount-1);
        resultTime = System.nanoTime() - startTime;
        System.out.println("Get last element: " + resultTime + " ns");

        startTime = System.nanoTime();
        contingentHashMap.remove(lastStudent.getId());
        resultTime = System.nanoTime() - startTime;
        System.out.println("Remove element by Key: " + resultTime + " ns");


        System.out.println("---------------------------------------------");
        System.out.println("HashMap time test END.");
        System.out.println("---------------------------------------------\n");
    }
}
