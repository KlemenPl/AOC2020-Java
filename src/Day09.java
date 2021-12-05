import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day09 {
    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        ArrayList<Long> numbers = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("inputs/input09.txt"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    numbers.add(Long.parseLong(line));
                }
            }


            /* part 1*/
            nm:
            for (int i = 25; i < numbers.size(); i++) {
                for (int j = i - 25; j < i; j++) {
                    for (int k = j; k < i; k++) {
                        if (k != j)
                            if (numbers.get(j)+numbers.get(k)==numbers.get(i)) {
                                continue nm;
                            }
                    }
                }

                // not valid
                System.out.printf("%d: %s%n", i, numbers.get(i));
            }
            /* */

            long number = 248131121;
            for (int i = 0; i < 590; i++) {
                for (int j = 0; j < 590; j++) {
                    if (i == j)
                        continue;
                    ArrayList<Long> list = new ArrayList<>();
                    for (int k = i; k <= j; k++) {
                        list.add(numbers.get(k));
                    }
                    long sum = 0;
                    long min = Integer.MAX_VALUE;
                    long max = Integer.MIN_VALUE;
                    for (long a : list) {
                        sum += a;
                        if(a<min)
                            min=a;
                        if(a>max)
                            max = a;
                    }
                    if (sum == number) {
                        System.out.println(sum);
                        System.out.println(list);
                        System.out.println(min+max);



                    }
                }


            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
