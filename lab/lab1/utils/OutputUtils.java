package utils;

public class OutputUtils {
    public static void printSearchResult(int number, int position) {
        if (position != -1) {
            System.out.println("Искомое число " + number + " имеет индекс в отсортированном массиве: " + position);
        } else {
            System.out.println("Искомое число " + number + " не найдено в отсортированном массиве");
        }
    }
    public static void printArray(int[] array){
        for (int i = 0; i<array.length; i++){
            System.out.print(array[i]);
        }
    }
}