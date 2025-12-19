package BFS;
import java.util.*;

public class TreeNode {
    int value;
    List<TreeNode> children = new ArrayList<>();

    public TreeNode(int value) {
        this.value = value;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
