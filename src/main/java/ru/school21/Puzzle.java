import java.util.ArrayList;
import java.util.Objects;

public class Puzzle implements Cloneable {

    private ArrayList<Integer> board;
//    final private ArrayList<Integer> end;
    final private int size;
    final private int edge;
    private int g = 0;

    public void setBoard(ArrayList<Integer> board) {
        this.board = board;
    }

    private int f = 0;
    private int e = 0;
    private Puzzle prev;

    public Puzzle (ArrayList<Integer> board, int size, int edge, int g) {
        this.board = board;
//        this.end = end;
        this.size = size;
        this.edge = edge;
        this.g = g;
        this.prev = prev;
    }

    public ArrayList<Integer> getBoard() {
        return board;
    }

    public Puzzle getPrev() {
        return prev;
    }

    public void setPrev(Puzzle prev) {
        this.prev = prev;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getE() {
        return e;
    }

    public void setE() {
        this.e = g + f;
    }

    public int getSize() {
        return size;
    }

    public int getEdge() {
        return edge;
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
