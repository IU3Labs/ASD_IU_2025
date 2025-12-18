import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }
}