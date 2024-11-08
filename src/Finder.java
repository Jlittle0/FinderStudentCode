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
    public HashMap list;

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!

        list = new HashMap();

        String line;
        while ((line = br.readLine()) != null) {
            // Goes through each line of the CVS file and takes the key and value and adds
            // them in the proper location in the table.
            list.add(line.split(",")[keyCol], line.split(",")[valCol]);
        }

        br.close();
    }


    public String query(String key){
        // Returns the element found through the findElement function in HashMap.
        return list.findElement(key);
    }


}