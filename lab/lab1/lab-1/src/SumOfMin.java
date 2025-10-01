import java.util.Scanner;

public class SumOfMin {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите количество элементов массива: ");
        int length = scanner.nextInt();

        if (length < 5) {
            System.out.println("Неверное количество элементов. ");
        } else {
            int[] array = Arrays.arraySort(Arrays.newArray(length));
            System.out.println("Сумма двух минимальных положительных числа в массиве: " + sumOfMinNumbers(array));
        }
    }

    private static int sumOfMinNumbers(int[] array) {
       int sum = 0;
       int counter = 0;
       for (int i : array) {
           if (counter == 2) {
               return sum;
           } else if (counter < 2 && i > 0) {
               sum += i;
               counter++;
           } else {
               continue;
           }
       }
       return sum;
    }
}
