import java.io.File;
import java.io.IOException;
import java.util.*;


public class Day13 {
    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input13.txt"))) {
            int timestamp = Integer.parseInt(sc.nextLine());
            String input = sc.nextLine();
            List<Integer> buses = new ArrayList<>();
            Map<Integer, Integer> busesOffset = new HashMap<>();
            int offset = 0;
            for (String c : input.split(",")) {
                if (!c.equals("x")) {
                    buses.add(Integer.parseInt(c));
                    busesOffset.put(buses.get(buses.size() - 1), offset);
                }
                offset++;

            }

            // Part 1:
            System.out.println(buses);
            int minTimeToWait = Integer.MAX_VALUE;
            int bestBus = 0;

            for (int bus : buses) {
                int time = 0;
                while (time < timestamp) {
                    time += bus;
                }
                if (time < minTimeToWait) {
                    bestBus = bus;
                    minTimeToWait = time;
                }
            }
            minTimeToWait -= timestamp;
            System.out.println(bestBus * minTimeToWait);

            // Part 2:
            System.out.println(busesOffset);
            long currentTimestemp = timestamp;
            long minutesPassed = 0;
            boolean isFound = false;

            while (!isFound) {
                currentTimestemp += busesOffset.get(buses.get(buses.size() - 1));
                minutesPassed += busesOffset.get(buses.get(buses.size() - 1));
                //if((minutesPassed&100000)==0)
                //    System.out.println(minutesPassed);

                isFound = true;
                for (int i = buses.size() - 1; i > 0; i--) {
                    int ID1 = buses.get(i);
                    int ID2 = buses.get(i - 1);

                    long bus1 = minutesPassed%busesOffset.get(ID1);
                    long bus2 = minutesPassed%busesOffset.get(ID2);

                    if (bus2 - bus1 != 1) {
                        isFound = false;
                        break;
                    }
                }

            }

            System.out.println(minutesPassed);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
