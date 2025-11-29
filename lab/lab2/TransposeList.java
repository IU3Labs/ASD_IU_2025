public class TransposeList<T> {
    
    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public boolean find(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            return true;
        }

        Node<T> current = head;
        Node<T> prev = null;
        Node<T> prevPrev = null;

        while (current != null && !current.data.equals(data)) {
            prevPrev = prev;
            prev = current;
            current = current.next;
        }

        if (current != null && prev != null) {
            if (prevPrev != null) {
                prevPrev.next = current;
            } else {
                head = current;
            }
            prev.next = current.next;
            current.next = prev;
            return true;
        }
        return false;
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
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
