// Группа А. Номер 3
// Изограмма – это слово, в котором нет повторяющихся букв,
// последовательных или непоследовательных. Реализуйте функцию, которая
// определяет, является ли строка, изограммой. Пустая строка является
// изограммой.
import java.util.Scanner;

public class Isogram {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter word: ");
        String word = scanner.nextLine();

        boolean check = checkIsogram(word);

        if (check) {
            System.out.printf("Word <%s> is an isogram", word);
        } else {
            System.out.printf("Word <%s> is not an isogram", word);
        }

        scanner.close();
    }

    private static boolean checkIsogram(String word) {
        String line = "";
        boolean check = true;

        for (char s : word.toCharArray()) {
            if (line.indexOf(s) != -1) {
                check = false;
                break;
            } else {
                line += s;
            }
        }
        return check;
    }
}
