package tasks;

import utils.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class InversionCounter {

    public static void main(String[] args) {
        int[] array = ArrayUtils.inputArray();
        List<InversionPair> inversions = findInversions(array);
        System.out.println("Количество инверсий: " + inversions.size());
        for (InversionPair inversion : inversions) {
            System.out.println("(" + inversion.indexI + ", " + inversion.indexJ + ") - " +
                    array[inversion.indexI] + " > " + array[inversion.indexJ]);
        }
    }

    public static List<InversionPair> findInversions(int[] array) {
        int[] tempArray = array.clone();
        int[] originalIndices = new int[array.length];
        for (int i = 0; i < originalIndices.length; i++) {
            originalIndices[i] = i;
        }

        List<InversionPair> inversionList = new ArrayList<>();
        mergeSortWithInversionCount(tempArray, originalIndices, 0, array.length - 1, inversionList);
        return inversionList;
    }

    private static void mergeSortWithInversionCount(int[] array, int[] indices, int left, int right,
                                                    List<InversionPair> inversionList) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortWithInversionCount(array, indices, left, middle, inversionList);
            mergeSortWithInversionCount(array, indices, middle + 1, right, inversionList);
            mergeWithInversionCount(array, indices, left, middle, right, inversionList);
        }
    }

    private static void mergeWithInversionCount(int[] array, int[] indices, int left, int middle, int right,
                                                List<InversionPair> inversionList) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];
        int[] leftIndices = new int[leftSize];
        int[] rightIndices = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
            leftIndices[i] = indices[left + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[middle + 1 + i];
            rightIndices[i] = indices[middle + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                indices[k] = leftIndices[i];
                i++;
            } else {
                array[k] = rightArray[j];
                indices[k] = rightIndices[j];
                for (int x = i; x < leftSize; x++) {
                    inversionList.add(new InversionPair(leftIndices[x], rightIndices[j]));
                }
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            indices[k] = leftIndices[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            indices[k] = rightIndices[j];
            j++;
            k++;
        }
    }

    private static class InversionPair {
        public final int indexI;
        public final int indexJ;

        public InversionPair(int i, int j) {
            this.indexI = i;
            this.indexJ = j;
        }
    }
}