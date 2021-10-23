import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    public static Puzzle start(Puzzle start, String end) {

        ArrayList<Integer> closed = new ArrayList<>(); // states already selected by the program , compared to the solution and expanded
        PriorityQueue<Puzzle> opened = new PriorityQueue<>(Comparator.comparing(Puzzle::getE));

        int iter = 0;

        opened.add(start);
        while (!opened.isEmpty()) {
            Puzzle cur = opened.poll();
            if (cur.toString().equals(end)) {
                System.out.println("Iterations " + iter);
                return cur;
            }
            closed.add(cur.hashCode());
            iter++;
            for (Puzzle e: findNeighbours.getNeighbourds(cur)) {
//                if (closed.contains(e.hashCode()))
//                    System.out.println("!");
                if (!closed.contains(e.hashCode())){
                    opened.add(e);
                }
            }
        }
        return null;
    }
}
