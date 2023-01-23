public class Node<State> {
    State element;
    Node<State> next;
    public Node(State element) {
        this(element,null);
    }
    public Node(State element, Node<State> n) {
        this.element = element;
        this.next = n;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}