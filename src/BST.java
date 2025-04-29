/* Quellen:
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/ */

public class BST {
    private BSTNode root;

    public BST() {
        root = null;
    } // Konstruktor

    public void insert(int key, String val) {
        BSTNode y = null;
        BSTNode x = root;

        while (x != null) {
            y = x;
            if (key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        // evtl muss hier noch was hin in diese zeile, vllt auch nicht
        if (y == null) {
            root = new BSTNode(key, val);
        } else if (key < y.key) {
            y.left = new BSTNode(key, val);
        } else {
            y.right = new BSTNode(key, val);
        }
    }

    public BSTNode search(int key) {
        while (root != null && root.key != key) {
            if (key < root.left.key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    public int height() {
    }

    public boolean isValidBST() {
    }

    public void remove(int key) {

    }

    public swap()
}
