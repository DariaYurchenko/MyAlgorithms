package dynamic_connectivity.task_2;

/*Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i)
returns the largest element in the connected component containing i. The operations, union(), connected(), and
find() should all take logarithmic time or better. For example, if one of the connected components is {1,2,6,9} then the
find()method should return 9 for each of the four elements in the connected components.*/
public class LargestUnionUF {
    private int id[];

    //extra array sz[i] to count number of objects in the tree rooted at i
    private int sz[];

    //extra array of largest elements in components
    private int largest[];

    public LargestUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        largest = new int[N];
        for(int i =0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            largest[i] = i;
        }
    }

    private int root(int i) {
        while(id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public int find(int p) {
        int root = root(p);
        return largest[root];
    }

    public boolean connected(int p, int q) {
        return root(q) == root(p);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if(largest[i] > largest[j]) {
            largest[j] = largest[i];
        }
        else {
            largest[i] = largest[j];
        }

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

    public static void main(String[] args) {
        LargestUnionUF largestUnionUF = new LargestUnionUF(5);

        largestUnionUF.union(2,3);
        largestUnionUF.union(4, 2);

        largestUnionUF.union(1, 0);

        System.out.println(largestUnionUF.find(3));
        System.out.println(largestUnionUF.find(0));

    }
}
