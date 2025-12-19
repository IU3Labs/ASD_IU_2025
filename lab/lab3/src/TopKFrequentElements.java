//Дан целочисленный массив nums и целое число k, верните k
//наиболее часто встречающихся элементов. Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int[] nums = ArrayMethods.readData(inputScanner);
        int k = readKValue(inputScanner);
        int[] result = getTopKFrequent(nums, k);
        System.out.print("k наиболее частых элементов: ");
        ArrayMethods.displayData(result);
        inputScanner.close();
    }

    public static int readKValue(Scanner scanner) {
        System.out.print("Введите значение k: ");
        return scanner.nextInt();
    }

    private static int[] getTopKFrequent(int[] nums, int k) {
        if (nums.length == 0) return new int[0];

        int[] sortedCopy = nums.clone();  // O(N)
        Arrays.sort(sortedCopy);  // O(N log N)

        int[] uniqueElements = new int[sortedCopy.length];
        int[] elementCounts = new int[sortedCopy.length];

        int uniqueCount = 1;
        uniqueElements[0] = sortedCopy[0];
        elementCounts[0] = 1;

        for (int i = 1; i < sortedCopy.length; i++) {  // O(N)
            if (sortedCopy[i] == sortedCopy[i-1]) {
                elementCounts[uniqueCount-1]++;
            } else {
                uniqueElements[uniqueCount] = sortedCopy[i];
                elementCounts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        int[][] elementFrequencyPairs = new int[uniqueCount][2];
        for (int i = 0; i < uniqueCount; i++) {  // ≤ O(N)
            elementFrequencyPairs[i][0] = uniqueElements[i];
            elementFrequencyPairs[i][1] = elementCounts[i];
        }

        Arrays.sort(elementFrequencyPairs, (pair1, pair2) -> pair2[1] - pair1[1]); //  ≤ O(N log N)

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {  // ≤ O(N)
            result[i] = elementFrequencyPairs[i][0];
        }
        return result;
    }
}
