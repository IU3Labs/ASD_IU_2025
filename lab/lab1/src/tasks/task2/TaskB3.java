package tasks.task2;

import helper.ArrayTools;

import java.io.IOException;

public class TaskB3 {
    public static int findTwoMinNumber(int[] array) {
        int min = Integer.MAX_VALUE;
        int prevMin = Integer.MAX_VALUE;
        for (int k : array) {
            if (k < 0) {
                continue;
            }
            if (k < min) {
                min = k;
            }
        }

        for (int j : array) {
            if (j < 0 || j == min) {
                continue;
            }
            if (j < prevMin) {
                prevMin = j;
            }
        }

        if (min != Integer.MAX_VALUE && prevMin == Integer.MAX_VALUE){
            prevMin = min;
        }

        return prevMin + min;
    }

    public static void taskB3() throws IOException {
        int[] array = ArrayTools.createIntArray();
        while (array.length < 5){
            System.out.println("Нам нужно больше элементов в массиве, постарайся ввести хотя бы 5 элементов");
            array = ArrayTools.createIntArray();
        }
        System.out.println(findTwoMinNumber(array));
    }
}