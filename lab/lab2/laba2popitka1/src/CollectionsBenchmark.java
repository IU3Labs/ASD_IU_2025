import java.util.*;

public class CollectionsBenchmark {
    private static final int N = 1_000_000;

    public static void main(String[] args) {
        benchmark();
    }

    /** Запускаем все тесты и выводим результаты в наносекундах */
    public static void benchmark() {
        System.out.println("=== Запуск тестов с N = " + N + " элементами ===\n");

        //ArrayList
        ArrayList<Student> arrayList = new ArrayList<>();
        long fillAL = measureTime(() -> fillCollection(arrayList));

        long addEndAL    = measureTime(() -> arrayList.add(new Student((long)(N+1), "NewEnd")));
        long addBeginAL  = measureTime(() -> arrayList.add(0, new Student((long)(N+2), "NewBegin")));
        long removeLastAL  = measureTime(() -> arrayList.remove(arrayList.size()-1));
        long removeFirstAL = measureTime(() -> arrayList.remove(0));
        long getMidAL      = measureTime(() -> arrayList.get(N/2));
        long getLastAL     = measureTime(() -> arrayList.get(N-1));

        //LinkedList
        LinkedList<Student> linkedList = new LinkedList<>();
        long fillLL = measureTime(() -> fillCollection(linkedList));

        long addEndLL    = measureTime(() -> linkedList.addLast(new Student((long)(N+1), "New")));
        long addBeginLL  = measureTime(() -> linkedList.addFirst(new Student((long)(N+2), "New")));
        long removeLastLL  = measureTime(linkedList::removeLast);
        long removeFirstLL = measureTime(linkedList::removeFirst);
        long getMidLL      = measureTime(() -> linkedList.get(N/2));      // O(n) — медленно!
        long getLastLL     = measureTime(linkedList::getLast);

        //HashSet
        HashSet<Student> hashSet = new HashSet<>();
        long fillHS = measureTime(() -> fillCollection(hashSet));

        long addHS         = measureTime(() -> hashSet.add(new Student((long)(N+1), "New")));
        long removeHS      = measureTime(() -> hashSet.remove(new Student((long)(N-1), "Old")));
        long containsHS    = measureTime(() -> hashSet.contains(new Student((long)(N/2), "Mid")));

        //HashMap
        HashMap<Long, Student> hashMap = new HashMap<>();
        long fillHM = measureTime(() -> fillMap(hashMap));

        long putHM    = measureTime(() -> hashMap.put((long)(N+1), new Student((long)(N+1), "New")));
        long removeHM = measureTime(() -> hashMap.remove((long)(N-1)));
        long getHM    = measureTime(() -> hashMap.get((long)(N/2)));

        //Вывод результатов
        System.out.println("Заполнение:");
        System.out.printf("ArrayList :  %,12d ns%n", fillAL);
        System.out.printf("LinkedList:  %,12d ns%n", fillLL);
        System.out.printf("HashSet   :  %,12d ns%n", fillHS);
        System.out.printf("HashMap   :  %,12d ns%n%n", fillHM);

        System.out.println("Операции:");
        System.out.printf("Add end    — ArrayList: %,10d ns | LinkedList: %,10d ns%n", addEndAL, addEndLL);
        System.out.printf("Add begin  — ArrayList: %,10d ns | LinkedList: %,10d ns%n", addBeginAL, addBeginLL);
        System.out.printf("Remove last— ArrayList: %,10d ns | LinkedList: %,10d ns%n", removeLastAL, removeLastLL);
        System.out.printf("Remove first—ArrayList: %,10d ns | LinkedList: %,10d ns%n", removeFirstAL, removeFirstLL);
        System.out.printf("Get middle — ArrayList: %,10d ns | LinkedList: %,10d ns%n", getMidAL, getMidLL);
        System.out.printf("HashSet add/remove/contains: %,d / %,d / %,d ns%n", addHS, removeHS, containsHS);
        System.out.printf("HashMap put/remove/get: %,d / %,d / %,d ns%n", putHM, removeHM, getHM);
    }

    /** Универсальный метод замера времени */
    private static long measureTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }

    /** Универсальное заполнение для List/Set */
    private static void fillCollection(Collection<Student> collection) {
        for (int i = 0; i < N; i++) {
            collection.add(new Student((long)i, "S" + i));
        }
    }

    /** Заполнение HashMap */
    private static void fillMap(HashMap<Long, Student> map) {
        for (int i = 0; i < N; i++) {
            map.put((long)i, new Student((long)i, "S" + i));
        }
    }
}