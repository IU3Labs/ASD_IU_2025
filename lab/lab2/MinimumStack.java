import java.util.Scanner;
import java.util.ArrayList;

public class MinimumStack {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите количество элементов: ");
        int count = scan.nextInt();

        MinStack stack = new MinStack();

        for (int i = 0; i < count; i++) {
            System.out.print("Введите " + (i + 1) + "-е число: ");
            int value = scan.nextInt();
            stack.push(value);

            System.out.println("Текущий минимум в стеке: " + stack.getMin());
        }

        System.out.println("Удаляем из стека");

        while (!stack.isEmpty()) {
            int removed = stack.pop();
            System.out.println("Удалили из стека: " + removed);

            if (!stack.isEmpty()) {
                System.out.println("Новый минимум в стеке: " + stack.getMin());
            } else {
                System.out.println("Стек пуст, минимума нет");
            }
        }

        scan.close();
    }
}

class MinStack {

    private ArrayList<Integer> stack;
    private ArrayList<Integer> minStack;

    private int top;
    private int minTop;

    public MinStack() {
        stack = new ArrayList<>();
        minStack = new ArrayList<>();
        top = -1;
        minTop = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        top += 1;

        if (stack.size() > top) {
            stack.set(top, value);
        } else {
            stack.add(value);
        }

        if (minTop == -1 || value <= minStack.get(minTop)) {
            minTop += 1;

            if (minStack.size() > minTop) {
                minStack.set(minTop, value);
            } else {
                minStack.add(value);
            }
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Стек пуст, удалить элемент нельзя");
            return -1;
        }

        int value = stack.get(top);
        top -= 1;

        if (value == minStack.get(minTop)) {
            minTop -= 1;
        }

        return value;
    }

    public int getMin() {
        if (minTop == -1) {
            System.out.println("Минимума нет, стек пуст");
            return -1;
        }
        return minStack.get(minTop);
    }
}
