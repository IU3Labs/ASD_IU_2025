/*Группа А, задание 4: Реализовать поиск в дереве в ширину двумя способами.*/

import java.util.*;

public class BreadthFirstSearch {

    public static void bfsStandard(DepthFirstSearch.TreeNode root) {
        if (root == null) return;

        Queue<DepthFirstSearch.TreeNode> queue = new LinkedList<>();
        Set<DepthFirstSearch.TreeNode> visited = new HashSet<>();

        queue.offer(root);
        visited.add(root);

        System.out.print("Стандартный BFS: ");

        while (!queue.isEmpty()) {
            DepthFirstSearch.TreeNode current = queue.poll();
            System.out.print(current.value + " ");

            for (DepthFirstSearch.TreeNode child : current.children) {
                if (!visited.contains(child)) {
                    queue.offer(child);
                    visited.add(child);
                }
            }
        }
        System.out.println();
    }

    public static void bfsLevelOrder(DepthFirstSearch.TreeNode root) {
        if (root == null) return;

        Queue<DepthFirstSearch.TreeNode> queue = new LinkedList<>();
        Set<DepthFirstSearch.TreeNode> visited = new HashSet<>();

        queue.offer(root);
        visited.add(root);

        System.out.println("BFS по уровням:");
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Уровень " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                DepthFirstSearch.TreeNode current = queue.poll();
                System.out.print(current.value + " ");

                for (DepthFirstSearch.TreeNode child : current.children) {
                    if (!visited.contains(child)) {
                        queue.offer(child);
                        visited.add(child);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    public static void testBFS() {

        DepthFirstSearch.TreeNode testTree = DepthFirstSearch.createTestTree();

        bfsStandard(testTree);
        bfsLevelOrder(testTree);
    }

    public static void main(String[] args) {
        testBFS();
    }
}