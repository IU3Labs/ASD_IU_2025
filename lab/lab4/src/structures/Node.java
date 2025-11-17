package structures;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    public T value;
    public List<Node<T>> children;

    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    };

    public void addChild(Node<T> child) {
        this.children.add(child);
    };
};
