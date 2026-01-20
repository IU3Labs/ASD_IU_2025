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
            return "Student{id=" + id + ", name='" + name + "'}";
        }
    }

    public static void main(String[] args) {
        int n = DEFAULT_N;
        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) { }
        }

        // Подготовка данных для тестов
        long idNewEnd = (long) n + 1L;
        long idNewStart = -100L;
        long idMiddle = (long) n / 2L;
        long idLast = (long) n - 1L;

        // 1. Тестирование ArrayList
        ArrayList<Student> arrayList = new ArrayList<>(n + 10);
        for (long i = 0; i < n; i++) {
            arrayList.add(new Student(i, "Student" + i));
        }
        measureArrayList(arrayList, n, idNewStart, idNewEnd, idMiddle, idLast);

        // 2. Тестирование LinkedList
        LinkedList<Student> linkedList = new LinkedList<>();
        for (long i = 0; i < n; i++) {
            linkedList.add(new Student(i, "Student" + i));
        }
        measureLinkedList(linkedList, n, idNewStart, idNewEnd, idMiddle, idLast);

        // 3. Тестирование HashSet
        HashSet<Student> hashSet = new HashSet<>((int)(n / 0.75f) + 1);
        for (long i = 0; i < n; i++) {
            hashSet.add(new Student(i, "Student" + i));
        }
        measureHashSet(hashSet, n, idNewStart, idNewEnd, idMiddle, idLast);

        // 4. Тестирование HashMap
        HashMap<Long, Student> hashMap = new HashMap<>((int)(n / 0.75f) + 1);
        for (long i = 0; i < n; i++) {
            hashMap.put(i, new Student(i, "Student" + i));
        }
        measureHashMap(hashMap, n, idNewStart, idNewEnd, idMiddle, idLast);
    }

    private static void measureArrayList(ArrayList<Student> list, int n, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("--- ArrayList Performance ---");
        long start, end;

        start = System.nanoTime();
        list.add(new Student(idEnd, "NewEnd"));
        end = System.nanoTime();
        System.out.println("1) Add to end: " + (end - start) + " ns");

        start = System.nanoTime();
        list.add(0, new Student(idStart, "NewStart"));
        end = System.nanoTime();
        System.out.println("2) Add to start: " + (end - start) + " ns");

        start = System.nanoTime();
        Student removedLast = list.remove(list.size() - 1);
        end = System.nanoTime();
        System.out.println("3) Remove last: " + (end - start) + " ns");
        list.add(removedLast);

        start = System.nanoTime();
        Student removedFirst = list.remove(0);
        end = System.nanoTime();
        System.out.println("4) Remove first: " + (end - start) + " ns");
        list.add(0, removedFirst);

        int midIndex = n / 2;
        start = System.nanoTime();
        list.get(midIndex);
        end = System.nanoTime();
        System.out.println("5) Get middle element: " + (end - start) + " ns");

        start = System.nanoTime();
        list.get(list.size() - 1);
        end = System.nanoTime();
        System.out.println("6) Get last element: " + (end - start) + " ns");
        System.out.println();
    }

    private static void measureLinkedList(LinkedList<Student> list, int n, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("--- LinkedList Performance ---");
        long start, end;

        start = System.nanoTime();
        list.addLast(new Student(idEnd, "NewEnd"));
        end = System.nanoTime();
        System.out.println("1) Add to end: " + (end - start) + " ns");

        start = System.nanoTime();
        list.addFirst(new Student(idStart, "NewStart"));
        end = System.nanoTime();
        System.out.println("2) Add to start: " + (end - start) + " ns");

        start = System.nanoTime();
        Student resLast = list.removeLast();
        end = System.nanoTime();
        System.out.println("3) Remove last: " + (end - start) + " ns");
        list.addLast(resLast);

        start = System.nanoTime();
        Student resFirst = list.removeFirst();
        end = System.nanoTime();
        System.out.println("4) Remove first: " + (end - start) + " ns");
        list.addFirst(resFirst);

        int midIndex = n / 2;
        start = System.nanoTime();
        list.get(midIndex);
        end = System.nanoTime();
        System.out.println("5) Get middle element: " + (end - start) + " ns");

        start = System.nanoTime();
        list.getLast();
        end = System.nanoTime();
        System.out.println("6) Get last element: " + (end - start) + " ns");
        System.out.println();
    }

    private static void measureHashSet(HashSet<Student> set, int n, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("--- HashSet Performance ---");
        long start, end;

        Student sEnd = new Student(idEnd, "NewEnd");
        start = System.nanoTime();
        boolean addedEnd = set.add(sEnd);
        end = System.nanoTime();
        System.out.println("1) Add element: " + (end - start) + " ns");
        if (addedEnd) set.remove(sEnd);

        Student sStart = new Student(idStart, "NewStart");
        start = System.nanoTime();
        boolean addedStart = set.add(sStart);
        end = System.nanoTime();
        System.out.println("2) Add another element: " + (end - start) + " ns");
        if (addedStart) set.remove(sStart);

        Student probeLast = new Student(idLast, "");
        start = System.nanoTime();
        boolean removed = set.remove(probeLast);
        end = System.nanoTime();
        System.out.println("3) Remove element: " + (end - start) + " ns");
        if (removed) set.add(probeLast);

        Student probeMid = new Student(idMid, "");
        start = System.nanoTime();
        set.contains(probeMid);
        end = System.nanoTime();
        System.out.println("4) Contains middle: " + (end - start) + " ns");

        start = System.nanoTime();
        set.contains(probeLast);
        end = System.nanoTime();
        System.out.println("5) Contains last: " + (end - start) + " ns");
        System.out.println();
    }

    private static void measureHashMap(HashMap<Long, Student> map, int n, long idStart, long idEnd, long idMid, long idLast) {
        System.out.println("--- HashMap Performance ---");
        long start, end;

        Student sEnd = new Student(idEnd, "NewEnd");
        start = System.nanoTime();
        map.put(idEnd, sEnd);
        end = System.nanoTime();
        System.out.println("1) Put element: " + (end - start) + " ns");
        map.remove(idEnd);

        start = System.nanoTime();
        map.remove(idLast);
        end = System.nanoTime();
        System.out.println("2) Remove element: " + (end - start) + " ns");
        map.put(idLast, new Student(idLast, "Student" + idLast));

        start = System.nanoTime();
        map.get(idMid);
        end = System.nanoTime();
        System.out.println("3) Get middle element: " + (end - start) + " ns");

        start = System.nanoTime();
        map.get(idLast);
        end = System.nanoTime();
        System.out.println("4) Get last element: " + (end - start) + " ns");
        System.out.println();
    }
}