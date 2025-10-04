package tasks;
import utils.InputManager;

import java.util.Scanner;

public class Isogram {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите слово: ");
        String word = InputManager.readWord(scan);
        checkisogram(isIsogram(word));
        scan.close();
    }

    private static boolean isIsogram(String word){
        String upperStr = word.toUpperCase();
        boolean[] seenLetters = new boolean[26];

        for (int i = 0; i < upperStr.length(); i++){
            char currentChar = upperStr.charAt(i);
            int index = currentChar - 'A';
            if (seenLetters[index]) {
                return false;
            }
            seenLetters[index] = true;
        }
        return true;
    }
    private static void checkisogram(boolean flag){
        if (flag){
            System.out.println("Это изгорамма.");
        }
        else{System.out.println("Это не изгорамма.");}
    }
}