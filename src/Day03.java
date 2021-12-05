import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new File("inputs/input04.txt"))) {
            List<char[]> map = new ArrayList<>();
            while (sc.hasNext()) {
                map.add(sc.nextLine().toCharArray());
            }

            int[] pairs = new int[]{1, 1, 3, 1, 5, 1, 7, 1, 1, 2};

            long answer = 1;

            for (int k = 0; k < pairs.length; k += 2) {
                int a = pairs[k];
                int b = pairs[k + 1];

                int numTrees = 0;
                int xSize = map.get(0).length;
                int x = a;
                for (int i = b; i < map.size(); i+=b) {
                    if (map.get(i)[x % xSize] == '#')
                        numTrees++;
                    x += a;
                }

                System.out.printf("Pair (%d, %d) encountered: %d trees.%n", a, b, numTrees);
                answer*=numTrees;
            }

            System.out.printf("Multiplied together: %d%n",answer);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
