/*  Группа А
    Реализуйте метод, входными данными которого являются два числа N и M,
    где N – число в десятичной системе исчисления, а M – число в диапазоне от
    2 до 9, основание системы исчисления, в которое надо перевести исходное
    число. Метод должен возвращать строку с преобразованным значением.*/

public class ConversionToAnotherBase {
    public static void main(String[] args) {
        int valueToConversion = InpOutMethods.getInt("Введите число для переревода ");
        int base = InpOutMethods.getInt("Введите систему исчисление ");

        String target = conversionBase(valueToConversion, base);

        System.out.println("Число в новой с/и: " + target);
    }

    public static String conversionBase(int value, int base) {
        String target = "";

        while (true) {
            if (value < base) return value + target;
            int converter = value % base;
            value /= base;
            target = converter + target;
        }
    }

}