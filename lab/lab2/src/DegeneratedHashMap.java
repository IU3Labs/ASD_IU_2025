import java.util.HashMap;
import java.util.Map;

public class DegeneratedHashMap{

    static class BadKey {
        private final int value;

        private static long equalsCount = 0;

        BadKey(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object o) {
            equalsCount++;
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BadKey badKey = (BadKey) o;
            return value == badKey.value;
        }
    }

    public static void main(String[] args) {
        Map<BadKey, Integer> map = new HashMap<>();

        int n = 100;

        for (int i = 0; i < n; i++) {
            map.put(new BadKey(i), i);
        }

        BadKey.equalsCount = 0;
        BadKey searchKey = new BadKey(n - 1);

        long start = System.nanoTime();
        Integer result = map.get(searchKey);
        long end = System.nanoTime();

        System.out.println("Результат поиска: " + result);
        System.out.println("Время поиска (нс): " + (end - start));
        System.out.println("Сколько раз вызывался equals: " + BadKey.equalsCount);

        if(BadKey.equalsCount > Math.sqrt(n))
            System.out.println("Мапа вырожденная, сложность O(n)");
        else
            System.out.println("Это обычная мапа, невырожденная, сложность O(1) ");
    }
}
