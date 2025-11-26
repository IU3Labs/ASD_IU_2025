package lab2.c;

public class RBTDemo {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // добавление элементов
        tree.insert(99);
        tree.insert(13);
        tree.insert(7);
        tree.insert(40);
        tree.insert(25);
        tree.insert(67);
        tree.insert(82);
        tree.printTree();

        // удаление элементов
        tree.remove(99);
        tree.remove(40);
        tree.remove(25);
        tree.printTree();

        // количество элементов в дереве
        System.out.println(tree.getSize(tree));
    }
}
