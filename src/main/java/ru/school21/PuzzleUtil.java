package ru.school21;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PuzzleUtil {

	static ArrayList<String> res = new ArrayList<>();
	static int size = 0;

	public static Puzzle validate(String[] puz) {
		String[] correctPuzzle = check(puz);
		int[][] initialState = new int[size][size];
		int z = 0;
		for (String s : correctPuzzle) {
			for (int i = 0; i < size; i++) {
				initialState[z][i] = Integer.parseInt(s.split(" ")[i]);
			}
			z++;
		}
		Puzzle.goal = Puzzle.calculateGoal(size);
		return new Puzzle(initialState, size * size, size, 0);
	}

	public static boolean isSolvable(Puzzle puzzle) {

		int puzzleInv = getInv(puzzle.getBoard());
		int goalInv = getInv(Puzzle.goal);

		//		if (size % 2 == 0) { // even grid
		//			if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
		//				return parity % 2 == 0;
		//			} else { // blank on even row; counting from bottom
		//				return parity % 2 != 0;
		//			}
		//		} else { // odd grid
		return puzzleInv % 2 == goalInv % 2;
		//		}
	}

	public static Puzzle solve(Puzzle puzzle, AlgoTypes alg) {
		if (alg.equals(AlgoTypes.A_STAR)) {
			return Algorithms.aStar(puzzle);
		} else {
			throw new NotFoundException("No implementation for this algorithm");
		}
	}

	@SneakyThrows
	public static String[] getPuzzleFromFilePath(String filePath) {
		InputStream inputStream = new ByteArrayInputStream(new FileInputStream(filePath).readAllBytes());

		return (new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining("\n"))).split("\n");
	}

	private static int getInv(int[][] arr) {
		int[] puzzle = Stream.of(arr).flatMapToInt(IntStream::of).toArray();
		int parity = 0;

		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == 0) {
				continue;
			}
			for (int j = i + 1; j < puzzle.length; j++) {
				if (puzzle[i] > puzzle[j] && puzzle[j] != 0) {
					parity++;
				}
			}
		}
		return parity;
	}

	static int getXPosition(int[][] puzzle) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (puzzle[i][j] == 0)
					return i;
			}
		}
		throw new RuntimeException("");
	}

	private static String[] check(String[] puzzle) {
		for (String s : puzzle) {
			if (s.matches("[0-9]")) {
				setSize(puzzle, s);
			} else if (s.startsWith("#") || s.isEmpty()) {
			} else if (s.matches("[0-9 ]+")) {
				addString(puzzle, s);
			} else if (s.contains("#")) {
				addString(puzzle, s.substring(0, s.indexOf("#")));
			} else {
				throw new NotFoundException("Какой то конченный аргумент");
			}
		}
		String[] result = new String[size];
		return res.toArray(result);
	}

	private static void setSize(String[] puzzle, String s) {
		if (size > 0) {
			throw new RuntimeException("Неверный аргумент");
		}
		size = Integer.parseInt(s);
		if (size > puzzle.length - 1) {
			throw new RuntimeException("wrong size of n-puzzle");
		}
	}

	private static void addString(String[] puzzle, String s) {
		String[] split = s.split(" ");
		if (split.length == size) {
			res.add(s);
		} else if (size == 0) {
			setSize(puzzle, s);
		} else {
			throw new RuntimeException("Mda");
		}
	}
}
