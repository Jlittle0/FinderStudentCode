public class HashMap {

    private int resizeFactor;
    private KVPair[] map;
    private int currentElements;
    private int array_size;

    public HashMap() {
        resizeFactor = 2;
        // prime number that doesn't share every single factor possible with the radix
        array_size = 27457;
        map = new KVPair[array_size];
        currentElements = 0;
    }

    public void add(String key, String value) {
        // If the array is less than 50% full, add the element in a valid location
        // otherwise, resize array and then add element.
        if ((double)currentElements / array_size >= 0.5)
            resize();
        map[findIndex(map, key)] = new KVPair(key, value);
        currentElements++;
    }

    public String findElement(String key) {
        // Finds the corresponding value of a given key
        int index = hash(key);
        // Uses hashed key value to get initial position and then runs through the map in
        // a linear manner until it finds desired result (matching keys) or an empty space.
        while (map[index] != null && !map[index].getKey().equals(key)) {
            index = (index + 1) % array_size;
        }
        return map[index] == null ? "INVALID KEY" : map[index].getValue();
    }

    public void resize() {
        // Resizes the array whenever the previous size was 50% full so that linear shifting
        // to find an open space is faster.
        array_size *= resizeFactor;
        KVPair[] oldMap = map;
        map = new KVPair[array_size];
        // creates a new map and rehashes all the old elements and moves them to new map.
        for (KVPair k : oldMap)
            if (k != null) {
                add(k.getKey(), k.getValue());
            }
    }

    public int hash(String s) {
        // Multipying by the radix (256) and adding char value then mod arrSize
        int numSTR = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            numSTR = ((numSTR << 8) + s.charAt(i)) % array_size;
        }
        return numSTR;
    }

    public int findIndex(KVPair[] list, String key) {
        // Finds the next valid index by hashing the key and then moving to the right by
        // one until an empty space is found.
        int index = hash(key);
        while (list[index] != null) {
            index = (index + 1) % array_size;
        }
        return index;
    }

}
