package com.iu3.tasks;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        demonstrateCapacityGrowth();
        demonstrateCustomInitialCapacity();
        demonstrateTrimToSize();
    }

    public static void demonstrateCapacityGrowth() {
        System.out.println("Демонстрация роста capacity в ArrayList");
        System.out.println("=" .repeat(50));

        // Эмуляция ArrayList с начальной capacity = 10
        ArrayListEmulator<String> list = new ArrayListEmulator<>();

        System.out.println("Начальное состояние:");
        list.printStatus();

        System.out.println("\nПроцесс добавления элементов:");
        System.out.println("-".repeat(50));

        for (int i = 1; i <= 25; i++) {
            String element = "Элемент - " + i;
            boolean capacityChanged = list.addWithTracking(element);

            if (capacityChanged || i <= 3 || i >= 23) {
                System.out.printf("Добавлен: %-12s | ", element);
                list.printStatus();
            }

            // Показываем моменты увеличения capacity
            if (capacityChanged) {
                System.out.println("capacity увеличена");
            }
        }

    }

    public static void demonstrateCustomInitialCapacity() {
        System.out.println("\nДемонстрация со своей начальной capacity");
        System.out.println("=" .repeat(50));

        ArrayListEmulator<Integer> customList = new ArrayListEmulator<>(5);
        System.out.println("Начальная capacity установлена в 5");
        customList.printStatus();

        for (int i = 1; i <= 12; i++) {
            boolean changed = customList.addWithTracking(i);
            System.out.printf("Добавлен: %-3d | ", i);
            customList.printStatus();

            if (changed) {
                System.out.println("capacity увеличена");
            }
        }
    }

    public static void demonstrateTrimToSize() {
        System.out.println("\nдемо trimToSize()");
        System.out.println("=" .repeat(50));

        ArrayListEmulator<String> list = new ArrayListEmulator<>();

        // Добавляем несколько элементов
        for (int i = 1; i <= 8; i++) {
            list.add("Элемент-" + i);
        }

        System.out.println("Состояние до trimToSize():");
        list.printStatus();

        // Эмулируем ситуацию, когда capacity больше чем нужно
        System.out.println("\nВызываем trimToSize()");
        list.trimToSize();

        System.out.println("Состояние после trimToSize():");
        list.printStatus();
    }

    /**
     * Эмулятор ArrayList с визуализацией изменения capacity
     */
    private static class ArrayListEmulator<E> {
        private static final int DEFAULT_CAPACITY = 10;
        private Object[] elementData;
        private int size;
        private int capacity;
        private int growthCount = 0; // счетчик увеличений capacity

        public ArrayListEmulator() {
            this(DEFAULT_CAPACITY);
        }

        public ArrayListEmulator(int initialCapacity) {
            this.capacity = initialCapacity;
            this.elementData = new Object[initialCapacity];
            this.size = 0;
        }

        /**
         * Добавляет элемент и возвращает true, если capacity была увеличена
         */
        public boolean addWithTracking(E element) {
            boolean capacityChanged = false;

            if (size >= capacity) {
                int oldCapacity = capacity;
                grow();
                capacityChanged = true;
                growthCount++;
                System.out.printf("Рост: %2d → %2d (элемент %d)%n",
                        oldCapacity, capacity, size + 1);
            }

            elementData[size] = element;
            size++;
            return capacityChanged;
        }

        public void add(E element) {
            if (size >= capacity) {
                grow();
            }
            elementData[size] = element;
            size++;
        }

        /**
         * Эмуляция метода grow() из ArrayList
         */
        private void grow() {
            int oldCapacity = capacity;

            // Реальная формула из ArrayList
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            // Гарантируем минимальный рост
            if (newCapacity - oldCapacity < 1) {
                newCapacity = oldCapacity + 1;
            }

            capacity = newCapacity;
            elementData = Arrays.copyOf(elementData, capacity);
        }

        /**
         * Эмуляция trimToSize() - уменьшает capacity до текущего size
         */
        public void trimToSize() {
            if (size < capacity) {
                capacity = size;
                elementData = Arrays.copyOf(elementData, capacity);
            }
        }

        public void printStatus() {
            double fillPercentage = (size * 100.0) / capacity;
            String fillBar = createFillBar(size, capacity);

            System.out.printf("Size: %2d | Capacity: %2d | Заполнение: %5.1f%% %s%n",
                    size, capacity, fillPercentage, fillBar);
        }

        private String createFillBar(int current, int total) {
            int barLength = 20;
            int filled = (current * barLength) / total;
            filled = Math.max(1, filled); // Минимум один символ если есть элементы

            return "[" + "█".repeat(filled) + "·".repeat(barLength - filled) + "]";
        }

        public int getSize() { return size; }
        public int getCapacity() { return capacity; }
        public int getGrowthCount() { return growthCount; }
    }
}