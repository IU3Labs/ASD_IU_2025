import java.util.HashMap;
import java.util.Map;

public class MAp_dopzadanie {

    static class BadKey {
        private final int value;

        public BadKey(int value) {
            this.value = value;
        }

        public int hashCode() {
            return 1;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BadKey)) return false;
            return value == ((BadKey) o).value;
        }

        public String toString() {
            return "Key(" + value + ")";
        }
    }

    public static void main(String[] args) {
        Map<BadKey, String> map = new HashMap<>();

        int n = 20;
        for (int i = 1; i <= n; i++) {
            map.put(new BadKey(i), "value_" + i);
        }

        System.out.println("Map size: " + map.size());
        System.out.println("Retrieving elements...");

        long start = System.nanoTime();
        String res = map.get(new BadKey(n));
        long end = System.nanoTime();

        System.out.println("Result for key " + n + ": " + res);
        System.out.println("Lookup time: " + (end - start) + " ns");
        System.out.println("\nAll map entries:");

        for (var e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}