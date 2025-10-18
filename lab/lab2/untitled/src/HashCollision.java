import java.util.HashMap;

public class HashCollision {

    static class BadHashKey {
        private  String value;

        public BadHashKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static void main(String[] args) {
        HashMap<BadHashKey, String> map = new HashMap<>();

        BadHashKey key1 = new BadHashKey("key1");
        BadHashKey key2 = new BadHashKey("key2");
        BadHashKey key3 = new BadHashKey("key3");

        System.out.println("Хэш-коды ключей:");

        map.put(key1, "value1");
        map.put(key2, "value2");
        map.put(key3, "value3");
        System.out.println("key1 hash: " + key1.hashCode() + ". map value: "+ map.get(key1));
        System.out.println("key2 hash: " + key2.hashCode() + ". map value: "+ map.get(key2));
        System.out.println("key3 hash: " + key3.hashCode() + ". map value: "+ map.get(key3));

        System.out.println("Размер HashMap: " + map.size());
        System.out.println("Все элементы успешно добавлены, несмотря на коллизии!");
    }
}