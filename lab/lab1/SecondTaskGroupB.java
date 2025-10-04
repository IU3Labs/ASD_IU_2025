package lab1;

public class SecondTaskGroupB {

    public static void main(String[] args) {
        int multiplierNum1[] = {1, 2, 3, 4, 5};
        int multiplierNum2[] = {1, 2, 3};

        int getNum1 = translateToNumbers(multiplierNum1);
        int getNum2 = translateToNumbers(multiplierNum2);

        int product = getNum1 * getNum2; // умножение
        System.out.println("Произведение: " + product);

        int[] resultArray = numberToArray(product);

        System.out.print("[");
        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(resultArray[i]);
            if (i < resultArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }


    public static int translateToNumbers(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str = str + arr[i];
        }
        int num = Integer.parseInt(str);
        return num;
    }


    public static int[] numberToArray(int num) {
        String str = String.valueOf(num);
        int[] arr = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) - '0';
        }

        return arr;
    }
}
