class MyHashSet<T> {
    private Entry<T>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 1048576;
    private static final float LOAD_FACTOR = 0.75f;

    public MyHashSet() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashSet(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    // Внутренний класс для элементов цепочки
    private static class Entry<T> {
        T data;
        Entry<T> next;

        Entry(T data) {
            this.data = data;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        int hash = element.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<T> current = table[index];
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean add(T element) {
        int hash = element.hashCode();
        int index = Math.abs(hash) % table.length;

        // Проверяем, нет ли уже такого элемента
        Entry<T> current = table[index];
        while (current != null) {
            if (current.data.equals(element)) {
                return false; // Элемент уже существует
            }
            current = current.next;
        }

        // Добавляем новый элемент в начало цепочки
        Entry<T> newEntry = new Entry<>(element);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;

        // Проверяем необходимость расширения
        if ((float) size / table.length > LOAD_FACTOR) {
            resizeTable();
        }

        return true;
    }

    public boolean remove(T element) {
        int hash = element.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<T> current = table[index];
        Entry<T> previous = null;

        while (current != null) {
            if (current.data.equals(element)) {
                if (previous == null) {
                    // Удаляем первый элемент цепочки
                    table[index] = current.next;
                } else {
                    // Удаляем из середины/конца цепочки
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<T>[] newTable = new Entry[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Entry<T> current = table[i];
            while (current != null) {
                Entry<T> next = current.next;

                int newHash = current.data.hashCode();
                int newIndex = Math.abs(newHash) % newCapacity;

                // Вставляем в начало цепочки новой таблицы
                current.next = newTable[newIndex];
                newTable[newIndex] = current;

                current = next;
            }
        }

        table = newTable;
    }
}