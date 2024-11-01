/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Model.Model;

/**
 *
 * @author iamwu
 */
public class Node {
    public Model info;
    public Node left;
    public Node right;

    Node(Model info, Node left, Node right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
    
    Node(Model info) {
        this(info,null,null);
    }

    public Model getInfo() {
        return info;
    }
    
    
}
