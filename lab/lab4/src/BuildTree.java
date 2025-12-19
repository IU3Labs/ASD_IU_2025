import java.util.Scanner;

public class BuildTree {
    //Заполнение дерева с клавиатуры
    public static Node buildTree(Scanner scanner) {
        System.out.print("Input Node value: ");
        int value = scanner.nextInt();
        Node node = new Node(value);

        System.out.print("Input number of children for node " + value + ": ");
        int childrenCount = scanner.nextInt();

        for (int i = 0; i < childrenCount; i++) {
            System.out.println("Creation " + (i + 1) + " child of node " + value);
            node.addChild(buildTree(scanner));
        }

        return node;
    }
}
