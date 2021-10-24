package ru.school21;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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

		StringBuilder inc = new StringBuilder("");

		Scanner scanner;
		StringBuilder a = new StringBuilder();
		String puz = getFileContent("src/main/resources/test.txt");
		try {
			scanner = new Scanner(new FileReader("src/main/resources/test.txt"));
			while (scanner.hasNext()) {
				a.append(scanner.nextLine());
				a.append(" ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(a);

		ArrayList<String> ss = new ArrayList<>(Arrays.asList(a.toString().split(" ")));
		ArrayList<Integer> start = new ArrayList<>();
		for (String s : ss) {
			start.add(Integer.parseInt(s.trim()));
		}

		System.out.println(start);

		ArrayList<Integer> end = new ArrayList<>();
		for (int i = 1; i < start.size(); i++) {
			end.add(i);
		}
		end.add(0);

		System.out.println(end);

		Puzzle puzzle = new Puzzle(start, start.size(), (int) Math.pow(start.size(), 0.5), 0);
		System.out.println(puzzle);

		//        puzzle.getBoard().set(0, -1);
		//        System.out.println(puzzle.getBoard());

		StringBuilder r = new StringBuilder();
		for (Integer i : end) {
			r.append(i.toString());
		}

		Puzzle solution = Puzzle.solve(puzzle, r.toString(), "A*");

		solution.pprint();

		System.out.println(solution.getG());

		//        Puzzle p = solution;
		//        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
		//        map.add(p.getBoard());
		////        System.out.println(p.getBoard());
		//        while (p.getPrev() != null) {
		////            System.out.println(p.getBoard());
		//            map.add(p.getBoard());
		//            p = p.getPrev();
		//        }
		//
		//        System.out.println(puzzle.getBoard());
		//        for (int i = map.size() - 1; i > 0; i--) {
		//            System.out.println(map.get(i));
		//        }

		// check for valid
	}

	@SneakyThrows
	public static String getFileContent(String filePath) {
		InputStream inputStream = new ByteArrayInputStream(new FileInputStream(filePath).readAllBytes());

		return new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining("\n"));
	}
}