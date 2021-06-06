package ru.school21;

public class Puzzle {
	private String puzzleString;

	public Puzzle(String puzzleString) {
		this.puzzleString = puzzleString;
	}

	public void setPuzzleString(String puzzleString) {
		this.puzzleString = puzzleString;
	}

	public boolean verifyPuzzle() {
		return true;
	}

	public String solvePuzzle() {
		StringBuilder solve = new StringBuilder("Test");

		return solve.toString();
	}
}
