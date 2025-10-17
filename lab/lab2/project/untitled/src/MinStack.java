/*
Реализовать "Стек с минимумом". Прокомментировать логику
 */
import java.util.Scanner;
public class MinStack {
    private static class Node {
        int value;       // значение элемента
        int minValue;    // минимальное значение в момент добавления
        Node next;       // ссылка на следующий элемент
        Node(int value, int minValue, Node next) {
            this.value = value;
            this.minValue = minValue;
            this.next = next;
        }
    }
    private Node top;
    public MinStack() {
        top = null;
    }
    // Добавление нового элемента
    public void push(int num) {
        if (top == null) {
            top = new Node(num, num, null);
        } else {
            int minNow = Math.min(num, top.minValue);
            top = new Node(num, minNow, top);
        }
        System.out.println("Добавлен элемент: " + num + " (минимум сейчас: " + getMin() + ")");
    }
    // Удаление верхнего элемента
    public int pop() {
        if (top == null) {
            System.out.println("Стек пуст — удалять нечего");
            return -1;
        }
        int removed = top.value;
        top = top.next;
        return removed;
    }
    // Просмотр верхнего элемента
    public int peek() {
        if (top == null) {
            System.out.println("Стек пуст");
            return -1;
        }
        return top.value;
    }
    public int getMin() {
        if (top == null) {
            System.out.println("Стек пуст — минимума нет");
            return -1;
        }
        return top.minValue;
    }
    public boolean isEmpty() {
        return top == null;
    }
    public void showStack() {
        if (top == null) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.println("Текущее содержимое стека:");
        Node temp = top;
        while (temp != null) {
            System.out.println("Значение: " + temp.value + " | Мин: " + temp.minValue);
            temp = temp.next;
        }
        System.out.println();
    }
    public void fillStack() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int count = sc.nextInt();
        System.out.println("Введите элементы стека:");
        for (int i = 0; i < count; i++) {
            int val = sc.nextInt();
            push(val);
        }
    }
    public static void main(String[] args) {
        MinStack s = new MinStack();
        s.fillStack();
        s.push(3);
        s.push(9);
        s.push(2);
        s.showStack();
        System.out.println("Минимум: " + s.getMin());
        s.pop();
        System.out.println("После удаления минимум: " + s.getMin());
        s.push(1);
        System.out.println("Верхний элемент: " + s.peek());
        System.out.println("Минимум: " + s.getMin());
        s.showStack();
    }
}

