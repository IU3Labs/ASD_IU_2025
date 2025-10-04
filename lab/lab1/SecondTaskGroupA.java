package lab1;

//Реализуйте перевод из римских чисел в арабские.

public class SecondTaskGroupA {
    public static void main() {
        String[] testNum = {"I", "IV", "IX", "XC", "CXXIV", "MMXXIII", "MCMXC"};

        for (String roman : testNum) {
            int arabic = convert(roman);
            System.out.println(roman + " = " + arabic);
        }
    }

    public static int convert(String roman) {
        if (roman.isEmpty() || roman == null) {
            return 0;
        }

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);

            int currentValue = getValue(currentChar);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }
        return result;
    }

    private static int getValue(char romanChar) {
        if (romanChar == 'I') return 1;
        else if (romanChar == 'V') return 5;
        else if (romanChar == 'X') return 10;
        else if (romanChar == 'L') return 50;
        else if (romanChar == 'C') return 100;
        else if (romanChar == 'D') return 500;
        else if (romanChar == 'M') return 1000;
        else return 0;
    }
}

