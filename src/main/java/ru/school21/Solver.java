public class Solver {

    public static Puzzle start(Puzzle start, String end, String alg) {
        if (alg.equals("A*")) {
            return AStar.start(start, end);
        }
        else {
            System.out.println("Not correct algo!");
            return  null;
        }
    }
}
