public class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // добавление элементов
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(5);
        tree.insert(3);
        tree.insert(50);
        tree.insert(40);
        tree.printTree();

        // удаление элементов
        tree.remove(10);
        tree.remove(3);
        tree.remove(50);
        tree.printTree();

        // количество элементов в дереве
        System.out.println(tree.getSize(tree));
    }
}