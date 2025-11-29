import java.util.*;

public class MinStack {
    // Основной стек — хранит все элементы
    private final Stack<Integer> data = new Stack<>();
    // Вспомогательный стек — хранит текущие минимумы (дублирует минимум при необходимости)
    private final Stack<Integer> minStack = new Stack<>();

    /** O(1) — добавление элемента */
    public void push(int value) {
        data.push(value);
        // Если новый элемент ≤ текущему минимуму — кладём его в minStack
        // Иначе повторяем текущий минимум (чтобы размеры стеков совпадали)
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        } else {
            minStack.push(minStack.peek());
        }
    }

    /** O(1) — удаление элемента */
    public void pop() {
        if (!data.isEmpty()) {
            data.pop();
            minStack.pop(); // размеры всегда одинаковые
        }
    }

    /** O(1) — получение минимума */
    public int getMin() {
        if (minStack.isEmpty()) throw new EmptyStackException();
        return minStack.peek();
    }

    public int size() { return data.size(); }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.push(2);
        System.out.println("Min: " + stack.getMin()); // 2
        stack.pop();
        System.out.println("Min after pop: " + stack.getMin()); // 3
    }
}