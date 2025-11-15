/* Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.*/

import java.util.Stack;

public class MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.add(10);
        stack.add(4);
        stack.add(7);
        stack.add(2);
        stack.add(5);

        System.out.println("Текущий минимум: " + stack.currentMin());
        stack.removeTop();
        System.out.println("Минимум после удаления: " + stack.currentMin());
        stack.removeTop();
        System.out.println("Минимум после удаления: " + stack.currentMin());
        System.out.println("Верхний элемент: " + stack.peekTop());
    }

    private Stack<Integer> mainStorage;         // основной стек
    private Stack<Integer> minTracker;          // стек минимальных значений

    public MinStack() {
        mainStorage = new Stack<>();
        minTracker = new Stack<>();
    }

    // возвращает текущий мнимум
    public int currentMin() {
        if (minTracker.isEmpty()) {
            throw new RuntimeException("Стек пуст :(");
        }
        return minTracker.peek();
    }

    //Добавление нового элемента
    public void add(int value) {
        mainStorage.push(value);
        if (minTracker.isEmpty() || value <= minTracker.peek()) {
            minTracker.push(value);
        }
    }

    // Проверка на пустоту
    public boolean empty() {
        return mainStorage.isEmpty();
    }

     // Удаление верхнего элемента
    public void removeTop() {
        if (mainStorage.isEmpty()) {
            System.out.println("Cтек пуст :(");
            return;
        }
        int removed = mainStorage.pop();
        if (!minTracker.isEmpty() && removed == minTracker.peek()) {
            minTracker.pop();
        }
    }

    // Возвращает верхний элемент стека без удаления
    public int peekTop() {
        if (mainStorage.isEmpty()) {
            throw new RuntimeException("Стек пуст :(");
        }
        return mainStorage.peek();
    }
}
