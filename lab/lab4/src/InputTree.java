import java.util.*;

public class InputTree {

    public static Tree buildTree() {
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();

        System.out.println("Введите количество узлов в дереве:");
        int k = scanner.nextInt();

        System.out.println("Введите значения узлов (целые числа):");
        for (int i = 0; i < k; i++) {
            int value = scanner.nextInt();
            tree.insert(value);
        }

        return tree;
    }
}