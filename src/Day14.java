import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new File("inputs/input14.txt"))) {
            ArrayList<String> temp = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() != 0) {
                    temp.add(line);
                }
            }

            //long[] memory = new long[640000];
            //List<Long> memory = new ArrayList<>();
            Map<String, Long> memory = new HashMap<>();
            List<String> buffer = new ArrayList<>();
            String mask = "";
            Pattern memPattern = Pattern.compile("^mem\\[(\\d+)]\\s=\\s(\\d+)$");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < "0X000101X00X000XX10100X11010XXX01001".length(); i++)
                sb.append("0");

            String zeros = sb.toString();

            getAllCombinations("X001X", 0, buffer);
            buffer.clear();

            System.out.println(buffer);

            for (String input : temp) {
                if (input.startsWith("mask")) {
                    mask = input.split(" ")[2];
                } else {
                    Matcher matcher = memPattern.matcher(input);

                    matcher.find();
                    int memoryOffset = Integer.parseInt(matcher.group(1));
                    long value = Long.parseLong(matcher.group(2));

                    /* Part 1:
                    String binaryValue = Long.toBinaryString(value).trim();
                    binaryValue = zeros.substring(binaryValue.length()) + binaryValue;


                    StringBuilder res = new StringBuilder();

                    for (int i = mask.length() - 1; i >= 0; i--) {

                        if (mask.charAt(i) == 'X') {
                            res.append(binaryValue.charAt(i));
                        } else {
                            res.append(mask.charAt(i));
                        }


                    }
                    binaryValue = res.reverse().toString();
                    */

                    StringBuilder res = new StringBuilder();
                    String memoryString = Long.toBinaryString(Long.parseLong(matcher.group(1))).trim();
                    memoryString = zeros.substring(memoryString.length()) + memoryString;
                    buffer.clear();

                    for (int i = mask.length() - 1; i >= 0; i--) {

                        if (mask.charAt(i) == 'X') {
                            res.append(mask.charAt(i));
                        } else if (mask.charAt(i) == '1') {
                            res.append(mask.charAt(i));
                        } else {
                            res.append(memoryString.charAt(i));
                        }
                    }
                    memoryString = res.reverse().toString();

                    buffer.clear();
                    getAllCombinations(memoryString, 0, buffer);
                    for (String memoryAddress : buffer) {
                        memory.put(memoryAddress, value);
                    }


                }
            }

            long sum = 0;
            //for (String s : buffer) sum += Long.parseLong(s, 2);
            for (Long l : memory.values()) sum += l;

            System.out.println(sum);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static void getAllCombinations(String input, int index, List<String> arr) {
        for (int i = index; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {

                String firstPart = input.substring(0, i);
                String secondPart = input.substring(i + 1);

                getAllCombinations(firstPart + '0' + secondPart, i, arr);
                getAllCombinations(firstPart + '1' + secondPart, i, arr);

                return;
            }
        }

        arr.add(input);

    }

    static long getSum(List<String> arr) {
        long sum = 0;
        for (String n : arr)
            sum += Long.parseLong(n, 2);
        return sum;
    }
}
