package utils;
import java.util.Scanner;

public class InputManager {
    private static Scanner scanner;


    public static int inputTarget(Scanner scan) {
        return scan.nextInt();
    }

    public static String readWord(Scanner scan) {
        return scan.nextLine();
    }


    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}