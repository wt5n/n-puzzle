package ru.school21;

public class Heuristics {

    public static int manhattanDistance(Puzzle start) {
        int res = 0;
        for (int i = 0; i < start.getSize(); i++) {
            if (start.getBoard().get(i) != 0) {
                res += (Math.abs((start.getBoard().get(i) - 1) / start.getEdge() - i / start.getEdge()) +
                        Math.abs((start.getBoard().get(i) - 1) % start.getEdge() - i % start.getEdge()));
            }
        }
        return res;
    }

    public static int secondHeuristic() {
        return 2;
    }

    public static int thirdHeuristic() {
        return 3;
    }
}
