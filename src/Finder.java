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
    public int array_size = 32000;
    public  KVPair[] list;

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!
        // How to read in data: br.readLine().split(",")[index] with the index being col or key.
        // End of the cvs returns null so while loop until null.


        int index = 0;
        String value;
        String key;

        // Iterate through all lines, given the elements, add them to the array while alpha is
        // less than half full;


        int currentElements = 0;
        double alpha;
        list = new KVPair[array_size];

        String line;
        while ((line = br.readLine()) != null) {
            alpha = (double)currentElements / array_size;
            if (alpha >= 0.5) {
                list = newArray(list);
            }
            index = findIndex(list, line.split(",")[keyCol]);
            list[index] = new KVPair(line.split(",")[keyCol], line.split(",")[valCol]);
            currentElements++;
        }

        br.close();
    }

    public KVPair[] newArray(KVPair[] arr) {
        array_size *= 2;
        KVPair[] newArr = new KVPair[array_size];
        for (KVPair p : arr)
            if (p != null)
                newArr[findIndex(arr, p.getKey())] = p;
        return newArr;
    }

    public int findIndex(KVPair[] list, String key) {
        int index = convertVal(key);
        while (list[index] != null) {
            index = (index + 1) % array_size;
        }
        return index;
    }

    public String query(String key){
        int index = convertVal(key);
        while (list[index] != null && !list[index].getKey().equals(key))
            index = (index + 1) % array_size;
        return list[index] == null ? "INVALID KEY" : list[index].getValue();
    }

    public int convertVal(String s) {
        int numSTR = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            numSTR = (shiftLeft(numSTR) + s.charAt(i)) % array_size;
        }
        return numSTR;
    }

    public int shiftLeft(int num) {
        // Multiply by 256 aka radix
        return num << 8;
    }
}