/*
Задание:
    Реализовать «Многослойная очередь» (очередь очередей), которая
    поддерживает приоритетный режим обработки элементов.
    Прокомментировать код.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiLayeredQueue {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите количество уровней очереди (0 - самый высокий приоритет): ");
        int levels = scan.nextInt();

        PriorityQueueOfQueues multiQueue = new PriorityQueueOfQueues(levels);

        System.out.print("Сколько операций выполнить: ");
        int count = scan.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Введите тип операции (1 - добавить, 2 - обработать): ");
            int op = scan.nextInt();

            if (op == 1) {
                System.out.print("Введите значение элемента: ");
                int value = scan.nextInt();

                System.out.print("Введите приоритет (0.."
                        + (levels - 1) + " , где 0 - самый высокий): ");
                int priority = scan.nextInt();

                multiQueue.enqueue(value, priority);

            } else if (op == 2) {
                Integer processed = multiQueue.dequeue();
                if (processed == null) {
                    System.out.println("Все очереди пустые, обрабатывать нечего");
                } else {
                    System.out.println("Обработан элемент: " + processed);
                }
            }
        }

        scan.close();
    }
}


class PriorityQueueOfQueues {

    // Список очередей для приоритетов
    private List<Queue<Integer>> queues;

    // создаём нужное количество уровней
    public PriorityQueueOfQueues(int levels) {
        queues = new ArrayList<>(levels);

        // КАждая очередь, как LinkedList
        for (int i = 0; i < levels; i++) {
            queues.add(new LinkedList<>());
        }
    }

    /*
    Проверяем приоритет на правильность, если все ок,
    то добавляем значение в очередь в соотвествии с приоритетом
     */
    public void enqueue(int value, int priority) {

        if (priority < 0 || priority >= queues.size()) {
            System.out.println("Неверный приоритет: " + priority);
            return;
        }

        queues.get(priority).add(value);
        System.out.println("Элемент " + value + " добавлен в очередь с приоритетом " + priority);
    }

    /*
    Перебираем очереди по приоритету. Первый не null элемент возвращаем.
    Если очереди уже пустые, то вернем null
     */
    public Integer dequeue() {

        for (int i = 0; i < queues.size(); i++) {
            Queue<Integer> q = queues.get(i);
            if (!q.isEmpty()) {
                return q.poll();
            }
        }

        return null;
    }
}
