package ru.school21;

import java.util.*;

public class Algorithms {

    public static Puzzle aStar(Puzzle start, int[][] goal, ArrayList<ArrayList<Integer>> tab_for_ln) {

//        ArrayList<Integer> closed = new ArrayList<>(); // states already selected by the program , compared to the solution and expanded
//        ArrayList<Puzzle> closed = new ArrayList<>();
        Map<Long, ArrayList<Puzzle>> closed = new HashMap<>();
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
            if (Puzzle.isItInClosed(closed, cur)) {
                continue;
            }
            addInClosed(closed, cur);
            checkMoves(cur, goal, tab_for_ln).stream().filter(e -> !Puzzle.isItInClosed(closed, e)).forEach(opened::add);
        }
        System.out.println("There is no solution");
        return null;
    }

    private static void addInClosed(Map<Long, ArrayList<Puzzle>> closed, Puzzle cur) {
        if (closed.containsKey(cur.getHashCodeL())) {
            ArrayList<Puzzle> p = closed.get(cur.getHashCodeL());
            p.add(cur);
        } else {
            ArrayList<Puzzle> p = new ArrayList<>();
            p.add(cur);
            closed.put(cur.getHashCodeL(), p);
        }
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
