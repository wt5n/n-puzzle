import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main (String[] args) {

        StringBuilder inc = new StringBuilder("");

        Scanner scanner = null;
        String a = "";
        try {
            scanner = new Scanner(new FileReader("src/input4.txt"));
            while (scanner.hasNext()) {
                a += scanner.nextLine();
                a += " ";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(a);

        ArrayList<String> ss = new ArrayList<>(Arrays.asList(a.split(" ")));
        ArrayList<Integer> start = new ArrayList<Integer>();
        for (String s:ss)
            start.add(Integer.parseInt(s.trim()));

        System.out.println(start);

        ArrayList<Integer> end = new ArrayList<Integer>();
        for (int i = 1; i < start.size(); i++)
            end.add(i);
        end.add(0);

        System.out.println(end);

        Puzzle puzzle = new Puzzle(start, start.size(), (int) Math.pow(start.size(), 0.5), 0);
        System.out.println(puzzle.toString());

//        puzzle.getBoard().set(0, -1);
//        System.out.println(puzzle.getBoard());

        StringBuilder r = new StringBuilder();
        for (Integer i:end)
            r.append(i.toString());

        Puzzle solution = Solver.start(puzzle, r.toString(), "A*");

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
}
