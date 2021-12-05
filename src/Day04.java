import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new File("inputs/input04.txt"))) {
            StringBuilder sb = new StringBuilder();
            int validPasswords = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.length() == 0) {
                    if (processInput(sb.toString()))
                        validPasswords++;
                    sb = new StringBuilder();
                }
                sb.append(line).append(" ");
            }
            if (processInput(sb.toString()))
                validPasswords++;


            System.out.printf("Number of valid passwords: %d%n", validPasswords);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static boolean processInput(String s) {
        boolean hasCid = false;
        int numberOfCredentials = 0;
        boolean valid = true;
        for (String c : s.split("\\s+")) {
            String[] cred = c.split(":");
            int year;

            switch (cred[0].trim()) {
                case "byr":
                    numberOfCredentials++;
                    year = Integer.parseInt(cred[1]);
                    if (year < 1920 || year > 2002)
                        return false;
                    break;
                case "iyr":
                    numberOfCredentials++;
                    year = Integer.parseInt(cred[1]);
                    if (year < 2010 || year > 2020)
                        return false;
                    break;
                case "eyr":
                    numberOfCredentials++;
                    year = Integer.parseInt(cred[1]);
                    if (year < 2020 || year > 2030)
                        return false;
                    break;
                case "hgt":
                    numberOfCredentials++;
                    boolean hasCM = cred[1].contains("cm");
                    cred[1] = cred[1].replaceAll("(cm|in)", "");
                    int height = Integer.parseInt(cred[1]);
                    if (hasCM) {
                        if (height < 150 || height > 193)
                            return false;
                    } else {
                        if (height < 59 || height > 76)
                            return false;
                    }
                    break;
                case "hcl":
                    numberOfCredentials++;
                    if (!cred[1].matches("\\s*#[0-9a-f]{6}\\s*"))
                        return false;
                    break;
                case "ecl":
                    numberOfCredentials++;
                    if(!cred[1].contains("amb")&&
                            !cred[1].contains("blu")&&
                            !cred[1].contains("brn")&&
                            !cred[1].contains("gry")&&
                            !cred[1].contains("grn")&&
                            !cred[1].contains("hzl")&&
                            !cred[1].contains("oth"))
                        return false;
                    break;
                case "pid":
                    numberOfCredentials++;
                    if(!cred[1].matches("[0-9]{9}"))
                        return false;
                    break;
                case "cid":
                    numberOfCredentials++;
                    hasCid=true;
                        break;
            }
        }


        return numberOfCredentials==8||(numberOfCredentials==7&&!hasCid);
    }
}
