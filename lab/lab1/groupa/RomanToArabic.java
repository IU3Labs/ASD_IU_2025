//Реализуйте перевод из римских чисел в арабские.

package groupa;

import java.util.Scanner;

public class RomanToArabic {
    public static void main(String[] args) {
        int[] resolve = {1, 5, 10, 50, 100, 500, 1000};
        String roma = "IVXLCDM";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type a romanian num to convert: ");
        String romNum = scanner.nextLine();
        int res = 0;
        for(int i = romNum.length()-1; i>=0; i--){
            char curChar = romNum.charAt(i);
            int curNum = resolve[roma.indexOf(curChar)];
            if((i != romNum.length()-1)){
                char prevChar = romNum.charAt(i+1);
                int prevNum = resolve[roma.indexOf(prevChar)];
                if(curNum >= prevNum){
                    res += curNum;
                }
                else{
                    res -= curNum;
                }
            }
            else{
                res += curNum;
            }
        }
        scanner.close();
        System.out.println("Your converted num: " + res);
    }
}