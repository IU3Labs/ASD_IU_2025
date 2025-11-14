import java.util.*;

/*
 Реализовать «Многослойная очередь» (очередь очередей), которая
 поддерживает приоритетный режим обработки элементов.
 Прокомментировать код.

 Логика многослойной очереди:
 У нас есть несколько очередей (слоёв), каждый слой соответствует своему приоритету.
 0-й слой — самый высокий приоритет, далее 1, 2, и т.д.

 Внутри мы храним список списков:
  - outerList (priorityQueues) — это "очередь очередей"
  - каждый innerList — обычная очередь для одного приоритета
 Добавление:
  - можно добавлять в начало, середину или конец очереди конкретного приоритета
 Удаление:
  - обычный dequeue() всегда берёт элемент из самой приоритетной непустой очереди,
    то есть сначала проверяется слой 0, затем 1, 2, и т.д.
*/

public class Main1B{
    private List<List<Integer>> priorityQueues; // список очередей по приоритетам
    private int levels;                         // количество уровней приоритета

    public Main1B(int levels) {
        this.levels = levels;
        priorityQueues = new ArrayList<>();
        for (int i = 0; i < levels; i++) {
            priorityQueues.add(new ArrayList<>());
        }
    }

    // Проверка корректности приоритета
    private boolean isValidPriority(int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Некорректный приоритет: " + priority);
            return false;
        }
        return true;
    }

    // 1. Добавление в конец очереди указанного приоритета
    public void enqueueToEnd(int priority, int value) {
        if (!isValidPriority(priority)) return;
        List<Integer> layer = priorityQueues.get(priority);
        layer.add(value);
    }

    // 1. Добавление в начало очереди указанного приоритета
    public void enqueueToStart(int priority, int value) {
        if (!isValidPriority(priority)) return;
        List<Integer> layer = priorityQueues.get(priority);
        layer.add(0, value);
    }

    // 1. Добавление в середину очереди указанного приоритета
    public void enqueueToMiddle(int priority, int value) {
        if (!isValidPriority(priority)) return;
        List<Integer> layer = priorityQueues.get(priority);
        int middleIndex = layer.size() / 2;
        layer.add(middleIndex, value);
    }

    // 2. Удаление элемента по приоритету (из начала конкретного слоя)
    public int dequeueFromPriority(int priority) {
        if (!isValidPriority(priority)) return Integer.MIN_VALUE;
        List<Integer> layer = priorityQueues.get(priority);
        if (layer.isEmpty()) {
            System.out.println("Очередь с приоритетом " + priority + " пуста");
            return Integer.MIN_VALUE;
        }
        return layer.remove(0);
    }

    // 2. Удаление элемента с конца конкретного приоритета
    public int dequeueFromPriorityEnd(int priority) {
        if (!isValidPriority(priority)) return Integer.MIN_VALUE;
        List<Integer> layer = priorityQueues.get(priority);
        if (layer.isEmpty()) {
            System.out.println("Очередь с приоритетом " + priority + " пуста");
            return Integer.MIN_VALUE;
        }
        return layer.remove(layer.size() - 1);
    }

    // 2. Удаление элемента из середины конкретного приоритета
    public int dequeueFromPriorityMiddle(int priority) {
        if (!isValidPriority(priority)) return Integer.MIN_VALUE;
        List<Integer> layer = priorityQueues.get(priority);
        if (layer.isEmpty()) {
            System.out.println("Очередь с приоритетом " + priority + " пуста");
            return Integer.MIN_VALUE;
        }
        int middleIndex = layer.size() / 2;
        return layer.remove(middleIndex);
    }

    // 2. Удаление элемента с учетом приоритета (общий dequeue)
    // Берём первый элемент из самой приоритетной непустой очереди
    public int dequeue() {
        for (int p = 0; p < levels; p++) {
            List<Integer> layer = priorityQueues.get(p);
            if (!layer.isEmpty()) {
                int value = layer.remove(0);
                System.out.println("Удалён элемент " + value + " из очереди приоритета " + p);
                return value;
            }
        }
        System.out.println("Все очереди пусты");
        return Integer.MIN_VALUE;
    }

    // 3. Подсчёт общего числа элементов во всех слоях
    public int size() {
        int total = 0;
        for (List<Integer> layer : priorityQueues) {
            total += layer.size();
        }
        return total;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // 4. Печать в прямом порядке:
    // от более высокого приоритета к более низкому,
    // внутри — от начала очереди к концу
    public void printForward() {
        System.out.println("Многослойная очередь (прямой порядок):");
        for (int p = 0; p < levels; p++) {
            List<Integer> layer = priorityQueues.get(p);
            System.out.print("Приоритет " + p + ": ");
            if (layer.isEmpty()) {
                System.out.println("(пусто)");
            } else {
                for (int i = 0; i < layer.size(); i++) {
                    System.out.print(layer.get(i) + " ");
                }
                System.out.println();
            }
        }
    }

    // 4. Печать в обратном порядке:
    // от низкого приоритета к высокому,
    // внутри — с конца к началу
    public void printBackward() {
        System.out.println("Многослойная очередь (обратный порядок):");
        for (int p = levels - 1; p >= 0; p--) {
            List<Integer> layer = priorityQueues.get(p);
            System.out.print("Приоритет " + p + ": ");
            if (layer.isEmpty()) {
                System.out.println("(пусто)");
            } else {
                for (int i = layer.size() - 1; i >= 0; i--) {
                    System.out.print(layer.get(i) + " ");
                }
                System.out.println();
            }
        }
    }

    //  Размеры слоёв
    public void printLayersSize() {
        System.out.print("Размеры по приоритетам: ");
        for (int p = 0; p < levels; p++) {
            System.out.print("[" + p + ":" + priorityQueues.get(p).size() + "] ");
        }
        System.out.println();
    }

    // Демонстрация работы
    public static void main(String[] args) {
        System.out.println("Демонстрация многослойной очереди (очередь очередей)\n");

        // Создаем очередь с 3 уровнями приоритета: 0 - высокий, 1 - средний, 2 - низкий
        Main1B multiQueue = new Main1B(3);

        System.out.println("1.Добавление элементов в разные приоритеты и позиции:");

        multiQueue.enqueueToEnd(0, 10);   // высокий приоритет, в конец
        multiQueue.enqueueToEnd(1, 20);   // средний приоритет, в конец
        multiQueue.enqueueToEnd(2, 30);   // низкий приоритет, в конец

        multiQueue.enqueueToStart(0, 5);  // высокий приоритет, в начало
        multiQueue.enqueueToMiddle(1, 25); // средний приоритет, в середину
        multiQueue.enqueueToEnd(2, 35);   // низкий приоритет, в конец

        System.out.println("Добавлены элементы с приоритетами 0,1,2");
        multiQueue.printForward();
        multiQueue.printLayersSize();
        System.out.println("Общее количество элементов: " + multiQueue.size());

        System.out.println("\n2.Печать в обратном порядке:");
        multiQueue.printBackward();

        System.out.println("\n3.Удаление по конкретному приоритету (начало/середина/конец):");
        System.out.println("Удаление из начала приоритета 1: " + multiQueue.dequeueFromPriority(1));
        System.out.println("Удаление из конца приоритета 2: " + multiQueue.dequeueFromPriorityEnd(2));
        System.out.println("Удаление из середины приоритета 0: " + multiQueue.dequeueFromPriorityMiddle(0));

        multiQueue.printForward();
        multiQueue.printLayersSize();
        System.out.println("Общее количество элементов: " + multiQueue.size());

        System.out.println("\n4.Удаление с учетом приоритета (общий dequeue):");
        multiQueue.dequeue();
        multiQueue.dequeue();
        multiQueue.dequeue();

        multiQueue.printForward();
        System.out.println("Общее количество элементов: " + multiQueue.size());

        System.out.println("\n5.Попытка удалить из пустой многослойной очереди:");
        while (!multiQueue.isEmpty()) {
            multiQueue.dequeue();
        }
        multiQueue.dequeue();
    }
}
/*1.Добавление элементов в разные приоритеты и позиции:
Добавлены элементы с приоритетами 0,1,2
Многослойная очередь (прямой порядок):
Приоритет 0: 5 10
Приоритет 1: 25 20
Приоритет 2: 30 35
Размеры по приоритетам: [0:2] [1:2] [2:2]
Общее количество элементов: 6

2.Печать в обратном порядке:
Многослойная очередь (обратный порядок):
Приоритет 2: 35 30
Приоритет 1: 20 25
Приоритет 0: 10 5

3.Удаление по конкретному приоритету (начало/середина/конец):
Удаление из начала приоритета 1: 25
Удаление из конца приоритета 2: 35
Удаление из середины приоритета 0: 10
Многослойная очередь (прямой порядок):
Приоритет 0: 5
Приоритет 1: 20
Приоритет 2: 30
Размеры по приоритетам: [0:1] [1:1] [2:1]
Общее количество элементов: 3

 4.Удаление с учетом приоритета (общий dequeue):
Удалён элемент 5 из очереди приоритета 0
Удалён элемент 20 из очереди приоритета 1
Удалён элемент 30 из очереди приоритета 2
Многослойная очередь (прямой порядок):
Приоритет 0: (пусто)
Приоритет 1: (пусто)
Приоритет 2: (пусто)
Общее количество элементов: 0

5.Попытка удалить из пустой многослойной очереди:
Все очереди пусты
*/