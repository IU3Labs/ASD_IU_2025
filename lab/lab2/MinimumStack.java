/*
Задание:
    Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

Комментарий по логике:
    в Min stack добавляются элементы только в том случае, если он пустой
    или предыдущий элемент больше, чем новый.
 */

import java.util.Scanner;

public class MinimumStack {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите максимальный размер стека: ");
        int size = scan.nextInt();


        MinStack stack = new MinStack(size);

        for (int i = 0; i < size; i++) {
            System.out.print("Введите " + (i + 1) + "-е число: ");
            int value = scan.nextInt();
            stack.push(value);


            System.out.println("Текущий минимум в стеке: " + stack.getMin());
        }

        System.out.println("Удаляем из стека");

        while (!stack.isEmpty()) { // нужно, чтобы продемонстрировать работу стека
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

    private int[] stack;

    private int[] minStack;

    private int top; //верхушка обыкновенного стека

    private int minTop; // верхушка стека минимумов

    public MinStack(int size) { //создаем массивы для стеков
        stack = new int[size];
        minStack = new int[size];
        top = -1;
        minTop = -1;
    }

    public boolean isEmpty() { // проверка пустой стек или нет
        return top == -1;
    }

    public boolean isFull() { // проверка заполнения стека
        return top == stack.length - 1;
    }

    public void push(int value) { // с помощью этого метода добавляем элемент в стек

        top += 1;
        stack[top] = value;

        if (minTop == -1 || value <= minStack[minTop]) {
            minTop += 1;
            minStack[minTop] = value;
        }
    }

    public int pop() { // метод удаления верхнего элемента
        int value = stack[top];
        top -= 1;

        if (value == minStack[minTop]) {
            minTop -= 1;
        }

        return value;
    }

    public int getMin() { // метод просмотра минимума
        return minStack[minTop];
    }
}
