import java.util.*;


public class MultiLevelQueue<T> {
    private final List<Queue<T>> queues;
    private final int levels;

    public MultiLevelQueue(int levels) {
        this.levels = levels;
        this.queues = new ArrayList<>();
        for (int i = 0; i < levels; i++) {
            queues.add(new LinkedList<>());
        }
    }


    //Добавляем элемент в конец очереди с указанным приоритетом.

    public void addToEnd(T data, int priority) {
        if (priority < 0 || priority >= levels) {
            return;
        }
        queues.get(priority).add(data);
    }

     //Добавляем элемент в начало очереди с указанным приоритетом.

    public void addToStart(T data, int priority) {
        if (priority < 0 || priority >= levels) {
            return;
        }
        LinkedList<T> list = (LinkedList<T>) queues.get(priority);
        list.addFirst(data);
    }


     // Добавляем элемент в середину очереди с указанным приоритетом.

    public void addToMiddle(T data, int priority) {
        if (priority < 0 || priority >= levels) {
            return;
        }
        Queue<T> queue = queues.get(priority);
        List<T> tempList = new LinkedList<>(queue);
        int middle = tempList.size() / 2;
        tempList.add(middle, data);
        queue.clear();
        queue.addAll(tempList);
    }


     //Удаляем и возвращаем элемент с наивысшим приоритетом.

    public T remove() {
        for (int i = 0; i < levels; i++) {
            Queue<T> queue = queues.get(i);
            if (!queue.isEmpty()) {
                return queue.poll();
            }
        }
        return null;
    }


     // Возвращаем общее количество элементов во всех очередях.

    public int countElements() {
        int count = 0;
        for (Queue<T> queue : queues) {
            count += queue.size();
        }
        return count;
    }


     // Выводим все очереди в прямом порядке приоритетов.

    public void printForward() {
        for (int i = 0; i < levels; i++) {
            System.out.print("Уровень " + i + ": ");
            for (T element : queues.get(i)) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }


     // Выводим все очереди в обратном порядке приоритетов.

    public void printBackward() {
        for (int i = levels - 1; i >= 0; i--) {
            System.out.print("Уровень " + i + ": ");
            List<T> list = new ArrayList<>(queues.get(i));
            Collections.reverse(list);
            for (T element : list) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }


     // Демонстрация работы многослойной очереди.

    public static void main(String[] args) {
        MultiLevelQueue<String> queue = new MultiLevelQueue<>(3);

        queue.addToEnd("A1", 2);
        queue.addToStart("B1", 0);
        queue.addToMiddle("C1", 1);
        queue.addToEnd("D1", 0);
        queue.addToStart("E1", 1);

        System.out.println("Прямой порядок:");
        queue.printForward();

        System.out.println("\nОбратный порядок:");
        queue.printBackward();

        System.out.println("\nВсего элементов: " + queue.countElements());

        System.out.println("\nУдаленные элементы:");
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.remove());
        }

        System.out.println("\nПосле удаления:");
        queue.printForward();
        System.out.println("Всего элементов: " + queue.countElements());
    }
}