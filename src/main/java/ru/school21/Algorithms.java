package ru.school21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Algorithms {

    public static Puzzle aStar(Puzzle start, String end, ArrayList<ArrayList<Integer>> tab_for_ln) {

        ArrayList<Integer> closed = new ArrayList<>(); // states already selected by the program , compared to the solution and expanded
        PriorityQueue<Puzzle> opened = new PriorityQueue<>(Comparator.comparing(Puzzle::getE));

        int iter = 0;
        start.pprint();

        opened.add(start);
        int x = 0;
        while (!opened.isEmpty()) {
            iter++;
            Puzzle cur = opened.poll();
            if (cur.toString().equals(end)) {
                System.out.println("Iterations " + iter);
                return cur;
            }


            if (iter == 600000) {
                System.out.println("Here");
            }
//            if (opened.contains(cur))
//                System.out.println("Here");
//            }
//            if (cur.toString().equals("1301113915874141265210"))
//                System.out.println("Here");
//            if (iter == 20000) {
//                System.out.println("Here");
//                opened.
//            }

            if (closed.contains(cur.hashCode())) {
                continue;
            }



//            cur.pprint();
//            System.out.println(cur.getE());
//            System.out.println(cur.getF());



            closed.add(cur.hashCode());
            checkMoves(cur, tab_for_ln).stream().filter(e -> !closed.contains(e.hashCode())).forEach(opened::add);
        }
        System.out.println("There is no solution");
        return null;
    }

    private static ArrayList<Puzzle> checkMoves(Puzzle puzzle, ArrayList<ArrayList<Integer>> tab_for_ln) {

        ArrayList<Puzzle> res = new ArrayList<>();

//        System.out.println("****************************");
//        puzzle.pprint();
//        System.out.println(puzzle.getE());
//        if (puzzle.getE() == 50)
//            System.exit(-1);

        int zeroCoordinate = puzzle.getBoard().indexOf(0);

        if (!((zeroCoordinate + 1) % puzzle.getEdge() == 0)) {
            Placement.right(puzzle, res, zeroCoordinate, tab_for_ln);
        }
        if (((zeroCoordinate) % puzzle.getEdge() > 0)) {
            Placement.left(puzzle, res, zeroCoordinate, tab_for_ln);
        }
        if (zeroCoordinate + puzzle.getEdge() < puzzle.getSize()) {
            Placement.down(puzzle, res, zeroCoordinate, tab_for_ln);
        }
        if (zeroCoordinate / puzzle.getEdge() > 0) {
            Placement.up(puzzle, res, zeroCoordinate, tab_for_ln);
        }
        return res;
    }
}
