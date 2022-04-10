package ru.school21;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import lombok.SneakyThrows;

class Main {

	@SneakyThrows
	public static void main(String[] args) {

		checkFlags(args);
		String path = "src/main/resources/input5.txt";

		String[] strPuzzle = PuzzleUtil.getPuzzleFromFilePath(path);
		Puzzle puzzle = PuzzleUtil.validate(strPuzzle);
		//		if (PuzzleUtil.isSolvable(puzzle)) {
		Puzzle solution = PuzzleUtil.solve(puzzle, AlgoTypes.A_STAR);
		puzzle.printBoard();
		puzzle.printGoal();
		System.out.println("g= " + solution.getG());
		//		}
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print(
				"Execution time is " + formatter.format((System.currentTimeMillis() - NPuzzleFlags.startTime) / 1000d) +
						" seconds");
	}

	private static void checkFlags(String[] args) {
		for (String s : args) {
			if (NPuzzleFlags.path != null && s.matches(".{3,}")) {
				NPuzzleFlags.path = s;
			}
			NPuzzleFlags.startTime = System.currentTimeMillis();
			NPuzzleFlags.euclide = s.equals("-e");
			NPuzzleFlags.manhattan = s.equals("-m");
			NPuzzleFlags.linearConflict = s.equals("-l");
			NPuzzleFlags.help = s.equals("-h");
		}
	}
}