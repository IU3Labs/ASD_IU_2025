import java.util.Scanner;
public class TreeHelper {

    private static final String INDENTATION = "    "; // 4 пробела для отступа

    public static Tree buildTreeFromUserInput(Scanner scanner) {
        Tree binaryTree = new Tree();

        System.out.print("Укажите количество элементов в дереве: ");
        int nodeCount = scanner.nextInt();

        if (nodeCount <= 0) {
            System.out.println("Создано пустое дерево.");
            return binaryTree;
        }

        System.out.println("Введите " + nodeCount + " числовых значений:");

        for (int i = 0; i < nodeCount; i++) {
            int value = scanner.nextInt();
            binaryTree.add(value);
        }

        return binaryTree;
    }

    public static int getSearchValue(Scanner scanner) {
        System.out.print("\nУкажите значение для поиска в дереве: ");
        return scanner.nextInt();
    }

    public static void displayTreeStructure(Tree.TreeNode root) {
        if (root == null) {
            System.out.println("Дерево пустое");
            return;
        }

        System.out.println("\nСтруктура дерева (симметричный обход):");
        traverseAndPrint(root, 0);
    }


    private static void traverseAndPrint(Tree.TreeNode currentNode, int depth) {
        if (currentNode == null) {
            return;
        }

        // Сначала обрабатываем левое поддерево
        traverseAndPrint(currentNode.leftChild, depth + 1);

        // Выводим текущий узел с отступами
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append(INDENTATION);
        }
        System.out.println(indentation.toString() + currentNode.value);

        // Затем обрабатываем правое поддерево
        traverseAndPrint(currentNode.rightChild, depth + 1);
    }
}
