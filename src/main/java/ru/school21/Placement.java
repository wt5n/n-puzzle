package ru.school21;

import java.util.ArrayList;

public class Placement {

	static void up(Puzzle puzzle, ArrayList<Puzzle> res, int zeroCoordinate, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate - puzzle.getEdge()));
		tmp.getBoard().set(zeroCoordinate - puzzle.getEdge(), 0);
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

	static void down(Puzzle puzzle, ArrayList<Puzzle> res, int zeroCoordinate, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate + puzzle.getEdge()));
		tmp.getBoard().set(zeroCoordinate + puzzle.getEdge(), 0);
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

	static void left(Puzzle puzzle, ArrayList<Puzzle> res, int zeroCoordinate, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate - 1));
		tmp.getBoard().set(zeroCoordinate - 1, 0);
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

	static void right(Puzzle puzzle, ArrayList<Puzzle> res, int zeroCoordinate, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		//            tmp = new Puzzle(puzzle.getBoard(), puzzle.getSize(), puzzle.getEdge(), puzzle.getG(), puzzle);
		tmp.getBoard().set(zeroCoordinate, puzzle.getBoard().get(zeroCoordinate + 1));
		tmp.getBoard().set(zeroCoordinate + 1, 0);
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

}
