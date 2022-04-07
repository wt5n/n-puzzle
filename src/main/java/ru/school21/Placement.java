package ru.school21;

import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Placement {
	
	static String LEFT = "LEFT";
	static String RIGHT = "RIGHT";
	static String UP = "UP";
	static String DOWN = "DOWN";

	static void up(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y - 1][x];
		board[y - 1][x] = 0;
		setHeuristicsAndHashCode(tmp, puzzle, goal);
		tmp.setDirection(UP);
		res.add(tmp);
		//		tmp.pprint();
	}

	static void down(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y + 1][x];
		board[y + 1][x] = 0;
		setHeuristicsAndHashCode(tmp, puzzle, goal);
		tmp.setDirection(DOWN);
		res.add(tmp);
		//		tmp.pprint();
	}

	static void left(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y][x - 1];
		board[y][x - 1] = 0;
		setHeuristicsAndHashCode(tmp, puzzle, goal);
		tmp.setDirection(LEFT);
		res.add(tmp);
		//		tmp.pprint();
	}

	static void right(Puzzle puzzle, ArrayList<Puzzle> res, int[][] goal, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y][x + 1];
		board[y][x + 1] = 0;
		setHeuristicsAndHashCode(tmp, puzzle, goal);
		tmp.setDirection(RIGHT);
		res.add(tmp);
		//		tmp.pprint();
	}

	private static void setHeuristicsAndHashCode(Puzzle tmp, Puzzle puzzle, int[][] goal) {
		tmp.setPrev(puzzle);
		tmp.setG(puzzle.getG() + 1);
		tmp.setE();
		tmp.setF(Heuristics.manhattanDistance(tmp, goal));
//		tmp.setF(Heuristics.euclideanDistance(tmp, goal));
		tmp.setL(Heuristics.linerConflict(tmp, goal));
		tmp.setHashCodeL(tmp.hashCodeL());
	}
}
