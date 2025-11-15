package tasks;

public class MinStack {
    private int[] mainStack;
    private int[] minStack;
    private int top;
    private int minTop;
    private final int capacity;

    public MinStack(int capacity) {
        this.capacity = capacity;
        this.mainStack = new int[capacity];
        this.minStack = new int[capacity];
        this.top = -1;
        this.minTop = -1;
    }

    public boolean push(int value) {
        if (top == capacity - 1) {
            System.out.println("Стек переполнен");
            return false;
        }
        top++;
        mainStack[top] = value;

        if (minTop == -1) {
            minTop++;
            minStack[minTop] = value;
        } else if (value <= minStack[minTop]) {
            minTop++;
            minStack[minTop] = value;
        } else {
            minTop++;
            minStack[minTop] = minStack[minTop - 1];
        }
        return true;
    }

    public int pop(){
        if (top == -1){
            System.out.println("Стек пуст");
            return -1;
        }
        int removeValue = mainStack[top];
        top--;
        minTop--;
        return removeValue;
    }

    public int top(){
        if (this.top == -1){
            System.out.println("Стек пуст");
            return -1;
        }
        return mainStack[this.top];
    }

    public int getMin(){
        if (this.top == -1){
            System.out.println("Стек пуст");
            return -1;
        }
        return minStack[this.minTop];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public void printForward() {
        if (isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.print("Стек (снизу вверх): [");
        for (int i = 0; i <= top; i++) {
            System.out.print(mainStack[i]);
            if (i < top) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void printBackward() {
        if (isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.print("Стек (сверху вниз): [");
        for (int i = top; i >= 0; i--) {
            System.out.print(mainStack[i]);
            if (i > 0) System.out.print(", ");
        }
        System.out.println("]");
    }
}