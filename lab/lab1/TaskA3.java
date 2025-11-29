// Изограмма – это слово, в котором нет повторяющихся букв, последовательных или непоследовательных. Реализуйте функцию, которая определяет, является ли строка, изограммой. Пустая строка является изограммой.
import java.util.Scanner;

public class TaskA3 {

    public static void  main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (isIsogram(str)) {
            System.out.println("Слово является изограммой");
        }
        else {
            System.out.println("Слово не является изограммой");
        }
    }

    // функция проверки
    public static boolean isIsogram(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        String lowerCaseStr = str.toLowerCase();
        for (int i = 0; i < lowerCaseStr.length(); i++) {
            char currentChar = lowerCaseStr.charAt(i);

            // смотрим повторяющийся символ
            for (int j = i + 1; j < lowerCaseStr.length(); j++) {
                if (currentChar == lowerCaseStr.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

}

