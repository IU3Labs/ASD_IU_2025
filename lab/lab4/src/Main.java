public class Main {
    private static void menu() {
        System.out.println("ЛР4 — Поиск в дереве (6 баллов)");
        System.out.println("1) Группа A №2: IDDFS (углубляющийся поиск в глубину)");
        System.out.println("2) Группа A №3: DFS двумя способами (рекурсия + стек)");
        System.out.println("3) Группа A №4: BFS двумя способами (очередь + рекурсивно через очередь)");
        System.out.print("Выберите задание (1-3): ");
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        menu();
        int choice = fs.nextInt();
        if (choice == Integer.MIN_VALUE) return;

        switch (choice) {
            case 1 -> A2_IDDFS.run(fs);
            case 2 -> A3_DFS_TwoWays.run(fs);
            case 3 -> A4_BFS_TwoWays.run(fs);
            default -> System.out.println("Неверный выбор");
        }
    }
}
