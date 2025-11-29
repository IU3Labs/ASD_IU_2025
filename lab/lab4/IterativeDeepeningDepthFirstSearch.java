package lab4;
import java.util.Scanner;

// 2 pеализовать поиск в дереве (Iterative deepening depth-first search)

public class IterativeDeepeningDepthFirstSearch {

        public static void main() {
            Scanner scanner = new Scanner(System.in);
            Tree tree = Utility.createTree(scanner);

            int target = Utility.inputTarget(scanner);

            boolean found = search(tree.root, target);
            if (!found) {
                System.out.println("Элемент не найден");
            }
        }

        public static boolean search(Tree.Node root, int target) {
            if (root == null) return false;

            int limit = 0;

            while (true) {
                System.out.println("Глубина = " + limit);

                boolean found = dfs(root, target, limit);
                if (found) {
                    System.out.println("Элемент найден на глубине " + limit);
                    return true;
                }
                limit += 1;
            }
        }

        public static boolean dfs(Tree.Node node, int target, int limit) {
            if (node == null) return false;

            if (node.data == target) {
                System.out.println("Найден: " + node.data);
                return true;
            }

            if (limit == 0) return false;

            return dfs(node.left, target, limit - 1) || dfs(node.right, target, limit - 1);
        }
    }
