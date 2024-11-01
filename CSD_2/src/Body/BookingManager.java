/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import Entity.Booking;
import Entity.Train;
import LinkedList.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

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
                boolean paid = Boolean.parseBoolean(parts[3]);
                Booking booking = new Booking(tcode, pcode, seat);
                booking.setPaidDate();
                addBookingToEnd(booking);
            }
            System.out.println("Load successfully " + filename);
        } catch (IOException e) {
            System.out.println("Load error " + e.getMessage());
        }
    }
    // 3.2 Book the train
    public boolean bookRoom(String tcode, String pcode) throws Exception {
        Train train = tm.getTrainByCode(tcode);
        if (train == null) {
            throw new Exception("Train does not exist.");
        }

        Passenger passenger = sm.getStudentByCode(scode);
        if (student == null) {
            throw new Exception("Student does not exist.");
        }

        if (room.getRemainBeds() <= 0) {
            throw new Exception("No available beds in the room.");
        }

        if (isStudentLiving(scode)) {
            throw new Exception("Student is currently living in another room.");
        }

        // Tạo booking mới
        Booking newBooking = new Booking(rcode, scode, new Date(), null, 1);
        bookingList.addFirst(newBooking);  // Thêm vào đầu danh sách
        room.setBeds(room.getRemainBeds() - 1);  // Giảm số giường trống

        return true;  // Trả về thành công
    }
    
    // Phương thức kiểm tra xem sinh viên có đang sống ở đâu không
    private boolean isStudentLiving(String scode) {
        Node current = bookingList.getFirst();
        while (current != null) {
            Booking booking = (Booking) current.getInfo();
            if (booking.getScode().equals(scode) && booking.getState() == 1) {
                return true; // Sinh viên đang sống trong phòng
            }
            current = bookingList.getNext(current);
        }
        return false; // Sinh viên không sống trong phòng nào
    }  
    
    //3.6 Leave the room
    public boolean leaveRoom(String rcode, String scode) {
        Node p = bookingList.getFirst();
        Booking b;

        while (p != null) {
            b = (Booking) p.getInfo();
            if (b.getRcode().equals(rcode) && b.getScode().equals(scode) && b.getState() == 1) {
                b.setState(0);
                b.setLdate(new Date());
                return true;
            }
            p = bookingList.getNext(p);
        }
        return false;
    }

}
