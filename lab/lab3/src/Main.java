public class Main {
    private static void menu() {
        System.out.println("LR3 - Sorting (6 points)");
        System.out.println("1) Group A #2: Top-K frequent (O(N log K))");
        System.out.println("2) Group A #3: Unique elements sorted (O(N log U))");
        System.out.println("3) Group A #4: Sort by (a & mask) then value (O(N log N))");
        System.out.println("4) Group A #5: Min in rotated sorted array (O(log N))");
        System.out.println("5) Group B #2: Sort K-sorted array (O(N log K))");
        System.out.print("Choose (1-5): ");
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        menu();
        int choice = fs.nextInt();
        if (choice == Integer.MIN_VALUE) return;

        switch (choice) {
            case 1 -> A2_TopKFrequent.run(fs);
            case 2 -> A3_UniqueSorted.run(fs);
            case 3 -> A4_MaskSort.run(fs);
            case 4 -> A5_RotatedMin.run(fs);
            case 5 -> B2_KSortedArray.run(fs);
            default -> System.out.println("Unknown choice");
        }
    }

    public static void printArray(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb);
    }
}
