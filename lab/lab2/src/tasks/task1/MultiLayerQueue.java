package tasks.task1;
import java.util.*;

public class MultiLayerQueue<T> {

    //Внутренний класс для представления узла очереди
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    //Внутренний класс для представления отдельной очереди
    private class Queue {
        private Node<T> front; // начало очереди
        private Node<T> rear;  // конец очереди
        private int size;      // количество элементов в очереди
        private int priority;  // приоритет очереди (чем меньше число, тем выше приоритет)

        Queue(int priority) {
            this.front = null;
            this.rear = null;
            this.size = 0;
            this.priority = priority;
        }

        //Добавление элемента в конец очереди
        void enqueue(T data) {
            Node<T> newNode = new Node<>(data);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        // Удаление элемента из начала очереди
        T dequeue() {
            if (isEmpty()) {
                return null;
            }

            T data = front.data;
            front = front.next;

            if (front == null) {
                rear = null;
            }

            size--;
            return data;
        }

        //Просмотр элемента в начале очереди без удаления
        T peek() {
            if (isEmpty()) {
                return null;
            }
            return front.data;
        }

        //Проверка, пуста ли очередь
        boolean isEmpty() {
            return front == null;
        }

        //Получение размера очереди
        int size() {
            return size;
        }

        //Получение приоритета очереди
        int getPriority() {
            return priority;
        }
    }


    private List<Queue> queues; // список очередей, отсортированный по приоритету
    private Map<Integer, Queue> priorityMap; // карта для быстрого доступа к очередям по приоритету
    private int totalSize; // общее количество элементов во всех очередях

    //Конструктор многослойной очереди
    public MultiLayerQueue() {
        this.queues = new ArrayList<>();
        this.priorityMap = new HashMap<>();
        this.totalSize = 0;
    }


     //Добавление элемента в очередь с указанным приоритетом
     //Если очередь с таким приоритетом не существует, она создается
    public void enqueue(T data, int priority) {
        Queue queue = priorityMap.get(priority);

        // Если очередь с таким приоритетом не существует, создаем новую
        if (queue == null) {
            queue = new Queue(priority);
            priorityMap.put(priority, queue);
            queues.add(queue);
            // Сортируем очереди по приоритету (от высшего к низшему)
            queues.sort(Comparator.comparingInt(Queue::getPriority));
        }

        queue.enqueue(data);
        totalSize++;
    }

     //Удаление и возврат элемента с наивысшим приоритетом
     //Элементы обрабатываются в порядке приоритета очередей
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        // Ищем первую непустую очередь (с наивысшим приоритетом)
        for (Queue queue : queues) {
            if (!queue.isEmpty()) {
                T data = queue.dequeue();
                totalSize--;

                // Если очередь стала пустой, удаляем ее из списка
                if (queue.isEmpty()) {
                    queues.remove(queue);
                    priorityMap.remove(queue.getPriority());
                }

                return data;
            }
        }

        return null;
    }

   //Просмотр элемента с наивысшим приоритетом без удаления
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        // Ищем первую непустую очередь
        for (Queue queue : queues) {
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }

        return null;
    }

    //Проверка, пуста ли вся многослойная очередь
    public boolean isEmpty() {
        return totalSize == 0;
    }

    //Получение общего количества элементов во всех очередях
    public int size() {
        return totalSize;
    }

    //Получение количества очередей в системе
    public int getQueueCount() {
        return queues.size();
    }

    //Получение размера конкретной очереди по приоритету
    public int getQueueSize(int priority) {
        Queue queue = priorityMap.get(priority);
        return queue != null ? queue.size() : 0;
    }

    //Получение списка всех приоритетов, отсортированных по возрастанию
    public List<Integer> getPriorities() {
        List<Integer> priorities = new ArrayList<>(priorityMap.keySet());
        Collections.sort(priorities);
        return priorities;
    }

    //Очистка всей многослойной очереди
    public void clear() {
        queues.clear();
        priorityMap.clear();
        totalSize = 0;
    }

    //Строковое представление многослойной очереди
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MultiLayerQueue {\n");

        for (Queue queue : queues) {
            sb.append("  Priority ").append(queue.getPriority())
                    .append(" (").append(queue.size()).append(" elements): ");

            Node<T> current = queue.front;
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(" -> ");
                }
                current = current.next;
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }
}
