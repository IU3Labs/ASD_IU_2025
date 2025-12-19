package IDDFS;
import java.util.ArrayList;
import java.util.List;

/* Итеративный поиск в глубину
* root - корень дерева
* target - искомое значение
* maxDepth - максимальная глубина поиска*/
public class IDDGSAlgorithm {
    public static List<Node> iddfsPindPath(Node root, int target, int maxDepth) {
        if (root == null) return List.of(); //Если дерево пустое, то ничего не надо
        for (int depth = 0; depth < maxDepth; depth++) { //итеративно увеливаем глубину поиска
            List<Node> path = new ArrayList<>(); //список для хранения текущего пути
            if (dls(root, target, depth, path)) { //dfs с ограничением по глубине (dls)
                return path; //если цель найдена, возвращаем путь
            }
        }
        return List.of(); //если цель не найдена, возвращаем путь
    }

    /*поиск в глубину с ограничением
    * cur - текущий узел
    * target - искомое значение
    * limit - оставшаяся глубина
    * path - текущий путь */
    private static boolean dls(Node cur, int target, int limit, List<Node> path) {
        path.add(cur); //добавляем узел в путь
        if (cur.value == target) return true;
        if (limit == 0) { //если глубина исчерпана, то заканчиваем
            path.remove(path.size() - 1); //не забываем убрать узел
            return false;
        }
        for (Node child : cur.children) { //рекурсия. Обходим всех детей
            if (dls(child, target, limit - 1, path)) { //переходим к ребёнку, уменьшая лимит глубины
                return true;
            }
        }
        path.remove(path.size() - 1); //если ничего не получилось, то откат
        return false;
    }

    public static String pathToString(List<Node> path) {
        if (path.isEmpty()) return "(not found)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(path.get(i).value);
        }
        return sb.toString();
    }
}
