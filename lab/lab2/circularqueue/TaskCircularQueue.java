package circularqueue;

public class TaskCircularQueue {
    public static void main(String[] args) {
        DataTypeCircularQueue cq = new DataTypeCircularQueue(5);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.enqueue(40);
        cq.enqueue(50);
        cq.printQueue();
        cq.dequeue();
        cq.dequeue();
        cq.printQueue();
        cq.enqueue(60);
        cq.enqueue(70);
        cq.printQueue();
        System.out.println("Первый элемент: " + cq.peek());
    }
}

/* Результат вывода:
Добавлен элемент: 10
Добавлен элемент: 20
Добавлен элемент: 30
Добавлен элемент: 40
Добавлен элемент: 50
Содержимое очереди:
10
20
30
40
50

Удалён элемпент: 10
Удалён элемпент: 20
Содержимое очереди:
30
40
50

Добавлен элемент: 60
Добавлен элемент: 70
Содержимое очереди:
30
40
50
60
70

Первый элемент: 30

 */