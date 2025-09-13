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
// - Referenced geeks for geeks for questions with exception handling
// https://www.geeksforgeeks.org/exceptions-in-java/
// - Referenced commenting style guide
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a tester class for the functionality of the HobbemounTree and Hobbemoun classes
 */
public class HobbemounTester {

  /**
   * Tests the compareTo method when comparing Hobbemouns with different type values
   *
   * @return true if test passes, false otherwise
   */
  public static boolean testCompareToByType() {
    // Test Scenario 1: Tests compareTo returns a positive integer when the hobbemoun's primary type
    // is greater than the other hobbemoun's primary type.
    {
      // Create two hobbemouns, with one stronger than the other.
      Hobbemoun greaterHobbemoun = new Hobbemoun(HobbemounType.FIRE, null, null);
      Hobbemoun weakerHobbemoun = new Hobbemoun(HobbemounType.POISON, null, null);

      // Store the comparison as an integer
      int comparison = greaterHobbemoun.compareTo(weakerHobbemoun);

      // Ensure that the integer isn't negative
      if (comparison <= 0) {
        System.out.println("ERROR: a positive integer was expected");
        return false;
      }

    }

    // Test Scenario 2: Tests compareTo returns a negative integer when the hobbemoun's primary type
    // is less than the other hobbemoun's primary type
    {
      // Create two hobbemouns, with one weaker than the other.
      Hobbemoun weakerHobbemoun = new Hobbemoun(HobbemounType.POISON, null, null);
      Hobbemoun greaterHobbemoun = new Hobbemoun(HobbemounType.FIRE, null, null);

      // Store the comparison as an integer
      int comparison = weakerHobbemoun.compareTo(greaterHobbemoun);

      // Ensure that the integer isn't positive
      if (comparison >= 0) {
        System.out.println("ERROR: a negative integer was expected");
        return false;
      }
    }


    // Test Scenario 3: Tests compareTo returns a positive integer when the hobbemoun's secondary
    // type is greater than the other hobbemoun's secondary type. The hobbemoun's primary types
    // are the same
    {
      // Create two hobbemouns, with one greater than the other and with the same primary types.
      Hobbemoun greaterHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.FIRE, null);
      Hobbemoun weakerHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.POISON, null);

      // Store the comparison as an integer
      int comparison = greaterHobbemoun.compareTo(weakerHobbemoun);

      // Ensure that the integer isn't negative
      if (comparison <= 0) {
        System.out.println("ERROR: a positive integer was expected");
        return false;
      }
    }


    // Test Scenario 4: Tests compareTo returns a negative integer when the hobbemoun's secondary
    // type is less than the other hobbemoun's secondary type. The hobbemoun's primary types
    // are the same
    {
      // Create two hobbemouns, with one weaker than the other and with the same primary types.
      Hobbemoun weakerHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.POISON, null);
      Hobbemoun greaterHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.FIRE, null);

      // Store the comparison as an integer
      int comparison = weakerHobbemoun.compareTo(greaterHobbemoun);

      // Ensure that the integer isn't positive
      if (comparison >= 0) {
        System.out.println("ERROR: a negative integer was expected");
        return false;
      }
    }

    return true; // All tests passed

  }

  /**
   * Tests the compareTo method when comparing Hobbemouns with same type values but different names
   *
   * @return true if test passes, false otherwise
   */
  public static boolean testCompareToByName() {
    // Test Scenario 1: Tests compareTo returns a positive integer when the hobbemoun's name is
    // greater than the other hobbemoun's name. The hobbemoun's primary and secondary types
    // are the same.
    {
      // Create two hobbemouns, with one name greater than the other and with the same
      // primary and secondary types.
      Hobbemoun greaterHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.POISON, "C");
      Hobbemoun weakerHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.POISON, "A");

      // Store the comparison as an integer
      int comparison = greaterHobbemoun.compareTo(weakerHobbemoun);

      // Ensure that the integer isn't negative
      if (comparison <= 0) {
        System.out.println("ERROR: a positive integer was expected");
        return false;
      }

    }

    // Test Scenario 2: Tests compareTo returns a negative integer when the hobbemoun's name is
    // less than the other hobbemoun's name. The hobbemoun's primary and secondary types
    // are the same.
    {
      // Create two hobbemouns, with one name less than the other and with the same
      // primary and secondary types.
      Hobbemoun weakerHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.POISON, "A");
      Hobbemoun greaterHobbemoun = new Hobbemoun(HobbemounType.POISON, HobbemounType.POISON, "C");

      // Store the comparison as an integer
      int comparison = weakerHobbemoun.compareTo(greaterHobbemoun);

      // Ensure that the integer isn't positive
      if (comparison >= 0) {
        System.out.println("ERROR: a negative integer was expected");
        return false;
      }
    }


    return true; // All tests passed

  }

  /**
   * Tests the compareTo method when comparing Hobbemouns with same type values
   *
   * @return true if test passes, false otherwise
   */
  public static boolean testCompareToSame() {
    // Test Scenario 1: tests the compareTo method returns 0 when the primary types of the
    // hobbemouns are the same
    {
      // Create two hobbemouns with the same primary types.
      Hobbemoun hobbemoun1 = new Hobbemoun(HobbemounType.POISON, null, "A");
      Hobbemoun hobbemoun2 = new Hobbemoun(HobbemounType.POISON, null, "A");

      // Store the comparison as an integer
      int comparison = hobbemoun1.compareTo(hobbemoun2);

      // Ensure that the comparison is 0
      if (comparison != 0) {
        System.out.println("ERROR: 0 was expected");
        return false;
      }

    }

    // Test Scenario 2: tests the compareTo method returns 0 when the primary and secondary
    // types of the hobbemouns are the same
    {
      // Create two hobbemouns with the same primary and secondary types.
      Hobbemoun hobbemoun1 = new Hobbemoun(HobbemounType.POISON, HobbemounType.BUG, "A");
      Hobbemoun hobbemoun2 = new Hobbemoun(HobbemounType.POISON, HobbemounType.BUG, "A");

      // Store the comparison as an integer
      int comparison = hobbemoun1.compareTo(hobbemoun2);

      // Ensure that the comparison is 0
      if (comparison != 0) {
        System.out.println("ERROR: 0 was expected");
        return false;
      }
    }

    // Test Scenario 3: tests the compareTo method returns 0 when the primary types, secondary
    // types, and names of the hobbemouns are the same
    {
      // Create two hobbemouns with the same primary types, secondary types, and names.
      Hobbemoun hobbemoun1 = new Hobbemoun(HobbemounType.POISON, HobbemounType.BUG, "A");
      Hobbemoun hobbemoun2 = new Hobbemoun(HobbemounType.POISON, HobbemounType.BUG, "A");

      // Store the comparison as an integer
      int comparison = hobbemoun1.compareTo(hobbemoun2);

      // Ensure that the comparison is 0
      if (comparison != 0) {
        System.out.println("ERROR: 0 was expected");
        return false;
      }

    }
    return true; // All tests passed

  }

  /**
   * Tests that isEmpty returns true for an empty tree and false for a non-empty tree.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testIsEmpty() {
    // Test Scenario 1: Tests that isEmpty returns true for an empty tree.
    {
      // Create an empty tree.
      HobbemounTree emptyTree = new HobbemounTree();

      // isEmpty should return true.
      if (!emptyTree.isEmpty()) {
        return false;
      }

    }

    // Test Scenario 2: Tests that isEmpty returns false for a non-empty tree.
    {
      Hobbemoun hobbemoun1 = new Hobbemoun("Num1");
      Node<Hobbemoun> node1 = new Node<>(hobbemoun1);

      HobbemounTree nonEmptyTree = new HobbemounTree(node1);

      // isEmpty should return false.
      if (nonEmptyTree.isEmpty()) {
        return false;
      }

    }
    return true; // All tests passed
  }

  /**
   * Tests that size returns the correct number of Hobbemoun objects in the tree.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSize() {
    // Test Scenario 1: Tests that a size of 2 is returned.
    {
      // Create the hobbemouns
      Hobbemoun hobbemoun1 = new Hobbemoun("Child2");
      Hobbemoun hobbemoun2 = new Hobbemoun("Child1");

      // Create the nodes and link them.
      Node<Hobbemoun> node1 = new Node<>(hobbemoun1);
      Node<Hobbemoun> node2 = new Node<>(hobbemoun2);
      node1.setLeft(node2);

      HobbemounTree tree = new HobbemounTree(node1);

      // The size of the tree is expected to be true.
      if (tree.size() != 2) {
        return false;
      }

    }
    return true; // All tests passed
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testIsValidBSTEmpty() {
    // Create an empty hobbemoun tree.
    HobbemounTree emptyTree = new HobbemounTree();

    return HobbemounTree.isValidBST(emptyTree.getRoot());
  }

  /**
   * Tests that isValidBST returns true for a valid BST. Should use a tree with depth > 2.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testIsValidBSTValid() {
    {
      // Root
      Hobbemoun root = new Hobbemoun(HobbemounType.FIRE, HobbemounType.FIRE, "Root");

      // Left child of root
      Hobbemoun left = new Hobbemoun(HobbemounType.BUG, HobbemounType.BUG, "Left node");

      // Left child of right child of root
      Hobbemoun rightLeft =
          new Hobbemoun(HobbemounType.WATER, HobbemounType.WATER, "Right left node");

      // Right child of root
      Hobbemoun right = new Hobbemoun(HobbemounType.PSYCHIC, HobbemounType.PSYCHIC, "Right node");

      Node<Hobbemoun> rootNode = new Node<>(root);
      Node<Hobbemoun> leftNode = new Node<>(left);
      Node<Hobbemoun> rightLeftNode = new Node<>(rightLeft);
      Node<Hobbemoun> rightNode = new Node<>(right);

      rootNode.setLeft(leftNode);
      rootNode.setRight(rightNode);
      rightNode.setLeft(rightLeftNode);

      HobbemounTree tree = new HobbemounTree(rootNode);

      return HobbemounTree.isValidBST(tree.getRoot()); // Test passed
    }

  }

  /**
   * Tests that isValidBST returns false for an invalid BST. Should use a tree with depth > 2 and
   * include a case where the left subtree contains a node greater than the right subtree.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testIsValidBSTInvalid() {
    {
      // Root
      Hobbemoun root = new Hobbemoun(HobbemounType.FIRE, HobbemounType.FIRE, "Root");

      // Left child of root
      Hobbemoun left = new Hobbemoun(HobbemounType.BUG, HobbemounType.BUG, "Left node");

      // Left child of right child of root
      Hobbemoun rightLeft =
          new Hobbemoun(HobbemounType.WATER, HobbemounType.WATER, "Right left node");

      // Right child of the left child of root; VIOLATES BST
      Hobbemoun leftRight = new Hobbemoun(HobbemounType.ICE, HobbemounType.ICE, "Left right node");

      // Right child of root
      Hobbemoun right = new Hobbemoun(HobbemounType.PSYCHIC, HobbemounType.PSYCHIC, "Right node");

      Node<Hobbemoun> rootNode = new Node<>(root);
      Node<Hobbemoun> leftNode = new Node<>(left);
      Node<Hobbemoun> rightLeftNode = new Node<>(rightLeft);
      Node<Hobbemoun> rightNode = new Node<>(right);
      Node<Hobbemoun> leftRightNode = new Node<>(leftRight);


      rootNode.setLeft(leftNode);
      rootNode.setRight(rightNode);
      rightNode.setLeft(rightLeftNode);
      leftNode.setRight(leftRightNode); // This will break the BST

      try {
        HobbemounTree tree = new HobbemounTree(rootNode);
        return false;
      } catch (IllegalArgumentException e) {
        // Exception thrown for invalid tree
        return true;
      }

    }

  }

  /**
   * Tests that insert throws IllegalArgumentException when given null
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testInsertNullException() {
    {
      Hobbemoun root = new Hobbemoun("TEST ROOT");
      Node<Hobbemoun> testRoot = new Node<>(root);


      HobbemounTree tree = new HobbemounTree(testRoot);

      try {
        tree.insert(null);
        // Shouldn't be able to insert null
        return false;
      } catch (IllegalArgumentException e) {
        // Exception thrown for a null argument
        return true;
      }
    }
  }

  /**
   * Tests that insert correctly adds a Hobbemoun to an empty tree.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testInsertEmpty() {
    {
      boolean insertValid;

      // Create an empty tree
      HobbemounTree tree = new HobbemounTree();

      Hobbemoun testInsert = new Hobbemoun("TEST INSERT");

      // Store the return value of insert in the insertValid boolean
      insertValid = tree.insert(testInsert);

      // Ensure that the size was incremented after insertion.
      if (tree.size() != 1) {
        System.out.println("ERROR: Size wasn't updated");
        return false;
      }


      // Ensure that the root of the tree isn't null
      if (tree.getRoot() == null) {
        System.out.println("ERROR: Root is null");
        return false;
      }

      // Ensure that the root of the tree is the inserted hobbemoun
      if (!tree.getRoot().getData().equals(testInsert)) {
        System.out.println("ERROR: Root isn't the inserted node.");
        return false;
      }

      if (insertValid) {
        // Should return true if successful insert
        return true;
      } else {
        // Return false if the insertion wasn't successful
        System.out.println("ERROR: Insertion wasn't successful");
        return false;
      }



    }

  }

  /**
   * Tests that insert correctly adds multiple Hobbemoun objects to a non-empty tree.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testInsertMultiple() {
    {
      // Create an empty tree
      HobbemounTree tree = new HobbemounTree();

      // Create three hobbemouns objects to be inserted

      // Expected to be the root
      Hobbemoun object1 = new Hobbemoun("B");

      // Expected to be the left child
      Hobbemoun object2 = new Hobbemoun("A");

      // Expected to be the right child
      Hobbemoun object3 = new Hobbemoun("C");

      // Store the return values of insertion into multiple booleans
      boolean insert1 = tree.insert(object1);
      boolean insert2 = tree.insert(object2);
      boolean insert3 = tree.insert(object3);

      // Verify the structure of the BST
      if (!HobbemounTree.isValidBST(tree.getRoot())) {
        System.out.println("ERROR: Tree isn't valid");
        return false;
      }

      Node<Hobbemoun> root = tree.getRoot();

      // Ensure that the root isn't null
      if (root == null) {
        System.out.println("ERROR: Root shouldn't be null");
        return false;
      }

      // Check that the left subtree isn't null
      if (root.getLeft() == null) {
        System.out.println("ERROR: Left subtree is empty");
        return false;
      }

      // Check that the right subtree isn't null
      if (root.getRight() == null) {
        System.out.println("ERROR: Right subtree is empty");
        return false;
      }

      // Check the left child of the root is correct
      if (!root.getLeft().getData().equals(object2)) {
        System.out.println("ERROR: Left child is incorrect");
        return false;
      }

      // Check the right child of the root is correct
      if (!root.getRight().getData().equals(object3)) {
        System.out.println("ERROR: Right child is incorrect");
        return false;
      }

      // Ensure that the size was incremented correctly
      if (tree.size() != 3) {
        System.out.println("ERROR: Size wasn't incremented correctly");
        return false;
      }

      // Ensure that insert returned true for multiple insertions
      if (insert1 && insert2 && insert3) {
        // Insertion of multiple objects was successful
        return true;
      } else {
        System.out.println("ERROR: Insertion wasn't successful");
        // Insertion wasn't successful, return false
        return false;
      }



    }

  }

  /**
   * Tests that insert returns false when adding a duplicate Hobbemoun.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testInsertDuplicate() {
    {
      // Create an empty tree
      HobbemounTree tree = new HobbemounTree();

      // Create the first hobbemoun and insert it into the tree
      Hobbemoun hobbemoun1 = new Hobbemoun("A");
      tree.insert(hobbemoun1);

      // Create the duplicate hobbemoun to try to be inserted
      Hobbemoun hobbemoun2 = new Hobbemoun("A");

      // Store the return value of inserting a duplicate hobbemoun
      boolean insertFailed = tree.insert(hobbemoun2);

      if (tree.size() != 1) {
        System.out.println("ERROR: Size was incorrectly incremented");
        return false;
      }

      // Check that insert returns false for inserting a duplicate hobbemoun
      if (!insertFailed) {
        return true;
      } else {
        System.out.println("ERROR: Insertion expected to return false");
        return false;
      }
    }

  }

  /**
   * Tests the lookup method for finding existing and non-existing Hobbemoun objects.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testLookup() {
    // Test Scenario 1: tests the lookup method finds an existing Hobbemoun object and returns it
    {
      // Create an empty tree
      HobbemounTree tree = new HobbemounTree();


      // Root of the tree
      Hobbemoun root = new Hobbemoun(50, 70);

      // Left child of the root
      Hobbemoun leftChild = new Hobbemoun(30, 90);

      // Right child of the root
      Hobbemoun rightChild = new Hobbemoun(70, 100);

      // Insert the hobbemouns into the tree
      tree.insert(root);
      tree.insert(leftChild);
      tree.insert(rightChild);


      // Looking for the left child of the root and storing it as a hobbemoun
      Hobbemoun found = tree.lookup(30, 90);

      // Ensure that the found hobbemoun isn't null
      if (found == null) {
        System.out.println("ERROR: null was not supposed to be returned");
        return false;
      }

      // Ensure that the found hobbemoun has the correct IDs
      if (found.getFirstID() != 30 && found.getSecondID() != 90) {
        System.out.println("ERROR: the incorrect hobbemoun was found");
        return false;
      }
    }

    // Test Scenario 2: tests the lookup method doesn't find a non-existing Hobbemoun object, and
    // returns null
    {

      // Create an empty tree
      HobbemounTree tree = new HobbemounTree();


      // Root of the tree
      Hobbemoun root = new Hobbemoun(50, 70);

      // Left child of the root
      Hobbemoun leftChild = new Hobbemoun(30, 90);

      // Right child of the root
      Hobbemoun rightChild = new Hobbemoun(70, 100);

      // Insert the hobbemouns into the tree
      tree.insert(root);
      tree.insert(leftChild);
      tree.insert(rightChild);

      // Looking for a non-existent hobbemoun
      Hobbemoun found = tree.lookup(2, 5);

      // Ensure that null is returned for looking up a non-existent hobbemoun
      if (found != null) {
        System.out.println("ERROR: null was expectd to be returned");
        return false;
      }

    }


    return true; // All tests passed
  }

  /**
   * Tests the height method for an empty tree, a single node tree, and a multi-level tree.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testHeight() {
    // Test Scenario 1: tests the height method for an empty tree
    {
      // Create an empty tree
      HobbemounTree tree = new HobbemounTree();

      // Store the height of the tree as an integer
      int height = tree.height();

      // Ensure that the height of the tree is 0
      if (height != 0) {
        System.out.println("ERROR: The height was expected to be 0");
        return false;
      }
    }

    // Test Scenario 2: tests the height method for a single node tree
    {
      Hobbemoun root = new Hobbemoun("A");

      // Create the single node
      Node<Hobbemoun> rootNode = new Node<>(root);

      // Create a tree with a single node
      HobbemounTree tree = new HobbemounTree(rootNode);

      // Store the height of the tree as an integer
      int height = tree.height();

      // Ensure that the height of the tree is 1
      if (height != 1) {
        System.out.println("ERROR: The height was expected to be 1");
        return false;
      }

    }

    // Test Scenario 3: tests the height method for a multi-level tree
    {
      // Root
      Hobbemoun root = new Hobbemoun(HobbemounType.FIRE, HobbemounType.FIRE, "Root");

      // Left child of root
      Hobbemoun left = new Hobbemoun(HobbemounType.BUG, HobbemounType.BUG, "Left node");

      // Left child of right child of root
      Hobbemoun rightLeft =
          new Hobbemoun(HobbemounType.WATER, HobbemounType.WATER, "Right left node");

      // Right child of root
      Hobbemoun right = new Hobbemoun(HobbemounType.PSYCHIC, HobbemounType.PSYCHIC, "Right node");

      // Create multiple nodes
      Node<Hobbemoun> rootNode = new Node<>(root);
      Node<Hobbemoun> leftNode = new Node<>(left);
      Node<Hobbemoun> rightLeftNode = new Node<>(rightLeft);
      Node<Hobbemoun> rightNode = new Node<>(right);

      // Create the structure of the tree
      rootNode.setLeft(leftNode);
      rootNode.setRight(rightNode);
      rightNode.setLeft(rightLeftNode);

      // Create the tree with the root node
      HobbemounTree tree = new HobbemounTree(rootNode);

      // Store the height of the tree as an integer
      int height = tree.height();

      // Ensure that the height of the tree is 3
      if (height != 3) {
        System.out.println("ERROR: The height was expected to be 3");
        return false;
      }

    }
    return true;
  }

  /**
   * Tests the getStrongest method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetStrongest() {
    // Test Scenario 1: tests that getStrongest returns the strongest hobbemoun
    {
      // Root
      Hobbemoun root = new Hobbemoun(HobbemounType.FIRE, HobbemounType.FIRE, "Root");

      // Left child of root
      Hobbemoun left = new Hobbemoun(HobbemounType.BUG, HobbemounType.BUG, "Left node");

      // Left child of right child of root
      Hobbemoun rightLeft =
          new Hobbemoun(HobbemounType.WATER, HobbemounType.WATER, "Right left node");

      // Right child of root and strongest hobbemoun
      Hobbemoun right = new Hobbemoun(HobbemounType.PSYCHIC, HobbemounType.PSYCHIC, "Right node");

      Node<Hobbemoun> rootNode = new Node<>(root);
      Node<Hobbemoun> leftNode = new Node<>(left);
      Node<Hobbemoun> rightLeftNode = new Node<>(rightLeft);
      Node<Hobbemoun> rightNode = new Node<>(right);

      // Create the structure of the tree
      rootNode.setLeft(leftNode);
      rootNode.setRight(rightNode);
      rightNode.setLeft(rightLeftNode);


      HobbemounTree tree = new HobbemounTree(rootNode);

      // Store the return value of hobbemoun of getStrongest in strongestHobbemoun
      Hobbemoun strongestHobbemoun = tree.getStrongest();

      // Ensure that the strongest hobbemoun returned is the right child.
      if (strongestHobbemoun.compareTo(right) != 0) {
        System.out.println("ERROR: the hobbemouns are not the same");
        return false;
      }

      // Ensure that the ID's of the hobbemouns match up
      if (strongestHobbemoun.getPrimaryType() != right.getPrimaryType()
          && strongestHobbemoun.getSecondaryType() != right.getSecondaryType()) {
        System.out.println("ERROR: the hobbemoun's ids differ");
        return false;
      }

    }
    return true; // Tests passed
  }

  /**
   * Tests the getWeakest method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetWeakest() {
    // Test Scenario 1: tests that getWeakest returns the weakest hobbemoun
    {
      // Root
      Hobbemoun root = new Hobbemoun(HobbemounType.FIRE, HobbemounType.FIRE, "Root");

      // Left child of root and weakest hobbemoun
      Hobbemoun left = new Hobbemoun(HobbemounType.BUG, HobbemounType.BUG, "Left node");

      // Left child of right child of root
      Hobbemoun rightLeft =
          new Hobbemoun(HobbemounType.WATER, HobbemounType.WATER, "Right left node");

      // Right child of root
      Hobbemoun right = new Hobbemoun(HobbemounType.PSYCHIC, HobbemounType.PSYCHIC, "Right node");

      Node<Hobbemoun> rootNode = new Node<>(root);
      Node<Hobbemoun> leftNode = new Node<>(left);
      Node<Hobbemoun> rightLeftNode = new Node<>(rightLeft);
      Node<Hobbemoun> rightNode = new Node<>(right);

      // Create the structure of the tree
      rootNode.setLeft(leftNode);
      rootNode.setRight(rightNode);
      rightNode.setLeft(rightLeftNode);


      HobbemounTree tree = new HobbemounTree(rootNode);

      // Store the return value of hobbemoun of getStrongest in strongestHobbemoun
      Hobbemoun weakestHobbemoun = tree.getWeakest();

      // Ensure that the strongest hobbemoun returned is the right child.
      if (weakestHobbemoun.compareTo(left) != 0) {
        System.out.println("ERROR: the hobbemouns are not the same");
        return false;
      }

      // Ensure that the ID's of the hobbemouns match up
      if (weakestHobbemoun.getPrimaryType() != left.getPrimaryType()
          && weakestHobbemoun.getSecondaryType() != left.getSecondaryType()) {
        System.out.println("ERROR: the hobbemoun's ids differ");
        return false;
      }
    }
    return true; // Tests passed
  }


  /**
   * Tests that next returns the successor of the given Hobbemoun.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testNext() {
    HobbemounTree tree = new HobbemounTree();
    Hobbemoun hobbemoun1 = new Hobbemoun("Bulbasury");
    Hobbemoun hobbemoun2 = new Hobbemoun("Charmindy");
    Hobbemoun hobbemoun3 = new Hobbemoun("Squirty");
    tree.insert(hobbemoun1);
    if (tree.next(hobbemoun1) != null) { // only one element in the tree so next should be null
      return false;

    }
    tree.insert(hobbemoun2);
    if (tree.next(hobbemoun2) != null) {
      // only two elements in the tree so the next element after the second one should be null
      return false; // Test failed
    }
    tree.insert(hobbemoun3);

    if (!tree.next(hobbemoun1).equals(hobbemoun2) && tree.next(hobbemoun2).equals(hobbemoun3)) {
      return false; // Test failed
    }
    return true; // Tests all passed
  }

  /**
   * Tests that next throws IllegalArgumentException when given a null argument.
   *
   * @return true if the test passes, false otherwise
   */
  // CITE: GeeksForGeeks for exception handling in test cases
  public static boolean testNextExceptionEmpty() {
    HobbemounTree testTree = new HobbemounTree();
    try {
      testTree.next(null);
      return false;
    } catch (IllegalArgumentException e) {
      return true; // expected
    }
  }

  /**
   * Tests that next returns null when the Hobbemoun has no successor.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testNextNoSuccessor() {
    HobbemounTree tree = new HobbemounTree();
    Hobbemoun hobbemoun1 = new Hobbemoun("Charmindy");
    Hobbemoun hobbemoun2 = new Hobbemoun("Squirty");
    tree.insert(hobbemoun1);
    if (tree.next(hobbemoun1) != null) { // only one element in the tree
      return false;
    }
    tree.insert(hobbemoun2);
    if (tree.next(hobbemoun2) != null) { // only two elements in the tree so next should be null
      return false;
    }
    return true; // All tests passed
  }



  /**
   * Tests that the iterator works correctly by checking if it returns the correct elements in
   * order.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testIterator() {
    HobbemounTree testTree = new HobbemounTree();

    // creating new hobbemoun in order of first Letter
    Hobbemoun hobbemoun1 = new Hobbemoun("Bulbasury");
    Hobbemoun hobbemoun2 = new Hobbemoun("Charmindy");
    Hobbemoun hobbemoun3 = new Hobbemoun("Squirty");

    // inserting new hobbemoun into the tree
    testTree.insert(hobbemoun1);
    testTree.insert(hobbemoun2);
    testTree.insert(hobbemoun3);

    Iterator<Hobbemoun> it = testTree.iterator();

    // First element
    if (!it.hasNext() || !it.next().equals(hobbemoun1)) {
      return false;
    }

    // Second element
    if (!it.hasNext() || !it.next().equals(hobbemoun2)) {
      return false;
    }

    // Third element
    if (!it.hasNext() || !it.next().equals(hobbemoun3)) {
      return false;
    }

    // Ensure that there are no more elements
    if (it.hasNext()) {
      return false;
    }

    return true; // Tests all passed

  }

  /**
   * Tests that the iterator throws NoSuchElementException when there are no more elements to
   * return.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testIteratorNoSuchElement() {
    HobbemounTree testTree = new HobbemounTree();
    Hobbemoun hobbemoun1 = new Hobbemoun("Chanmindy");
    Hobbemoun hobbemoun2 = new Hobbemoun("Squirty");
    testTree.insert(hobbemoun1);
    testTree.insert(hobbemoun2);
    Iterator<Hobbemoun> testIterator = testTree.iterator();
    testIterator.next();
    testIterator.next();
    // calls 2 nexts so that when next is called in the try block it should be null
    try {
      testIterator.next();

    } catch (NoSuchElementException e) {
      return true; // expected because there is not another element in the tree there is just one
    }
    return false;
  }


  /**
   * Tests that the iterator is empty when the tree is empty. hasNext should return false and next()
   * should throw NoSuchElementException.
   *
   * @return true if the test passes, false otherwise
   */
  // CITE: GeeksForGeeks for exception handling in test cases
  public static boolean testIteratorEmpty() {
    HobbemounTree testTree = new HobbemounTree();
    Iterator<Hobbemoun> testIterator = testTree.iterator();
    if (testIterator.hasNext()) { // if the empty tree has a next it means its not empty so return
                                  // false
      return false;
    }
    try {
      testIterator.next();

    } catch (NoSuchElementException e) {
      return true; // expected because tree is empty
    }
    return false;
  }


  /**
   * Main method to run all tests
   */
  public static void main(String[] args) {

    System.out.println("Testing Hobbemoun implementation...");

    System.out.println("testCompareToByType: " + testCompareToByType());
    System.out.println("testCompareToByName: " + testCompareToByName());
    System.out.println("testCompareToSame: " + testCompareToSame());
    System.out.println("\nTesting HobbemounTree implementation...");
    System.out.println("testIsEmpty: " + testIsEmpty());
    System.out.println("testSize: " + testSize());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testInsertNullException: " + testInsertNullException());
    System.out.println("testInsertEmpty: " + testInsertEmpty());
    System.out.println("testInsertMultiple: " + testInsertMultiple());
    System.out.println("testInsertDuplicate: " + testInsertDuplicate());
    System.out.println("testLookup: " + testLookup());
    System.out.println("testHeight: " + testHeight());
    System.out.println("testGetStrongest: " + testGetStrongest());
    System.out.println("testGetWeakest: " + testGetWeakest());
    System.out.println("testNext: " + testNext());
    System.out.println("testNextExceptionEmpty: " + testNextExceptionEmpty());
    System.out.println("testNextNoSuccessor: " + testNextNoSuccessor());
    System.out.println("testIterator: " + testIterator());
    System.out.println("testIteratorExceptions: " + testIteratorNoSuchElement());
    System.out.println("testIteratorEmpty: " + testIteratorEmpty());

  }
}
