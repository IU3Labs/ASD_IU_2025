public class Main {
    public static void main(String[] args) {
        System.out.println("0) Основное задание.");
        MainTask.main(args);
        System.out.println();

        System.out.println("А1)");
        StackMinimum.main(args);
        System.out.println();

        System.out.println("А2)");
        QueueCycle.main(args);
        System.out.println();

        System.out.println("B1)");
        QueueMultilayer.main(args);
        System.out.println();

        System.out.println("B2)");
        LRUCache.main(args);
        System.out.println();
    }
}
