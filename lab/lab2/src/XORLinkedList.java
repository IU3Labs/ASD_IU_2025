import java.util.HashMap;
import java.util.Map;


public class XORLinkedList<T> {
    class Node<T> { // Создание узла с универсальным типом данных
        T data;
        int both;
        int id;

        Node(T data, int id) {
            this.data = data;
            this.both = 0;
            this.id = id;
        }
    }

    private final Map<Integer, Node<T>> memory = new HashMap<>(); // Создание виртуальных указателей при помощи
    private Node<T> head = null;                                  // создания виртуальной памяти, т.к. в Java нет указателей
    private Node<T> tail = null;
    private int size = 0;
    private int nextId = 1; // Уникальный номер узла

    private Node getNode(int id) {
        return memory.getOrDefault(id, null);
    }

    private int registerNode(Node<T> newNode) { // Создание нового узла
        int addr = nextId++;
        newNode.id = addr;
        memory.put(addr, newNode);
        return addr;
    }

    public int len() {
        return size;
    }

    public void add(T data) { // Создание метода добавления новых значений
        Node<T> newNode = new Node<>(data, nextId++); //
        memory.put(newNode.id, newNode);

        if (head == null) // Случай если список пуст
            head = tail = newNode;

        else {  // Если список не пуст
            newNode.both = tail.id; // Передаем новому узлу адрес предыдущего
            tail.both ^= newNode.id; // Обновляем указатель добавив в XOR адрес нового узла
            tail = newNode; // Обновляем указатель на хвост
        }
        size++;
    }

    public void addFirst(T data) {

        Node<T> newNode = new Node<>(data, nextId);
        if (head == null)
            head = tail = newNode;
        else {
            newNode.both = head.id;
            head.both ^= newNode.id;
            head = newNode;
        }
        size++;
    }

    public T getFirst() {
        return (head == null) ? null : head.data;
    }

    public T getLast() {
        return (tail == null) ? null : tail.data;
    }

    public T get(int reqIndex) {
        if (reqIndex < 0 || reqIndex >= size) {
            System.out.println("Выход за границы списка");
            return null;
        }

        if (head == null) return null;

        int prevId = 0;
        Node<T> curr = head;
        for (int i = 0; i < reqIndex; i++) {
            int next = prevId ^ curr.both;
            prevId = curr.id;
            curr = memory.get(next);

            if (curr == null) {
                System.out.println("Структура XOR списка повреждена");
                return null;
            }
        }
        return curr.data;

    }

    public void removeLast() { // Удаление последнего
        if (tail == null) return;

        int tailAddr = tail.id; // Берем значение из памяти и записываем как адрес удаляемого узла

        if (head == tail) {
            head = tail = null;
        } else {
            int prevAddr = tail.both; // т.к. tail.both = address(prev)
            Node<T> prevNode = memory.get(prevAddr);
            prevNode.both ^= tailAddr; // Убираем ссылку на старый хвост

            tail = prevNode; // Задаем новый хвост
        }

        memory.remove(tailAddr); // Удаляем узел из "виртуальной памяти"
        size--;
    }

    public void removeFirst() {
        if (head == null) return;

        int headAddr = head.id;

        if (head == tail)
            head = tail = null;
        else {
            int nextAddr = head.both;
            Node<T> nextNode = memory.get(nextAddr);
            nextNode.both ^= headAddr;
            head = nextNode;
        }

        memory.remove(headAddr);
        size--;
    }

    public int countByTraversal() {
        int count = 0;
        int prev = 0;
        Node<T> curr = head;

        while (curr != null) {
            count++;
            int nextAddr = prev ^ curr.both;
            prev = curr.id;
            curr = memory.get(nextAddr);
        }

        return count;
    }

}
