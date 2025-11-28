public class Main {
    public static void main(String[] args) {
        System.out.println("2) IDDFS:");
        IDDFS.main(args);
        System.out.println();

        System.out.println("3) DFS Recursive:");
        DFSRecursive.main(args);
        System.out.println();

        System.out.println("3) DFS Iterative:");
        DFSIterative.main(args);
        System.out.println();

        System.out.println("4) BFS (levels):");
        BFSLevels.main(args);
        System.out.println();

        System.out.println("4) BFS (recursive levels):");
        BFSLevelsRecursive.main(args);
        System.out.println();
    }
}
