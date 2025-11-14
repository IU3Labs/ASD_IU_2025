import java.util.*;

/*
 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
 Логика Min Stack:
 структура использует два списка — elements для хранения всех добавленных значений и minValues
 для отслеживания минимальных. Каждый раз при добавлении нового элемента он обязательно попадает в elements.
 Дополнительно этот же элемент заносится в minValues только в том случае, если список минимумов пуст
 или новое значение меньше либо равно текущему минимуму (последнему элементу minValues).
 Таким образом, в конце minValues всегда лежит актуальный минимум стека. При удалении мы сначала снимаем элемент
 с вершины elements, а из minValues удаляем значение только тогда, когда снятый элемент совпадает с текущим минимумом.
 Благодаря этому операции получения минимума выполняются за O(1), так как минимальный элемент всегда доступен
 как последний элемент списка minValues.
*/

public class Main1A {
    private List<Integer> elements;   // Хранение элементов
    private List<Integer> minValues;  // Хранение минимумов

    public Main1A() {
        elements = new ArrayList<>();
        minValues = new ArrayList<>();
    }

    // Добавление элемента в конец
    public void push(int value) {
        elements.add(value);
        if (minValues.isEmpty() || value <= getLastElement(minValues)) {
            minValues.add(value);
        }
    }

    // Добавление элемента в начало
    public void pushFront(int value) {
        elements.add(0, value);
        rebuildMinList();
    }

    // Добавление элемента в середину
    public void pushMiddle(int value) {
        int middleIndex = elements.size() / 2;
        elements.add(middleIndex, value);
        rebuildMinList();
    }

    // Пересчитываем список минимумов
    private void rebuildMinList() {
        minValues.clear();
        if (elements.isEmpty()) return;
        int currentMin = elements.get(0);
        minValues.add(currentMin);
        for (int i = 1; i < elements.size(); i++) {
            currentMin = Math.min(currentMin, elements.get(i));
            minValues.add(currentMin);
        }
    }

    // Удаление элемента из конца
    public int pop() {
        int value = removeLastElement(elements);
        if (!minValues.isEmpty() && value == getLastElement(minValues)) {
            removeLastElement(minValues);
        }
        return value;
    }

    // Удаление элемента из начала
    public int popFront() {
        int value = elements.remove(0);
        rebuildMinList();
        return value;
    }

    // Удаление элемента из середины
    public int popMiddle() {
        int middleIndex = elements.size() / 2;
        int value = elements.remove(middleIndex);
        rebuildMinList();
        return value;
    }

    // Получение минимального элемента
    public int getMin() {
        if (minValues.isEmpty()) {
            throw new EmptyStackException();
        }
        return getLastElement(minValues);
    }

    // Кол-во элементов
    public int size() {
        return elements.size();
    }

    // Печать в прямом порядке (сверху вниз)
    public void printForward() {
        System.out.print("Стек (сверху вниз): ");
        for (int i = elements.size() - 1; i >= 0; i--) {
            System.out.print(elements.get(i) + " ");
        }
        System.out.println();
    }

    // Печать в обратном порядке (снизу вверх)
    public void printBackward() {
        if (elements.isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }
        System.out.print("Стек (снизу вверх): ");
        for (int i = 0; i < elements.size(); i++) {
            System.out.print(elements.get(i) + " ");
        }
        System.out.println();
    }

    // Получение последнего элемента списка
    private int getLastElement(List<Integer> list) {
        return list.get(list.size() - 1);
    }

    // Удаление последнего элемента списка
    private int removeLastElement(List<Integer> list) {
        return list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println("Демонстрация стека с минимумом");
        Main1A stack = new Main1A();

        System.out.println("\n1.Добавление в конец");
        stack.push(8);
        stack.push(2);
        stack.push(11);
        System.out.println("Добавлены 8, 2, 11");
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n2.Добавление в начало:");
        stack.pushFront(6);
        System.out.println("Добавление 6");
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n3.Добавление в середину:");
        stack.pushMiddle(4);
        System.out.println("Добавление 4");
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n4. Удаление с конца: " + stack.pop());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n5.Удаление с начала: " + stack.popFront());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n6.Удаление из середины: " + stack.popMiddle());
        stack.printForward();
        System.out.println("Минимум: " + stack.getMin());
        System.out.println("Размер: " + stack.size());

        System.out.println("\n7.Печать в обратном порядке:");
        stack.printBackward();
        System.out.println("Размер: " + stack.size());
    }
}

/*
Демонстрация стека с минимумом

1.Добавление в конец
Добавлены 8, 2, 11
Стек (сверху вниз): 11 2 8
Минимум: 2
Размер: 3

2.Добавление в начало:
Добавление 6
Стек (сверху вниз): 11 2 8 6
Минимум: 2
Размер: 4

3.Добавление в середину:
Добавление 4
Стек (сверху вниз): 11 2 4 8 6
Минимум: 2
Размер: 5

4. Удаление с конца: 11
Стек (сверху вниз): 2 4 8 6
Минимум: 2
Размер: 4

5.Удаление с начала: 6
Стек (сверху вниз): 2 4 8
Минимум: 2
Размер: 3

6.Удаление из середины: 4
Стек (сверху вниз): 2 8
Минимум: 2
Размер: 2

7.Печать в обратном порядке:
Стек (снизу вверх): 8 2
Размер: 2
*/
