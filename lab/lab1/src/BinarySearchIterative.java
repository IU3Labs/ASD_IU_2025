/*  задача 0
    реализовать алгоритм бинарного поиска двумя способами.
    иттерационный метод  */


public class BinarySearchIterative {

    public static void main(String[] args) {
        int[] inputArray = ArrayMethods.bubleSort(InpOutMethods.getArray());

        int referenceValue = InpOutMethods.getInt("Ввудите искомое значение ");

        int targetValue = searchIterative(inputArray, referenceValue);

        System.out.println("искомое значение находится на " + targetValue + " месте");

        InpOutMethods.printArray(inputArray);
    }


    private static int searchIterative(int[] array, int referenceValue) {
        int leftBorder = 0, rightBorder = array.length - 1, midle = 0;
        while (true) {
            midle = (rightBorder + leftBorder) / 2;

            if (array[leftBorder] == referenceValue) return midle + 1;
            if (rightBorder < leftBorder) return -1;

            if (array[midle] > referenceValue) rightBorder = midle - 1;
            if (array[midle] < referenceValue) leftBorder = midle + 1;
        }
    }
}