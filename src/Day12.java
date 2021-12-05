import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    static enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input12.txt"))) {
            ArrayList<String> temp = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    temp.add(line);
                }
            }

            Direction dir = Direction.EAST;
            int posX = 0;
            int posY = 0;

            for (String command : temp) {
                char c = command.charAt(0);
                int argument = Integer.parseInt(command.substring(1));

                if (c != 'L' && c != 'R') {
                    Direction action = Direction.NORTH;
                    switch (c) {
                        case 'N':
                            action = Direction.NORTH;
                            break;
                        case 'S':
                            action = Direction.SOUTH;
                            break;
                        case 'E':
                            action = Direction.EAST;
                            break;
                        case 'W':
                            action = Direction.WEST;
                            break;
                        case 'F':
                            action = dir;
                            break;
                    }
                    switch (action) {
                        case NORTH:
                            posY += argument;
                            break;
                        case SOUTH:
                            posY -= argument;
                            break;
                        case EAST:
                            posX += argument;
                            break;
                        case WEST:
                            posX -= argument;
                            break;
                    }
                } else {
                    if (c == 'L') {
                        while (argument > 0) {
                            switch (dir) {
                                case NORTH:
                                    dir = Direction.WEST;
                                    break;
                                case SOUTH:
                                    dir = Direction.EAST;
                                    break;
                                case EAST:
                                    dir = Direction.NORTH;
                                    break;
                                case WEST:
                                    dir = Direction.SOUTH;
                                    break;
                            }
                            argument -= 90;
                        }
                    } else {
                        while (argument > 0) {
                            switch (dir) {
                                case NORTH:
                                    dir = Direction.EAST;
                                    break;
                                case SOUTH:
                                    dir = Direction.WEST;
                                    break;
                                case EAST:
                                    dir = Direction.SOUTH;
                                    break;
                                case WEST:
                                    dir = Direction.NORTH;
                                    break;
                            }
                            argument -= 90;

                        }
                    }
                }

            }

            System.out.println(Math.abs(posX)+Math.abs(posY));

            posX = posY=0;
            int waypointX = 10;
            int waypointY = 1;

            for (String command : temp) {
                char c = command.charAt(0);
                int argument = Integer.parseInt(command.substring(1));
                switch (c){
                    case 'F':
                        posX += waypointX*argument;
                        posY += waypointY*argument;
                        break;
                    case 'N':
                        waypointY+=argument;
                        break;
                    case 'E':
                        waypointX+=argument;
                        break;
                    case 'S':
                        waypointY-=argument;
                        break;
                    case 'W':
                        waypointX-=argument;
                        break;
                    case 'L':
                        while (argument>0){
                            int t = waypointX;
                            waypointX = -waypointY;
                            waypointY = t;
                            argument-=90;
                        }
                        break;
                    case 'R':
                        while (argument>0){
                            int t = waypointX;
                            waypointX = waypointY;
                            waypointY = -t;
                            argument-=90;
                        }
                        break;
                }

            }

            System.out.println(Math.abs(posX)+Math.abs(posY));

            /*
                action = line[:1]
                arg = int(line[1:])
                if action == 'F':
                    x += wx * arg
                    y += wy * arg
                if action == 'N':
                    wy += arg
                if action == 'E':
                    wx += arg
                if action == 'S':
                    wy -= arg
                if action == 'W':
                    wx -= arg
                if action == 'L':
                    while arg:
                        wx, wy = -wy, wx
                        arg -= 90
                if action == 'R':
                    while arg:
                        wx, wy = wy, -wx
                        arg -= 90
             */


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
