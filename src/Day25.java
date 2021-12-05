import java.util.ArrayList;
import java.util.List;


public class Day25 {

    public static List<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        lines.add("15113849");
        lines.add("4206373");
        int cardPub = Integer.parseInt(lines.get(0));
        int doorPub = Integer.parseInt(lines.get(1));

        long v = 1;
        int i = 0;
        while (v != cardPub) {
            v = (7 * v) % 20201227;
            i++;
        }
        System.out.println(i);

        v = 1;
        for (int j = 0; j < i; j++) {
            v = (doorPub * v) % 20201227;
        }
        System.out.println(v);
    }
}

