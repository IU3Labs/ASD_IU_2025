//Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.
public class CircularQueue {
    private final int[] queue;
    private int headIndex;
    private int tailIndex;
    private int elementsCount;//текущее количество элементов
    private final int maxSize;//максимальное количество

    public CircularQueue(int queueSize) {
        maxSize = queueSize;
        queue = new int[queueSize];
        headIndex = 0;
        tailIndex = 0;
        elementsCount = 0;
    }

    public boolean isEmpty() {
        return elementsCount == 0;
    }

    public boolean isFull() {
        return elementsCount == maxSize;
    }

    public void addElement(int value) {
        if (isFull()) {
            queue[tailIndex] = value;
            tailIndex = (tailIndex + 1) % maxSize;
            headIndex = (headIndex + 1) % maxSize;
        }

        else{
            queue[tailIndex] = value;//добавляем элемент в текущую позицию
            tailIndex = (tailIndex + 1) % maxSize;//перемещаем нашу позицию
            elementsCount++;
        }
    }

    public void removeElement() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        headIndex = (headIndex + 1) % maxSize;//удаляем элемент, перемещая указатель
        elementsCount--;
    }

    public int getFirstElement() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return -1;
        }
        return queue[headIndex];
    }

    public void printEndStart() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        System.out.print("Очередь: ");
        for (int i = 0; i < elementsCount; i++) {
            int currentIndex = (headIndex + i) % maxSize;
            System.out.print(queue[currentIndex]);
            if (i < elementsCount - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public void printStartEnd() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }

        System.out.print("Очередь: ");
        for (int i = elementsCount-1; i >-1; i--) {
            int currentIndex = (headIndex + i) % maxSize;
            System.out.print(queue[currentIndex]);
            if (i > 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public int countElements() {
        return elementsCount;
    }

    public static void main() {
        CircularQueue queue = new CircularQueue(5);
        queue.addElement(15);
        queue.addElement(25);
        queue.addElement(35);
        queue.addElement(45);
        queue.addElement(55);

        queue.printEndStart();

        queue.addElement(65);

        // Удаляем элементы
        queue.removeElement();

        queue.printEndStart();

        // Добавляем элементы
        queue.addElement(75);

        //печатаем очередь 2 способами
        queue.printStartEnd();
        queue.printEndStart();

        System.out.println("Первый элемент: " + queue.getFirstElement());

        System.out.println("Количество элементов: " + queue.countElements());
    }
}

//Очередь: 15 25 35 45 55
//Очередь заполнена
//Очередь: 25 35 45 55
//Очередь: 75 55 45 35 25
//Очередь: 25 35 45 55 75
//Первый элемент: 25
//Количество элементов: 5