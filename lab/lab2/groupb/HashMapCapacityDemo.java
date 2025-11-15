package groupb;

import java.util.HashMap;
import java.util.Map;

public class HashMapCapacityDemo {
    public static void main(String[] args) {
        System.out.println("=== Исследование capacity HashMap ===");

        //HashMap с разными capacity
        Map<Integer, String> map1 = new HashMap<>(16);
        Map<Integer, String> map2 = new HashMap<>(64);
        Map<Integer, String> map3 = new HashMap<>(2);

        // производительность добавления
        testMapPerformance(map1, "HashMap(16)", 1000);
        testMapPerformance(map2, "HashMap(64)", 1000);
        testMapPerformance(map3, "HashMap(2)", 1000);

        // как capacity влияет на коллизии
        demonstrateCollisions();
    }

    public static void testMapPerformance(Map<Integer, String> map, String mapName, int elements) {
        long startTime = System.nanoTime();

        for (int i = 0; i < elements; i++) {
            map.put(i, "Value_" + i);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.printf("%s: добавление %d элементов заняло %,d нс%n",
                mapName, elements, duration);
    }

    public static void demonstrateCollisions() {
        System.out.println("\n=== Демонстрация коллизий ===");

        // Создаем HashMap с очень маленькой capacity
        Map<String, Integer> smallMap = new HashMap<>(4);

        // Добавляем элементы которые вызовут коллизии
        smallMap.put("key1", 1);
        smallMap.put("key5", 5);  // Коллизия если hash % 4 одинаковый
        smallMap.put("key9", 9);  // Коллизия
        smallMap.put("key2", 2);
        smallMap.put("key6", 6);  // Коллизия

        System.out.println("Маленькая HashMap (capacity=4): " + smallMap);

        //с оптимальной capacity
        Map<String, Integer> optimalMap = new HashMap<>(32);
        optimalMap.put("key1", 1);
        optimalMap.put("key5", 5);
        optimalMap.put("key9", 9);
        optimalMap.put("key2", 2);
        optimalMap.put("key6", 6);

        System.out.println("Оптимальная HashMap (capacity=32): " + optimalMap);
    }

    public static void showCapacityGrowth() {
        System.out.println("\n=== Рост capacity ===");

        Map<Integer, String> map = new HashMap<>(8);
        System.out.println("Начальная capacity: ~8 (по умолчанию 16 при 0.75 load factor)");

        for (int i = 0; i < 20; i++) {
            map.put(i, "value_" + i);
            if (i == 6 || i == 12 || i == 19) {
                System.out.printf("После %d элементов: load factor = %.2f%n",
                        i + 1, (double)(i + 1) / 16);
            }
        }

        System.out.println("При достижении load factor 0.75 capacity удваивается!");
    }
}