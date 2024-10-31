/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.LinkedList;

/**
 *
 * @author ASUS
 */
public class MyQueue {
    
    LinkedList<PassengerNode> queue;

    public MyQueue() {
        this.queue = new LinkedList<PassengerNode>();
    }

    public void clear() {
        this.queue.clear();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void enqueue(PassengerNode x) {
        this.queue.addLast(x);
    }

    public PassengerNode dequeue() {
        if (isEmpty()) {
            return null;
        }
        PassengerNode p = this.queue.removeFirst();
        return p;
    }

    public PassengerNode front() {
        if (isEmpty()) {
            return null;
        }
        return this.queue.getFirst();
    }
}
