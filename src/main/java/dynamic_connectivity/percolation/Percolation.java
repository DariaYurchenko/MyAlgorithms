package dynamic_connectivity.percolation;

/* *****************************************************************************
 *  Name: Daria Yurchenko
 *  Date: 9/27/2019
 *  Description: Percolation task for Algorithms course, week 1
 **************************************************************************** */

import dynamic_connectivity.percolation.library.WeightedQuickUnionUF;

public class Percolation {

    //false - blocked, true - open
    private boolean[][] grid;
    private final int n;
    private final int size;
    private int openSites;
    private final WeightedQuickUnionUF topBottom;
    //just fot isFull() method
    private final WeightedQuickUnionUF top;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("Incorrect number of rows in the grid.");
        }

        this.grid = new boolean[n][n];
        this.n = n;
        this.size = n*n;
        this.topBottom = new WeightedQuickUnionUF(size + 2);
        this.top = new WeightedQuickUnionUF(size + 1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        for(int i = 1; i <= n; i++) {
            topBottom.union(0, i);
            topBottom.union(size + 1, size + 1 - i);
            top.union(0, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateParameters(row, col);
        if(!isOpen(row, col)) {
            grid[row-1][col-1] = true;
            openSites++;
        }
        int index = findIndexInUF(row, col);
        if(row > 1 && isOpen(row-1,col)) {
            topBottom.union(findIndexInUF(row-1,col), index);
            top.union(findIndexInUF(row-1,col), index);
        }
        if(row < n && isOpen(row+1, col)) {
            topBottom.union(findIndexInUF(row+1, col), index);
            top.union(findIndexInUF(row+1, col), index);
        }
        if(col > 1 && isOpen(row, col-1)) {
            topBottom.union(findIndexInUF(row,col-1), index);
            top.union(findIndexInUF(row,col-1), index);
        }
        if(col < n && isOpen(row, col+1)) {
            topBottom.union(findIndexInUF(row,col+1), index);
            top.union(findIndexInUF(row,col+1), index);
        }
    }

    //mapps row/col parameters to the index in array
    private int findIndexInUF(int row, int col) {
        return (row - 1) * n + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateParameters(row, col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateParameters(row, col);
        return isOpen(row, col) && top.connected(findIndexInUF(row, col), 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if(n == 1) {
            return isOpen(1,1);
        }
        return topBottom.connected(0, size-1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 5);
        percolation.open(1, 5);
        System.out.println(percolation.isOpen(1, 5));
        System.out.println(percolation.isOpen(1, 5));
        //percolation.open(2,3);
        //percolation.open(2,4);
        //percolation.open(3,4);
        //percolation.open(4,4);
        //percolation.open(2,2);
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(percolation.percolates());
    }

    private boolean validateParameters(int i, int j) {
        if(i <= 0 || i > n || j <= 0 || j > n) {
            throw new IllegalArgumentException("Parameters you are putting in are incorrect.");
        }
        return true;
    }

}