package lab2;


import java.util.Random;


public class SkipList {
    private static final int MAX_LEVEL = 16; // макс. уровней
    private static final double P = 0.5;     // вероятность подняться на следующий уровень
    private final Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL); // sentinel
    private int level = 0; // текущий верхний уровень
    private final Random rnd = new Random();


    private static class Node {
        int value;
        Node[] forward;
        Node(int value, int level) {
            this.value = value;
            this.forward = new Node[level + 1];
        }
    }

    // Генерация случайного уровня (геометрическое распределение)
    private int randomLevel() {
        int lvl = 0;
        while (lvl < MAX_LEVEL && rnd.nextDouble() < P) lvl++;
        return lvl;
    }

    // Вставка (insert)
    public void insert(int value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;


        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }


        current = current.forward[0];
        if (current != null && current.value == value) return; // уже есть


        int lvl = randomLevel();
        if (lvl > level) {

            for (int i = level + 1; i <= lvl; i++) update[i] = head;
            level = lvl;
        }


        Node newNode = new Node(value, lvl);
        for (int i = 0; i <= lvl; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    // Поиск (search)
    public boolean search(int value) {
        Node current = head;
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value == value;
    }


    public boolean delete(int value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;


        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }


        current = current.forward[0];
        if (current == null || current.value != value) return false; // нет такого


        for (int i = 0; i <= level; i++) {
            if (update[i].forward[i] != current) break;
            update[i].forward[i] = current.forward[i];
        }


        while (level > 0 && head.forward[level] == null) level--;
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node x = head.forward[0];
        while (x != null) {
            sb.append(x.value).append(" ");
            x = x.forward[0];
        }
        return sb.toString().trim();
    }


    public static void main(String[] args) {
        SkipList sl = new SkipList();
        sl.insert(10);
        sl.insert(3);
        sl.insert(7);
        sl.insert(1);
        sl.insert(5);
        System.out.println("After inserts: " + sl);

        System.out.println("Search 7: " + sl.search(7));
        System.out.println("Search 4: " + sl.search(4));

        System.out.println("Delete 3: " + sl.delete(3));
        System.out.println("After delete: " + sl);
    }
}


