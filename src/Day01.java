import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day01 {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new File("inputs/input01.txt"))) {
            List<Integer> list = new ArrayList<>();
            while (sc.hasNext()) {
                list.add(Integer.parseInt(sc.nextLine()));
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    for (int k = 0; k < list.size(); k++)
                        if (i != j && j != k && i != k) {
                            int a = list.get(i);
                            int b = list.get(j);
                            int c = list.get(k);
                            if (a + b + c == 2020) {
                                System.out.printf("%d * %d * %d = %d%n", a, b, c, a * b * c);
                                return;
                            }
                        }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();

        }
    }
}
