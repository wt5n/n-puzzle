package ru.school21;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Puzzle implements Cloneable {

    static int[][] goal;
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

    public Long hashCodeL() {
        long res = 0L;
        int a = 1;
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                long H = ((long) (board[i][j] + board[j][i]) * (board[i][j] + board[j][i] + 1) + board[i][j]) / 2;
                res += ((H + a) * (H + a + 1) + a) * (board[2][2] + 1);
                a += (board[2][0] + 1);
            }
        }
        return res;
    }

    // провкерка по хеш-коду, что паззл есть в closed
    public static boolean isItInClosed(Map<Long, ArrayList<Puzzle>> closed, Puzzle current) {
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

    public static boolean isEqualsByCell(Puzzle p, Puzzle current) {
        return IntStream.range(0, p.edge)
                .noneMatch(i -> IntStream.range(0, p.edge)
                        .anyMatch(j -> p.board[i][j] != current.board[i][j]));
    }

    @Override
    public Puzzle clone() {
        try {
            Puzzle clone = (Puzzle) super.clone();
            int[][] cloneBoard = new int[edge][edge];
            for (int i = 0; i < edge; i++) {
                System.arraycopy(getBoard()[i], 0, cloneBoard[i], 0, edge);
            }
            clone.setBoard(cloneBoard);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static int[][] calculateGoal(int edge) {
        int[][] arr = new int[edge][edge];
        int top = 0;
        int bottom = edge - 1;
        int left = 0;
        int right = edge - 1;
        int z = 0;
        while (true) {
            if (left > right) {
                break;
            }
            for (int i = left; i <= right; i++)
                arr[top][i] = ++z == edge * edge? 0: z;
            if (top > bottom)
                break;
            top++;
            for (int i = top; i <= bottom; i++)
                arr[i][right] = ++z == edge * edge? 0: z;
            right--;
            for (int i = right; i >= left; i--) {
                arr[bottom][i] = ++z == edge * edge ? 0 : z;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                arr[i][left] = ++z == edge * edge ? 0 : z;
            }
            left++;
        }
        return arr;
    }

    public void printBoard() {
        System.out.println("-------------------");
        for (int[] ints : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    public void printGoal() {
        for (int[] ints : goal) {
            for (int j = 0; j < goal.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
