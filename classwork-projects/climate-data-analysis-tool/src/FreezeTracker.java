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
// - https://www.geeksforgeeks.org/introduction-to-doubly-linked-lists-in-java/
// Helped with our understanding of doubly linked list.
// - https://www.cs.toronto.edu/~david/course-notes/csc110-111/13-linked-lists/02-traversing-linked-
// lists.html
// Helped with logic of how to traverse linked list.
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A doubly-linked list implementation for managing freeze-thaw records of Lake Mendota. Implements
 * ListADT and Iterable, providing operations for adding, removing, merging, and analyzing freeze
 * data.
 */
public class FreezeTracker implements ListADT<LakeRecord>, Iterable<LakeRecord> {
  /**
   * Pointer to head of the linked list.
   */
  private LinkedNode head;
  /**
   * Pointer to tail of the linked list.
   */
  private LinkedNode tail;
  /**
   * Number of elements in the list.
   */
  private int size;
  /**
   * Whether to traverse the list is reverse-chronological order.
   */
  private boolean reversed;

  /**
   * Constructs an empty FreezeTracker.
   */
  public FreezeTracker() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.reversed = false;
  }

  /**
   * Constructs a FreezeTracker and initializes it with an ArrayList of LakeRecords. This
   * constructor processes the provided dataset. After this process, the linked list will contain
   * exactly one cleaned and merged record per winter.
   * 
   * @param records The list of LakeRecord objects read from FreezeData.csv. This list may contain
   *                missing or duplicate entries, which are handled during initialization.
   */
  public FreezeTracker(ArrayList<LakeRecord> records) {
    if (records == null) {
      records = LakeRecordReader.getLakeRecords("/Users/olivia/Downloads/FreezeData.csv");
    }

    if (records.isEmpty()) {
      this.head = null;
      this.tail = null;
      this.size = 0;
      return;
    }

    // Update the duration of each record.
    for (LakeRecord record : records) {
      record.updateDuration();
    }

    ArrayList<LakeRecord> cleanedRecords = new ArrayList<>();
    for (LakeRecord record : records) {
      if (record.hasCompleteData()) {
        cleanedRecords.add(record);
      }
    }

    // Sets the head as the first record in the cleanedRecords ArrayList.
    this.head = new LinkedNode(cleanedRecords.get(0));
    LinkedNode curr = head;
    this.size = 1;
    // Initialize size with the first valid record.

    for (int i = 1; i < cleanedRecords.size(); i++) {
      LakeRecord record = cleanedRecords.get(i);
      LinkedNode prev = curr;

      // If the previous node is from the same winter, merge the records.
      if (prev.getLakeRecord().getWinter().equals(record.getWinter())) {
        prev.getLakeRecord().mergeWith(record);
      } else {
        // Otherwise, create a new linked node and link it.
        prev.setNext(new LinkedNode(record));
        curr = prev.getNext(); // Continue onto next record.
        this.size++; // Increment size each time a new node is added.
      }
    }
    this.tail = curr;

  }

  /**
   * Returns the number of records in the list.
   * 
   * @return The size of the list.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Checks if the list is empty.
   * 
   * @return True if the list is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return this.head == null;
  }

  /**
   * Clears all records from the list.
   */
  @Override
  public void clear() {
    this.clear();
  }

  /**
   * Specifies which direction the list should be traversed in the future
   * 
   * @param reversed whether to traverse the list backwards
   */
  public void setReversed(boolean reversed) {
    this.reversed = reversed;
  }

  /**
   * Getter method for head
   * 
   * @return head of the linked list
   */
  public LinkedNode getHead() {
    return this.head;
  }

  /**
   * Getter method for tail
   * 
   * @return tail of the linked list
   */
  public LinkedNode getTail() {
    return this.tail;
  }



  /**
   * Appends a new freeze record to the end of the linked list in O(1) time.
   *
   * <br>
   * <br>
   * Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining each
   * algorithmic step you use!
   *
   * @param record The record to add.
   */
  // CITE: GeeksForGeeks for the implementation of adding nodes to a doubly linked list.
  @Override
  public void add(LakeRecord record) {
    LinkedNode newNode = new LinkedNode(record);

    if (this.isEmpty()) { // If the list is initially empty, updates the tail and head nodes
                          // to point to the added record.
      head = newNode;
      tail = newNode;
    } else {
      tail.setNext(newNode); // Updates the tail node's pointer to the added record.
      tail = newNode;
    }
    size++; // Increment the size of the list if the record was added.
  }

  /**
   * Removes the given node from the linked list in O(1) time. Note: this method does not verify
   * that the given node is a member of the list, and should only be used as a helper function
   * inside the FreezeTracker class.
   *
   * <br>
   * <br>
   * Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining each
   * algorithmic step you use!
   *
   * @param node the node to be removed
   * @throws IllegalArgumentException if node is null
   */
  // CITE: GeeksforGeeks for understanding how to relink nodes after removal.
  private void removeNode(LinkedNode node) {

    if (node == null) {
      throw new IllegalArgumentException("ERROR: node to remove is null!");
    }

    if (node == head) {
      head = head.getNext(); // If the node to remove is the head, update the head node to
                             // the next node.
      if (head == null) {
        tail = null; // If the head is null, set the tail to null as the list is empty.
      }
      size--; // Decrement size when node is removed.
      return;
    }
    if (node == tail) {
      LinkedNode prev = head;
      while (prev.getNext() != tail) { // Loop through list until it gets to the tail node.
        prev = prev.getNext(); // Sets prev equal to the node before the tail.
      }
      prev.setNext(null);
      tail = prev; // Sets tail pointer to previous node.
      size--; // Decrements size.
      return;
    }

    // If node is in the middle of the list, keep moving through list until the node is found.
    LinkedNode prev = head;
    while (prev != null && prev.getNext() != node) {
      prev = prev.getNext();
    }
    if (prev != null) {
      prev.setNext(node.getNext()); // Once node is found, set the pointer to the next node.
      size--;
    }

  }

  /**
   * Removes the first node in the list that contains the given record.
   *
   * <br>
   * <br>
   * Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining each
   * algorithmic step you use!
   *
   * @param record the record to be removed
   * @return boolean indicating whether the record was found in the list
   */
  @Override
  public boolean remove(LakeRecord record) {
    // List cannot be empty
    if (head == null) {
      return false;
    }

    // Record cannot be null!
    if (record == null) {
      return false;
    }

    // If the record to remove is the head:
    if (head != null && head.getLakeRecord().equals(record)) {
      head = head.getNext();
      if (head != null) {
        head.setPrev(null);
      } else {
        tail = null; // If the list is empty, the tails reference should be null.
      }
      size--;
      return true;
    }

    LinkedNode curr = head;
    while (curr != null) {
      if (curr.getLakeRecord().equals(record)) {
        // Removing from middle or end.
        LinkedNode prev = curr.getPrev();
        LinkedNode next = curr.getNext();

        if (prev != null) { // Updates the previous nodes to next.
          prev.setNext(next);
        }

        if (next != null) {
          next.setPrev(prev); // Update the next nodes previous.
        }

        if (curr == tail) { // If we are removing tail, update the tail.
          tail = prev;
          if (tail != null) {
            tail.setNext(null);
          }
        }
        size--;
        return true;
      }
      curr = curr.getNext();
    }

    return false;

  }

  /**
   * Finds the given record in the list
   * 
   * @param record the LakeRecord to search for
   * @return The first LinkedNode containing the given record, or null if none exists
   */
  public LinkedNode find(LakeRecord record) {
    LinkedNode curr = head;

    while (curr != null) {
      if (curr.getLakeRecord().equals(record)) {
        return curr; // If the Lake Record equals the record to find, return it.
      }
      curr = curr.getNext(); // Keep moving through list until record is found.
    }

    return null; // If record isn't found, return null.
  }

  /**
   * Returns the LakeRecord at index i in the list, using zero-indexing.
   * 
   * @param i a non-negative integer
   * @return The LakeRecord at the given index
   * @throws IndexOutOfBoundsException if i is negative or greater than size()-1
   */
  public LakeRecord get(int i) {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + i);
    }

    LinkedNode current = head;
    for (int count = 0; count < i; count++) { // Loop through list, until at index i.
      current = current.getNext();
    }

    return current.getLakeRecord();
  }

  /**
   * Provides an iterator for traversal. The direction of traversal is head-to-tail if this.reversed
   * is false, and tail-to-head otherwise.
   *
   * @return An iterator traversing the list.
   */
  @Override
  public Iterator<LakeRecord> iterator() {
    if (reversed) {
      return new IteratorBwd(tail);
    } else {
      return new IteratorFwd(head);
    }
  }


  /**
   * Removes all nodes with missing freeze or thaw dates
   */
  public void removeIncompleteRecords() {
    while (head != null && !head.getLakeRecord().hasCompleteData()) {
      head = head.getNext(); // Move head forward if it contains an incomplete record.
      size--;
    }

    if (head == null) { // If all records are removed
      tail = null;
      size = 0;
      return;
    }

    LinkedNode prev = head;
    LinkedNode curr = head.getNext();

    while (curr != null) {
      if (!curr.getLakeRecord().hasCompleteData()) {
        prev.setNext(curr.getNext()); // Skip over incomplete record.
        size--; // Decrement size
      } else {
        prev = curr;
      }
      curr = curr.getNext();
    }
    tail = prev; // Update the tail's reference.
    // Ensure that the tail is updated correctly.
    if (tail != null && !tail.getLakeRecord().hasCompleteData()) {
      tail = null;
    }
  }

  /**
   * Fixes all LakeRecords contained in this list with incorrect durations (Hint: LakeRecord already
   * has a method for this!)
   */
  public void updateDurations() {
    LinkedNode curr = head;

    while (curr != null) { // Loops through the list, updating each lake record's duration.
      curr.getLakeRecord().updateDuration();
      curr = curr.getNext();
    }
  }

  /**
   * Merges consecutive nodes containing records from the same winter. The merged node contains a
   * single record with the earliest freeze date, the latest thaw date, and the total number of days
   * of ice cover. Note that merging discards the middle thaw and freeze dates, and so this method
   * should only be used after calling updateDurations().
   *
   * <br>
   * <br>
   * Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining each
   * algorithmic step you use!
   */
  public void mergeWinters() {
    if (head == null || tail == null) {
      return; // Nothing to merge because the list is empty.
    }

    LinkedNode curr = head;
    while (curr != null && curr.getNext() != null) {
      LinkedNode nextNode = curr.getNext();


      // Check if the lake records, have the same winter.
      if (curr.getLakeRecord().getWinter().equals(nextNode.getLakeRecord().getWinter())) {

        curr.getLakeRecord().mergeWith(nextNode.getLakeRecord());
        size--;

        curr.setNext(nextNode.getNext()); // Remove nextNode by moving past it.

        if (nextNode == tail) {
          tail = curr;
        }
      } else {
        curr = curr.getNext();
      }
    }
  }

  /**
   * Returns a new linked list containing all the records falling between year1 and year 2,
   * inclusive. The returned list should not contain any references to nodes or records from the
   * original list, and the relative ordering of nodes should not change.
   *
   * @param year1 minimum allowable year for the new list
   * @param year2 maximum allowable year for the new list
   * @return a new, filtered linked list covering the given range of years.
   */
  public FreezeTracker filterByYear(int year1, int year2) {
    FreezeTracker filteredList = new FreezeTracker();
    LinkedNode curr = head;

    while (curr != null) {

      // Splits the date to only store the year.
      String[] years = curr.getLakeRecord().getWinter().split("-");
      if (years.length < 2) {
        curr = curr.getNext();
        continue;
      }

      // Turns the winter string into an integer.
      int winterYear = Integer.parseInt(years[0].trim());

      if (winterYear >= year1 && winterYear <= year2) {
        // Ensures the year falls between year1 and year2.
        filteredList.add(
            new LakeRecord(curr.getLakeRecord().getWinter(), curr.getLakeRecord().getFreezeDate(),
                curr.getLakeRecord().getThawDate(), curr.getLakeRecord().getDaysOfIceCover()));
        // Creates a new lake record with the same details and adds it to the new linked list.
      }
      curr = curr.getNext();
    }
    return filteredList; // Returns the new linked list within the range of years.
  }

  /**
   * Returns a new linked list containing all of the records from the given year. The returned list
   * should not contain any references to nodes or records from the original list, and the relative
   * ordering of nodes should not change.
   *
   * @param year the single year covered by the new list
   * @return a new linked list containing only nodes from the given year
   */
  public FreezeTracker filterByYear(int year) {
    return filterByYear(year, year);
  }

  /**
   * Returns a new linked list containing all of the records whose total days of ice cover are
   * between low and high, inclusive. The returned list should not contain any references to nodes
   * or records from the original list, and the relative ordering of nodes should not change.
   *
   * @param low  The minimum allowed duration for the new list
   * @param high The maximum allowed duration for the new list
   * @return a new list containing only records with duration in the given range
   */
  public FreezeTracker filterByDuration(int low, int high) {
    FreezeTracker filteredList = new FreezeTracker();
    LinkedNode curr = head;

    while (curr != null) { // Goes through the linked list.
      int days = curr.getLakeRecord().getDaysOfIceCover(); // Gets each lake record's day count.

      if (days >= low && days <= high) {
        // Ensures that the total days of ice cover are within in range.
        filteredList.add(
            new LakeRecord(curr.getLakeRecord().getWinter(), curr.getLakeRecord().getFreezeDate(),
                curr.getLakeRecord().getThawDate(), curr.getLakeRecord().getDaysOfIceCover()));
        // Creates new lake record with the same details and adds it to the new linked list.
      }
      curr = curr.getNext();
    }
    return filteredList; // Returns the new linked list within the ice cover day range.

  }


  /**
   * Finds the latest date at which the lake thawed.
   * 
   * @return The date of the latest thaw, e.g. "April 15"
   */
  public String getLatestThaw() {
    LinkedNode current = head;
    if (size() == 0) {
      return null;
    }
    String temp = head.getLakeRecord().getThawDate();
    for (LakeRecord record : this) {
      if (Date.compareDates(record.getThawDate(), temp) > 0) {
        temp = record.getThawDate();


      }
    }
    return temp;

  }

  /**
   * Finds the earliest date at which the lake froze.
   *
   * @return The day of the earliest freeze, e.g. "December 2"
   */
  // CITE: Referenced traversing through a linked list for incorrect instances from Toronto edu.
  public String getEarliestFreeze() {
    if (head == null)
      return null; // List is empty
    LinkedNode curr = head;
    String earliestFreeze = curr.getLakeRecord().getFreezeDate();
    while (curr != null) { // Loop through linked list
      if (curr.getLakeRecord().getFreezeDate().compareTo(earliestFreeze) < 0) {
        earliestFreeze = curr.getLakeRecord().getFreezeDate(); // Comparing each lake record's
        // freeze date until the earliest freeze is found.
      }
      curr = curr.getNext();
    }
    return earliestFreeze;
  }

  /**
   * Finds the average (arithmetic mean) number of days of ice cover across the entire list
   *
   * @return The average number of days of ice cover across all nodes, or 0 if list is empty.
   */
  public float getAverageFreezeDuration() {
    if (size == 0)
      return 0f; // List is empty
    float totalDays = 0;
    int count = 0;
    LinkedNode curr = head;
    while (curr != null) { // Loop through list
      if (curr.getLakeRecord().getDaysOfIceCover() != LakeRecord.MISSING) {
        totalDays += (float) curr.getLakeRecord().getDaysOfIceCover();
        // If the days of ice cover data isn't missing, add to total days.
        count++; // Increment count
      }
      curr = curr.getNext();
    }
    return count == 0 ? 0f : (float) totalDays / count; // Divide total days of ice cover by the
    // num of lake records to get the average.
  }

  /**
   * Finds the maximum number of days of ice cover across the entire list
   * 
   * @return The maximum number of days of ice cover across all nodes, or 0 if the list is empty.
   */
  public int getMaxFreezeDuration() {
    if (head == null)
      return 0; // List is empty
    int maxDuration = 0;
    LinkedNode curr = head;
    while (curr != null) { // Loop through list
      if (curr.getLakeRecord().getDaysOfIceCover() > maxDuration) {
        // Compares each lake record's freeze duration to find the max duration.
        maxDuration = curr.getLakeRecord().getDaysOfIceCover();
      }
      curr = curr.getNext();
    }
    return maxDuration;
  }

  /**
   * Finds the minimum number of days of ice cover across the entire list
   * 
   * @return The minimum number of days of ice cover across all nodes, or 0 if the list is empty.
   */
  public int getMinFreezeDuration() {
    if (head == null)
      return 0; // List is empty
    int minDuration = Integer.MAX_VALUE;
    LinkedNode curr = head;
    while (curr != null) {
      int days = curr.getLakeRecord().getDaysOfIceCover();
      if (days != LakeRecord.MISSING && days < minDuration) {
        // Ensures that the freeze duration data isn't missing.
        minDuration = days;
      }
      // Loops through list comparing each lake record's freeze duration to find the min.
      curr = curr.getNext();
    }
    return minDuration == Integer.MAX_VALUE ? 0 : minDuration;
  }

  /**
   * Creates a string representation of the tracker with each node on a new line. The order of the
   * nodes depends on whether the string is currently reversed.
   *
   * @return a String representation of the list
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    LinkedNode curr = head;
    while (curr != null) {
      sb.append(curr.getLakeRecord().toString()).append("\n");
      curr = curr.getNext();
    }
    return sb.toString();
  }

}
