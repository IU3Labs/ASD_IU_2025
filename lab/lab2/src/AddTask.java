//демо капасити для аррейлиста

import java.util.ArrayList;
import java.util.List;

public class AddTask{

    public static void main() {
        int count = 1_000_000;

        // Тестирование с разной начальной емкостью
        testArrayListPerformance(count, 10);// Маленькая емкость
        testArrayListPerformance(count, count); // Идеальная емкость
    }

    //узнаем время, за которое выполняется заполнение массива с разной изначально заданной физической длиной
    private static void testArrayListPerformance(int count, int firstCapacity) {
        long start = System.nanoTime();

        List<Integer> list = new ArrayList<>(firstCapacity);
        for (int i = 0; i < count; i++) {
            list.add(i);
        }

        long end = System.nanoTime();
        long time = end - start;

        System.out.printf("Начальная емкость: " + firstCapacity + ". Время: " + time / 10000000 + " ms" +  ". Количество расширений: "+ countCapacityChanges(firstCapacity, count)+".\n");
    }

    //узнаем, сколько было изменений физической длины во время выполнения добавления элементов, чтобы узнать зависимость
    private static int countCapacityChanges(int nowCapacity, int count) {
        if (nowCapacity >= count) {
            return 0;
        }

        int changesCount = 0;
        int currentCapacity = nowCapacity;

        //известно, что capacity увеличивается в полтора раза, когда ArrayList полностью заполняется
        while (currentCapacity < count) {
            currentCapacity = currentCapacity + (currentCapacity >> 1);
            changesCount++;
        }

        return changesCount;
    }
}
//Начальная емкость: 10. Время: 4 ms. Количество расширений: 29.
//Начальная емкость: 1000000. Время: 2 ms. Количество расширений: 0.
//видно, что поле capacity влияет на производительность аррейлиста: его увеличение приводит к перезаписи массива,
// и увеличивается количество элементарных операций -> растет время выполнения.