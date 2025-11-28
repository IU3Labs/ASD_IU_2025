/**
 * Реализовать «LRU Cache» — кэш с вытеснением на основе связанного
 * списка и хеш-таблицы. Прокомментировать код.
 */
package tasks.task2;

public class TaskB2 {
    public static void taskB2() {
        // Создаем LRU кэш вместимостью 3 элемента
        LRUCache cache = new LRUCache(3);

        System.out.println("---------- LRU Cache ----------");

        // Добавляем элементы в кэш
        System.out.println("\n1. Добавляем элементы:");
        cache.put(1, 100);
        System.out.println(cache.getCashSize());
        cache.put(2, 200);
        System.out.println(cache.getCashSize());
        cache.put(3, 300);
        System.out.println(cache.getCashSize());
        System.out.println("Добавлены: (1,100), (2,200), (3,300)");

        // Получаем существующие элементы
        System.out.println("\n2. Получаем элементы:");
        System.out.println("get(1) = " + cache.get(1)); // Должен вернуть 100
        System.out.println("get(2) = " + cache.get(2)); // Должен вернуть 200
        System.out.println("get(3) = " + cache.get(3)); // Должен вернуть 300

        // Пытаемся получить несуществующий элемент
        System.out.println("\n3. Пробуем получить несуществующий элемент:");
        System.out.println("get(4) = " + cache.get(4)); // Должен вернуть -1

        // Добавляем новый элемент - должен вытеснить наименее используемый
        System.out.println("\n4. Добавляем новый элемент (4,400):");
        cache.put(4, 400);
        System.out.println("Кэш переполнен, должен вытесниться наименее используемый элемент");

        // Проверяем, что произошло
        System.out.println("\n5. Проверяем состояние кэша:");
        System.out.println("get(1) = " + cache.get(1)); // Должен вернуть -1 (вытеснен)
        System.out.println("get(2) = " + cache.get(2)); // Должен вернуть 200
        System.out.println("get(3) = " + cache.get(3)); // Должен вернуть 300
        System.out.println("get(4) = " + cache.get(4)); // Должен вернуть 400

        // Обновляем существующий элемент
        System.out.println("\n6. Обновляем элемент (3,350):");
        cache.put(3, 350);
        System.out.println("get(3) = " + cache.get(3)); // Должен вернуть 350

        // Добавляем еще один элемент - проверяем вытеснение
        System.out.println("\n7. Добавляем элемент (5,500):");
        cache.put(5, 500);

        System.out.println("\n8. Финальное состояние кэша:");
        System.out.println("get(2) = " + cache.get(2)); // Должен вернуть -1 (вытеснен)
        System.out.println("get(4) = " + cache.get(4)); // Должен вернуть 400
        System.out.println("get(3) = " + cache.get(3)); // Должен вернуть 350
        System.out.println("get(5) = " + cache.get(5)); // Должен вернуть 500

    }
}
