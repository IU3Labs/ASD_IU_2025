import java.util.Scanner;

public class Utils {
    public static Tree createTree(Scanner scanner) {
        Tree tree = new Tree();

        System.out.print("Input num of nodes: ");
        int n = scanner.nextInt();

        System.out.println("Input nodes values: ");

        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            tree.insert(val);
        }

        return tree;
    }

    public static int inputTarget(Scanner scanner) {
        System.out.print("Input number to search: ");
        return scanner.nextInt();
    }
}
