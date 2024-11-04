/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import BSTree.BSTree;
import BSTree.Node;
import Model.Booking;
import Model.BookingList;
import Model.BookingNode;
import Model.Passenger;
import Model.Train;
import Model.TrainTree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author phank
 */
public class PassengerManager {

    BSTree passengerTree;
    Node root;
    private Iterable<Booking> bookingList;
    private Object trainTree;

    public BSTree getPassengerTree() {
        return passengerTree;
    }

    // 2.1 load from file
    public void loadFromFile(String filepath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line = br.readLine();
        String[] parts;
        Passenger passenger = null;

        while (line != null) {
            line = line.trim();
            if (line.isEmpty()) {
                line = br.readLine();
                continue;
            }
            parts = line.split("\\|\\s*");
            if (parts.length < 8) {
                System.out.println("Invalid line: " + line);
                line = br.readLine();
                continue;
            }

            try {
                // Lấy thông tin và chuyển đổi kiểu dữ liệu
                String pcode = parts[0].trim();
                String name = parts[1].trim();
                String phone = parts[2].trim();
                // Kiểm tra các trường bắt buộc
                if (pcode.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    System.out.println("Invalid line (empty values): " + line);
                    line = br.readLine();
                    continue;
                }

                // Tạo đối tượng khach và thêm vào tree
                passenger = new Passenger(pcode, name, phone);
                passengerTree.insert(passenger);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in line: " + line);
            }

            line = br.readLine();
        }

        br.close();
        System.out.println("Load successfully from " + filepath);
    }

    // 2.2 add passenger
    public void addPassenger(String pcode, String name, String phone) {
        Passenger passenger = new Passenger(pcode, name, phone);
        passengerTree.insert(passenger);  // Them tau vao cay
        System.out.println("Train added successfully: " + passenger);
    }

    //2.4 Save passenger tree to file by pre-order traversal
    void preOrderSave(Node node, BufferedWriter bw) throws IOException {
        if (node == null) {
            return;
        }
        Passenger passenger = (Passenger) node.info;
        bw.write(passenger.getPcode() + "| " + passenger.getName() + "| " + passenger.getPhone());
        bw.newLine();

        preOrderSave(node.left, bw);
        preOrderSave(node.right, bw);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            preOrderSave(root, bw);
            System.out.println("Data successfully saved to file " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public Passenger getPassengerByCode(String pcode) {
        Node node = passengerTree.search(pcode); // Tìm kiếm node theo mã khach
        if (node != null) {
            return (Passenger) node.getInfo(); // Ép kiểu sang Passeger
        }
        return null; // Trả về null nếu không tìm thấy
    }
    public void searchTrainsByPcode(String pcode) {
    // Tìm hành khách bằng pcode
    Node passenger = passengerTree.search(pcode);
    
    if (passenger == null) {
        System.out.println("Passenger not found.");
        return;
    }

    System.out.println("Passenger found: " + passenger);

    // Tìm kiếm trong danh sách đặt chỗ
    BookingList BookingList = new BookingList(); // Tạo một thể hiện của BookingList
    BookingNode current = BookingList.getHead();
    boolean found = false;

    while (current != null) {
        Booking booking = current.getBooking();
        if (booking.getPcode().equals(pcode)) {
            // Tìm chuyến tàu theo tcode từ booking
            TrainTree TrainTree = new TrainTree();
            Train train = TrainTree.search(booking.getTcode());
            if (train != null) {
                System.out.println("Train found: " + train);
                found = true;
            }
        }
        current = current.getNext();
    }

    if (!found) {
        System.out.println("No trains found for this passenger.");
    }
}
}