package lab2.circularQueue;

import lab2.utils.utils;

public class CircularQueue {
    private Integer front, rear, size;
    private Integer[] data;

    public CircularQueue(int capacity) {
        data = new Integer[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public Integer size() {
        return size;
    }

    private Integer correctIndex(Integer index) {
        return (index + data.length) % data.length;
    }

    public boolean push(Integer newElement) {
        if (size == data.length) return false;
        data[rear] = newElement;
        rear = correctIndex(rear + 1);
        size++;
        return true;
    }

    public Integer pop() {
        if (size == 0) throw new RuntimeException("CircularQueue empty");
        Integer value = data[front];
        front = correctIndex(front + 1);
        size--;
        return value;
    }

    public void print() {
        System.out.print("[");
        if (size > 0) {
            System.out.print(data[front]);
            for (int i = correctIndex(front + 1); i != rear; i = correctIndex(i + 1)) {
                System.out.print(", " + data[i]);
            }
        }
        System.out.println("]");
    }


    public void printRevers() {
        System.out.print("[");
        if (size > 0) {
            System.out.print(data[correctIndex(rear - 1)]);

            for (int i = correctIndex(rear - 2); i != correctIndex(front - 1); i = correctIndex(i - 1)) {
                System.out.print(", " + data[i]);
            }
        }
        System.out.println("]");
    }

    public static void main() {
        CircularQueue circularQueue = new CircularQueue(10);
        for (int i = 0; i < 6; i++) {
            circularQueue.push(utils.random100());
        }

        circularQueue.print();
//        [9, 84, 10, 71, 23, 48]
        circularQueue.printRevers();
//        [48, 23, 71, 10, 84, 9]
        System.out.println("size "+circularQueue.size());
//        size 6
        for (int i = 0; i < 3; i++) {
            circularQueue.pop();
        }
        circularQueue.print();
//        [71, 23, 48]
        circularQueue.printRevers();
//        [48, 23, 71]
    }
}
