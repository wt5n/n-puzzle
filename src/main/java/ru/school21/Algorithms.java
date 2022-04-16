package ru.school21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static ru.school21.Puzzle.isEqualsByCell;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Algorithms {

	public static Puzzle aStar(Puzzle start) {

		Map<Long, ArrayList<Puzzle>> closed = new HashMap<>();
		PriorityQueue<Puzzle> opened = new PriorityQueue<>(Comparator.comparing(Puzzle::getE));

		int iter = 0;
		opened.add(start);

		Puzzle goalPuzzle = new Puzzle(Puzzle.goal, start.getEdge() * start.getEdge(), start.getEdge(), 0);

		while (!opened.isEmpty()) {
			Puzzle cur = opened.poll();
			if (isEqualsByCell(cur, goalPuzzle)) {
				if (NPuzzleFlags.sequence) {
					Puzzle.printSeq(cur);
					System.out.println();
				}
				System.out.println("Iterations " + iter);
				System.out.println("opened size = " + opened.size());
				System.out.println("closed size = " + closed.size());
				return cur;
			}
			iter++;
			if (Puzzle.isItInClosed(closed, cur)) {
				continue;
			}
			addInClosed(closed, cur);
			checkMoves(cur).stream().filter(e -> !Puzzle.isItInClosed(closed, e)).forEach(opened::add);
		}
		return null;
	}

	private static void addInClosed(Map<Long, ArrayList<Puzzle>> closed, Puzzle cur) {
		if (closed.containsKey(cur.getHashCodeL())) {
			closed.get(cur.getHashCodeL()).add(cur);
		} else {
			ArrayList<Puzzle> p = new ArrayList<>();
			p.add(cur);
			closed.put(cur.getHashCodeL(), p);
		}
	}

	private static ArrayList<Puzzle> checkMoves(Puzzle puzzle) {

		ArrayList<Puzzle> res = new ArrayList<>();

		int y = 0;
		int x = 0;
		for (int i = 0; i < puzzle.getEdge(); i++) {
			for (int j = 0; j < puzzle.getEdge(); j++) {
				if (puzzle.getBoard()[i][j] == 0) {
					y = i;
					x = j;
					break;
				}
			}
		}

		if (x + 1 < puzzle.getEdge()) {
			res.add(Movements.right(puzzle, y, x));
		}
		if (x - 1 >= 0) {
			res.add(Movements.left(puzzle, y, x));
		}
		if (y + 1 < puzzle.getEdge()) {
			res.add(Movements.down(puzzle, y, x));
		}
		if (y - 1 >= 0) {
			res.add(Movements.up(puzzle, y, x));
		}
		return res;
	}
}
