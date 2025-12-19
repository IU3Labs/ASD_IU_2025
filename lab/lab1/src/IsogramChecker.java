import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class IsogramChecker {

    private static final String CONTINUE_COMMAND = "да";

    public static boolean isIsogram(String word) {
        if (word == null) {
            return false;
        }

        if (word.isEmpty()) {
            return true;
        }

        String lowercaseWord = word.toLowerCase();
        Set<Character> seenLetters = new HashSet<>();

        for (char character : lowercaseWord.toCharArray()) {
            if (Character.isLetter(character)) {
                if (seenLetters.contains(character)) {
                    return false;
                }
                seenLetters.add(character);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        System.out.println("=== Проверка изограмм ===");

        while (shouldContinue) {
            System.out.print("Введите слово для проверки: ");
            String word = scanner.nextLine();

            boolean isIsogram = isIsogram(word);

            if (isIsogram) {
                System.out.println("✓ '" + word + "' является изограммой");
            } else {
                System.out.println("✗ '" + word + "' не является изограммой");
            }

            System.out.print("Проверить еще одно слово? (да/нет): ");
            String userAnswer = scanner.nextLine();

            if (!userAnswer.equalsIgnoreCase(CONTINUE_COMMAND)) {
                shouldContinue = false;
                System.out.println("Программа завершена!");
            }
        }

        scanner.close();
    }
}
