import java.util.HashMap;

public class LRUCache<K, V> {

    private class Node {
        K key;        // ключ элемента
        V value;      // значение элемента
        Node prev;    // ссылка на предыдущий узел
        Node next;    // ссылка на следующий узел

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;         // максимальный размер кэша
    private HashMap<K, Node> map;       // хранит ключ и узел
    private Node head;                  // самый недавно использованный элемент
    private Node tail;                  // самый давно использованный элемент

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    // Получение значения по ключу
    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            System.out.println("Ключ " + key + " не найден");
            return null;
        }

        moveToHead(node);
        return node.value;
    }

    // Добавление или обновление элемента
    public void put(K key, V value) {
        Node node = map.get(key);

        if (node != null) {
            // Если ключ уже есть лбновляем значение и перемещаем в начало
            node.value = value;
            moveToHead(node);
        } else {
            // Создаём новый элемент
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addFirst(newNode);

            // Если превысили лимит удаляем наименее используемый элемент
            if (map.size() > capacity) {
                removeLast();
            }
        }
    }

    // Добавить элемент в начало списка
    private void addFirst(Node node) {
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = head;
        }
    }

    // Удалить узел из списка
    private void removeNode(Node node) {
        if (node.prev != null)
            node.prev.next = node.next;
        else
            head = node.next; // если удаляем голову

        if (node.next != null)
            node.next.prev = node.prev;
        else
            tail = node.prev; // если удаляем хвост
    }

    // Переместить узел в начало списка
    private void moveToHead(Node node) {
        removeNode(node);
        addFirst(node);
    }

    private void removeLast() {
        if (tail == null) return;
        map.remove(tail.key);
        removeNode(tail);
    }

    public void printCache() {
        Node current = head;
        System.out.print("Кэш: [");
        while (current != null) {
            System.out.print(current.key + "=" + current.value);
            current = current.next;
            if (current != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Тест
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "Конспект по математике");
        cache.put(2, "Лабораторная по ООП");
        cache.put(3, "Методичка по физике");

        cache.get(1);
        cache.printCache();  // 1,3,2
        cache.put(4, "Список вопросов к экзамену");
        cache.printCache();  // 4,1,3

        cache.get(3);
        cache.printCache();  // 3,4,1
    }

}
