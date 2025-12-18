import java.util.List;

public class BellmanFordResult {
    public final boolean hasNegativeCycle;
    public final boolean pathExists;
    public final long distance;
    public final List<Integer> path;

    private BellmanFordResult(boolean hasNegativeCycle, boolean pathExists, long distance, List<Integer> path) {

        this.hasNegativeCycle = hasNegativeCycle;
        this.pathExists = pathExists;
        this.distance = distance;
        this.path = path;
    }
    public static BellmanFordResult negativeCycle() {
        return new BellmanFordResult(true, false, 0, null);
    }

    public static BellmanFordResult noPath() {
        return new BellmanFordResult(false, false, 0, null);
    }

    public static BellmanFordResult success(long distance, List<Integer> path) {
        return new BellmanFordResult(false, true, distance, path);
    }
}