

import java.io.IOException;
import java.util.TreeSet;

public class A3_UniqueSorted {
    public static void run(FastScanner fs) throws IOException {
        System.out.print("Enter N: ");
        int n = fs.nextInt();

        System.out.print("Enter " + n + " integers: ");
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) set.add(fs.nextInt());

        System.out.println("Unique count: " + set.size());
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int x : set) {
            if (!first) sb.append(' ');
            first = false;
            sb.append(x);
        }
        System.out.println(sb);
    }
}
