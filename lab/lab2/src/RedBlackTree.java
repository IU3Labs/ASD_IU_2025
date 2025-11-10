// узел красно-черного дерева
class Node {
    int data;
    Node left;
    Node right;
    Node parent;
    boolean color; // бит цвета

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = true; // красный - true, черный - false
    }
}

// красно-черное дерево
//Принципы организации КЧД:
//1. Корень дерева черный.
//2. Все листья, не содержащие данных, черные
//3. Оба потомка каждого красного узла - черные
//4. Глубина в черных узлах одинаковая для любого поддерева
public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root = null; // корень дерева
    private int size = 0;

    // вставка элемента в дерево
    public void insert(int data) {
        Node newNode = new Node(data);

        // если структура данных пустая, то корень = новый элемент
        if (root == null) {
            root = newNode;
        } else {
            insertWithoutBalancing(newNode); // вставка без балансировки
        }

        balanceInsert(newNode); // балансировка

        root.color = BLACK; // корень всегда черный
        size++;
    }

    // вставка без балансировки (как в бинарном дереве)
    private void insertWithoutBalancing(Node newNode) {
        Node currentNode = root; // начинаем с корня
        Node parent = null;

        while(currentNode != null) {
            parent = currentNode;
            if (newNode.data < currentNode.data) {
                currentNode = currentNode.left;
            } else if (newNode.data > currentNode.data) {
                currentNode = currentNode.right;
            }
        }

        newNode.parent = parent;
        if (newNode.data < parent.data) {
            parent.left = newNode;
        } else if (newNode.data > parent.data) {
            parent.right = newNode;
        }
    }

    // балансировка после вставки

    private void balanceInsert(Node node) {
        // балансировка выполняется пока узел не станет корнем и его родитель красный (нарушение свойства)
        while(node != this.root && node.parent.color) {
            Node parent = node.parent;
            Node grandParent = parent.parent;

            // случай 1: родитель является левым ребенком дедушки, тогда дядя - справа
            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                // случай 1.1: дядя существует и он красный
                if (uncle != null && uncle.color) {
                    // перекрашиваем дедушку в красный, родителя и дядю в черный
                    grandParent.color = true;
                    parent.color = false;
                    uncle.color = false;
                    // перемещаемся на уровень выше для проверки возможных новых нарушений
                    node = grandParent;
                }
                // случай 1.2: дядя черный или отсутствует
                else {
                    // случай 1.2.1: узел является правым ребенком (треугольная конфигурация)
                    if (node == parent.right) {
                        // делаем левый поворот вокруг родителя
                        node = parent;
                        rotateLeft(parent);
                        parent = parent.parent;
                    }

                    // случай 1.2.2: узел является левым ребенком (линейная конфигурация)
                    // перекрашиваем родителя в черный, дедушку в красный
                    parent.color = false;
                    grandParent.color = true;
                    // делаем правый поворот вокруг дедушки
                    rotateRight(grandParent);
                }
            }
            // случай 2: родитель является правым ребенком дедушки (симметрично случаю 1)
            else {
                Node uncle = grandParent.left;
                // случай 2.1: дядя существует и он красный
                if (uncle != null && uncle.color) {
                    // перекрашиваем дедушку в красный, родителя и дядю в черный
                    grandParent.color = true;
                    parent.color = false;
                    uncle.color = false;
                    // перемещаемся на уровень выше
                    node = grandParent;
                }
                // случай 2.2: дядя черный или отсутствует
                else {
                    // случай 2.2.1: узел является левым ребенком (треугольная конфигурация)
                    if (node == parent.left) {
                        // делаем правый поворот вокруг родителя
                        node = parent;
                        rotateRight(parent);
                        parent = parent.parent;
                    }

                    // случай 2.2.2: узел является правым ребенком (линейная конфигурация)
                    // перекрашиваем родителя в черный, дедушку в красный
                    parent.color = false;
                    grandParent.color = true;
                    // делаем левый поворот вокруг дедушки
                    rotateLeft(grandParent);
                }
            }
        }

        // корень всегда черный
        this.root.color = false;
    }

    // удаление элемента из дерева
    public boolean remove(int data) {
        Node node = this.findNode(data);

        // false если элемента нет в дереве
        if (node == null) {
            return false;
        } else {
            deleteWithoutBalancing(node);
            size--;

            return true;
        }
    }

    // удаление элемента без балансировки
    private void deleteWithoutBalancing(Node node) {
        // если у элемента 2 ребенка
        if (node.left != null && node.right != null) {
            Node child = this.findMin(node.right);
            node.data = child.data;
            node = child;
        }

        // если 1 или 0 детей
        Node replacement = node.left != null ? node.left : node.right; // замена элементу, который надо удалить
        if (replacement != null) {
            replacement.parent = node.parent;

            if (node.parent == null) {
                this.root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            if (!node.color) {
                this.balanceDelete(replacement);
            }
        } else if (node.parent == null) {
            this.root = null;
        } else {
            if (!node.color) {
                this.balanceDelete(node);
            }

            if (node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
            }
        }

    }

    // балансировка после удаления
    private void balanceDelete(Node node) {
        Node current = node;

        // балансировка выполняется пока узел не станет корнем и он черный (или null)
        while(current != this.root && (current == null || !current.color)) {
            Node parent = current.parent;
            if (parent == null) {
                break;
            }

            // случай 1: текущий узел является левым ребенком родителя
            if (current == parent.left) {
                Node brother = parent.right;

                // случай 1.1: брат красный
                if (brother != null && brother.color) {
                    // преобразуем случай 1.1 в случай 1.2, 1.3 или 1.4
                    brother.color = false;
                    parent.color = true;
                    this.rotateLeft(parent);
                    brother = parent.right;
                }

                // случай 1.2, 1.3, 1.4: брат черный
                if (brother == null || brother.left != null && brother.left.color || brother.right != null && brother.right.color) {
                    // проверяем есть ли хотя бы один красный ребенок у брата
                    if (brother != null) {
                        // случай 1.3: правый ребенок брата черный, левый - красный
                        if (brother.right == null || !brother.right.color) {
                            if (brother.left != null) {
                                brother.left.color = false;
                            }
                            brother.color = true;
                            this.rotateRight(brother);
                            brother = parent.right;
                        }

                        // случай 1.4: правый ребенок брата красный
                        brother.color = parent.color;
                        parent.color = false;
                        if (brother.right != null) {
                            brother.right.color = false;
                        }
                        this.rotateLeft(parent);
                        current = this.root; // завершаем балансировку
                    }
                } else {
                    // случай 1.2: оба ребенка брата черные
                    brother.color = true;
                    current = parent; // перемещаем проблему на уровень выше
                }
            }
            // случай 2: текущий узел является правым ребенком родителя (симметрично случаю 1)
            else {
                Node brother = parent.left;

                // случай 2.1: брат красный
                if (brother != null && brother.color) {
                    brother.color = false;
                    parent.color = true;
                    this.rotateRight(parent);
                    brother = parent.left;
                }

                // случай 2.2, 2.3, 2.4: брат черный
                if (brother == null || brother.left != null && brother.left.color || brother.right != null && brother.right.color) {
                    if (brother != null) {
                        // случай 2.3: левый ребенок брата черный, правый - красный
                        if (brother.left == null || !brother.left.color) {
                            if (brother.right != null) {
                                brother.right.color = false;
                            }
                            brother.color = true;
                            this.rotateLeft(brother);
                            brother = parent.left;
                        }

                        // случай 2.4: левый ребенок брата красный
                        brother.color = parent.color;
                        parent.color = false;
                        if (brother.left != null) {
                            brother.left.color = false;
                        }
                        this.rotateRight(parent);
                        current = this.root; // завершаем балансировку
                    }
                } else {
                    // случай 2.2: оба ребенка брата черные
                    brother.color = true;
                    current = parent; // перемещаем проблему на уровень выше
                }
            }
        }

        // текущий узел черный
        if (current != null) {
            current.color = false;
        }
    }

    // поиск минимального узла
    private Node findMin(Node node) {
        while(node.left != null) {
            node = node.left;
        }

        return node;
    }

    // поиск узла
    private Node findNode(int data) {
        Node currentNode = this.root;

        while(currentNode != null) {
            if (data == currentNode.data) {
                return currentNode;
            }

            if (data < currentNode.data) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return null;
    }

    // количество элементов в дереве
    int getSize(RedBlackTree tree) {
        return tree.size;
    }

    // левый поворот
    private void rotateLeft(Node node) {
        // сохраняем правого ребенка узла, вокруг которого выполняется поворот
        Node rightChild = node.right;

        // переносим левого ребенка правого ребенка на место правого ребенка текущего узла
        node.right = rightChild.left;
        // если перенесенный ребенок существует, обновляем его ссылку на родителя
        if (node.right != null) {
            node.right.parent = node;
        }

        // устанавливаем родителя для правого ребенка
        rightChild.parent = node.parent;
        // обновляем ссылку родителя текущего узла
        if (node.parent == null) {
            // если узел был корнем, правый ребенок становится новым корнем
            this.root = rightChild;
        } else if (node == node.parent.left) {
            // если узел был левым ребенком, обновляем левую ссылку родителя
            node.parent.left = rightChild;
        } else {
            // если узел был правым ребенком, обновляем правую ссылку родителя
            node.parent.right = rightChild;
        }

        // устанавливаем текущий узел как левого ребенка для правого ребенка
        rightChild.left = node;
        // обновляем родителя текущего узла
        node.parent = rightChild;
    }

    // правый поворот
    private void rotateRight(Node node) {
        // сохраняем левого ребенка узла, вокруг которого выполняется поворот
        Node leftChild = node.left;

        // переносим правого ребенка левого ребенка на место левого ребенка текущего узла
        node.left = leftChild.right;
        // если перенесенный ребенок существует, обновляем его ссылку на родителя
        if (node.left != null) {
            node.left.parent = node;
        }

        // устанавливаем родителя для левого ребенка
        leftChild.parent = node.parent;
        // обновляем ссылку родителя текущего узла
        if (node.parent == null) {
            // если узел был корнем, левый ребенок становится новым корнем
            this.root = leftChild;
        } else if (node == node.parent.left) {
            // если узел был левым ребенком, обновляем левую ссылку родителя
            node.parent.left = leftChild;
        } else {
            // если узел был правым ребенком, обновляем правую ссылку родителя
            node.parent.right = leftChild;
        }

        // устанавливаем текущий узел как правого ребенка для левого ребенка
        leftChild.right = node;
        // обновляем родителя текущего узла
        node.parent = leftChild;
    }

    // рекурсивный алгоритм для вывода дерева
    public void printTree() {
        System.out.println("tree:");
        this.printTreeRecursive(this.root);
        System.out.println();
    }

    private void printTreeRecursive(Node node) {
        if (node == null) {
            System.out.print("null");
        } else {
            String color = node.color ? "R" : "B"; // определяем какой цвет у данного узла
            System.out.print(node.data + color + "(");
            this.printTreeRecursive(node.left);
            System.out.print(",");
            this.printTreeRecursive(node.right);
            System.out.print(")");
        }
    }
}

class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // добавление элементов
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(5);
        tree.insert(3);
        tree.insert(50);
        tree.insert(40);
        tree.printTree();

        // удаление элементов
        tree.remove(10);
        tree.remove(3);
        tree.remove(50);
        tree.printTree();

        // количество элементов в дереве
        System.out.println(tree.getSize(tree));
    }
}