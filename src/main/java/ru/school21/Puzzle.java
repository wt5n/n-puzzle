package ru.school21;

import java.util.PriorityQueue;

import lombok.Data;

@Data
public class Puzzle {
	private String puzzleString;
	private int timeComplexity;
	private int sizeComplexity;
	private int numberOfMoves;
	private StringBuilder solution;
	private PriorityQueue<String> map;
	private String testUnsolve = "# This puzzle is unsolvable\n" + "4\n" + "12  1  4 15 \n" + " 0 13  6  7 \n" +
			" 9  2 11  5 \n" + " 3  8 14 10 ";
	private String testSolve = "# This puzzle is solvable\n" + "4\n" + "10  7  1 12 \n" + "13 14  3 11 \n" +
			" 6  9  8  2 \n" + " 5 15  0  4 ";

	public Puzzle(String puzzleString) {
		this.puzzleString = puzzleString;
	}

	public boolean verifyPuzzle() {
		return true;
	}

	public String solvePuzzle() {
		return "Test";
	}

	private void convertToList() {
//		map = puzzleString;
	}
}
