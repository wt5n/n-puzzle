package ru.school21;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import lombok.SneakyThrows;

class Main {

	@SneakyThrows
	public static void main(String[] args) {

		checkFlags(args);
		if (NPuzzleFlags.help || args.length < 1) {
			System.out.println("How to use: -[flags] [pathToFile] \n" + "-h - help\n" + "-e - euclidean distance\n" +
					"-m - manhattan distance\n" + "-l - linear conflict\n" +
					"If there is no flag Greedy search will be used");
			System.exit(1);
		}

		String[] strPuzzle = PuzzleUtil.getPuzzleFromFilePath(NPuzzleFlags.path);
		Puzzle puzzle = PuzzleUtil.validate(strPuzzle);
		if (PuzzleUtil.isSolvable(puzzle)) {
			Puzzle solution = PuzzleUtil.solve(puzzle, AlgoTypes.A_STAR);
			System.out.println("Number of moves = " + solution.getG());
		} else {
			System.out.println("Unsolvable");
			System.exit(-1);
		}
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print(
				"Execution time is " + formatter.format((System.currentTimeMillis() - NPuzzleFlags.startTime) / 1000d) +
						" seconds");
	}

	private static void checkFlags(String[] args) {
		for (String s : args) {
			if (NPuzzleFlags.path == null && s.matches(".{3,}")) {
				NPuzzleFlags.path = s;
				NPuzzleFlags.sequence = true;
			}
			NPuzzleFlags.startTime = System.currentTimeMillis();
			if (s.equals("-e")) {
				NPuzzleFlags.euclide = true;
			}
			if (s.equals("-m")) {
				NPuzzleFlags.manhattan = true;
			}
			if (s.equals("-l")) {
				NPuzzleFlags.linearConflict = true;
			}
			if (s.equals("-h")) {
				NPuzzleFlags.help = true;
			}
		}
	}
}