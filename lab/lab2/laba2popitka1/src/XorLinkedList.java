import java.util.ArrayList;

public class XorLinkedList {
    private static class Node {
        int data;
        long xorPtr; // XOR предыдущего и следующего (храним индексы в ArrayList)
    }

    private final ArrayList<Node> memory = new ArrayList<>(); // симуляция памяти
    private int head = -1;
    private int tail = -1;
    private int size = 0;

    private int allocate(int data) {
        Node node = new Node();
        node.data = data;
        node.xorPtr = 0;
        memory.add(node);
        return memory.size() - 1;
    }

    public void addFirst(int data) {
        int newIdx = allocate(data);
        if (head == -1) {
            head = tail = newIdx;
        } else {
            memory.get(head).xorPtr ^= newIdx;
            memory.get(newIdx).xorPtr = head;
            head = newIdx;
        }
        size++;
    }

    public void addLast(int data) {
        int newIdx = allocate(data);
        if (head == -1) {
            head = tail = newIdx;
        } else {
            memory.get(tail).xorPtr ^= newIdx;
            memory.get(newIdx).xorPtr = tail;
            tail = newIdx;
        }
        size++;
    }

    public void insert(int data, int position) {
        if (position < 0 || position > size) throw new IndexOutOfBoundsException();
        if (position == 0) { addFirst(data); return; }
        if (position == size) { addLast(data); return; }

        int newIdx = allocate(data);

        int prev = 0;
        int curr = head;
        for (int i = 0; i < position - 1; i++) {
            int next = (int)(memory.get(curr).xorPtr ^ prev);
            prev = curr;
            curr = next;
        }

        int next = (int)(memory.get(curr).xorPtr ^ prev);

        memory.get(curr).xorPtr = memory.get(curr).xorPtr ^ next ^ newIdx;
        memory.get(next).xorPtr = memory.get(next).xorPtr ^ curr ^ newIdx;
        memory.get(newIdx).xorPtr = curr ^ next;

        size++;
    }

    public void remove(int position) {
        if (position < 0 || position >= size) throw new IndexOutOfBoundsException();
        if (size == 1) {
            head = tail = -1;
            size = 0;
            return;
        }
        if (position == 0) {
            int next = (int)memory.get(head).xorPtr;
            memory.get(next).xorPtr ^= head;
            head = next;
            size--;
            return;
        }
        if (position == size - 1) {
            int prev = (int)memory.get(tail).xorPtr;
            memory.get(prev).xorPtr ^= tail;
            tail = prev;
            size--;
            return;
        }

        int prev = 0;
        int curr = head;
        for (int i = 0; i < position - 1; i++) {
            int next = (int)(memory.get(curr).xorPtr ^ prev);
            prev = curr;
            curr = next;
        }
        int next = (int)(memory.get(curr).xorPtr ^ prev);

        memory.get(prev).xorPtr = memory.get(prev).xorPtr ^ curr ^ next;
        memory.get(next).xorPtr = memory.get(next).xorPtr ^ curr ^ prev;

        size--;
    }

    public int size() {
        return size;
    }

    public void printForward() {
        if (head == -1) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        int prev = 0;
        int curr = head;
        System.out.print(memory.get(curr).data);
        while ((curr = (int)(memory.get(curr).xorPtr ^ prev)) != 0) {
            prev = (int)(memory.get(curr).xorPtr ^ prev); // обновляем prev
            System.out.print(", " + memory.get(curr).data);
        }
        System.out.println("]");
    }

    public void printReverse() {
        if (tail == -1) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        int next = 0;
        int curr = tail;
        System.out.print(memory.get(curr).data);
        while ((curr = (int)(memory.get(curr).xorPtr ^ next)) != 0) {
            next = (int)(memory.get(curr).xorPtr ^ next);
            System.out.print(", " + memory.get(curr).data);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        XorLinkedList list = new XorLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(0);
        list.insert(99, 2);

        System.out.println("Size: " + list.size()); // 5
        list.printForward();  // [0, 1, 99, 2, 3]
        list.printReverse();  // [3, 2, 99, 1, 0]

        list.remove(2);       // удаляем 99
        list.printForward();  // [0, 1, 2, 3]
    }
}