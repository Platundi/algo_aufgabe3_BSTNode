/* Quellen:
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
 * https://www.geeksforgeeks.org/binary-search-tree-set-3-iterative-delete/
 * https://www.geeksforgeeks.org/insertion-in-binary-search-tree/ */

public class BST {
    private BSTNode root;

    public BST() {
        root = null;
    } // Konstruktor

    public void insert(int key, String val) {
        root = insert(root, key, val, null);
    }

    private BSTNode insert(BSTNode node, int key, String val, BSTNode parent) {
        if (node == null) {
            BSTNode newNode = new BSTNode(key, val);
            newNode.parent = parent;
            return newNode;
        }

        if (key < node.key)
            node.left = insert(node.left, key, val, node);
        else if (key > node.key)
            node.right = insert(node.right, key, val, node);
        return node;
    }


    public String search(int key) {
        BSTNode result = search(root, key);
        if(result != null) {
            return result.val;
        }
        // Wenn nicht gefunden
        return null;
    }

    private BSTNode search(BSTNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }

        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // Returns height which is the number of edges along the longest path from the root node down to the farthest leaf node.
    public int height() {
        return height(root);
    }

    private int height(BSTNode node) {
        if (node == null)
            return -1;
        int lHeight = height(node.left);
        int rHeight = height(node.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    // Helper function to check if a tree is BST within a given range
    private boolean isBSTUtil(BSTNode node, BSTNode min, BSTNode max) {
        if (node == null) return true;

        // If the current node's data is not in the valid range, return false
        if ((min != null && node.key <= min.key) || (max != null && node.key >= max.key)) return false;

        if (node.left != null && node.left.parent != node) return false;
        if (node.right != null && node.right.parent != node) return false;

        // Recursively check the left and right subtrees with updated ranges
        return isBSTUtil(node.left, min, node) &&
                isBSTUtil(node.right, node, max);
    }

    // Function to check if the entire binary tree is a BST
    public boolean isBST() {
        return isBSTUtil(root, null, null);
    }

    public void remove(int key) {
        BSTNode parent = null;
        BSTNode current = root;

        while (current != null && current.key != key) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return; // Schlüssel nicht gefunden

        // Fall 1: max. ein Kind
        if (current.left == null || current.right == null) {
            BSTNode child = (current.left != null) ? current.left : current.right;

            if (child != null)
                child.parent = parent;

            if (parent == null) {
                root = child;
                if (root != null) root.parent = null;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }

        } else {
            // Fall 2: zwei Kinder → Nachfolger finden
            BSTNode nextParent = current;
            BSTNode next = current.right;

            while (next.left != null) {
                nextParent = next;
                next = next.left;
            }

            current.key = next.key;
            current.val = next.val;

            // Entferne Nachfolger
            BSTNode replacement = next.right;

            if (replacement != null)
                replacement.parent = nextParent;

            if (nextParent.left == next) {
                nextParent.left = replacement;
            } else {
                nextParent.right = replacement;
            }
        }
    }

    public void hurtBST(int key, int wert) {
        search(root, key).key = wert;
    }
}
