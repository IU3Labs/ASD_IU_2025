//Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

package minstack;

import java.util.Stack;

public class DataTypeMinStack {
    private Stack<Integer> mainStack; //основной стэк
    private Stack<Integer> minStack; //вспомогательный

    public DataTypeMinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value){ //добавление минимума
        mainStack.push(value); //всегда новые элементы добавляются в основной стэк для хранения
        if (minStack.isEmpty() || value <= minStack.peek()){
            minStack.push(value); //обновляем минимум
        }
    }

    public void pop() { //удаление верхнего элемента
        if (mainStack.isEmpty()) {
            System.out.println("Стэк пуст!");
            return;
        }
        int removed = mainStack.pop(); //удаление из основного стэка
        if (removed == minStack.peek()) { //если удаленное значение совпадает с минимумом, удаляем и его
            minStack.pop();
        }
    }

    public int getMin() { //получение минимального элемента
        if (minStack.isEmpty()){
            throw new RuntimeException("Стэк пуст!");
        }
        return minStack.peek();
    }

    public int top() { //возврат верхнего элемента
        if (mainStack.isEmpty()){
            throw new RuntimeException("Стэк пуст!");
        }
        return mainStack.peek();
    }

    public boolean isEmpty() { //проверка стэка на пустоту
        return mainStack.isEmpty();
    }
}
