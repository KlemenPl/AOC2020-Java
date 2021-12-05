import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day19p1 {
        public static List<String> lines;

    static {
        try {
            lines = Files.readAllLines(Paths.get("inputs/input19.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Map<Integer, String> rules = new HashMap<>();

        public static void main(String[] args) {
            int i = 0;
            for (i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.isEmpty()) {
                    i++;
                    break;
                }

                String[] parts = line.split(": ");
                rules.put(Integer.parseInt(parts[0]), parts[1]);
            }

            String regex = "^" + getRegex(0) + "$";

            int cnt = 0;
            while (i < lines.size()) {
                String line = lines.get(i);

                if (line.matches(regex)) { cnt++; }

                i++;
            }
            System.out.println(cnt);
        }

        private static Map<Integer, String> memo = new HashMap<>();

        public static String getRegex(int rule) {
            if (memo.containsKey(rule)) { return memo.get(rule); }

            String ruleS = rules.get(rule);
            if (ruleS.contains("\"")) { return ruleS.replaceAll("\"", ""); }

            StringBuilder sb = new StringBuilder("(");
            String[] parts = ruleS.split(" ");
            for (String part : parts) {
                if (Character.isDigit(part.charAt(0))) {
                    sb.append(getRegex(Integer.parseInt(part)));
                } else if (part.equals("|")) {
                    sb.append('|');
                } else {
                    throw new RuntimeException(part);
                }
            }
            sb.append(')');

            memo.put(rule, sb.toString());
            return sb.toString();
        }

}
