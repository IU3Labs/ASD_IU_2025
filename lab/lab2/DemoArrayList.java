// Продемонстрировать capacity ArrayList

public class DemoArrayList<T> {
    private Object[] items; // внутренний массив
    private int count;      // сколько элементов сейчас в списке

    public static void main(String[] args) {
        System.out.println("Демонстрация работы capacity в ArrayList");

        DemoArrayList<String> list = new DemoArrayList<>();
        list.printInfo();

        System.out.println("\nЗаполнение массива:");
        for (int i = 1; i <= 15; i++) {
            list.add("Элемент " + i);
            if (i == 10 || i == 11 || i == 15) {
                list.printInfo();
            }
        }

        // Ручное управление памятью
        System.out.println("\n Ручное управление capacity:");

        // Увеличиваем capacity заранее
        list.ensureCapacity(50);
        list.printInfo();

        // Добавляем еще элементы
        for (int i = 16; i <= 25; i++) {
            list.add("Элемент " + i);
        }
        list.printInfo();

        // Уменьшаем capacity
        list.trimToSize();
        list.printInfo();

        // Проверяем что элементы на месте
        System.out.println("\nПроверяем элементы:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list[" + i + "] = " + list.get(i));
        }
    }

    // Конструктор по умолчанию
    public DemoArrayList() {
        items = new Object[10]; // по умолчанию в Java устанавливается 10 ячеек
        count = 0;
    }

    // Конструктор с заданным размером
    public DemoArrayList(int initialSize) {
        items = new Object[initialSize];
        count = 0;
    }

    // Добавляем элемент в конец
    public void add(T item) {
        // Если массив заполнен, увеличиваем его
        if (count == items.length) {
            increaseCapacity();
        }
        items[count] = item;
        count++;
    }

    // Получаем элемент по индексу
    public T get(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Индекс " + index + " за пределами");
        }
        return (T) items[index];
    }

    // Автоматически увеличиваем размер массива когда он заполнен
    private void increaseCapacity() {
        // Создаем новый массив в 1.5 раза больше
        int newSize = items.length * 3 / 2 + 1;
        Object[] newItems = new Object[newSize];

        // Копируем старые элементы в новый массив
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
        System.out.println("Увеличили capacity с " + count + " до " + newSize);
    }

    // Ручное увеличение capacity
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            Object[] newItems = new Object[minCapacity];
            for (int i = 0; i < count; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
            System.out.println("Вручную установили capacity: " + minCapacity);
        }
    }

    // Можем уменьшить capacity до текущего размера (при size < capacity)
    public void trimToSize() {
        if (count < items.length) {
            Object[] newItems = new Object[count];
            for (int i = 0; i < count; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
            System.out.println("Уменьшили capacity до размера: " + count);
        }
    }

    // Текущий размер (сколько элементов)
    public int size() {
        return count;
    }

    // Текущая capacity (сколько может поместиться до увеличения capacity)
    public int capacity() {
        return items.length;
    }

    // Выводим информацию о capacity и размере массива на данный момент
    public void printInfo() {
        System.out.println("Размер: " + count + ", Capacity: " + items.length);
    }
}
