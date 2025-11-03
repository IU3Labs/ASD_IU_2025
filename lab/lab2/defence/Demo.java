package defence;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Map<Object, Object> normalMap = new HashMap<>();
        Map<Object, Object> degenerateMap = new HashMap<>();

        measureMaps(normalMap, degenerateMap);

        /*HashMap: 33100ns
        Degenerated in LinkedList HashMap: 145000ns
        LinkedList: 1058100ns*/

        //Полученный результат быстрее LinkedList-а потому что на самом деле, вырожденная HashMap в последних версиях Java оптимизирована и представляет собой Red Black Tree

        demoArrayListCapacity();

        /*Demo of ArrayList capacity
        Default capacity: 10
        Addition of elements and increase of capacity:
        Size: 1, capacity: 10, filled: 10.0%
        Size: 11, capacity: 15, filled: 73.33333333333333%
        >>> Capacity has increased from 10 to 15 while adding 11 element
        >>> Capacity has increased from 15 to 22 while adding 16 element
        Size: 21, capacity: 22, filled: 95.45454545454545%
        >>> Capacity has increased from 22 to 33 while adding 23 element
        Size: 31, capacity: 33, filled: 93.93939393939394%
        >>> Capacity has increased from 33 to 49 while adding 34 element
        Size: 41, capacity: 49, filled: 83.6734693877551%
        Size: 50, capacity: 73, filled: 68.4931506849315%
        >>> Capacity has increased from 49 to 73 while adding 50 element
        ArrayList with given default capacity = 5:
        Default capacity: 5
        Size: 1, capacity: 5
        Size: 2, capacity: 5
        Size: 3, capacity: 5
        Size: 4, capacity: 5
        Size: 5, capacity: 5
        Size: 6, capacity: 7
        >>> Capacity increased from 5 to 7
        Size: 7, capacity: 7
        Size: 8, capacity: 10
        >>> Capacity increased from 7 to 10
        Size: 9, capacity: 10
        Size: 10, capacity: 10
        Size: 11, capacity: 15
        >>> Capacity increased from 10 to 15
        Size: 12, capacity: 15
        Using trimToSize()
        After trimToSize(), capacity: 12 (was: 15, size: 12)

        Process finished with exit code 0
        */
    }

    public static void measureMaps(Map<Object, Object> correct, Map<Object, Object> incorrect) {
        for (int i = 0; i < 10000; i++) {
            correct.put("key" + i, "value" + i);
            incorrect.put(new incorrectKey<>("key" + i), "value" + i);
        }

        // Нормальная Map
        long start = System.nanoTime();
        Object normalResult = correct.get("key9999");
        long normalTime = System.nanoTime() - start;

        // Вырожденная Map
        start = System.nanoTime();
        Object degenerateResult = incorrect.get(new incorrectKey<>("key9999"));
        long degenerateTime = System.nanoTime() - start;

        // LinkedList для сравнения
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            linkedList.add("value" + i);
        }

        // Время доступа для LinkedList (поиск по значению)
        start = System.nanoTime();
        String linkedListResult = null;
        for (String item : linkedList) {
            if (item.equals("value9999")) {
                linkedListResult = item;
                break;
            }
        }
        long linkedListTime = System.nanoTime() - start;

        // Результаты в нс
        System.out.println("HashMap: " + normalTime + "ns");
        System.out.println("Degenerated in LinkedList HashMap: " + degenerateTime + "ns");
        System.out.println("LinkedList: " + linkedListTime + "ns");
    }

    static class incorrectKey<T> {
        private final T value;
        private final int HASHCODE_CONST_RESULT = 777;

        public incorrectKey(T value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return HASHCODE_CONST_RESULT;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return value.equals(((incorrectKey) obj).value);
        }
    }

    public static void demoArrayListCapacity() {
        System.out.println("Demo of ArrayList capacity");

        // Будем эмулировать поведение capacity
        ArrayListCapacityTracker tracker = new ArrayListCapacityTracker();
        System.out.println("Default capacity: " + tracker.getCurrentCapacity());

        // Демонстрируем как capacity увеличивается при добавлении элементов
        System.out.println("Addition of elements and increase of capacity:");

        for (int i = 0; i < 50; i++) {
            int oldCapacity = tracker.getCurrentCapacity(); // Запоминаем capacity до добавления

            tracker.add("element" + i);

            int newCapacity = tracker.getCurrentCapacity();
            int size = tracker.getSize();

            // Выводим информацию в ключевые моменты
            if (i == 0 || i == 10 || i == 20 || i == 30 || i == 40 || i == 49) {
                System.out.println("Size: " + size + ", capacity: " + newCapacity + ", filled: " + (size * 100.0 / newCapacity) + "%");
            }

            // Показываем моменты увеличения capacity только когда оно действительно изменилось
            if (newCapacity != oldCapacity) {
                System.out.println(">>> Capacity has increased from " + oldCapacity + " to " + newCapacity + " while adding " + (i + 1) + " element");
            }
        }

        // Демонстрация с указанием начальной емкости
        System.out.println("ArrayList with given default capacity = 5:");
        ArrayListCapacityTracker customTracker = new ArrayListCapacityTracker(5);
        System.out.println("Default capacity: " + customTracker.getCurrentCapacity());

        for (int i = 0; i < 12; i++) {
            int oldCapacity = customTracker.getCurrentCapacity();
            customTracker.add("element" + i);
            int newCapacity = customTracker.getCurrentCapacity();

            System.out.println("Size: " + customTracker.getSize() + ", capacity: " + newCapacity);

            // Показываем увеличение capacity если оно произошло
            if (newCapacity != oldCapacity) {
                System.out.println(">>> Capacity increased from " + oldCapacity + " to " + newCapacity);
            }
        }

        // Демонстрация trimToSize
        System.out.println("Using trimToSize()");
        int sizeBeforeTrim = customTracker.getSize();
        int capacityBeforeTrim = customTracker.getCurrentCapacity();
        customTracker.trimToSize();
        System.out.println("After trimToSize(), capacity: " + customTracker.getCurrentCapacity() +
                " (was: " + capacityBeforeTrim + ", size: " + sizeBeforeTrim + ")");
    }

    /*
     Класс для эмуляции поведения capacity ArrayList
     Алгоритм роста capacity соответствует реальной реализации ArrayList:
     - Начальная capacity по умолчанию: 10
     - При переполнении: newCapacity = oldCapacity + (oldCapacity >> 1)
     - Минимальный рост: минимум на 1
     */
    private static class ArrayListCapacityTracker {
        private int size = 0;
        private int capacity;

        // Эмуляция внутреннего массива (только для отслеживания capacity)
        private Object[] elementData;

        public ArrayListCapacityTracker() {
            this(10); // Дефолтная capacity как в ArrayList
        }

        public ArrayListCapacityTracker(int initialCapacity) {
            this.capacity = initialCapacity;
            this.elementData = new Object[initialCapacity];
        }

        public void add(Object element) {
            if (size >= capacity) {
                // Эмуляция роста capacity как в ArrayList
                int newCapacity = capacity + (capacity >> 1); // Увеличиваем примерно в 1.5 раза
                if (newCapacity - capacity < 1) {
                    newCapacity = capacity + 1; // Минимальный рост
                }
                capacity = newCapacity;
                elementData = new Object[capacity]; // Эмуляция создания нового массива
            }
            elementData[size] = element;
            size++;
        }

        public void trimToSize() {
            if (size < capacity) {
                capacity = size;
                elementData = Arrays.copyOf(elementData, size);
            }
        }

        public int getSize() {
            return size;
        }

        public int getCurrentCapacity() {
            return capacity;
        }
    }
}