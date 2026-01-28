import java.util.Scanner;

public class GroupA_Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите слово: ");
        String word = sc.nextLine();
        boolean isIsogram = checkIsogram(word);
        if (isIsogram) {
            System.out.println("Это слово является изограммой.");
        } else {
            System.out.println("Это слово не является изограммой");
        }

    }

    public static boolean checkIsogram(String word) {
        String[] wasMet = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (wasMet[j].equals(word.substring(i, i+1))) {
                    return false;
                }
            }
            wasMet[i] = word.substring(i, i+1);
        }
        return true;
    }
}
