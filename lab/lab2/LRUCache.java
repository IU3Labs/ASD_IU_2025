/*Задание В2
При добавлении нового элемента сверх лимита удаляется самый давно неиспользуемый
 */
import java.util.HashMap;

public class LRUCache<KeyType, ValueType> {

    private class CacheNode {
        KeyType nodeKey;
        ValueType nodeValue;
        CacheNode previousNode;
        CacheNode nextNode;

        CacheNode(KeyType nodeKey, ValueType nodeValue) {
            this.nodeKey = nodeKey;
            this.nodeValue = nodeValue;
        }
    }

    private final int maxSize;
    private HashMap<KeyType, CacheNode> nodeMap;
    private CacheNode mostRecentNode;
    private CacheNode leastRecentNode;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.nodeMap = new HashMap<>();
        this.mostRecentNode = null;
        this.leastRecentNode = null;
    }

    public ValueType retrieve(KeyType key) {
        CacheNode node = nodeMap.get(key);
        if (node == null) {
            System.out.println("Ключ " + key + " не найден");
            return null;
        }

        moveToFront(node);
        return node.nodeValue;
    }

    public void store(KeyType key, ValueType value) {
        CacheNode node = nodeMap.get(key);

        if (node != null) {
            node.nodeValue = value;
            moveToFront(node);
        } else {
            CacheNode newNode = new CacheNode(key, value);
            nodeMap.put(key, newNode);
            addToFront(newNode);

            if (nodeMap.size() > maxSize) {
                removeLeastRecent();
            }
        }
    }

    private void addToFront(CacheNode node) {
        node.nextNode = mostRecentNode;
        node.previousNode = null;

        if (mostRecentNode != null) {
            mostRecentNode.previousNode = node;
        }
        mostRecentNode = node;

        if (leastRecentNode == null) {
            leastRecentNode = mostRecentNode;
        }
    }

    private void removeNode(CacheNode node) {
        if (node.previousNode != null)
            node.previousNode.nextNode = node.nextNode;
        else
            mostRecentNode = node.nextNode;

        if (node.nextNode != null)
            node.nextNode.previousNode = node.previousNode;
        else
            leastRecentNode = node.previousNode;
    }

    private void moveToFront(CacheNode node) {
        removeNode(node);
        addToFront(node);
    }

    private void removeLeastRecent() {
        if (leastRecentNode == null) return;
        nodeMap.remove(leastRecentNode.nodeKey);
        removeNode(leastRecentNode);
    }

    public void displayCache() {
        CacheNode current = mostRecentNode;
        System.out.print("Содержимое кэша: [");
        while (current != null) {
            System.out.print(current.nodeKey + "=" + current.nodeValue);
            current = current.nextNode;
            if (current != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> studentCache = new LRUCache<>(3);

        studentCache.store(1, "Конспект по математике");
        studentCache.store(2, "Лабораторная по ООП");
        studentCache.store(3, "Методичка по физике");

        studentCache.retrieve(1);
        studentCache.displayCache();
        studentCache.store(4, "Список вопросов к экзамену");
        studentCache.displayCache();

        studentCache.retrieve(3);
        studentCache.displayCache();
    }
}