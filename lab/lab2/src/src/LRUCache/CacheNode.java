// Узел двусвязного списка для LRU Cache

package LRUCache;
public class CacheNode {
    public int key;
    public int value;
    public CacheNode prev;
    public CacheNode next;
    public CacheNode hashNext;

    public CacheNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
        this.hashNext = null;
    }
}