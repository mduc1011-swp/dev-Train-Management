/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Entity.Passenger;
import Entity.Train;
import Utility.validator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author phank
 */
public class trainBSTree {

    TrainNode root;

    public trainBSTree() {
        this.root = null;
    }

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(Train x) {
        // insert when BSTree is empty
        if (isEmpty()) {
            TrainNode newNode = new TrainNode(x);
            root = newNode;
            return;
        }
        TrainNode curr;
        TrainNode parentOfCurr;
        curr = root;
        parentOfCurr = null;
        while (curr != null) {
            // check if node x exist
            if (curr.info.compareTo(x) == 0) {
                return;
            }
            // find position to insert: foundNode == null; parentOfCurr is parent
            parentOfCurr = curr;
            if (x.compareTo(curr.info) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // now curr = null; parentOfCurr has not both children

        // insert x to be child of parentOfCurr node
        TrainNode newNode = new TrainNode(x);
        if (x.compareTo(parentOfCurr.info) < 0) {
            parentOfCurr.left = newNode;
        } else {
            parentOfCurr.right = newNode;
        }
    }

    // 1.1 load data from file trains.txt
    public void loadFromFile(String filename) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|\\s*");
                if (parts.length < 8) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }
                String tcode = parts[0].trim();
                String name = parts[1].trim();
                String dstation = parts[2].trim();
                String astation = parts[3].trim();
                double dtime;
                int seat;
                int booked;
                double atime;

                try {
                    dtime = Double.parseDouble(parts[4].trim());
                    seat = Integer.parseInt(parts[5].trim());
                    booked = Integer.parseInt(parts[6].trim());
                    atime = Double.parseDouble(parts[7].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                    continue;
                }
                if (tcode.isEmpty() || name.isEmpty() || dstation.isEmpty() || astation.isEmpty()) {
                    System.out.println("Invalid line (empty values): " + line);
                    continue;
                }
                Train train = new Train(tcode, name, dstation, astation, dtime, seat, booked, atime);
                insert(train);
            }
            System.out.println("Load successfully from " + filename);

        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }

//1.2 input and add 
    public void addTrain() {
        String tcode = validator.getString("Enter Train Code: ", "Train code cannot be empty.", "Invalid input.", ".+");
        String name = validator.getString("Enter Train Name: ", "Train name cannot be empty.", "Invalid input.", ".+");

        String dstation = validator.getString("Enter Departure Station: ", "Departure station cannot be empty.", "Invalid input.", ".+");
        String astation = validator.getString("Enter Arrival Station: ", "Arrival station cannot be empty.", "Invalid input.", ".+");

        double dtime = validator.getDoubleCond(0.0, 23.59);
        int atime = validator.getIntCond(0, 24);

        int seat = validator.getIntCond(1, Integer.MAX_VALUE);
        int booked = validator.getIntCond(0, seat);

        Train train = new Train(tcode, name, dstation, astation, dtime, atime, seat, booked);
        insert(train); // Assuming TrainBST has an insert method
        System.out.println("Train added successfully: " + train);
    }

    //1.3 Display data by pre-order traversal
    public void visit(TrainNode p) {
        System.out.println(p.info);
    }

    void preOrder(TrainNode p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void display(TrainNode p) {
        preOrder(p);
    }

    //1.4 Save train tree to file by in-order traversal
    void inOrderSave(TrainNode node, BufferedWriter bw) throws IOException {
        if (node == null) {
            return;
        }

        inOrderSave(node.left, bw); // Traverse the left subtree first

        Train train = node.info;
        bw.write(train.getTcode() + "| " + train.getName() + "| " + train.getDstation()
                + "| " + train.getAstation() + "| " + train.getDtime() + "| " + train.getSeat()
                + "| " + train.getBooked() + "| " + train.getAtime());
        bw.newLine(); // Visit the node and save it to the file

        inOrderSave(node.right, bw); // Traverse the right subtree
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            inOrderSave(root, bw); // Use inOrder traversal to save
            System.out.println("Data successfully saved to file " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // 1.5 Search by tcode
    public TrainNode searchByTcode(String tcode) {
        TrainNode p = root;
        while (p != null) {
            if (p.info.getTcode().equals(tcode)) {
                return p;
            } else if (tcode.compareTo(p.info.getTcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public void searchWithInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(" ----- Search by Pcode ----- ");
            System.out.print("Enter pcode: ");
            String tcode = sc.nextLine().trim();

            TrainNode p = searchByTcode(tcode);
            if (p != null) {
                System.out.println("Passenger found: " + p.info);
                System.out.println("");
                break;
            } else {
                System.out.println("Passenger not found");
            }
            System.out.println("");
        }
    }
//1.6 Delete by tcode by copying

    public void deleteByCopying(String tcode) {
        TrainNode parent = null;
        TrainNode current = root;

        // Find the train node with matching tcode
        while (current != null && !current.info.getTcode().equals(tcode)) {
            parent = current;
            current = (tcode.compareTo(current.info.getTcode()) < 0) ? current.left : current.right;
        }

        if (current == null) {
            return; // tcode not found
        }
        // Case 1: Node has no left child
        if (current.left == null) {
            if (parent == null) {
                root = current.right; // Deleting root
            } else if (parent.left == current) {
                parent.left = current.right; // Current is left child
            } else {
                parent.right = current.right; // Current is right child
            }
        } // Case 2: Node has no right child
        else if (current.right == null) {
            if (parent == null) {
                root = current.left; // Deleting root
            } else if (parent.left == current) {
                parent.left = current.left; // Current is left child
            } else {
                parent.right = current.left; // Current is right child
            }
        } // Case 3: Node has two children
        else {
            // Find the in-order successor (smallest in the right subtree)
            TrainNode successorParent = current;
            TrainNode successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Copy the successor's info to current
            current.info = successor.info;

            // Remove the successor
            if (successorParent.left == successor) {
                successorParent.left = successor.right; // Bypass successor
            } else {
                successorParent.right = successor.right; // Bypass successor
            }
        }
    }

    //1.7 delete by tcode by merging
    public void deleteByMerging(String tcode) {
        TrainNode parent = null;
        TrainNode current = root;

        // Find the train node with matching tcode
        while (current != null && !current.info.getTcode().equals(tcode)) {
            parent = current;
            current = (tcode.compareTo(current.info.getTcode()) < 0) ? current.left : current.right;
        }

        if (current == null) {
            return; // tcode not found
        }
        // Node to attach will be either the left or right child
        TrainNode nodeToAttach = (current.left != null) ? current.left : current.right;

        if (parent == null) {
            // Deleting the root
            root = nodeToAttach; // Set root to the child
        } else if (parent.left == current) {
            parent.left = nodeToAttach; // Attach to the left
        } else {
            parent.right = nodeToAttach; // Attach to the right
        }

        // If the node to delete has two children, we need to handle merging
        if (current.left != null && current.right != null) {
            // Find the rightmost node in the left subtree
            TrainNode rightmost = current.left;
            while (rightmost.right != null) {
                rightmost = rightmost.right;
            }

            // Attach the right subtree to the rightmost node
            rightmost.right = current.right;
        }
    }

    //1.8 Simply balancing
    public void balance() {
        List<Train> sortedTrains = new ArrayList<>();
        // Step 1: Store the trains in a sorted list
        inOrderTraversal(root, sortedTrains);
        // Step 2: Build a balanced BST from the sorted list
        root = buildBalancedBST(sortedTrains, 0, sortedTrains.size() - 1);
    }

    // Helper method for in-order traversal
    private void inOrderTraversal(TrainNode node, List<Train> sortedTrains) {
        if (node != null) {
            inOrderTraversal(node.left, sortedTrains);
            sortedTrains.add(node.info); // Add train info to the list
            inOrderTraversal(node.right, sortedTrains);
        }
    }

    // Helper method to build a balanced BST
    private TrainNode buildBalancedBST(List<Train> sortedTrains, int start, int end) {
        if (start > end) {
            return null; // Base case: no elements to add
        }

        int mid = (start + end) / 2; // Find the middle index
        TrainNode node = new TrainNode(sortedTrains.get(mid)); // Create a new node with the middle element

        // Recursively build the left and right subtrees
        node.left = buildBalancedBST(sortedTrains, start, mid - 1);
        node.right = buildBalancedBST(sortedTrains, mid + 1, end);

        return node; // Return the balanced node

    }

    //1.9 Display data by breadth-first traversal
    public void bfsTraversal() {
        MyQueue queue = new MyQueue();
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TrainNode current = (TrainNode) queue.dequeue();
            System.out.println(current.info);
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

    }

    //1.10 
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TrainNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    //1.11 Search by name
    public void searchByName() {
        Scanner sc = new Scanner(System.in);

        System.out.println(" ----- Search by Train Name -----");
        System.out.print("Enter train name to search: ");
        String nameToSearch = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        found = searchByNameHelper(root, nameToSearch, found);

        if (!found) {
            System.out.println("Can't find any train with the name: " + nameToSearch);
        }
    }

    private boolean searchByNameHelper(TrainNode node, String nameToSearch, boolean found) {
        if (node == null) {
            return found; // Base case: return found status
        }

        // Check if the current node's train name matches
        if (node.info.getName().toLowerCase().equals(nameToSearch)) {
            System.out.println("Found Train: " + node.info);
            found = true;
        }

        // Search left and right subtrees
        found = searchByNameHelper(node.left, nameToSearch, found);
        found = searchByNameHelper(node.right, nameToSearch, found);

        return found; // Return the found status
    }
    //1.12 Search booked by tcode
    
}
