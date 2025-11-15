//1 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

//Стек с минимумом хранит не только введенные элементы, но и
// минимальное значение в нем на момент добавления нового элемента

import java.util.Scanner;

public class MinStack {

    private static class StackNode {
        int data;           // Числовое значение элемента
        int currentMin;     // Минимальное значение в стеке при добавлении этого элемента
        StackNode next;     // Ссылка на следующий элемент в стеке

        StackNode(int value, int min, StackNode nextNode) {
            this.data = value;
            this.currentMin = min;
            this.next = nextNode;
        }
    }

    private StackNode top;  // Верхний элемент стека

    public MinStack() {
        top = null;  // Изначально стек пустой
    }

    public void add(int x) {
        if (top == null) {
            // Если стек пустой, новый элемент сам становится минимумом
            top = new StackNode(x, x, null);
        } else {
            // Если стек не пустой, вычисляем новый минимум
            int newMin = Math.min(x, top.currentMin);
            // Создаем новый узел, который становится новой вершиной
            top = new StackNode(x, newMin, top);
        }
        System.out.println("Добавляем " + x + ", минимум стека = " + getMin());
    }

    public int delete() {
        if (top == null) {
            System.out.println("Стек пуст");
            return -1;  // Возвращаем -1 как индикатор ошибки
        }

        int value = top.data;  // Сохраняем значение удаляемого элемента
        top = top.next;        // Перемещаем указатель на следующий элемент

        return value;
    }

    public int check() {
        if (top == null) {
            System.out.println("Стек пуст");
            return -1;  // Возвращаем -1 как индикатор ошибки
        }
        return top.data;
    }

    public int getMin() {
        if (top == null) {
            System.out.println("Стек пуст");
            return -1;  // Возвращаем -1 как индикатор ошибки
        }
        return top.currentMin;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printStack() {
        if (top == null) {
            System.out.println("Стек пуст");
            return;
        }

        System.out.println("Стек: ");
        StackNode current = top;
        while (current != null) {
            if (current.next != null) {
                System.out.println(current.data + "->" + current.currentMin + "(min), ");
            }else{
                System.out.println(current.data + "->" + current.currentMin + "(min).");
            }
            current = current.next;
        }
        System.out.println();
    }

    public void inputStack() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Введите количество элементов: ");
        int numElem = inputScanner.nextInt();

        System.out.println("Введите элементы: ");
        for (int i = 0; i < numElem; i++) {
            int elem = inputScanner.nextInt();
            this.add(elem);
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.inputStack();

        stack.add(3);
        stack.add(2);
        stack.add(7);

        stack.printStack();
        System.out.println("Текущий минимум: " + stack.getMin());

        stack.delete();
        System.out.println("Удалили верхний элемент, минимум: " + stack.getMin());

        stack.add(1);
        stack.add(2);
        stack.add(7);
        System.out.println("Верхний элемент: " + stack.check());
        System.out.println("Минимум: " + stack.getMin());

        stack.printStack();
    }

}
