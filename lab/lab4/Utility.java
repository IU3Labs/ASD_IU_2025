package lab4;
import java.util.Scanner;

public class Utility {

        public static Tree createTree(Scanner scanner) {
            Tree tree = new Tree();

            System.out.print("Введите число узло: ");
            int n = scanner.nextInt();

            System.out.println("Введите значение: ");

            for (int i = 0; i < n; i++) {
                int val = scanner.nextInt();
                tree.insert(val);
            }
            return tree;
        }

        public static int inputTarget(Scanner scanner) {
            System.out.print("Число для поиска: ");
            return scanner.nextInt();
        }
    }
