/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class BookingList {
    private BookingNode head;

    public BookingList() {
        this.head = null;
    }

    public BookingNode getHead() {
        return head;
    }

    public void addBooking(Booking booking) {
        BookingNode newNode = new BookingNode(booking);
        if (head == null) {
            head = newNode;
        } else {
            BookingNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // Bạn có thể thêm các phương thức khác để quản lý danh sách đặt chỗ nếu cần
}
