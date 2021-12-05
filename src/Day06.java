import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day06 {
    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input06.txt"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() == 0) {
                    numOfAnswers += getSumAllVotes(sb.toString());
                    sb = new StringBuilder();
                }
                sb.append(line).append('\n');
            }
            numOfAnswers += getSumAllVotes(sb.toString());

            System.out.println(numOfAnswers);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static int getSumVotes(String s) {
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (c != '\n')
                set.add(c);
        }

        return set.size();
    }

    static int getSumAllVotes(String s) {
        if(s.charAt(0)=='\n')
            s=s.substring(1);
        String[] lines = s.split("\n");
        HashSet<Character>[] set = new HashSet[lines.length];


        for (int i = 0; i < lines.length; i++) {
            set[i] = new HashSet<Character>();
            for (char c : lines[i].toCharArray()) {
                set[i].add(c);
            }
        }
        HashSet<Character> res = new HashSet<>(set[0]);

        for (int i = 1; i < set.length; i++) {
            res.retainAll(set[i]);
        }



        return res.size();
    }
}
