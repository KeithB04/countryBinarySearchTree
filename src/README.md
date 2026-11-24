# Country Binary Search Tree

A Java-based command-line application that manages country happiness
data using a custom implementation of a Binary Search Tree (BST).
The program reads country data from a CSV file and provides an
interactive menu for performing tree operations, searching for
countries, and ranking countries by happiness score.

## Features

- **CSV File Loading:** Reads country names and happiness scores
  from a CSV file.
- **Tree Traversals:** Supports three traversal methods:
  - Inorder traversal
  - Preorder traversal
  - Postorder traversal
- **Country Insertion:** Adds new countries while maintaining
  the binary search tree structure.
- **Country Deletion:** Removes countries while preserving
  the properties of the binary search tree.
- **Country Searching:** Performs case-insensitive searches
  and displays the path from the root to the requested country.
- **Happiness Rankings:** Displays a specified number of countries
  with the highest or lowest happiness scores.
- **Duplicate Prevention:** Prevents duplicate country names
  from being inserted into the tree.
- **Input Validation:** Handles invalid user input, malformed
  records, and file-reading errors.
- **Interactive Menu:** Allows users to perform multiple
  operations without restarting the application.

## Technologies and Concepts

- Java
- Object-Oriented Programming (OOP)
- Binary Search Trees
- Recursive Algorithms
- Tree Traversal Algorithms
- Searching and Sorting
- CSV File Processing
- Exception Handling
- Input Validation

## Project Structure

```text
src/
├── countryBinarySearchTree.java
├── BinarySearchTree.java
├── Node.java
└── README.md
data/
├── countries.csv
```

| File | Description |
| --- | --- |
| `CountryBinarySearchTree.java` | Main application responsible for file loading, user interaction, and menu operations. |
| `BinarySearchTree.java` | Implements the binary search tree, including insertion, deletion, searching, traversal, and happiness rankings. |
| `Node.java` | Represents an individual tree node containing country data and child references. |
| `countries.csv` | Sample dataset containing country information. |
| `README.md` | Project documentation and usage instructions. |

## How It Works

The program loads country data from a CSV file and inserts each
country into a binary search tree using its name as the key.

Country names are compared alphabetically without considering
capitalization. Countries that come alphabetically before the
current node are placed in its left subtree, while countries
that come after it are placed in its right subtree.

Each node stores:

- Country name
- Happiness score
- Reference to the left child
- Reference to the right child

When deleting a node with two children, the program uses its
inorder successor to maintain the binary search tree structure.

Happiness rankings are calculated by traversing the tree,
maintaining an array of ranking candidates, and sorting the
selected countries by their happiness scores.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or newer
- A terminal or command prompt

### Compilation

Navigate to the project directory and compile the Java files:

```bash
javac CountryBinarySearchTree.java BinarySearchTree.java Node.java
```

### Running the Program

```bash
java CountryBinarySearchTree
```

When prompted, enter the name of the CSV file:

```text
Enter the file name: countries.csv
```

A sample CSV file is included in the repository.

## CSV File Format

The program expects a CSV file containing a header row and
at least six columns. The country name must appear in the
first column and the happiness score in the sixth column.

Example:

```csv
Name,Capital,Population,GDP,Area,Happiness
Germany,Berlin,83200000,4080000000000,357022,6.892
Canada,Ottawa,38900000,2140000000000,9984670,6.961
Japan,Tokyo,125100000,4230000000000,377975,6.129
```

Only the country name and happiness score are stored in
the binary search tree.

The included dataset contains illustrative values intended
for testing and demonstration purposes.

## Menu Options

```text
===== Country Binary Search Tree =====
1) Print tree inorder
2) Print tree preorder
3) Print tree postorder
4) Insert a country
5) Delete a country
6) Search for a country
7) Print countries with lowest happiness
8) Print countries with highest happiness
9) Exit
```

## Example Output

### Searching for a Country

```text
Enter your choice: 6
Enter country name: Japan

Japan was found with a happiness score of 6.129.
Path to Japan: Germany -> Japan
```

### Top Three Countries by Happiness

```text
Enter your choice: 8
Enter the number of countries: 3

Top 3 Countries by Happiness
Name                                          Happiness
--------------------------------------------------------
Finland                                       7.804
Denmark                                       7.586
Iceland                                       7.530
```

### Bottom Three Countries by Happiness

```text
Enter your choice: 7
Enter the number of countries: 3

Bottom 3 Countries by Happiness
Name                                          Happiness
--------------------------------------------------------
India                                         4.036
South Africa                                  5.275
China                                         5.818
```

## Algorithm Complexity

| Operation | Average Time | Worst-Case Time | Auxiliary Space |
| --- | --- | --- | --- |
| Insertion | O(log n) | O(n) | O(h) |
| Deletion | O(log n) | O(n) | O(h) |
| Search | O(log n) | O(n) | O(h) |
| Inorder Traversal | O(n) | O(n) | O(h) |
| Preorder Traversal | O(n) | O(n) | O(h) |
| Postorder Traversal | O(n) | O(n) | O(h) |
| Happiness Rankings | O(nk + k²) | O(nk + k²) | O(k + h) |

Where:

- `n` is the number of countries in the tree.
- `h` is the height of the binary search tree.
- `k` is the number of countries requested for a ranking.

The binary search tree requires O(n) total storage space.

Average logarithmic performance assumes a reasonably balanced
tree. Because the tree is not self-balancing, its height can
reach O(n) in the worst case.

## Limitations

- The binary search tree is not self-balancing.
- CSV fields containing quoted commas are not supported.
- Extremely large ranking requests may consume excessive memory.
- Changes made during execution are not saved to the CSV file.

## Potential Future Improvements

- Implement a self-balancing tree, such as an AVL tree.
- Use a priority queue to improve happiness ranking efficiency.
- Add support for exporting updated country data to a CSV file.
- Implement comprehensive unit tests using JUnit.
- Add support for additional country statistics.

## Author

**Keith Brandenburg**
