//Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].


package Tasks;

//улучшена атомарность
public class Task4 {
    public static int arrayToNumber(int[] arr) {
        int num = 0;
        for (int digit : arr) {
            num = num * 10 + digit;
        }
        return num;
    }
    public static int multiply(int a, int b) {
        return a * b;
    }
    public static int[] numberToArray(int num) {
        String s = String.valueOf(num);
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(i) - '0';
        }
        return result;
    }


    public static void main(String[] args) {

        int[] array1 = Main.arrayWork();
        Main.fillArray(array1);
        int[] array2 = Main.arrayWork();
        Main.fillArray(array2);
        int a=arrayToNumber(array1);
        int b =arrayToNumber(array2);
        int rez = multiply(a,b);
        int[] rezarray=numberToArray(rez);
        Main.arrayOutput(rezarray);

    }
}
