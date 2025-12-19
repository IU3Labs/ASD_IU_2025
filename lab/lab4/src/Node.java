import java.util.*;

public class Node {
    int value;
    List<Node> children;

    Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    void addChild(Node child) {
        children.add(child);
    }
}
