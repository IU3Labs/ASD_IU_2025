import java.util.Scanner;

public class Task1GroupB {
    public static void main(String[] args) {
        int[] array = inputArray();
        System.out.println("Число,равное количеству вхождений в массив: ");
        System.out.println(findFrequencyNumber(array));
    }

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int length = scanner.nextInt();
        int[] array = new int[length];
        System.out.println("Введите " + length + " элементов массива:");
        for (int i = 0; i < length; i++) {
            System.out.print("Элемент [" + i + "]: ");
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int findFrequencyNumber(int[] array) {
        if (array.length == 0) {
            return -1;
        }
        int maxNumber = -1;
        for (int i = 0; i < array.length; i++) {
            int currentNumber = array[i];
            int frequency = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == currentNumber) {
                    frequency++;
                }
            }
            if (currentNumber == frequency) {
                if (currentNumber > maxNumber) {
                    maxNumber = currentNumber;
                }
            }
        }
        return maxNumber;
    }
}
    /*
    1 Дан целочисленный массив. Верните число, частота встречи которого в
    массиве равна его значению. Если таких чисел нет, вернуть «-1».
    Если таких чисел несколько, вернуть наибольшее.
     */
