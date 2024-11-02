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
    public void loadFromFile(String filepath) throws FileNotFoundException, IOException, Exception {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line = br.readLine();
        String[] parts;
        Train train = null;

        while (line != null) {
            line = line.trim();

            // Nếu dòng trống, đọc dòng tiếp theo
            if (line.isEmpty()) {
                line = br.readLine();
                continue;
            }

            parts = line.split("\\|\\s*");
            if (parts.length < 8) {
                // Đóng BufferedReader trước khi ném Exception để giải phóng tài nguyên
                br.close();
                throw new Exception("Invalid line: " + line);
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
                    throw new Exception("Invalid line (empty values): " + line);
                }

                // Tạo đối tượng Train và thêm vào cây
                train = new Train(tcode, name, dstation, astation, dtime, atime, seat, booked);
                trainTree.insert(train);

            } catch (NumberFormatException e) {
                // Đóng BufferedReader trước khi ném Exception để giải phóng tài nguyên
                br.close();
                throw new Exception("Invalid number format in line: " + line, e);
            }

            line = br.readLine();
        }

        br.close();
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
