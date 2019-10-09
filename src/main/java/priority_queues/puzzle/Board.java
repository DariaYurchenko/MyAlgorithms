package priority_queues.puzzle;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int[][] tiles;
    private int size;
    private int hamming;
    private int manhattan;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if(tiles == null && tiles.length >= 2 && tiles.length < 128) {
            throw new IllegalArgumentException();
        }

        this.size = tiles.length;
        this.tiles = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];

                if(tiles[i][j] != i*size + j + 1) {
                    hamming++;
                }

                int a = (tiles[i][j] - 1) / size;
                int b = (tiles[i][j] - 1) % size;
                manhattan += Math.abs(a - i) + Math.abs(b - j);
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        System.out.println(size);
        for(int i = 0; i < size; i++) {
            if(i > 0) {
                builder.append("\n");
            }
            for(int j = 0; j < size; j++) {
                builder.append(tiles[i][j]).append(" ");
            }
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] != i * size + j + 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal obj?
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Board) {
            Board other = (Board) obj;
            if (size != other.dimension()) return false;
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (tiles[i][j] != other.tiles[i][j]) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();
        int[][] copy = copyBoard();

        int row = 0;
        int column = 0;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(copy[i][j] == 0) {
                    row = i;
                    column = j;
                }
            }
        }

        if(row > 0) {
            swap(copy, row, column, row - 1, column);
            neighbors.add(new Board(copy));
            swap(copy, row, column, row - 1, column);
        }
        if(row < size - 1) {
            swap(copy, row, column, row + 1, column);
            neighbors.add(new Board(copy));
            swap(copy, row, column, row + 1, column);
        }
        if(column > 0) {
            swap(copy, row, column, row, column - 1);
            neighbors.add(new Board(copy));
            swap(copy, row, column, row, column - 1);
        }
        if(column < size - 1) {
            swap(copy, row, column, row, column + 1);
            neighbors.add(new Board(copy));
            swap(copy, row, column, row, column + 1);
        }
        return neighbors;
    }

    private void swap(int[][] copy, int rowStart, int columnStart, int rowFinish, int columnFinish) {
        int temp = copy[rowStart][columnStart];
        copy[rowStart][columnStart] = copy[rowFinish][columnFinish];
        copy[rowFinish][columnFinish] = temp;
    }

    private int[][] copyBoard() {
        int[][] copy = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinBlocks = copyBoard();
        int i = 0;
        int j = 0;
        while (twinBlocks[i][j] == 0 || twinBlocks[i][j + 1] == 0) {
            j++;
            if (j >= twinBlocks.length - 1) {
                i++;
                j = 0;
            }
        }
        swap(twinBlocks, i, j, i, j + 1);
        return new Board(twinBlocks);
    }

    // unit testing (not graded)
    public static void main(String[] args) {}

}