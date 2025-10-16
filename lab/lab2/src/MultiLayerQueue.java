//Реализовать «Многослойная очередь» (очередь очередей), которая
//поддерживает приоритетный режим обработки элементов.
//Прокомментировать код.

public class MultiLayerQueue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {//Элементы очереди
            this.data = data;//Содержание элемента
            this.next = null;//Следующий элемент
                            //Очереди реализованы по принципу односвязного FIFO списка
        }
        @Override
        public String toString() { //Преобразование содержания элемента в строку для вывода
            return data.toString();
        }
    }


    private static class Queue<T> {
        private Node<T> start;//Первый элемент очереди
        private Node<T> end;//Последний элемент очереди
        private int size;

        Queue() {
            this.start = null;
            this.end = null;
            this.size = 0;
        }

        void addEnd(T data) { //Добавление нового элемента в конец очереди
            Node<T> newNode = new Node<>(data);
            if (end == null) {
                start = newNode;//Случай пустого списка -> навый элемент и начало и конец
                end = newNode;
            } else {
                end.next = newNode;//Изменение конца очереди на новый элемент
                end = newNode;
            }
            size++;
        }

        T deleteStart() { //Изымание первого элемента очереди(FIFO)
            if (isEmpty()) {//Проверка на пустую очередь
                return null;
            }
            T data = start.data;//Сохраняем содержимое изымаемого элемента
            start = start.next;//Перенос начала очереди
            if (start == null) {
                end = null;//Случай опустошения очереди
            }
            size--;//Изменение размера очереди
            return data;
        }

        T checkStart() {
            return isEmpty() ? null : start.data;//Смотрим первый элемент в очереди
        }

        boolean isEmpty() {
            return start == null;
        }

        int size() {
            return size;
        }

        void printQueue(){ // Вывод очереди
            Node<T> current = this.start;//Берем первый элемент очереди за текущий
            for(int j = 0; j < size;j++) {
                if (this.isEmpty()) {
                    System.out.println("Очередь пуста");//Пустая очередь
                    break;
                }
                if(j != size - 1) {
                    System.out.print(current + ", ");
                } else{
                    System.out.print(current + ".");
                }
                current = current.next;
                }
            }
        }
    //Поля Многоуровневой очереди
    private Queue<T>[] queues;        // Массив очередей разных приоритетов
    private final int levels;         // Количество уровней приоритета
    private int currentPriority;      // Текущий активный приоритет
    private int totalSize;            // Общее количество элементов во всех очередях

    //Конструктор многоуровневой очереди, создает переданное количество вложенных очередей.
    @SuppressWarnings("unchecked")
    public MultiLayerQueue(int levels) {
        if (levels <= 0) {
            throw new IllegalArgumentException("Количество уровней должно быть положительным");
        }

        this.levels = levels;
        this.queues = new Queue[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new Queue<>();
        }
        this.currentPriority = 0;
        this.totalSize = 0;
    }


    public void addEnd(T element, int priority) {//Добавление элемента в очередь переданного приоритета
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Неверный приоритет: " + priority);
        }
        queues[priority].addEnd(element);//Добавляем в выбранную очередь
        totalSize++;//Обновляем общий размер
        currentPriority=priority;//Обновляем задействованный приоритет
    }
    public T deletePriorityStart(int priority) { //Изымание первого элемента очереди переданного приоритета
        if (queues[priority].isEmpty()) {//Проверка пустой очереди
            return null;
        }
        totalSize--;//Обновляем общий размер
        currentPriority=priority;//Обновляем задействованный приоритет
        return queues[priority].deleteStart();//Удаляем начало выбранноц очереди
    }

    public T deleteStart() {//Изымание первого элемента самой приоритетной очереди
        if (isEmpty()) {//Проверка пустой очереди
            return null;
        }

        // Поиск очереди с наивысшим приоритетом, содержащей элементы
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) {//Проверка пустой очереди
                currentPriority = i;//Обновляем задействованный приоритет
                totalSize--;//Обновляем общий размер
                return queues[i].deleteStart();//Удаляем начало выбранноц очереди
            }
        }
        return null;//Теоретически недостижимый возврат, тк проверка на пустые очереди в начале метода
    }

    public T checkStart() {//Просмотр первого элемента самой приоритетной очереди без изъятия
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) {//Проверка пустой очереди
                return queues[i].checkStart();
            }
        }
        return null;//Пустые очереди
    }

    public T checkStartPriority(int priority) {//Просмотр первого элемента очереди переданного приоритета без изъятия

        if (!queues[priority].isEmpty()) {//Проверка пустой очереди
            return queues[priority].checkStart();
        }
        return null;//Элементы в очереди отсутсвуют
    }

    void printQueuesDescending(){ //Вывод всей многоуровневой очереди
        System.out.println("Очереди в порядке убывания приоритета: ");
        for (int i = 0; i < levels; i++) {
            System.out.print("Приоритет " + i + ": ");
            queues[i].printQueue();
            System.out.println();
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return totalSize == 0;
    }

    public int size() {
        return totalSize;
    }

    public int size(int priority) {
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Неверный приоритет");
        }
        return queues[priority].size();
    }

    public int getCurrentPriority() {
        return currentPriority;
    }

    public int getLevelsCount() {
        return levels;
    }

    public static void main(String[] args){
        MultiLayerQueue<String> tasks = new MultiLayerQueue<>(5);

        //Создаем список дел по приоритетам
        tasks.addEnd("Купить пельмени", 0);
        tasks.addEnd("Погладить кота", 0);
        tasks.addEnd("Выполнить мдз", 1);
        tasks.addEnd("Выспаться", 4);
        tasks.addEnd("Сдать лабу по АСД", 1);
        tasks.addEnd("Выпить кофе", 2);
        tasks.addEnd("Потрогать траву", 4);
        tasks.addEnd("Выпить чаю", 2);
        tasks.addEnd("Снять дверь с петель", 3);
        tasks.addEnd("Покрасить балкон", 3);

        System.out.println("Ваш список дел: ");
        tasks.printQueuesDescending();

        System.out.println("Самое приоритетное дело: " + tasks.checkStart());
        System.out.println("Первое дело из последнего приоритета: " + tasks.checkStartPriority(4));
        System.out.println();

        System.out.println("Выполнено: " + tasks.deleteStart());
        System.out.println("ВЫполнено: " + tasks.deletePriorityStart(2));
        System.out.println();

        System.out.println("Актуальный список дел: ");
        tasks.printQueuesDescending();
    }
}
//Ваш список дел:
//Очереди в порядке убывания приоритета:
//Приоритет 0: Купить пельмени, Погладить кота.
//Приоритет 1: Выполнить мдз, Сдать лабу по АСД.
//Приоритет 2: Выпить кофе, Выпить чаю.
//Приоритет 3: Снять дверь с петель, Покрасить балкон.
//Приоритет 4: Выспаться, Потрогать траву.
//
//Самое приоритетное дело: Купить пельмени
//Первое дело из последнего приоритета: Выспаться
//
//Выполнено: Купить пельмени
//ВЫполнено: Выпить кофе
//
//Актуальный список дел:
//Очереди в порядке убывания приоритета:
//Приоритет 0: Погладить кота.
//Приоритет 1: Выполнить мдз, Сдать лабу по АСД.
//Приоритет 2: Выпить чаю.
//Приоритет 3: Снять дверь с петель, Покрасить балкон.
//Приоритет 4: Выспаться, Потрогать траву.