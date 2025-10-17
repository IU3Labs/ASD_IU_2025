/*Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходное
число. Метод должен возвращать строку с преобразованным значением.
 */
package Tasks;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите число которое хотите перевести: ");
        int N = scan.nextInt();
        System.out.print("Введите новую систему счисления: ");
        int M = scan.nextInt();
        String newbase = Integer.toString(N, M);
        System.out.println("Ваше число в новой системе счисления: " + newbase);

    }
}
