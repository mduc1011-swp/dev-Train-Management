package LinkedList;

import Model.Booking;

public class LinkedList {

    LL_Node head;
    LL_Node tail;

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

    public LL_Node getFirst() {
        return head;
    }

    public LL_Node getLast() {
        return tail;
    }

    public int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            LL_Node p = head;
            int size = 0;
            while (p != null) {
                size += 1;
                p = p.next;
            }
            return size;
        }
    }

    public LL_Node getNext(LL_Node p) {
        if (p == null || p.next == null) {
            return null;
        }
        return p.next;
    }

    public LL_Node getPrevious(LL_Node p) {
        LL_Node curr = head;
        while (curr != null && curr.next != p) {
            curr = curr.next;
        }
        return curr;
    }

    public void addLast(Object o) {
        LL_Node newNode = new LL_Node(o);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void addFirst(Object o) {
        LL_Node newNode = new LL_Node(o);
        if (isEmpty()) {
            head = tail = newNode;
            } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public LL_Node getByIndex(int index) {
        LL_Node a = head;
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
            LL_Node newNode = new LL_Node(o);
            LL_Node prev = getByIndex(index - 1);
            LL_Node curr = getByIndex(index);
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
            LL_Node prev = getByIndex(k - 1);
            LL_Node curr = getByIndex(k);
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
            LL_Node p = head;
            while (p != null) {
                if (p.info.equals(o)) {
                    LL_Node prev = getPrevious(p);
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
            LL_Node tmp = head.next;
            head.next = null;
            head = tmp;
        }
    }

    public void removeLast() {
        if (head == tail) {
            head = tail = null;
        } else {
            LL_Node temp = getPrevious(tail);
            temp.next = null;
            tail = temp;
        }
    }

    public void swap(LL_Node p, LL_Node q) {
        Object tmp = p.info;
        p.info = q.info;
        q.info = tmp;
    }

    // Thêm hàm setFirst
    public void setFirst(LL_Node newHead) {
        this.head = newHead; // Cập nhật head thành newHead
        if (newHead == null) {
            tail = null; // Nếu newHead là null, cũng cần thiết lập tail thành null
        }
    }
    // Phương thức xóa nút
    public void delete(LL_Node node) {
        if (head == null || node == null) return;

        // Nếu nút đầu tiên cần xóa
        if (head == node) {
            head = head.next;
            return;
        }

        // Tìm nút trước nút cần xóa
        LL_Node current = head;
        while (current.next != null && current.next != node) {
            current = current.next;
        }

        // Nếu tìm thấy nút cần xóa
        if (current.next == node) {
            current.next = node.next; // Bỏ qua nút cần xóa
        }
    }

    public void add(Booking booking) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
