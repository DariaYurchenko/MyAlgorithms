package dynamic_connectivity;
//makes trees flat
public class PathCompressionQuickUnionUF {
    private int id[];

    //extra array sz[i] to count number of objects in the tree rooted at i
    private int sz[];

    public PathCompressionQuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for(int i =0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while(id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(q) == root(p);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if(i == j) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }

    }
}
