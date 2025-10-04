/*
Изограмма – это слово, в котором нет повторяющихся букв,
последовательных или непоследовательных. Реализуйте функцию, которая
определяет, является ли строка, изограммой. Пустая строка является
изограммой.
 */
import java.util.Locale;
import  java.util.Scanner;
public class Task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите слово: ");
        String word = sc.nextLine();
        if (ifIsogram(word)) {
            System.out.println("Слово является изограммой");
        } else {
            System.out.println("Слово не является изограммой");
        }
    }
    private static boolean ifIsogram(String word){
        word = word.toLowerCase();
        if(word.isEmpty()){
           return true;
        }
        boolean[] arr = new boolean[Character.MAX_VALUE+1];
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(arr[ch]){
                return false;
            }
            arr[ch] = true;
        }
        return true;
    }
}
