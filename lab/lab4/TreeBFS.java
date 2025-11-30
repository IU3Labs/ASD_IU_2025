//Реализовать поиск в дереве в ширину двумя способами.

package lab4;
import java.util.*;

// Узел дерева
class treenode {
    int value;
    List<treenode> children;

    treenode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    void addChild(treenode child) {
        this.children.add(child);
    }
}

public class TreeBFS {

    public static void main(String[] args) {
        TreeBFS treeBFS = new TreeBFS();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Поиск в ширину (BFS) в дереве двумя способами ===");
        treenode root = treeBFS.buildTreeFromInput(scanner);

        System.out.print("\nВведите значение для поиска: ");
        int target = scanner.nextInt();
        treeBFS.runBothBFSMethods(root, target);
        scanner.close();
    }

    private treenode buildTreeFromInput(Scanner scanner) {
        System.out.print("Введите значение корня дерева: ");
        int rootValue = scanner.nextInt();
        treenode root = new treenode(rootValue);

        Queue<treenode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            treenode current = queue.poll();

            System.out.print("Сколько детей у узла " + current.value + "? ");
            int childCount = scanner.nextInt();

            for (int i = 0; i < childCount; i++) {
                System.out.print("Введите значение ребенка " + (i + 1) + " узла " + current.value + ": ");
                int childValue = scanner.nextInt();
                treenode child = new treenode(childValue);
                current.addChild(child);
                queue.offer(child);
            }
        }

        return root;
    }

    private void runBothBFSMethods(treenode root, int target) {
        System.out.println("\nСПОСОБ 1: Классический BFS с очередью");
        treenode result1 = bfsClassic(root, target);
        printSearchResult(result1, target, 1);

        System.out.println("\nСПОСОБ 2: BFS с уровневым обходом");
        treenode result2 = bfsLevelOrder(root, target);
        printSearchResult(result2, target, 2);

        System.out.println("\nСТРУКТУРА ДЕРЕВА");
        printTreeStructure(root);
    }

    private treenode bfsClassic(treenode root, int target) {
        if (root == null) return null;
        int steps = 0;

        Queue<treenode> queue = new LinkedList<>();
        Set<treenode> visited = new HashSet<>();

        queue.offer(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            treenode current = queue.poll();
            steps++;

            System.out.println("Шаг " + steps + ": Проверяем узел " + current.value);

            if (current.value == target) {
                System.out.println("Найден узел " + current.value + " за " + steps + " шагов");
                return current;
            }

            for (treenode child : current.children) {
                if (!visited.contains(child)) {
                    queue.offer(child);
                    visited.add(child);
                    System.out.println("  Добавляем в очередь дите: " + child.value);
                }
            }
        }

        System.out.println("Узел со значением " + target + " не найден");
        return null;
    }

    private treenode bfsLevelOrder(treenode root, int target) {
        if (root == null) return null;
        int steps = 0;
        int level = 0;

        Queue<treenode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.println("\nУровень " + level + " (" + levelSize + " узлов)");

            for (int i = 0; i < levelSize; i++) {
                treenode current = queue.poll();
                steps++;

                System.out.println("  Узел " + current.value +
                        (current.value == target ? "НАЙДЕН" : ""));

                if (current.value == target) {
                    System.out.println("Найден на уровне " + level + " за " + steps + " шагов");
                    return current;
                }

                for (treenode child : current.children) {
                    queue.offer(child);
                }
            }
            level++;
        }

        System.out.println("Узел со значением " + target + " не найден");
        return null;
    }

    private void printSearchResult(treenode result, int target, int method) {
        System.out.println("\nРЕЗУЛЬТАТ метода " + method + ":");
        if (result != null) {
            System.out.println("Успешно найден узел со значением " + target);
        } else {
            System.out.println("Узел со значением " + target + " не найден в дереве");
        }
    }

    private void printTreeStructure(treenode root) {
        System.out.println("Структура дерева (BFS обход):");

        Queue<treenode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Уровень " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                treenode current = queue.poll();
                System.out.print(current.value);

                if (!current.children.isEmpty()) {
                    System.out.print(" → [");
                    for (int j = 0; j < current.children.size(); j++) {
                        System.out.print(current.children.get(j).value);
                        if (j < current.children.size() - 1) {
                            System.out.print(", ");
                        }
                        queue.offer(current.children.get(j));
                    }
                    System.out.print("]");
                }

                if (i < levelSize - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            level++;
        }
    }

    public List<Integer> bfsGetAllNodes(treenode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<treenode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            treenode current = queue.poll();
            result.add(current.value);

            for (treenode child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }
}
