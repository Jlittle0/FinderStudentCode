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
            list.add(line.split(",")[keyCol], line.split(",")[valCol]);
        }

//        for (int i = 0; i < list; i++)
//            if (list[i] != null)
//                System.out.println(i + " | " + list[i].getKey() + " | " + list[i].getValue());

        br.close();
    }


    public String query(String key){
        return list.findElement(key);
    }


}