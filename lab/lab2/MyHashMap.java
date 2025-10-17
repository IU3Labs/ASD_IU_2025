class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static class Entry<K, V> {
        K K;
        V V;
        Entry<K, V> next;

        Entry(K K, V V) {
            this.K = K;
            this.V = V;
        }
    }

    public V get(K K) {
        int hash = K.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.K.equals(K)) {
                return current.V;
            }
            current = current.next;
        }
        return null;
    }

    public void put(K K, V V) {
        int hash = K.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<K, V> newEntry = new Entry<>(K, V);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            newEntry.next = table[index];
            table[index] = newEntry;
        }
        size++;

        if ((float)size / table.length > LOAD_FACTOR) {
            resizeTable();
        }
    }

    public V remove(K K) {
        int hash = K.hashCode();
        int index = Math.abs(hash) % table.length;

        Entry<K, V> current = table[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.K.equals(K)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.V;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                Entry<K, V> next = current.next;
                int newIndex = Math.abs(current.K.hashCode()) % newCapacity;

                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    current.next = null;
                } else {
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                }
                current = next;
            }
        }

        table = newTable;
    }
}