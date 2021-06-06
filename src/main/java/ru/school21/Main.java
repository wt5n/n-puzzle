package ru.school21;

import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Puzzle puzzle = new Puzzle(scanner.next());

		if (puzzle.verifyPuzzle()) {
			System.out.println(puzzle.solvePuzzle());
		} else {
			System.out.println("Puzzle is not valid");
		}
	}
}