import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * 
 * This class prompts the user for a file of countries then creates a binary search tree. Then the
 * program will prompt the user with a menu that offers the ability to print the tree inorder,
 * preorder, and postorder, insert a country with name and happiness index, delete a country with
 * a given name, search and print a country and its path with a given name, print the bottom countries
 * according to happiness, and print the top countries according to happiness.
 * 
 * @author Keith Brandenburg
 * @version 7 April 2025
 */
public class countryBinarySearchTree {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        System.out.println();

        int count = 0;
        try {
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String name = parts[0];
                    double happiness = Double.parseDouble(parts[5]);
                    bst.insert(name, happiness);
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            scanner.close();
            return;
        }

        System.out.println("There were " + count + " records read to build a binary search tree.\n");

        int choice;
        do {
            System.out.println("1) Print tree inorder\n2) Print tree preorder\n3) Print tree postorder");
            System.out.println("4) Insert a country with name and happiness");
            System.out.println("5) Delete a country for a given name");
            System.out.println("6) Search and print a country and its path for a given name");
            System.out.println("7) Print bottom countries regarding happiness");
            System.out.println("8) Print top countries regarding happiness");
            System.out.println("9) Exit");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid choice enter 1-9: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Inorder traversal:\nName                                          Happiness\n--------------------------------------------------------");
                    bst.printInorder();
                }
                case 2 -> {
                    System.out.println("Preorder traversal:\nName                                          Happiness\n--------------------------------------------------------");
                    bst.printPreorder();
                }
                case 3 -> {
                    System.out.println("Postorder traversal:\nName                                          Happiness\n--------------------------------------------------------");
                    bst.printPostorder();
                }
                case 4 -> {
                    System.out.print("Enter country name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter country happiness: ");
                    double happiness = scanner.nextDouble();
                    scanner.nextLine();
                    bst.insert(name, happiness);
                    System.out.println(name + " with happiness of " + happiness + " is inserted.");
                }
                case 5 -> {
                    System.out.print("Enter country name: ");
                    String name = scanner.nextLine();
                    bst.delete(name);
                }
                case 6 -> {
                    System.out.print("Enter country name: ");
                    String name = scanner.nextLine();
                    double result = bst.find(name, false);
                    if (result == -1) {
                        System.out.println(name + " is not found.");
                        }
                }
                case 7 -> {
                    System.out.print("Enter the number of countries: ");
                    int c = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Bottom " + c + " countries regarding happiness:\nName                                          Happiness\n--------------------------------------------------------");
                    bst.printBottomCountries(c);
                }
                case 8 -> {
                    System.out.print("Enter the number of countries: ");
                    int c = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Top " + c + " countries regarding happiness:\nName                                          Happiness\n--------------------------------------------------------");
                    bst.printTopCountries(c);
                }
                case 9 -> System.out.println("Have a good day!");
                default -> System.out.println("Invalid choice enter 1-9:");
            }
        } while (choice != 9);
        scanner.close();
    }
}