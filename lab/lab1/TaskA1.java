/*
Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходное
число. Метод должен возвращать строку с преобразованным значением.
 */

package lab1;

public class TaskA1 {
    public static void main(String[] args) {
        int number = MainTask.readInt("Введите число: ", false);
        int numberSystem = MainTask.readInt("Введите систему счисления (от 2 до 9): ", true);
        System.out.println(calculator(number, numberSystem));
    }
    private static String calculator(int number, int numberSystem) {
        String result = "";
        while (number>0) {
            result = number%numberSystem + result;
            number = number/numberSystem;
        }
        return result;
    }
}
