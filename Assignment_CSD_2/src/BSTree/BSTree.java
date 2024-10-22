package BSTree;

import java.util.ArrayList;

public class BSTree<E extends Comparable<E>> {

    public Node<E> root;

    private boolean isEmpty() {
        return (root == null);
    }

    public void clear() {
        this.root = null;
    }

    public BSTree() {
        root = null;
    }

    public void insert(E x) {
        if (root == null) {
            root = new Node<>(x);
            return;
        }
        Node<E> f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info == x) {
                System.out.println("The Key " + x + "already exists, no insertion");
                return;
            }
            f = p;
            if (x.compareTo(p.info) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.compareTo(f.info) < 0) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }

    }

    public void visit(E p) {
        System.out.print(" " + p);
    }

    public void breath() {
        if (isEmpty()) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node visitNode = null;
        while (!q.isEmpty()) {
            visitNode = q.dequeue();
            visit(visitNode);
            if (visitNode.left != null) {
                q.enqueue(visitNode.left);
            }
            if (visitNode.right != null) {
                q.enqueue(visitNode.right);
            }

        }
    }
// 1.3 display by pre-order

    public void preOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void inOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
// 1.5 search by code ( tcode or pcode) 

//mai- 1.6 delete code by copy (tocde or pcode)
//khoa- 1.7 delete code by merging (tcode or pcode) 
// 1.8 balance tree
// 1.9 display by breath-first traversal
// 1.10 display number of components( just count num of trains)
// 1.11 search by name ( name of train or passenger)
// 1.12 search boorked by code ( tcode or pcode)
    public void deleteByMerging(E x) {
        // check if BSTree is emptys
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion");
            return;
        }
        //search node to be delete
        Node<E> deleteNode;
        Node<E> parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info == x) {
                break; // found x
            }
            // continue search
            if (x.compareTo(deleteNode.info) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }
        // check if found x
        if (deleteNode == null) {
            System.out.println("The key" + x + "does not exist, no deletion");
            return;
        }
        // case 1: delete leaf node
        if (deleteNode.left == null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = null;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;
                } else {
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }
        // case 2: delete node having only left child
        if (deleteNode.left != null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }
        // case 3: delete node having only right child
        if (deleteNode.left != null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.right;
                } else {
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }
        // case 4: delete node having both left and right children
        if (deleteNode.left != null && deleteNode.right == null) {
            Node rightOfDeleteNode;
            Node replaceNode; // the right most node - this will replace deleteNode

            // find the right most node on the left sub-tree of deleteNode
            rightOfDeleteNode = deleteNode.right;
            replaceNode = deleteNode.left;
            while (replaceNode.right != null) {
                replaceNode = replaceNode.right;
            }

            replaceNode.right = rightOfDeleteNode;
            deleteNode.right = null;
            // now deleteNode has only left child

            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;

            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }
    }

    // delete node by copying
    public void deleteByCopying(E x) {
        // check if BSTree is emptys
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion");
            return;
        }

        // search node to be delete
        Node<E> deleteNode;
        Node<E> parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info == x) {
                break; // found x
            }

            // continue search
            if (x.compareTo(deleteNode.info) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;

            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }
        // check if found x
        if (deleteNode == null) {
            System.out.println("The key " + x + " does not exist, no deletion");
            return;
        }

        // case 1: delete leaf node
        if (deleteNode.left == null && deleteNode.right == null) {
            // check if delete if root
            if (parentOfDeleteNode == null) {
                root = null;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;

                } else {
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }
        // case 2: delete node having only left child
        if (deleteNode.left != null && deleteNode.right == null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }
        // case 3: delete node having only right child
        if (deleteNode.left == null && deleteNode.right != null) {
            // check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.right;
                } else {
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }
        // case 4: delete node having both left and right children
        if (deleteNode.left != null && deleteNode.right != null) {
            Node<E> parentReplaceNode; // parentNode of replaceNode
            Node<E> replaceNode; // the right most node - this will replace deleteNode

            // find the right most node on the left sub-tree of deleteNode
            parentReplaceNode = null;
            replaceNode = deleteNode.left;
            while (replaceNode.right != null) {
                parentReplaceNode = replaceNode;
                replaceNode = replaceNode.right;
            }

            // copy info of replaceNode to deleteNode
            deleteNode.info = replaceNode.info;

            if (parentReplaceNode == null) {
                // replacenode is left child of deleteNode
                deleteNode.left = replaceNode.left;
            } else {
                // replaceNode is not left child of deleteNode
                parentReplaceNode.right = replaceNode.left;
            }
            replaceNode.left = null;
            return;
        }

    }

//    // BST-->array
//    public void inOrderToArray(ArrayList<Integer> a, Node from) {
//        if (from == null) {
//            return;
//        }
//        inOrderToArray(a, from.left);
//        a.add(from.info);
//        inOrderToArray(a, from.right);
//    }
//    public void balance(ArrayList<Integer> a, int from, int to) {
//        if (from > to) {
//            return;
//        }
//        int middle = (from + to) / 2;
//        int x = a.get(middle);
//        this.insert(x);
//        balance(a, from, middle - 1);
//        balance(a, middle + 1, to);
//
//    }

//    public void balance() {
//        ArrayList<Integer> a = new ArrayList<Integer>();
//        inOrderToArray(a, this.root);
//        this.clear();
//        int size = a.size();
//        balance(a, 0, a.size() - 1);
//    }

}
