/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class PassengerNode {
    private Passenger passenger;
    private PassengerNode left;
    private PassengerNode right;

    public PassengerNode(Passenger passenger) {
        this.passenger = passenger;
        this.left = null;
        this.right = null;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public PassengerNode getLeft() {
        return left;
    }

    public PassengerNode getRight() {
        return right;
    }

    public void setLeft(PassengerNode left) {
        this.left = left;
    }

    public void setRight(PassengerNode right) {
        this.right = right;
    }
}

