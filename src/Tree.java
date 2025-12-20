public class Tree {

    public static class TreeNode {
        Integer value;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(Integer value) {
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    private TreeNode rootNode;

    public Tree() {
        this.rootNode = null;
    }

    public void add(Integer value) {
        rootNode = addValue(rootNode, value);
    }

    private TreeNode addValue(TreeNode currentNode, Integer value) {
        // Если достигли пустого места - создаем новый узел
        if (currentNode == null) {
            return new TreeNode(value);
        }

        // Сравниваем значение с текущим узлом
        int comparison = value.compareTo(currentNode.value);

        if (comparison < 0) {
            // Значение меньше - идем в левое поддерево
            currentNode.leftChild = addValue(currentNode.leftChild, value);
        } else if (comparison > 0) {
            // Значение больше - идем в правое поддерево
            currentNode.rightChild = addValue(currentNode.rightChild, value);
        }
        // Если comparison == 0 - значение уже существует, ничего не делаем

        return currentNode;
    }

    public TreeNode getRoot() {
        return rootNode;
    }
}