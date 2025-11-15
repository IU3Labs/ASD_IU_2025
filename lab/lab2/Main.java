import student.Student;
import java.util.*;
import utils.TimeMeasurer;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 1000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите задание для выполнения:");
        System.out.println("1 - Основное задание (сравнение коллекций)");
        System.out.println("2 - Min Stack");
        System.out.println("3 - Circular Queue");
        System.out.println("4 - LRU Cache");
        System.out.println("5 - Stack с undo/redo");
        System.out.println("999 - Исследование capacity HashMap");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                runCollectionComparison();
                break;
            case 2:
                runMinStackDemo();
                break;
            case 3:
                runCircularQueueDemo();
                break;
            case 4:
                runLRUCacheDemo();
                break;
            case 5:
                runUndoRedoStackDemo();
                break;
            case 999:
                groupb.HashMapCapacityDemo.main(args);
                break;
            default:
                System.out.println("Неверный выбор");
        }

        scanner.close();
    }

    private static void runCollectionComparison() {
        List<Student> arrayList = new ArrayList<>();
        List<Student> linkedList = new LinkedList<>();
        Set<Student> hashSet = new HashSet<>();
        Map<Long, Student> hashMap = new HashMap<>();

        for (long i = 0; i < SIZE; i++) {
            Student student = new Student(i, "Student_" + i);
            arrayList.add(student);
            linkedList.add(student);
            hashSet.add(student);
            hashMap.put(i, student);
        }

        System.out.println("1. Добавление в конец:");
        System.out.printf("%-10s: %,d нс%n", "ArrayList", TimeMeasurer.measureTime(() -> arrayList.add(new Student(10000001L, "New"))));
        System.out.printf("%-10s: %,d нс%n", "LinkedList", TimeMeasurer.measureTime(() -> linkedList.add(new Student(10000001L, "New"))));
        System.out.printf("%-10s: %,d нс%n", "HashSet", TimeMeasurer.measureTime(() -> hashSet.add(new Student(10000001L, "New"))));
        System.out.printf("%-10s: %,d нс%n", "HashMap", TimeMeasurer.measureTime(() -> hashMap.put(10000001L, new Student(10000001L, "New"))));

        System.out.println("2. Добавление в начало:");
        System.out.printf("%-10s: %,d нс%n", "ArrayList", TimeMeasurer.measureTime(() -> arrayList.add(0, new Student(10000002L, "New"))));
        System.out.printf("%-10s: %,d нс%n", "LinkedList", TimeMeasurer.measureTime(() -> linkedList.add(0, new Student(10000002L, "New"))));
        System.out.printf("%-10s: %,d нс%n", "HashSet", TimeMeasurer.measureTime(() -> hashSet.add(new Student(10000002L, "New"))));
        System.out.printf("%-10s: %,d нс%n", "HashMap", TimeMeasurer.measureTime(() -> hashMap.put(10000002L, new Student(10000002L, "New"))));

        System.out.println("3. Удаление последнего элемента:");
        System.out.printf("%-10s: %,d нс%n", "ArrayList", TimeMeasurer.measureTime(() -> arrayList.remove(arrayList.size() - 1)));
        System.out.printf("%-10s: %,d нс%n", "LinkedList", TimeMeasurer.measureTime(() -> linkedList.remove(linkedList.size() - 1)));
        System.out.printf("%-10s: %,d нс%n", "HashSet", TimeMeasurer.measureTime(() -> hashSet.remove(new Student(999999L, ""))));
        System.out.printf("%-10s: %,d нс%n", "HashMap", TimeMeasurer.measureTime(() -> hashMap.remove(999999L)));

        System.out.println("4. Удаление первого элемента:");
        System.out.printf("%-10s: %,d нс%n", "ArrayList", TimeMeasurer.measureTime(() -> arrayList.remove(0)));
        System.out.printf("%-10s: %,d нс%n", "LinkedList", TimeMeasurer.measureTime(() -> linkedList.remove(0)));
        System.out.printf("%-10s: %,d нс%n", "HashSet", TimeMeasurer.measureTime(() -> hashSet.remove(new Student(0L, ""))));
        System.out.printf("%-10s: %,d нс%n", "HashMap", TimeMeasurer.measureTime(() -> hashMap.remove(0L)));

        System.out.println("5. Получение центрального элемента:");
        System.out.printf("%-10s: %,d нс%n", "ArrayList", TimeMeasurer.measureTime(() -> arrayList.get(500000)));
        System.out.printf("%-10s: %,d нс%n", "LinkedList", TimeMeasurer.measureTime(() -> linkedList.get(500000)));
        System.out.printf("%-10s: %,d нс%n", "HashSet", TimeMeasurer.measureTime(() -> hashSet.contains(new Student(500000L, ""))));
        System.out.printf("%-10s: %,d нс%n", "HashMap", TimeMeasurer.measureTime(() -> hashMap.get(500000L)));

        System.out.println("6. Получение последнего элемента:");
        System.out.printf("%-10s: %,d нс%n", "ArrayList", TimeMeasurer.measureTime(() -> arrayList.get(999999)));
        System.out.printf("%-10s: %,d нс%n", "LinkedList", TimeMeasurer.measureTime(() -> linkedList.get(999999)));
        System.out.printf("%-10s: %,d нс%n", "HashSet", TimeMeasurer.measureTime(() -> hashSet.contains(new Student(999999L, ""))));
        System.out.printf("%-10s: %,d нс%n", "HashMap", TimeMeasurer.measureTime(() -> hashMap.get(999999L)));
    }

    private static void runMinStackDemo() {
        groupa.MinStack minStack = new groupa.MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    private static void runCircularQueueDemo() {
        groupa.CircularQueue queue = new groupa.CircularQueue(3);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
        System.out.println(queue.isFull());
        queue.deQueue();
        queue.enQueue(4);
        System.out.println(queue.Rear());
    }

    private static void runLRUCacheDemo() {
        groupb.LRUCache cache = new groupb.LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private static void runUndoRedoStackDemo() {
        groupb.UndoRedoStack stack = new groupb.UndoRedoStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.printStack();

        stack.pop();
        stack.printStack();

        stack.undo();
        stack.printStack();

        stack.redo();
        stack.printStack();
    }
}