public class TaskA1 {
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
    public static void main(String[] args) {
        System.out.println(converterFunc(10, 2));
        System.out.println(converterFunc(42, 8));
    }
}
