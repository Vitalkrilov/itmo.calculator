import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    private static BigInteger calculate(String str) throws Exception {
        BigInteger currentResult = new BigInteger("0");
        boolean lastOperatorIsPlus = true;
        int crIdx = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                if (i == 0) {
                    if (str.charAt(i) == '+')
                        throw new Exception("'+' at beginning.");
                } else if (i == crIdx)
                    throw new Exception("Two arithmetical operators at once.");

                // Nothing to plus
                if (i == 0) {
                    lastOperatorIsPlus = false;
                    crIdx = 1;
                    continue;
                }

                if (lastOperatorIsPlus)
                    currentResult = currentResult.add(new BigInteger(str.substring(crIdx, i)));
                else
                    currentResult = currentResult.subtract(new BigInteger(str.substring(crIdx, i)));
                crIdx = i + 1;

                lastOperatorIsPlus = (str.charAt(i) == '+');
            } else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                throw new Exception("Invalid character.");
            }
        }

        if (str.length() > 0) {
            if (str.charAt(str.length()-1) == '+' || str.charAt(str.length()-1) == '-')
                throw new Exception("Arithmetical operator at end.");
            else {
                if (lastOperatorIsPlus)
                    currentResult = currentResult.add(new BigInteger(str.substring(crIdx, str.length())));
                else
                    currentResult = currentResult.subtract(new BigInteger(str.substring(crIdx, str.length())));
            }
        }
        return currentResult;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String currentLine = scanner.next().trim();
            try {
                System.out.println(calculate(currentLine));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
