/**
 * Группа А. Задание 1
 * Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
 */

import java.util.*;
public class MinStack {

    private List<Integer> mainList; // Список со всеми элементами стека
    private Stack<Integer> minStack; // Стек минимальных значений

    public MinStack() {
        mainList = new ArrayList<>(); // основной список
        minStack = new Stack<>();     // стек для отслеживания минимума
    }

    public void push(int value) { // Добавление элемента в конец стека
        mainList.add(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value); // если новый элемент <= текущему минимуму, то добавляем в minStack
        }
    }

    public int pop() { // Удаление элемента с конца
        if (mainList.isEmpty()) {
            throw new EmptyStackException();
        }
        int removed = mainList.remove(mainList.size() - 1); // удаляем последний элемент
        if (!minStack.isEmpty() && removed == minStack.peek()) {
            minStack.pop(); // если удалённый элемент был минимумом, то удаляем из minStack
        }
        return removed;
    }

    public int peek() {
        if (mainList.isEmpty()) throw new EmptyStackException();
        return mainList.get(mainList.size() - 1);
    }

    public void pushToStart(int value) { // Добавление элемента в начало стека
        mainList.add(0, value);  // вставляем элемент в начало
        rebuildMinStack(); // пересчитываем minStack, потому что порядок поменялся
    }

    // Добавление элемента в середину
    public void pushToMiddle(int value) {
        int middle = mainList.size() / 2; // находим середину
        mainList.add(middle, value);
        rebuildMinStack();  // пересчитываем minStack, потому что порядок поменялся
    }

    // Удаление элемента с начала
    public int popFromStart() {
        if (mainList.isEmpty()) throw new EmptyStackException();
        int val = mainList.remove(0);
        rebuildMinStack();  // пересчитываем minStack, потому что порядок поменялся
        return val;
    }

    public int popFromMiddle() { // Удаление элемента из середины
        if (mainList.isEmpty()) throw new EmptyStackException();
        int mid = mainList.size() / 2;
        int val = mainList.remove(mid);
        rebuildMinStack();  // пересчитываем minStack, потому что порядок поменялся
        return val;
    }

    public int getMin() { // Получаем текущий минимум
        if (minStack.isEmpty()) throw new EmptyStackException();
        return minStack.peek();
    }

    public int size() { // Размер стека
        return mainList.size();
    }

    public void printForward() { // Печать стека сверху вниз
        System.out.print("Стек сверху вниз: ");
        for (int i = mainList.size() - 1; i >= 0; i--) {
            System.out.print(mainList.get(i));
            if (i != 0) System.out.print(" ");
        }
        System.out.println();
    }

    public void printBackward() { // Печать стека снизу вверх
        if (mainList.isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.print("Стек снизу вверх: ");
        for (int i = 0; i < mainList.size(); i++) {
            System.out.print(mainList.get(i));
            if (i != mainList.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }

    private void rebuildMinStack() { // Пересчёт minStack
        minStack.clear();
        if (mainList.isEmpty()) return;

        int curMin = Integer.MAX_VALUE;
        for (Integer v : mainList) {
            if (v <= curMin) {
                curMin = v;
                minStack.push(curMin);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация:  ");
        MinStack stack = new MinStack();

        System.out.println("\n1) Добавляет в конец:");
        stack.push(7);
        stack.push(9);
        stack.push(2);
        stack.push(4);
        System.out.println("Добавлены: 7, 9, 2, 4");
        stack.printForward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());

        System.out.println("\n2) Добавляет в начало:");
        stack.pushToStart(3);
        stack.printForward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());

        System.out.println("\n3) Добавляет в середину:");
        stack.pushToMiddle(3);
        stack.printForward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());

        System.out.println("\n4) Удаляет из конца:");
        System.out.println("Удалённый элемент: " + stack.pop());
        stack.printForward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());

        System.out.println("\n5) Удаляет из начала:");
        System.out.println("Удалённый элемент: " + stack.popFromStart());
        stack.printForward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());

        System.out.println("\n6) Удаляет из середины:");
        System.out.println("Удалённый элемент: " + stack.popFromMiddle());
        stack.printForward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());

        System.out.println("\n7) Печать в обратном порядке:");
        stack.printBackward();
        System.out.println("Размер: " + stack.size());
        System.out.println("Минимум: " + stack.getMin());
    }
}
/**
 * Демонстрация:
 *
 * 1) Добавляет в конец:
 * Добавлены: 7, 9, 2, 4
 * Стек сверху вниз: 4 2 9 7
 * Размер: 4
 * Минимум: 2
 *
 * 2) Добавляет в начало:
 * Стек сверху вниз: 4 2 9 7 3
 * Размер: 5
 * Минимум: 2
 *
 * 3) Добавляет в середину:
 * Стек сверху вниз: 4 2 9 3 7 3
 * Размер: 6
 * Минимум: 2
 *
 * 4) Удаляет из конца:
 * Удалённый элемент: 4
 * Стек сверху вниз: 2 9 3 7 3
 * Размер: 5
 * Минимум: 2
 *
 * 5) Удаляет из начала:
 * Удалённый элемент: 3
 * Стек сверху вниз: 2 9 3 7
 * Размер: 4
 * Минимум: 2
 *
 * 6) Удаляет из середины:
 * Удалённый элемент: 9
 * Стек сверху вниз: 2 3 7
 * Размер: 3
 * Минимум: 2
 *
 * 7) Печать в обратном порядке:
 * Стек снизу вверх: 7 3 2
 * Размер: 3
 * Минимум: 2
 */