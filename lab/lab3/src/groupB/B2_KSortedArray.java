
import java.io.IOException;
import java.util.PriorityQueue;

public class B2_KSortedArray {
    public static void run(FastScanner fs) throws IOException {
        System.out.print("Enter N: ");
        int n = fs.nextInt();
        System.out.print("Enter K: ");
        int k = fs.nextInt();

        int[] a = new int[n];
        System.out.print("Enter " + n + " integers: ");
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        sortKSorted(a, k);
        System.out.println("Sorted:");
        Main.printArray(a);
    }

    public static void sortKSorted(int[] a, int k) {
        int n = a.length;
        if (n == 0) return;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int m = Math.min(n, k + 1);
        for (int i = 0; i < m; i++) pq.add(a[i]);

        int idx = 0;
        for (int i = m; i < n; i++) {
            a[idx++] = pq.poll();
            pq.add(a[i]);
        }
        while (!pq.isEmpty()) {
            a[idx++] = pq.poll();
        }
    }
}
