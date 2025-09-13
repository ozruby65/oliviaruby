//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Java P07 Lake Mendota FreezeTracker
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
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources:
// - https://sbme-tutorials.github.io/2020/data-structure-FALL/notes/week03b.html
// Helped with understanding the head and tail of a linked list.
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class iterates through the linked list backwards.
 */
public class IteratorBwd implements Iterator<LakeRecord> {
  private LinkedNode current;

  /**
   * Constructs a backward iterator starting from the given node.
   * 
   * @param start The starting node (tail of the list).
   */
  public IteratorBwd(LinkedNode start) {
    this.current = start;
  }

  /**
   * Checks if there is a previous element in the list.
   * 
   * @return True if there is another element, false otherwise.
   */
  @Override
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the previous LakeRecord and moves the iterator backward.
   * 
   * @return The previous LakeRecord in the list.
   * @throws NoSuchElementException if there are no more elements.
   */
  @Override
  // CITE: Github to help with our understanding of the head and tail nodes.
  public LakeRecord next() {
    // If there are no more lake records in the list, an exception is thrown.
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements in the list.");
    }

    LakeRecord record = current.getLakeRecord();
    current = current.getPrev(); // Move to the previous node
    return record;
  }
}
