import java.util.NoSuchElementException;

class  Node{
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
    
}

public class LinkdLiestPractise {
    public static Node linkedList;
    public static Node tail;
    public static void main(String[] args) {
        linkedList = new Node(10);
        linkedList.next = new Node(20);
        linkedList.next.next = new Node(30);
        tail = linkedList.next.next;
        // insertFront(new Node(0));
        // insertEnd(new Node(0));
        print();
        insertAtIndex(2,new Node(25));
        print();
        insertAtIndex(4,new Node(25));
        print();

        
    }

         public static void removeAtIndex(int indexed,Node currentNode) throws IndexOutOfBoundsException{
        if (indexed < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        // this means removeing at zero right
        if (linkedList==null){
            linkedList = currentNode;
            tail = currentNode;
            return;
        }

        // then get the exsiting linked list
        Node temp = linkedList;
        System.out.println(temp.data);
         // then loop the exsiting linked list index-1
        for (int index = 0;index<indexed-1;index++){
            if (temp == null) {
                break;
            }
            temp = temp.next;
            System.out.println(temp.data);
        }
        currentNode.next = temp.next;
        temp.next = currentNode;
        if (currentNode.next == null) {
            tail = currentNode;
        }
     }
    public static void removeFront(){
        if (linkedList == null) {
            throw new NoSuchElementException("LinkedList is empty");
        }

        linkedList = linkedList.next;

        if (linkedList == null) {
            tail = null;
        }
    }
    public static void removeEnd(Node currentNode) throws  NoSuchElementException{
        if (linkedList == null) {
            throw new NoSuchElementException("LinkedList is empty");
        }

        // Only one node
        if (linkedList.next == null) {
            linkedList = null;
            tail = null;
            return;
        }

        Node temp = linkedList;

        // Find second-last node
        while (temp.next.next != null) {
            temp = temp.next;
        }

        // Remove last node
        temp.next = null;

        // Update tail
        tail = temp;
    }

    public static void removeAll(int value) {

        // Remove matching nodes from the beginning
        while (linkedList != null && linkedList.data == value) {
            linkedList = linkedList.next;
        }

        Node temp = linkedList;

        while (temp != null && temp.next != null) {

            if (temp.next.data == value) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        // Update tail
        tail = linkedList;

        while (tail != null && tail.next != null) {
            if (tail.next.data == value) {
                tail.next = tail.next.next;
            } else {
                tail = tail.next;
            }
        }
    }
     public static void insertAtIndex(int indexed,Node currentNode) throws IndexOutOfBoundsException{
        if (indexed < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        // this means inserting at zero right
        if (linkedList==null){
            linkedList = currentNode;
            tail = currentNode;
            return;
        }

        // then get the exsiting linked list
        Node temp = linkedList;
        System.out.println(temp.data);
         // then loop the exsiting linked list index-1
        for (int index = 0;index<indexed-1;index++){
            if (temp == null) {
                break;
            }
            temp = temp.next;
            System.out.println(temp.data);
        }
        currentNode.next = temp.next;
        temp.next = currentNode;
        if (currentNode.next == null) {
            tail = currentNode;
        }
     }
    public static void insertFront(Node currentNode){
        currentNode.next = linkedList;
        linkedList = currentNode;
    }
    public static void insertEnd(Node currentNode){
        if (linkedList==null){
            linkedList = currentNode;
            tail = currentNode;

        }
        tail.next = currentNode;
        tail = currentNode;
    }
    public static void print(Node head){
        
        while (head!=null) {
            System.out.print(head.data+"->");
            head = head.next; 
            
        }
        System.out.println("Null");
    }
    public static void print(){
        Node current = linkedList;
        while (current!=null) {
            System.out.print(current.data+"->");
            current = current.next; 
            
        }
        System.out.println("Null");
    }
}
