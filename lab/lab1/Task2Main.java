package lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Группа А. Задание 2.
Реализуйте перевод из римских чисел в арабские.
*/

public class Task2Main {
    public static int romanToArabic(String roman) {
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            int current = romanValues.get(roman.charAt(i));
            if (i + 1 < roman.length()) {
                int next = romanValues.get(roman.charAt(i + 1));
                if (current < next) {
                    result -= current;
                } else {
                    result += current;
                }
            } else {
                result += current;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите римское число (например, XIV): ");
        String roman = scanner.nextLine().toUpperCase();
        int arabic = romanToArabic(roman);
        System.out.println("Арабское число: " + arabic);
        scanner.close();
    }
}
