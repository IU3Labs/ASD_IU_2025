import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache {
    private HashMap<Integer, Integer> values;
    private LinkedList<Integer>history;
    private int length;
    private int maxLength;

    public LRUCache(int len) {
        values = new HashMap<Integer, Integer>();
        history = new LinkedList<Integer>();
        maxLength = len;
    }

    public int Get(int key) {
        int result;
        if (values.containsKey(key)) {
            result = values.get(key);
            history.removeFirstOccurrence(key);
            history.add(0, key);
        } else {
            result = 0;
            System.out.println("Элемент не найден");
        }
//        System.out.println("Порядок связного списка в методе Get():");
//        for (int i = 0; i < length; i++) {
//            System.out.println(history.get(i));
//        }
//        System.out.println();
        return result;
    }

    public void Put(int key, int value) {
        if (length < maxLength) {
            values.put(key, value);
            history.add(0, key);
            length++;
        } else {
            int garbage;
            garbage = history.remove(maxLength-1);
            values.remove(garbage);
            values.put(key, value);
            history.addFirst(key);
        }
//        System.out.println("Порядок связного списка в методе Put():");
//        for (int i = 0; i < length; i++) {
//            System.out.println(history.get(i));
//        }
//        System.out.println();
    }

    public int Length() {
        return length;
    }

    public void Display() {
        System.out.println("Элементы списка:");
        for (int i = 0; i < length; i++) {
            System.out.println(values.get(history.get(i)));
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.Put(0, 10);
        cache.Put(1, 20);
        cache.Display();
        System.out.printf("Элемент с ключом 0: %d\n", cache.Get(0));
        cache.Display();
        cache.Put(2, 30);
        cache.Display();
    }

}
