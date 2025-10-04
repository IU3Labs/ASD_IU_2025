import java.util.Scanner;

public class First // класс 1-ой лабы задания
{
    private static Scanner in = new Scanner(System.in);
    // main
    public static void main (String[] args) {
        startZero(); // во второй реализации управления знаками "<", ">" нет для экономии времени
        firstDopTask();
        secondDopTask();
        thirdDopTask();

        in.close();
    }
    // --------------- запуск 0-ой задачи ---------------
    public static void startZero () {
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
    public static void bazaVar1 (int val, int mi, int ma) {

        System.out.println(" ------------ Starting bazaVar1 ------------");

        // объявление переменных
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
        }
        System.out.println(coursore);
        System.out.println("Found.");
        System.out.println(" ------------ Ending bazaVar1 ------------");

    }
    // метод вызова рекурсии и красивого вывода
    public static void bazaVar2 (int val, int mi, int ma) {
        System.out.println(" ------------ Starting bazaVar2 ------------");
        // объявление переменных
        int maxx = ma;
        int minn = mi;
        int finding = val;

        // вызов рекурсии
        rec((maxx - minn)/2, minn, maxx, finding);
        System.out.println("Found.");
        System.out.println(" ------------ Ending bazaVar2 ------------");
    }
    // алгоритм 2 для 0 задачи (рекурсия)
    public static int rec (int coursore, int minn, int maxx, int real ) {
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
    public static int boolToInt (int[] psevdoBoolList, int len) {
        // len нужно чтобы обрезать лишние битики из массива (даже если там нули, они влияют)
        int result = 0;
        for (byte i = 0; i < len; i++) {
            result += psevdoBoolList[i] * (Math.pow(2, len - i - 1)); // формулка
        }
        return result;
    }
    // методы вывода массива
    public static void print (boolean[] massive) {
        for (int i=0; i<massive.length; i++) {
            System.out.print(massive[i]+ " ");
        }
    }
    public static void print (byte[] massive) {
        for (int i=0; i<massive.length; i++) {
            System.out.print(massive[i]+ " ");
        }
    }
    // метод ввода бин. массива
    public static byte[] inData () {
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
    public static int inNumber () {
        System.out.println("Enter n: ");
        int n = in.nextInt();
        return n;
    }

    // --------------- 1-ое доп. задание ---------------
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
        byte[] inputt = inData();
        int n = inNumber();
        int[] nums = new int[inputt.length]; // переведенный дес. массив 
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
        System.out.println("");

    }

    // методы проверки элемента на наличие в списке
    public static boolean isElInList (byte[] massive, byte el) {
        for (int j = 0; j < massive.length; j++) {
            if (massive[j] == el) {
                return true;
            }
        }
        return false;
    }
    public static int isElInListWithId (byte[] massive, int el) {
        for (int j = 0; j < massive.length; j++) {
            if (massive[j] == el) {
                return j;
            }
        }
        return -1;
    }
    
    // --------------- 2-ое доп. задание ---------------
    public static void secondDopTask () {

        // ЗАДАНИЕ:
        // Дан целочисленный массив. Верните число, частота встречи которого в
        // массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
        // таких чисел несколько, вернуть наибольшее.

        System.out.println(" --------------- Second task ---------------");
        byte[] inputt = inData();
        byte[] simpleNums = new byte[inputt.length];
        byte[] meetCounter = new byte[inputt.length];
        byte next_empty = 0;
        byte answer2 = 0;
        for (int i = 0; i < inputt.length; i++) {
            if (!(isElInList(simpleNums, inputt[i]))) {
                simpleNums[next_empty] = inputt[i];
                meetCounter[next_empty] = 0;
                for (int j = 0; j < inputt.length; j++) {
                    if (simpleNums[next_empty] == inputt[j]) {
                        meetCounter[next_empty]++;
                    }
                }
                next_empty++;
            } 
        }        
        for (int i = 0; i < simpleNums.length; i++) {
            if ((simpleNums[i] == meetCounter[i]) && (simpleNums[i] > answer2)) {
                answer2 = simpleNums[i];
            }
        }

        print(simpleNums);
        System.out.println("");
        print(meetCounter);
        System.out.println("");
        System.out.println("Answer = " + answer2);

    }

    // --------------- 3-е доп. задание ---------------
    public static int thirdDopTask () {

        // ЗАДАНИЕ:
        // Дан массив целых чисел и целое число. Реализовать метод, который
        // возвращает индексы тех двух чисел массива, которые дают сумму
        // заданного числа. Индексы вернуть в любом порядке. Один элемент в сумме
        // использовать дважды нельзя.
        // Примечание. Задача должна быть решена со сложностью меньше, чем
        // ( !). В комментариях кода привести доказательство, что сложность
        // меньше.

        System.out.println(" --------------- Third task ---------------");
        byte[] massive3 = inData(); // ввод
        int n = inNumber(); // ввод 
        for (int i = 0; i < massive3.length; i++ ) {
            for (int j = 0; (j < massive3.length && j != i); j++ ) { // сложность меньше, тк во 2-м цикле мы пробегаемся по n - 1 элементам
                if (massive3[i] + massive3[j] == n) {
                    System.out.println("Индексы: " + i + ", " + j);
                }
            }
        }
        System.out.println("-1");
    
    return 0;
    }
}



