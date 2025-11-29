import java.util.*;


class Graph {
    int V;

    LinkedList<Integer> adj[];

    boolean DLS(int v, int target, int limit)
    {
        if (v == target)
            return true;

        if (limit <= 0)
            return false;

        for (int i : adj[v])
            if (DLS(i, target, limit - 1))
                return true;

        return false;
    }

    public Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w)
    {
        adj[v].add(w);
    }

    boolean IDDFS(int src, int target, int max_depth)
    {
        for (int i = 0; i <= max_depth; i++)
            if (DLS(src, target, i))
                return true;

        return false;
    }
}