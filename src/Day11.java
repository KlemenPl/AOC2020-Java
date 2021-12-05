import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day11 {
    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input11.txt"))) {
            ArrayList<String> temp = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    temp.add(line);
                }
            }

            char[][] seats = new char[temp.size()][temp.get(0).length()];

            for (int i = 0; i < temp.size(); i++) {
                for (int j = 0; j < temp.get(i).length(); j++) {
                    seats[i][j] = temp.get(i).charAt(j);
                }
            }

            int seatChanges = 0;


            do {
                seatChanges = 0;
                char[][] newSeats = new char[seats.length][seats[0].length];
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                         int   count = getNumberOfOccupied(seats, j, i);
                        switch (seats[i][j]) {
                            case 'L':
                                if (count == 0) {
                                    newSeats[i][j] += '#';
                                    seatChanges++;
                                } else
                                    newSeats[i][j] += 'L';
                                break;
                            case '#':
                                if (count >= 5) {
                                    newSeats[i][j] += 'L';
                                    seatChanges++;
                                } else
                                    newSeats[i][j] += '#';
                                break;
                            case '.':
                                newSeats[i][j] += '.';
                                break;
                        }
                    }
                }
                seats = newSeats;
            } while (seatChanges > 0);

            int numOccupied = 0;
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j] == '#')
                        numOccupied++;
                }
            }
            System.out.println(numOccupied);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /* Part 1
    static int getNumberOfOccupied(char[][] array, int x, int y) {
        int count = 0;


        if (x > 0) {

            if (array[x - 1][y] == '#')
                count++;

            if (y < array[0].length - 1)
                if (array[x - 1][y + 1] == '#')
                    count++;

            if (y > 0)
                if (array[x - 1][y - 1] == '#')
                    count++;
        }

        if (x < array.length - 1) {

            if (array[x + 1][y] == '#')
                count++;

            if (y < array[0].length - 1)
                if (array[x + 1][y + 1] == '#')
                    count++;

            if (y > 0)
                if (array[x + 1][y - 1] == '#')
                    count++;
        }

        if (y < array[0].length - 1)
            if (array[x][y + 1] == '#')
                count++;

        if (y > 0)
            if (array[x][y - 1] == '#')
                count++;


        return count;
    }
     */

    static int getNumberOfOccupied(char[][] array, int x, int y) {
        int count = 0;


        // going right
        for (int i = x+1; i < array[y].length; i++) {
            if (array[y][i] == '#') {
                count++;
                break;
            }

            if(array[y][i]=='L')
                break;

        }

        // going left
        for (int i = x - 1; i >= 0; i--) {
            if (array[y][i] == '#') {
                count++;
                break;
            }
            if(array[y][i]=='L')
                break;
        }

        // going down
        for (int i = y+1; i < array.length; i++) {
            if (array[i][x] == '#') {
                count++;
                break;
            }
            if(array[i][x]=='L')
                break;
        }

        // going up
        for (int i = y - 1; i >=0; i--) {
            if (array[i][x] == '#') {
                count++;
                break;
            }
            if(array[i][x]=='L')
                break;
        }

        // going down right
        for(int i=x+1, j=y+1; i<array[y].length&&j<array.length;i++,j++){
            if(array[j][i]=='#'){
                count++;
                break;
            }
            if(array[j][i]=='L'){
                break;
            }
        }

        // going up right
        for(int i=x+1, j=y-1; i<array[y].length&&j>=0;i++,j--){
            if(array[j][i]=='#'){
                count++;
                break;
            }
            if(array[j][i]=='L'){
                break;
            }
        }

        // going down left
        for(int i=x-1, j=y+1; i>=0&&j<array.length;i--,j++){
            if(array[j][i]=='#'){
                count++;
                break;
            }
            if(array[j][i]=='L'){
                break;
            }
        }

        // going up left
        for(int i=x-1, j=y-1; i>=0&&j>=0;i--,j--){
            if(array[j][i]=='#'){
                count++;
                break;
            }
            if(array[j][i]=='L'){
                break;
            }
        }

        return count;
    }

}
