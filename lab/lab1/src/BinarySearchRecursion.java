/*  задача 0
    реализовать алгоритм бинарного поиска двумя способами.
    метод рекурсией  */

public class BinarySearchRecursion {

    public static void main(String[] args) {
        int[] inputArray = ArrayMethods.bubleSort(InpOutMethods.getArray());

        int referenceValue = InpOutMethods.getInt("Ввудите искомое значение ");

        int targetValue = searchRecurshion(inputArray,
                referenceValue, 0, inputArray.length - 1, 0);
        System.out.println("искомое значение находится на " + targetValue + " месте");

        InpOutMethods.printArray(inputArray);
    }

    public static int searchRecurshion(int[] array,
                                       int referenceValue,
                                       int leftBorder,
                                       int rightBorder,
                                       int midle) {
        midle = (rightBorder + leftBorder) / 2;
        if (array[leftBorder] == referenceValue) return midle + 1;
        if (rightBorder < leftBorder) return -1;

        if (array[midle] > referenceValue) rightBorder = midle - 1;
        if (array[midle] < referenceValue) leftBorder = midle + 1;

        return searchRecurshion(array, referenceValue, leftBorder, rightBorder, midle);

    }
}