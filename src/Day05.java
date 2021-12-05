import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day05 {

    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new File("inputs/input05.txt"))) {
            int highest = 0;
            ArrayList<String> list = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                list.add(line);

                int id = getID(line);
                if (id > highest)
                    highest = id;
            }

            System.out.printf("Highest ID: %d%n", highest);

            System.out.printf("Your seat: %d%n", getSeatID(list));


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static int getID(String s) {
        int i = 0;
        int lower = 0, higher = 127;
        for (i = 0; i < 7; i++) {
            int[] res = search(lower, higher, s.charAt(i));
            lower = res[0];
            higher = res[1];
        }
        int row = lower;

        lower = 0;
        higher = 7;
        for (; i < s.length(); i++) {
            int[] res = search(lower, higher, s.charAt(i));
            lower = res[0];
            higher = res[1];
        }
        int column = lower;

        return row * 8 + column;
    }

    static int[] search(int lower, int higher, char c) {
        if (c == 'B' || c == 'R') {
            // upper half
            return new int[]{(lower + higher + 1) / 2, higher};

        } else {
            // lower half
            return new int[]{lower, (lower + higher) / 2};
        }
    }

    private static int getSeatID(List<String> boardingPasses) {
        Set<Integer> set = new HashSet<>();
        for (String pass : boardingPasses) {
            set.add(getID(pass));
        }

        for (int i = 0; i < 1000; i++) {
            if (set.contains(i - 1) && set.contains(i + 1) && !set.contains(i))
                return i;
        }

        return 0;
    }


}
