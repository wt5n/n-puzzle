package ru.school21;

import java.util.ArrayList;

public class Placement {

	static void up(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int zeroCoordinateY, int zeroCoordinateX, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard()[zeroCoordinateY][zeroCoordinateX] = tmp.getBoard()[zeroCoordinateY - 1][zeroCoordinateX];
		tmp.getBoard()[zeroCoordinateY - 1][zeroCoordinateX] = 0;
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp, goal));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

	static void down(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int zeroCoordinateY, int zeroCoordinateX, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard()[zeroCoordinateY][zeroCoordinateX] = tmp.getBoard()[zeroCoordinateY + 1][zeroCoordinateX];
		tmp.getBoard()[zeroCoordinateY + 1][zeroCoordinateX] = 0;
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp, goal));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

	static void left(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int zeroCoordinateY, int zeroCoordinateX, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard()[zeroCoordinateY][zeroCoordinateX] = tmp.getBoard()[zeroCoordinateY][zeroCoordinateX - 1];
		tmp.getBoard()[zeroCoordinateY][zeroCoordinateX - 1] = 0;
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp, goal));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

	static void right(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int zeroCoordinateY, int zeroCoordinateX, ArrayList<ArrayList<Integer>> tab_for_ln) {
		Puzzle tmp = puzzle.clone();
		tmp.setPrev(puzzle);
		tmp.getBoard()[zeroCoordinateY][zeroCoordinateX] = tmp.getBoard()[zeroCoordinateY][zeroCoordinateX + 1];
		tmp.getBoard()[zeroCoordinateY][zeroCoordinateX + 1] = 0;
		tmp.setG(puzzle.getG() + 1);
		tmp.setF(Heuristics.manhattanDistance(tmp, goal));
		tmp.setL(Heuristics.linerConflict(tmp, tab_for_ln));
		tmp.setE();
		res.add(tmp);
//		tmp.pprint();
	}

}
