package ru.school21;

import java.util.ArrayList;

public class Heuristics {

    public static int manhattanDistance(Puzzle puzzle, int[][] goal) {
//        puzzle.pprint();
        int res = 0;
        for (int y1 = 0; y1 < puzzle.getEdge(); y1++) {
            for (int x1 = 0; x1 < puzzle.getEdge(); x1++) {
                if (puzzle.getBoard()[y1][x1] == 0)
                    continue;
//                System.out.print(puzzle.getBoard()[y1][x1] + " ");
                for (int y2 = 0; y2 < puzzle.getEdge(); y2++) {
                    for (int x2 = 0; x2 < puzzle.getEdge(); x2++) {
                        if (puzzle.getBoard()[y1][x1] == goal[y2][x2]) {
                            res += Math.abs(y2 - y1) + Math.abs(x2 - x1);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static int linerConflict(Puzzle puzzle, ArrayList<ArrayList<Integer>> tab_for_ln) {
//        puzzle.pprint();
        int res = 0;

//        int edge = puzzle.getEdge();
//        int size = puzzle.getSize();
//        ArrayList<Integer> board = puzzle.getBoard();
////        // modificator for each line/column
//        int addition_edge = 0;
//        // by lines
//
//        for (int e = 0; e < edge; e++) {
//            for (int i = 0; i < edge - 1; i++) {
//                for (int j = i + 1; j < edge; j++) {
//                    if (board.get(i + addition_edge) != 0 & board.get(j + addition_edge) != 0
//                            && j + addition_edge != size - 1 // for skip left_down corner
//                            && board.get(i + addition_edge) <= edge + addition_edge && board.get(i + addition_edge) > addition_edge
//                            && board.get(j + addition_edge) <= edge + addition_edge && board.get(j + addition_edge) > addition_edge
//                            && board.get(i + addition_edge) > board.get(j + addition_edge)) {
//                        res += 2;
//                    }
//                }
//            }
//            addition_edge += edge;
//        }
//        // by columns
//        addition_edge = 0;
//        for (int e = 0; e < edge; e++) {
//            for (int i = 0; i < edge; i++) {
//                for (int j = i + edge; j < edge * edge; j += edge) {
//                    if (j + addition_edge < size
//                            && board.get(i + addition_edge) != 0 & board.get(j + addition_edge) != 0
//                            && j + addition_edge != size - 1 // for skip left_down corner
//                            && tab_for_ln.get(i).contains(board.get(j + addition_edge)) && tab_for_ln.get(i).contains(board.get(i + addition_edge))
//                            && board.get(i + addition_edge) > board.get(j + addition_edge)) {
//                        res += 2;
//                    }
//                }
//            }
//            addition_edge += edge;
//        }
        return res;
    }

    public static int thirdHeuristic() {
        return 3;
    }
}
