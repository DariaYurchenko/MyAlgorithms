package dynamic_connectivity.percolation;

/* *****************************************************************************
 *  Name: Daria Yurchenko
 *  Date: 9/27/2019
 *  Description: Percolation task for Algorithms course, week 1
 **************************************************************************** */

import dynamic_connectivity.percolation.library.StdRandom;
import dynamic_connectivity.percolation.library.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE = 1.96;

    private final int trials;
    private final double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Input parameters are incorrect (<=0).");
        }

        this.trials = trials;
        this.results = new double[trials];

        int sitesAmount = n*n;

        Percolation percolation;
        for(int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            int p = 0;
            int j = 0;
            int openSites = 0;
            while(!percolation.percolates()) {
                p = StdRandom.uniform(1, n + 1);
                j = StdRandom.uniform(1, n + 1);
                if (percolation.isOpen(p, j)) {
                    continue;
                }
                percolation.open(p, j);
                openSites++;
            }
            results[i] = (double) openSites/sitesAmount;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE * stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(2, 10000);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
    }

}
