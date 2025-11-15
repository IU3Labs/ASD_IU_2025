/*
1. Реализовать «Многослойную очередь» (очередь очередей) с приоритетной обработкой элементов.

ЛОГИКА:
- Есть несколько уровней приоритета (0 — самый высокий).
- Каждый уровень — это своя очередь (FIFO).
- Добавляем задачу в нужный слой по приоритету.
- Удаляем и смотрим задачи, начиная с самого высокого приоритета.
*/

public class QueueMultilayer {

    // ===== Узел (элемент очереди) =====
    private static class Node {
        String data;   // данные элемента
        Node next;     // ссылка на следующий элемент

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    // ===== Обычная очередь =====
    private static class Queue {
        private Node start; // начало очереди
        private Node end;   // конец очереди
        private int size;   // количество элементов

        Queue() {
            start = null;
            end = null;
            size = 0;
        }

        // Добавление в конец
        void addEnd(String data) {
            Node node = new Node(data);
            if (end == null) {
                start = node;
                end = node;
            } else {
                end.next = node;
                end = node;
            }
            size++;
        }

        // Удаление из начала
        String deleteStart() {
            if (isEmpty()) return null;
            String data = start.data;
            start = start.next;
            if (start == null) end = null;
            size--;
            return data;
        }

        // Просмотр первого элемента
        String checkStart() {
            if (isEmpty()) return null;
            return start.data;
        }

        // Проверка на пустоту
        boolean isEmpty() {
            return start == null;
        }

        // Печать очереди
        void printQueue() {
            if (isEmpty()) {
                System.out.print("(пусто)");
                return;
            }
            Node temp = start;
            while (temp != null) {
                System.out.print(temp.data);
                if (temp.next != null) System.out.print(", ");
                temp = temp.next;
            }
            System.out.print(".");
        }

        int size() {
            return size;
        }
    }

    // ===== МНОГОСЛОЙНАЯ ОЧЕРЕДЬ =====
    private Queue[] layers; // массив очередей (по приоритетам)
    private int levels;     // количество уровней
    private int totalSize;  // общее число элементов

    public QueueMultilayer(int levels) {
        this.levels = levels;
        layers = new Queue[levels];
        for (int i = 0; i < levels; i++) {
            layers[i] = new Queue();
        }
        totalSize = 0;
    }

    // Добавление с приоритетом
    public void addEnd(String element, int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Ошибка: неверный приоритет " + priority);
            return;
        }
        layers[priority].addEnd(element);
        totalSize++;
    }

    // Удаление с наивысшим приоритетом
    public String deleteStart() {
        if (totalSize == 0) return null;
        for (int i = 0; i < levels; i++) {
            if (!layers[i].isEmpty()) {
                totalSize--;
                return layers[i].deleteStart();
            }
        }
        return null;
    }

    // Просмотр первого элемента по приоритету
    public String checkStart() {
        for (int i = 0; i < levels; i++) {
            if (!layers[i].isEmpty()) {
                return layers[i].checkStart();
            }
        }
        return null;
    }

    // Печать состояния всей очереди
    public void printQueues() {
        System.out.println("Состояние многослойной очереди:");
        for (int i = 0; i < levels; i++) {
            System.out.print("Приоритет " + i + ": ");
            layers[i].printQueue();
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        QueueMultilayer studentTasks = new QueueMultilayer(5);

        studentTasks.addEnd("Сдать курсовую работу", 0);
        studentTasks.addEnd("Подготовиться к зачёту", 0);
        studentTasks.addEnd("Прочитать главы к семинару", 1);
        studentTasks.addEnd("Постирать вещи", 2);
        studentTasks.addEnd("Посмотреть фильм", 3);
        studentTasks.addEnd("Поиграть во что-нибудь", 3);
        studentTasks.addEnd("Навести порядок на рабочем столе", 4);

        System.out.println("Исходный список задач:");
        studentTasks.printQueues();

        System.out.println("Первое дело по приоритету: " + studentTasks.checkStart());

        System.out.println("\nВыполнено: " + studentTasks.deleteStart());
        System.out.println("Выполнено: " + studentTasks.deleteStart());

        System.out.println("\nСписок задач после выполнения двух первых дел:");
        studentTasks.printQueues();
    }

}
