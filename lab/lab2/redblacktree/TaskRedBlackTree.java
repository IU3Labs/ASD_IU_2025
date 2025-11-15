package redblacktree;

public class TaskRedBlackTree {
    public static void main(String[] args){
        AlgorithmRedBlackTree tree = new AlgorithmRedBlackTree();
        int[] values = {10, 20, 30, 15, 25, 5, 1};
        for (int v : values) tree.insert(v);
        System.out.println("Исходное дерево:");
        tree.printTree();
        System.out.println("\nКоличество элементов: " + tree.countNodes());
        tree.delete(20);
        System.out.println("\nПосле удаления 20:");
        tree.printTree();
        System.out.println("\nКоличество элементов: " + tree.countNodes());
    }
}
