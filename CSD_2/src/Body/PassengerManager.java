/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import BSTree.*;
import Model.Passenger;
import Model.Train;
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

    // 2.1 load from file
    public void loadFromFile(String filepath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line = br.readLine();
        String[] parts;
        Train train = null;

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
                String tcode = parts[0].trim();
                String name = parts[1].trim();
                String dstation = parts[2].trim();
                String astation = parts[3].trim();
                double dtime = Double.parseDouble(parts[4].trim());
                int seat = Integer.parseInt(parts[5].trim());
                int booked = Integer.parseInt(parts[6].trim());
                double atime = Double.parseDouble(parts[7].trim());

                // Kiểm tra các trường bắt buộc
                if (tcode.isEmpty() || name.isEmpty() || dstation.isEmpty() || astation.isEmpty()) {
                    System.out.println("Invalid line (empty values): " + line);
                    line = br.readLine();
                    continue;
                }

                // Tạo đối tượng khach và thêm vào tree
                train = new Train(tcode, name, dstation, astation, dtime, seat, booked, atime);
                passengerTree.insert(train);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in line: " + line);
            }

            line = br.readLine();
        }

        br.close();
        System.out.println("Load successfully from " + filepath);
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
}
