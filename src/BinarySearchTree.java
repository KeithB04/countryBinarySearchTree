import java.util.ArrayList;
import java.util.List;

/**
 * A binary search tree that stores country names and happiness scores.
 *
 * Countries are organized alphabetically by name using case-insensitive
 * comparisons. The tree supports insertion, deletion, searching,
 * traversal, and happiness rankings.
 */
public class BinarySearchTree {

    private Node root;

    /**
     * Creates an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a country if its name does not already exist.
     *
     * @param name the country name
     * @param happiness the country's happiness score
     * @return true if the country was inserted
     */
    public boolean insert(String name, double happiness) {
        if (find(name, true) != -1) {
            return false;
        }

        root = insertRec(root, name, happiness);
        return true;
    }

    private Node insertRec(Node root, String name, double happiness) {
        if (root == null) {
            return new Node(name, happiness);
        }

        int comparison = name.compareToIgnoreCase(root.name);

        if (comparison < 0) {
            root.left = insertRec(root.left, name, happiness);
        } else if (comparison > 0) {
            root.right = insertRec(root.right, name, happiness);
        }

        return root;
    }

    /**
     * Searches for a country and optionally displays its search path.
     *
     * @param name the country name
     * @param silent whether to suppress search output
     * @return the happiness score, or -1 if the country is not found
     */
    public double find(String name, boolean silent) {
        List<String> path = new ArrayList<>();
        Node result = findRec(root, name, path);

        if (result == null) {
            return -1;
        }

        if (!silent) {
            System.out.printf(
                "%s was found with a happiness score of %.3f.%n",
                result.name, result.happiness
            );

            System.out.println(
                "Path to " + result.name + ": "
                + String.join(" -> ", path)
            );
        }

        return result.happiness;
    }

    private Node findRec(Node root, String name, List<String> path) {
        if (root == null) {
            return null;
        }

        path.add(root.name);

        int comparison = name.compareToIgnoreCase(root.name);

        if (comparison == 0) {
            return root;
        }

        if (comparison < 0) {
            return findRec(root.left, name, path);
        }

        return findRec(root.right, name, path);
    }

    /**
     * Deletes a country from the tree if it exists.
     *
     * @param name the country name
     */
    public void delete(String name) {
        if (find(name, true) == -1) {
            System.out.println(name + " was not found.");
            return;
        }

        root = deleteRec(root, name);
        System.out.println(name + " was deleted from the tree.");
    }

    private Node deleteRec(Node root, String name) {
        if (root == null) {
            return null;
        }

        int comparison = name.compareToIgnoreCase(root.name);

        if (comparison < 0) {
            root.left = deleteRec(root.left, name);
        } else if (comparison > 0) {
            root.right = deleteRec(root.right, name);
        } else {
            // Handle nodes with zero or one child.
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // Replace a node with two children using its inorder successor.
            Node successor = findMin(root.right);

            root.name = successor.name;
            root.happiness = successor.happiness;
            root.right = deleteRec(root.right, successor.name);
        }

        return root;
    }

    /**
     * Finds the alphabetically smallest node in a subtree.
     */
    private Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    /**
     * Displays countries in alphabetical order.
     */
    public void printInorder() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            root.print();
            inorder(root.right);
        }
    }

    /**
     * Displays countries using preorder traversal.
     */
    public void printPreorder() {
        preorder(root);
    }

    private void preorder(Node root) {
        if (root != null) {
            root.print();
            preorder(root.left);
            preorder(root.right);
        }
    }

    /**
     * Displays countries using postorder traversal.
     */
    public void printPostorder() {
        postorder(root);
    }

    private void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            root.print();
        }
    }

    /**
     * Displays the countries with the lowest happiness scores.
     *
     * @param count the number of countries to display
     */
    public void printBottomCountries(int count) {
        printRankedCountries(count, true);
    }

    /**
     * Displays the countries with the highest happiness scores.
     *
     * @param count the number of countries to display
     */
    public void printTopCountries(int count) {
        printRankedCountries(count, false);
    }

    /**
     * Collects, sorts, and displays countries based on happiness.
     */
    private void printRankedCountries(int count, boolean ascending) {
        if (count <= 0) {
            System.out.println("The number of countries must be positive.");
            return;
        }

        Node[] countries = new Node[count];

        fillTopOrBottom(root, countries, ascending);
        sortByHappiness(countries, ascending);

        for (Node country : countries) {
            if (country != null) {
                country.print();
            }
        }
    }

    /**
     * Traverses the tree to collect candidates for happiness rankings.
     */
    private void fillTopOrBottom(
            Node root, Node[] countries, boolean isBottom) {

        if (root == null) {
            return;
        }

        insertToArray(countries, root, isBottom);
        fillTopOrBottom(root.left, countries, isBottom);
        fillTopOrBottom(root.right, countries, isBottom);
    }

    /**
     * Adds a candidate to the ranking array or replaces its least
     * suitable country when the candidate has a better ranking.
     */
    private void insertToArray(
            Node[] countries, Node candidate, boolean isBottom) {

        int position = -1;

        for (int i = 0; i < countries.length; i++) {
            if (countries[i] == null) {
                countries[i] = candidate;
                return;
            }

            boolean betterCandidate =
                (isBottom && candidate.happiness < countries[i].happiness)
                || (!isBottom && candidate.happiness > countries[i].happiness);

            if (betterCandidate) {
                if (position == -1
                        || (isBottom && countries[i].happiness
                            > countries[position].happiness)
                        || (!isBottom && countries[i].happiness
                            < countries[position].happiness)) {

                    position = i;
                }
            }
        }

        if (position != -1) {
            countries[position] = candidate;
        }
    }

    /**
     * Sorts the ranking array by happiness score.
     */
    private void sortByHappiness(Node[] countries, boolean ascending) {
        for (int i = 0; i < countries.length - 1; i++) {
            for (int j = i + 1; j < countries.length; j++) {
                if (countries[i] == null || countries[j] == null) {
                    continue;
                }

                boolean shouldSwap =
                    (ascending && countries[i].happiness
                        > countries[j].happiness)
                    || (!ascending && countries[i].happiness
                        < countries[j].happiness);

                if (shouldSwap) {
                    Node temp = countries[i];
                    countries[i] = countries[j];
                    countries[j] = temp;
                }
            }
        }
    }
}