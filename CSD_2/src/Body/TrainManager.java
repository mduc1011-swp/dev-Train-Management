/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import BSTree.BSTree;
import BSTree.Node;
import Model.Train;
import Utility.validator;
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
public class TrainManager {

    Node root;

    BSTree trainTree;

    public TrainManager() {
        this.trainTree = new BSTree();
    }

    public BSTree getTrainTree() {
        return trainTree;
    }

    // 1.1 load data from file trains.txt
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

                // Tạo đối tượng Train và thêm vào tree
                train = new Train(tcode, name, dstation, astation, dtime, seat, booked, atime);
                trainTree.insert(train);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in line: " + line);
            }

            line = br.readLine();
        }

        br.close();
        System.out.println("Load successfully from " + filepath);
    }

    //1.4 Save file 
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
            inOrderSave(root, bw); // Use inOrder traversal to save
            System.out.println("Data successfully saved to file " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    //1.2 input and add 
    public void addTrain(String tcode, String name, String dstation, String astation,
            double dtime, int atime, int seat, int booked) {
        Train train = new Train(tcode, name, dstation, astation, dtime, atime, seat, booked);
        trainTree.insert(train);  // Them tau vao cay
        System.out.println("Train added successfully: " + train);
    }

    //1.6 Delete by tcode by copying
    public void deleteTrainByCopying(String tcode) {
        Node trainNode = trainTree.search(tcode);
        if (trainNode != null) {
            trainTree.deleteNodeByCopy(trainNode);
        }
    }

    //1.7 Delete by pcode by merging
    public void deleteTrainByMerging(String pcode) {
        Node trainNode = trainTree.search(pcode);
        if (trainNode != null) {
            trainTree.deleteNodeByMerging(trainNode);
        }
    }

    // 1.10 count number of trains
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }
    // 1.12 search booked by tcode
    

//funciton for 3.2
    public Train getTrainByCode(String tcode) {
        Node node = trainTree.search(tcode); // Tìm kiếm node theo mã phòng
        if (node != null) {
            return (Train) node.getInfo();
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
