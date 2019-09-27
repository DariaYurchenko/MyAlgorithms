package dynamic_connectivity.task_1;

/*Social network connectivity. Given a social network containing n members and a log file containing m
timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at
which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend).
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of
your algorithm should be m*log(n) or better and use extra space proportional to n.*/
public class SocialNetworkUF {
    private int id[];

    //extra array sz[i] to count number of objects in the tree rooted at i
    private int sz[];

    //amount of connections between elements;
    private int count;

    public SocialNetworkUF(int N) {
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

    public int getCount() {
        return count;
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

        if(!connected(p, q)) {
            count++;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
        if(count == id.length-1) {
            System.out.println("All elements are connected!");
        }

    }

    public static void main(String[] args) {
        SocialNetworkUF socialNetworkUF = new SocialNetworkUF(4);

        socialNetworkUF.union(0, 1);
        socialNetworkUF.union(1,3);
        socialNetworkUF.union(0,2);
    }

}
