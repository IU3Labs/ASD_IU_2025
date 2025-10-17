/*
Реализовать «Многослойная очередь» (очередь очередей), которая
поддерживает приоритетный режим обработки элементов.
Прокомментировать код.
 */
public class MultiQueue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    private static class Queue<T> {
        private Node<T> start;
        private Node<T> end;
        private int size;

        Queue() {
            start = null;
            end = null;
            size = 0;
        }
        // Добавление элемента в конец очереди
        void addEnd(T data) {
            Node<T> node = new Node<>(data);
            if (end == null) {
                start = node;
                end = node;
            } else {
                end.next = node;
                end = node;
            }
            size++;
        }
        // Удаление первого элемента очереди(проверяем на пустоту -> сохранем значение> сдвигаем начало > уменьшаем и возвращаем удаленный элемент если очередь стала пустой)
        T deleteStart() {
            if (isEmpty()) return null;
            T data = start.data;
            start = start.next;
            if (start == null) end = null;
            size--;
            return data;
        }
        // Просмотр первого элемента без удаления
        T checkStart() {
            if (isEmpty()) return null;
            return start.data;
        }
        boolean isEmpty() {
            return start == null;
        }
        void printQueue() {
            Node<T> current = start;
            for (int i = 0; i < size; i++) {
                System.out.print(current.data);
                if (i < size - 1) System.out.print(", ");
                else System.out.print(".");
                current = current.next;
            }
        }
        int size() {
            return size;
        }
    }
    private Queue<T>[] layers; // Массив очередей для каждого уровня приоритета
    private int levels;        // Количество уровней
    private int totalSize;     //  количество элементов
    public MultiQueue(int levels) {
        this.levels = levels;
        layers = new Queue[levels]; // Создаём массив очередей
        for (int i = 0; i < levels; i++) layers[i] = new Queue<>();
        totalSize = 0;
    }
    // Добавление элемента в очередь с заданным приоритетом
    public void addEnd(T element, int priority) {
        layers[priority].addEnd(element);
        totalSize++;
    }
    // Удаление элемента с наивысшим приоритетом
    public T deleteStart() {
        if (totalSize == 0) return null;
        for (int i = 0; i < levels; i++) {
            if (!layers[i].isEmpty()) {
                totalSize--;
                return layers[i].deleteStart();
            }
        }
        return null;
    }
    // Просмотр элемента с наивысшим приоритетом без удаления
    public T checkStart() {
        for (int i = 0; i < levels; i++) {
            if (!layers[i].isEmpty()) return layers[i].checkStart();
        }
        return null;
    }
    public void printQueues() {
        System.out.println("Состояние многослойной очереди:");
        for (int i = 0; i < levels; i++) {
            System.out.print("Слой " + i + ": ");
            layers[i].printQueue();
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        MultiQueue<String> tasks = new MultiQueue<>(5);
        tasks.addEnd("Купить хавку", 0);
        tasks.addEnd("Погладить мяускула", 0);
        tasks.addEnd("Сделать дз", 1);
        tasks.addEnd("Лечь спать", 4);
        tasks.addEnd("Сдать лабу", 1);
        tasks.addEnd("Похавать", 2);
        tasks.addEnd("Отдохнуть", 4);
        tasks.addEnd("Посмотреть ютубчик", 2);
        tasks.addEnd("Погамать контру", 3);
        System.out.println("Исходный список задач:");
        tasks.printQueues();
        System.out.println("Первое дело по приоритету: " + tasks.checkStart());
        System.out.println("\nВыполнено: " + tasks.deleteStart());
        System.out.println("Выполнено: " + tasks.deleteStart());
        System.out.println("\nСписок задач после выполнения двух первых дел:"); // Чисто для демонстрации работы
        tasks.printQueues();
    }
}
