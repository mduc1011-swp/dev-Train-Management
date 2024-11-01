/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import Entity.Booking;

/**
 *
 * @author phank
 */
public class Node {

    public Object info;
    public Node next;

    public Node() {
    }

    public Node(Object info) {
        this.info = info;
        this.next = null;
    }

    public Node(Object info, Node next) {
        this.info = info;
        this.next = next;
    }

    public Object getInfo() {
        return info;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext(Node p) {
        if (p == null || p.next == null) {
            return null;
        }
        return p.next;
    }

}
