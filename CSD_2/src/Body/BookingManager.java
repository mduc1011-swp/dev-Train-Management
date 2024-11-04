/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import LinkedList.*;
import Model.Booking;
import Model.BookingNode;
import Model.Passenger;
import Model.Train;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author phank
 */
public class BookingManager {

    private LL_Node head; // Start of the linked list
    private LinkedList bookingList;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public BookingManager() {
        this.bookingList = new LinkedList(); // get available roomlist
    }

    public LinkedList getBookingList() {
        return bookingList;
    }

    TrainManager tm = new TrainManager();
    PassengerManager pm = new PassengerManager();

    // Method to add a booking to the end of the list
    public void addBookingToEnd(Booking booking) {
        LL_Node newNode = new LL_Node(booking); // Create a new node with the booking

        if (head == null) {
            head = newNode; // If the list is empty, set head to the new node
        } else {
            LL_Node current = head; // Start at the head
            while (current.next != null) {
                current = current.next; // Traverse to the end of the list
            }
            current.next = newNode; // Link the new node to the end of the list
        }
    }

    // 3.1. Load data from file
    public void loadFile(String filepath) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(filepath));
        String line = bf.readLine();
        String[] info;
        Booking booking = null;
        Date odate, paiddate;

        while (line != null) {
            try {
                info = line.split("\\|\\s*");
                String bcode = info[0];
                String pcode = info[1];

                odate = formatter.parse(info[2]);
                paiddate = null;
                if (info[3].equalsIgnoreCase("null") || info[3].isEmpty()) {
                } else {
                    paiddate = formatter.parse(info[3]);
                }

                int seat = Integer.parseInt(info[4]);
                int state = Integer.parseInt(info[5]);

                bookingList.addLast(new Booking(pcode, pcode, odate, paiddate, seat, state));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            line = bf.readLine();
        }
        bf.close();
    }

    //3.4 Save booking list to file 
    
    public void saveToFile(String filepath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        LL_Node p = bookingList.getFirst();
        while (p != null) {
            bw.write(p.getInfo().toString());
            bw.newLine();
            p = bookingList.getNext(p);
        }
        bw.close();
    }

    // 3.2 Book the train
    public boolean bookTrain(String tcode, String pcode) throws Exception {
        Train train = tm.getTrainByCode(tcode);
        if (train == null) {
            throw new Exception("Train does not exist.");
        }

        Passenger passenger = pm.getPassengerByCode(pcode);
        if (passenger == null) {
            throw new Exception("Student does not exist.");
        }

        if (train.getRemainSeat() <= 0) {
            throw new Exception("No available seats in the room.");
        }

        if (isPassengerBook(pcode)) {
            throw new Exception("Student is currently living in another room.");
        }

        // Tạo booking mới
        Booking newBooking = new Booking(tcode, pcode, new Date(), null, 1, 0);
        bookingList.addFirst(newBooking);  // Thêm vào đầu danh sách
        train.setSeat(train.getRemainSeat() - 1);  // Giảm số ghe trống

        return true;  // Trả về thành công
    }

    // Phương thức kiểm tra xem khach da dat tau chua
    private boolean isPassengerBook(String pcode) {
        LL_Node current = bookingList.getFirst();
        while (current != null) {
            Booking booking = (Booking) current.getInfo();
            if (booking.getPcode().equals(pcode) && booking.getState() == 1) {
                return true; // khach da dat tau
            }
            current = bookingList.getNext(current);
        }
        return false; // khach chua dat tau
    }

    // 3.5 Sort bookings by `tcode` first and then by `pcode`
    public void sortBookings() {
        if (head == null) {
            return;  // No sorting needed for an empty list
        }

        LL_Node current, index;
        Booking temp;

        // Perform a bubble sort to sort by `tcode` and then `pcode`
        for (current = head; current.next != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                // Cast info to Booking to access its properties
                Booking currentBooking = (Booking) current.info;
                Booking indexBooking = (Booking) index.info;

                // Compare based on `tcode` and `pcode`
                if (currentBooking.getTcode().compareTo(indexBooking.getTcode()) > 0
                        || (currentBooking.getTcode().equals(indexBooking.getTcode())
                        && currentBooking.getPcode().compareTo(indexBooking.getPcode()) > 0)) {

                    // Swap the bookings
                    temp = currentBooking;
                    current.info = indexBooking;
                    index.info = temp;
                }
            }
        }
        System.out.println("Sort by tcode and pcode complete!");
    }

    //3.6 pay booking by tcode & pcode
    public boolean paidBooking(String tcode, String pcode) throws Exception {
        // Iterate over the booking list to find the relevant booking
        LL_Node current = head;
        while (current != null) {
            Booking booking = (Booking) current.getInfo();  // Cast to Booking to access properties

            // Check if the tcode and pcode match, and if the booking is not already paid
            if (booking.getTcode().equals(tcode) && booking.getPcode().equals(pcode)) {
                if (booking.getState() != 1) {  // Check if booking is unpaid
                    booking.setState(1);  // Mark as paid
                    System.out.println("Booking has been marked as paid.");
                    return true;  // Indicate success
                } else {
                    throw new Exception("Booking is already paid.");
                }
            }
            current = current.next;  // Move to the next node in the list
        }

        // If no booking is found with the given tcode and pcode
        throw new Exception("Booking not found.");
    }

    //function Leave the train
    public boolean leaveTrain(String tcode, String pcode) {
        LL_Node p = bookingList.getFirst();
        Booking b;

        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getTcode().equals(tcode) && b.getPcode().equals(pcode) && b.getState() == 1) {
                b.setState(0);
                b.setOdate(new Date());
                return true;
            }
            p = bookingList.getNext(p);
        }
        return false;
    }

    // function for 1.6 & 7
    // delete booking by tcode and return the list of deleted passenger
    public LinkedList deleteBookingByTrainCode(String tcode) {
        LL_Node current = bookingList.getFirst();
        LinkedList passengerToMove = new LinkedList(); // list contains passenger code

        // find and delete all booking have corresponding tcode
        while (current != null) {
            Booking booking = (Booking) current.getInfo();
            if (booking.getTcode().equals(tcode)) {
                passengerToMove.addLast(booking.getPcode()); // add passenger to the delete list
                LL_Node nodeToDelete = current; // save node to be delete
                current = bookingList.getNext(nodeToDelete); // go to next node
                bookingList.delete(nodeToDelete);
            } else {
                current = bookingList.getNext(current); // nect node
            }
        }
        return passengerToMove;
    }
public void payBooking(String tcode, String pcode) {
    BookingNode current = bookingList.getHead();
    boolean found = false;

    while (current != null) {
        Booking booking = current.getBooking();
        if (booking.getTcode().equals(tcode) && booking.getPcode().equals(pcode)) {
            found = true;
            if (booking.getState() == 1) {
                System.out.println("Đặt chỗ đã được thanh toán.");
                return; // Đã thanh toán rồi
            }
            // Cập nhật trạng thái thanh toán
            booking.setState(1); // Đặt trạng thái là đã thanh toán
            booking.setPaidDate(new Date()); // Cập nhật ngày thanh toán
            System.out.println("Thanh toán thành công cho đặt chỗ.");
            return;
        }
        current = current.getNext();
    }

    if (!found) {
        System.out.println("Không tìm thấy đặt chỗ cho mã tàu và mã hành khách này.");
    }
}

}
