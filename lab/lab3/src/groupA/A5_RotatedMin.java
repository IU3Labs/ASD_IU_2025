
import java.io.IOException;

public class A5_RotatedMin {
    public static void run(FastScanner fs) throws IOException {
        System.out.print("Enter N: ");
        int n = fs.nextInt();
        int[] a = new int[n];
        System.out.print("Enter " + n + " integers (rotated sorted, no duplicates): ");
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        System.out.println("Min: " + findMin(a));
    }

    public static int findMin(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] > a[r]) l = m + 1;
            else r = m;
        }
        return a[l];
    }
}
