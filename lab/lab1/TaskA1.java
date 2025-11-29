import java.util.Scanner;

public class TaskA1 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        System.out.println(converterFunc(N, M));
    }

    public static String converterFunc(int N, int M)
    {
        if (M < 2 || M > 9)
        {
            return "Выберите систему счисления от 2 до 9";
        }

        int number = Math.abs(N);
        StringBuilder result = new StringBuilder();
        while (number > 0)
        {
            result.insert(0, number % M);
            number = number / M;
        }

        if (N < 0)
        {
            result.insert(0, "-");
        }
        return result.toString();
    }

}
