// Текст задачи: Изограмма это слово, в котором нет повторяющихся букв,
// последовательных или непоследовательных. Реализуйте функцию, которая
// определяет, является ли строка, изограммой. Пустая строка является
// изограммой. 

public class IsogramChecker {

    public static boolean isIsogram(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }

        String lowerCaseStr = str.toLowerCase();
        int len = lowerCaseStr.length();

        boolean[] seen = new boolean[256];

        for (int i = 0; i < len; i++) {
            char c = lowerCaseStr.charAt(i);

            if (seen[c]) {
                return false;
            }
            seen[c] = true;
        }

        return true;
    }

    public static void main(String[] args) {
        String input = LabHelper.readString("Введите слово для проверки на изограмму: ");

        if (isIsogram(input)) {
            System.out.println("Строка \"" + input + "\" является изограммой.");
        } else {
            System.out.println("Строка \"" + input + "\" НЕ является изограммой.");
        }
    }
}