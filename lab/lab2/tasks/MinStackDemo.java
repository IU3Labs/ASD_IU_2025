package tasks;

public class MinStackDemo {
    public static void main(String[] args) {
        MinStack stack = new MinStack(5);

        System.out.println("=== Тестирование MinStack ===");

        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.push(2);
        stack.push(4);

        System.out.println("Минимум: " + stack.getMin()); // Должен быть 2
        System.out.println("Верхний: " + stack.top());    // Должен быть 4

        stack.pop();
        System.out.println("После pop():");
        System.out.println("Минимум: " + stack.getMin()); // Должен быть 2
        System.out.println("Верхний: " + stack.top());    // Должен быть 2

        stack.pop();
        System.out.println("После pop():");
        System.out.println("Минимум: " + stack.getMin()); // Должен быть 3
        System.out.println("Верхний: " + stack.top());    // Должен быть 8
    }
}