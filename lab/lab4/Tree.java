import java.util.*;

public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }


    public void readFromKeyboard() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите дерево (уровневый обход, используйте 'null'):");

        String line = sc.nextLine();
        String[] parts = line.split("\\s+");

        if (parts.length == 0 || parts[0].equals("null")) {
            root = null;
            return;
        }

        root = new Node(Integer.parseInt(parts[0]));
        List<Node> current = new ArrayList<>();
        current.add(root);

        int i = 1;

        while (!current.isEmpty() && i < parts.length) {
            List<Node> next = new ArrayList<>();

            for (Node n : current) {
                if (i < parts.length && !parts[i].equals("null")) {
                    n.left = new Node(Integer.parseInt(parts[i]));
                    next.add(n.left);
                }
                i++;

                if (i < parts.length && !parts[i].equals("null")) {
                    n.right = new Node(Integer.parseInt(parts[i]));
                    next.add(n.right);
                }
                i++;
            }

            current = next;
        }
    }
}
