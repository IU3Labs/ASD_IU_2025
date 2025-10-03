package lab1.a;

/* 3. Изограмма – это слово, в котором нет повторяющихся букв,
последовательных или непоследовательных. Реализуйте функцию, которая
определяет, является ли строка, изограммой. Пустая строка является
изограммой. */

import java.util.Scanner;

public class IsogramChecker {static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String word = input.next();
        if (checkForIsogram(word)) {
            System.out.println("Изограмма");
        } else {
            System.out.println("Не изограмма");
        }
    }

    static void quickSort(StringBuilder word, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        int pivot = word.charAt((left + right) / 2);
        while (i <= j) {
            while (word.charAt(i) < pivot) i++;
            while (word.charAt(j) > pivot) j--;
            if (i <= j) {
                char temp = word.charAt(i);
                word.setCharAt(i, word.charAt(j));
                word.setCharAt(j, temp);
                i++;
                j--;
            }
        }
        quickSort(word, left, j);
        quickSort(word, i, right);
    }

    static boolean checkForIsogram(String word) {
        StringBuilder wordBuilder = new StringBuilder(word.toLowerCase());
        quickSort(wordBuilder, 0, word.length() - 1);
        for (int i = 0; i < word.length() - 1; i++) {
            if (wordBuilder.charAt(i) == wordBuilder.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
