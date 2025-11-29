import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите задание для выполнения:");
        System.out.println("1 - Алгоритм Беллмана-Форда");
        System.out.println("2 - Iterative Deepening DFS");
        System.out.println("3 - DFS двумя способами");
        System.out.println("4 - BFS двумя способами");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                groupa.BellmanFord.main(args);
                break;
            case 2:
                groupa.IterativeDeepeningDFS.main(args);
                break;
            case 3:
                groupa.DFSImplementations.main(args);
                break;
            case 4:
                groupa.BFSImplementations.main(args);
                break;
            default:
                System.out.println("Неверный выбор");
        }

        scanner.close();
    }
}