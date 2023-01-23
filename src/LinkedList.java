public class LinkedList<State> {
    private Node<State> head;
    private Node<State> tail;
    private int size;

    /**
     * Adds an item to the back of the Linked List
     *
     * @param item
     * @author Kate Numbers
     */
    public void enqueue(Node<State> item){
        if(tail==null){
            tail = item;
            head = tail;
        }
        else {
            tail.next = item;
            tail = tail.next;
        }
    }

    /**
     * Removes an item from the front of the Linked List
     *
     * @return boolean
     * @author Kate Numbers
     */
    public boolean dequeue() {
        if (head == null) {
            return false;
        }
        head = head.next;
        return true;
    }

    /**
     * Prints the contents of the Linked List
     *
     * @author Kate Numbers
     */
    public void printContents() {
        Node<State> curr = head;
        System.out.print("size:" + getSize() + " ");
        while (curr != null) {
            System.out.print(curr.toString());
            curr = curr.next;
        }
        System.out.println("");
    }

    /**
     * Returns the size of the Linked List. For testing purposes only.
     *
     * @return Integer
     * @author Kate Numbers
     */
    private Integer getSize() {
        Node<State> curr = head;
        Integer size = 0;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }


}
