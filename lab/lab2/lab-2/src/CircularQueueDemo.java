public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);
        cq.enqueue(1);
        cq.enqueue(2);
        cq.enqueue(3);
        cq.enqueue(4);
        cq.enqueue(5); // очередь переполнена
        cq.displayQueue();
        cq.dequeue();
        cq.dequeue();
        cq.displayQueue();
        cq.enqueue(6);
        cq.enqueue(7);
        cq.displayQueue();
        System.out.println("Первый элемент: " + cq.peekFront());
    }
}
