/**
 * Двусвязный список для управления порядком использования элементов в LRU Cache
 * head - наиболее недавно использованный элемент
 * tail - наименее недавно использованный элемент
 **/
package LRUCache;

public class DoublyLinkedList {
    private CacheNode head;
    private CacheNode tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Добавляет узел в начало списка (как наиболее используемый)
    public void addToHead(CacheNode node) {
        if (head == null) {
            // Список пустой - оба указателя на новый узел
            head = node;
            tail = node;
        } else {
            // Добавляем в начало непустого списка
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
        }

        // Очищаем ссылки у новой ноды
        node.prev = null;
        // node.next уже установлен выше
    }

    // Перемещает существующий узел в начало списка
    public void moveToHead(CacheNode node) {
        if (node == head) {
            return; // уже в начале
        }

        removeNode(node);
        addToHead(node);
    }

    // Удаляет узел из списка
    public void removeNode(CacheNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    // Удаляет и возвращает последний элемент списка (наименее используемый)
    public CacheNode removeTail() {
        if (tail == null) {
            return null;
        }

        CacheNode oldTail = tail;
        removeNode(tail);
        return oldTail;
    }

    // Пустой ли список
    public boolean isEmpty() {
        return head == null;
    }

    // Выводим список
    public void printList() {
        CacheNode current = head;
        while (current != null) {
            System.out.print("[" + current.key + "=" + current.value + "]");
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
    }
}