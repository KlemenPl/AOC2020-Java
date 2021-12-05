import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {
    static class Bag {
        Map<String, Integer> contents;

        public Bag() {
            contents = new HashMap<>();
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File("inputs/input07.txt"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                sb.append(line).append('\n');
            }

            Map<String, Bag> map = buildMap(sb.toString().split("\n"));
            System.out.println(findBags(map, new HashSet<>(), "shiny gold"));
            System.out.println(findCost(map, "shiny gold"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Bag> buildMap(String[] lines) {
        Map<String, Bag> validColors = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split("\\scontain\\s");

            String key = parts[0].replace("bags", "").replaceAll("bag", "").trim();

            Bag contains = validColors.getOrDefault(key, new Bag());
            validColors.putIfAbsent(key, contains);

            for (String c : parts[1].split("[,.]")) {
                Matcher m = Pattern.compile(".*(\\d+).*").matcher(c);
                String bag = c.replaceAll("[\\d,.]*", "").replaceAll("bags?", "").trim();
                contains.contents.put(bag, m.matches() ? Integer.parseInt(m.group(1)) : 0);
            }

        }

        return validColors;
    }

    private static int findBags(Map<String, Bag> map, Set<String> checked, String searchColor) {

        if (!map.containsKey(searchColor) || checked.contains(searchColor))
            return 0;

        // traversal
        int count = 0;
        for (String key : map.keySet()) {
            if (map.get(key).contents.containsKey(searchColor)) {
                if (!checked.contains(key))
                    count++;
                count += findBags(map, checked, key);
                checked.add(key);
            }
        }

        return count;
    }

    private static int findCost(Map<String, Bag> map, String searchColor) {

        if (!map.containsKey(searchColor))
            return 0;

        // traversal
        int count = 0;
        Bag bag = map.get(searchColor);
        for (String bagName : bag.contents.keySet()) {
            count += bag.contents.get(bagName);
            count += bag.contents.get(bagName) * findCost(map, bagName);
        }
        return count;
    }
}
