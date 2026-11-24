import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A menu-driven application that stores and manages country happiness
 * data using a custom binary search tree.
 *
 * Supports CSV file loading, tree traversals, insertion, deletion,
 * searching, and happiness rankings.
 *
 * @author Keith Brandenburg
 */
public class countryBinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the file name: ");
            String fileName = scanner.nextLine().trim();

            int count;

            try {
                count = loadCountries(fileName, bst);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
                return;
            }

            System.out.println(
                "\nSuccessfully loaded " + count + " countries into the tree."
            );

            int choice;

            do {
                printMenu();
                choice = readInt(scanner, "Enter your choice: ", 1, 9);

                System.out.println();

                switch (choice) {
                    case 1 -> {
                        printHeader("Inorder Traversal");
                        bst.printInorder();
                    }

                    case 2 -> {
                        printHeader("Preorder Traversal");
                        bst.printPreorder();
                    }

                    case 3 -> {
                        printHeader("Postorder Traversal");
                        bst.printPostorder();
                    }

                    case 4 -> insertCountry(scanner, bst);

                    case 5 -> {
                        String name = readCountryName(scanner);
                        bst.delete(name);
                    }

                    case 6 -> searchCountry(scanner, bst);

                    case 7 -> printRankings(scanner, bst, true);

                    case 8 -> printRankings(scanner, bst, false);

                    case 9 -> System.out.println("Have a good day!");
                }

            } while (choice != 9);
        }
    }

    /**
     * Loads country names and happiness scores from a CSV file.
     *
     * The first row is treated as a header. Country names are stored
     * in the first column and happiness scores in the sixth column.
     *
     * Invalid records and duplicate countries are skipped.
     *
     * @return the number of countries successfully inserted
     */
    private static int loadCountries(
            String fileName, BinarySearchTree bst) throws IOException {

        int count = 0;
        int skipped = 0;

        try (BufferedReader reader =
                new BufferedReader(new FileReader(fileName))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length < 6) {
                    skipped++;
                    continue;
                }

                try {
                    String name = parts[0].trim();
                    double happiness = Double.parseDouble(parts[5].trim());

                    if (name.isEmpty()
                            || !Double.isFinite(happiness)
                            || happiness < 0) {

                        skipped++;
                        continue;
                    }

                    if (bst.insert(name, happiness)) {
                        count++;
                    } else {
                        skipped++;
                    }

                } catch (NumberFormatException e) {
                    skipped++;
                }
            }
        }

        if (skipped > 0) {
            System.out.println(
                "Skipped " + skipped + " invalid or duplicate records."
            );
        }

        return count;
    }

    /**
     * Displays the available menu options.
     */
    private static void printMenu() {
        System.out.println("\n===== Country Binary Search Tree =====");
        System.out.println("1) Print tree inorder");
        System.out.println("2) Print tree preorder");
        System.out.println("3) Print tree postorder");
        System.out.println("4) Insert a country");
        System.out.println("5) Delete a country");
        System.out.println("6) Search for a country");
        System.out.println("7) Print countries with lowest happiness");
        System.out.println("8) Print countries with highest happiness");
        System.out.println("9) Exit");
    }

    /**
     * Displays a formatted heading for country data.
     */
    private static void printHeader(String title) {
        System.out.println(title);
        System.out.printf("%-45s %s%n", "Name", "Happiness");
        System.out.println(
            "--------------------------------------------------------"
        );
    }

    /**
     * Prompts the user for a nonempty country name.
     */
    private static String readCountryName(Scanner scanner) {
        while (true) {
            System.out.print("Enter country name: ");
            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Country name cannot be empty.");
        }
    }

    /**
     * Reads an integer within the specified range.
     */
    private static int readInt(
            Scanner scanner, String prompt, int min, int max) {

        while (true) {
            System.out.print(prompt);

            try {
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Prompt again if the input is not an integer.
            }

            System.out.printf(
                "Invalid input. Enter an integer between %d and %d.%n",
                min, max
            );
        }
    }

    /**
     * Reads a finite, nonnegative happiness score.
     */
    private static double readHappiness(Scanner scanner) {
        while (true) {
            System.out.print("Enter country happiness: ");

            try {
                double happiness =
                    Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(happiness) && happiness >= 0) {
                    return happiness;
                }
            } catch (NumberFormatException e) {
                // Prompt again if the input is not numeric.
            }

            System.out.println("Enter a valid nonnegative happiness score.");
        }
    }

    /**
     * Prompts the user for country data and inserts it into the tree.
     */
    private static void insertCountry(
            Scanner scanner, BinarySearchTree bst) {

        String name = readCountryName(scanner);
        double happiness = readHappiness(scanner);

        if (bst.insert(name, happiness)) {
            System.out.printf(
                "%s with a happiness score of %.3f was inserted.%n",
                name, happiness
            );
        } else {
            System.out.println(name + " already exists in the tree.");
        }
    }

    /**
     * Searches for a country and displays its happiness and search path.
     */
    private static void searchCountry(
            Scanner scanner, BinarySearchTree bst) {

        String name = readCountryName(scanner);

        if (bst.find(name, false) == -1) {
            System.out.println(name + " was not found.");
        }
    }

    /**
     * Displays the highest or lowest ranked countries by happiness.
     */
    private static void printRankings(
            Scanner scanner, BinarySearchTree bst, boolean isBottom) {

        int count = readInt(
            scanner,
            "Enter the number of countries: ",
            1,
            Integer.MAX_VALUE
        );

        if (isBottom) {
            printHeader("Bottom " + count + " Countries by Happiness");
            bst.printBottomCountries(count);
        } else {
            printHeader("Top " + count + " Countries by Happiness");
            bst.printTopCountries(count);
        }
    }
}