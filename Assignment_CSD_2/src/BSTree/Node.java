/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

/**
 *
 * @author phank
 */
public class Node<E> {

    E info;
    int height;
    public Node<E> left, right;

    Node(E x) {
        info = x;
        left = right = null;
        height = 1;
    }

    Node() {

    }

    public E getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "" + info;
    }
}
