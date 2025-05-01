/* Quellen:
* https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/ */

import java.util.Random;

public class Main {

    public static int[] randperm(int n) {
        int[] arr = new int[n];
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = n-1; i > 0; i--) {
            int j = r.nextInt(i+1);

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

    public static void main(String[] args) {
        int n = 10000;
        int[] arr = randperm(n);
        BST bst = new BST();

        for (int i = 0; i < n; i++) {
            bst.insert(arr[i], arr[i] + ". Schluessel");
        }

        System.out.println(bst.height());
        System.out.println(bst.isValidBST());

        for (int i = 0; i < n; i++) {
            if (i % 2 != 0) {
                bst.remove(i);
            }
        }
        System.out.println(bst.height());
        System.out.println(bst.isValidBST());

        bst.hurtBST();
        System.out.println(bst.isValidBST());
    }
}