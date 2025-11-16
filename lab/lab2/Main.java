/**
 * Создать класс Student с полями:
 * 1. Long id
 * 2. String name
 * В методе main:
 * 1. Создать ArrayList, который хранит объекты класса Student
 * (ArrayList<Student>).
 * 2. Создать LinkedList, который хранит объекты класса Student
 * (LinkedList <Student>).
 * 3. Создать Set, который хранит объекты класса Student (HashSet
 * <Student>).
 * 4. Создать HashMap, который хранит объекты класса Student (HashMap
 * <Long, Student>).
*/
import java.util.*;

public class Main {

    private static final int DEFAULT_N = 10_000_000;

    static class Student {
        final Long id;
        final String name;

        Student(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student other = (Student) o;
            return Objects.equals(id, other.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        @Override
        public String toString() {
            return "Student{" + id + ", '" + name + "'}";
        }
    }

    public static void main(String[] args) {
        int N = DEFAULT_N;
        if (args.length > 0) {
            try {
                N = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) { }
        }

        long idNewEnd = (long) N + 1L;
        long idNewStart = -100L;
        long idMiddle = (long) N / 2L;
        long idLast = (long) N - 1L;

        ArrayList<Student> arrayList = new ArrayList<>(N + 10);
        for (long i = 0; i < N; i++) {
            Student student = new Student(i, "Student" + i);
            arrayList.add(student);
        }
        measureArrayList(arrayList, N, idNewStart, idNewEnd, idMiddle, idLast);

        LinkedList<Student> linkedList = new LinkedList<>();
        for (long i = 0; i < N; i++) {
            Student student = new Student(i, "Student" + i);
            linkedList.add(student);
        }
        measureLinkedList(linkedList, N, idNewStart, idNewEnd, idMiddle, idLast);

        HashSet<Student> hashSet = new HashSet<>((int)(N / 0.75f) + 1);
        for (long i = 0; i < N; i++) {
            Student student = new Student(i, "Student" + i);
            hashSet.add(student);
        }
        measureHashSet(hashSet, N, idNewStart, idNewEnd, idMiddle, idLast);

        HashMap<Long, Student> hashMap = new HashMap<>((int)(N / 0.75f) + 1);
        for (long i = 0; i < N; i++) {
            Student student = new Student(i, "Student" + i);
            hashMap.put(i, student);
        }
        measureHashMap(hashMap, N, idNewStart, idNewEnd, idMiddle, idLast);

    }

    private static void measureArrayList(ArrayList<Student> list, int N, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("ArrayList: ");
        long timeStart, timeEnd;

        timeStart = System.nanoTime();
        list.add(new Student(idEnd, "NewEnd"));
        timeEnd = System.nanoTime();
        System.out.println("1) Добавление элемента в конец: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        list.add(0, new Student(idStart, "NewStart"));
        timeEnd = System.nanoTime();
        System.out.println("2) Добавление элемента в начало: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        timeEnd = System.nanoTime();
        System.out.println("3) Удаление последнего элемента: " + (timeEnd - timeStart) + " нс");
        list.add(removedLast);

        timeStart = System.nanoTime();
        Student removedFirst = list.remove(0);
        timeEnd = System.nanoTime();
        System.out.println("4) Удаление первого элемента: " + (timeEnd - timeStart) + " нс");
        list.add(0, removedFirst);

        int midIndex = N / 2;
        timeStart = System.nanoTime();
        Student mid = list.get(midIndex);
        timeEnd = System.nanoTime();
        System.out.println("5) Получение центрального элемента: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        Student last = list.get(list.size() - 1);
        timeEnd = System.nanoTime();
        System.out.println("6) Получение последнего элемента: " + (timeEnd - timeStart) + " нс");
        System.out.println();
    }

    private static void measureLinkedList(LinkedList<Student> list, int N, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("LinkedList: ");
        long timeStart, timeEnd;

        timeStart = System.nanoTime();
        list.addLast(new Student(idEnd, "NewEnd"));
        timeEnd = System.nanoTime();
        System.out.println("1) Добавление элемента в конец: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        list.addFirst(new Student(idStart, "NewStart"));
        timeEnd = System.nanoTime();
        System.out.println("2) Добавление элемента в начало: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        Student removedLast = list.removeLast();
        timeEnd = System.nanoTime();
        System.out.println("3) Удаление последнего элемента: " + (timeEnd - timeStart) + " нс");
        list.addLast(removedLast);

        timeStart = System.nanoTime();
        Student removedFirst = list.removeFirst();
        timeEnd = System.nanoTime();
        System.out.println("4) Удаление первого элемента:" + (timeEnd - timeStart) + " нс");
        list.addFirst(removedFirst);

        int midIndex = N / 2;
        timeStart = System.nanoTime();
        Student mid = list.get(midIndex);
        timeEnd = System.nanoTime();
        System.out.println("5) Получение центрального элемента: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        Student last = list.getLast();
        timeEnd = System.nanoTime();
        System.out.println("6) Получение последнего элемента: " + (timeEnd - timeStart) + " нс");
        System.out.println();
    }

    private static void measureHashSet(HashSet<Student> set, int N, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("HashSet: ");
        long timeStart, timeEnd;

        Student sEnd = new Student(idEnd, "NewEnd");
        timeStart = System.nanoTime();
        boolean added = set.add(sEnd);
        timeEnd = System.nanoTime();
        System.out.println("1) Добавление элемента в конец: " + (timeEnd - timeStart) + " нс");
        if (added) set.remove(sEnd);

        Student sStart = new Student(idStart, "NewStart");
        timeStart = System.nanoTime();
        boolean addedStart = set.add(sStart);
        timeEnd = System.nanoTime();
        System.out.println("2) Добавление элемента в начало: " + (timeEnd - timeStart) + " нс");
        if (addedStart) set.remove(sStart);

        Student probeLast = new Student(idLast, "");
        timeStart = System.nanoTime();
        boolean removedLast = set.remove(probeLast);
        timeEnd = System.nanoTime();
        System.out.println("3) Удаление последнего элемента: " + (timeEnd - timeStart) + " нс");
        if (removedLast) set.add(probeLast);

        Student probeFirst = new Student(0L, "");
        timeStart = System.nanoTime();
        boolean removedFirst = set.remove(probeFirst);
        timeEnd = System.nanoTime();
        System.out.println("4) Удаление первого элемента: " + (timeEnd - timeStart) + " нс");
        if (removedFirst) set.add(probeFirst);

        Student probeMid = new Student(idMid, "");
        timeStart = System.nanoTime();
        boolean hasMid = set.contains(probeMid);
        timeEnd = System.nanoTime();
        System.out.println("5) Проверка наличия центрального элемента: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        boolean hasLast = set.contains(probeLast);
        timeEnd = System.nanoTime();
        System.out.println("6) Проверка наличия последнего элемента: " + (timeEnd - timeStart) + " нс");
        System.out.println();
    }

    private static void measureHashMap(HashMap<Long, Student> map, int N, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("HashMap: ");
        long timeStart, timeEnd;

        Student sEnd = new Student(idEnd, "NewEnd");
        timeStart = System.nanoTime();
        map.put(idEnd, sEnd);
        timeEnd = System.nanoTime();
        System.out.println("1) Добавление элемента в конец: " + (timeEnd - timeStart) + " нс");
        map.remove(idEnd);

        Student sStart = new Student(idStart, "NewStart");
        timeStart = System.nanoTime();
        map.put(idStart, sStart);
        timeEnd = System.nanoTime();
        System.out.println("2) Добавление элемента в начало: " + (timeEnd - timeStart) + " нс");
        map.remove(idStart);

        timeStart = System.nanoTime();
        Student removed = map.remove(idLast);
        timeEnd = System.nanoTime();
        System.out.println("3) Удаление последнего элемента: " + (timeEnd - timeStart) + " нс");
        if (removed != null) map.put(idLast, removed);

        timeStart = System.nanoTime();
        Student removed0 = map.remove(0L);
        timeEnd = System.nanoTime();
        System.out.println("4) Удаление первого элемента: " + (timeEnd - timeStart) + " нс");
        if (removed0 != null) map.put(0L, removed0);

        timeStart = System.nanoTime();
        Student mid = map.get(idMid);
        timeEnd = System.nanoTime();
        System.out.println("5) Проверка наличия центрального элемента: " + (timeEnd - timeStart) + " нс");

        timeStart = System.nanoTime();
        Student last = map.get(idLast);
        timeEnd = System.nanoTime();
        System.out.println("6)Проверка наличия последнего элемента: " + (timeEnd - timeStart) + " нс");
        System.out.println();
    }
}
/**
 * Демонстрация:
 *
 * ArrayList:
 * 1) Добавление элемента в конец: 3041 нс
 * 2) Добавление элемента в начало: 3701875 нс
 * 3) Удаление последнего элемента: 9667 нс
 * 4) Удаление первого элемента: 2813000 нс
 * 5) Получение центрального элемента: 13542 нс
 * 6) Получение последнего элемента: 1083 нс
 *
 * LinkedList:
 * 1) Добавление элемента в конец: 14042 нс
 * 2) Добавление элемента в начало: 4584 нс
 * 3) Удаление последнего элемента: 2542 нс
 * 4) Удаление первого элемента:1750 нс
 * 5) Получение центрального элемента: 58513000 нс
 * 6) Получение последнего элемента: 7042 нс
 *
 * HashSet:
 * 1) Добавление элемента в конец: 28625 нс
 * 2) Добавление элемента в начало: 8708 нс
 * 3) Удаление последнего элемента: 750 нс
 * 4) Удаление первого элемента: 1083 нс
 * 5) Проверка наличия центрального элемента: 9542 нс
 * 6) Проверка наличия последнего элемента: 417 нс
 *
 * HashMap:
 * 1) Добавление элемента в конец: 2125 нс
 * 2) Добавление элемента в начало: 5542 нс
 * 3) Удаление последнего элемента: 500 нс
 * 4) Удаление первого элемента: 1500 нс
 * 5) Проверка наличия центрального элемента: 2750 нс
 * 6)Проверка наличия последнего элемента: 209 нс
 *
 */
