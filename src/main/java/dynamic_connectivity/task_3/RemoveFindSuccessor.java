package dynamic_connectivity.task_3;

import dynamic_connectivity.task_2.LargestUnionUF;

/*Successor with delete. Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
- Remove x from S
- Find the successor of x: the smallest y in S such that y≥x
design a data type so that all operations (except construction) take logarithmic time or better in the worst case.*/
public class RemoveFindSuccessor {

    private boolean removeInfo[];

    private LargestUnionUF unionUF;

    private int N;

    public RemoveFindSuccessor(int N) {
        this.unionUF = new LargestUnionUF(N);
        this.N = N;
        this.removeInfo = new boolean[N];

        for(int i = 0; i < N; i++) {
            removeInfo[i] = true;
        }
    }

    //unions all removed objects
    public void remove(int p) {
        removeInfo[p] = false;
        if(p > 0 && !removeInfo[p-1]) {
            unionUF.union(p, p-1);
        }
        if(p < N-1 && !removeInfo[p+1]) {
            unionUF.union(p, p+1);
        }
    }

    public int findSuccessor(int p) {
        if(removeInfo[p]) {
            return p;
        }
        if((unionUF.find(p) + 1) >= N) {
            throw new IndexOutOfBoundsException();
        }
        return unionUF.find(p) + 1;
    }

    public static void main(String[] args) {
        RemoveFindSuccessor removeFindSuccessor = new RemoveFindSuccessor(8);
        removeFindSuccessor.remove(3);
        removeFindSuccessor.remove(4);
        System.out.println(removeFindSuccessor.findSuccessor(3));
    }
}
