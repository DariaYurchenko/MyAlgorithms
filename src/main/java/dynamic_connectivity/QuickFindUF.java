package dynamic_connectivity;

//if there are many objects can be very slow because has to change a lot of ids
//N^2 - 2 cycles. union() is very expensive
//(eager approach)
public class QuickFindUF {

    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for(int i =0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int p_id = id[p];
        int q_id = id[q];

        for(int i = 0; i < id.length; i++) {
            if(id[i] == p_id) {
                id[i] = q_id;
            }
        }
    }
}
