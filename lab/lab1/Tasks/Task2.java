/*Изограмма – это слово, в котором нет повторяющихся букв,
 последовательных или непоследовательных. Реализуйте функцию, которая
 определяет, является ли строка, изограммой. Пустая строка является изограммой.
 */


package Tasks;

import java.util.Scanner;

public class Task2 {

    public static void checkIsogram(String word) {
        boolean isIsogram = true;

        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j < word.length(); j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    isIsogram = false;
                    break;
                }
            }
            if (!isIsogram) {
                break;
            }
        }

        if (isIsogram) {
            System.out.println("изограмма");
        } else {
            System.out.println("не изограмма");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("проверь, является ли твое слово изограммой: ");
        String word = scan.nextLine();
        checkIsogram(word);
    }
}

