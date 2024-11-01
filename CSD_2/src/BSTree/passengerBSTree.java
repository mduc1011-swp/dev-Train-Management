/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Entity.Passenger;

/**
 *
 * @author phank
 */
public class passengerBSTree {
    PassengerNode root;

    public passengerBSTree() {
        this.root = null;
    }

    

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(Passenger x) {
        // insert when BSTree is empty
        if (isEmpty()) {
            PassengerNode newNode = new PassengerNode(x);
            root = newNode;
            return;
        }
        PassengerNode curr;
        PassengerNode parentOfCurr;
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
        PassengerNode newNode = new PassengerNode(x);
        if (x.compareTo(parentOfCurr.info) < 0) {
            parentOfCurr.left = newNode;
        } else {
            parentOfCurr.right = newNode;
        }
    }

    // 2.1 load data from file passengers.txt
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|\\s*");
                if (parts.length < 3) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                String pcode = parts[0].trim();
                String name = parts[1].trim();
                String phone = parts[2].trim();
                if (pcode.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    System.out.println("Invalid line (empty values): " + line);
                    continue;
                }
                Passenger pa = new Passenger(pcode, name, phone);
                insert(pa);
            }
            System.out.println("Load successfully " + filename);
        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }

    //2.2 input and add 
    // Check if pcode is unique
    public boolean isPcodeUnique(String pcode) {
        PassengerNode p = root;
        while (p != null) {
            if (p.info.getPcode().equals(pcode)) {
                return false; // pcode already exists
            } else if (pcode.compareTo(p.info.getPcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return true; // pcode is unique
    }

// Check if phone is unique and valid
    public boolean isPhoneUnique(String phone) {
        PassengerNode p = root;
        while (p != null) {
            if (p.info.getPhone().equals(phone)) {
                return false; // phone number already exists
            } else if (phone.compareTo(p.info.getPhone()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return true; // phone number is unique
    }

// Add passenger to the tree
    public void add() {
        Scanner sc = new Scanner(System.in);
        String pcode;
        String phone;

        while (true) {
            System.out.print("Enter pcode: ");
            pcode = sc.nextLine().trim();

            // Kiểm tra Pcode là duy nhất
            if (isPcodeUnique(pcode)) {
                break;
            } else {
                System.err.println("Pcode number already exists.");
            }
        }

        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();

        while (true) {
            System.out.print("Enter phone: ");
            phone = sc.nextLine().trim();

            // Kiểm tra xem phone chỉ chứa chữ số
            if (!phone.matches("\\d+")) {
                System.err.println("Invalid phone number. Must contain digits only.");
                continue;  // Yêu cầu nhập lại
            }

            // Kiểm tra tính duy nhất của số điện thoại
            if (!isPhoneUnique(phone)) {
                System.err.println("Phone number already exists.");
                continue;  // Yêu cầu nhập lại
            }

            // Nếu cả hai điều kiện đều đúng, thoát khỏi vòng lặp
            break;
        }

        Passenger newP = new Passenger(pcode, name, phone);
        insert(newP);
        System.out.println("Passenger added successfully.");
    }

    //2.3 Display data by post-order traversal
    public void visit(PassengerNode p) {
        System.out.println(p.info);
    }

    void postOrder(PassengerNode p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    public void display(PassengerNode p) {
        postOrder(p);
    }

    //2.4 Save passenger tree to file by pre-order traversal
    void preOrderSave(PassengerNode node, BufferedWriter bw) throws IOException {
        if (node == null) {
            return;
        }
        Passenger passenger = node.info;
        bw.write(passenger.getPcode() + "| " + passenger.getName() + "| " + passenger.getPhone());
        bw.newLine();

        preOrderSave(node.left, bw);
        preOrderSave(node.right, bw);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            preOrderSave(root, bw);
            System.out.println("Data successfully saved to file " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    //2.5 Search by pcode
    public PassengerNode searchByPcode(String pcode) {
        PassengerNode p = root;
        while (p != null) {
            if (p.info.getPcode().equals(pcode)) {
                return p;
            } else if (pcode.compareTo(p.info.getPcode()) < 0) {
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
            String pcode = sc.nextLine().trim();

            PassengerNode p = searchByPcode(pcode);
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

    //2.7 Search by name
    public void searchByName() {
        Scanner sc = new Scanner(System.in);

        System.out.println(" ----- Search by Name -----");
        System.out.print("Enter name to search: ");
        String nameToSearch = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        searchByNameHelper(root, nameToSearch, found);

        if (!found) {
            System.out.println("Can't find anyone with the name: " + nameToSearch);
        }
    }

    private void searchByNameHelper(PassengerNode node, String nameToSearch, boolean found) {
        if (node == null) {
            return; 
        }

        if (node.info.getName().toLowerCase().equals(nameToSearch)) {
            System.out.println("Found Passenger: " + node.info);
            found = true; 
        }

        searchByNameHelper(node.left, nameToSearch, found); 
        searchByNameHelper(node.right, nameToSearch, found); 
    }
}
