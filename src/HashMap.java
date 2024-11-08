public class HashMap {

    private int resizeFactor;
    private KVPair[] map;
    private int currentElements;
    private int array_size;

    public HashMap() {
        resizeFactor = 2;
        array_size = 64000;
        map = new KVPair[array_size];
        currentElements = 0;
    }

    public void add(String key, String value) {
        if (currentElements / array_size >= 0.5)
            resize();
        map[findIndex(map, key)] = new KVPair(key, value);
        currentElements++;
    }

    public String findElement(String key) {
        int index = hash(key);
        while (map[index] != null && !map[index].getKey().equals(key)) {
            index = (index + 1) % array_size;
        }
        return map[index] == null ? "INVALID KEY" : map[index].getValue();
    }

    public void resize() {
        array_size *= resizeFactor;
        KVPair[] temp = new KVPair[array_size];
        for (KVPair k : map)
            if (k != null)
                temp[findIndex(temp, k.getKey())] = k;
        map = temp;
        System.out.println("New resized value: " + map.length);
    }

    public int hash(String s) {
        int numSTR = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            numSTR = ((numSTR << 8) + s.charAt(i)) % array_size;
        }
        return numSTR;
    }

    public int findIndex(KVPair[] list, String key) {
        int index = hash(key);
        while (list[index] != null) {
            index = (index + 1) % array_size;
        }
        return index;
    }

}
