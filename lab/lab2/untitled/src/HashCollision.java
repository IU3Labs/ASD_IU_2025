import java.util.HashMap;

public class HashCollision {

    // Создаю класс который будет ключем для хэш мапы с намерено одинаковым хэш-кодом
    static class BadHashKey {
        private  String value;

        public BadHashKey(String value) {
            this.value = value;
        }

        // Метод hashCode() возвращает одно и то же значение (1) для всех объектов чтоб создать
        // преднамеренную коллизию в HashMap и все ключи попали в один бакет.
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
        // Создаём хэш мапу, где ключом будет объект BadHashKey
        HashMap<BadHashKey, String> map = new HashMap<>();

        // Создаём несколько разных ключей, у которых разные значения, но одинаковый хэш-код
        BadHashKey key1 = new BadHashKey("key1");
        BadHashKey key2 = new BadHashKey("key2");
        BadHashKey key3 = new BadHashKey("key3");
        BadHashKey key4 = new BadHashKey("key4");
        BadHashKey key5 = new BadHashKey("key5");


        // Добавляем пары ключ–значение в хэш мапу
        map.put(key1, "value1");
        map.put(key2, "value2");
        map.put(key3, "value3");
        map.put(key4, "value4");
        map.put(key5, "value5");

        // Еесмотря на одинаковый hashCode, хэщ мапа различает объекты с помощью equals() (у нас используется equals
        // из Object, который сравнивает по ссылке, поэтому коллизии не приводят к перезаписи)
        System.out.println("Хэш-коды ключей:");
        System.out.println("key1 hash: " + key1.hashCode() + ". map value: "+ map.get(key1));
        System.out.println("key2 hash: " + key2.hashCode() + ". map value: "+ map.get(key2));
        System.out.println("key3 hash: " + key3.hashCode() + ". map value: "+ map.get(key3));
        System.out.println("key4 hash: " + key4.hashCode() + ". map value: "+ map.get(key4));
        System.out.println("key5 hash: " + key5.hashCode() + ". map value: "+ map.get(key5));

        // Размер карты равен 5, значит все ключи хранятся независимо
        // несмотря на одинаковый hashCode (т.е. коллизия успешно разрешается)
        System.out.println("Размер HashMap: " + map.size());

        System.out.println("Индексы бакетов для ключей:");
        System.out.println("key1 bucket index: " + (key1.hashCode() & (16 - 1))); // 16 - начальный капасити HashMap
        System.out.println("key2 bucket index: " + (key2.hashCode() & (16 - 1))); // 16 - начальный капасити HashMap
        System.out.println("key3 bucket index: " + (key3.hashCode() & (16 - 1))); // 16 - начальный капасити HashMap
        System.out.println("key4 bucket index: " + (key4.hashCode() & (16 - 1))); // 16 - начальный капасити HashMap
        System.out.println("key5 bucket index: " + (key5.hashCode() & (16 - 1))); // 16 - начальный капасити HashMap
    }
}

//    --Вывод--
//    Хэш-коды ключей:
//    key1 hash: 1. map value: value1
//    key2 hash: 1. map value: value2
//    key3 hash: 1. map value: value3
//    key4 hash: 1. map value: value4
//    key5 hash: 1. map value: value5
//    Размер HashMap: 5
//    Индексы бакетов для ключей:
//    key1 bucket index: 1
//    key2 bucket index: 1
//    key3 bucket index: 1
//    key4 bucket index: 1
//    key5 bucket index: 1
