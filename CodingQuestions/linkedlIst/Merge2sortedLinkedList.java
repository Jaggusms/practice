class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class Merge2sortedLinkedList {

    // Create Linked List from an array
    public static Node createList(int[] arr) {

        Node head = new Node(arr[0]);
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Merge Two Sorted Linked Lists
    public static Node mergeTwoLists(Node list1, Node list2) {

        Node dummy = new Node(-1);
        Node current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.value <= list2.value) {
                current.next = new Node(list1.value);
                list1 = list1.next;
            } else {

                current.next = new Node(list2.value);
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        }

        if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }

    // Print Linked List
    public static void printList(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // Input arrays
        int[] arr1 = {1, 3, 6};
        int[] arr2 = {1, 4, 7, 10, 11};

        // Create input linked lists
        Node list1 = createList(arr1);
        Node list2 = createList(arr2);

        System.out.println("List 1:");
        printList(list1);

        System.out.println("List 2:");
        printList(list2);

        // Merge
        Node result = mergeTwoLists(list1, list2);

        System.out.println("Merged List:");
        printList(result);
    }
}