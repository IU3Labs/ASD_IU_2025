/*Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику.*/
public class CircularQueue {
    private int[] queue;
    private int capacity; //максимальный размер очереди
    private int front; //индекс начала очереди
    private int rear; //индекс конца очереди
    private int size; //вспомогательная переменная для отслеживания текущего количества элементов

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(int value) { //добавление в конец
        if (isFull()) {
            throw new RuntimeException("Очередь заполнена.");
        }

        rear = (rear + 1) % capacity; //перемещаем rear на следующую позицию по кругу
        queue[rear] = value;
        size++;

        System.out.println("Добавлен элемент: " + value);
    }

    public int dequeue() { //удаление из начала
        if (isEmpty()) {
            throw new RuntimeException("Очередь пуста.");
        }

        int removedValue = queue[front];
        front = (front + 1) % capacity;  //сдвигаем начало вперёд по кругу
        size--;

        System.out.println("Удалён элемент: " + removedValue);
        return removedValue;
    }

    public int peek() { //просмотр первого эл-та(без удаления)
        if (isEmpty()) {
            throw new RuntimeException("Очередь пуста.");
        }
        return queue[front];
    }

    public int size() { //получение текущего размера
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Очередь пуста.");
            return;
        }

        System.out.print("Очередь: ");
        int index = front;
        for (int i = 0; i < size; i++) {
            System.out.print(queue[index] + " ");
            index = (index + 1) % capacity;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display(); //очередь: 10 20 30

        q.dequeue(); //удалится 10
        q.display(); // очередь: 20 30

        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60); //очередь заполнена
        q.display();   //очередь: 20 30 40 50 60

        // q.enqueue(70); //исключение т.к. очередь заполнена

        System.out.println("Первый элемент: " + q.peek());
        System.out.println("Размер: " + q.size());
    }
}