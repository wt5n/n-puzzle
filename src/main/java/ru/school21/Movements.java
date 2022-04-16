package ru.school21;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Movements {

	static String LEFT = "LEFT";
	static String RIGHT = "RIGHT";
	static String UP = "UP";
	static String DOWN = "DOWN";

	static Puzzle up(Puzzle puzzle, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y - 1][x];
		board[y - 1][x] = 0;
		setHeuristicsAndHashCode(tmp, puzzle);
		tmp.setDirection(UP);
		return tmp;
	}

	static Puzzle down(Puzzle puzzle, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y + 1][x];
		board[y + 1][x] = 0;
		setHeuristicsAndHashCode(tmp, puzzle);
		tmp.setDirection(DOWN);
		return tmp;
	}

	static Puzzle left(Puzzle puzzle, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y][x - 1];
		board[y][x - 1] = 0;
		setHeuristicsAndHashCode(tmp, puzzle);
		tmp.setDirection(LEFT);
		return tmp;

	}

	static Puzzle right(Puzzle puzzle, int y, int x) {
		Puzzle tmp = puzzle.clone();
		int[][] board = tmp.getBoard();
		board[y][x] = board[y][x + 1];
		board[y][x + 1] = 0;
		setHeuristicsAndHashCode(tmp, puzzle);
		tmp.setDirection(RIGHT);
		return tmp;
	}

	private static void setHeuristicsAndHashCode(Puzzle tmp, Puzzle puzzle) {
		tmp.setPrev(puzzle);
		tmp.setG(puzzle.getG() + 1);
		tmp.setE();
		if (NPuzzleFlags.manhattan) {
			tmp.setF(Heuristics.manhattanDistance(tmp));
		}
		if (NPuzzleFlags.euclide) {
			tmp.setF(Heuristics.euclideanDistance(tmp));
		}
		if (NPuzzleFlags.linearConflict) {
			tmp.setL(Heuristics.linerConflict(tmp));
		}
		tmp.setHashCodeL(tmp.hashCodeL());
	}
}
