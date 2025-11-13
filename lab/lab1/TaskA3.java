import java.util.Scanner;

public class TaskA3 {
    public static void main(String[] args) {
        System.out.println("Input word: ");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        System.out.println(isIsogramAlternative(word));
    }

    public static boolean isIsogramAlternative(String str) {
        if (str.isEmpty()) {
            return true;
        }
        
        String lowerCaseStr = str.toLowerCase();
        
        for (int i = 0; i < lowerCaseStr.length(); i++) {
            char currentChar = lowerCaseStr.charAt(i);
            for (int j = i + 1; j < lowerCaseStr.length(); j++) {
                if (currentChar == lowerCaseStr.charAt(j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}