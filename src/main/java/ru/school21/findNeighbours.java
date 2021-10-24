package ru.school21;

import java.util.ArrayList;

public class findNeighbours {

    public static ArrayList<Puzzle> getNeighbourds(Puzzle puzzle) {

        ArrayList<Puzzle> res = new ArrayList<>();
        Puzzle tmp;

        int zeroCoordinate = puzzle.getBoard().indexOf(0);
//        puzzle.pprint();

        // right
        if (!((zeroCoordinate + 1) % puzzle.getEdge() == 0))
         {
            tmp = puzzle.clone();
            tmp.setPrev(puzzle);
//            tmp = new Puzzle(puzzle.getBoard(), puzzle.getSize(), puzzle.getEdge(), puzzle.getG(), puzzle);
            tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate + 1));
            tmp.getBoard().set(zeroCoordinate + 1, 0);
            tmp.setG(puzzle.getG() + 1);
            tmp.setF(Heuristics.manhattanDistance(tmp));
            tmp.setE();
            res.add(tmp);
//            tmp.pprint();
//            System.exit(-1);
        }
        // left
        if (((zeroCoordinate) % puzzle.getEdge() > 0)) {
            tmp = puzzle.clone();
            tmp.setPrev(puzzle);
            tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate - 1));
            tmp.getBoard().set(zeroCoordinate - 1, 0);
            tmp.setG(puzzle.getG() + 1);
            tmp.setF(Heuristics.manhattanDistance(tmp));
            tmp.setE();
            res.add(tmp);
//            tmp.pprint();
        }
        //down
        if (zeroCoordinate + puzzle.getEdge() < puzzle.getSize()) {
            tmp = puzzle.clone();
            tmp.setPrev(puzzle);
            tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate + puzzle.getEdge()));
            tmp.getBoard().set(zeroCoordinate + puzzle.getEdge(), 0);
            tmp.setG(puzzle.getG() + 1);
            tmp.setF(Heuristics.manhattanDistance(tmp));
            tmp.setE();
            res.add(tmp);
//            tmp.pprint();
        }
        // up
        if (zeroCoordinate / puzzle.getEdge() > 0) {
            tmp = puzzle.clone();
            tmp.setPrev(puzzle);
            tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate - puzzle.getEdge()));
            tmp.getBoard().set(zeroCoordinate - puzzle.getEdge(), 0);
            tmp.setG(puzzle.getG() + 1);
            tmp.setF(Heuristics.manhattanDistance(tmp));
            tmp.setE();
            res.add(tmp);
//            tmp.pprint();
        }
        return res;
    }
}
