package lab1;

import java.util.Scanner;

public class Zero // класс 1-ой лабы задания
{
    private static Scanner in = new Scanner(System.in);

    // main
    public static void main(String[] args) {
        startZero(); // во второй реализации управления знаками "<", ">" нет для экономии времени
        in.close();
    }

    // --------------- запуск 0-ой задачи ---------------
    public static void startZero() {
        // ЗАДАНИЕ:
        // Реализовать алгоритм бинарного поиска двумя способами.

        //ввод
        System.out.println(" --------------- Base (0) task ---------------");
        System.out.print("Finding value - ");
        int finding = inNumber();
        System.out.print("Min value -  ");
        int minn = inNumber();
        System.out.print("Max value - ");
        int maxx = inNumber();
        in.nextLine();

        // вызов вариантов решений
        bazaVar1(finding, minn, maxx);
        System.out.println("\n");
        bazaVar2(finding, minn, maxx);
        System.out.println("\n");
    }

    // алгоритм 1 для 0 задачи (прямой)
    public static void bazaVar1(int val, int mi, int ma) {

        System.out.println(" ------------ Starting bazaVar1 ------------");

        // объявление переменных
        int finding = val;
        int minn = mi;
        int maxx = ma;
        int coursore = (maxx - minn) / 2;

        // сам алгоритм 1 (деревянный)
        while (coursore != finding) {
            System.out.println("< or > than " + coursore + "?");
            String comand = in.nextLine();
            if (comand.equals(">")) {
                minn = coursore + 1;
            } else if (comand.equals("<")) {
                maxx = coursore - 1;
            }
            coursore = (maxx - minn) / 2 + minn;
        }
        System.out.println(coursore);
        System.out.println("Found.");
        System.out.println(" ------------ Ending bazaVar1 ------------");

    }

    // метод вызова рекурсии и красивого вывода
    public static void bazaVar2(int val, int mi, int ma) {
        System.out.println(" ------------ Starting bazaVar2 ------------");
        // объявление переменных
        int maxx = ma;
        int minn = mi;
        int finding = val;

        // вызов рекурсии
        rec((maxx - minn) / 2, minn, maxx, finding);
        System.out.println("Found.");
        System.out.println(" ------------ Ending bazaVar2 ------------");
    }

    // алгоритм 2 для 0 задачи (рекурсия)
    public static int rec(int coursore, int minn, int maxx, int real) {
        System.out.println("Min = " + minn + ", Current = " + coursore + ", Finding = " + real + ", Max = " + maxx);
        if (coursore == real) { // выход в случае успеха
            return real;
        }
        if (coursore > real) {
            maxx = coursore - 1;
        }
        if (coursore < real) {
            minn = coursore + 1;
        }
        if (minn > maxx) { // выход в случае ошибки
            System.out.println("Out of range");
            return -1;
        }
        coursore = (maxx - minn) / 2 + minn; // обновляем среднее значение
        return rec(coursore, minn, maxx, real);
    }

    // метод перевода дв. в дес.
    public static int boolToInt(int[] psevdoBoolList, int len) {
        // len нужно чтобы обрезать лишние битики из массива (даже если там нули, они влияют)
        int result = 0;
        for (byte i = 0; i < len; i++) {
            result += psevdoBoolList[i] * (Math.pow(2, len - i - 1)); // формулка
        }
        return result;
    }

    // методы вывода массива
    public static void print(boolean[] massive) {
        for (int i = 0; i < massive.length; i++) {
            System.out.print(massive[i] + " ");
        }
    }

    public static void print(byte[] massive) {
        for (int i = 0; i < massive.length; i++) {
            System.out.print(massive[i] + " ");
        }
    }

    // метод ввода бин. массива
    public static byte[] inData() {
        System.out.println("Massive's lenth: ");
        int lenn = in.nextInt();
        System.out.println("Enter elements: ");
        byte[] inputt = new byte[lenn]; // данный массив 0 и 1
        for (int i = 0; i < lenn; i++) {
            inputt[i] = (byte) in.nextInt();
        }
        return inputt;
    }

    // метод ввода числа n
    public static int inNumber() {
        System.out.println("Enter n: ");
        int n = in.nextInt();
        return n;
    }

    // методы проверки элемента на наличие в списке
    public static boolean isElInList(byte[] massive, byte el) {
        for (int j = 0; j < massive.length; j++) {
            if (massive[j] == el) {
                return true;
            }
        }
        return false;
    }

    public static int isElInListWithId(byte[] massive, int el) {
        for (int j = 0; j < massive.length; j++) {
            if (massive[j] == el) {
                return j;
            }
        }
        return -1;
    }

}

