//2 Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.


import java.util.Scanner;

public class ArrayMultiply {
    int[] inputArray() {
        System.out.print("Enter array size: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter number (" + (i + 1) + "/" + size + "): ");
            a[i] = input();
        }
        return a;
    }

    public int input(){
        Scanner scanner =  new Scanner(System.in);
        int res = scanner.nextInt();
        return res;
    }

    void printArray(int[] a){
        for (int j : a) System.out.print(j + " ");
    }

    int[] multiply(int[] a, int[] b){
        int aNum = toNum(a), bNum = toNum(b);
        return toArray(aNum * bNum);
    }

    int[] toArray(int num){
        if (num == 0) return new int[]{0};

        int temp = num;
        int length = 0;
        while (temp > 0) {
            temp /= 10;
            length++;
        }

        // Создаем массив и заполняем цифрами
        int[] res = new int[length];
        temp = num;
        for (int i = length - 1; i >= 0; i--) {
            res[i] = temp % 10;
            temp /= 10;
        }
        return res;

    }

    int toNum(int[]  a){
        int aNum = 0;
        for(int i = a.length - 1; i >= 0; i--){
            aNum += a[i] * Math.pow(10, a.length - i - 1);
        }
        return aNum;
    }


    void main(){
        int[] a = inputArray();
        int[] b = inputArray();
        System.out.print("Result:");
        printArray(multiply(a, b));
    }
}
