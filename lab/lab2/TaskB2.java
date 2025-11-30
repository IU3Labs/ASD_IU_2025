import models.LRUCache;

public static void main(String[] args) {
    LRUCache<Integer, String> cache = new LRUCache<>(3);

    cache.put(1, "Apple");
    cache.put(2, "Banana");
    cache.put(3, "Cherry");
    cache.printCache(); // [3=Cherry] [2=Banana] [1=Apple]

    cache.get(1); // доступ к Apple
    cache.printCache(); // [1=Apple] [3=Cherry] [2=Banana]

    cache.put(4, "Date"); // добавляем новый элемент
    cache.printCache(); // [4=Date] [1=Apple] [3=Cherry] - Banana удален

    cache.put(3, "Cherry Updated"); // обновляем существующий
    cache.printCache(); // [3=Cherry Updated] [4=Date] [1=Apple]
}