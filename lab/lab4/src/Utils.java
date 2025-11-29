import java.util.Scanner;

public class Utils {
    public static Tree inputTree(Scanner scanner) {
        Tree tree = new Tree();

        System.out.print("Введите кол-во узлов: ");
        int n = scanner.nextInt();

        System.out.println("Введите значения: ");

        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            tree.insert(val);
        }

        return tree;
    }

    public static int inputTarget(Scanner scanner) {
        System.out.print("Введите число которое нужно найти: ");
        return scanner.nextInt();
    }
}