/**
 * Represents a node in a binary search tree containing a country's
 * name, happiness score, and references to its child nodes.
 */
public class Node {

    String name;
    double happiness;

    Node left;
    Node right;

    /**
     * Creates a node containing country data.
     *
     * @param name the country name
     * @param happiness the country's happiness score
     */
    public Node(String name, double happiness) {
        this.name = name;
        this.happiness = happiness;
        this.left = null;
        this.right = null;
    }

    /**
     * Displays the country's name and happiness score.
     */
    public void print() {
        System.out.printf("%-45s %.3f%n", name, happiness);
    }
}