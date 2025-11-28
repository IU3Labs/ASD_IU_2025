/*
Группа А. Алгоритмические задачи
Реализуйте метод, входными данными которого являются два числа N и M, где N - число
в десятичной системе исчисления, а M - число в диапазоне от 2 до 9, основание системы
исчисления, в которое надо перевести исходное число. Метод должен возвращать строку
с преобразованным значением.
 */


import java.util.Scanner;

public class GroupA_Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int decNum, difNum;
        System.out.print("Введите число в десятичной системе счисления: ");
        decNum = sc.nextInt();
        System.out.print("Введите основание системы счисления (2-9): ");
        difNum = sc.nextInt();

        int massiveLength = getMassiveLength(decNum, difNum);
        int[] invertResult = numToInvMas(decNum, difNum, massiveLength);

        displayInvMassive(invertResult);

    }

    public static int getMassiveLength(int decNum, int difNum) {
        int len = 0;
        for (int i = 0; ; i++) {
            if (Math.pow(difNum, i) > decNum) {
                len = i;
                break;
            }
        }
        return len;
    }

    public static int[] numToInvMas(int decNum, int difNum, int massiveLength) {
        int[] invertResult = new int[massiveLength];
        for (int i = 0; i < invertResult.length; i++) {
            invertResult[i] = decNum % difNum;
            decNum = decNum / difNum;
        }
        return invertResult;
    }

    public static void displayInvMassive(int[] massive) {
        for (int i = massive.length - 1; i >= 0; i--) {
            System.out.print(massive[i]);
        }
    }

}
