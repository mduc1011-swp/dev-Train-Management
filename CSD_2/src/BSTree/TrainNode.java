/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Entity.Train;

/**
 *
 * @author phank
 */
public class TrainNode {

    Train info;
    TrainNode left, right;

    public TrainNode() {
    }
    public TrainNode(Train info, TrainNode left, TrainNode right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    public TrainNode(Train x) {
        info = x;
        left = right = null;
    }
    public Train getInfo() {
        return info;
    }
}
