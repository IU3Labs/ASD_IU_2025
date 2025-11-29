/*
Задание B1
Реализовать «Многослойную очередь» (очередь очередей) с приоритетной обработкой элементов.
Несколько уровней, самый важный - 0, каждый уровень это новая очередь FIFO, задача добавляется в нужный слой по приоритету, задачи выполняются по приоритету, а не времени поступления
*/

public class MultiLayeredQueue {


    private static class QueueElement {
        String content;
        QueueElement nextElement;

        QueueElement(String content) {
            this.content = content;
            this.nextElement = null;
        }
    }

    private static class SimpleQueue {
        private QueueElement firstElement;
        private QueueElement lastElement;
        private int elementCount;

        SimpleQueue() {
            firstElement = null;
            lastElement = null;
            elementCount = 0;
        }

        void enqueue(String content) {
            QueueElement newElement = new QueueElement(content);
            if (lastElement == null) {
                firstElement = newElement;
                lastElement = newElement;
            } else {
                lastElement.nextElement = newElement;
                lastElement = newElement;
            }
            elementCount++;
        }

        String dequeue() {
            if (isEmpty()) return null;
            String content = firstElement.content;
            firstElement = firstElement.nextElement;
            if (firstElement == null) lastElement = null;
            elementCount--;
            return content;
        }

        String peek() {
            if (isEmpty()) return null;
            return firstElement.content;
        }

        boolean isEmpty() {
            return firstElement == null;
        }

        void display() {
            if (isEmpty()) {
                System.out.print("пусто");
                return;
            }
            QueueElement current = firstElement;
            while (current != null) {
                System.out.print(current.content);
                if (current.nextElement != null) System.out.print(", ");
                current = current.nextElement;
            }
            System.out.print(".");
        }

        int getSize() {
            return elementCount;
        }
    }

    private SimpleQueue[] priorityLevels;
    private int totalLevels;
    private int totalElements;

    public MultiLayeredQueue(int totalLevels) {
        this.totalLevels = totalLevels;
        priorityLevels = new SimpleQueue[totalLevels];
        for (int i = 0; i < totalLevels; i++) {
            priorityLevels[i] = new SimpleQueue();
        }
        totalElements = 0;
    }

    public void addTask(String task, int priorityLevel) {
        if (priorityLevel < 0 || priorityLevel >= totalLevels) {
            System.out.println("Ошибка: неверный приоритет " + priorityLevel);
            return;
        }
        priorityLevels[priorityLevel].enqueue(task);
        totalElements++;
    }

    public String processNextTask() {
        if (totalElements == 0) return null;
        for (int i = 0; i < totalLevels; i++) {
            if (!priorityLevels[i].isEmpty()) {
                totalElements--;
                return priorityLevels[i].dequeue();
            }
        }
        return null;
    }

    public String viewNextTask() {
        for (int i = 0; i < totalLevels; i++) {
            if (!priorityLevels[i].isEmpty()) {
                return priorityLevels[i].peek();
            }
        }
        return null;
    }

    public void displayAllQueues() {
        System.out.println("Состояние многослойной очереди:");
        for (int i = 0; i < totalLevels; i++) {
            System.out.print("Приоритет " + i + ": ");
            priorityLevels[i].display();
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MultiLayeredQueue taskManager = new MultiLayeredQueue(5);

        taskManager.addTask("Подготовиться к сессии", 0);
        taskManager.addTask("Подготовиться к лабе", 0);
        taskManager.addTask("Написать конспект", 1);
        taskManager.addTask("Приготовить еду", 2);
        taskManager.addTask("Глянуть сериал", 3);
        taskManager.addTask("погулять", 3);
        taskManager.addTask("Сделать текучки", 4);

        System.out.println("Исходный список задач:");
        taskManager.displayAllQueues();

        System.out.println("Первое дело по приоритету: " + taskManager.viewNextTask());

        System.out.println("\nВыполнено: " + taskManager.processNextTask());
        System.out.println("Выполнено: " + taskManager.processNextTask());

        System.out.println("\nСписок задач после выполнения двух первых дел:");
        taskManager.displayAllQueues();
    }
}