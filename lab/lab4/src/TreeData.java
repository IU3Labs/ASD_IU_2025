import java.util.ArrayList;

public class TreeData {
    public final int n;
    public final ArrayList<Integer>[] children;
    public final int root;

    @SuppressWarnings("unchecked")
    public TreeData(int n, int root) {
        this.n = n;
        this.root = root;
        this.children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
    }
}
