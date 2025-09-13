//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Priority Events Java
// Course: CS 300 Spring 2025
//
// Author: Olivia Ruby
// Email: oruby@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources:
// - https://www.geeksforgeeks.org/priority-queue-in-java/
// Increased my understanding of implementing priority queues
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This class implements a single priority queue of events for the CS300 P10 program Priority Events
 * using a min-heap structure maintained in an array
 */
public class PriorityEvents {

  // An array which contains all of the completed Events that have passed through this priority
  // queue; this array has double the capacity of heapData.
  private Event[] completed;

  // The number of events currently stored in the completed array
  private int completedSize;

  // An array which maintains the heap structure for our priority queue
  private Event[] heapData;

  // The number of events currently stored in the heapData array
  private int size;

  // Indicates whether the events in this priority queue should be arranged in heap order with
  // respect to their timestamps or alphabetically by their descriptions
  private static boolean sortAlphabetically;


  /**
   * Creates a new priority queue of events, initializing all data fields accordingly
   * 
   * @param capacity - the capacity of the queue to be created; must be > 0
   * @throws IllegalArgumentException - if a capacity of 0 or less is provided
   */
  public PriorityEvents(int capacity) throws IllegalArgumentException {

    // Ensure that the capacity isn't 0 or less
    if (capacity <= 0) {
      throw new IllegalArgumentException("ERROR: Capacity cannot be 0 or less");
    }

    // Initialize the heap that stores the upcoming events
    this.heapData = new Event[capacity];
    this.size = 0;

    // Store the completed events
    this.completed = new Event[capacity * 2];
    this.completedSize = 0;
  }

  /**
   * Creates a valid min-heap from the provided oversize array of Events
   * 
   * @param events - the Events to be prioritized
   * @param size   - the number of Events in the provided events array, assumed valid
   * @return
   * @throws IllegalArgumentException - if any Event in events has already been completed
   */
  // CITE: GeeksForGeeks for understanding the implementation of the heap of a priority queue
  public PriorityEvents(Event[] events, int size) throws IllegalArgumentException {

    // Ensure that no events have already been completed by
    // looping through the array, checking if any events are already completed
    for (int i = 0; i < size; i++) {
      if (events[i].isComplete()) {
        throw new IllegalArgumentException("ERROR: no events can already be completed");
      }
    }

    // Copy into the internal heap array
    this.heapData = new Event[events.length];
    this.size = size;
    for (int i = 0; i < size; i++) {
      this.heapData[i] = events[i];
    }

    // Intialize the completed events storage, double the heap capacity
    this.completed = new Event[heapData.length * 2];
    // Initalize the size to 0
    this.completedSize = 0;

    // Create the heap by percolating down from the last parent to the root
    for (int i = (size - 2) / 2; i >= 0; i--) {
      percolateDown(i);
    }

  }

  /**
   * Inserts a new Event into the priority queue in the correct location in O(log N) time
   * 
   * @param e - the new Event to be added
   * @throws IllegalStateException    - if the queue is full
   * @throws IllegalArgumentException - if the event is null or the Event is completed
   */
  public void addEvent(Event e) throws IllegalStateException, IllegalArgumentException {
    // Ensure that the event isn't null or already complete
    if (e == null || e.isComplete()) {
      throw new IllegalArgumentException("ERROR: Event cannot be null or completed");
    }

    // Ensure that the queue isn't full
    if (size >= heapData.length) {
      throw new IllegalStateException("ERROR: The queue cannot be full");
    }

    // Add event to the end of the heap
    heapData[size] = e;
    // Percolate up to maintain min-heap order
    percolateUp(size);
    // Increment size after adding event
    size++;
  }

  /**
   * Returns a deep copy of the completed array, and empties out the array
   * 
   * @return returns a deep copy of the completed array, and empties out the array
   */
  public Event[] clearCompletedEvents() {
    // Make a new array that is a copy of the completed array
    Event[] copy = Arrays.copyOf(completed, completedSize);

    // Clear out the completed array, by setting each value to null
    for (int i = 0; i < completedSize; i++) {
      completed[i] = null;
    }
    // Reset the completed array's size to 0
    completedSize = 0;
    return copy;

  }

  /**
   * Removes the next (according to priority) Event from the priority queue, marks it as complete,
   * and appends it to the completed array.
   * 
   * @throws IllegalStateException - if the queue is empty or the completed array is full
   */
  public void completeEvent() throws IllegalStateException {
    // Ensure that the queue isn't empty
    if (size == 0) {
      throw new IllegalStateException("ERROR: queue cannot be empty");
    }

    // Ensure that the completed array isn't full
    if (completedSize >= completed.length) {
      throw new IllegalStateException("ERROR: completed array cannot be full");
    }

    // Remove the root of the heap data
    Event next = heapData[0];

    // Replace the root with the last element
    heapData[0] = heapData[size - 1];

    // TODO Set last element to null

    // Decrement size
    size--;

    // Percolate heap down from the root
    percolateDown(0);

    // Mark the event as complete, add to completed array, increment size of completed array
    next.markAsComplete();
    completed[completedSize++] = next;

  }

  /**
   * Required helper method for toString, which creates a deep copy of the current queue
   * 
   * @return a new PriorityEvents queue with a deep copy of the heapData and completed arrays and
   *         their corresponding sizes
   */
  protected PriorityEvents deepCopy() {

    // Creates a new PriorityEvents queue with the same heap length
    PriorityEvents copy = new PriorityEvents(this.heapData.length);

    // Copy over the heap's size
    copy.size = this.size;

    // Copy the heap's contents with a loop
    for (int i = 0; i < this.size; i++) {
      copy.heapData[i] = this.heapData[i];
    }

    // Copy the completed event's contents and size
    copy.completedSize = this.completedSize;

    for (int i = 0; i < this.completedSize; i++) {
      copy.completed[i] = this.completed[i];
    }

    return copy;

  }

  /**
   * For testing purposes; returns a deep copy of the completed array WITHOUT clearing the array
   * 
   * @return a deep copy of the contents of the completed array
   */
  protected Event[] getCompletedEvents() {
    // Returns a new array with the same elements from the completed array
    Event[] copy = new Event[completedSize];
    for (int i = 0; i < completedSize; i++) {
      copy[i] = completed[i];
    }
    return copy;

  }

  /**
   * For testing purposes; accesses a deep copy of the heapData array.
   * 
   * @return a deep copy of the heapData array
   */
  protected Event[] getHeapData() {
    // Returns a new array with the same elements from the heapData array
    return heapData.clone();

  }


  /**
   * Reports whether this priority queue currently contains any Events, not counting those in the
   * completed array
   * 
   * @return true if this priority queue contains no Events, false otherwise
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Reports whether this priority queue is maintained according to Event description or timestamp
   * 
   * @return true if this priority queue is ordered by description, false if it is ordered by
   *         timestamp
   */
  public static boolean isSortedAlphabetically() {
    return sortAlphabetically;
  }

  /**
   * Accesses the number of events in the completed array
   * 
   * @return the number of Events in the completed array
   */
  public int numCompleted() {
    return completedSize;
  }

  /**
   * Accesses the next (according to priority) event without removing it from the queue
   * 
   * @return a reference to the next (upcoming or alphabetical) event in the queue
   * @throws NoSuchElementException - if the queue is currently empty
   */
  public Event peekNextEvent() throws NoSuchElementException {
    // Ensure that the queue isn't empty
    if (this.size == 0) {
      throw new NoSuchElementException("ERROR: queue cannot be empty");
    }

    // Return the next event from the queue without removing it
    return heapData[0];

  }


  /**
   * Percolates the value at index i of the heapData array away from index 0 according to min-heap
   * protocols, comparing either Event timestamps or descriptions depending on the value of the
   * sortAlphabetically field
   * 
   * @param i - the index of the Event in heapData to be percolated
   */
  protected void percolateDown(int i) {
    // Index of left child
    int left = 2 * i + 1;
    // Index of right child
    int right = 2 * i + 2;

    // If no more children left, return
    if (left >= size) {
      return;
    }

    // Pick the smaller child
    int smallerChild = left;

    // Find the smallest among the children
    if (right < size && compare(heapData[right], heapData[left]) < 0) {
      smallerChild = right;
    }

    // If child is smaller than current, then swap them and recurse
    if (compare(heapData[smallerChild], heapData[i]) < 0) {
      Event tmp = heapData[i];
      heapData[i] = heapData[smallerChild];
      heapData[smallerChild] = tmp;
      // Recursive call on the index of the smallest child
      percolateDown(smallerChild);
    }

  }

  /**
   * Percolates the value at index i of the heapData array toward index 0 according to min-heap
   * protocols, comparing either Event timestamps or descriptions depending on the value of the
   * sortAlphabetically field
   * 
   * @param i - the index of the Event in heapData to be percolated
   */
  protected void percolateUp(int i) {
    // Base case: once the root is reached, it's done
    if (i <= 0) {
      return;
    }

    // Index of the parent
    int parent = (i - 1) / 2;

    // If current is smaller than parent, then swap them and recurse
    if (compare(heapData[i], heapData[parent]) < 0) {

      Event tmp = heapData[i];
      heapData[i] = heapData[parent];
      heapData[parent] = tmp;
      // Recursive call on the parent's index
      percolateUp(parent);
    }

  }

  /**
   * Accesses the number of events currently in this priority queue, not counting those in the
   * completed array
   * 
   * @return the number of Events in this priority queue
   */
  public int size() {
    return size;
  }

  /**
   * Sets all priority queues to be sorted alphabetically.
   * 
   */
  public static void sortAlphabetically() {
    sortAlphabetically = true;
  }

  /**
   * Sets all priority queues to be sorted chronoloically (i.e., not alphabetically).
   * 
   */
  public static void sortChronologically() {
    sortAlphabetically = false;
  }

  /**
   * Creates a String representation of all events in the queue in sorted order, one on each line
   * (no trailing newline).
   * 
   * @return a String representation of all events in sorted order
   */
  @Override
  public String toString() {
    // Create a copy of the queue in sorted order
    PriorityEvents copy = deepCopy();
    StringBuilder sb = new StringBuilder();

    // Peek and remove from the copy in sorted order
    while (!copy.isEmpty()) {
      sb.append(copy.peekNextEvent().toString());
      // Remove the event from the copy
      copy.completeEvent();

      // Only add a new line while the copy isn't empty
      if (!copy.isEmpty()) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Helper method used to compare two events either by its description or timestamp
   * 
   * @param a - the first event to compare
   * @param b - the second event to compare to
   * @return a negative integer, zero, or a positive integer depending on the event comparisons
   */
  private int compare(Event a, Event b) {
    if (sortAlphabetically) {
      return a.getDescription().compareTo(b.getDescription());
    } else {
      return a.compareTo(b);
    }
  }



}
