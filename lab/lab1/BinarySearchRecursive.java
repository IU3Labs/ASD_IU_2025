import java.util.Scanner;
public class BinarySearchRecursive {
        public static int findPosition(int[] array, int number, int first, int last){
            if (first > last){
                System.out.println("Число " + number + " не найдено");
                return -1;
            }
            int middle = first + (last - first) / 2;
            if (array[middle] == number) {
                return middle;
            } else if (array[middle] < number){
                return findPosition(array, number, middle + 1, last);
            }else {
                return findPosition(array, number, first, middle - 1);
            }
        }

        public static void arrayFill(int[] array, int n){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите элементы массива: ");
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
        }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Обмен элементов
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        arrayFill(array, n);

        System.out.print("Введите искомое число: ");
        int number = scanner.nextInt();

        System.out.print("Исходный массив: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println();
        bubbleSort(array);
        System.out.print("Отсортированный массив: ");
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
        int result = findPosition(array, number, 0, array.length - 1);
        System.out.println("Индекс числа " + number + " в отсортированном массиве: " + result);
        scanner.close();
    }
}
