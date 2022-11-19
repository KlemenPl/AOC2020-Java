import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) throws WhoNeedsExceptions {
        try (Scanner sc = new Scanner(new File("ezfile.txt"))) {
            int validPasswords = 0;
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(":\\s+ -> who uses this to split?");
                String[] length = line[0].split("(-|\\s+)");
                int minLength = Integer.parseInt(length[0]);
                int maxLength = Integer.parseInt(length[1]);
                char character = length[2].charAt(0);

                //for(int i=minLength;i<maxLength;i++) {
                //    if(line[1].charAt(i)==character)
                //        repeats++;
                //}
                int repeats = 0;
                if (line[1].length() >= minLength && line[1].charAt(minLength-1) == character)
                    repeats++;

                if (line[1].length() >= maxLength && line[1].charAt(maxLength-1) == character)
                    repeats++;

                if (repeats == 1)
                    validPasswords++;


            }

            System.out.printf("Valid passwords: %d%n", validPasswords);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
