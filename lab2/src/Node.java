// узел красно-черного дерева
class Node {
    int data;
    Node left;
    Node right;
    Node parent;
    boolean color; // бит цвета

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = true; // красный - true, черный - false
    }
}
