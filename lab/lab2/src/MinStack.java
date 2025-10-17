//Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

public class MinStack {

    //создадим класс-элемент стека, который хранит свое значение и наименьший элемент стека на момент добавления
    private static class Node {
        private final int data;
        private final int currentMinimum;

        public Node(int data, int currentMinimum) {
            this.data = data;
            this.currentMinimum = currentMinimum;
        }

        public int getData() {
            return data;
        }

        public int getCurrentMinimum() {
            return currentMinimum;
        }
    }

    //стек реализован на динамическом массиве
    Node[] stack;
    //физическая длина массива
    int size;
    //указатель на последний элемент стека + 1
    int currentTop = 0;

    public MinStack(int size) {
        this.size = size;
        stack = new Node[size];
    }

    private boolean isFull() {
        if (currentTop == size - 1) {
            return true;
        }
        return false;
    }

    //увеличиваем длину массива в 2 раза в случае его заполненности
    private void resizeArray() {
        int newSize = size * 2;

        Node[] newStack = new Node[newSize];

        for (int i = 0; i < size; i++) {
            newStack[i] = stack[i];
        }

        stack = newStack;
        size = newSize;
    }

    public void push(int data) {
        //расширение стека, если он заполнился
        if (isFull()) {
            resizeArray();
        }
        //добавление 1 элемента в массив
        if (currentTop == 0) {
            Node buffer = new Node(data, data);
            stack[currentTop] = buffer;
        }
        //добавление любого, кроме 1
        else {
            Node buffer = new Node(data, Math.min(stack[currentTop - 1].getCurrentMinimum(), data));
            stack[currentTop] = buffer;
        }
        currentTop++;
    }

    public void pop() {
        currentTop--;
    }

    public int lenght() {
        return currentTop;
    }

    public void printEndStart() {
        for (int i = currentTop - 1; i > -1; i--) {
            System.out.print(stack[i].data + " ");
        }
    }

    public void printStartEnd() {
        for (int i = 0; i < currentTop; i++) {
            System.out.print(stack[i].data + " ");
        }
    }

    //минимальное значение хранится в поле currentMinimum, из-за чего доступ к нему происходит на 0(1)
    public int findMin() {
        return stack[currentTop - 1].getCurrentMinimum();
    }

    public static void main() {

        MinStack stack = new MinStack(3);
        stack.push(12);
        stack.push(10);
        stack.push(5);
        stack.push(-3);
        stack.push(-10);

        stack.printStartEnd();

        long start = System.nanoTime();
        stack.findMin();
        long end = System.nanoTime();
        //выведем время, чтобы проверить, что операция происходит за O(1)
        System.out.println("\nМинимум: " + stack.findMin() + ", время (сложность операции - O(1)) " + (end - start));
        stack.pop();
        stack.printStartEnd();
        System.out.println("\nМинимум после 1 удаления: " + stack.findMin());
        stack.pop();
        stack.printStartEnd();
        System.out.println("\nМинимум после 2 удалений: " + stack.findMin());
    }
}

//12 10 5 -3 -10
//Минимум: -10, время (сложность операции - O(1)) 2700
//12 10 5 -3
//Минимум после 1 удаления: -3
//12 10 5
//Минимум после 2 удалений: 5