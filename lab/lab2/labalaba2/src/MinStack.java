//Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
public class MinStack {

    private int[] data; //массив для основного стека
    private int[] minData; //вспомогательный массив минимумов

    private int size; //текущее кол-во эл-тов в стеке
    private static final int INITIAL_CAPACITY = 10;

    public MinStack() {
        data = new int[INITIAL_CAPACITY];
        minData = new int[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int x) { //добавление элемента в стек
        if (size == data.length) {
            resize();
        }

        data[size] = x;
        int currentMin;
        if (size == 0) {
            currentMin = x;
        } else {
            currentMin = Math.min(x, minData[size - 1]);
        }
        minData[size] = currentMin;
        size++;
    }

    public void pop() { //удаление верхнего эл-та стека
        if (size == 0) {
            throw new RuntimeException("Стек пуст.");
        }
        size--;
    }

    public int top() { //возвращение верхнего эл-та стека
        if (size == 0) {
            throw new RuntimeException("Стек пуст.");
        }
        return data[size - 1];
    }

    public int getMin() { //возвращение текущего минимума
        if (size == 0) {
            throw new RuntimeException("Стек пуст.");
        }
        return minData[size - 1];
    }

    private void resize() { //вспомогательный метод для увеличения массива
        int newCapacity = data.length * 2;

        int[] newData = new int[newCapacity];
        int[] newMinData = new int[newCapacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
            newMinData[i] = minData[i];
        }

        data = newData;
        minData = newMinData;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Стек пуст.");
            return;
        }

        System.out.print("Стек снизу вверх: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(" - ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.display(); //Стек пуст.

        ms.push(3);
        ms.push(1);
        ms.push(4);
        ms.push(-1);

        ms.display(); //Стек снизу вверх: 3 - 1 - 4 - -1

        System.out.println("Минимум: " + ms.getMin()); // -1

        ms.pop();
        ms.display(); //Стек снизу вверх: 3 - 1 - 4
    }
}