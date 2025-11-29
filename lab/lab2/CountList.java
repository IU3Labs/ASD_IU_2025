public class CountList<T> {
    
    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        int count;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.count = 0;
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public boolean find(T data) {
        Node<T> current = head;
        Node<T> prev = null;
        boolean found = false;

        while (current != null) {
            if (current.data.equals(data)) {
                current.count++;
                found = true;
                if (prev != null) {
                    prev.next = current.next;
                    insertInOrder(current);
                }
                break;
            }
            prev = current;
            current = current.next;
        }
        return found;
    }

    private void insertInOrder(Node<T> node) {
        if (head == null || node.count > head.count) {
            node.next = head;
            head = node;
            return;
        }

        Node<T> current = head;
        while (current.next != null && current.next.count >= node.count) {
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
    }

    public boolean contains(T data) {
        return find(data);
    }

    public int size() {
        return size;
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + "[" + current.count + "] ");
            current = current.next;
        }
        System.out.println();
    }
}
