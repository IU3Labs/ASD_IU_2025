public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue buffer = new CircularQueue(3);

        // добавляем элементы в очередь
        buffer.addToEnd(10);
        buffer.addToEnd(20);
        buffer.addToEnd(30);

        // буфер переполнен
        buffer.addToEnd(40);

        buffer.showContents();

        // извлекаем два элемента из начала очереди
        buffer.removeFromFront();
        buffer.removeFromFront();

        buffer.showContents();

        buffer.addToEnd(100);
        buffer.addToEnd(110);

        // получаем первый элемент без извлечения
        System.out.println("first element: " + buffer.getFirstElement());
    }
}