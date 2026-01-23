package structures;

import java.sql.Array;
import java.util.ArrayList;

public class Queue {
    private ArrayList<Integer>data;
    private int length;

    public Queue() {
        data = new ArrayList<Integer>();
        length = 0;
    }

    public void EnQueue(int element) {
        data.add(element);
        length++;
    }

    public int DeQueue() {
        if (length == 0) {
            System.out.println("Очередь пустая");
            return 0;
        }
        length--;
        return data.removeFirst();
    }

    public int Length() {
        return length;
    }

    public void Display() {
        for (int i = 0; i < length; i++) {
            System.out.println(data.get(i));
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        for (int i = 0; i < 10; i++) {
            q.EnQueue(i);
        }
        System.out.println("Заполненная очередь:");
        q.Display();
        System.out.printf("Вытащим первый элемент из очереди: %d\n", q.DeQueue());
        q.Display();

    }
}

