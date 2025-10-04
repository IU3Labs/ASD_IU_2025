package lab1.a;
/*Изограмма – это слово, в котором нет повторяющихся букв,
последовательных или непоследовательных. Реализуйте функцию, которая
определяет, является ли строка, изограммой. Пустая строка является
изограммой.*/

import java.util.Scanner;
public class IsogramChecker {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Введите строку для проверки");
      String input = scanner.nextLine();

      boolean result = isIsogram(input);

      if (result) {
          System.out.println("Строка " + input + " является изограммой");
      }else{
          System.out.println("Строка " + input + " не является изограммой");
      }
      scanner.close();
    }
    public static boolean isIsogram(String input) {
        String lowerInput = input.toLowerCase();
        for (int i = 0; i < lowerInput.length(); i++) {
            for (int j = i + 1; j < lowerInput.length(); j++) {
                if (input.charAt(i) == lowerInput.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }






}
