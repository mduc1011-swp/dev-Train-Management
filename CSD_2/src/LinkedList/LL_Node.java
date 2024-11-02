/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;


/**
 *
 * @author phank
 */
public class LL_Node {

    public Object info;
    public LL_Node next;

    public LL_Node() {
    }

    public LL_Node(Object info) {
        this.info = info;
        this.next = null;
    }

    public LL_Node(Object info, LL_Node next) {
        this.info = info;
        this.next = next;
    }

    public Object getInfo() {
        return info;
    }

    public void setNext(LL_Node next) {
        this.next = next;
    }

    public LL_Node getNext(LL_Node p) {
        if (p == null || p.next == null) {
            return null;
        }
        return p.next;
    }

}
