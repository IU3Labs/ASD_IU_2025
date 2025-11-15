// Текст задачи: Реализуйте метод, входными данными которого являются два числа N и M,
// где N число в десятичной системе исчисления, а M число в диапазоне от 2 до 9,
// основание системы исчисления, в которое надо перевести исходное число.
// Метод должен возвращать строку с преобразованным значением. 

public class BaseConverter {

    public static String convertToBase(int n, int base) {
        if (n == 0) {
            return "0";
        }

        boolean isNegative = n < 0;
        if (isNegative) {
            n = -n;
        }

        String result = "";
        while (n > 0) {
            int remainder = n % base;
            result = remainder + result;
            n = n / base;
        }

        if (isNegative) {
            result = "-" + result;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = LabHelper.readInt("Введите десятичное число (N): ");
        int m = 0;
        while (m < 2 || m > 9) {
            m = LabHelper.readInt("Введите основание системы (M от 2 до 9): ");
            if (m < 2 || m > 9) {
                System.out.println("Ошибка: Основание должно быть в диапазоне от 2 до 9.");
            }
        }

        String converted = convertToBase(n, m);
        System.out.println("Число " + n + " в системе счисления " + m + " = " + converted);
    }
}