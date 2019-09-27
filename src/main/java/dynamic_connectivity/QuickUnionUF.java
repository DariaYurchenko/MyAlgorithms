package dynamic_connectivity;

//find() is slow (cycle)
//trees can be very tall
//faster then quickFind but still slow
//(lazy approach)
public class QuickUnionUF {
    private int id[];

    public QuickUnionUF(int N) {
        id = new int[N];
        for(int i =0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while(id[i] != i) {
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
        id[i] = j;
    }


}
