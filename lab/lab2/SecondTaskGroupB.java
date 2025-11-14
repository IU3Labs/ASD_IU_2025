package lab2;

import java.util.HashMap;

public class SecondTaskGroupB {

    public static class LRUCache {
        static void main(String[] args) {
            LRUCache cache = new LRUCache(2);

            cache.addNewValue(1, 100);
            cache.addNewValue(2, 200);

            System.out.println(cache.getKeyValue(1));


            cache.addNewValue(3, 300);

            System.out.println(cache.getKeyValue(2));

            cache.addNewValue(4, 400);

            System.out.println(cache.getKeyValue(1));
            System.out.println(cache.getKeyValue(3));
            System.out.println(cache.getKeyValue(4));
        }

        private static class Node {
            int key;
            int value;
            Node previous;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int maxSizeCash;
        private final HashMap<Integer, Node> map;
        private final Node head;
        private final Node tail;


        public LRUCache(int maxSizeCash) {
            this.maxSizeCash = maxSizeCash;
            this.map = new HashMap<>();


            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.previous = head;
        }


        public int getKeyValue(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);

            removeNode(node);
            insertToHead(node);

            return node.value;
        }


        public void addNewValue(int key, int value) {


            if (map.containsKey(key)) {
                removeNode(map.get(key));
            }

            Node node = new Node(key, value);
            insertToHead(node);
            map.put(key, node);


            if (map.size() > maxSizeCash) {
                Node last = tail.previous;
                removeNode(last);
                map.remove(last.key);
            }
        }


        private void removeNode(Node node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }


        private void insertToHead(Node node) {
            node.next = head.next;
            node.previous = head;

            head.next.previous = node;
            head.next = node;
        }

    }

}
