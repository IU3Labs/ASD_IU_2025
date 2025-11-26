package lab2.a;

//реализация стека с минимумом

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    //основной стек для хранения всех элементов
    private final Deque<Integer> elementStorage = new ArrayDeque();
    //вспомогательный стек для отслеживания текущих минимальных значений
    private final Deque<Integer> minimumTracker = new ArrayDeque();

    //добавление нового элемента в стек
    public void pushElement(int elementValue) {
        //всегда добавляем элемент в основной стек
        elementStorage.push(elementValue);
        //во вспомогательный стек добавляем элемент только если стек минимумов пустой, или новый элемент меньше или равен текущему минимуму
        if (minimumTracker.isEmpty() || elementValue <= minimumTracker.peek()) {
            minimumTracker.push(elementValue);
        }
    }

    //удаление верхнего элемента из стека
    public void popElement() {
        if (elementStorage.isEmpty()) {
            System.out.println("stack is empty");
        } else {
            //удаляем элемент из основного стека
            int removedElement = elementStorage.pop();
            //если удаляемый элемент был текущим минимумом, то удаляем его и из стека минимумов
            if (!minimumTracker.isEmpty() && removedElement == minimumTracker.peek()) {
                minimumTracker.pop();
            }
        }
    }

    //верхнего элемента без удаления
    public int getTopElement() {
        if (elementStorage.isEmpty()) {
            System.out.println("stack is empty");
        }
        return elementStorage.peek();
    }

    //текущий минимальный элемента в стеке
    public int getCurrentMinimum() {
        if (minimumTracker.isEmpty()) {
            System.out.println("stack is empty");
        }
        //верхний элемент стека минимумов всегда содержит текущий минимум
        return minimumTracker.peek();
    }

    //пустой стек или нет
    public boolean isEmpty() {
        return elementStorage.isEmpty();
    }

    //получение количества элементов в стеке
    public int getSize() {
        return this.elementStorage.size();
    }

    //отображение текущего состояния стека
    public void displayStackState() {
        System.out.println("Stack:");
        System.out.println("elements: " + elementStorage);
        System.out.println("minimim: " + minimumTracker);
        System.out.println("current minimum: " + (minimumTracker.isEmpty() ? "нет" : getCurrentMinimum()));
        System.out.println("size: " + getSize());
    }
}
