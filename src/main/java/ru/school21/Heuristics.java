package ru.school21;

import java.util.ArrayList;

public class Heuristics {

    public static int manhattanDistance(Puzzle start) {
//        start.pprint();
        int res = 0;
        for (int i = 0; i < start.getSize(); i++) {
            if (start.getBoard().get(i) != 0) {
                res += (Math.abs((start.getBoard().get(i) - 1) / start.getEdge() - i / start.getEdge()) +
                        Math.abs((start.getBoard().get(i) - 1) % start.getEdge() - i % start.getEdge()));
            }
        }
        return res;
    }

    public static int linerConflict(Puzzle puzzle, ArrayList<ArrayList<Integer>> tab_for_ln) {
//        puzzle.pprint();
        int res = 0;

        int edge = puzzle.getEdge();
        int size = puzzle.getSize();
        ArrayList<Integer> board = puzzle.getBoard();
//        // modificator for each line/column
        int addition_edge = 0;
        // by lines

        for (int e = 0; e < edge; e++) {
            for (int i = 0; i < edge - 1; i++) {
                for (int j = i + 1; j < edge; j++) {
                    if (board.get(i + addition_edge) != 0 & board.get(j + addition_edge) != 0
                            && j + addition_edge != size - 1 // for skip left_down corner
                            && board.get(i + addition_edge) <= edge + addition_edge && board.get(i + addition_edge) > addition_edge
                            && board.get(j + addition_edge) <= edge + addition_edge && board.get(j + addition_edge) > addition_edge
                            && board.get(i + addition_edge) > board.get(j + addition_edge)) {
                        res += 2;
                    }
                }
            }
            addition_edge += edge;
        }
        // by columns
        addition_edge = 0;
        for (int e = 0; e < edge; e++) {
            for (int i = 0; i < edge; i++) {
                for (int j = i + edge; j < edge * edge; j += edge) {
                    if (j + addition_edge < size
                            && board.get(i + addition_edge) != 0 & board.get(j + addition_edge) != 0
                            && j + addition_edge != size - 1 // for skip left_down corner
                            && tab_for_ln.get(i).contains(board.get(j + addition_edge)) && tab_for_ln.get(i).contains(board.get(i + addition_edge))
                            && board.get(i + addition_edge) > board.get(j + addition_edge)) {
                        res += 2;
                    }
                }
            }
            addition_edge += edge;
        }
        return res;
    }

    public static int thirdHeuristic() {
        return 3;
    }
}
