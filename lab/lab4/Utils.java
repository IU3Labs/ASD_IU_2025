import java.util.Scanner;

public class Utils {
    public static Tree createTree(Scanner scanner) {
        Tree tree = new Tree();

        System.out.print("Введите количество узлов в дереве: ");
        int n = scanner.nextInt();

        System.out.println("Введите значения узлов: ");

        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            tree.insert(val);
        }

        return tree;
    }

    public static int inputTarget(Scanner scanner) {
        System.out.print("Введите число для поиска: ");
        return scanner.nextInt();
    }
}


