import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Josh Little
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";

    public Finder() {}

    // Large Prime Number
    public final int ARRAY_SIZE = 919913;
    public  ArrayList<Object>[] list;

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!
        // How to read in data: br.readLine().split(",")[index] with the index being col or key.
        // End of the cvs returns null so while loop until null.

        int index = 0;
        Object value;
        list = new ArrayList[ARRAY_SIZE];



        String line;
        while ((line = br.readLine()) != null) {
            index = convertVal(line.split(",")[keyCol]);
//            value = convertVal(line.split(",")[valCol]);
            value = line.split(",")[valCol];

            list[index] = list[index] == null ? new ArrayList<>() : list[index];
            list[index].add(value);
        }

//        for (Object e : list)
//            if (e != null)
//                System.out.println(e);

        br.close();
    }

    public String query(String key){
        String s = list[convertVal(key)] == null ? "INVALID KEY" : (String)list[convertVal(key)].get(0);
        // TODO: Complete the query() function!
        return s;

        // New idea here, maybe append the sum of all the digits of the key onto the end of the value, so that I can just take the last 3 or 4 characters/integer numbers and use that to check whether or not I'm looking for the right thing or whether it's bad.
    }

    public int convertVal(String s) {
        int numSTR = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            numSTR = (shiftLeft(numSTR) + s.charAt(i)) % ARRAY_SIZE;
        }
        return numSTR;
    }

    public int shiftLeft(int num) {
        // Multiply by 256 aka radix
        return num << 8;
    }
}