//Группа Б.
//Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

public class ElementFrequency {
    public static void main(String[] args) {
        int[] array = ArrayMethods.newArray();

        int maxNumber = 0;
        for(int i: array){
            if(i > maxNumber){
                maxNumber = i;
            }
        }

        int[] frequency = new int[maxNumber + 1];

        for(int i: array){
            frequency[i]++;
        }

        int result = -1;
        int length = frequency.length;
        for(int i = 1; i <= length; i++){
            if(i <= maxNumber && i == frequency[i] && result < i){
                result = i;
            }
        }
        System.out.println(result);
    }

}

