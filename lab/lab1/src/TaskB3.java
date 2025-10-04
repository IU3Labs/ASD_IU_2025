import java.util.Scanner;

/*
Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел.
 */


public class TaskB3 {

    private static final int MIN = Integer.MAX_VALUE; //Максимально возможный integer

    public static void main(String[] args) {
        //Ввод и вывод
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array (the minimal number is 5): ");
        int size = input.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements in the array: ");
        for (int i = 0; i < size; i++) arr[i] = input.nextInt();
        System.out.println("The result is: " + sumMinArray(arr));

    }

    //Поиск двух минимумов
    public static int sumMinArray(int[] array) {
        //Задаем очень большое число для минимумов, чтобы оно точно было больше любого числа в массиве
        int min1 = MIN;
        int min2 = MIN;
        //Проходимся по массиву обновляя второй минимум значением первого, при нахождении нового минимального числа массива
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min1 && array[i] > 0) {
                min2 = min1;
                min1 = array[i];
            }
            //Проверяем число на случай если оно больше первого минимума, но при этом меньше второго
            else if (((array[i] < min2 && array[i] > 0) || min2 == MIN) && array[i] != min1) min2 = array[i];
        }
        return min1 + min2;
    }
}
