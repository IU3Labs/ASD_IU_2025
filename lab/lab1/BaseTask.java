package lab1;


public class BaseTask extends FunctionsLab1 // класс 1-ой лабы задания
{
    // main
    public static void main(String[] args) {
        startZero(); // во второй реализации управления знаками "<", ">" нет для экономии времени
    }

    // --------------- запуск базовой (0) задачи ---------------
    public static void startZero() {
        // ЗАДАНИЕ:
        // Реализовать алгоритм бинарного поиска двумя способами.

        //ввод
        System.out.println(" --------------- Base (0) task ---------------");
        System.out.print("Finding value - ");
        int finding = inNumber();
        in.nextLine();

        // ввод массива
        int [] numbers = inIntList();
        bubbleSort(numbers); // сортировка

        // вызов вариантов решений
        bazaVar1(finding, numbers);
        System.out.println("\n");
        bazaVar2(finding, numbers);
        System.out.println("\n");
    }

    // алгоритм 1 для базовой (0) задачи (прямой)
    public static void bazaVar1(int val, int [] nums) {

        System.out.println(" ------------ Starting bazaVar1 ------------");

        // объявление переменных
        int finding = val;
        int minn = 0;
        int maxx = nums.length-1;
        int coursore = (maxx - minn) / 2;

        // сам алгоритм 1 (деревянный)
        int currentElement = nums[coursore];
        while (currentElement != finding) {
            currentElement = nums[coursore];
            if (finding > currentElement) {
                minn = coursore + 1;
            } else if (finding < currentElement) {
                maxx = coursore - 1;
            }
            coursore = (maxx - minn) / 2 + minn;
            System.out.println("Min = " + nums[minn] + ", Current = " + currentElement + ", Finding = " + finding + ", Max = " + nums[maxx]);

        }
        System.out.println(currentElement);
        System.out.println("Found.");
        System.out.println(" ------------ Ending bazaVar1 ------------");

    }

    // метод вызова рекурсии и красивого вывода
    public static void bazaVar2(int val, int [] nums) {
        System.out.println(" ------------ Starting bazaVar2 ------------");
        // объявление переменных
        int maxx = nums.length-1;
        int minn = 0;
        int finding = val;

        // вызов рекурсии
        rec((maxx - minn) / 2, minn, maxx, nums, finding);
        System.out.println("Found.");
        System.out.println(" ------------ Ending bazaVar2 ------------");
    }

    // алгоритм 2 для базовой (0) задачи (рекурсия)
    public static int rec(int coursore, int minn, int maxx, int [] nums, int real) {
        int current = nums[coursore];
        System.out.println("Min = " + nums[minn] + ", Current = " + current + ", Finding = " + real + ", Max = " + nums[maxx]);
        if (current == real) { // выход в случае успеха
            return real;
        }
        if (current > real) {
            maxx = coursore - 1;
        }
        if (current < real) {
            minn = coursore + 1;
        }
        if (minn > maxx) { // выход в случае ошибки
            System.out.println("Out of range");
            return -1;
        }
        coursore = (maxx - minn) / 2 + minn; // обновляем среднее значение
        return rec(coursore, minn, maxx, nums, real);
    }
}

