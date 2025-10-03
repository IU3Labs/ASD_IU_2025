import java.util.Scanner;


// Реализуйте метод, входными данными которого являются два числа N и M,
// где N – число в десятичной системе исчисления, а M – число в диапазоне от 2 до 9,
// основание системы исчисления, в которое надо перевести исходное число.
// Метод должен возвращать строку с преобразованным значением.
public class TaskA1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter N: ");
        int n = scanner.nextInt();
        System.out.print("enter M: ");
        int m = scanner.nextInt();
        if (m < 2 || m > 9) {
            System.out.println("invalid M!");
            return;
        }
        String result = convert(n, m);
        System.out.println(result);
    }

    public static String convert(int n, int m) {
        String result = "";

        while (n > 0) {
            int temp = n % m;
            result = temp + result;
            n = n / m;
        }

        return result;
    }
}
