//Группа А Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
import java.util.Stack;
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // Добавление элемента
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    // Удаление элемента
    public int pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        int removed = stack.pop();
        if (removed == minStack.peek()) {
            minStack.pop();
        }
        return removed;
    }

    // Получение минимума
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        return minStack.peek();
    }

    // Просмотр верхнего элемента
    public int peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        return stack.peek();
    }

    // Подсчет элементов
    public int count() {
        return stack.size();
    }

    // Печать в прямом порядке (сверху вниз)
    public void print() {
        System.out.print("Стек: ");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print(stack.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();

        System.out.println("Демонстрация MinStack");

        ms.push(5);
        ms.push(3);
        ms.push(7);
        ms.push(2);
        ms.push(8);

        System.out.println("Добавлены: 5, 3, 7, 2, 8");
        ms.print();
        System.out.println("Текущий минимум: " + ms.getMin());
        System.out.println("Количество элементов: " + ms.count());

        System.out.println("\nУдаление элемента: " + ms.pop());
        ms.print();
        System.out.println("Новый минимум: " + ms.getMin());

        System.out.println("\nВерхний элемент: " + ms.peek());
    }
}