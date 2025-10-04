// Основное задание. Задание оценивается в 0 баллов, но является обязательным.
//        Реализовать алгоритм бинарного поиска двумя способами.

import java.util.Scanner;

public class First // класс основного задания
{
    private static Scanner in = new Scanner(System.in);
    public static void main (String[] args) {
        // задаем значения для работы (на обе версии)
        // System.out.println("Enter finding value: ");
        // int finding = in.nextInt();
        // in.nextLine();
        int minn = 0;
        int maxx = 100;
        // вызов вариантов решений

        // BazaVar1(finding, minn, maxx);
        // System.out.println("\n");
        // BazaVar2(finding, minn, maxx);
        // System.out.println("\n");

        FirstDopTask();

        in.close();
    }
    // алгоритм 1 (прямой)
    public static void BazaVar1 (int val, int mi, int ma) {
        // int num = in.nextInt(); 
        System.out.println(" ------------ Starting Baza_var_1 ------------");
        // объявление констант
        int finding = val;
        int minn = mi;
        int maxx = ma;
        int coursore = (maxx - minn)/2;
        // сам алгоритм 1 (деревянный)
        while (coursore != finding) {
            System.out.println("< or > than " + coursore + "?");
            String comand = in.nextLine();
            if (comand.equals(">")) {
                minn = coursore + 1;
            }
            else if (comand.equals("<")) {
                maxx = coursore - 1;
            }
            coursore = (maxx - minn) / 2 + minn;
            // System.out.println(minn + " " + " " + maxx); // дебагер своего рода
        }
        System.out.println(coursore);
        System.out.println("FOUND!!");
        System.out.println(" ------------ Ending Baza_var_1 ------------");

    }
    // метод вызова рекурсии и красивого вывода
    public static void BazaVar2 (int val, int mi, int ma) {
        System.out.println(" ------------ Starting Baza_var_2 ------------");
        int maxx = ma;
        int minn = mi;
        int finding = val;
        Rec((maxx - minn)/2, minn, maxx, finding);
        System.out.println("FOUND!!");
        System.out.println(" ------------ Ending Baza_var_2 ------------");
    }
    // алгоритм 2 (рекурсивный)
    public static int Rec (int coursore, int minn, int maxx, int real ) {
        System.out.println("Min = " + minn + ", Current = " + coursore + ", Finding = " + real + ", Max = " + maxx);
        if (coursore == real) { 
            return real;
        }
        if (coursore > real) {
            maxx = coursore - 1;
        }
        if (coursore < real) {
            minn = coursore + 1;
        }
        if (minn > maxx) {
            System.out.println("Out of range");
            return -1;
        }
        coursore = (maxx - minn) / 2 + minn;
        return Rec(coursore, minn, maxx, real);
    }

    // метод перевода дв. в дес.
    public static int BoolToInt (int[] psevdo_bool_list, int len) {
        // len нужно чтобы обрезать лишние битики из массива (даже если там нули, они влияют)
        int result = 0;
        for (byte i = 0; i < len; i++) {
            result += psevdo_bool_list[i] * (Math.pow(2, len - i - 1)); // формулка
            // System.out.println(psevdo_bool_list[i] + " " + result + " " + len);
        }
        return result;
    }
    // метод вывода массива
    public static void Print (boolean[] massive) {
        for (int i=0; i<massive.length; i++) {
            System.out.print(massive[i]+ " ");
        }
    }
    // метод ввода бин. массива
    public static byte[] InData () {
        System.out.println("Massive's lenth: ");
        int lenn = in.nextInt();
        System.out.println("Enter elements: ");
        byte[] inputt = new byte[lenn]; // данный массив 0 и 1
        for (int i = 0; i<lenn; i++) {
            inputt[i] = (byte) in.nextInt();
        }
        return inputt;
    }
    // метод ввода числа n
        public static int InNumber () {
        System.out.println("Enter n: ");
        int n = in.nextInt();
        return n;
    }

    // 1-ое доп. задание
    public static void FirstDopTask () {

        // Дан массив целых чисел, представляющий двоичное число.
        // Пример, дан массив bi_arr = [1, 1, 0]. Этот массив в десятичной системе
        // выглядит так: arr = [1, 3, 6]. То есть:
        // • arr[0] = bi_arr[0] = 1! = 1"#,
        // • arr[1] = bi_arr[0] bi_arr[1] =11! = 3"#,
        // • arr[2] = bi_arr[0] bi_arr[1] bi_arr[2] =110! = 6"#
        //         Так же дано целое положительное число – n. Вернуть массив Boolean, где
        // true – число делится на N, false – нет.
        // Пусть n = 6, тогда для предыдущего примера результат должен выглядеть
        // так: [false, false, true].
        // Примечание. Делитель тоже необходимо ввести с клавиатуры.

        byte[] inputt = InData();
        int n = InNumber();
        int[] nums = new int[inputt.length]; // переведенный дес. массив 
        int[] bool_each_num = new int[inputt.length]; // массив для перевода каждого двоич. числа
        boolean[] answer = new boolean[inputt.length];
        for (byte i = 0; i < inputt.length; i++) {
            bool_each_num[i] = inputt[i]; // берем новый элемент списка
            // System.out.println(bool_el);
            int num_el = BoolToInt(bool_each_num, i+1); // переводим в дес.
            System.out.println("NUM RES = " + num_el);
            nums[i] = num_el; // массив дес. чисел nums (на примере -- [1, 3, 6])  
            answer[i] = (num_el % n == 0);
        }
        Print(answer);
    }


}
