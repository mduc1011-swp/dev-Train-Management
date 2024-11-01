/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import LinkedList.*;
import Model.Booking;
import Model.Passenger;
import Model.Train;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author phank
 */
public class BookingManager {

    private Node head; // Start of the linked list
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
        Node newNode = new Node(booking); // Create a new node with the booking

        if (head == null) {
            head = newNode; // If the list is empty, set head to the new node
        } else {
            Node current = head; // Start at the head
            while (current.next != null) {
                current = current.next; // Traverse to the end of the list
            }
            current.next = newNode; // Link the new node to the end of the list
        }
    }

    // 3.1. Load data from file
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String tcode = parts[0];
                String pcode = parts[1];
                int seat = Integer.parseInt(parts[2]);
                int paid = Integer.parseInt(parts[3]);
                Booking booking = new Booking(tcode, pcode, seat);
//                booking.setPaidDate();
                addBookingToEnd(booking);
            }
            System.out.println("Load successfully " + filename);
        } catch (IOException e) {
            System.out.println("Load error " + e.getMessage());
        }
    }

    //3.4 Save booking list to file 
    public void saveToFile(String filepath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        Node p = bookingList.getFirst();
        while (p != null) {
            bw.write(p.getInfo().toString());
            bw.newLine();
            p = bookingList.getNext(p);
        }
        bw.close();
    }

    // 3.2 Book the train
    public boolean bookRoom(String tcode, String pcode) throws Exception {
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
        Booking newBooking = new Booking(tcode, pcode, 0);
        bookingList.addFirst(newBooking);  // Thêm vào đầu danh sách
        train.setSeat(train.getRemainSeat() - 1);  // Giảm số ghe trống

        return true;  // Trả về thành công
    }

    // Phương thức kiểm tra xem khach da dat tau chua
    private boolean isPassengerBook(String pcode) {
        Node current = bookingList.getFirst();
        while (current != null) {
            Booking booking = (Booking) current.getInfo();
            if (booking.getPcode().equals(pcode) && booking.getPaidDate() == 1) {
                return true; // khach da dat tau
            }
            current = bookingList.getNext(current);
        }
        return false; // khach chua dat tau
    }

    //3.6 Leave the train
    public boolean leaveTrain(String tcode, String pcode) {
        Node p = bookingList.getFirst();
        Booking b;

        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getTcode().equals(tcode) && b.getPcode().equals(pcode) && b.getPaidDate() == 1) {
                b.setPaidDate(0);
                b.setOdate(new Date());
                return true;
            }
            p = bookingList.getNext(p);
        }
        return false;
    }

}
