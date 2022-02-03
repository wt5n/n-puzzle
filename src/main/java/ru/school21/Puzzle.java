package ru.school21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Puzzle implements Cloneable {

    int[][] board;
    final int size;
    final int edge;
    int g;
    int f = 0;
    int l = 0;
    int e = 0;
    Puzzle prev;
    long hashCodeL;

    public Puzzle(int[][] board, int size, int edge, int g) {
        this.board = board;
        this.size = size;
        this.edge = edge;
        this.g = g;
        hashCodeL = hashCodeL();
    }

    public void setE() {
        this.e = g + f + l;
    }

//    @Override
//    public String toString() {
//        StringBuilder res = new StringBuilder();
//        for (Integer i:this.board)
//            res.append(i.toString());
//        return res.toString();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return Arrays.deepEquals(board, puzzle.board);
    }

//    @Override
    public Long hashCodeL() {
        long res = 0L;
        int a = 1;
        for (int i = 0; i < edge; i++) {
            for (int j =0; j < edge; j++) {
                res += (long) board[i][j] *a;
                a++;
            }
        }
        return res;
//        return Arrays.deepHashCode(board);
    }

    static public boolean isItInClosed(Map<Long, ArrayList<Puzzle>> closed, Puzzle current) {
        if (closed.containsKey(current.hashCodeL)) {
            ArrayList<Puzzle> includePuzzles = closed.get(current.hashCodeL);
            for (Puzzle p : includePuzzles) {
                if (isItInClosed2(p, current)) {
                    return true;
                }
            }
        }
        return false;
    }

    static private boolean isItInClosed2(Puzzle p, Puzzle current) {
        for (int i = 0; i < p.edge - 1; i++) {
            for (int j = 0; j < p.edge - 1; j++)
                if (p.board[i][j] != current.board[i][j]) {
                    return false;
                }
        }
        return true;
    }

    static public void thereIsDoubleHashCode(ArrayList<Puzzle> closed) {
        for (int i = 0; i < closed.size() - 1; i++) {
            if (closed.get(i).hashCodeL == closed.get(i + 1).hashCodeL) {
                System.out.println("GOTTA");
            }
        }
    }

    public void pprint() {
        System.out.println("-------------------");
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println("-------------------");
    }

    @Override
    public Puzzle clone() {
        try {
            Puzzle clone = (Puzzle) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            int[][] cloneBoard = new int[edge][edge];
            for (int i = 0; i < edge; i++) {
                System.arraycopy(getBoard()[i], 0, cloneBoard[i], 0, edge);
            }
            clone.setBoard(cloneBoard);
//            for (int i = 0; i < this.getSize(); i++)
//                clone.board[i] = this.getBoard().get(i);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static Puzzle solve(Puzzle start, int[][] end, String alg, ArrayList<ArrayList<Integer>> tab_for_ln) {
        switch (alg) {
            case "A*":
                return Algorithms.aStar(start, end, tab_for_ln);
            default:
                System.out.println("Not correct algo!");
                return null;
        }
    }
}
