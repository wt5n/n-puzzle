package ru.school21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Algorithms {

    public static Puzzle aStar(Puzzle start, String end) {

        ArrayList<Integer> closed = new ArrayList<>(); // states already selected by the program , compared to the solution and expanded
        PriorityQueue<Puzzle> opened = new PriorityQueue<>(Comparator.comparing(Puzzle::getE));

        int iter = 0;

        opened.add(start);
        while (!opened.isEmpty()) {
            Puzzle cur = opened.poll();
            if (cur.toString().equals(end)) {
                System.out.println("Iterations " + iter);
                return cur;
            }
            closed.add(cur.hashCode());
            iter++;
            //                if (closed.contains(e.hashCode()))
            //                    System.out.println("!");
            checkMoves(cur).stream().filter(e -> !closed.contains(e.hashCode())).forEach(opened::add);
        }
        return null;
    }

    private static ArrayList<Puzzle> checkMoves(Puzzle puzzle) {

        ArrayList<Puzzle> res = new ArrayList<>();

        int zeroCoordinate = puzzle.getBoard().indexOf(0);

        if (!((zeroCoordinate + 1) % puzzle.getEdge() == 0)) {
            Placement.right(puzzle, res, zeroCoordinate);
        }
        if (((zeroCoordinate) % puzzle.getEdge() > 0)) {
            Placement.left(puzzle, res, zeroCoordinate);
        }
        if (zeroCoordinate + puzzle.getEdge() < puzzle.getSize()) {
            Placement.down(puzzle, res, zeroCoordinate);
        }
        if (zeroCoordinate / puzzle.getEdge() > 0) {
            Placement.up(puzzle, res, zeroCoordinate);
        }
        return res;
    }
}
