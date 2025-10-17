import java.util.*;

public class MainTask {

    // Простой класс Learner с двумя полями
    public static class Learner {
        Long id;
        String name;

        public Learner(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private static final int SIZE = 1_000_000;

    public static void main(String[] args) {

        ArrayList<Learner> arrayList = new ArrayList<>();
        LinkedList<Learner> linkedList = new LinkedList<>();
        HashSet<Learner> hashSet = new HashSet<>();
        HashMap<Long, Learner> hashMap = new HashMap<>();

        for (long i = 0; i < SIZE; i++) {
            Learner learner = new Learner(i, "Learner_" + i);
            arrayList.add(learner);
            linkedList.add(learner);
            hashSet.add(learner);
            hashMap.put(i, learner);
        }

        System.out.println("=== ArrayList ===");
        testList(arrayList);

        System.out.println("\n=== LinkedList ===");
        testList(linkedList);

        System.out.println("\n=== HashSet ===");
        testSet(hashSet);

        System.out.println("\n=== HashMap ===");
        testMap(hashMap);
    }

    private static long time(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }

    private static void testList(List<Learner> list) {
        System.out.println("Добавление в конец: " + time(() -> list.add(new Learner(1_000_001L, "New"))));
        System.out.println("Добавление в начало: " + time(() -> list.add(0, new Learner(1_000_002L, "New"))));
        System.out.println("Удаление последнего: " + time(() -> list.remove(list.size() - 1)));
        System.out.println("Удаление первого: " + time(() -> list.remove(0)));
        System.out.println("Получение среднего: " + time(() -> list.get(SIZE / 2)));
        System.out.println("Получение последнего: " + time(() -> list.get(SIZE - 1)));
    }

    private static void testSet(Set<Learner> set) {
        Learner a = new Learner(1_000_001L, "X");
        Learner b = new Learner(1_000_002L, "Y");

        System.out.println("Добавление элемента: " + time(() -> set.add(a)));
        System.out.println("Добавление второго: " + time(() -> set.add(b)));
        System.out.println("Удаление элемента: " + time(() -> set.remove(a)));
        System.out.println("Удаление второго: " + time(() -> set.remove(b)));
        System.out.println("Проверка среднего: " + time(() -> set.contains(a)));
        System.out.println("Проверка последнего: " + time(() -> set.contains(b)));
    }

    private static void testMap(Map<Long, Learner> map) {
        System.out.println("Добавление записи: " + time(() -> map.put(1_000_001L, new Learner(1_000_001L, "Z"))));
        System.out.println("Добавление второй записи: " + time(() -> map.put(1_000_002L, new Learner(1_000_002L, "Q"))));
        System.out.println("Удаление записи: " + time(() -> map.remove(1_000_001L)));
        System.out.println("Удаление второй записи: " + time(() -> map.remove(1_000_002L)));
        System.out.println("Получение среднего элемента: " + time(() -> map.get(500_000L)));
        System.out.println("Получение последнего элемента: " + time(() -> map.get(999_999L)));
    }
}

/*
=== РЕЗУЛЬТАТЫ ПРОВЕРОК ===

=== ArrayList ===
Добавление в конец: 9200
Добавление в начало: 274100
Удаление последнего: 7000
Удаление первого: 464700
Получение среднего: 4400
Получение последнего: 2800

=== LinkedList ===
Добавление в конец: 2000
Добавление в начало: 9600
Удаление последнего: 4500
Удаление первого: 1000
Получение среднего: 3900500
Получение последнего: 4500

=== HashSet ===
Добавление элемента: 7000
Добавление второго: 3100
Удаление элемента: 7600
Удаление второго: 2700
Проверка среднего: 4200
Проверка последнего: 2700

=== HashMap ===
Добавление записи: 6800
Добавление второй записи: 2200
Удаление записи: 6300
Удаление второй записи: 2700
Получение среднего элемента: 9000
Получение последнего элемента: 3100
*/
