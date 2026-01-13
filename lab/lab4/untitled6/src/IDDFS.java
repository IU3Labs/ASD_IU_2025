//Реализовать поиск в дереве (Iterative deepening depth-first search)
import java.util.*;

public class IDDFS {

    public static SearchResult iterativeDeepeningDFS(TreeNode root, int target) {
        if (root == null) return new SearchResult(new ArrayList<>(), -1);

        int maxDepth = 10; // Максимальная глубина поиска

        for (int depth = 0; depth <= maxDepth; depth++) {
            List<Integer> path = new ArrayList<>();

            boolean found = depthLimitedSearch(root, target, depth, path);
            if (found) {
                return new SearchResult(path, depth);
            }
        }

        return new SearchResult(new ArrayList<>(), -1);
    }

    private static boolean depthLimitedSearch(TreeNode node, int target, int depth, List<Integer> path) {
        if (node == null) return false;

        path.add(node.value);

        if (node.value == target) {
            return true;
        }

        if (depth == 0) {
            path.remove(path.size() - 1);
            return false;
        }

        for (TreeNode child : node.children) {
            if (depthLimitedSearch(child, target, depth - 1, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    // Класс для хранения результата поиска (путь + глубина)
    static class SearchResult {
        List<Integer> path;
        int depthFound;

        SearchResult(List<Integer> path, int depthFound) {
            this.path = path;
            this.depthFound = depthFound;
        }

        boolean found() {
            return !path.isEmpty();
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestTree();

        Scanner scanner = new Scanner(System.in);

        System.out.println(" ITERATIVE DEEPENING DEPTH-FIRST SEARCH (IDDFS) \n");

        System.out.println("\nДоступные узлы: " + TreeNode.getAllValues(root));
        System.out.print("\nВведите значение для поиска: ");
        int target = scanner.nextInt();

        System.out.println("ПОИСК С ПОСТЕПЕННЫМ УГЛУБЛЕНИЕМ (IDDFS):\n");

        SearchResult result = iterativeDeepeningDFS(root, target);

        if (result.found()) {
            System.out.println("Узел найден!");
            System.out.println("Глубина, на которой найден: " + result.depthFound);
            System.out.println("Путь от корня: " + result.path);
            System.out.println("Длина пути: " + result.path.size());

            System.out.println("\nПроцесс поиска по глубинам:");
            showSearchProcess(root, target);

        } else {
            System.out.println("Узел не найден в пределах максимальной глубины");
        }

    }

    private static void showSearchProcess(TreeNode root, int target) {
        System.out.println("\nДетали поиска:");

        for (int depth = 0; depth <= 5; depth++) {
            List<Integer> visited = new ArrayList<>();
            depthLimitedTraversal(root, depth, visited);

            System.out.print("Глубина " + depth + ": проверено узлов " + visited.size() + " [");
            for (int i = 0; i < Math.min(visited.size(), 10); i++) {
                System.out.print(visited.get(i));
                if (i < Math.min(visited.size(), 10) - 1) System.out.print(", ");
            }
            if (visited.size() > 10) System.out.print(", ...");
            System.out.print("]");

            // Проверяем, найден ли целевой узел на этой глубине
            if (visited.contains(target)) {
                System.out.print(" ← НАЙДЕН на этой глубине!");
            }
            System.out.println();
        }
    }

    private static void depthLimitedTraversal(TreeNode node, int depth, List<Integer> visited) {
        if (node == null || depth < 0) return;

        visited.add(node.value);

        if (depth > 0) {
            for (TreeNode child : node.children) {
                depthLimitedTraversal(child, depth - 1, visited);
            }
        }
    }
}











