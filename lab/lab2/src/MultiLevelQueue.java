//Реализовать «Многослойная очередь» (очередь очередей), которая
//поддерживает приоритетный режим обработки элементов.
//Прокомментировать код.

public class MultiLevelQueue {

    //реализуем обычную очередь через массив
    private static class Queue {
        private int[] elements;
        private int size;

        public Queue() {
            elements = new int[10];
            size = 0;
        }

        // Добавление в конец
        public void addEnd(int element) {
            if (size == elements.length) {
                resize();
            }
            elements[size] = element;
            size++;
        }

        // Удаление начального элемента
        public int deleteStart() {
            int firstElement = (int) elements[0];

            // Сдвигаем все элементы на одну позицию вперед
            for (int i = 0; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }

            elements[size - 1] = 0; // очищаем последний элемент
            size--;
            return firstElement;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // Просмотр первого элемента
        public int firstElement() {
            return elements[0];
        }

        // Увеличиваем массив, если он заполнился
        private void resize() {
            int[] newElements = new int[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        private void printQueue() {
            for (int i = 0; i < size; i++) {
                System.out.print(elements[i] + " ");
            }
        }
    }

    private final Queue[] multiQueue; // массив очередей
    private final int priorities;     // количество приоритетов
    private int size;  // общее число элементов

    public MultiLevelQueue(int countPriorities) {
        priorities = countPriorities;
        multiQueue = new Queue[countPriorities];
        for (int i = 0; i < countPriorities; i++) {
            multiQueue[i] = new Queue();
        }
        size = 0;
    }

    // Добавление элемента по его приоритету
    public void addEnd(int element, int priority) {
        multiQueue[priority].addEnd(element);
        size++;
    }

    // Удаление элемента с наивысшим приоритетом
    public int removeQueue() {
        if (size == 0) return 0;
        for (int i = 0; i < priorities; i++) {
            if (!multiQueue[i].isEmpty()) {
                size--;
                return multiQueue[i].deleteStart();
            }
        }
        return 0;
    }

    // Просмотр первого элемента
    public int firstQueue() {
        for (int i = 0; i < priorities; i++) {
            if (!multiQueue[i].isEmpty()) {
                return multiQueue[i].firstElement();
            }
        }
        return 0;
    }

    // Вывод многослойной очереди
    public void printMultiQueue() {
        System.out.println("Многослойная очередь:");
        for (int i = 0; i < priorities; i++) {
            System.out.print("Приоритет " + i + ": ");
            multiQueue[i].printQueue();
            System.out.println();
        }
        System.out.println();
    }


    public static void main() {
        MultiLevelQueue testQueue = new MultiLevelQueue(5);

        testQueue.addEnd(2, 0);
        testQueue.addEnd(2, 0);
        testQueue.addEnd(5, 1);
        testQueue.addEnd(9, 2);
        testQueue.addEnd(7, 3);
        testQueue.addEnd(100, 4);
        testQueue.addEnd(121, 4);

        testQueue.printMultiQueue();

        System.out.println("Первое число по приоритету: " + testQueue.firstQueue());

        System.out.println("Удаляем число: " + testQueue.removeQueue());

        testQueue.printMultiQueue();
    }

}

//Многослойная очередь:
//Приоритет 0: 2 2
//Приоритет 1: 5
//Приоритет 2: 9
//Приоритет 3: 7
//Приоритет 4: 100 121
//
//Первое число по приоритету: 2
//Удаляем число: 2
//Многослойная очередь:
//Приоритет 0: 2
//Приоритет 1: 5
//Приоритет 2: 9
//Приоритет 3: 7
//Приоритет 4: 100 121