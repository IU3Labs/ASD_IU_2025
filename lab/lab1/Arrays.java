package lab1;
import java.util.Scanner;


public class Arrays {
    static Scanner input = new Scanner(System.in);

    public static void inputArray (int[] array){
        for (int i=0;  i<array.length; i++){
            array[i]= input.nextInt();
        }
    }
}
