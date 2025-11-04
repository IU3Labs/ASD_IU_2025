import java.util.ArrayList;
import java.util.Random;

public class SkipList {

    private static class Node {
        int key;
        int value;
        Node[] next;

        public Node(int key, int value, int level) {
            this.key = key;
            this.value = value;
            this.next = new Node[level + 1];
        }
    }

    private static final double P = 0.25;
    private static final int MAX_LEVEL = 10;

    private final Node head;
    private int currentMaxLevel;
    private final Random random;
    private int size;

    public SkipList() {
        this.head = new Node(Integer.MIN_VALUE, 0, MAX_LEVEL);
        this.currentMaxLevel = 0;
        this.random = new Random();
        this.size = 0;
    }

    public int get(int key) {
        Node current = head;

        for (int level = currentMaxLevel; level >= 0; level--) {
            while (current.next[level] != null && current.next[level].key < key) {
                current = current.next[level];
            }
        }
        current = current.next[0];

        if (current != null && current.key == key) return current.value;
        else return Integer.MIN_VALUE;
    }

    public void put(int key, int value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int level = currentMaxLevel; level >= 0; level--) {
            while (current.next[level] != null && current.next[level].key < key) {
                current = current.next[level];
            }
            update[level] = current;
        }
        current = current.next[0];

        if (current != null && current.key == key) {
            current.value = value;
            return;
        }

        int newLevel = generateRandomLevel();
        if (newLevel > currentMaxLevel) {
            for (int level = currentMaxLevel + 1; level <= newLevel; level++) {
                update[level] = head;
            }
            currentMaxLevel = newLevel;
        }
        Node newNode = new Node(key, value, newLevel);
        for (int level = 0; level <= newLevel; level++) {
            newNode.next[level] = update[level].next[level];
            update[level].next[level] = newNode;
        }
        size++;
    }

    private int generateRandomLevel() {
        int level = 0;
        while (random.nextDouble() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void remove(int key) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int level = currentMaxLevel; level >= 0; level--) {
            while (current.next[level] != null && current.next[level].key < key) {
                current = current.next[level];
            }
            update[level] = current;
        }
        current = current.next[0];
        if (current == null || current.key != key) {
            return;
        }

        for (int level = 0; level <= currentMaxLevel; level++) {
            if (update[level].next[level] == current) {
                update[level].next[level] = current.next[level];
            }
        }

        while (currentMaxLevel > 0 && head.next[currentMaxLevel] == null) {
            currentMaxLevel--;
        }
        size--;
    }

    public int size() {
        return size;
    }

    public void print() {
        ArrayList<Integer> keys = new ArrayList<>();
        Node base = head.next[0];
        while (base != null) {
            keys.add(base.key);
            base = base.next[0];
        }
        for (int level = currentMaxLevel; level >= 0; level--) {
            Node current = head.next[level];
            for (int key : keys) {
                if (current != null && current.key == key) {
                    System.out.print("[" + key + "]");
                    current = current.next[level];
                } else {
                    System.out.print("   ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}