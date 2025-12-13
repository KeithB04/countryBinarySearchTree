// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 * This class creates a node for a binary search tree to store country data.
 * Includes name, happiness index, and references to child nodes.
 */
public class Node {
    
    /** The name. */
    String name;
    
    /** The happiness. */
    double happiness;
    
    /** The right. */
    Node left, right;

    /**
     * Constructor for Node class.
     *
     * @param name      the country name
     * @param happiness the happiness index of the country
     */
    public Node(String name, double happiness) {
        this.name = name;
        this.happiness = happiness;
        this.left = null;
        this.right = null;
    }

    /**
     * Prints the node's data in the format "Name Happiness".
     */
    public void print() {
        System.out.printf("%-45s %.3f\n", name, happiness);
    }
} 