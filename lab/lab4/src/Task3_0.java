import java.util.Stack;
import java.util.Scanner;

public class Task3_0 {
    public static void main(String[] args) {

        Node root = buildTree();

        Scanner sc = new Scanner(System.in);
        System.out.print("n: ");
        int target = sc.nextInt();

        Node r1 = dfsRecursive(root, target);
        if (r1 == null) {
            System.out.println("recursive: not found");
        } else {
            System.out.println("recursive: found " + r1.value);
        }

        Node r2 = dfsIterative(root, target);
        if (r2 == null) {
            System.out.println("iteractive: not found");
        } else {
            System.out.println("iteractive: found " + r2.value);
        }
    }

    public static class Node {
        public int value;
        public Node[] children;

        public Node(int value, Node[] children) {
            this.value = value;
            this.children = children;
        }
    }

    public static Node buildTree() {
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

    public static Node dfsRecursive(Node root, int target) {
        if (root == null) return null;

        if (root.value == target) return root;

        for (int i = 0; i < root.children.length; i++) {
            Node res = dfsRecursive(root.children[i], target);
            if (res != null) return res;
        }

        return null;
    }

    public static Node dfsIterative(Node root, int target) {
        if (root == null) return null;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            if (cur.value == target) return cur;
            for (int i = cur.children.length - 1; i >= 0; i--) {
                stack.push(cur.children[i]);
            }
        }

        return null;
    }
}
