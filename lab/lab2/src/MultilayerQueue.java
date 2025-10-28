/*1 Реализовать «Многослойная очередь» (очередь очередей), которая
поддерживает приоритетный режим обработки элементов.
Прокомментировать код.*/




public class MultilayerQueue {

    //элемент очереди
    private static class Node {
        String data; // данные
        Node next; // ссылка на следующий элемент

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    //Очередь без подразделения на уровни
    private static class Queue {
        private Node start; // начало очереди
        private Node end; // конец очереди
        private int size; // размер очереди

        Queue(){
            this.start = null;
            this.end = null;
            this.size = 0;
        }

        // Добавить элемент в конец
        void addLastElement(String data) {
            Node node = new Node(data);
            if (end == null) {
                start = node;
                end = node;
            }
            else {
                end.next = node;
                end = node;
            }
            size++;
            }

        // Удалить 1 элемент очереди
        void deleteFirstElement(){
            if (isEmpty()){
                return;
            }
            start  = start.next;
            if (start == null) {
                end = null;
            }
            size--;
            }

        // Вывести очередь
        void print(){
            if (isEmpty()){
                System.out.println("нет элементов");
            }
            Node temp = start;
            while (temp != null){
                System.out.println(temp.data + " ");
                temp = temp.next;
            }
        }
        // проверка на то пустая ли у нас очередь
        boolean isEmpty() {
            return start == null;
        }
        //Вывод размера
        int size(){
            return size;
        }

    }

    // многослойная очередь
        private Queue[] layers;  // массив очередей
        private int levels; // кол-во уровней
        private int totalSize; // общий размер

        public MultilayerQueue(int levels){
            this.levels = levels;
            layers = new Queue[levels];
            for (int i = 0; i < levels; i++) {
                layers[i] = new Queue();
            }
            totalSize = 0;
        }

    // Добавление элемента  с указанным приоритетом
    public void addLastElement(String element, int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Некорректный выбор приоритета");
            return;
        }
        layers[priority].addLastElement(element);
        totalSize++;
    }

    // Удаление  первого элемента c учетом приоритетов, начиная с наиболее приоритетного
    public void deleteFirstElement() {
        if (totalSize == 0) {
            return;
        }
        for (int i = 0; i < levels; i++) {
            if (!layers[i].isEmpty()) {
                layers[i].deleteFirstElement();
                totalSize--;
                return;
            }
        }
    }

    //размер
    public int getSize(){
        return totalSize;
    }

    // Вывод многослойной очереди
    public void print() {
        System.out.println("Многослойная очередь:");
        for (int i = 0; i < levels; i++) {
            System.out.println("Приоритет " + i + ":");
            layers[i].print();
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args){
        MultilayerQueue multilayerQueue = new MultilayerQueue(4);
        multilayerQueue.addLastElement("Сдать любимую лр по любимому предмету АиС", 0);
        multilayerQueue.addLastElement("Выучить физику хотя бы немножко", 0);
        multilayerQueue.addLastElement("Поиграть в доту", 1);
        multilayerQueue.addLastElement("Посмотреть тикток", 1);
        multilayerQueue.addLastElement("Не уснуть днем", 2);
        multilayerQueue.addLastElement("Обед", 2);
        multilayerQueue.addLastElement("Сходить на пары", 3);
        System.out.println();

        System.out.println("Первоначальный список задач:");
        multilayerQueue.print();

        System.out.println( "Количество дел = " + multilayerQueue.getSize());


        System.out.println("Сделаю ка я 3 самых важных в жизни дела");

        multilayerQueue.deleteFirstElement();
        multilayerQueue.deleteFirstElement();
        multilayerQueue.deleteFirstElement();

        System.out.println("Количество дел после выполнения 3 важных:" + multilayerQueue.getSize());

        System.out.println("\nСписок задач после выполнения 3 важнейших дел:");
        multilayerQueue.print();
    }

}



