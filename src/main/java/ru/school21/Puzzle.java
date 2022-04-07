package ru.school21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

import lombok.AccessLevel;
import lombok.Builder;
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
    String direction;


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
            for (int j = 0; j < edge; j++) {
//                res += (long) board[i][j] * a;
//                a++;
                long H = ((long) (board[i][j] + board[j][i]) * (board[i][j] + board[j][i] + 1) + board[i][j]) / 2;
                res += ((H + a) * (H + a + 1) + a) * (board[2][2] + 1);
                a += (board[2][0] + 1);
            }
        }
        return res;
//        return Arrays.deepHashCode(board);
    }

    // провкерка по хеш-коду, что паззл есть в closed
    public static boolean isItInClosed(Map<Long, ArrayList<Puzzle>> closed, Puzzle current, int[][] goal) {
        if (closed.containsKey(current.hashCodeL)) {
            ArrayList<Puzzle> includePuzzles = closed.get(current.hashCodeL);
            for (Puzzle p : includePuzzles) {
                if (isEqualsByCell(p, current)) {
//                if (Arrays.deepEquals(p.getBoard(), goal)) {
                    return true;
                }
            }
        }
        return false;
    }

    // проверка по ячейкам, что паззл есть в closed
    public static boolean isEqualsByCell(Puzzle p, Puzzle current) {
        return IntStream.range(0, p.edge)
                .noneMatch(i -> IntStream.range(0, p.edge)
                        .anyMatch(j -> p.board[i][j] != current.board[i][j]));
    }

    public static void thereIsDoubleHashCode(ArrayList<Puzzle> closed) {
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

    public static Puzzle solve(Puzzle start, int[][] end, Algos alg) {
        if (alg.equals(Algos.A_STAR)) {
            return Algorithms.aStar(start, end);
        } else
         throw new NotFoundException("No implementation for this algorithm");
    }

    // функция генерации финального положения пазла
    static int[][] getGoal(int edge) {
        int[][] arr = new int[edge][edge];
        int top = 0;
        int bottom = edge - 1;
        int left = 0;
        int right = edge - 1;
        int z = 0;
        while (true) {
            if (left > right)
                break;
            for (int i = left; i <= right; i++)
                arr[top][i] = ++z == edge * edge? 0: z;
            if (top > bottom)
                break;
            top++;
            for (int i = top; i <= bottom; i++)
                arr[i][right] = ++z == edge * edge? 0: z;
            right--;
            for (int i = right; i >= left; i--)
                arr[bottom][i] = ++z == edge * edge? 0: z;
            bottom--;
            for (int i = bottom; i >= top; i--)
                arr[i][left] = ++z == edge * edge? 0: z;
            left++;
        }
        return arr;
    }
}
