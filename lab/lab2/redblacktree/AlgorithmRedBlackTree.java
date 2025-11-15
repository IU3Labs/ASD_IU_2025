package redblacktree;

public class AlgorithmRedBlackTree {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static class Node{
        int data;
        Node left, right, parent;
        boolean color;

        Node(int data) {
            this.data = data;
            color = RED; // цвет по умолчанию - красный
        }
    }
//вставка
    public void insert(int data) {
        Node newNode = new Node(data);
        if (root == null) { //если дерево пустое, новый элемент будет корнем
            root = newNode;
            root.color = BLACK;
            return;
        }
        Node parent = null, current = root;
        while (current != null) { // ищем крайний элемент, к которому будем присоединяться
            parent = current;
            if (data <= current.data) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        newNode.parent = parent;
        if (data < parent.data) { //выбираем, присоединяться или справа
            parent.left = newNode;
        }
        else {
            parent.right = newNode;
        }
        fixInsertion(newNode);
    }

    private void fixInsertion(Node node){
        while (node != root && node.parent.color == RED) { // пока мы не в корне и наш родитель красный (возможно нарушение - 2 красных подряд)
            Node parent = node.parent;
            Node grandparent = node.parent.parent;
            if (parent == grandparent.left) { //случай А: родитель - левый ребенок дедушки
                Node uncle = grandparent.right; //тогда дядя - правый ребенок дедушки
                if (uncle != null && uncle.color == RED) { //случай А1: дядя существует и он красный
                    parent.color = BLACK; //папа становится черным
                    uncle.color = BLACK; //дядя становится черный
                    grandparent.color = RED; //дедушка - красным
                    node = grandparent; //продолжаем работу с дедушкой
                }
                else {
                    if (node == parent.right) { //Случай А2: Дядя черный или отсутствует, а мы - правый ребенок отца. Делаем ситуацию линейной
                        node = parent; //продолжаем работу с отцом
                        rotateLeft(node); //левый поворот вокруг отца
                    } //ситуация уже линейная. Перекрашиваем и делаем поворот вокруг дедушки
                    parent.color = BLACK; //папа становится черным
                    grandparent.color = RED; //дедушка - красным
                    rotateRight(grandparent); //правый поворот вокруг дедушки
                }
            } else { //случай Б: папа - правый ребенок дедушки
                Node uncle = grandparent.left; //тогда дядя - левый ребенок
                if (uncle != null && uncle.color == RED) { //случай Б1: если дядя существует и он красный
                    parent.color = BLACK; //тогда папа будет черным
                    uncle.color = BLACK; //дядя тоже
                    grandparent.color = RED; //а дедушка - красным
                    node = grandparent; //продолжаем проверку с дедушки
                } else { //случай Б2: дядя не существует или он черный и мы левый ребенок отца. Делаем ситуацию линейной
                    if (node == parent.left) {
                        node = parent; //работаем с отцом
                        rotateRight(node); //правый поворот вокруг отца
                    }
                    parent.color = BLACK; //случай Б3. Ситуация линейная. Перекрашиваем и левый поворот вокруг деда
                    grandparent.color = RED;
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = BLACK; //ну и на всякий напоминаем, что корень - черный
    }

    private void rotateLeft(Node node) { //левый поворот
        Node rightChild = node.right; //заносим нашего правого ребенка в rightChild
        node.right = rightChild.left; // левый ребенок нашего правого ребенка становится нашим правым ребенком
        if (rightChild.left != null) { //если этот левый ребенок ex правого ребенка не null
            rightChild.left.parent = node; //тогда его отцом становимся мы
        }
        rightChild.parent = node.parent;    //родителем нашего экс-правого ребенка становится наш отец
        if (node.parent == null) { //если он не нуль
            root = rightChild; //то в рамках нашего локального дерева он становится корнем
        }
        else if (node == node.parent.left) { //иначе если мы явлемся левым ребенком нашего отца
            node.parent.left = rightChild; //то на наше место встает наш экс-правый ребенок
        }
        else {
            node.parent.right = rightChild; //иначе - на место нашего брата
        }
    }

    /* если до поворота было     то стало
    x                                          y
     \                                        / \
      y                                      x   B
     / \                                    /
    A   B                                  A

     */

    private void rotateRight(Node node) { //аналогичен правый поворот
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        }
        else if (node == node.parent.left) {
            node.parent.left = leftChild;
        }
        else {
            node.parent.right = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    public int countNodes() {
        return countHelper(root);
    }

    private int countHelper(Node node) {
        if (node == null) return 0;
        return 1 + countHelper(node.left) + countHelper(node.right);
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void delete(int key) {
        Node node = root; //откуда начинаем искать
        Node target = null; //то, куда кладем найденный элемент

        while (node != null) { //поиск элемента
            if (key == node.data) {
                target = node;
                break;
            } else if (key < node.data)
                node = node.left;
            else
                node = node.right;
        }
        if (target == null) {
            System.out.println("Элемент " + key + " не найден.");
            return;
        }
        deleteNode(target);
        System.out.println("Удалён элемент: " + key);
    }

    private void deleteNode(Node node) { //сосбвтенно удаление
        Node replacement; //узел, который встанет на место удаленного
        Node fixNode; //узел, откуда, если что, начнем балансировку
        boolean originalColor = node.color; //цвет удаляемого узла
        if (node.left == null) { //случай А: нет левого ребенка
            replacement = node.right; //значит на место удаленного встанет правый ребенок
            transplant(node, node.right); //смена местами
            fixNode = replacement; //Будем балансировать с нашего новичка
        }
        else if (node.right == null) { //случай Б: нет правого ребенка. Аналогично
            replacement = node.left;
            transplant(node, node.left);
            fixNode = replacement;
        }
        else { //Случай В: оба ребенка есть
            Node successor = minimum(node.right); //находим среди правых потомков самого маленького. Он будет нашим наследником
            originalColor = successor.color; //запоминаем его цвет
            replacement = successor.right; //Поскольку наследний самый младший, поэтому берем его правого сына
            if (successor.parent == node) { //если наследний - наш сын
                if (replacement != null) //если нащш заменяющий не нулль
                    replacement.parent = successor; //то его отцом станет наследник
            }
            else { //иначе копаем глубже.
                transplant(successor, successor.right); //Меняем наследника и его правого сына
                successor.right = node.right; //правый сыном наследника становится наш правый сын
                successor.right.parent = successor; //обновляем родителя
            }
            transplant(node, successor); //поднимаем наследника до уровня нас (удаляемых_
            successor.left = node.left; //передаем ему нашего левого сына
            successor.left.parent = successor; //передаем ему отцовство на нашего бывшего левого сына
            successor.color = node.color; //передаем ему наш цвет
            fixNode = replacement;
        }
        if (originalColor == BLACK && fixNode != null) //если мы удаляли черный узел и нам есть, с кого начинать балансировку, то мы забываем про сон и расписываем балансировку
            fixDeletion(fixNode);
    }

    private void transplant(Node oldNode, Node newNode) { //замена одного узла другим
        if (oldNode.parent == null) //если у старого узла нет родителя, то он корень
            root = newNode;
        else if (oldNode == oldNode.parent.left) // иначе, если старый является левым ребенокм
            oldNode.parent.left = newNode; // то новый встает на его место
        else
            oldNode.parent.right = newNode; //иначе новый встает на место правого

        if (newNode != null) //если новый элемент не нулл, то их родители меняются местами
            newNode.parent = oldNode.parent;
    }

    private void fixDeletion(Node node){
        while (node != root && node.color == BLACK) { //пока мы не дошли до корня и есть допольнительный черный
            if (node == node.parent.left) {
                Node sibling = node.parent.right; // наш братишка. С ним и играем
                if (sibling.color == RED) { // если братишка красный
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) { //Если наш брат черный и всего его дети черные
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.right == null || sibling.right.color == BLACK) { //если наш брат черный, его правый ребенок либо есть, либо черный и у него есть левый сын
                        if (sibling.left != null)
                            sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.color = node.parent.color; //брат черный, а его правый рбенок красный
                    node.parent.color = BLACK;
                    if (sibling.right != null)
                        sibling.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else { //аналоигчно, когда мы - правые ребенок
                Node sibling = node.parent.left;

                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }

                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.left == null || sibling.left.color == BLACK) {
                        if (sibling.right != null)
                            sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    if (sibling.left != null)
                        sibling.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    public void printTree() {
        printHelper(root, "", true);
    }

    private void printHelper(Node node, String indent, boolean last) { //indent - строка с отступами для визуала, last - флаг последнего (правого) ребенка
        if (node != null) { // если узел, с которого мы начинаем - не нуль
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }
            String color = node.color == RED ? "RED" : "BLACK";
            System.out.println(node.data + "(" + color + ")");
            printHelper(node.left, indent, false);
            printHelper(node.right, indent, true);
        }
    }
}
