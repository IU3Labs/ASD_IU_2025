import java.util.*;

class TreeNode {
    int val;
    List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
