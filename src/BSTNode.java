/* Quellen:
* https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/ */

public class BSTNode {
    public int key;
    public String val;
    public BSTNode left, right, parent;

    public BSTNode(int k, String s) {
        key = k;
        val = s;
        left = right = parent = null;
    }
}
