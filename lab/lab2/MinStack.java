import java.util.Stack;

//1 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
public class MinStack {
    private Stack<Integer> stack;     // Основной стек для элементов
    private Stack<Integer> minStack;  // Стек для хранения минимумов

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // Добавление элемента (в конец стека)
    public void push(int value) {
        stack.push(value);

        // Если minStack пуст ИЛИ новый элемент меньше/равен текущему минимуму
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    // Удаление элемента (с конца стека)
    public int pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Стек пуст");
        }

        int removed = stack.pop();

        // Если удаляемый элемент был минимумом, удаляем его из minStack
        if (removed == minStack.peek()) {
            minStack.pop();
        }

        return removed;
    }

    // Получение верхнего элемента
    public int top() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Стек пуст!");
        }
        return stack.peek();
    }

    //      Получение минимального элемента
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Стек пуст!");
        }
        return minStack.peek();
    }

    //   Количество элементов
    public int size() {
        return stack.size();
    }

    //   Проверка на пустоту
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Печать в прямом порядке (сверху вниз)
    public void printForward() {
        System.out.println("Стек (сверху вниз): " + stack);
    }

    //  Печать в обратном порядке (снизу вверх)
    public void printBackward() {
        Stack<Integer> temp = new Stack<>();
        Stack<Integer> copy = (Stack<Integer>) stack.clone();

        // Переворачиваем стек
        while (!copy.isEmpty()) {
            temp.push(copy.pop());
        }
        System.out.println("Стек (снизу вверх): " + temp);
    }

        // Демонстрация работы
    public static void main(String[] args) {
        System.out.println(" Демонстрация работы Min Stack");

        MinStack stack = new MinStack();

        // Добавляем элементы
        System.out.println("1. Добавляем элементы: 5, 3, 7, 2");
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);

        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        // Удаляем элемент
        System.out.println("2. Удаляем верхний элемент: " + stack.pop());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());

        // добавляем еще элементы
        System.out.println("3. Добавляем элементы: 1, 4");
        stack.push(1);
        stack.push(4);

        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());

        // Печатаем в обратном порядке
        System.out.println("4. Печать в обратном порядке:");
        stack.printBackward();

        // Продолжаем удалять
        System.out.println("5. Удаляем несколько элементов:");
        while (!stack.isEmpty()) {
            System.out.println("Удален: " + stack.pop() + ", Минимум: " +
                    (stack.isEmpty() ? "стек пуст" : stack.getMin()));
        }
    }
}