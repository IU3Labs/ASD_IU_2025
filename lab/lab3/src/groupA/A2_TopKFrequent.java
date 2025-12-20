

import java.io.IOException;
import java.util.*;

public class A2_TopKFrequent {
    private static class Pair {
        int val;
        int freq;
        Pair(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public static void run(FastScanner fs) throws IOException {
        System.out.print("Enter N: ");
        int n = fs.nextInt();
        int[] a = new int[n];
        System.out.print("Enter " + n + " integers: ");
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        System.out.print("Enter K: ");
        int k = fs.nextInt();

        int[] res = topKFrequent(a, k);
        System.out.println("Answer:");
        Main.printArray(res);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) cnt.put(x, cnt.getOrDefault(x, 0) + 1);

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.freq != p2.freq) return Integer.compare(p1.freq, p2.freq);
            return Integer.compare(p2.val, p1.val);
        });

        for (var e : cnt.entrySet()) {
            Pair p = new Pair(e.getKey(), e.getValue());
            if (pq.size() < k) {
                pq.add(p);
            } else {
                Pair top = pq.peek();
                if (top.freq < p.freq || (top.freq == p.freq && top.val > p.val)) {
                    pq.poll();
                    pq.add(p);
                }
            }
        }

        ArrayList<Pair> list = new ArrayList<>();
        while (!pq.isEmpty()) list.add(pq.poll());

        list.sort((p1, p2) -> {
            if (p1.freq != p2.freq) return Integer.compare(p2.freq, p1.freq);
            return Integer.compare(p1.val, p2.val);
        });

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i).val;
        return res;
    }
}
