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
public class BSTree {

    Node root;

    public BSTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node getRoot() {
        return root;
    }


    public void insert(Model x) {
        Node p = new Node(x);
        Node f = null, q = root;
        while (q != null) {
            Model current = (Model) q.getInfo();  // Lấy thông tin của Node q
            int comparison = x.getKey().toLowerCase().compareToIgnoreCase(current.getKey());

            if (comparison == 0) {
                System.out.println("Key cannot be duplicated...");
                return;
            }
            if (comparison > 0) {
                f = q;
                q = q.right;
            } else {
                f = q;
                q = q.left;
            }
        }
        if (f == null) {
            root = p;
        } else {
            Model fModel = (Model) f.getInfo();  // Lấy thông tin của Node f
            if (x.getKey().toLowerCase().compareToIgnoreCase(fModel.getKey()) > 0) {
                f.right = p;
            } else {
                f.left = p;
            }
        }
    }
    
        //inorder a tree
    public void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    private void visit(Node p) {
        Model m = (Model) p.info;
        System.out.println(m.toString());
    }
    // Xóa Node bằng cách gộp (Merging)
    public void deleteNodeByMerging(Node p) {
        Node f = findParent(p);  // Tìm Node cha
        if (p.left == null) {
            replaceChild(f, p, p.right);
        } else if (p.right == null) {
            replaceChild(f, p, p.left);
        } else {
            Node maxNode = findMax(p.left);
            maxNode.right = p.right;
            replaceChild(f, p, p.left);
        }
    }

    // Xóa Node bằng cách sao chép (Copying)
    public void deleteNodeByCopy(Node p) {
        Node f = findParent(p);  // Tìm Node cha
        if (p.left == null) {
            replaceChild(f, p, p.right);
        } else if (p.right == null) {
            replaceChild(f, p, p.left);
        } else {
            Node maxNode = findMax(p.left);
            p.info = maxNode.info;
            deleteNodeByCopy(maxNode);
        }
    }

    // Tìm Node lớn nhất trong cây con trái
    private Node findMax(Node p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    // Tìm Node cha
    private Node findParent(Node child) {
        Node f = null, p = root;
        while (p != null && p != child) {
            f = p;
            p = (child.getInfo().getKey().compareToIgnoreCase(p.getInfo().getKey()) > 0) ? p.right : p.left;
        }
        return f;
    }

    // Thay thế con của Node f
    private void replaceChild(Node f, Node p, Node newChild) {
        if (f == null) root = newChild;
        else if (f.left == p) f.left = newChild;
        else f.right = newChild;
    }

    
    // Tìm kiếm phương thức đã tồn tại
    public Node search(String key) {
        return search(root, key);
    }

    private Node search(Node node, String key) {
        if (node == null || key.equals(node.info.getKey())) {
            return node;
        }
        if (key.compareTo(node.info.getKey()) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }
}
