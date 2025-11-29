package lab2;

import java.util.HashMap;

public class DegenerateMapDemo {
    public static void main(String[] args) {
        // Вырожденная HashMap, где хэш ключа всегда одинаковый
        HashMap<Structure, Integer> badMap = new HashMap<>();
        // Нормальная HashMap с хорошей хэш-функцией для сравнения
        HashMap<Integer, Integer> goodMap = new HashMap<>();

        // Добавляем в обе HashMap по 10000 элементов
        for (int i = 0; i < 10_000; i++) {
            badMap.put(new Structure(i), i * 10);
            goodMap.put(i, i * 10);
        }

        // Добавляем последний элемент, который будем искать
        Structure last = new Structure(10_000);
        badMap.put(last, 100_000);
        goodMap.put(10_000, 100_000);

        // Сравниваем время выполнения операции по получению элемента
        System.out.println(runMeasuring(() -> badMap.get(last)));
        System.out.println(runMeasuring(() -> goodMap.get(10_000)));

        // Полученные результаты:
        // Вырожденная - 87000 нс
        // Обычная - 4400 нс
    }

    private static long runMeasuring(Runnable function) {
        long startTime = System.nanoTime();
        function.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Вспомогательный класс для использования в качестве ключа, чей хэш всегда одинаковый
    static class Structure {
        int id;

        Structure(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return 2025;
        }
    }
}
