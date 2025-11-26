package lab2.a;

public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue buffer = new CircularQueue(3);

        // добавляем элементы в очередь
        buffer.addToEnd(7);
        buffer.addToEnd(90);
        buffer.addToEnd(46);

        // буфер переполнен
        buffer.addToEnd(13);

        buffer.showContents();

        // извлекаем два элемента из начала очереди
        buffer.removeFromFront();
        buffer.removeFromFront();

        buffer.showContents();

        buffer.addToEnd(100);
        buffer.addToEnd(110);

        buffer.showContents();

        // получаем первый элемент без извлечения
        System.out.println("first element: " + buffer.getFirstElement());
    }
}
