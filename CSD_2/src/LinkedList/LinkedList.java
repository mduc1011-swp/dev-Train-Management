package LinkedList;

public class LinkedList {

    Node head, tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = tail = null;
    }

    public Node getFirst() {
        return head;
    }

    public Node getLast() {
        return tail;
    }

    public int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            Node p = head;
            int size = 0;
            while (p != null) {
                size += 1;
                p = p.next;
            }
            return size;
        }
    }

    public Node getNext(Node p) {
        if (p == null || p.next == null) {
            return null;
        }
        return p.next;
    }

    public Node getPrevious(Node p) {
        Node curr = head;
        while (curr != null && curr.next != p) {
            curr = curr.next;
        }
        return curr;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void addFirst(Object o) {
        Node newNode = new Node(o);
        if (isEmpty()) {
            head = tail = newNode;
            } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public Node getByIndex(int index) {
        Node a = head;
        int cnt = 0;
        while (a != null && cnt != index) {
            cnt++;
            a = a.next;
        }
        return a;
    }

    public void insert(int index, Object o) {
        if (index == 0) {
            addFirst(o);
        } else if (index == getSize() - 1) {
            addLast(o);
        } else {
            Node newNode = new Node(o);
            Node prev = getByIndex(index - 1);
            Node curr = getByIndex(index);
            prev.next = newNode;
            newNode.next = curr;
        }
    }

    public void deletePositionK(int k) {
        if (k == 0) {
            removeFirst();
        } else if (k == getSize() - 1) {
            removeLast();
        } else {
            Node prev = getByIndex(k - 1);
            Node curr = getByIndex(k);
            prev.next = curr.next;
            curr.next = null;
        }
    }

    public void remove(Object o) {
        if (isEmpty()) {
            return;
        }
        if (head.info.equals(o)) {
            removeFirst();
        }
        if (tail.info.equals(o)) {
            removeLast();
        } else {
            Node p = head;
            while (p != null) {
                if (p.info.equals(o)) {
                    Node prev = getPrevious(p);
                    prev.next = p.next;
                    p.next = null;
                }
                p = p.next;
            }
        }
    }

    public void removeFirst() {
        if (head == tail) {
            head = tail = null;
        } else {
            Node tmp = head.next;
            head.next = null;
            head = tmp;
        }
    }

    public void removeLast() {
        if (head == tail) {
            head = tail = null;
        } else {
            Node temp = getPrevious(tail);
            temp.next = null;
            tail = temp;
        }
    }

    public void swap(Node p, Node q) {
        Object tmp = p.info;
        p.info = q.info;
        q.info = tmp;
    }

    // Thêm hàm setFirst
    public void setFirst(Node newHead) {
        this.head = newHead; // Cập nhật head thành newHead
        if (newHead == null) {
            tail = null; // Nếu newHead là null, cũng cần thiết lập tail thành null
        }
    }
    // Phương thức xóa nút
    public void delete(Node node) {
        if (head == null || node == null) return;

        // Nếu nút đầu tiên cần xóa
        if (head == node) {
            head = head.next;
            return;
        }

        // Tìm nút trước nút cần xóa
        Node current = head;
        while (current.next != null && current.next != node) {
            current = current.next;
        }

        // Nếu tìm thấy nút cần xóa
        if (current.next == node) {
            current.next = node.next; // Bỏ qua nút cần xóa
        }
    }
    
}
