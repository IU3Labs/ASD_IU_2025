// Task:Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

package lab1;

public class FrequencyNumber {

        public static void main(String[] args) {
            int[] numbers = {3, 1, 2, 2, 3, 3}; // пример массива
            int result = findNumberWithSameFrequency(numbers);
            System.out.println("Результат: " + result);
        }
        private static int findNumberWithSameFrequency(int[] array) {
            int result = -1;

            for (int i = 0; i < array.length; i++) {
                int currentNumber = array[i];
                int count = 0;

                for (int j = 0; j < array.length; j++) {
                    if (array[j] == currentNumber) {
                        count++;
                    }
                }
                if (count == currentNumber) {
                    if (currentNumber > result) {
                        result = currentNumber;
                    }
                }
            }

            return result;
        }
    }

