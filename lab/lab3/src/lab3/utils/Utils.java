package lab3.utils;

import java.util.Random;

public class Utils {
    public static void mergeSort (int [] array, int first, int last){
        if (last <= first) return;
        int center = (first+last)/2;
        mergeSort(array,first,center);
        mergeSort(array,center+1,last);
        mergeArrays(array, first, center, last);
    }
    public static void mergeArrays(int[] array,int first, int center, int last){
        int index1 = first;
        int index2 = center + 1;
        int indexNew = 0;
        int[] result = new int[last - first + 1];

        while (index1 <= center && index2 <= last) {
            if (array[index1] <= array[index2]) {
                result[indexNew++] = array[index1++];
            } else {
                result[indexNew++] = array[index2++];
            }
        }
        while (index1 <= center) {
            result[indexNew++] = array[index1++];
        }
        while (index2 <= last) {
            result[indexNew++] = array[index2++];
        }
        System.arraycopy(result,0,array,first,result.length);
    }

    public static int random10(){
        Random random = new Random();
        return random.nextInt(10);
    }
}
