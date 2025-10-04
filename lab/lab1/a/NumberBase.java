package lab1.a;

/*Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходноечисло.
 Метод должен возвращать строку с преобразованным значением.
 */


import java.util.Scanner;

public class NumberBase {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        int N  = input.nextInt();
        int M  = input.nextInt();
        System.out.println(convert(N,M));
    }
    static  String convert(int number, int base ){
        StringBuilder result = new StringBuilder();
        while ( number!=0){
            result.insert(0,number%base);
            number/=base;
        }
        return result.toString();

    }

}
