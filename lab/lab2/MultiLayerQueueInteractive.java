//Реализовать «Многослойная очередь» (очередь очередей), которая
//поддерживает приоритетный режим обработки элементов.
//Прокомментировать код.

package lab2;

import java.util.*;

/**
 * Многослойная очередь (очередь очередей) с приоритетным режимом обработки
 * с поддержкой интерактивного ввода от пользователя
 */
public class MultiLayerQueueInteractive<T> {
    private final Map<Integer, Queue<T>> queues;
    private final List<Integer> priorities;
    private final Scanner scanner;

    /**
     * Конструктор с настройкой приоритетов через консоль
     */
    public MultiLayerQueueInteractive() {
        this.queues = new HashMap<>();
        this.priorities = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        setupPriorities();
    }

    /**
     * Настройка приоритетов через диалог с пользователем
     */
    private void setupPriorities() {
        while (true) {
            System.out.println("\nВыбери вариант:");
            System.out.println("1 - Использовать стандартные приоритеты (1-5)");
            System.out.println("2 - Ввести свои приоритеты");
            System.out.print("Ты выбрал: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // Стандартные приоритеты от 1 до 5
                for (int i = 1; i <= 5; i++) {
                    queues.put(i, new LinkedList<>());
                    priorities.add(i);
                }
                Collections.sort(priorities);
                System.out.println("Установлены стандартные приоритеты: " + priorities);
                break;
            } else if (choice.equals("2")) {
                // Пользовательские приоритеты
                setupCustomPriorities();
                break;
            } else {
                System.out.println("Неверно! Попробуйте снова.");
            }
        }
    }

    /**
     * Настройка пользовательских приоритетов
     */
    private void setupCustomPriorities() {
        System.out.print("Введи приоритеты через пробел (например: 1 2 3 5 10): ");

        try {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            for (String part : parts) {
                int priority = Integer.parseInt(part.trim());
                if (!queues.containsKey(priority)) {
                    queues.put(priority, new LinkedList<>());
                    priorities.add(priority);
                }
            }

            Collections.sort(priorities);
            System.out.println("Установлены приоритеты: " + priorities);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Введи числа через пробел.");
            setupCustomPriorities(); // Рекурсивный вызов при ошибке
        }
    }

    /**
     * Добавляет элемент с выбором приоритета через консоль
     */
    public void addInteractive() {
        System.out.println("\n--- Добавление элемента ---");

        // Ввод значения элемента
        System.out.print("Введи значение элемента: ");
        T element = (T) scanner.nextLine();

        // Выбор приоритета
        int priority = choosePriority();

        // Добавление элемента
        queues.get(priority).add(element);
        System.out.println("Добавлен элемент '" + element + "' с приоритетом " + priority);
    }

    /**
     * Выбор приоритета из доступных
     */
    private int choosePriority() {
        while (true) {
            System.out.println("Доступные приоритеты: " + priorities);
            System.out.print("Выберите приоритет: ");

            try {
                int priority = Integer.parseInt(scanner.nextLine());
                if (queues.containsKey(priority)) {
                    return priority;
                } else {
                    System.out.println("Приоритет " + priority + " не существует!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            }
        }
    }

    /**
     * Интерактивное извлечение элементов
     */
    public void pollInteractive() {
        System.out.println("\n--- Извлечение элемента ---");

        T element = poll();
        if (element != null) {
            System.out.println("Извлечен элемент: '" + element + "'");
        } else {
            System.out.println("Очередь пуста!");
        }
    }

    /**
     * Основная логика извлечения
     */
    public T poll() {
        for (int priority : priorities) {
            Queue<T> queue = queues.get(priority);
            if (!queue.isEmpty()) {
                return queue.poll();
            }
        }
        return null;
    }

    /**
     * Показать следующий элемент без извлечения
     */
    public void peekInteractive() {
        System.out.println("\n--- Просмотр следующего элемента ---");

        T element = peek();
        if (element != null) {
            // Находим приоритет элемента
            for (int priority : priorities) {
                Queue<T> queue = queues.get(priority);
                if (!queue.isEmpty() && queue.peek().equals(element)) {
                    System.out.println("Следующий элемент: '" + element + "' (приоритет " + priority + ")");
                    return;
                }
            }
        } else {
            System.out.println("Очередь пуста!");
        }
    }

    /**
     * Основная логика просмотра
     */
    public T peek() {
        for (int priority : priorities) {
            Queue<T> queue = queues.get(priority);
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }
        return null;
    }

    /**
     * Показать статистику
     */
    public void printStatistics() {
        System.out.println("\n=== Статистика очереди ===");
        System.out.println("Всего элементов: " + size());
        System.out.println("Очередь пуста: " + isEmpty());

        for (int priority : priorities) {
            int count = queues.get(priority).size();
            System.out.println("Приоритет " + priority + ": " + count + " элементов");
        }
        System.out.println("==========================\n");
    }

    /**
     * Показать все элементы (для отладки)
     */
    public void showAllElements() {
        System.out.println("\n--- Все элементы в очереди ---");

        boolean hasElements = false;
        for (int priority : priorities) {
            Queue<T> queue = queues.get(priority);
            if (!queue.isEmpty()) {
                System.out.println("Приоритет " + priority + ": " + queue);
                hasElements = true;
            }
        }

        if (!hasElements) {
            System.out.println("Очередь пуста");
        }
    }

    public boolean isEmpty() {
        for (Queue<T> queue : queues.values()) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        int total = 0;
        for (Queue<T> queue : queues.values()) {
            total += queue.size();
        }
        return total;
    }

    /**
     * Главное меню для взаимодействия с пользователем
     */
    public void showMenu() {
        System.out.println("\n=== Многослойная очередь ===");
        System.out.println("Доступные приоритеты: " + priorities);

        while (true) {
            System.out.println("\n--- Главное меню ---");
            System.out.println("1) Добавить элемент");
            System.out.println("2) Извлечь элемент");
            System.out.println("3) Показать следующий элемент");
            System.out.println("4) Показать статистику");
            System.out.println("5) Показать все элементы");
            System.out.println("0) Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addInteractive();
                    break;
                case "2":
                    pollInteractive();
                    break;
                case "3":
                    peekInteractive();
                    break;
                case "4":
                    printStatistics();
                    break;
                case "5":
                    showAllElements();
                    break;
                case "0":
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    /**
     * Основная программа с интерактивным вводом
     */
    public static void main(String[] args) {
        System.out.println("Многослойная очередь с интерактивным вводом");

        // Создаем очередь
        MultiLayerQueueInteractive<String> queue = new MultiLayerQueueInteractive<>();

        // Показываем главное меню
        queue.showMenu();

        System.out.println("Программа завершена.");
    }
}