package ru.school21;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

class Main {

	@SneakyThrows
	public static void main(String[] args) {
		String str = getFileContent();
		Puzzle puzzle = new Puzzle(str);

		if (puzzle.verifyPuzzle()) {
			System.out.println(puzzle.solvePuzzle());
		} else {
			System.out.println("Puzzle is not valid");
		}
	}

	@SneakyThrows
	public static String getFileContent() {
		InputStream inputStream = new ByteArrayInputStream(new FileInputStream("src/main/resources/test.txt").readAllBytes());

		return new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining("\n"));
	}
}