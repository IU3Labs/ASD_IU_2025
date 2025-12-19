import java.util.Scanner;

public class Utils {

    public static Tree createTree(Scanner scanner) {
        Tree tree = new Tree();

        int countNode = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введите количество узлов: ");

            if (scanner.hasNextInt()) {
                countNode = scanner.nextInt();
                if (countNode >= 0) {
                    validInput = true;
                } else {
                    System.out.println("Количество узлов не может быть отрицательным.");
                }
            } else {
                System.out.println("Ошибка ввода. Введите целое число.");
                scanner.next();
            }
        }

        System.out.println("Введите значения узлов: ");

        for (int i = 0; i < countNode; i++) {
            int val = readInt(scanner, "Узел " + (i + 1) + ": ");
            tree.insert(val);
        }

        return tree;
    }

    // Ввод элемента для поиска
    public static int inputTarget(Scanner scanner) {
        return readInt(scanner, "\nВведите элемент для поиска: ");
    }

    private static int readInt(Scanner scanner, String message) {
        boolean valid = false;
        int value = 0;

        while (!valid) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Ошибка ввода");
                scanner.next(); // очистка неверного ввода
            }
        }
        return value;
    }

    // Печать дерева
    public static void printInOrder(Tree.Node node) {
        printInOrderRecursive(node, 0);
    }
    private static void printInOrderRecursive(Tree.Node node, int level) {
        if (node == null) {
            return;
        }
        // Сначала правый потомок
        printInOrderRecursive(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }

        if (level == 0) {
            System.out.println("[" + node.data + "]");
        } else {
            System.out.println(node.data);
        }
        // Левый потомок
        printInOrderRecursive(node.left, level + 1);
    }
}
