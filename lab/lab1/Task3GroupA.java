import java.util.Scanner;
public class Task3GroupA {
    public static void main(String[] args) {
        String input = inputString();
        if (isIsogramCheck(input)) {
            System.out.println("Строка является изограммой");
        } else {
            System.out.println("Строка не является изограммой");
        };
    }
    public static String inputString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static boolean isIsogramCheck(String inputString) {
        if (inputString.isEmpty()) {
            return true;
        }
        String lowerString = inputString.toLowerCase();
        for (int i = 0; i < lowerString.length(); i++) {
            for (int j = i + 1; j < lowerString.length(); j++) {
                if (lowerString.charAt(i) == lowerString.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
/* 3 Изограмма – это слово, в котором нет повторяющихся букв,
последовательных или непоследовательных. Реализуйте функцию, которая
определяет, является ли строка, изограммой. Пустая строка является
изограммой.
 */
