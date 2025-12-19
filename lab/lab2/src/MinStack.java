//A1 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

import java.util.Stack;

public class MinStack {
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    // Добавление элемента в конец стека
    public void push(int value) {
        mainStack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    // Удаление элемента с вершины стека
    public int pop() {
        if (mainStack.isEmpty()) return -1;

        int removed = mainStack.pop();
        if (removed == minStack.peek()) {
            minStack.pop();
        }
        return removed;
    }

    // Подсчет числа элементов
    public int count() {
        return mainStack.size();
    }

    // Печать стека в прямом порядке (снизу вверх)
    public void printForward() {
        if (mainStack.isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }

        // Копируем стек для печати
        Stack<Integer> temp = new Stack<>();
        Stack<Integer> copy = new Stack<>();
        copy.addAll(mainStack);

        // Переносим элементы во временный стек для правильного порядка
        while (!copy.isEmpty()) {
            temp.push(copy.pop());
        }

        System.out.print("Стек (снизу вверх): ");
        while (!temp.isEmpty()) {
            System.out.print(temp.pop() + " ");
        }
        System.out.println();
    }

    // Печать стека в обратном порядке (сверху вниз)
    public void printReverse() {
        if (mainStack.isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }

        System.out.print("Стек (сверху вниз): ");
        Stack<Integer> copy = new Stack<>();
        copy.addAll(mainStack);

        while (!copy.isEmpty()) {
            System.out.print(copy.pop() + " ");
        }
        System.out.println();
    }

    // Получение минимального элемента
    public int getMin() {
        if (minStack.isEmpty()) return -1;
        return minStack.peek();
    }

    // Демонстрация работы стека
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы MinStack ===\n");

        MinStack stack = new MinStack();

        System.out.println("1. Добавление элементов в конец стека:");
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        stack.push(4);

        stack.printForward();
        stack.printReverse();
        System.out.println("Минимальный элемент: " + stack.getMin());
        System.out.println("Количество элементов: " + stack.count());

        System.out.println("\n2. Удаление элемента с вершины:");
        System.out.println("Удален: " + stack.pop());
        stack.printForward();
        System.out.println("Минимальный элемент: " + stack.getMin());

        System.out.println("\n3. Еще одно удаление:");
        System.out.println("Удален: " + stack.pop());
        stack.printForward();
        System.out.println("Минимальный элемент: " + stack.getMin());

        System.out.println("\n4. Добавление новых элементов:");
        stack.push(1);
        stack.push(8);
        stack.printForward();
        System.out.println("Минимальный элемент: " + stack.getMin());
        System.out.println("Количество элементов: " + stack.count());
    }
}
