package lab2;

public class Node {
    public String color;
    public Node parent;
    public Node left_child;
    public Node right_child;
    public int value;

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.color = "RED";
        this.left_child = null;
        this.right_child = null;
    }
}