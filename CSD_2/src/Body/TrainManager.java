/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import BSTree.TrainNode;
import BSTree.trainBSTree;
import Entity.Train;

/**
 *
 * @author phank
 */
public class TrainManager {

    trainBSTree trainTree;
//funciton for 3.2

    public Train getTrainByCode(String tcode) {
        TrainNode node = trainTree.searchByTcode(tcode); // Tìm kiếm node theo mã phòng
        if (node != null) {
            return (Train) node.getInfo();
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
