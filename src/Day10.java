import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day10 {
    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input10.txt"))) {
            ArrayList<Integer> input = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    input.add(Integer.parseInt(line));
                }
            }


            int max = 0;
            for (int num : input)
                if (num > max)
                    max = num;

            input.add(0);
            input.add(max + 3);
            input.sort(Comparator.naturalOrder());

            int count1 = 0;
            int count3 = 0;

            for (int i = 0; i < input.size() - 1; i++) {
                if (input.get(i + 1) - input.get(i) == 1)
                    count1++;
                else if (input.get(i + 1) - input.get(i) == 3)
                    count3++;
            }

            System.out.println("Part 1: " + count1 * count3);

            Map<Long, Long> map = new HashMap<>();
            map.put(0L,1L);

            for(long num:input){
                long sum = map.getOrDefault(num-1,0L)+
                        map.getOrDefault(num-2, 0L)+
                        map.getOrDefault(num-3, 0L);
                long current = map.getOrDefault(num,0L);
                map.put(num, current+sum);
            }

            System.out.println("Part 2: " + map.get((long)input.get(input.size()-2)));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
