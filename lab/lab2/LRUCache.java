import java.util.HashMap;

public class LRUCache<K, V> {

    private class CacheEntry {
        K key;        // ключ элемента
        V payload;    // значение элемента
        CacheEntry before; // ссылка на предыдущий узел
        CacheEntry after;  // ссылка на следующий узел

        CacheEntry(K key, V payload) {
            this.key = key;
            this.payload = payload;
        }
    }

    private final int maxLimit; // максимальный размер кэша
    private HashMap<K, CacheEntry> index; // хранит ключ и узел
    private CacheEntry newest; // самый недавно использованный элемент (голова)
    private CacheEntry oldest; // самый давно использованный элемент (хвост)

    public LRUCache(int limit) {
        this.maxLimit = limit;
        this.index = new HashMap<>();
    }

    // Получение значения по ключу
    public V get(K key) {
        if (!index.containsKey(key)) {
            System.out.println("Ключ " + key + " не найден");
            return null;
        }
        CacheEntry entry = index.get(key);
        promote(entry); // перемещаем в начало
        return entry.payload;
    }

    // Добавление или обновление элемента
    public void put(K key, V value) {
        if (index.containsKey(key)) {
            // Если ключ уже есть обновляем значение и перемещаем в начало
            CacheEntry existing = index.get(key);
            existing.payload = value;
            promote(existing);
        } else {
            // Создаём новый элемент
            CacheEntry newEntry = new CacheEntry(key, value);
            index.put(key, newEntry);
            linkToHead(newEntry);

            // Если превысили лимит удаляем наименее используемый элемент
            if (index.size() > maxLimit) {
                evictOldest();
            }
        }
    }

    // Переместить узел в начало списка
    private void promote(CacheEntry entry) {
        unlink(entry);
        linkToHead(entry);
    }

    // Добавить элемент в начало списка
    private void linkToHead(CacheEntry entry) {
        entry.after = newest;
        entry.before = null;

        if (newest != null) {
            newest.before = entry;
        }
        newest = entry;

        if (oldest == null) {
            oldest = newest;
        }
    }

    // Удалить узел из списка
    private void unlink(CacheEntry entry) {
        if (entry.before != null) entry.before.after = entry.after;
        else newest = entry.after; // если удаляем голову

        if (entry.after != null) entry.after.before = entry.before;
        else oldest = entry.before; // если удаляем хвост
    }

    private void evictOldest() {
        if (oldest == null) return;
        index.remove(oldest.key);
        unlink(oldest);
    }

    public void debugPrint() {
        CacheEntry curr = newest;
        System.out.print("Кэш: [");
        while (curr != null) {
            System.out.print(curr.key + "=" + curr.payload);
            curr = curr.after;
            if (curr != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Тест
    public static void main(String[] args) {
        LRUCache<Integer, String> browserHistory = new LRUCache<>(3);

        browserHistory.put(1, "Конспект по математике");
        browserHistory.put(2, "Лабораторная по ООП");
        browserHistory.put(3, "Методичка по физике");

        browserHistory.get(1);
        browserHistory.debugPrint();

        browserHistory.put(4, "Список вопросов к экзамену");
        browserHistory.debugPrint();

        browserHistory.get(3);
        browserHistory.debugPrint();
    }
}