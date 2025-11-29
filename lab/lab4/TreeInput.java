package lab4;
import java.util.Scanner;

public class TreeInput {

    public static Tree buildTreeFromConsole() {
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();

        System.out.println("Введите количество узлов в дереве:");
        int n = scanner.nextInt();

        System.out.println("Введите значения узлов (целые числа):");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            tree.insert(value);
        }

        return tree;
    }
}
