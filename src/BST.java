/* Quellen:
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/ */

public class BST {
    private BSTNode root;

    public BST() {
        root = null;
    } // Konstruktor

    public void insert(int key, String val) {
        // If the tree is empty, return a new node
        if (root == null)
            new BSTNode(key, val);

        // Otherwise, recur down the tree
        if (key < root.key) {
            root.left = new BSTNode(key, val);
            insert(root.left.key, root.left.val);
        }
        else {
            root.right = new BSTNode(key, val);
            insert(root.right.key, root.right.val);
        }
    }

    public BSTNode search(int key) {
        if (root == null || root.key == key) {
            return root;
        }

        // gesuchter Key ist kleiner als root.key -> links weitersuchen
        if (root.key > key) {
            return search(root.left.key);
        }

        // gesuchter Key ist größer als root.key -> rechts weitersuchen
        return search(root.right.key);
    }

    // Returns height which is the number of edges along the longest path from the root node down to the farthest leaf node.
    public int height(BSTNode node) {
        if (root == null)
            return -1;

        // compute the height of left and right subtrees
        int lHeight = height(root.left);
        int rHeight = height(root.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    public boolean isValidBST() {
        /* {Integer.MIN_VALUE}: kleinstmöglicher Wert, damit erster Knoten immer grösser ist
        * warum array: um referenz im rekursiven aufruf zu übergeben. Primitiver Datentyp int übergibt nur eine Kopie.
        * Datentyp Integer geht nicht, weil dieser Immutable ist, Wert kann icht direkt verändert werden */
        int[] prev = {Integer.MIN_VALUE};
        return inorder(root, prev);
    }

    private boolean inorder(BSTNode root, int[] prev) {
        if (root == null)
            return true;

        // Recursively check the left subtree
        if (!inorder(root.left, prev))
            return false;

        // Check the current node value against the previous value
        if (prev[0] >= Integer.parseInt(root.val))
            return false;

        // Update the previous value to the current node's value
        prev[0] = Integer.parseInt(root.val);

        // Recursively check the right subtree
        return inorder(root.right, prev);
    }

    public void remove(int key) {
        BSTNode prev = null;

        while (root != null && root.key != key) {
            prev = root;
            if (key < root.key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        // Check if the node to be deleted has at most one child.
        if (root.left == null && root.right == null) {
            BSTNode newRoot;

            if (root.left != null) {
                newRoot = root.left;
            } else {
                newRoot = root.right;
            }

            if (root == prev.left) {
                prev.left = newRoot;
            } else {
                prev.right = newRoot;
            }
        } else {
            // Node to be deleted has two children.
            BSTNode p = null;
            BSTNode temp = root.right;
            while (temp.left != null) {
                p = temp;
                temp = temp.left;
            }

            if (p != null){
                p.left = temp.right;
            } else {
                root.right = temp.right;
            }
            root.key = temp.key;
        }
    }
}
