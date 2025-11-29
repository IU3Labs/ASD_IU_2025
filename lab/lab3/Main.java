/*Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
данном массиве и вывести их. Дать комментарии. Вычислить сложность.

Сложность O(n^2), так как цикл в цикле с N итераций
 */

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите размер массива: ");
        int N = scan.nextInt();
        int arr[] = new int[N];
        int count = 0;
        for (int i =0; i < N; i++){
            System.out.println("Введите "+(i+1)+"-й элемент массива");
            arr[i] = scan.nextInt();
        }
        for (int i =0; i < N-1; i++){
            for(int j = i+1; j < N; j++){
                if (arr[i]> arr[j]){
                    count+=1;
                    System.out.println("("+arr[i]+";"+ arr[j]+")");
                }
            }
        }
        System.out.println("Количнство инверсий в массиве равно:"+count);

    }
}