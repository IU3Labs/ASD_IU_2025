public class Main {
    public static void main(String[] args) {
        int x = 20, y = 30;
        System.out.println(sum(x, y));
        double arg1 = 20.4, arg2 = 4.5;
        System.out.println(getMaxValueOfTwo(arg1, arg2));
        prettyPrint("Hello", 10);
    }
    public static int sum(int a, int b) {
        return a + b;
    }
    public static double getMaxValueOfTwo(double first, double second) {
        if (first > second) {
            return first;
        } else {
            return second;
        }
    }
    public static void prettyPrint(String s, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("****" + s + "****");
        }
    }
    int[] array = new int[10];

}
