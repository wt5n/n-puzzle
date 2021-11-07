package ru.school21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Algorithms {

    public static Puzzle aStar(Puzzle start, int[][] goal, ArrayList<ArrayList<Integer>> tab_for_ln) {

        ArrayList<Integer> closed = new ArrayList<>(); // states already selected by the program , compared to the solution and expanded
        PriorityQueue<Puzzle> opened = new PriorityQueue<>(Comparator.comparing(Puzzle::getE));

        int iter = 0;
        start.pprint();

        opened.add(start);
        int x = 0;
        while (!opened.isEmpty()) {
            Puzzle cur = opened.poll();
//            cur.pprint();
            if (Arrays.deepEquals(cur.getBoard(), goal)) {
                System.out.println("Iterations " + iter);
                return cur;
            }
            iter++;
            if (closed.contains(cur.hashCode())) {
                continue;
            }
            closed.add(cur.hashCode());
            checkMoves(cur, goal, tab_for_ln).stream().filter(e -> !closed.contains(e.hashCode())).forEach(opened::add);
        }
        System.out.println("There is no solution");
        return null;
    }

    private static ArrayList<Puzzle> checkMoves(Puzzle puzzle, int[][] goal, ArrayList<ArrayList<Integer>> tab_for_ln) {

        ArrayList<Puzzle> res = new ArrayList<>();

        int zeroCoordinateY = 0;
        int zeroCoordinateX = 0;
        for (int i = 0; i < puzzle.getEdge(); i++) {
            for (int j = 0; j < puzzle.getEdge(); j++) {
                if (puzzle.getBoard()[i][j] == 0) {
                    zeroCoordinateY = i;
                    zeroCoordinateX = j;
                    break;
                }
            }
        }

        if (zeroCoordinateX + 1 < puzzle.getEdge()) {
            Placement.right(puzzle, res, goal, zeroCoordinateY, zeroCoordinateX, tab_for_ln);
        }
        if (zeroCoordinateX - 1 >= 0) {
            Placement.left(puzzle, res, goal, zeroCoordinateY, zeroCoordinateX, tab_for_ln);
        }
        if (zeroCoordinateY + 1 < puzzle.getEdge()) {
            Placement.down(puzzle, res, goal, zeroCoordinateY, zeroCoordinateX, tab_for_ln);
        }
        if (zeroCoordinateY - 1 >= 0) {
            Placement.up(puzzle, res, goal, zeroCoordinateY, zeroCoordinateX, tab_for_ln);
        }
        return res;
    }
}
