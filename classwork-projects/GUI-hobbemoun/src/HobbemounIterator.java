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
// - Referenced P07 iterator class
// - referenced unit 7 on zyBooks for recursion algorithim help
// https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2025/chapter/7/section/3
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements Iterator and is used to move through the HobbemounTree This class utilizes
 * the hobbemoun tree classes next method to imoplement its own hasNext and next method
 */
public class HobbemounIterator implements Iterator<Hobbemoun> {

  private HobbemounTree tree;

  private Hobbemoun nextHobbemoun;

  /**
   * Constructor for HobbemounIterator. Should initialize the tree to this tree and set
   * nextHobbemoun to the weakest hobbemoun in the tree.
   */
  // CITE: P07 Iterator class for help implemented hobbemoun iterator
  public HobbemounIterator(HobbemounTree tree) {
    if (tree == null) {
      throw new IllegalArgumentException("ERROR: Tree cannot be null");
    }
    this.tree = tree;
    this.nextHobbemoun = tree.getWeakest(); // Start at the weakest/leftmost
  }

  /**
   * Checks whether the next hobbemoun is null
   * 
   * @return if the next hobbemoun is not null it returns true
   */
  @Override
  public boolean hasNext() {
    return nextHobbemoun != null;
  }

  /**
   * @return the next hobbemoun in the tree using the next method recursively in the HobbemounTree
   *         class
   * @throws NoSuchElementException -if there are no elements left in the tree there is no next
   */
  @Override
  public Hobbemoun next() {
    if (!hasNext()) {
      throw new NoSuchElementException("ERROR: no more elements in the HobbemounTree");
    }

    Hobbemoun curr = nextHobbemoun;
    nextHobbemoun = tree.next(nextHobbemoun); // Keep moving through the tree
    return curr;

  }

}
