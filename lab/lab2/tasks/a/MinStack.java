package org.example.lab2.tasks.a;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Стек, умеющий возвращать минимум за O(1).
 * В демонстрации показаны все операции и логика работы.
 */
public class MinStack<E extends Comparable<E>> {
    private final Deque<E> data = new ArrayDeque<>();
    private final Deque<E> mins = new ArrayDeque<>();

    public void push(E e) {
        data.push(e);
        if (mins.isEmpty() || e.compareTo(mins.peek()) <= 0) {
            mins.push(e);
        }
    }

    public E pop() {
        E val = data.pop();
        if (val.equals(mins.peek())) {
            mins.pop();
        }
        return val;
    }

    public E getMin() {
        return mins.peek();
    }

    public int size() {
        return data.size();
    }

    public String printForward() {
        return data.toString();
    }

    public static void main(String[] args) {
        MinStack<Integer> st = new MinStack<>();
        System.out.println("Добавляем 3,5,2,4:");
        st.push(3);
        st.push(5);
        st.push(2);
        st.push(4);
        System.out.println("Стек: " + st.printForward());
        System.out.println("Минимум: " + st.getMin());
        st.pop();
        System.out.println("После pop: " + st.printForward());
        System.out.println("Минимум: " + st.getMin());
    }
}