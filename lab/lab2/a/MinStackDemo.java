package lab2.a;

public class MinStackDemo {public static void main(String[] args) {
    MinStack stack = new MinStack();

    //проверка добавления
    stack.pushElement(69);
    stack.pushElement(420);
    stack.pushElement(52);
    stack.displayStackState();

    //текущий минимум
    System.out.println("\ncurrent minimum: " + stack.getCurrentMinimum());

    //удаление верхнего элемента
    stack.popElement();

    System.out.println("\nminimum after delete: " + stack.getCurrentMinimum());

    System.out.println("\ntop element: " + stack.getTopElement());

    System.out.println("\nstack size: " + stack.getSize());
    }
}
