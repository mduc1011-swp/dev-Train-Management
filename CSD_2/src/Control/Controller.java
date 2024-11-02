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

/**
 *
 * @author phank
 */
public class Controller {

    validator vali = new validator();
    TrainManager tm = new TrainManager();
    PassengerManager pm = new PassengerManager();
    BookingManager bm = new BookingManager();
    SimpleDateFormat dateFomate = new SimpleDateFormat("dd-MM-yyyy");
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
    public void inOrderSave(Node node, BufferedWriter bw) throws IOException {
        if (node == null) {
            return;
        }

        inOrderSave(node.left, bw); // Traverse the left subtree first

        Train train = (Train) node.info;
        bw.write(train.getTcode() + "| " + train.getName() + "| " + train.getDstation()
                + "| " + train.getAstation() + "| " + train.getDtime() + "| " + train.getSeat()
                + "| " + train.getBooked() + "| " + train.getAtime());
        bw.newLine(); // Visit the node and save it to the file

        inOrderSave(node.right, bw); // Traverse the right subtree
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Node root = null;
            inOrderSave(root, bw); // Use inOrder traversal to save
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
    public void bookTrain(String tcode, String pcode){
        try{
            boolean done = bm.bookRoom(tcode, pcode)
        }
    }
    

}
