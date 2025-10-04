package lab1.b;


/*Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.*/

import java.util.Scanner;
public class ArrayMass {
    static Scanner input = new Scanner(System.in);
    public static void main (String [] args){
        byte [] a = inputArray();
        byte [] b = inputArray();
        printArray(mutippleArrays(a,b));
    }
    static byte [] mutippleArrays(byte[] firstArray,byte[] secondArray){
        int resultNumber = convertToNumberArray(firstArray) * convertToNumberArray(secondArray);
        return convertNumberToArray(resultNumber);
    }
    static int convertToNumberArray(byte[] array){
        int number = 0;
        for (int i= array.length-1; i >= 0; i--){
            number += array[i] * (int) Math.pow(10,array.length - i-1);
        }
        return number;
    }
    static byte [] convertNumberToArray(int number){
        int arrayLength = ("" + number).length();
        byte [] result = new byte [arrayLength];
        for (int i= arrayLength-1; i >= 0; i--){
            result[i] = (byte) (number%10);
            number/=10;
        }
        return result;
    }

    static byte[] inputArray(){
        System.out.print("Введите длину массива:");
        int arrayLength = input.nextInt();
        byte [] array = new byte [arrayLength];
        System.out.print("Введите элементы массива:");
        for(int i=0; i< arrayLength; i++){
            array[i] = input.nextByte();
        }
        return array;
    }
    static void printArray(byte [] array){
        System.out.print("[");
        for (int i=0; i< array.length; i++){
            System.out.print(array[i]+",");
        }
        System.out.println("]");
    }

}