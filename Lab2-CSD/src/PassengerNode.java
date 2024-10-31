/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ASUS
 */
public class PassengerNode {
    Passenger info;
    PassengerNode left, right;

    public PassengerNode() {
    }

    public PassengerNode(Passenger info, PassengerNode left, PassengerNode right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    public PassengerNode(Passenger x) {
        info = x;
        left = right = null;
    }
}
