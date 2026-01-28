import java.util.*;

class Node {
    int weight;
    List<Node> subnodes;

    public Node(int _weight) {
        weight = _weight;
        subnodes = new ArrayList<>();
    }

    public void AddChild(Node _subnode) {
        subnodes.add(_subnode);
    }
}

public class Task2 {

    // Функция поиска, вызывающая рекурсивную функцию поиска в глубину с пределом
    public static boolean Search(Node root, int target, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            if (DepthLimitedSearch(root, target, depth)) {
                return true; // Цель найдена
            }
        }
        return false;
    }

    // Вспомогательный поиск в глубину с пределом глубины
    private static boolean DepthLimitedSearch(Node node, int target, int limit) {
        if (node.weight == target) {
            return true;
        }
        if (limit == 0) {
            return false;
        }

        for (Node child : node.subnodes) {
            if (DepthLimitedSearch(child, target, limit - 1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Создание дерева: 1 -> (2, 3), 2 -> (4, 5), 3 -> (6, 7)
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        root.AddChild(n2);
        root.AddChild(n3);
        n2.AddChild(new Node(4));
        n2.AddChild(new Node(5));
        n3.AddChild(new Node(6));
        n3.AddChild(new Node(7));

        int target = 6;
        int maxDepth = 3;

        if (Search(root, target, maxDepth)) {
            System.out.println("Цель " + target + " найдена!");
        } else {
            System.out.println("Цель " + target + " не найдена.");
        }
    }
}