/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */

public class BookingNode {
    private Booking booking;
    private BookingNode next; // Liên kết đến nút tiếp theo

    public BookingNode(Booking booking) {
        this.booking = booking;
        this.next = null;
    }

    public Booking getBooking() { return booking; }
    public BookingNode getNext() { return next; }
    public void setNext(BookingNode next) { this.next = next; }
}
