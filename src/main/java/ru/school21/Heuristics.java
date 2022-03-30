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

    public static int linerConflict(Puzzle puzzle, int[][] goal) {
//        puzzle.pprint();
        int res = 0;

        int edge = puzzle.getEdge();
        int[][] board = puzzle.getBoard();
        boolean flag;
        ArrayList<Integer> correctLine = new ArrayList<>();
        // by lines
        for (int e = 0; e < edge; e++) {
            correctLine = getCorrectLineForLinerConflict(goal, e, true);
            flag = false;
            for (int i = 0; i < edge - 1 && !flag; i++) {
                for (int j = i + 1; j < edge && !flag; j++) {
                    if ((i != edge - 1 && j != edge - 1) // skip right down corner
                         && thereIsConflict(board[e][i], board[e][j], correctLine, board[e])) {
                        res += 2;
                        flag = true;
                    }
                }
            }
        }
        // by columns
//        for (int e = 0; e < edge; e++) {
//            correctLine = getCorrectLineForLinerConflict(goal, e, false);
//            flag = false;
//            for (int i = 0; i < edge - 1 && !flag; i++) {
//                for (int j = i + 1; j < edge && !flag; j++) {
//                    if ((i != edge - 1 && j != edge - 1) // skip right down corner
//                            && thereIsConflict(board[i][e], board[j][e], correctLine, board[i])) {
//                        res += 2;
//                        flag = true;
//                    }
//                }
//            }
//        }
        return res;
    }

    private static boolean thereIsConflict(int a, int b, ArrayList<Integer> correctLine, int[] board) {
        if (a != 0 && b != 0 && correctLine.contains(a) && correctLine.contains(b)) {
            int shift = correctLine.indexOf(a) > correctLine.indexOf(b) ? 1 : 0;
            int i;
            for (i = 0; i < board.length; i++) {
                if (board[i] == a) {
                    break;
                }
            }
            int j;
            for (j = 0; j < board.length; j++) {
                if (board[j] == b) {
                    break;
                }
            }
            return (i > j && shift == 0) || (i < j && shift == 1);
        }
        return false;
    }

    private static ArrayList<Integer> getCorrectLineForLinerConflict(int[][] map, int e, boolean isVertical) {
        ArrayList<Integer> correctLine = new ArrayList<>();
        if (isVertical) {
            for (int i = 0; i < map.length; i++) {
                correctLine.add(map[e][i]);
            }
        } else {
            for (int i = 0; i < map.length; i++) {
                correctLine.add(map[i][e]);
            }
        }
        return correctLine;
    }

    public static int euclideanDistance(Puzzle puzzle, int[][] goal) {
        int res = 0;
        for (int y1 = 0; y1 < puzzle.getEdge(); y1++) {
            for (int x1 = 0; x1 < puzzle.getEdge(); x1++) {
                if (puzzle.getBoard()[y1][x1] == 0)
                    continue;
//                System.out.print(puzzle.getBoard()[y1][x1] + " ");
                for (int y2 = 0; y2 < puzzle.getEdge(); y2++) {
                    for (int x2 = 0; x2 < puzzle.getEdge(); x2++) {
                        if (puzzle.getBoard()[y1][x1] == goal[y2][x2]) {
                            res += Math.round(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
