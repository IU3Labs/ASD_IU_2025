


public class XORNode {
    /**
     * Узел XOR связного списка
     * Вместо двух указателей на следующий и предыдущий узлы,
     * используется один указатель, содержащий XOR адресов соседних узлов
     */
    int data;
    XORNode npx; // XOR указатель (next XOR previous)

    public XORNode(int data) {
        this.data = data;
        this.npx = null; // Изначально указатель null
    }
}

/**
 * XOR Linked List - список, где каждый узел содержит один указатель,
 * который является XOR адресов предыдущего и следующего узлов
 */
public class XORLinkedList {
    private XORNode head;
    private XORNode tail;

    public XORLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Вспомогательный метод для выполнения XOR операции между двумя узлами
     * В Java нельзя напрямую выполнять XOR с указателями, поэтому
     * мы используем System.identityHashCode для получения "адреса" объекта
     */
    private int xor(XORNode a, XORNode b) {
        if (a == null && b == null) return 0;
        if (a == null) return System.identityHashCode(b);
        if (b == null) return System.identityHashCode(a);
        return System.identityHashCode(a) ^ System.identityHashCode(b);
    }

    /**
     * Поиск узла по "адресу" (identityHashCode)
     */
    private XORNode getNodeByAddress(int address) {
        if (address == 0) return null;

        // Для поиска узла нам нужно пройти по списку
        XORNode current = head;
        XORNode prev = null;

        while (current != null) {
            if (System.identityHashCode(current) == address) {
                return current;
            }

            // Вычисляем следующий узел
            XORNode next = getNextNode(prev, current);
            prev = current;
            current = next;
        }

        return null;
    }

    /**
     * Получение следующего узла на основе предыдущего и текущего
     */
    private XORNode getNextNode(XORNode prev, XORNode current) {
        if (current == null) return null;

        // next = current.npx XOR prev
        int nextAddress = current.npx != null ?
                xor(prev, getNodeByAddress(current.npx.npx)) : 0;

        return getNodeByAddress(nextAddress);
    }

    /**
     * Добавление элемента в начало списка
     */
    public void insertAtHead(int data) {
        XORNode newNode = new XORNode(data);

        if (head == null) {
            // Если список пустой
            head = newNode;
            tail = newNode;
            newNode.npx = new XORNode(0); // Храним XOR(null, null) = 0
        } else {
            // Обновляем npx нового узла: XOR(null, head)
            newNode.npx = new XORNode(xor(null, head));

            // Обновляем npx старого head: XOR(newNode, next_of_old_head)
            XORNode nextOfOldHead = getNextNode(null, head);
            head.npx = new XORNode(xor(newNode, nextOfOldHead));

            head = newNode;
        }
    }

    /**
     * Добавление элемента в конец списка
     */
    public void insertAtTail(int data) {
        XORNode newNode = new XORNode(data);

        if (tail == null) {
            // Если список пустой
            head = newNode;
            tail = newNode;
            newNode.npx = new XORNode(0); // XOR(null, null) = 0
        } else {
            // Обновляем npx нового узла: XOR(tail, null)
            newNode.npx = new XORNode(xor(tail, null));

            // Обновляем npx старого tail: XOR(prev_of_old_tail, newNode)
            XORNode prevOfOldTail = getPrevNode(tail, null);
            tail.npx = new XORNode(xor(prevOfOldTail, newNode));

            tail = newNode;
        }
    }

    /**
     * Получение предыдущего узла на основе текущего и следующего
     */
    private XORNode getPrevNode(XORNode current, XORNode next) {
        if (current == null) return null;

        // prev = current.npx XOR next
        int prevAddress = current.npx != null ?
                xor(next, getNodeByAddress(current.npx.npx)) : 0;

        return getNodeByAddress(prevAddress);
    }

    /**
     * Удаление элемента из начала списка
     */
    public void deleteFromHead() {
        if (head == null) return;

        if (head == tail) {
            // Если в списке только один элемент
            head = null;
            tail = null;
        } else {
            XORNode newHead = getNextNode(null, head);
            XORNode nextOfNewHead = getNextNode(head, newHead);

            // Обновляем npx нового head: XOR(null, next_of_new_head)
            newHead.npx = new XORNode(xor(null, nextOfNewHead));

            head = newHead;
        }
    }

    /**
     * Печать списка от начала к концу
     */
    public void printForward() {
        XORNode current = head;
        XORNode prev = null;

        System.out.print("Список (вперед): ");
        while (current != null) {
            System.out.print(current.data + " ");

            XORNode next = getNextNode(prev, current);
            prev = current;
            current = next;
        }
        System.out.println();
    }

    /**
     * Печать списка от конца к началу
     */
    public void printBackward() {
        XORNode current = tail;
        XORNode next = null;

        System.out.print("Список (назад): ");
        while (current != null) {
            System.out.print(current.data + " ");

            XORNode prev = getPrevNode(current, next);
            next = current;
            current = prev;
        }
        System.out.println();
    }

    /**
     * Поиск элемента в списке
     */
    public boolean contains(int data) {
        XORNode current = head;
        XORNode prev = null;

        while (current != null) {
            if (current.data == data) {
                return true;
            }

            XORNode next = getNextNode(prev, current);
            prev = current;
            current = next;
        }

        return false;
    }

    /**
     * Получение размера списка
     */
    public int size() {
        int count = 0;
        XORNode current = head;
        XORNode prev = null;

        while (current != null) {
            count++;
            XORNode next = getNextNode(prev, current);
            prev = current;
            current = next;
        }

        return count;
    }

    // Демонстрация работы XOR Linked List
    public static void main(String[] args) {
        XORLinkedList list = new XORLinkedList();

        System.out.println("Добавляем элементы в конец:");
        list.insertAtTail(1);
        list.insertAtTail(2);
        list.insertAtTail(3);
        list.printForward();
        list.printBackward();

        System.out.println("\nДобавляем элементы в начало:");
        list.insertAtHead(0);
        list.insertAtHead(-1);
        list.printForward();
        list.printBackward();

        System.out.println("\nРазмер списка: " + list.size());
        System.out.println("Содержит 2: " + list.contains(2));
        System.out.println("Содержит 5: " + list.contains(5));

        System.out.println("\nУдаляем из начала:");
        list.deleteFromHead();
        list.printForward();
        list.printBackward();
    }
}
