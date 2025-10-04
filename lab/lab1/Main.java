import utils.InputManager;
import utils.ArrayUtils;
import utils.OutputUtils;
import algorithms.BinarySearch;
import utils.SorterArrays;
import java.util.Scanner;


public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите кол-во чисел в массиве: ");
        int size = InputManager.inputTarget(scan);
        int[] array = new int[size];
        ArrayUtils.fillArray(array, scan);
        System.out.println("Введите искомое число: ");
        int target = InputManager.inputTarget(scan);
        System.out.println("1 - Итерационный метод. 2 - Рекурсивный метод: ");
        if (InputManager.inputTarget(scan) == 1) {OutputUtils.printSearchResult(target, BinarySearch.iterativeSearch(SorterArrays.sort(array), target));

        }
        else{
            OutputUtils.printSearchResult(target, BinarySearch.recursiveSearch(SorterArrays.sort(array), target));
        }
        scan.close();
    }
}
