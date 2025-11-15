package lab1;

import java.util.Scanner;

import static lab1.FunctionsLab1.*;

public class FirstDopTask {
    private static final Scanner in = new Scanner(System.in);
    public static void main (String[] args) {
        firstDopTask();
        in.close();
    }
    public static void firstDopTask () {

        // ЗАДАНИЕ:
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


        System.out.println(" --------------- First task ---------------");
        // объявление переменных
        byte[] inputt = inByteList();
        int n = inNumber();
        int[] nums = new int[inputt.length]; // Переведенный дес. Массив
        int[] boolEachNum = new int[inputt.length]; // массив для перевода каждого двоич. числа
        boolean[] answer = new boolean[inputt.length];
        for (byte i = 0; i < inputt.length; i++) {
            boolEachNum[i] = inputt[i]; // берем новый элемент списка
            int numEl = boolToInt(boolEachNum, i+1); // переводим в дес.
            System.out.println("NUM RES = " + numEl);
            nums[i] = numEl; // массив дес. чисел nums (на примере -- [1, 3, 6])
            answer[i] = (numEl % n == 0);
        }
        print(answer);
        System.out.println();

    }
}
