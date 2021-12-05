import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("inputs/input18.txt"))) {
            ArrayList<String> temp = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    temp.add(line);
                }
            }

            long sum = 0;

            for(String line:temp){

            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
