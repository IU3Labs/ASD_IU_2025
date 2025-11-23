package lab2.multiLevelQueue;

import lab2.circularQueue.CircularQueue;

public class MultiLevelQueue {
    private CircularQueue[] data;
    private Integer size;

    MultiLevelQueue(int levels, int elements) {
        data = new CircularQueue[levels];
        for (int i = 0; i < data.length; i++) {
            data[i] = new CircularQueue(elements);
        }
        size = 0;
    }

    public boolean push(int level, int element) {
        if (level < 0 || level >= data.length) return false;
        if (data[level].push(element)) {
            size++;
            return true;
        }
        return false;
    }

    public Integer pop() {
        if (size == 0) throw new RuntimeException("MultiLevelQueue empty");
        Integer value = 0;
        for (CircularQueue level : data) {
            if (level.size() > 0) {
                value = level.pop();
                size--;
                break;
            }
        }
        return value;
    }

    public Integer size() {
        return size;
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(i + ": ");
            data[i].print();
        }
    }

    public void printRevers() {
        for (int i = data.length - 1; i >= 0; i--) {
            System.out.print(i + ": ");
            data[i].printRevers();
        }
    }

    public static void main() {
        MultiLevelQueue queue = new MultiLevelQueue(3,10);
        queue.push(2,5);
        queue.push(2,6);

        queue.push(1,3);
        queue.push(1,4);


        queue.push(0,1);
        queue.push(0,2);

        queue.print();
//        0: [1, 2]
//        1: [3, 4]
//        2: [5, 6]
        queue.printRevers();
//        2: [6, 5]
//        1: [4, 3]
//        0: [2, 1]

        queue.pop();
        queue.print();
//        0: [2]
//        1: [3, 4]
//        2: [5, 6]

        System.out.println("size "+queue.size());
//        size 5

        queue.pop();
        queue.pop();
        queue.printRevers();
//        2: [6, 5]
//        1: [4]
//        0: []
    }
}
