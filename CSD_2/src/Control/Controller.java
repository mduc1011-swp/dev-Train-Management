/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Utility.validator;
import Body.*;
import Model.Train;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import LinkedList.*;
import BSTree.*;
import Model.Booking;

/**
 *
 * @author phank
 */
public class Controller {

    validator vali = new validator();
    TrainManager tm = new TrainManager();
    PassengerManager pm = new PassengerManager();
    BookingManager bm = new BookingManager();
    SimpleDateFormat dateFormate = new SimpleDateFormat("dd-MM-yyyy");
//==============================================================================================================
// 1. Train management
//==============================================================================================================
    // 1.1 load data from file

    public void loadTrainFile() throws Exception {
        try {
            tm.loadFromFile("trains.txt");
            System.out.println("Lead successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //1.2 input & add to the tree
    private Train addNewTrain() {
        String tcode = vali.getString("Train Code: ");
        String name = vali.getString("Train Name: ");
        String dstation = vali.getString("Departing station: ");
        String astation = vali.getString("Arriving station: ");
        double dtime = vali.getDouble("Departing time: ", 0.0, 24.0);
        double atime = vali.getDouble("Arrivig time: ", dtime, 24.0);
        int seat = vali.getInt("Number of seat: ", 1, Integer.MAX_VALUE);
        int booked = vali.getInt("Number of booked seat: ", 0, seat);

        Train newTrain = new Train(tcode, name, dstation, astation, dtime, atime, seat, booked);
        return newTrain;
    }

    //1.3 Display data in preorder traversal
    public void displayTrainTree() {
        tm.getTrainTree().preOrder(tm.getTrainTree().getRoot());
    }

    //1.4 save train tree to file by inorder
    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Node root = null;  // Root of the tree should be properly initialized before calling this method
            tm.inOrderSave(root, bw);  // Start in-order traversal and save process
            System.out.println("Data successfully saved to file " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // 1.6 delete by copying
    public void deleteByCopy(String tcode) {
        // delete all related records in booking list
        LinkedList passengerToMove = bm.deleteBookingByTrainCode(tcode);

        // check is passeger list empty
        if (!passengerToMove.isEmpty()) {
            LL_Node passegerNode = passengerToMove.getFirst();

            // traversal the list and call leaveTrain for each passeger
            while (passegerNode != null) {
                String pcode = (String) passegerNode.getInfo();
                bm.leaveTrain(tcode, pcode);
                passegerNode = passengerToMove.getNext(passegerNode);
            }
        }

        // delete by method cpoying
        tm.deleteTrainByCopying(tcode);

        // Display data after deleted
        if (!passengerToMove.isEmpty()) {
            System.out.println("Delete train: " + tcode + ". Passenger with this pcode: ");
            LL_Node passengerNode = passengerToMove.getFirst();
            while (passengerNode != null) {
                System.out.println((String) passengerNode.getInfo());
                passengerNode = passengerToMove.getNext(passengerNode);
                if (passengerNode != null) {
                    System.out.println(", ");
                }
            }
            System.out.println("Please choose a new train");
        } else {
            System.out.println("Deleted train " + tcode + ". No booked passenger found in train. ");
        }
    }
    //1.7 Delete by merging

    public void deleteByMerge(String tcode) {
        // Xóa tất cả booking cho mã phòng tương ứng và nhận danh sách sinh viên đã rời
        LinkedList passengerToMove = bm.deleteBookingByTrainCode(tcode);

        // Kiểm tra xem danh sách sinh viên có trống không
        if (!passengerToMove.isEmpty()) {
            LL_Node passengerNode = passengerToMove.getFirst();

            // Duyệt qua danh sách và gọi leaveRoom cho từng sinh viên
            while (passengerNode != null) {
                String pcode = (String) passengerNode.getInfo();
                bm.leaveTrain(tcode, pcode); // Xử lý rời phòng cho sinh viên
                passengerNode = passengerToMove.getNext(passengerNode);
            }
        }

        // Thực hiện xóa phòng bằng phương thức delete by merging
        tm.deleteTrainByMerging(tcode);

        // In ra thông tin sau khi xóa
        if (!passengerToMove.isEmpty()) {
            System.out.print("Deleted train " + tcode + ". passenger with this pcode: ");
            LL_Node passengerNode = passengerToMove.getFirst();
            while (passengerNode != null) {
                System.out.print((String) passengerNode.getInfo());
                passengerNode = passengerToMove.getNext(passengerNode);
                if (passengerNode != null) {
                    System.out.print(", ");
                }
            }
            System.out.println(". Please choose a new train");
        } else {
            System.out.println("Deleted room " + tcode + ". No booked passengers found in train.");
        }
    }
// ==========================================================================================================
// 2. Passenger management
// ==========================================================================================================
    //2.1 load data from file

    public void loadPassengerFile() {
        try {
            pm.loadFromFile("passegers.txt");
            System.out.println("Load successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //  TEST VER
    //2.3 display data by postorder traversal
    public void displayPassengerTree() {
        pm.getPassengerTree().postOrder(pm.getPassengerTree().getRoot());
    }

// ==========================================================================================================
// 3. Booking management
// ==========================================================================================================
    //3.1 Load data from file
    public void loadBookingFile() {
        try {
            bm.loadFile("Bookings.txt");
            System.out.println("Load successfully!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3.2 Book train
    public void bookTrain(String tcode, String pcode) {
        try {
            boolean done = bm.bookTrain(tcode, pcode);
            if (done) {
                System.out.println("Train " + tcode + " booked successfully for passenger " + pcode + ".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 3.3 display data
    public void displayBookings(LinkedList bookings) {
        LL_Node current = bookings.getFirst(); // get the first node of the booking list
        Booking b;
        String paidDate;

        //Header for display
        System.out.println(String.format("%-10s| %-10s| %-20s| %-20s| %-6s", "TRAIN CODE", "PASSENGER CODE", "BOOKED DATE", "STATUS", "SEAT"));
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

        //traversal throw the booking list
        while (current != null) {
            b = (Booking) current.getInfo();
            try {
                paidDate = dateFormate.format(b.getPaidDate());
            } catch (Exception e) {
                paidDate = "null";
            }
            //print booking details using the existing toScreen method or format it directly
            System.out.printf(b.toString());
            current = bm.getBookingList().getNext(current);
        }
        System.out.println();
    }

    public void displayAllBookigs() {
        displayBookings(bm.getBookingList());
    }

    //3.4 save booking list to file
    public void writeFileBook() {
        try {
            bm.saveToFile("bookings.txt");
            System.out.println("Save successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //3.5 sort by tcode and pcode
    public void sortBookings() {
        try {
            bm.sortBookings();
            System.out.println("Sort successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
