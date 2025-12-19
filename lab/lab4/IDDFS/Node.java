package IDDFS;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public final int value;
    public final List<Node> children = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }

    public Node addChild(Node child) {
        this.children.add(child);
        return this;
    }
}
