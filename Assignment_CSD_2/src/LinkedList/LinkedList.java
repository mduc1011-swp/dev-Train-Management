package LinkedList;

public class LinkedList<E extends Comparable<E>> {

    private static class Node<E> {

        // type E is use to pass object like train or passenger
        private E info;
        private Node<E> next;

        public Node() {

        }

        public Node(E info, Node<E> next) {
            this.info = info;
            this.next = next;
        }

        public Node(E info) {
            this.info = info;
            this.next = null;
        }

        public E getInfo() {
            return info;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setInfo(E info) {
            this.info = info;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    Node<E> head, tail;

    public void MyList() {
        head = tail = null;
    }

    // check linked is empty or not
    public boolean isEmpty() {
        return head == null;
    }

    // clear linked list
    public void clear() {
        head = tail = null;
    }

    // Add to tail of linked list
    public void addToTail(E x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            tail.next = q;
            tail = q;
        }
    }

    public void traverse() {
        Node<E> p = head;
        while (p != null) {
            System.out.println(p.info);
            p = p.next;
        }
        System.out.println();

    }

    // Add to head of lineked list
    public void addToHead(E x) {
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
        }
    }
    public E removeFirst(){
        
    }
    public E getFirst(){
        return head.info;
    }

}
