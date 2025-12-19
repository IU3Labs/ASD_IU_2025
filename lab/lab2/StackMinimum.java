/*
Реализовать "Стек с минимумом".
Каждый элемент хранит не только своё значение, но и минимальное значение на момент его добавления.
Это позволяет получать минимум за O(1), без прохода по всему стеку.
*/

import java.util.Scanner;

public class StackMinimum {

    // Внутренний узел стека
    private static class Node {
        int val;
        int min;
        Node next;

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    private Node top; // вершина стека

    public StackMinimum() {
        top = null;
    }

    // Добавление элемента в стек
    public void push(int num) {
        if (top == null) {
            top = new Node(num, num, null);
        } else {
            int minNow = (num < top.min) ? num : top.min;
            top = new Node(num, minNow, top);
        }
        System.out.println("Добавлен элемент: " + num + " | Текущий минимум: " + getMin());
    }

    // Удаление верхнего элемента
    public int pop() {
        if (top == null) {
            System.out.println("Стек пуст — удаление невозможно");
            return -1;
        }
        int removed = top.val;
        top = top.next;
        return removed;
    }

    // Просмотр верхнего элемента без удаления
    public int peek() {
        if (top == null) {
            System.out.println("Стек пуст");
            return -1;
        }
        return top.val;
    }

    // Получение текущего минимума
    public int getMin() {
        if (top == null) {
            System.out.println("Стек пуст — минимум отсутствует");
            return -1;
        }
        return top.min;
    }

    // Проверка, пуст ли стек
    public boolean isEmpty() {
        return top == null;
    }

    // Печать содержимого стека
    public void showStack() {
        if (top == null) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.println("Текущее состояние стека:");
        Node temp = top;
        while (temp != null) {
            System.out.println("Значение: " + temp.val + " | Мин. при добавлении: " + temp.min);
            temp = temp.next;
        }
        System.out.println();
    }

    // Заполнение стека пользователем
    public void fillStack() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int count = sc.nextInt();
        System.out.println("Введите элементы:");
        for (int i = 0; i < count; i++) {
            int n = sc.nextInt();
            push(n);
        }
    }

    // Тестирование
    public static void main(String[] args) {
        StackMinimum stack = new StackMinimum();
        stack.fillStack();

        stack.push(3);
        stack.push(9);
        stack.push(2);
        stack.showStack();

        System.out.println("Минимум: " + stack.getMin());
        stack.pop();
        System.out.println("После удаления минимум: " + stack.getMin());

        stack.push(1);
        System.out.println("Верхний элемент: " + stack.peek());
        System.out.println("Минимум: " + stack.getMin());
        stack.showStack();
    }
}
