import java.util.ArrayDeque;
import java.util.Deque;

class TreeSearchBFS {
    TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    TreeNode bfsIterative(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.val == target) {
                return node;
            }
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return null;
    }

    int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;
    }

    TreeNode bfsRecursiveLevel(TreeNode root, int target) {
        int h = height(root);
        for (int level = 0; level < h; level++) {
            TreeNode found = searchLevel(root, target, level);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    TreeNode searchLevel(TreeNode root, int target, int level) {
        if (root == null) {
            return null;
        }
        if (level == 0) {
            if (root.val == target) {
                return root;
            }
            return null;
        }
        TreeNode leftResult = searchLevel(root.left, target, level - 1);
        if (leftResult != null) {
            return leftResult;
        }
        return searchLevel(root.right, target, level - 1);
    }

    public static void main(String[] args) {
        TreeSearchBFS tree = new TreeSearchBFS();
        TreeNode root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            root = tree.insert(root, v);
        }

        TreeNode a = tree.bfsIterative(root, 40);
        TreeNode b = tree.bfsIterative(root, 90);
        TreeNode c = tree.bfsRecursiveLevel(root, 40);
        TreeNode d = tree.bfsRecursiveLevel(root, 90);

        System.out.println(a != null);
        System.out.println(b != null);
        System.out.println(c != null);
        System.out.println(d != null);
    }
}
