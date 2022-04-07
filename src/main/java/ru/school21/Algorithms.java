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

	public static Puzzle aStar(Puzzle start, int[][] goal) {

		Map<Long, ArrayList<Puzzle>> closed = new HashMap<>();
		PriorityQueue<Puzzle> opened = new PriorityQueue<>(Comparator.comparing(Puzzle::getE));

		int iter = 0;
		start.pprint();
		opened.add(start);

		Puzzle goalPuzzle = new Puzzle(goal, start.getEdge() * start.getEdge(), start.getEdge(), 0);

		while (!opened.isEmpty()) {
			Puzzle cur = opened.poll();
			if (isEqualsByCell(cur, goalPuzzle)) {
				System.out.println("Iterations " + iter);
				System.out.println("closed size = " + closed.size());
				return cur;
			}
			iter++;
			if (iter == 4909) {
				System.out.println("here");
			}
			if (Puzzle.isItInClosed(closed, cur, goal)) {
				continue;
			}
			addInClosed(closed, cur);
			checkMoves(cur, goal).stream().filter(e -> !Puzzle.isItInClosed(closed, e, goal)).forEach(opened::add);
		}
		System.out.println(
				"Iterations " + iter + "\n" + "closed size = " + closed.size() + "\n" + "There is no solution");
		return null;
	}

	private static void addInClosed(Map<Long, ArrayList<Puzzle>> closed, Puzzle cur) {
		if (closed.containsKey(cur.getHashCodeL())) {
			//            System.out.println("A");
			closed.get(cur.getHashCodeL()).add(cur);
			//            System.out.println("B");
		} else {
			ArrayList<Puzzle> p = new ArrayList<>();
			p.add(cur);
			closed.put(cur.getHashCodeL(), p);
		}
		//        ArrayList<Puzzle> p = new ArrayList<>();
		//        p.add(cur);
		//        closed.put(cur.getHashCodeL(), p);
	}

	private static ArrayList<Puzzle> checkMoves(Puzzle puzzle, int[][] goal) {

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
			Placement.right(puzzle, res, goal, y, x);
		}
		if (x - 1 >= 0) {
			Placement.left(puzzle, res, goal, y, x);
		}
		if (y + 1 < puzzle.getEdge()) {
			Placement.down(puzzle, res, goal, y, x);
		}
		if (y - 1 >= 0) {
			Placement.up(puzzle, res, goal, y, x);
		}
		return res;
	}
}
