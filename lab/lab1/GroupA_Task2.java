import java.util.Scanner;

public class GroupA_Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число в римской системе счисления: ");
        String romeNum = sc.nextLine();
        System.out.println(romeNum);
        int arabNum = romeToArab(romeNum);
        System.out.print("В десятичной системе счисления: ");
        System.out.println(arabNum);
    }

    public static int romeToArab(String romeNum) {
        int arabNum = 0;
        String[] subFour = new String[]{"IV", "XL", "CD"};
        String[] subNine = new String[]{"IX", "XC", "CM"};
        String[] fullOne = new String[]{"I", "X", "C", "M"};
        String[] fullFive = new String[]{"V", "L", "D"};
        for (int i = 0; i < romeNum.length(); i++) {
            boolean deepBreak = false;
            for (int j = 0; j < subFour.length; j++) {
                if ((i+1)== romeNum.length()) break;
                if (romeNum.substring(i, i+2).equals(subFour[j])) {
                    arabNum += 4 * ((int) Math.pow(10, j));
                    deepBreak = true;
                    i++;
                    break;
                }
            }
            for (int j = 0; (!deepBreak) && (j < subNine.length); j++) {
                if ((i + 1) == romeNum.length()) break;
                if (romeNum.substring(i, i+2).equals(subNine[j])) {
                    arabNum += 9*((int) Math.pow(10, j));
                    deepBreak = true;
                    i++;
                    break;
                }
            }
            for (int j = 0; (!deepBreak) && (j < fullOne.length); j++) {
                if (romeNum.substring(i, i+1).equals(fullOne[j])) {
                    arabNum += 1*((int)Math.pow(10, j));
                    deepBreak = true;
                    break;
                }
            }
            for (int j = 0; (!deepBreak) && (j < fullFive.length); j++) {
                if (romeNum.substring(i, i+1).equals(fullFive[j])) {
                    arabNum += 5*((int)Math.pow(10, j));
                    deepBreak = true;
                    break;
                }
            }
            if (!deepBreak) {
                System.out.print("Введен недопустимый символ: ");
                System.out.println(romeNum.substring(i, i+1));
                System.out.println("Программа завершена аварийно");
                return -1;
            }

        }
        return arabNum;
    }
}
