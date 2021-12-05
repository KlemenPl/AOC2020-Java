import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day08 {
    public static void main(String[] args) throws IOException {
        int numOfAnswers = 0;
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input08.txt"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() == 0) {
                    sb = new StringBuilder();
                }
                sb.append(line).append('\n');
            }

            String[] commands = sb.toString().split("\n");

            for(int i=0;i<commands.length;i++){
                String[] newCommands;
                switch (commands[i].split("\\s")[0]){
                    case "nop":
                        newCommands = commands.clone();
                        newCommands[i] = newCommands[i].replace("nop","jmp");
                        execute(newCommands);
                        break;

                    case "jmp":
                        newCommands = commands.clone();
                        newCommands[i] = newCommands[i].replace("jmp","nop");
                        execute(newCommands);
                        break;
                }
            }




        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static void execute(String[] commands) {
        double accumulator = 0;
        int commandIndex = 0;

        boolean terminated = false;
        Set<Integer> set = new HashSet<>();
        while (true) {
            //System.out.println(accumulator);
            if(set.contains(commandIndex)||commandIndex>=commands.length) {
                System.out.println("Infinite loop");
                terminated = set.contains(commandIndex);
                break;
            }
            String[] parts = commands[commandIndex].split("\\s");
            String command = parts[0];
            int num = Integer.parseInt(parts[1]);

            set.add(commandIndex);

            switch (command) {
                case "nop":
                    commandIndex++;
                    break;
                case "acc":
                    accumulator+=num;
                    commandIndex++;
                    break;
                case "jmp":
                    commandIndex+=num;
                    break;
            }


        }

        if(!terminated)
        System.out.println("Executed sucesfully: "+accumulator);
    }
}
