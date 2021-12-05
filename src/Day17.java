import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Day17 {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new File("inputs/input17.txt"))) {
            ArrayList<String> temp = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    temp.add(line);
                }
            }

            char[][] state = new char[temp.size()][temp.get(0).length()];

            for (int i = 0; i < temp.size(); i++) {
                for (int j = 0; j < temp.get(i).length(); j++) {
                    state[i][j] = temp.get(i).charAt(j);
                }
            }

            System.out.println(Arrays.deepToString(state));

            ArrayList<char[][]> states = new ArrayList<>();
            states.add(state.clone());

            for (int c = 0; c < 6; c++) {
                char[][] arr = states.get(c);

                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        int count = countSurrounding(states,i,j,c);
                        if(arr[i][j]=='#'){
                            if(count!=2&&count!=3)
                                state[i][j]='.';
                            else
                                state[i][j]=arr[i][j];
                        } else {
                            if(count==3)
                                state[i][j]='#';
                            else
                                state[i][j]='.';
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static int countSurrounding(List<char[][]> arr, int x, int y, int z) {
        return 0;
    }
}
