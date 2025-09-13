//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Hobbemoun Tree Java
// Course: CS 300 Spring 2025
//
// Author: Olivia Ruby
// Email: oruby@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Anish Mantri
// Partner Email: agmantri@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: N/A
// Online Sources:
// - https://hackajob.com/talent/blog/implementing-binary-search-trees-in-java#:~:text=Binary%20sea
// rch%20trees%20(BST)%20are,are%20greater%20than%20the%20root.
// Helped with our understanding of inserting nodes in a binary tree.
// - https://www.enjoyalgorithms.com/blog/validate-binary-search-tree
// Helped with our understanding of a valid binary tree.
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements an Iterable binary search tree for storing Hobbemoun objects.
 *
 * The tree maintains ordering based on Hobbemoun's compareTo method and provides operations for
 * inserting, removing,and traversing Hobbemouns.
 *
 * Notes: 1) You may NOT use any arrays or Collections objects (ArrayLists, etc) in this class. 2)
 * You may NOT use any loops (for, while, etc) in this class. Recursive strategies only.
 *
 */
public class HobbemounTree implements Iterable<Hobbemoun> {
  /**
   * The root of this HobbemounTree. Set to null when tree is empty.
   */
  private Node<Hobbemoun> root;

  /**
   * The size of this HobbemounTree (total number of Hobbemoun stored in this BST)
   */
  private int size;

  /**
   * Constructor for HobbemounTree. Should set size to 0 and root to null.
   */
  public HobbemounTree() {
    this.size = 0;
    this.root = null;
  }

  /**
   * Constructor for HobbemounTree that takes a root node and initializes the tree with it. It
   * should make sure the root node is the root of a valid BST. This constructor is useful for
   * testing (particularly insert), as one can manually set the tree state.
   *
   * This constructor also sets the size of the tree to the number of elements stored in the tree
   * rooted at the input root. See countNodes private helper method.
   *
   * @param root the root of the tree
   * @throws IllegalArgumentException if the input root is not the root of a valid BST
   */
  public HobbemounTree(Node<Hobbemoun> root) {
    if (!isValidBST(root)) {
      throw new IllegalArgumentException("ERROR: The input root isn't the root of a valid BST!");
    }
    this.root = root;
    this.size = countNodes(root);
  }


  /**
   * Returns the number of nodes in the tree. While not required, this recursive method may be
   * helpful in setting the size of the tree in the HobbemounTree(root) constructor.
   *
   * @return the number of nodes in the tree
   */
  private int countNodes(Node<Hobbemoun> node) {
    // Reached end of tree or the tree is empty.
    if (node == null) {
      return 0;
    }

    // Count the current node + the nodes in the left and right subtrees.
    return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
  }

  /**
   * Getter method for the Node<Hobbemoun> at the root of this BST. This method is used for testing
   * purposes (eg, to check if the current state of the tree is a valid BST). It should return a
   * deep copy of the root node.
   *
   * @return a deep copy of the root of the BST.
   */
  protected Node<Hobbemoun> getRoot() {
    return getRootHelper(root);
  }


  /**
   * Helper method to get a deep copy of the root of the BST.
   *
   * @param node the root node of the BST
   * @return a deep copy of the root node
   */
  private Node<Hobbemoun> getRootHelper(Node<Hobbemoun> node) {
    // empty tree
    if (node == null) {
      return null;
    }
    // non-empty tree: make a deep copy of the tree rooted at node in the pre-order traversal
    // fashion
    // duplicate self - left - right
    return new Node<>(node.getData(), getRootHelper(node.getLeft()),
        getRootHelper(node.getRight()));
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Checks if the binary subtree rooted at the given node is a valid binary search tree.
   *
   * In order to be a valid BST, the following must be true: For every internal (non-leaf) node X of
   * a binary tree, all the values in the node's left subtree are less than the value in X, and all
   * the values in the node's right subtree are greater than the value in X.
   *
   * @param node the root node of the binary tree
   * @return true if the binary tree is a valid binary search tree, false otherwise
   */
  public static boolean isValidBST(Node<Hobbemoun> node) {
    return isValidBSTHelper(node, null, null);
  }


  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Helper method to check if a binary subtree is a valid BST recursively. We use min and max
   * Hobbemoun values to check if each node's value is within the valid range for its position in
   * the tree.
   *
   * @param node the current node to check
   * @param min  the minimum allowed value for this subtree (null means no minimum)
   * @param max  the maximum allowed value for this subtree (null means no maximum)
   * @return true if the subtree rooted at node is a valid BST, false otherwise
   */
  // CITE: EnjoyAlgorithms to further our understanding of valid binary trees
  private static boolean isValidBSTHelper(Node<Hobbemoun> node, Hobbemoun min, Hobbemoun max) {
    // empty tree
    if (node == null) {
      return true;
    }

    // get the hobbemoun data of this node
    Hobbemoun data = node.getData();

    // check if the node violates the min/max constraint
    if ((min != null && data.compareTo(min) <= 0) || (max != null && data.compareTo(max) >= 0)) {
      return false;
    }

    // recursively validate the left and right subtrees
    return isValidBSTHelper(node.getLeft(), min, data)
        && isValidBSTHelper(node.getRight(), data, max);
  }

  /**
   * Checks whether this HobbemounTree is empty or not
   *
   * @return true if this tree is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Gets the size of this HobbemounTree
   *
   * @return the total number of Hobbemouns stored in this tree
   */
  public int size() {
    return this.size;
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Adds a new Hobbemoun to this HobbemounTree. Duplicate Hobbemouns are NOT allowed.
   *
   * @param newHobbemoun Hobbemoun to add to this HobbemounTree
   * @throws IllegalArgumentException with a descriptive error message if newHobbemoun is null.
   * @return true if the newHobbemoun is successfully added to this HobbemounTree, false otherwise.
   */
  public boolean insert(Hobbemoun newHobbemoun) {
    // Ensure that the new Hobbemoun isn't null
    if (newHobbemoun == null) {
      throw new IllegalArgumentException("ERROR: the hobbemoun cannot be null!");
    }

    boolean inserted;

    // If the tree is empty, set the root to the new hobbemoun
    if (root == null) {
      root = new Node<>(newHobbemoun);
      inserted = true;
    } else {
      // Call the helper method to recursively insert.
      inserted = insertHelper(newHobbemoun, root);
    }

    // If the insertion was sucessful, increment the size of the tree
    if (inserted) {
      size++;
    }

    return inserted;
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive helper method to insert a new Hobbemoun to a Pokedex rooted at node.
   *
   * @param node         The "root" of the subtree we are inserting the new Hobbemoun into.
   * @param newHobbemoun The Hobbemoun to be added to a BST rooted at node. We assume that
   *                     newHobbemoun is NOT null.
   * @return true if the newHobbemoun is successfully added to this HobbemounTree, false otherwise.
   */
  // CITE: HackAJob to help our understanding of inserting nodes into a BST.
  private static boolean insertHelper(Hobbemoun newHobbemoun, Node<Hobbemoun> node) {
    if (node == null) {
      // Create a new node with the new hobbemoun
      node = new Node<>(newHobbemoun);
      return true;
    }


    int comparison = newHobbemoun.compareTo(node.getData());

    // If comparison is 0, return false because it's a duplicate
    if (comparison == 0) {
      return false;
    }

    // If the new hobbemoun is smaller, go to the left subtree
    if (comparison < 0) {
      if (node.getLeft() == null) {
        // Set the hobbemoun node as the left node.
        node.setLeft(new Node<>(newHobbemoun));
        return true;
      } else {
        return insertHelper(newHobbemoun, node.getLeft());
      }
    }

    // If the new hobbemoun is bigger, go to the right subtree
    if (comparison > 0) {
      if (node.getRight() == null) {
        // Set the hobbemoun node as the right node.
        node.setRight(new Node<>(newHobbemoun));
        return true;
      } else {
        return insertHelper(newHobbemoun, node.getRight());
      }
    }

    // If it reaches here, something went wrong.
    return false;
  }



  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Searches a Hobbemoun given its first and second identifiers.
   *
   * @param firstId  First identifier of the Hobbemoun to find
   * @param secondId Second identifier of the Hobbemoun to find
   * @return the matching Hobbemoun if match found, null otherwise.
   */
  public Hobbemoun lookup(int firstId, int secondId) {

    // Creates a hobbemoun with the two identifiers
    Hobbemoun toFind = new Hobbemoun(firstId, secondId);

    // Return the lookup helper to search for the hobbemoun starting from the root.
    return lookupHelper(toFind, root);
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive helper method to search and return a match with a given Hobbemoun in the subtree
   * rooted at node, if present.
   *
   * @param toFind a Hobbemoun to be searched in the BST rooted at node. We assume that toFind is
   *               NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match to target.
   * @return a reference to the matching Hobbemoun if found, null otherwise.
   */
  private static Hobbemoun lookupHelper(Hobbemoun toFind, Node<Hobbemoun> node) {
    // Base case: if the node is null, the element hasn't been found
    if (node == null) {
      return null;
    }

    Hobbemoun curr = node.getData();

    int comparison = toFind.compareTo(curr);

    // Found a match, return it
    if (comparison == 0) {
      return curr;
    }

    // If the hobbemoun to find is greater than the current node, go to the right subtree
    else if (comparison > 0) {
      return lookupHelper(toFind, node.getRight());

    } else {

      // If the hobbemoun to find is less than the current node, go to the left subtree
      return lookupHelper(toFind, node.getLeft());
    }

  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive helper method that computes the height of the subtree rooted at node counting the
   * number of nodes and NOT the number of edges from node to the deepest leaf
   *
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  private static int heightHelper(Node<Hobbemoun> node) {
    // If tree is empty or reached the end.
    if (node == null) {
      return 0;
    }
    // Get the height on the left subtree.
    int leftHeight = heightHelper(node.getLeft());

    // Get the height on the right subtree.
    int rightHeight = heightHelper(node.getRight());

    // Add the left and right heights + the rooted node.
    return 1 + Math.max(leftHeight, rightHeight);
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive method to find and return the first Hobbemoun, in the increasing order, within this
   * HobbemounTree (meaning the smallest element stored in the tree). As long as this is a valid
   * BST, getting the leftmost value in the tree should satisfy this.
   *
   * @return the first element in the increasing order of this BST, and null if the tree is empty.
   */
  public Hobbemoun getWeakest() {
    Node<Hobbemoun> leftMost = getLeftmostHelper(root); // root of the tree

    if (leftMost == null) {
      return null; // Empty tree
    }
    return leftMost.getData();
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive helper method to find and return the leftmost node in the subtree rooted at node.
   *
   * @param root the node from which to find the the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  private static Node<Hobbemoun> getLeftmostHelper(Node<Hobbemoun> root) {
    // Base case
    if (root == null) {
      return null;
    }

    // The most left tree no more left nodes remaining
    if (root.getLeft() == null) {
      return root;
    }

    // Recursive call if the weakest node has not been reached yet
    return getLeftmostHelper(root.getLeft());
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive method to find and return the last Hobbemoun, in the increasing order, within this
   * HobbemounTree (meaning the greatest element stored in the tree). As long as this is a valid
   * BST, getting the rightmost value in the tree should satisfy this.
   *
   * @return the last element in the increasing order of this BST, and null if the tree is empty.
   */
  public Hobbemoun getStrongest() {
    // Start from the root of the tree
    Node<Hobbemoun> rightMost = getRightmostHelper(root);

    if (rightMost == null) {
      return null; // Empty tree, return null
    }

    // Data of the strongest/rightmost node
    return rightMost.getData();
  }

  /**
   * Recursive helper method to find and return the rightmost node in the subtree rooted at node.
   *
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  private static Node<Hobbemoun> getRightmostHelper(Node<Hobbemoun> root) {
    // If tree is empty
    if (root == null) {
      return null;
    }

    // Base case: this is the rightmost/strongest node
    if (root.getRight() == null) {
      return root;
    }

    // If rightmost node is not found, recursive call
    return getRightmostHelper(root.getRight());
  }


  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Finds and returns the in-order successor of a specified Hobbemoun in this HobbemounTree
   *
   * @param hobbemoun the Hobbemoun to find its successor
   * @return the in-order successor of a specified Hobbemoun in this HobbemounTree. If there is no
   *         successor, return null.
   *
   * @throws IllegalArgumentException with a descriptive error message if <b>hobbemoun</b> is null
   */
  public Hobbemoun next(Hobbemoun hobbemoun) {
    if (hobbemoun == null) {
      throw new IllegalArgumentException("ERROR: Input hobbemoun cannot be null");
    }

    return nextHelper(hobbemoun, root, null);
  }

  /**
   * MUST BE IMPLEMENTED RECURSIVELY - NO LOOPS
   *
   * Recursive helper method to find and return the next Hobbemoun in the tree rooted at node.
   *
   * @param hobbemoun a Hobbemoun to search its in-order successor. We assume that <b>hobbemoun</b>
   *                  is NOT null.
   * @param node      "root" of a subtree storing Hobbemoun objects
   * @param next      a Node which stores a potentional candidate for next node
   * @return the next Hobbemoun in the tree rooted at node. If there is no next Hobbemoun, return
   *         null.
   */
  private static Hobbemoun nextHelper(Hobbemoun hobbemoun, Node<Hobbemoun> node,
      Node<Hobbemoun> next) {
    // Base case: node is null
    if (node == null) {
      return null;
    }
    int cmp = hobbemoun.compareTo(node.getData());

    // If both the hobbemouns are equal
    if (cmp == 0) {

      if (node.getRight() != null) {
        Node<Hobbemoun> leftMost = getLeftmostHelper(node.getRight());
        if (leftMost != null) {
          return leftMost.getData();
        } else {
          return null;
        }

      }
      if (next != null) {
        return next.getData();
      } else {
        return null;
      }

    }
    // check to see if one hobbemoun is greater than the other
    if (cmp < 0) {
      return nextHelper(hobbemoun, node.getLeft(), node);
    }

    // if the comparison is greater than 0
    return nextHelper(hobbemoun, node.getRight(), next);
  }

  /**
   * Returns an iterator over the Hobbemoun objects in this HobbemounTree using in-order travesal
   * (smallest to largest).
   *
   * @return an HobbemounIterator over the Hobbemoun objects in this HobbemounTree
   */
  @Override
  public Iterator<Hobbemoun> iterator() {
    return new HobbemounIterator(this);
  }

  /**
   * YOU MUST USE AN ENHANCED FOR LOOP TO IMPLEMENT THIS METHOD.
   *
   * Returns a String representation of all the Hobbemouns stored within this HobbemounTree in the
   * increasing order with respect to the result of Hobbemoun.compareTo() method. The string should
   * have the Hobbemoun toString() return value on a new line; one Hobbemoun per line:
   *
   * "nameOne (12.25)\nnameTwo (12, 56)\nnameTwo (89,27)"
   *
   *
   * @return a string containing all the Hobbemoun, in the increasing order. Returns an empty string
   *         "" if this BST is empty.
   *
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    for (Hobbemoun i : this) {
      str.append(i.toString()).append("\n");
    }

    return str.toString().trim();
  }

  /**
   * OPTIONAL - NOT REQUIRED BUT YOU PROBABLY WANT TO KNOW HOW TO DO THIS JUST SAYING
   *
   * Removes a specific Hobbemoun from this HobbemounTree, recursively.
   *
   * @param hobbemoun the Hobbemoun to remove
   * @return true if the specific Hobbemoun is successfully removed, false if no match found with
   *         any Hobbemoun in this tree.
   * @throws IllegalArgumentException with a descriptive error message if <b>hobbemoun</b> is null
   */
  public boolean remove(Hobbemoun hobbemoun) {
    // Ensure that the hobbemoun isn't null
    if (hobbemoun == null) {
      throw new IllegalArgumentException("ERROR: input hobbemoun cannot be null");
    }

    try {
      root = removeHobbemounHelper(hobbemoun, root);
      return true;
    } catch (NoSuchElementException e) {
      // Target wasn't in the tree, return false
      return false;
    }



  }

  /**
   * OPTIONAL - NOT REQUIRED BUT YOU PROBABLY WANT TO KNOW HOW TO DO THIS JUST SAYING
   *
   * Recursive helper method to search and remove a specific Hobbemoun from the BST rooted at node
   *
   * @param target a reference to a Hobbemoun to remove from the BST rooted at node. We assume that
   *               target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match with the target
   *               Hobbemoun.
   *
   *
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Hobbemoun
   *                                matching target in the BST rooted at <b>node</b>
   */
  private static Node<Hobbemoun> removeHobbemounHelper(Hobbemoun target, Node<Hobbemoun> node) {
    // Compare the target to the data at node and proceed accordingly
    if (node == null) {
      throw new NoSuchElementException("ERROR: Hobbemoun not found in the tree");
    }

    int compare = target.compareTo(node.getData());

    // Recurse on the left or right subtree with respect to the comparison result
    if (compare < 0) {
      // node = node with updated left child
      return new Node<>(node.getData(), removeHobbemounHelper(target, node.getLeft()),
          node.getRight());

    } else if (compare > 0) {
      return new Node<>(node.getData(), node.getLeft(),
          removeHobbemounHelper(target, node.getRight()));

    } else {
      // If match with target found, three cases should be considered.

      // Case 1: node may be a leaf (has no children), set node to null
      if (node.getLeft() == null && node.getRight() == null) {
        return null;
      }

      // Case 2: node may have only one child, set node to that child (whether left or right child)
      if (node.getLeft() == null) {
        return node.getRight();
      }

      if (node.getRight() == null) {
        return node.getLeft();
      }

      // Case 3: node may have two children, replace node with a new node whose data field is the
      // successor of target in the tree
      Node<Hobbemoun> successor = node.getRight();
      while (successor.getLeft() != null) {
        successor = successor.getLeft();
      }

      // Then remove the successor from the right subtree
      Node<Hobbemoun> nextRight = removeHobbemounHelper(successor.getData(), node.getRight());

      // Make sure to return node at the end of each case or end of method
      return new Node<>(successor.getData(), node.getLeft(), nextRight);

    }
  }

}
