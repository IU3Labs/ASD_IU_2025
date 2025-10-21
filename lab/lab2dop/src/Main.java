/* Вырожденная Map (дополнительное задание)*/
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int size = 10000;

        //Создание Map
        Map<String, String> normalMap = new HashMap<>();
        Map<BadKey, String> badMap = new HashMap<>();

        String lastNormalKey = "key-" + (size - 1);
        BadKey lastBadKey = new BadKey("key-" + (size - 1));

        //Добавление 10000 элементов
        for (int i = 0; i < size; i++) {
            normalMap.put("key-" + i, "value-" + i);
            badMap.put(new BadKey("key-" + i), "value-" + i);
        }

        System.out.println("\nПоиск последнего элемента:");

        //Нормальная Map
        long start = System.nanoTime();
        String normalResult = normalMap.get(lastNormalKey);
        long normalTime = System.nanoTime() - start;

        //Вырожденная Map (хэш ключа всегда одинаковый)
        start = System.nanoTime();
        String degenerateResult = badMap.get(lastBadKey);
        long degenerateTime = System.nanoTime() - start;

        //Результаты в нс
        System.out.printf("Нормальная Map: %d нс%n", normalTime);
        System.out.printf("Вырожденная Map: %d нс%n", degenerateTime);
    }

    static class BadKey {
        private final String value;
        public BadKey(String value) {
            this.value = value;
        }
        @Override
        public int hashCode() {
            return 1;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return value.equals(((BadKey) obj).value);
        }
    }
}
/*Поиск последнего элемента:
Нормальная Map: 25700 нс
Вырожденная Map: 149600 нс */