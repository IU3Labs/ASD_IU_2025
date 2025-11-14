public class LRUCacheTest {
    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        System.out.println("put + get");
        cache.put(1, 10);
        System.out.println(cache.get(1));

        System.out.println("get несуществующего ключа");
        System.out.println(cache.get(2));

        System.out.println("проверка lru");
        cache.put(2, 20);
        cache.put(3, 30);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

        System.out.println("обновление существующего");
        cache.put(2, 200);
        System.out.println(cache.get(2));

        System.out.println("capacity = 1");
        LRUCache cache2 = new LRUCache(1);
        cache2.put(1, 100);
        System.out.println(cache2.get(1));
        cache2.put(2, 200);
        System.out.println(cache2.get(1));
        System.out.println(cache2.get(2));

    }
}
