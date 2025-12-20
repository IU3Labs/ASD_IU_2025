
import java.io.IOException;
import java.util.Arrays;

public class A4_MaskSort {
    public static void run(FastScanner fs) throws IOException {
        System.out.print("Enter N: ");
        int n = fs.nextInt();
        System.out.print("Enter mask: ");
        int mask = fs.nextInt();

        int[] a = new int[n];
        System.out.print("Enter " + n + " non-negative integers: ");
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        sortByMask(a, mask);
        System.out.println("Sorted:");
        Main.printArray(a);
    }

    public static void sortByMask(int[] a, int mask) {
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) b[i] = a[i];

        Arrays.sort(b, (x, y) -> {
            int kx = x & mask;
            int ky = y & mask;
            if (kx != ky) return Integer.compare(kx, ky);
            return Integer.compare(x, y);
        });

        for (int i = 0; i < a.length; i++) a[i] = b[i];
    }
}
