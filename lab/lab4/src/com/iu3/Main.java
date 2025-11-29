package com.iu3;

public class Main {
    public static void main(String[] args) {

        // 2. Реализовать поиск в дереве (Iterative deepening depth-first search)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        IDDFS iddfs = new IDDFS();
        System.out.println(iddfs.search(root, 5)); // true
        System.out.println(iddfs.search(root, 6)); // false

        // 3. Реализовать поиск в дереве в глубину двумя способами.
        // Строим простое дерево:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);

        DFS dfs = new DFS();
        int targetFound = 5;
        int targetNotFound = 6;

        // Рекурсивный поиск
        System.out.println("Рекурсивный DFS:");
        System.out.println("Ищем " + targetFound + ": " + dfs.searchRecursive(root2, targetFound)); // true
        System.out.println("Ищем " + targetNotFound + ": " + dfs.searchRecursive(root2, targetNotFound)); // false

        // Итеративный поиск
        System.out.println("\nИтеративный DFS:");
        System.out.println("Ищем " + targetFound + ": " + dfs.searchIterative(root2, targetFound)); // true
        System.out.println("Ищем " + targetNotFound + ": " + dfs.searchIterative(root2, targetNotFound)); // false



        // 4. Реализовать поиск в дереве в ширину двумя способами.
        // Строим дерево:
        //       1
        //      / \
        //     2   3
        //    /   / \
        //   4   5   6
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(6);

        BFS bfs = new BFS();
        int targetFound1 = 5;
        int targetNotFound1 = 9;

        // Итеративный BFS
        System.out.println("Итеративный BFS:");
        System.out.println("Ищем " + targetFound1 + ": " + bfs.searchIterative(root1, targetFound1)); // true
        System.out.println("Ищем " + targetNotFound1 + ": " + bfs.searchIterative(root1, targetNotFound1)); // false

        // Рекурсивный BFS
        System.out.println("\nРекурсивный BFS:");
        System.out.println("Ищем " + targetFound1 + ": " + bfs.searchRecursive(root1, targetFound1)); // true
        System.out.println("Ищем " + targetNotFound1 + ": " + bfs.searchRecursive(root1, targetNotFound1)); // false
    }
}