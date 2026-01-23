import structures.Queue;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

public class MultipriorityQueue
{
    private HashMap<Integer, Queue> queues;
    private int length;

    public MultipriorityQueue() {
        queues = new HashMap<Integer, Queue>();  // Приоритет по индексам: от высшего до низшего 0,1,2,..
    }

    public void EnQueue(int priority, int element) {
        if (!queues.containsKey(priority)) {
            queues.put(priority, new Queue());
        }
        queues.get(priority).EnQueue(element);
        length++;
    }

    public ArrayList<Integer> GetSortedKeys() {
        ArrayList<Integer> keys = new ArrayList<Integer>(queues.keySet());
        Collections.sort(keys);
        return keys;
    }

    public int DeQueue() {
        int res;
        ArrayList<Integer> keys = GetSortedKeys();
        for (int i = 0; i < keys.size(); i++) {
            res = queues.get(keys.get(i)).DeQueue();
            if (queues.get(keys.get(i)).Length() == 0) {
                queues.remove(keys.get(i));
            }
            length--;
            return res;
        }
        System.out.println("Многослойная очередь пустая");
        return 0;
    }

    public void Display() {
        ArrayList<Integer> keys = GetSortedKeys();
        for (int i = 0; i < keys.size(); i++) {
            queues.get(keys.get(i)).Display();
        }
    }

    public int Length() {
        return length;
    }

    public static void main(String[] args) {
        MultipriorityQueue mq = new MultipriorityQueue();
        mq.EnQueue(10, 5);
        mq.EnQueue(0, 1);
        mq.EnQueue(1, 3);
        mq.EnQueue(3, 4);
        mq.EnQueue(0, 2);
        mq.Display();
        System.out.printf("Количество непустых приоритетов у очереди: %d\n", mq.Length());
        System.out.printf("Первый элемент с учетом приоритета: %d\n", mq.DeQueue());
        System.out.printf("Второй элемент с учетом приоритета: %d\n", mq.DeQueue());
        System.out.printf("Третий элемент с учетом приоритета: %d\n", mq.DeQueue());
        mq.EnQueue(0, 6);
        mq.EnQueue(6, 0);
        mq.Display();
        System.out.printf("Количество непустых приоритетов у очереди: %d\n", mq.Length());
    }
}