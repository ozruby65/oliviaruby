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
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
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
 * This class iterates through the linked list forwards.
 */
public class IteratorFwd implements Iterator<LakeRecord> {
  private LinkedNode current;

  /**
   * Constructs a forward iterator starting from the given node.
   * 
   * @param start The starting node (head of the list).
   */
  public IteratorFwd(LinkedNode start) {
    this.current = start;
  }

  /**
   * Checks if there is a next element in the list.
   * 
   * @return True if there is another element, false otherwise.
   */
  @Override
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the next LakeRecord and moves the iterator forward.
   * 
   * @return The next LakeRecord in the list.
   * @throws NoSuchElementException if no more elements are available.
   */
  @Override
  // CITE: Github to help with our understanding of the head and tail nodes.
  public LakeRecord next() {
    // If there are no more lake records in the list, an exception is thrown.
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements in the list.");
    }

    LakeRecord record = current.getLakeRecord();
    current = current.getNext();
    return record;
  }
}
