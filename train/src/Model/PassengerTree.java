/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class PassengerTree {
    private final PassengerNode root;

    public PassengerTree() {
        this.root = null;
    }

    public Passenger search(String pcode) {
        // Phương thức tìm kiếm hành khách theo pcode
        return searchRecursive(root, pcode);
    }

    private Passenger searchRecursive(PassengerNode node, String pcode) {
        if (node == null) {
            return null; // Không tìm thấy
        }

        if (node.getPassenger().getPcode().equals(pcode)) {
            return node.getPassenger(); // Tìm thấy hành khách
        } else if (pcode.compareTo(node.getPassenger().getPcode()) < 0) {
            return searchRecursive(node.getLeft(), pcode); // Tìm bên trái
        } else {
            return searchRecursive(node.getRight(), pcode); // Tìm bên phải
        }
    }

    // Bạn cũng cần các phương thức khác như thêm, xoá, v.v.
}

