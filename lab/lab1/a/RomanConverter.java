package lab1.a;

/* 2. Реализуйте перевод из римских чисел в арабские. */

import java.util.Scanner;

public class RomanConverter {
        static Scanner input = new Scanner(System.in);

        public static void main(String[] args) {
            String romNum = input.next();
            System.out.println(convert(romNum));
        }

        static int convert(String romNum) {
            int previousValue = 0;
            int counter = 0;
            for (int i = 0; i < romNum.length(); i++) {
                int currentValue = convertSymbol(romNum.charAt(i));
                if (currentValue >= previousValue) {
                    counter += currentValue - previousValue * 2;
                } else {
                    counter += currentValue;
                }
                previousValue = currentValue;
            }
            return counter;
        }

        static int convertSymbol(char symbol) {
            return switch (symbol) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };
        }
    }
