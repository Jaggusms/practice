import java.util.LinkedHashMap;

class LRUCache {

    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    // GET method
    public int get(int key) {

        if (!cache.containsKey(key)) {
            return -1;
        }

        // Move accessed key to the latest position
        int value = cache.remove(key);
        cache.put(key, value);

        return value;
    }

    // PUT method
    public void put(int key, int value) {

        // If key already exists, remove it first
        cache.remove(key);

        // Insert as most recently used
        cache.put(key, value);

        // Remove least recently used if capacity exceeds
        if (cache.size() > capacity) {
            Integer firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey);
        }
    }

    public void display() {
        System.out.println(cache);
    }
}

public class LRUCaches {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);

        cache.display();

        cache.get(1);

        cache.display();

        cache.put(4, 400);

        cache.display();
    }
} {
    
}
