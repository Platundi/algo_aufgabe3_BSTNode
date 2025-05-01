/* Quellen:
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
 * https://www.geeksforgeeks.org/binary-search-tree-set-3-iterative-delete/ */

public class BST {
    private BSTNode root;

    public BST() {
        root = null;
    } // Konstruktor

    public void insert(int key, String val) {
        root = insert(root, key, val);
    }

    private BSTNode insert(BSTNode node, int key, String val) {
        if (node == null)
            return new BSTNode(key, val);

        if (key < node.key)
            node.left = insert(node.left, key, val);
        else if (key > node.key)
            node.right = insert(node.right, key, val);
        return node;
    }


    public BSTNode search(int key) {
        return search(root, key);
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

    public boolean isValidBST() {
        /* {Integer.MIN_VALUE}: kleinstmöglicher Wert, damit erster Knoten immer grösser ist
         * warum array: um referenz im rekursiven aufruf zu übergeben. Primitiver Datentyp int übergibt nur eine Kopie.
         * Datentyp Integer geht nicht, weil dieser Immutable ist, Wert kann icht direkt verändert werden */
        int[] prev = {Integer.MIN_VALUE};
        return inorder(root, prev);
    }

    private boolean inorder(BSTNode node, int[] prev) {
        if (node == null)
            return true;

        // Recursively check the left subtree
        if (!inorder(node.left, prev))
            return false;

        // Check the current node key against the previous key
        if (prev[0] >= node.key)
            return false;

        // Update the previous value to the current node's value
        prev[0] = node.key;

        // Recursively check the right subtree
        return inorder(node.right, prev);
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

        // Fall 1: Node hat maximal ein Kind
        if (current.left == null || current.right == null) {
            BSTNode child = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                root = child; // Wir löschen den Root-Knoten
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            // Fall 2: Node hat zwei Kinder → Nachfolger finden
            BSTNode nextParent = current;
            BSTNode next = current.right;

            while (next.left != null) {
                nextParent = next;
                next = next.left;
            }

            current.key = next.key;
            current.val = next.val;

            // Lösche Nachfolger-Knoten rekursiv
            if (nextParent.left == next) {
                nextParent.left = next.right;
            } else {
                nextParent.right = next.right;
            }
        }
    }
    public void hurtBST() {
        root.left.key = 9999;
    }
}
