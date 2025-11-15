package tasks;

import tasks.LRUCache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        System.out.println("LRU CACHE DEMO\n");

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.print("Добавили 1,2,3: ");
        cache.printForward();

        cache.get(2);
        System.out.print("После get(2): ");
        cache.printForward();

        cache.get(1);
        System.out.print("После get(1): ");
        cache.printForward();

        cache.put(4, "D");
        System.out.print("После put(4) - вытеснен 3: ");
        cache.printForward();

        cache.put(5, "E");
        System.out.print("После put(5) - вытеснен 2: ");
        cache.printForward();

        System.out.println("\nВнутреннее состояние:");
        cache.printInternalState();
    }
}