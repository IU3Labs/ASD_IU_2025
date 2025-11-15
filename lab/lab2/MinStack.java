

// Реализовать стек с минимумом, прокомментировать логику

import java.util.Scanner;

public class MinStack {
    // Класс для узла стека - каждый элемент знает про следующий и хранит минимум
    private static class StackNode {
        int data;        // число (данные)
        int currentMin;  // минимальное значение в стеке на момент добавления узла
        StackNode next;  // ссылка на следующий элемент

        // Конструктор узла
        StackNode(int value, int min, StackNode nextNode) {
            this.data = value;
            this.currentMin = min;
            this.next = nextNode;
        }
    }

    private StackNode top; // верхний узел в стеке

    // Конструктор стека
    public MinStack() {
        top = null; // изначально стек пустой
    }

    // Добавляем новый элемент в стек
    public void push(int x) {
        // Если стек пустой, то новый элемент сам себе минимум
        if (top == null) {
            top = new StackNode(x, x, null);
        } else {
            // Иначе смотрим, что меньше: новый элемент или текущий минимум
            int newMin = Math.min(x, top.currentMin);
            // Создаем новый узел который ссылается на старый top
            top = new StackNode(x, newMin, top);
        }
        System.out.println("Добавили " + x + ", теперь минимум = " + getMin());
    }

    // Удаляем верхний элемент
    public int pop() {
        if (top == null) {
            System.out.println("Стек пустой!"); // предупреждаем, если стек пустой
        }

        int value = top.data; // запоминаем значение, которое удаляем
        top = top.next; // переходим к следующему элементу
        System.out.println("Удалили " + value);

        return value;
    }

    // Смотрим на верхний элемент без удаления
    public int peek() {
        if (top == null) {
            System.out.println("Стек пустой!");
        }
        return top.data;
    }

    // Получаем минимум
    public int getMin() {
        if (top == null) {
            System.out.println("Стек пустой!");
        }
        return top.currentMin; // каждый узел помнит, какой был минимум, когда его добавляли
    }

    // Проверяем, пустой ли стек
    public boolean isEmpty() {
        return top == null;
    }

    // Выводим содержимое стека
    public void printStack() {
        if (top == null) {
            System.out.println("Стек пустой!");
            return;
        }
        System.out.print("Стек: ");
        StackNode current = top;
        while (current != null) {
            System.out.print(current.data + "(" + current.currentMin + ") ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();

        // Добавляем элементы
        inputStack(stack);

        stack.printStack();
        System.out.println("Текущий минимум: " + stack.getMin());

        stack.pop(); // удаляем последний
        System.out.println("После удаления последнего, минимум: " + stack.getMin());

        stack.push(1); // добавляем новый возможный минимум
        System.out.println("После добавления возможного минимума, минимум: " + stack.getMin());

        System.out.println("Верхний элемент: " + stack.peek());
        System.out.println("Минимум: " + stack.getMin());
    }

    // Ввод стека (можно будет доввести потом с помощью метода push)
    static void inputStack(MinStack stack) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Введите количество элементов в стеке: ");
        int numElem = inputScanner.nextInt();
        System.out.println("Введите элементы стека: ");

        for (int i = 0; i < numElem; i++) {
            int elem = inputScanner.nextInt();
            stack.push(elem);
        }
    }
}
