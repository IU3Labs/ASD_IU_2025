import java.util.HashMap;

public class HashCollision {

    // Создаю класс который будет ключем для хэш мапы с намерено одинаковым хэш-кодом
    static class CollidingKey {
        private String label;

        public CollidingKey(String label) {
            this.label = label;
        }

        // Метод hashCode() возвращает одно и то же значение для всех объектов чтоб создать
        // преднамеренную коллизию в HashMap и все ключи попали в один бакет.
        @Override
        public int hashCode() {
            return 42;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static void main(String[] args) {
        // Создаём хэш мапу
        HashMap<CollidingKey, String> map = new HashMap<>();

        // Создаём несколько разных ключей, у которых разные значения, но одинаковый хэш-код
        CollidingKey k1 = new CollidingKey("key1");
        CollidingKey k2 = new CollidingKey("key2");
        CollidingKey k3 = new CollidingKey("key3");

        // Добавляем пары ключ–значение в хэш мапу
        map.put(k1, "value1");
        map.put(k2, "value2");
        map.put(k3, "value3");

        // Несмотря на одинаковый hashCode, хэш мапа различает объекты с помощью equals()
        System.out.println("Хэш-коды ключей:");
        System.out.println("key1 hash: " + k1.hashCode());
        System.out.println("key2 hash: " + k2.hashCode());

        // Размер карты равен 3, значит все ключи хранятся независимо
        // несмотря на одинаковый hashCode (т.е. коллизия успешно разрешается)
        System.out.println("Размер HashMap: " + map.size());

        System.out.println("Индексы бакетов для ключей:");
        System.out.println("key1 bucket index: " + (k1.hashCode() & (16 - 1))); // 16 - начальный капасити HashMap
        System.out.println("key2 bucket index: " + (k2.hashCode() & (16 - 1)));

        System.out.println("Значение key1: " + map.get(k1));
    }
}