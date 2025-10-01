/*  Группа А
    2 Реализуйте перевод из римских чисел в арабские.
*/


public class ConversionToRoman {

    public static void main(String[] arg) {

        String romanValue = InpOutMethods.getString("Введите римсоке число ");

        int targetValue = conversion(romanValue);

        System.out.println(targetValue);
    }

    private static int conversion(String romanValue) {
        int targetValue = 0, len = romanValue.length();
        for (int i = 0; i < len; i++) {
            int current = translation(romanValue.charAt(i));
            if (i < len - 1 && current < translation(romanValue.charAt(i + 1))) {
                targetValue -= current;
            } else {
                targetValue += current;
            }
        }
        return targetValue;
    }

    private static int translation(char value) {
        switch (value) {
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
                return 0;
        }
    }
}