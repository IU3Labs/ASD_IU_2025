import tasks.BinarySearch;
import tasks.TaskB1;
import tasks.TaskB2;
import tasks.TaskB3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Основное задание ");
        BinarySearch.main(args);
        System.out.println();

        System.out.println("Б2");
        TaskB2.main(args);
        System.out.println();

        System.out.println("Б3");
        TaskB3.main(args);
        System.out.println();

        System.out.println("Б1");
        TaskB1.main(args);
        System.out.println();

    }
}