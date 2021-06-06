package ru.school21;

import lombok.Data;

@Data
public class Puzzle {
	private String puzzleString;
	private int timeComplexity;
	private int sizeComplexity;
	private int numberOfMoves;
	private StringBuilder solution;

	public Puzzle(String puzzleString) {
		this.puzzleString = puzzleString;
	}

	public boolean verifyPuzzle() {
		return true;
	}

	public String solvePuzzle() {

		return "Test";
	}

}
