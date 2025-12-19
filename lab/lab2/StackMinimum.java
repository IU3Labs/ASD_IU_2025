

import java.util.Scanner;

public class StackMinimum {

    // Внутренний узел стека
    private static class StackItem {
        int value;
        int currentMin; // минимум на момент добавления
        StackItem prevItem; // ссылка на следующий (нижний) узел

        StackItem(int value, int currentMin, StackItem prevItem) {
            this.value = value;
            this.currentMin = currentMin;
            this.prevItem = prevItem;
        }
    }

    private StackItem head; // вершина стека

    public StackMinimum() {
        head = null;
    }

    // Добавление элемента в стек
    public void push(int number) {
        if (head == null) {
            head = new StackItem(number, number, null);
        } else {
            // Сравниваем и записываем минимум (текущий или новый)
            int newMin = Math.min(number, head.currentMin);
            head = new StackItem(number, newMin, head);
        }
        System.out.println("Добавлен элемент: " + number + " | Текущий минимум: " + getMin());
    }

    // Удаление верхнего элемента
    public int pop() {
        if (head == null) {
            System.out.println("Стек пуст — удаление невозможно");
            return -1;
        }
        int removedValue = head.value;
        head = head.prevItem;
        return removedValue;
    }

    // Просмотр верхнего элемента без удаления
    public int peek() {
        if (head == null) {
            System.out.println("Стек пуст");
            return -1;
        }
        return head.value;
    }

    // Получение текущего минимума
    public int getMin() {
        if (head == null) {
            System.out.println("Стек пуст — минимум отсутствует");
            return -1;
        }
        return head.currentMin;
    }

    // Проверка, пуст ли стек
    public boolean isEmpty() {
        return head == null;
    }

    // Печать содержимого стека
    public void display() {
        if (head == null) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.println("Текущее состояние стека:");
        StackItem current = head;
        while (current != null) {
            System.out.println("Значение: " + current.value + " | Мин. при добавлении: " + current.currentMin);
            current = current.prevItem;
        }
        System.out.println();
    }

    // Заполнение стека пользователем
    public void fillFromConsole() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int count = input.nextInt();
        System.out.println("Введите элементы:");
        for (int i = 0; i < count; i++) {
            push(input.nextInt());
        }
    }

    public static void main(String[] args) {
        StackMinimum minStack = new StackMinimum();
        minStack.fillFromConsole();

        minStack.push(3);
        minStack.push(9);
        minStack.push(2);
        minStack.display();

        System.out.println("Минимум: " + minStack.getMin());
        minStack.pop();
        System.out.println("После удаления минимум: " + minStack.getMin());
    }
}