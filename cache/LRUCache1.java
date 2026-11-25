import java.util.HashMap;
import java.util.Map;

class LRUCache1 {

    class Node {
        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> map;

    private Node head;
    private Node tail;

    private int capacity;

    public LRUCache1(int capacity) {

        this.capacity = capacity;

        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    // GET operation
    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // Mark as recently used
        moveToHead(node);

        return node.value;
    }

    // PUT operation
    public void put(int key, int value) {

        // Key already exists
        if (map.containsKey(key)) {

            Node node = map.get(key);

            node.value = value;

            moveToHead(node);

            return;
        }

        // Create new node
        Node node = new Node(key, value);

        map.put(key, node);

        addToHead(node);

        // Check capacity
        if (map.size() > capacity) {

            Node lru = removeTail();

            map.remove(lru.key);
        }
    }

    // Add node immediately after head
    private void addToHead(Node node) {

        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // Remove a node from its current position
    private void removeNode(Node node) {

        Node previous = node.prev;
        Node next = node.next;

        previous.next = next;
        next.prev = previous;
    }

    // Move existing node to head
    private void moveToHead(Node node) {

        removeNode(node);

        addToHead(node);
    }

    // Remove least recently used node
    private Node removeTail() {

        Node lru = tail.prev;

        removeNode(lru);

        return lru;
    }
}