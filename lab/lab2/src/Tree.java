import java.util.*;

public class Tree {
    private int key;
    private String color;
    private Tree left;
    private Tree right;
    private Tree parent;

    public Tree(int key, String color) {
        this.key = key;
        this.color = color;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public static void main(String[] args) {
        Tree root = new Tree(0, "BLACK");
        add(root, 5);
        add(root, 2);
        add(root, 7);
    }

    private static void add(Tree tree, int key) {
        Tree current = tree;
        Tree parent = null;

        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        } ///двигаемся по массиву. Если наш элемент больше curr, то двигаемся вправо, иначе - влево

        Tree newNode = new Tree(key, "RED");
        newNode.parent = parent;

        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        balancing(tree, newNode);
    }

    private static void balancing(Tree root, Tree node) {
        while (node != root && node.parent != null && "RED".equals(node.parent.color)) {
            Tree pa = node.parent;
            Tree grandpa = pa.parent;
            if (grandpa == null) break;

            Tree uncle = (grandpa.left == pa) ? grandpa.right : grandpa.left;

            // красный дядя - просто перекрашиваем дядю в черный, а деда в красный
            if (uncle != null && "RED".equals(uncle.color)) {
                pa.color = "BLACK";
                uncle.color = "BLACK";
                grandpa.color = "RED";
                node = grandpa;
            } else {
                // черный дядя
                if (pa == grandpa.left) {
                    if (node == pa.right) {
                        rotateLeft(root, pa); //нужен, чтобы привести поддерево к случаю, где необходим правый поворот
                        node = pa;
                    }
                    //собственно делаем правый поворот(когда отец - левый потомок)
                    rotateRight(root, grandpa);
                } else {
                    if (node == pa.left) {
                        rotateRight(root, pa); //аналогично в другую сторону
                        node = pa;
                    }
                    // Теперь всегда прямой левый кейс
                    rotateLeft(root, grandpa);
                }
                // Перекрашиваем после поворота
                pa.color = "BLACK";
                grandpa.color = "RED";
                break;
            }
        }
        root.color = "BLACK"; //учитываем случай, если делаем поворот на руте
    }

    private static void rotateLeft(Tree root, Tree node) {
        Tree pivot = node.right;
        node.right = pivot.left;

        if (pivot.left != null){
            pivot.left.parent = node;
        }

        pivot.parent = node.parent;
        if (node.parent == null){
            node = root;
        } else if (node == node.parent.left) {
            node.parent.left = pivot;
        } else {
            node.parent.right = pivot;
        }

        pivot.left = node;
        node.parent = pivot;
    }
    /// совершаем "поворот", т.е. передвигаем наши элементы так, что дядя становится новым сыном,
    /// Дедушка становится новым папой, а старый папа становится дедушкой, при этом сын становится дядей.
    /// И перекрашиваем так, чтобы дед стал черным, папа стал красным

    private static void rotateRight(Tree root, Tree node) {
        Tree pivot = node.left;
        node.left = pivot.right;
        if (pivot.right != null) pivot.right.parent = node;

        pivot.parent = node.parent;
        if (node.parent == null) {
            // root assignment depends on outer code context
        } else if (node == node.parent.right) {
            node.parent.right = pivot;
        } else {
            node.parent.left = pivot;
        }

        pivot.right = node;
        node.parent = pivot;
    }
}