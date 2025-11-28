/**
 * Реализовать «Многослойная очередь» (очередь очередей), которая
 * поддерживает приоритетный режим обработки элементов.
 * Прокомментировать код.
 */
package tasks.task1;

public class TaskB1 {
    public static void taskB1() {
        MultiLayerQueue<String> mlq = new MultiLayerQueue<>();

        // Добавляем элементы с разными приоритетами
        mlq.enqueue("Задача высокого приоритета 1", 1);
        mlq.enqueue("Задача среднего приоритета 1", 3);
        mlq.enqueue("Задача высокого приоритета 2", 1);
        mlq.enqueue("Задача низкого приоритета 1", 5);
        mlq.enqueue("Задача среднего приоритета 2", 3);
        mlq.enqueue("Задача высокого приоритета 3", 1);

        System.out.println("Исходное состояние очереди:");
        System.out.println(mlq);
        System.out.println("Общее количество элементов: " + mlq.size());
        System.out.println("Количество очередей: " + mlq.getQueueCount());

        System.out.println("\nОбработка элементов в порядке приоритета:");

        // Обрабатываем элементы
        while (!mlq.isEmpty()) {
            String task = mlq.dequeue();
            System.out.println("Обработано: " + task);
            System.out.println("Текущее состояние: " + mlq);
        }
    }
}
