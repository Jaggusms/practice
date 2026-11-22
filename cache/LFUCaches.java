// package cache;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.Map;

// public class LFU {
//     public static HashMap<String, Integer> cache = new HashMap<>();
//     public static final Integer maxSize=3;
//     public static void main(String[] args) {
//         LFU cacheAlgo = new LFU();
//         cacheAlgo.addCache("a");
//         cacheAlgo.addCache("b");
//         cacheAlgo.addCache("b");
//         cacheAlgo.addCache("c");
//         cacheAlgo.addCache("c");
//         // cacheAlgo.addCache("c");
//         // cacheAlgo.addCache("d");

//     }
//     public boolean removeCache(){
//         HashMap<Integer, String > newCache= new HashMap<>();

//         for (Map.Entry<String,Integer> entry: cache.entrySet()){
//             newCache.put(entry.getValue(), entry.getKey());
//         }

//         ArrayList<Integer> couters = new ArrayList<>(cache.values());
//         couters.sort(Comparator.comparingInt(a->a));
//         // couters.sort(Comparator.comparingInt(Integer::intValue));

//         // // Using a 2-argument lambda (beware of potential integer overflow with a - b)
//         // couters.sort((a, b) -> a - b);

//         // // Safer alternative using Integer.compare (avoids overflow)
//         // couters.sort((a, b) -> Integer.compare(a, b));

//         // // Or even simpler:
//         // couters.sort(Integer::compareTo);
//         System.out.println(cache);
//         cache.remove(newCache.get(couters.get(0)));
//         System.out.println(cache);
//         return  true;

//     }
//     public boolean addCache(String cacheValue){
//         if (cache.size() >= maxSize){
//             removeCache();
//             // this.removeCache();
//         }
//         cache.put(cacheValue, cache.getOrDefault(cacheValue,0)+1);
//         System.out.println(cache);
//         return true;
//     }


// }
import java.util.HashMap;
import java.util.LinkedHashSet;

class LFUCache {

    private final int capacity;

    // Stores key -> value
    private final HashMap<Integer, Integer> cache;

    // Stores key -> frequency
    private final HashMap<Integer, Integer> frequency;

    // Stores frequency -> keys in access order
    private final HashMap<Integer, LinkedHashSet<Integer>> freqMap;

    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequency = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFrequency = 0;
    }

    // GET method
    public int get(int key) {

        if (!cache.containsKey(key)) {
            return -1;
        }

        updateFrequency(key);

        return cache.get(key);
    }

    // PUT method
    public void put(int key, int value) {

        if (capacity <= 0) {
            return;
        }

        // Existing key
        if (cache.containsKey(key)) {
            cache.put(key, value);
            updateFrequency(key);
            return;
        }

        // Remove LFU key if capacity exceeds
        if (cache.size() >= capacity) {

            LinkedHashSet<Integer> keys = freqMap.get(minFrequency);

            int lfuKey = keys.iterator().next();

            keys.remove(lfuKey);

            if (keys.isEmpty()) {
                freqMap.remove(minFrequency);
            }

            cache.remove(lfuKey);
            frequency.remove(lfuKey);
        }

        // Insert new key
        cache.put(key, value);
        frequency.put(key, 1);

        freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);

        minFrequency = 1;
    }

    // Update frequency
    private void updateFrequency(int key) {

        int oldFrequency = frequency.get(key);

        LinkedHashSet<Integer> oldKeys = freqMap.get(oldFrequency);

        oldKeys.remove(key);

        if (oldKeys.isEmpty()) {

            freqMap.remove(oldFrequency);

            if (minFrequency == oldFrequency) {
                minFrequency++;
            }
        }

        int newFrequency = oldFrequency + 1;

        frequency.put(key, newFrequency);

        freqMap.computeIfAbsent(newFrequency, k -> new LinkedHashSet<>()).add(key);
    }

    public void display() {
        System.out.println("Cache: " + cache);
        System.out.println("Frequency: " + frequency);
    }
}

public class LFUCaches {

    public static void main(String[] args) {

        LFUCache cache = new LFUCache(2);

        cache.put(1, 100);
        cache.put(2, 200);

        cache.get(1);

        cache.display();

        cache.put(3, 300);

        cache.display();
    }
}