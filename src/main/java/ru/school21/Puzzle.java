package ru.school21;

import java.util.ArrayList;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Puzzle implements Cloneable {

    ArrayList<Integer> board;
//    final private ArrayList<Integer> end;
    final int size;
    final int edge;
    int g;
    int f = 0;
    int e = 0;
    Puzzle prev;

    public Puzzle (ArrayList<Integer> board, int size, int edge, int g) {
        this.board = board;
//        this.end = end;
        this.size = size;
        this.edge = edge;
        this.g = g;
    }

    public void setE() {
        this.e = g + f;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Integer i:this.board)
            res.append(i.toString());
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return Objects.equals(board, puzzle.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    public void pprint() {
        System.out.println("-------------------");
        for (int i = 1; i <= this.size; i++) {
            System.out.printf("%2d ", this.getBoard().get(i - 1));
            if (i % this.edge == 0)
                System.out.println();
        }
        System.out.println("-------------------");
    }

    @Override
    public Puzzle clone() {
        try {
            Puzzle clone = (Puzzle) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            ArrayList<Integer> cloneBoard = new ArrayList<>(getBoard());
            clone.setBoard(cloneBoard);
//            for (int i = 0; i < this.getSize(); i++)
//                clone.board[i] = this.getBoard().get(i);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
