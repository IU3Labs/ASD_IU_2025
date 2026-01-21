import java.util.Scanner;

public class Util {

    private static final Scanner scanner = new Scanner(System.in);

    public static int Input() {
        System.out.print("Enter array size: ");
        return scanner.nextInt();
    }



    public static int[] inputArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter number (" + (i + 1) + "/" + n + "): ");
            a[i] = Input();
        }
        return a;
    }
    public static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}