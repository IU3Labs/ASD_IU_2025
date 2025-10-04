package lab1;//Реализуйте метод, входными данными которого являются два числа N и M,
//где N – число в десятичной системе исчисления, а M – число в диапазоне от
//2 до 9, основание системы исчисления, в которое надо перевести исходное число.
// Метод должен возвращать строку с преобразованным значением.

import java.util.Scanner;

public class Num3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Число в десятичной системе: ");
        int n = s.nextInt();
        System.out.print("Введи основание системы 2-9: ");
        int m = s.nextInt();
        String result = Num(n, m);
        System.out.println("Число " + n + " в системе с основанием " + m + " = " + result);
        s.close();
    }

    public static String Num(int n, int m) {
        if (n < 2 || m > 9) {
            return "Ошибка";
        }
        String res = "";
        while (n > 0) {
            int rem = n % m;
            res = rem + res;
            n = n / m;
        }
        return res;
    }

}
