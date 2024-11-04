/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class TrainTree {
    private TrainNode root;

    public TrainTree() {
        this.root = null;
    }

    // Phương thức thêm Train vào cây
    public void insert(Train train) {
        root = insertRec(root, train);
    }

    private TrainNode insertRec(TrainNode root, Train train) {
        if (root == null) {
            root = new TrainNode(train);
            return root;
        }

        if (train.getTcode().compareTo(root.getTrain().getTcode()) < 0) {
            root.left = insertRec(root.left, train);
        } else if (train.getTcode().compareTo(root.getTrain().getTcode()) > 0) {
            root.right = insertRec(root.right, train);
        }

        return root;
    }

    // Phương thức tìm kiếm Train theo tcode
    public Train search(String tcode) {
        return searchRec(root, tcode);
    }

    private Train searchRec(TrainNode root, String tcode) {
        if (root == null || root.getTrain().getTcode().equals(tcode)) {
            return root != null ? root.getTrain() : null;
        }

        if (tcode.compareTo(root.getTrain().getTcode()) < 0) {
            return searchRec(root.left, tcode);
        }

        return searchRec(root.right, tcode);
    }

    // Phương thức để lấy root (đầu) của cây
    public TrainNode getRoot() {
        return root;
    }
}
