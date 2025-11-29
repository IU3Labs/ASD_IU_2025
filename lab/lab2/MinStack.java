/*
Задание А1
Реализовать стек с минимумом.
Каждый элемент помимо своего значения хранит еще и значение минимума во время его добавления, получается получить значения минимума можно сразу без перебора
*/

import java.util.Scanner;

public class MinStack {


    private static class StackNode {
        int value;
        int minimum;
        StackNode nextNode;

        StackNode(int value, int minimum, StackNode nextNode) {
            this.value = value;
            this.minimum = minimum;
            this.nextNode = nextNode;
        }
    }

    private StackNode headNode;

    public MinStack() {
        headNode = null;
    }


    public void push(int number) {
        if (headNode == null) {
            headNode = new StackNode(number, number, null);
        } else {
            int currentMinimum = (number < headNode.minimum) ? number : headNode.minimum;
            headNode = new StackNode(number, currentMinimum, headNode);
        }
        System.out.println("Добавлен элемент: " + number + " | Текущий минимум: " + getMinimum());
    }


    public int del() {
        empty();
        int removedValue = headNode.value;
        headNode = headNode.nextNode;
        return removedValue;
    }

    // Просмотр верхнего элемента
    public int peek() {
        empty();
        return headNode.value;
    }


    public int getMinimum() {
        empty();
        return headNode.minimum;
    }


    public boolean empty() {
        return headNode == null;
    }


    public void printStack() {
        if (headNode == null) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.println("Текущее состояние стека:");
        StackNode currentNode = headNode;
        while (currentNode != null) {
            System.out.println("Значение: " + currentNode.value + " | Мин. при добавлении: " + currentNode.minimum);
            currentNode = currentNode.nextNode;
        }
        System.out.println();
    }


    public void fillFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int elementCount = scanner.nextInt();
        System.out.println("Введите элементы:");
        for (int i = 0; i < elementCount; i++) {
            int inputNumber = scanner.nextInt();
            push(inputNumber);
        }
    }


    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.fillFromUser();
        System.out.println(" Проверка ");

        stack.push(99);
        stack.push(-1);
        stack.printStack();

        System.out.println(" Удалим: " + stack.peek() );
        stack.del();
        System.out.println("После удаления минимум: " + stack.getMinimum());

        System.out.println("Верхний элемент: " + stack.peek());
        stack.printStack();
    }
}
