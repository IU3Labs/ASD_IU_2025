/*Воспользуемся уже ранее реализованным RBT в lab2.
Пришлось изменить:
Node - теперь public
поля Node теперь private и имеют getter-ы
В класс RedBlackTree добавлены методы getRootNode() и getNilNode()
*/

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

//2. Реализовать поиск в дереве (Iterative deepening depth-first search)

public class IterativeDeepeningDFS {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        Object root = tree.getRootNode();
        Object nil = tree.getNilNode();

        boolean found = iddfs(
                root,
                node -> ((RedBlackTree<Integer>.Node) node).getData() != null
                        && ((RedBlackTree<Integer>.Node) node).getData().equals(7),
                node -> {
                    RedBlackTree<Integer>.Node n = (RedBlackTree<Integer>.Node) node;
                    return java.util.List.of(n.getLeft(), n.getRight());
                },
                4
        );

        tree.printTree();
        System.out.println(found);
    }

    //Возвращаем true если target достигаем из src через maxDepth
    public static <T> boolean iddfs(
            T start,
            Predicate<T> isTarget,
            Function<T, List<T>> neighbors,
            int maxDepth
    ) {
        for (int limit = 0; limit <= maxDepth; limit++) {
            if (dls(start, isTarget, neighbors, limit)) {
                return true;
            }
        }
        return false;
    }

    //Depth-Limited Search
    private static <T> boolean dls(
            T node,
            Predicate<T> isTarget,
            Function<T, List<T>> neighbors,
            int limit
    ) {
        if (node == null) return false;
        if (isTarget.test(node)) return true;
        if (limit <= 0) return false;

        for (T next : neighbors.apply(node)) {
            if (dls(next, isTarget, neighbors, limit - 1)) {
                return true;
            }
        }
        return false;
    }
}
