/*Прописываем структуру данных SkipList*/

package skiplist;

import java.util.Random;

public class DataTypeSkipList<T extends Comparable<T>> { //нам нужны те типы данных, которые можно сравнивать (int, long. double и т.д.) //
    private static final double P = 0.5;
    private static final int MAX_LEVEL = 16;
    private final Random random = new Random();
    private final Node<T> head = new Node<>(null, MAX_LEVEL);
    private int level = 1;
    private int size = 0;

    private static class Node<T> {
        T value;
        Node<T>[] forward;
        @SuppressWarnings("unchecked")
        Node(T value, int level) {
            this.value = value;
            this.forward = new Node[level];
        }
    }

    public boolean contains(T value) { //метод наличия элемента в SkipList
        Node<T> current = head;
        for (int i = level - 1; i >= 0; i--) { //перебираем списки с ссылками на следующе элементы в поиске нашего значения
            while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0) {
                current = current.forward[i];
            }
        }
        current = current.forward[0]; //найденное значение
        return current != null && current.value.compareTo(value) == 0;
    }

    public void insert (T value) {
        Node<T>[] update = new Node[MAX_LEVEL];
        Node<T> current = head;
        for (int i = level - 1; i >= 0; i--) {  //ищем место для вставки
            while (current.forward[i] != null &&
                    current.forward[i].value.compareTo(value) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }
        current = current.forward[0];
        if (current != null && current.value.compareTo(value) == 0) { // если элемент уже существует, ничего не делаем
            return;
        }
        int newLevel = randomLevel(); //случайная высота для нового узла
        if (newLevel > level) { // если новый уровень выше текущего — обновляем массив update
            for (int i = level; i < newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }
        Node<T> newNode = new Node<>(value, newLevel); //создаем новый узел для нового элемента
        for (int i = 0; i < newLevel; i++) { // вставляем его на всех уровнях до newLevel
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
        size++;
    }

    public boolean delete(T value) {
        Node<T>[] update = new Node[MAX_LEVEL];
        Node<T> current = head;
        for (int i = level - 1; i >= 0; i--) { //поиск узлов, указываемых на удаляемый элемент
            while (current.forward[i] != null &&
                    current.forward[i].value.compareTo(value) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }
        current = current.forward[0];
        if (current == null || current.value.compareTo(value) != 0) {
            return false; // элемент не найден
        }
        for (int i = 0; i < level; i++) {     // перенастраиваем ссылки на всех уровнях
            if (update[i].forward[i] != current) break;
            update[i].forward[i] = current.forward[i];
        }
        while (level > 1 && head.forward[level - 1] == null) {// уменьшаем уровень, если верхние уровни пусты
            level--;
        }
        size--;
        return true;
    }

    public int size() {
        return size;
    }
    public void print() {
        System.out.println("\n=== Skip List ===");
        for (int i = level - 1; i >= 0; i--) {
            Node<T> node = head.forward[i];
            System.out.print("Level " + (i + 1) + ": ");
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.forward[i];
            }
            System.out.println();
        }
        System.out.println("=================\n");
    }

    private int randomLevel() {
        int lvl = 1;
        while (random.nextDouble() < P && lvl < MAX_LEVEL) {
            lvl++;
        }
        return lvl;
    }
}
