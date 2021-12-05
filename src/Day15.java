import java.io.IOException;
import java.util.*;

public class Day15 {
    public static void main(String[] args) throws IOException {
        //String tmp = "2,1,10,11,0,6";
        String tmp = "2,1,10,11,0,6";
        String[] input = tmp.split(",");

        List<Integer> inputList = new ArrayList<>();
        for (String s : input)
            inputList.add(Integer.parseInt(s));

        Map<Integer, Integer> lastPlace = new HashMap<>();
        int previous = -1;
        for (int i = 0; i < inputList.size(); i++) {
            lastPlace.put(previous, i - 1);
            previous = inputList.get(i);
        }
        //lastPlace.put(previous,inputList.size()-1);
        int turn = inputList.size() - 1;
        while (true) {
            turn++;
            int x;
            if (lastPlace.containsKey(previous)) {
                x = turn - 1 - lastPlace.get(previous);
            } else {
                x = 0;
            }

            //if (turn == 2020 ) {
            if (turn == 30000000) {
                System.out.println(previous);
                break;
            }

            lastPlace.put(previous, turn - 1);
            previous = x;

        }


    }
}
