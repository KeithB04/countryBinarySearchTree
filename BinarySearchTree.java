// TODO: Auto-generated Javadoc
/**
 * The Class BinarySearchTree.
 * 
 * This class represents a binary search tree that stores country names and their happiness index.
 * It allows insertion, deletion, searching, and traversal operations, and includes methods to find 
 * the top and bottom countries based on happiness.
 */
public class BinarySearchTree {
    
    /** The root. */
    private Node root;


    /**
     * Instantiates a new binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }


    /**
     * Insert.
     *
     * @param name the name
     * @param happiness the happiness
     */
    public void insert(String name, double happiness) {
        root = insertRec(root, name, happiness);
    }

    /**
     * Insert rec.
     *
     * @param root the root
     * @param name the name
     * @param happiness the happiness
     * @return the node
     */
    private Node insertRec(Node root, String name, double happiness) {
        if (root == null) return new Node(name, happiness);
        if (name.compareToIgnoreCase(root.name) < 0)
            root.left = insertRec(root.left, name, happiness);
        else if (name.compareToIgnoreCase(root.name) > 0)
            root.right = insertRec(root.right, name, happiness);
        return root;
    }


    /**
     * Find.
     *
     * @param name the name
     * @param delete the delete
     * @return the double
     */
    public double find(String name, boolean delete) {
        String[] path = new String[128];
        int[] index = {0};
        Node result = findRec(root, name, path, index);
        if (result == null) return -1;
        if(!delete) {
        	System.out.println(name + " is found with happiness of " + result.happiness);
        System.out.print("Path to " + name + " is ");
        for (int i = 0; i < index[0]; i++) {
            System.out.print(path[i]);
            if (i < index[0] - 1) System.out.print(" -> ");
        }
        System.out.println(".");
        }
        return result.happiness;
    }

    /**
     * Find rec.
     *
     * @param root the root
     * @param name the name
     * @param path the path
     * @param index the index
     * @return the node
     */
    private Node findRec(Node root, String name, String[] path, int[] index) {
        if (root == null) return null;
        path[index[0]++] = root.name;
        if (name.equalsIgnoreCase(root.name)) return root;
        if (name.compareToIgnoreCase(root.name) < 0)
            return findRec(root.left, name, path, index);
        return findRec(root.right, name, path, index);
    }


    /**
     * Delete.
     *
     * @param name the name
     */
    public void delete(String name) {
    	boolean delete = true;
        if (find(name, delete) == -1) {
            System.out.println(name + " is not found.");
        } else {
            root = deleteRec(root, name);
            System.out.println(name + " is deleted from binary search tree.");
        }
    }

    /**
     * Delete rec.
     *
     * @param root the root
     * @param name the name
     * @return the node
     */
    private Node deleteRec(Node root, String name) {
        if (root == null) return null;
        if (name.compareToIgnoreCase(root.name) < 0)
            root.left = deleteRec(root.left, name);
        else if (name.compareToIgnoreCase(root.name) > 0)
            root.right = deleteRec(root.right, name);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node minNode = findMin(root.right);
            root.name = minNode.name;
            root.happiness = minNode.happiness;
            root.right = deleteRec(root.right, minNode.name);
        }
        return root;
    }

    /**
     * Find min.
     *
     * @param root the root
     * @return the node
     */
    private Node findMin(Node root) {
        while (root.left != null) root = root.left;
        return root;
    }


    /**
     * Prints the inorder.
     */
    public void printInorder() {
        inorder(root);
    }

    /**
     * Inorder.
     *
     * @param root the root
     */
    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            root.print();
            inorder(root.right);
        }
    }


    /**
     * Prints the preorder.
     */
    public void printPreorder() {
        preorder(root);
    }

    /**
     * Preorder.
     *
     * @param root the root
     */
    private void preorder(Node root) {
        if (root != null) {
            root.print();
            preorder(root.left);
            preorder(root.right);
        }
    }


    /**
     * Prints the postorder.
     */
    public void printPostorder() {
        postorder(root);
    }

    /**
     * Postorder.
     *
     * @param root the root
     */
    private void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            root.print();
        }
    }


    /**
     * Prints the bottom countries.
     *
     * @param c the c
     */
    public void printBottomCountries(int c) {
        Node[] bottom = new Node[c];
        fillTopOrBottom(root, bottom, c, true);
        sortByHappiness(bottom, true);
        for (Node n : bottom) if (n != null) n.print();
    }


    /**
     * Prints the top countries.
     *
     * @param c the c
     */
    public void printTopCountries(int c) {
        Node[] top = new Node[c];
        fillTopOrBottom(root, top, c, false);
        sortByHappiness(top, false);
        for (Node n : top) if (n != null) n.print();
    }

    /**
     * Fill top or bottom.
     *
     * @param root the root
     * @param arr the arr
     * @param c the c
     * @param isBottom the is bottom
     */
    private void fillTopOrBottom(Node root, Node[] arr, int c, boolean isBottom) {
        if (root == null) return;
        insertToArray(arr, root, isBottom);
        fillTopOrBottom(root.left, arr, c, isBottom);
        fillTopOrBottom(root.right, arr, c, isBottom);
    }

    /**
     * Insert to array.
     *
     * @param arr the arr
     * @param candidate the candidate
     * @param isBottom the is bottom
     */
    private void insertToArray(Node[] arr, Node candidate, boolean isBottom) {
        int pos = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = candidate;
                return;
            } else if ((isBottom && candidate.happiness < arr[i].happiness) ||
                       (!isBottom && candidate.happiness > arr[i].happiness)) {
                if (pos == -1 || (isBottom && arr[i].happiness > arr[pos].happiness) ||
                                 (!isBottom && arr[i].happiness < arr[pos].happiness)) {
                    pos = i;
                }
            }
        }
        if (pos != -1) arr[pos] = candidate;
    }

    /**
     * Sort by happiness.
     *
     * @param arr the arr
     * @param ascending the ascending
     */
    private void sortByHappiness(Node[] arr, boolean ascending) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == null || arr[j] == null) continue;
                if ((ascending && arr[i].happiness > arr[j].happiness) ||
                    (!ascending && arr[i].happiness < arr[j].happiness)) {
                    Node temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}