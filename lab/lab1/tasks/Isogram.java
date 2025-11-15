package tasks;

import utils.InputManager;

import java.util.Scanner;

public class Isogram {
    public static void main(String[] args) {
        Scanner scan = InputManager.getScanner();
        System.out.println("Введите слово: ");
        String word = InputManager.readWord(scan);
        checkIsogram(isIsogram(word));
    }

    private static boolean isIsogram(String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        String upperStr = word.toUpperCase();
        boolean[] seenCharacters = new boolean[65536];
        for (int i = 0; i < upperStr.length(); i++) {
            char currentChar = upperStr.charAt(i);
            int index = currentChar;
            if (seenCharacters[index]) {
                return false;
            }
            seenCharacters[index] = true;
        }
        return true;
    }

    private static void checkIsogram(boolean flag) {
        if (flag) {
            System.out.println("Это изограмма.");
        } else {
            System.out.println("Это не изограмма.");
        }
    }
}