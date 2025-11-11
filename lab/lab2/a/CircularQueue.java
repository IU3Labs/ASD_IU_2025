package lab2.a;

//циклическая очередь

public class CircularQueue {
    // массив для хранения элементов очереди
    private int[] storage;
    // начало очереди
    private int startPointer;
    // конец очереди
    private int endPointer;
    // максимальный размер внутреннего массива
    private int maxSize;

    public CircularQueue(int size) {
        // +1 к запрошенному размеру чтоб понять полная очередь или пустая
        this.maxSize = size + 1;
        // массив для хранения элементов
        this.storage = new int[this.maxSize];
        // 2 указателя на начало массива
        this.startPointer = 0;
        this.endPointer = 0;
    }

    //проверка очереди на пустоту
    public boolean isBufferEmpty() {
        // очередь пустая если указатели совпадают
        return this.startPointer == this.endPointer;
    }

    //проверка на заполнение
    public boolean isBufferFull() {
        // очередь полная если следующий за endPointer элемент равен startPointer
        return (this.endPointer + 1) % this.maxSize == this.startPointer;
    }

    //добавление элемента в конец
    public void addToEnd(int elementValue) {
        if (this.isBufferFull()) {
            System.out.println("buffer is full");
        } else {
            // добавляем элемент в текущую позицию endPointer
            this.storage[this.endPointer] = elementValue;
            // перемещаем endPointer на следующую позицию
            this.endPointer = (this.endPointer + 1) % this.maxSize;
        }
    }

    //извлечение элемента из начала очереди
    public int removeFromFront() {
        if (this.isBufferEmpty()) {
            System.out.println("buffer is empty");
            return -1;
        } else {
            // сохраняем значение элемента для возврата
            int extractedValue = this.storage[this.startPointer];
            // перемещаем startPointer на следующую позицию
            this.startPointer = (this.startPointer + 1) % this.maxSize;
            return extractedValue;
        }
    }

    //получение первого элемента
    public int getFirstElement() {
        if (this.isBufferEmpty()) {
            System.out.println("buffer is empty");
            return -1;
        } else {
            // возвращаем элемент на который указывает startPointer
            return this.storage[this.startPointer];
        }
    }

    //отображение информации об очереди
    public void showContents() {
        if (this.isBufferEmpty()) {
            System.out.println("buffer is empty");
        } else {
            System.out.println("buffer: ");

            // проходим по всем элементам от startPointer до endPointer
            for(int currentIndex = this.startPointer; currentIndex != this.endPointer; currentIndex = (currentIndex + 1) % this.maxSize) {
                // вывод значение текущего элемента
                int element = this.storage[currentIndex];
                System.out.print(element + " ");
            }

            System.out.println();
            int startPointer = this.startPointer;
            System.out.println("start " + startPointer + ", end: " + this.endPointer + ", fill level: " + this.calculateFillLevel() + "%");
        }
    }

    private int calculateFillLevel() {
        // количество элементов в очереди
        int elementsCount = (this.endPointer - this.startPointer + this.maxSize) % this.maxSize;
        // доступная емкость с учётом резерва
        int availableSpace = this.maxSize - 1;
        // процент заполнения
        return elementsCount * 100 / availableSpace;
    }

    //количество элементов в очереди
    public int getElementCount() {
        //вычисление количества элементов в циклической очереди
        return (this.endPointer - this.startPointer + this.maxSize) % this.maxSize;
    }

    //максимальная емкость
    public int getMaxCapacity() {
        //фактическая емкость
        return this.maxSize - 1;
    }

    //очистка
    public void clearBuffer() {
        this.startPointer = 0;
        this.endPointer = 0;
        System.out.println("buffer is cleared");
    }
}

class CircularQueueDemo {
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