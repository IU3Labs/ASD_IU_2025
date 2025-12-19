package lab.lab3.inversion;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Inversion.FastScanner fs = new Inversion.FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int n = fs.nextInt();

        // Инициализируем поля в классе Inversion
        Inversion.a = new Inversion.Item[n];
        Inversion.temp = new Inversion.Item[n];
        Inversion.inversionCount = 0;
        Inversion.inversions.clear();

        // Читаем массив, сохраняем исходные индексы (1-based)
        for (int i = 0; i < n; i++) {
            long x = fs.nextLong();
            Inversion.a[i] = new Inversion.Item(x, i + 1);
        }

        // Запускаем merge sort с подсчётом инверсий
        Inversion.mergeSort(0, n);

        // Вывод количества инверсий
        out.append("Number of inversions: ")
                .append(Inversion.inversionCount)
                .append('\n');

        // Вывод всех инверсий
        for (int[] pair : Inversion.inversions) {
            out.append(pair[0]).append(' ').append(pair[1]).append('\n');
        }

        System.out.print(out);
    }
}