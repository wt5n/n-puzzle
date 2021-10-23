public class ManhattenDistance {

    public static int getData (Puzzle start) {
        int res = 0;
        for (int i = 0; i < start.getSize(); i++) {
            if (start.getBoard().get(i) != 0) {
                res += (Math.abs((start.getBoard().get(i) - 1) / start.getEdge() - i / start.getEdge()) +
                        Math.abs((start.getBoard().get(i) - 1) % start.getEdge() - i % start.getEdge()));
            }
        }
        return res;
    }
}
