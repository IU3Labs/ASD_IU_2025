// Текст задачи: Дан целочисленный массив. Верните число, частота встречи которого в
// массиве равна его значению. Если таких чисел нет, вернуть «-1».
// Если таких чисел несколько, вернуть наибольшее. 

public class FrequencyValueMatcher {

    public static int findLuckyNumber(int[] arr) {
        int luckyNumber = -1;

        // внешний цикл перебираем каждый уникальный элемент
        for (int i = 0; i < arr.length; i++) {

            int num = arr[i];


            if (num <= 0) {
                continue;
            }

            // проверяем обрабатывали мы это раньше
            boolean seenBefore = false;
            for (int k = 0; k < i; k++) {
                if (arr[k] == num) {
                    seenBefore = true;
                    break;
                }
            }
            if (seenBefore) {
                continue;
            }

            // считаем частоту
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == num) {
                    count++;
                }
            }

            // проверяем условие
            if (count == num) {
                if (num > luckyNumber) {
                    luckyNumber = num;
                }
            }
        }

        return luckyNumber;
    }

    public static void main(String[] args) {
        System.out.println("--- Поиск 'счастливого' числа ---");
        int[] arr = LabHelper.readIntArray();

        int result = findLuckyNumber(arr);

        System.out.println("Введенный массив:");
        LabHelper.printArray(arr);

        if (result != -1) {
            System.out.println("Число, у которого частота встреч равна его значению: " + result);
        } else {
            System.out.println("Таких чисел в массиве нет. Результат: -1");
        }
    }
}