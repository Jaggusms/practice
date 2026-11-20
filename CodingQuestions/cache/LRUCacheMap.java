import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheMap<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCacheMap(int capacity) {
        // Initial capacity, load factor (0.75), and true for access-order (instead of insertion-order)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Overrides
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Automatically evicts the eldest entry when size exceeds capacity
        return size() > capacity;
    }
}