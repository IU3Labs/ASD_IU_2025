//Группа Б.
//Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.

public class SumOfTwoMin {
    public static void main(String[] args){
        int[] array = getValidArray();

        int sum = sum(array);
        if(sum == -1){
            System.out.println("В массиве нет двух положительных чисел");
        }
        else{
            System.out.println(sum);
        }
    }

    private static int[] getValidArray() {
        int[] array = ArrayMethods.newArray();

        while (array.length < 5) {
            System.out.println("Минимальное количество элементов - 5. Повторите ввод:");
            array = ArrayMethods.newArray();
        }
        return array;
    }
    private static int sum(int[] array){
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        int flag = 0;
        for(int i: array){
            if(i > 0) {
                flag++;
                if (i < min1) {
                    min2 = min1;
                    min1 = i;
                }
                else if (i < min2) {
                    min2 = i;
                }
            }
        }
        if(flag < 2){
            return -1;
        }
        return (min1 + min2);
    }
}
