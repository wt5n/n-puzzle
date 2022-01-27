package ru.school21;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

class Main {

	@SneakyThrows
	public static void main(String[] args) {
//		String str = getFileContent();
//
//		if (puzzle.verifyPuzzle()) {
//			System.out.println(puzzle.solvePuzzle());
//		} else {
//			System.out.println("Puzzle is not valid");
//		}
//
//		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
//		System.out.println(arr.get(0).get(0));


		long startTime = System.currentTimeMillis();

		String puz = getFileContent("src/main/resources/input9.txt");
		String[] sss = puz.split("\n");
		int sizeOfArr = Integer.parseInt(sss[0]);
		int[][] start = new int[sizeOfArr][sizeOfArr];
		int z = 0;
		for (String s: Arrays.copyOfRange(sss, 1, sss.length)) {
			for (int i = 0; i < sizeOfArr; i++) {
				start[z][i] = Integer.parseInt(s.split(" ")[i]);
			}
			z++;
		}

		System.out.println(start);

		Puzzle puzzle = new Puzzle(start, sizeOfArr * sizeOfArr, sizeOfArr, 0);
		System.out.println(puzzle);

		//        puzzle.getBoard().set(0, -1);
		//        System.out.println(puzzle.getBoard());

//		int[][] tab_for_ln = new int[puzzle.getEdge()][puzzle.getEdge()];
		// for linearConflict
		ArrayList<ArrayList<Integer>> tab_for_ln = new ArrayList<>(puzzle.getEdge());
//		for(int i=0; i < puzzle.getEdge(); i++) {
//			tab_for_ln.add(new ArrayList());
//		}
//		for (int i = 0; i < puzzle.getEdge(); i++) {
//			int x = -puzzle.getEdge() + i;
//			for (int j = 0; j < puzzle.getEdge(); j++) {
//				x += puzzle.getEdge();
//				tab_for_ln.get(i).add(end.get(x));
//			}
//		}
//		System.out.println(tab_for_ln.get(0).contains(9));


		Puzzle solution = Puzzle.solve(puzzle, get_goal(puzzle.getEdge()), "A*", tab_for_ln);
//
		solution.pprint();
//
		System.out.println("g= " + solution.getG());
//
		long endTime   = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
//
		System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");


//		        Puzzle p = solution;
//		        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
//		        map.add(p.getBoard());
//		//        System.out.println(p.getBoard());
//		        while (p.getPrev() != null) {
//		//            System.out.println(p.getBoard());
//		            map.add(p.getBoard());
//		            p = p.getPrev();
//		        }
//
//		        System.out.println(puzzle.getBoard());
//		        for (int i = map.size() - 1; i > 0; i--) {
//		            System.out.println(map.get(i));
//		        }
	}

	static int[][] get_goal(int edge) {
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