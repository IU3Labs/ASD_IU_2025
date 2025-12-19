
public class QueueMultilayer {

    // Узел (элемент очереди)
    private static class Link {
        String info;
        Link nextLink;

        Link(String info) {
            this.info = info;
        }
    }

    // Обычная очередь
    private static class SimpleQueue {
        private Link first; // начало очереди
        private Link last;  // конец очереди

        // Добавление в конец
        void pushBack(String val) {
            Link newLink = new Link(val);
            if (last == null) {
                first = newLink;
                last = newLink;
            } else {
                last.nextLink = newLink;
                last = newLink;
            }
        }

        // Удаление из начала
        String popFront() {
            if (first == null) return null;
            String val = first.info;
            first = first.nextLink;
            if (first == null) last = null;
            return val;
        }

        // Просмотр первого элемента
        String peekFront() {
            return (first == null) ? null : first.info;
        }

        // Проверка на пустоту
        boolean hasItems() {
            return first != null;
        }

        // Печать очереди
        void printAll() {
            Link cur = first;
            while (cur != null) {
                System.out.print(cur.info);
                if (cur.nextLink != null) System.out.print(", ");
                cur = cur.nextLink;
            }
            System.out.print(".");
        }
    }

    // Многослойная очередь
    private SimpleQueue[] priorityGroups; // массив очередей (по приоритетам)
    private int maxPriorities;

    public QueueMultilayer(int maxPriorities) {
        this.maxPriorities = maxPriorities;
        priorityGroups = new SimpleQueue[maxPriorities];
        for (int i = 0; i < maxPriorities; i++) {
            priorityGroups[i] = new SimpleQueue();
        }
    }

    // Добавление с приоритетом
    public void addTask(String task, int priority) {
        if (priority < 0 || priority >= maxPriorities) {
            System.out.println("Ошибка: неверный приоритет " + priority);
            return;
        }
        priorityGroups[priority].pushBack(task);
    }

    // Удаление с наивысшим приоритетом
    public String completeHighestPriorityTask() {
        for (SimpleQueue group : priorityGroups) {
            if (group.hasItems()) {
                return group.popFront();
            }
        }
        return null;
    }

    // Просмотр первого элемента по приоритету
    public String peekHighestPriorityTask() {
        for (SimpleQueue group : priorityGroups) {
            if (group.hasItems()) {
                return group.peekFront();
            }
        }
        return null;
    }

    // Печать состояния всей очереди
    public void displayAll() {
        System.out.println("Состояние многослойной очереди:");
        for (int i = 0; i < maxPriorities; i++) {
            System.out.print("Приоритет " + i + ": ");
            priorityGroups[i].printAll();
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueMultilayer taskManager = new QueueMultilayer(5);

        taskManager.addTask("Сдать курсовую работу", 0);
        taskManager.addTask("Подготовиться к зачёту", 0);
        taskManager.addTask("Прочитать главы к семинару", 1);
        taskManager.addTask("Постирать вещи", 2);
        taskManager.addTask("Посмотреть фильм", 3);

        System.out.println("Исходный список задач:");
        taskManager.displayAll();

        System.out.println("Первое дело по приоритету: " + taskManager.peekHighestPriorityTask());

        System.out.println("\nВыполнено: " + taskManager.completeHighestPriorityTask());
        System.out.println("Выполнено: " + taskManager.completeHighestPriorityTask());

        System.out.println("\nСписок задач после выполнения двух первых дел:");
        taskManager.displayAll();
    }
}