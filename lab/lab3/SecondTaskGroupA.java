package lab3;

import java.util.*;

public class SecondTaskGroupA {

    public static List<Integer> topKFrequent(int[] nums, int k) {

        if (k <= 0) return Collections.emptyList();

        Map<Integer, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a, b) -> {
                    int fa = freq.get(a);
                    int fb = freq.get(b);

                    if (fa != fb) return Integer.compare(fa, fb);

                    return Integer.compare(b, a);
                }
        );

        for (int val : freq.keySet()) {


            if (heap.size() < k) {
                heap.offer(val);
            } else {

                int worst = heap.peek();

                int freqWorst = freq.get(worst);
                int freqVal   = freq.get(val);


                if (freqVal > freqWorst ||
                        (freqVal == freqWorst && val < worst)) {

                    heap.poll();

                    heap.offer(val);
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        int[] a = {1,1,1, 2,2, 3,3, 4,4,4,4, 5};
        int k = 3;

        System.out.println(topKFrequent(a, k));
    }
}

