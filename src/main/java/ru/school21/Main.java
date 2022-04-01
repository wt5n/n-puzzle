package ru.school21;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

class Main {

	@SneakyThrows
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		String[] incomingPuzzle = getFileContent("src/main/resources/input8.txt").split("\n");
		int sizeOfArr = Integer.parseInt(incomingPuzzle[0]);
		int[][] initialState = new int[sizeOfArr][sizeOfArr];
		int z = 0;
		for (String s: Arrays.copyOfRange(incomingPuzzle, 1, incomingPuzzle.length)) {
			for (int i = 0; i < sizeOfArr; i++) {
				initialState[z][i] = Integer.parseInt(s.split(" ")[i]);
			}
			z++;
		}
		System.out.println(initialState);

		Puzzle puzzle = new Puzzle(initialState, sizeOfArr * sizeOfArr, sizeOfArr, 0);
		System.out.println(puzzle);

		int[][] goal = getGoal(puzzle.getEdge());
		// печать финальнго состояния
		for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal.length; j++)
				System.out.print(goal[i][j] + " ");
			System.out.println();
		}

		Puzzle solution = Puzzle.solve(puzzle, goal, "A*");
		solution.pprint();

		System.out.println("g= " + solution.getG());
		long endTime   = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}

	// функция генерации финального положения пазла
	static int[][] getGoal(int edge) {
		int[][] arr = new int[edge][edge];
		int top = 0;
		int bottom = edge - 1;
		int left = 0;
		int right = edge - 1;
		int z = 0;
		while (true) {
			if (left > right)
				break;
			for (int i = left; i <= right; i++)
				arr[top][i] = ++z == edge * edge? 0: z;
			if (top > bottom)
				break;
			top++;
			for (int i = top; i <= bottom; i++)
				arr[i][right] = ++z == edge * edge? 0: z;
			right--;
			for (int i = right; i >= left; i--)
				arr[bottom][i] = ++z == edge * edge? 0: z;
			bottom--;
			for (int i = bottom; i >= top; i--)
				arr[i][left] = ++z == edge * edge? 0: z;
			left++;
		}
		return arr;
	}

	@SneakyThrows
	public static String getFileContent(String filePath) {
		InputStream inputStream = new ByteArrayInputStream(new FileInputStream(filePath).readAllBytes());

		return new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining("\n"));
	}

}