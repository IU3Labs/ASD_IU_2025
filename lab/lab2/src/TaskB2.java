// 2 Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
//списка и хеш-таблицы. Прокомментировать код.

import models.LRUCache;

public class TaskB2 {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        System.out.println("1 = " + cache.get(1));
        System.out.println("2 = " + cache.get(2));
        System.out.println();

        cache.put(4, "four");
        System.out.println("4 = " + cache.get(4));
        System.out.println("size: " + cache.size());
        System.out.println();

        cache.put(2, "update");
        System.out.println("2 = " + cache.get(2));
        cache.put(5, "five");
        System.out.println("4 = " + cache.get(4));
        System.out.println("5 = " + cache.get(5));
        System.out.println("size: " + cache.size());
    }
}
