/*Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику. */

// Логика - Каждый элемент помимо собственного значения хранит значение минимального элемента на момент его добавления

import java.util.Scanner;

public class MinStack {

    //Узел, хранящий значение элемента, мин значение на момент добавления и ссылку на соседний узел
    private class Node {
        int value;
        int minValue;
        Node next;

        Node(int value, int minValue, Node next) {
            this.value = value;
            this.minValue = minValue;
            this.next = next;
        }
    }

    private Node head; //Последний добавленный элемент

    public MinStack() {
        head = null;
    }

    //добавление нового элемента стека
    public void add(int value) {
        if(head != null) {
            int currentMin = (head.minValue <= value) ? head.minValue : value;
            head = new Node(value, currentMin, head);
        }
        else {
            head = new Node(value, value, head);
        }

        System.out.println("Добавлен элемент " + value + ". Текущий минимум: " + head.minValue);

    }

    // Удаление верхнего элемента стека

    public void delete() {
        if (head == null) throw new IllegalStateException("Стек пуст. Удалить нечего.");
        int deletedValue = head.value;
        head = head.next;
        System.out.println("Мы удалили следующее значение:" + deletedValue);
    }

    // Получение минимального значения

    public int getMinValue(){
        if (head == null){
            throw new IllegalStateException("Стек пуст. Нельзя получить минимум.");
        }
        return head.minValue;
    }

    // Вывод стека

    public void print(){
        if (head == null) throw new IllegalStateException("Стек пуст. Вывести нечего.");;
        System.out.println("MinStack: ");
        Node temp = head;

        while (temp != null) {
            System.out.println("Значение: " + temp.value + " Минимальное значение при добавлении: " + temp.minValue);
            temp = temp.next;
        }
        System.out.println();
    }

    // Ручной ввод стека

    public void input(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите желаемый размер стека");
        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            System.out.println("Введите элемент: ");
            int element = scanner.nextInt();
            add(element);
        }

    }

    //получение размера стека
    public int getSize(){
        int size = 0;
        Node temp = head;

        while (temp != null) {
            size++;
            temp = temp.next;
        }

        return size;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.input();
        minStack.print();

        System.out.println("Размер стека: " + minStack.getSize());
        System.out.println("Удалим элемент");
        minStack.delete();
        System.out.println("Минимальное значение: " + minStack.getMinValue());
        System.out.println("Размер стека: " + minStack.getSize());
        minStack.print();

    }


}
