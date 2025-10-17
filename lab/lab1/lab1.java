import java.util.*;
public class lab1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // создание и заполнение массива
        System.out.print("Размер массива (минимум 5): ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) arr[i] = scanner.nextInt();
        Arrays.sort(arr);
        System.out.print("Число для поиска: ");
        int target = scanner.nextInt();
        // Итеративный поиск
        int index = binarySearchIter(arr, target);
        System.out.println("Итеративный поиск: " + (index == -1 ? "не найдено" : "найдено на индексе " + index));
        // Рекурсивный поиск
        index = binarySearchRec(arr, target, 0, arr.length - 1);
        System.out.println("Рекурсивный поиск: " + (index == -1 ? "не найдено" : "найдено на индексе " + index));
        // Число, частота которого равна его значению
        System.out.println("Число с частотой = значению: " + findSpecialNumber(arr));
        // Сумма двух наименьших положительных
        System.out.println("Сумма двух наименьших положительных: " + sumTwoMinPos(arr));
        scanner.nextLine(); // очистка буфера
        // Изограмма
        System.out.print("Введите слово для изограммы: ");
        String word = scanner.nextLine();
        if (word.chars().allMatch(Character::isLetter)) {
            System.out.println("Изограмма: " + (isIsogram(word) ? "да" : "нет"));
        } else {
            System.out.println("Слово содержит не только буквы.");
        }
        //Римские числа
        System.out.print("Введите римское число: ");
        String roman = scanner.nextLine();
        System.out.println("Арабское число: " + romanToArabic(roman));
        scanner.close();
    }
    // Методы
    //бинарный посик
    // итерациями
    private static int binarySearchIter(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] == target) return m;
            else if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }
    //рекрсией
    private static int binarySearchRec(int[] arr, int target, int l, int r) {
        if (l > r) return -1;
        int m = (l + r) / 2;
        if (arr[m] == target) return m;
        else if (arr[m] < target) return binarySearchRec(arr, target, m + 1, r);
        else return binarySearchRec(arr, target, l, m - 1);
    }
    //задание б1
    private static int findSpecialNumber(int[] arr) {
        Map<Integer, Integer> f = new HashMap<>();
        for (int x : arr) f.put(x, f.getOrDefault(x, 0) + 1);
        int res = -1;
        for (int x : f.keySet()) if (f.get(x) == x && x > res) res = x;
        return res;
    }
    //задание б2
    private static int sumTwoMinPos(int[] arr) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int x : arr) if (x > 0) {
            if (x < min1) { min2 = min1; min1 = x; }
            else if (x < min2) min2 = x;
        }
        return (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) ? -1 : min1 + min2;
    }
    // задание а1
    private static boolean isIsogram(String word) {
        word = word.toLowerCase();
        Set<Character> s = new HashSet<>();
        for (char c : word.toCharArray()) if (!s.add(c)) return false;
        return true;
    }
    // задание а2
    private static int romanToArabic(String roman) {
        Map<Character, Integer> m = Map.of(
                'I',1,'V',5,'X',10,'L',50,'C',100,'D',500,'M',1000
        );
        int res = 0, prev = 0;
        roman = roman.toUpperCase();
        for (int i = roman.length() - 1; i >= 0; i--) {
            int val = m.getOrDefault(roman.charAt(i), 0);
            res += (val < prev ? -val : val);
            prev = val;
        }
        return res;
    }
}
