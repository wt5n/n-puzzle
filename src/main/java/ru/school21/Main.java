package ru.school21;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import lombok.SneakyThrows;

class Main {

	@SneakyThrows
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		String path = "src/main/resources/input5.txt";

		Puzzle puzzle = Validator.validate(path);
		System.out.println(puzzle.getBoard());

		int[][] goal = Puzzle.getGoal(puzzle.getEdge());
		// печать финального состояния
		for (int[] ints : goal) {
			for (int j = 0; j < goal.length; j++) {
				System.out.print(ints[j] + " ");
			}
			System.out.println();
		}

		Puzzle solution = Puzzle.solve(puzzle, goal, Algos.A_STAR);
		solution.pprint();

		System.out.println("g= " + solution.getG());
		long endTime = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}
}