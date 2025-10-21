//Task:Реализуйте перевод из римских чисел в арабские

package lab1;
public class RomanToArabic {
    public static void main(String[] args) {
        String romanNumber = "MCMXCIV"; // Пример: 1994
        int arabicNumber = convertRomanToArabic(romanNumber);
        System.out.println("Арабское число: " + arabicNumber);
    }
    private static int convertRomanToArabic(String roman) {
        int result = 0;
        int length = roman.length();
        for (int i = 0; i < length; i++) {
            int currentValue = romanCharToInt(roman.charAt(i));
            int nextValue = 0;
            if (i + 1 < length) {
                nextValue = romanCharToInt(roman.charAt(i + 1));
            }
            if (nextValue > currentValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
        }
        return result;
    }
    private static int romanCharToInt(char romanChar) {
        switch (romanChar) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0; // если неверный символ
        }
    }
}