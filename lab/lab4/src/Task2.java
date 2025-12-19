import java.util.Scanner;

public class Task2 {
    static class Node {
        int value;
        Node[] children;

        Node(int value, Node[] children) {
            this.value = value;
            this.children = children;
        }
    }

    public static void main(String[] args) {

        Node root = buildTree();

        Scanner sc = new Scanner(System.in);
        System.out.print("n: ");
        int target = sc.nextInt();

        int maxDepth = 10;

        Node found = iterativeDeepeningSearch(root, target, maxDepth);

        if (found == null) {
            System.out.println("element not found");
        } else {
            System.out.println("element: " + found.value);
        }
    }

    static Node iterativeDeepeningSearch(Node root, int target, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            Node res = depthLimitedDfs(root, target, depth);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    static Node depthLimitedDfs(Node node, int target, int limit) {
        if (node == null) return null;

        if (node.value == target) return node;

        if (limit == 0) return null;

        for (int i = 0; i < node.children.length; i++) {
            Node res = depthLimitedDfs(node.children[i], target, limit - 1);
            if (res != null) return res;
        }

        return null;
    }

    static Node buildTree() {
        Node n8 = new Node(8, new Node[]{});
        Node n9 = new Node(9, new Node[]{});
        Node n10 = new Node(10, new Node[]{});
        Node n11 = new Node(11, new Node[]{});
        Node n12 = new Node(12, new Node[]{});
        Node n13 = new Node(13, new Node[]{});
        Node n4 = new Node(4, new Node[]{n8, n9});
        Node n5 = new Node(5, new Node[]{n10});
        Node n6 = new Node(6, new Node[]{n11, n12});
        Node n7 = new Node(7, new Node[]{n13});
        Node n2 = new Node(2, new Node[]{n4, n5});
        Node n3 = new Node(3, new Node[]{n6, n7});

        return new Node(1, new Node[]{n2, n3});
    }
}
