package ru.school21;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
				int x = Integer.parseInt(s.split(" ")[i]);
				if (x >= size * size || x < 0) {
					throw new RuntimeException("Значение пазла больше максимально возможного или отрицательное");
				}
				initialState[z][i] = x;
			}
			z++;
		}
		Puzzle.goal = Puzzle.calculateGoal(size);
		return new Puzzle(initialState, size * size, size, 0);
	}

	public static boolean isSolvable(Puzzle puzzle) {

		int puzzleInv = getInv(puzzle.getBoard(), puzzle.getEdge());
		int goalInv = getInv(Puzzle.goal, puzzle.getEdge());

		return puzzleInv % 2 == goalInv % 2;
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

	private static int getInv(int[][] arr, int edge) {
		List<Integer> puzzle = convertSnail2DArrayToArray(arr, edge);
		int parity = 0;

		for (int i = puzzle.size() - 1; i >= 1; i--) {
			if (puzzle.get(i) == 0) {
				continue;
			}
			for (int j = i - 1; j >= 0; j--) {
				if (puzzle.get(j) != 0 && puzzle.get(i) < puzzle.get(j)) {
					parity++;
				}
			}
		}
		return parity;
	}

	private static List<Integer> convertSnail2DArrayToArray(int[][] arr, int edge) {
		List<Integer> res = new ArrayList<>();
		int top = 0;
		int bottom = edge - 1;
		int left = 0;
		int right = edge - 1;
		int z = 0;
		while (true) {
			if (left > right) {
				break;
			}
			for (int i = left; i <= right; i++) {
				res.add(arr[top][i]);
			}
			if (top > bottom) {
				break;
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				res.add(arr[i][right]);
			}
			right--;
			for (int i = right; i >= left; i--) {
				res.add(arr[bottom][i]);
			}
			bottom--;
			for (int i = bottom; i >= top; i--) {
				res.add(arr[i][left]);
			}
			left++;
		}
		return res;
	}

	private static String[] check(String[] puzzle) {
		for (String s : puzzle) {
			if (s.matches("\\d")) {
				setSize(puzzle, s);
			} else if (s.startsWith("#") || s.isEmpty()) {
			} else if (s.matches("[\\d ]+")) {
				addString(puzzle, s);
			} else if (s.contains("#")) {
				addString(puzzle, s.substring(0, s.indexOf("#")));
			} else {
				throw new NotFoundException("Файл не прошёл проверку");
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
			throw new RuntimeException("Неверный размер пазла");
		}
	}
}
