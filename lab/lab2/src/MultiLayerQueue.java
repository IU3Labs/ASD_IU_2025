import java.util.*;

//1 Реализовать «Многослойная очередь» (очередь очередей), которая
//поддерживает приоритетный режим обработки элементов.
//Прокомментировать код.
public class MultiLayerQueue {
    private List<Queue<Integer>> queues;  // Список очередей по приоритетам
    private int levels;                   // Количество уровней приоритета

    public MultiLayerQueue(int levels) {
        this.levels = levels;
        queues = new ArrayList<>();

        // Создаем очереди для каждого уровня приоритета
        for (int i = 0; i < levels; i++) {
            queues.add(new LinkedList<>());
        }
    }

    //    Добавление элемента в очередь с указанным приоритетом
//    item - элемент
//    priority - приоритет (0 - высший, levels-1 - низший)
    public void enqueue(int item, int priority) {
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Неверный приоритет: " + priority);
        }
        queues.get(priority).offer(item);
    }

    //      Удаление и возврат элемента с наивысшим приоритетом
//      Сначала проверяем очередь 0, потом 1, потом 2 и т.д.
    public Integer dequeue() {
        // Ищем первую непустую очередь с наивысшим приоритетом
        for (int i = 0; i < levels; i++) {
            Queue<Integer> queue = queues.get(i);
            if (!queue.isEmpty()) {
                return queue.poll();
            }
        }
        return null; // Все очереди пусты
    }

    //    Просмотр элемента с наивысшим приоритетом без удаления
    public Integer peek() {
        for (int i = 0; i < levels; i++) {
            Queue<Integer> queue = queues.get(i);
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }
        return null;
    }

    //    Общее количество элементов во всех очередях
    public int size() {
        int total = 0;
        for (Queue<Integer> queue : queues) {
            total += queue.size();
        }
        return total;
    }

    //    Количество элементов в очереди с указанным приоритетом
    public int size(int priority) {
        return queues.get(priority).size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    //    Печать всех очередей в прямом порядке
    public void printForward() {
        System.out.println("Многоуровневая очередь (прямой порядок):");
        for (int i = 0; i < levels; i++) {
            System.out.println("Приоритет " + i + ": " + queues.get(i));
        }
    }

    //    Печать всех очередей в обратном порядке
    public void printBackward() {
        System.out.println("Многоуровневая очередь (обратный порядок):");
        for (int i = levels - 1; i >= 0; i--) {
            List<Integer> reversed = new ArrayList<>(queues.get(i));
            Collections.reverse(reversed);
            System.out.println("Приоритет " + i + ": " + reversed);
        }
    }

    // Демонстрация работы
    public static void main(String[] args) {
        System.out.println("Демонстрация работы");

        // Создаем очередь с 3 уровнями приоритета
        MultiLayerQueue queue = new MultiLayerQueue(3);

        // Добавляем элементы с разными приоритетами
        System.out.println("1. Добавляем элементы:");
        queue.enqueue(10, 2);  // Низкий приоритет
        queue.enqueue(20, 1);  // Средний приоритет
        queue.enqueue(30, 0);  // Высший приоритет
        queue.enqueue(40, 0);  // Высший приоритет
        queue.enqueue(50, 1);  // Средний приоритет

        queue.printForward();
        System.out.println("Всего элементов: " + queue.size());

        // Обрабатываем элементы (должны идти по приоритету)
        System.out.println("2. Обработка элементов по приоритету:");
        while (!queue.isEmpty()) {
            int item = queue.dequeue();
            System.out.println("Обработан: " + item + ", следующий: " + queue.peek());
        }

        // Еще одна демонстрация
        System.out.println("3. Демонстрация приоритетов:");
        queue.enqueue(100, 2); // Низкий
        queue.enqueue(200, 0); // Высший
        queue.enqueue(300, 1); // Средний
        queue.enqueue(400, 0); // Высший

        queue.printForward();

        System.out.println("Обработка:");
        System.out.println("Первый: " + queue.dequeue()); // 200 (высший)
        System.out.println("Второй: " + queue.dequeue()); // 400 (высший)
        System.out.println("Третий: " + queue.dequeue()); // 300 (средний)
        System.out.println("Четвертый: " + queue.dequeue()); // 100 (низкий)

        // Печать в обратном порядке
        System.out.println("4. Печать в обратном порядке:");
        queue.enqueue(1, 0);
        queue.enqueue(2, 1);
        queue.enqueue(3, 2);
        queue.printBackward();
    }
}