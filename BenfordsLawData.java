import java.util.*;

/**
 * Java program to experimentally output Benford's Law data
 */

public class BenfordsLawData {
    public static void main(String args[]) {
        int SAMPLE = Integer.parseInt(args[0]);
        String leadingDigit = "1";
        int digitCount = 1;
        Random rand = new Random();
        HashMap<String, Integer> data = new HashMap<String, Integer>();

        // Loop to collect randomized leading digits
        for (int i=0; i<SAMPLE; i++) {
            String normalDistValueToString = getNormalValue(rand, SAMPLE);
            leadingDigit = getLeadingDigit(normalDistValueToString);

            if (data.containsKey(leadingDigit)) {
                digitCount = data.get(leadingDigit) + 1;
                data.put(leadingDigit, digitCount);
            }
            else {
                digitCount = 1;
                data.put(leadingDigit, digitCount);
            }
        }
        outputData(data, SAMPLE);
    }

    /** Return random normal distribution value as a string */
    public static String getNormalValue(Random rand, int sample) {
        double randStandardDeviation = (double) rand.nextInt(sample);
        double normalDistValue = Math.abs(rand.nextGaussian()) * randStandardDeviation;
        String normalDistValueToString = String.valueOf(normalDistValue);
        return normalDistValueToString;
    }

    /** Given an input digit string, return the first non-zero leading digit */
    public static String getLeadingDigit(String digitString) {
        String leadingDigit = "1";
        for (int j = 0; j < digitString.length(); j++) {
            if (digitString.charAt(j) != '0' && digitString.charAt(j) != '.') {
                leadingDigit = "" + digitString.charAt(j);
                break;
            }
        }
        return leadingDigit;
    }

    /** Print array data to stdout to be read by the flask app */
    public static void outputData(HashMap<String, Integer> data, int sample) {
        ArrayList<Double> output = new ArrayList<Double>();
        for (String s : data.keySet()) {
            output.add((double) data.get(s) / sample);
        }
        System.out.print(output);
    }
}
