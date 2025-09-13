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
// - https://www.programiz.com/java-programming/priorityqueue
// Helped with my understanding of peeking from queues
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * Tester class for the CS300 P10 Priority Events project. You may add tester methods to this class
 * but they must be declared private; the existing public tester methods may use the output of these
 * private testers to help determine their output (as with testAddEvent or testCompleteEvent).
 */
public class PriorityEventsTester {
  /**
   * This method runs all sub-testers related to testing adding an Event to the priority queue. You
   * may wish to add additional output for clarity, or additional private tester methods related to
   * adding Events.
   * 
   * @return true if all tests relating to adding an Event to a priority queue pass; false otherwise
   */
  public static boolean testAddEvent() {
    boolean testAdd = true;
    testAdd &= testAddEventChronological();
    testAdd &= testAddEventAlphabetical();
    return testAdd;
  }

  /**
   * This method runs testers relating to testing adding an Event to a chronological priority queue
   * 
   * 
   * @return true if all tests relating to adding an Event to a chronological priority queue pass;
   *         false otherwise
   */
  private static boolean testAddEventChronological() {


    // Test Scenario 1: Tests that addEventChronological returns true when events on the same day
    // but different times are added to the queue
    {
      try {
        // Ensure the queue is int chronological mode
        PriorityEvents.sortChronologically();

        // Create a small priority events queue
        PriorityEvents pq = new PriorityEvents(3);

        // Create 3 events on the same day but different times
        Event beginning = new Event("A", 1, 2, 30);
        Event middle = new Event("B", 1, 3, 30);
        Event end = new Event("C", 1, 4, 20);

        // Add the events to the priority events queue out of order
        pq.addEvent(end);
        pq.addEvent(beginning);
        pq.addEvent(middle);

        // Check the internal heap data to ensure it's not already sorted
        Event[] data = pq.getHeapData();
        // In a fully sorted array, data[1] < data[2] so this should fail
        if (data[1].compareTo(data[2]) < 0) {
          System.out.println("ERROR: heap data is already fully sorted");
          return false;
        }

        // Ensure that the first "peek" is the beginning event
        if (!pq.peekNextEvent().equals(beginning)) {
          System.out.println("ERROR: first event isn't the beginning event");
          return false;
        }
        pq.completeEvent();

        // Ensure that the second "peek" is the middle event
        if (!pq.peekNextEvent().equals(middle)) {
          System.out.println("ERROR: second event isn't the middle event");
          return false;
        }
        pq.completeEvent();

        // Ensure that the third "peek" is the end event
        if (!pq.peekNextEvent().equals(end)) {
          System.out.println("ERROR: third event isn't the end event");
          return false;
        }

      } catch (Exception e) {
        System.out.println("ERROR: Exception shouldn't be thrown");
        return false;
      }

    }



    // Test Scenario 2: Tests that addEventChronological returns true when events are on
    // different days but have the same times
    {
      try {
        // Ensure the queue is int chronological mode
        PriorityEvents.sortChronologically();

        // Create a small priority events queue
        PriorityEvents pq = new PriorityEvents(3);

        // Create 3 events on differnet days but same times
        Event beginning = new Event("A", 1, 2, 30);
        Event middle = new Event("B", 2, 2, 30);
        Event end = new Event("C", 3, 2, 30);

        // Add the events to the priority events queue out of order
        pq.addEvent(end);
        pq.addEvent(beginning);
        pq.addEvent(middle);

        // Check the internal heap data to ensure it's not already sorted
        Event[] data = pq.getHeapData();
        // In a fully sorted array, data[1] < data[2] so this should fail
        if (data[1].compareTo(data[2]) < 0) {
          System.out.println("ERROR: heap data is already fully sorted");
          return false;
        }

        // Ensure that the first "peek" is the beginning event
        if (!pq.peekNextEvent().equals(beginning)) {
          System.out.println("ERROR: first event isn't the beginning event");
          return false;
        }
        pq.completeEvent();

        // Ensure that the second "peek" is the middle event
        if (!pq.peekNextEvent().equals(middle)) {
          System.out.println("ERROR: second event isn't the middle event");
          return false;
        }
        pq.completeEvent();

        // Ensure that the third "peek" is the end event
        if (!pq.peekNextEvent().equals(end)) {
          System.out.println("ERROR: third event isn't the end event");
          return false;
        }

      } catch (Exception e) {
        System.out.println("ERROR: Exception shouldn't be thrown");
        return false;
      }

    }


    // Test Scenario 3: tests that an IllegalArgumentException is thrown when trying to add
    // a null event
    {
      boolean caught = false;
      try {
        // Create a small priority events queue
        PriorityEvents pq = new PriorityEvents(3);
        // Attempt to add an event thats null
        pq.addEvent(null);
      } catch (IllegalArgumentException e) {
        // Correct exception was thrown
        caught = true;
      } catch (Exception e) {
        System.out.println("ERROR: incorrect exception was thrown");
        caught = false;
      }

      // Ensure that an exception was thrown
      if (!caught) {
        System.out.println("ERROR: no IllegalArgumentException was thrown");
        return false;
      }
    }



    // Test Scenario 4: tests that an IllegalArgumentException is thrown when trying to add
    // a completed event to the queue
    {
      boolean caught = false;
      // Create an event and mark it as complete
      Event complete = new Event("A", 1, 2, 3);
      complete.markAsComplete();

      // Create a small priority events queue
      PriorityEvents pq = new PriorityEvents(3);

      try {
        pq.addEvent(complete);

      } catch (IllegalArgumentException e) {
        // Correct Exception was thrown
        caught = true;
      } catch (Exception e) {
        System.out.println("ERROR: incorrect exception was thrown");
        caught = false;
      }

      // Ensure that an exception was thrown
      if (!caught) {
        System.out.println("ERROR: no IllegalArgumentException was thrown");
        return false;
      }

    }


    return true; // All tests passed
  }

  /**
   * This method runs testers relating to testing adding an Event to an alphabetical priority queue
   * 
   * 
   * @return true if all tests relating to adding an Event to an alphabetical priority queue pass;
   *         false otherwise
   */
  private static boolean testAddEventAlphabetical() {


    // Test Scenario 1: Tests that addEventAlphabetical returns true when events of different
    // descriptions are added
    {
      // Ensure the queue is in alphabetical mode
      PriorityEvents.sortAlphabetically();

      // Create a small priority events queue
      PriorityEvents pq = new PriorityEvents(3);

      // Create 3 events with different descriptions
      Event beginning = new Event("A", 1, 5, 30);
      Event middle = new Event("B", 2, 2, 30);
      Event end = new Event("C", 3, 2, 30);

      // Add the events to the priority events queue out of order
      pq.addEvent(end);
      pq.addEvent(beginning);
      pq.addEvent(middle);

      // Check the internal heap data to ensure it's not already sorted
      Event[] data = pq.getHeapData();
      // In a fully sorted array, data[1] < data[2] so this should fail
      if (data[1].getDescription().compareTo(data[2].getDescription()) < 0) {
        System.out.println("ERROR: heap data is already fully sorted");
        return false;
      }

      // Ensure that the first "peek" is the beginning event
      if (!pq.peekNextEvent().equals(beginning)) {
        System.out.println("ERROR: first event isn't the beginning event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the second "peek" is the middle event
      if (!pq.peekNextEvent().equals(middle)) {
        System.out.println("ERROR: second event isn't the middle event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the third "peek" is the end event
      if (!pq.peekNextEvent().equals(end)) {
        System.out.println("ERROR: third event isn't the end event");
        return false;
      }

    }



    // Test Scenario 2: Tests that addEventAlphabetical returns true when events of different
    // descriptions and events of the same descriptions are added
    {
      // Ensure the queue is in alphabetical mode
      PriorityEvents.sortAlphabetically();

      // Create a small priority events queue
      PriorityEvents pq = new PriorityEvents(3);

      // Create 2 events with different descriptions and one with a matching description
      Event beginning = new Event("A", 1, 5, 30);
      Event middle = new Event("A", 2, 2, 30);
      Event end = new Event("C", 3, 2, 30);

      // Add the events to the priority events queue out of order
      pq.addEvent(end);
      pq.addEvent(beginning);
      pq.addEvent(middle);

      // Check the internal heap data to ensure it's not already sorted
      Event[] data = pq.getHeapData();
      // In a fully sorted array, data[1] < data[2] so this should fail
      if (data[1].getDescription().compareTo(data[2].getDescription()) < 0) {
        System.out.println("ERROR: heap data is already fully sorted");
        return false;
      }

      // Ensure that the first "peek" is the beginning event
      if (!pq.peekNextEvent().equals(beginning)) {
        System.out.println("ERROR: first event isn't the beginning event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the second "peek" is the middle event
      if (!pq.peekNextEvent().equals(middle)) {
        System.out.println("ERROR: second event isn't the middle event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the third "peek" is the end event
      if (!pq.peekNextEvent().equals(end)) {
        System.out.println("ERROR: third event isn't the end event");
        return false;
      }

    }
    return true; // All tests passed
  }

  /**
   * This method runs all sub-testers related to testing marking an Event in the priority queue as
   * completed. You may wish to add additional output for clarity, or additional private tester
   * methods related to marking Events as completed.
   * 
   * @return true if all tests relating to removing an Event from a priority queue pass; false
   *         otherwise
   */
  public static boolean testCompleteEvent() {
    boolean testComplete = true;
    testComplete &= testCompleteEventChronological();
    testComplete &= testCompleteEventAlphabetical();
    return testComplete;
  }

  /**
   * This method runs all testers related to testing marking an Event in the chronological priority
   * queue as completed.
   * 
   * 
   * @return true if all tests relating to removing an an Event from a chronologcal priority queue
   *         pass; false otherwise
   */
  private static boolean testCompleteEventChronological() {


    // Test Scenario 1: Tests completing an event in a queue with multiple events
    {
      try {
        PriorityEvents.sortChronologically();

        PriorityEvents pq = new PriorityEvents(3);

        // Create three events
        Event first = new Event("First", 1, 2, 3);
        Event second = new Event("Second", 2, 2, 3);
        Event third = new Event("Third", 3, 2, 3);

        // Add events out of order
        pq.addEvent(second);
        pq.addEvent(third);
        pq.addEvent(first);

        // Check the internal heap data to ensure it's not already sorted
        Event[] data = pq.getHeapData();

        // If data[1] is less than data[2], the heap data is already sorted
        if (data[1].compareTo(data[2]) < 0) {
          System.out.println("ERROR: the heap data is fully sorted");
          return false;
        }

        // The first completion should remove the first event
        if (!pq.peekNextEvent().equals(first)) {
          System.out.println("ERROR: peeking the queue should return the first event");
          return false;
        }
        // Complete the first event in the queue
        pq.completeEvent();

        // Ensure that the event was marked as complete
        if (!first.isComplete()) {
          System.out.println("ERROR: event wasn't marked as complete");
          return false;
        }

        // Ensure that the size of the queue decrements after completing event
        if (pq.size() != 2) {
          System.out.println("ERROR: size wasn't decremented after event completion");
          return false;
        }

        // Now peeking the queue should return the second event
        if (!pq.peekNextEvent().equals(second)) {
          System.out.println("ERROR: peeking the queue should return the second event");
          return false;
        }

        Event[] completedEvents = pq.getCompletedEvents();

        // Ensure that the completed event is now in the completed events array
        if (!completedEvents[0].equals(first)) {
          System.out.println("ERROR: the completed event isn't in the completed events array");
          return false;
        }



      } catch (Exception e) {
        System.out.println("Exception was thrown");
      }


    }



    // Test Scenario 2: tests that the complete event method
    // throws an exception when the queue is empty
    {
      // Create an empty queue
      PriorityEvents pqEmpty = new PriorityEvents(3);
      boolean caught = false;

      try {
        pqEmpty.completeEvent();
      } catch (IllegalStateException e) {
        // If IllegalStateException was thrown, caught = true
        caught = true;
      }

      // Ensure that the exception was thrown
      if (!caught) {
        System.out.println("ERROR: no exception was thrown when the queue is empty");
        return false;
      }
    }



    // Test Scenario 3: tests that the complete event method throws an exception when
    // completedEvents is full
    {
      PriorityEvents.sortChronologically();
      PriorityEvents pq = new PriorityEvents(3);

      // completedEvents array should be 3*2 = 6
      int toFill = pq.getHeapData().length * 2;

      // Fill completedEvents to its capacity
      for (int i = 0; i < toFill; i++) {
        Event e = new Event("Event " + i, 1, 0, 0);
        pq.addEvent(e);
        pq.completeEvent();
      }

      boolean caught = false;

      try {
        pq.completeEvent();
      } catch (IllegalStateException e) {
        // Exception should be thrown
        caught = true;
      }

      // Ensure that an exception is thrown for a full completedEvents
      if (!caught) {
        System.out.println("ERROR: Exception wasn't thrown for a full completedEvents");
        return false;
      }

    }

    return true; // All tests passed
  }

  /**
   * This method runs all testers related to testing marking an Event in the alphabetical priority
   * queue as completed.
   * 
   * 
   * @return true if all tests relating to removing an an Event from an alphabetical priority queue
   *         pass; false otherwise
   */
  private static boolean testCompleteEventAlphabetical() {


    // Test Scenario 1: Tests completing an event in a queue with multiple events
    {
      PriorityEvents.sortAlphabetically();

      PriorityEvents pq = new PriorityEvents(3);

      Event a = new Event("A", 1, 2, 3);
      Event b = new Event("B", 1, 2, 3);
      Event c = new Event("C", 1, 2, 3);

      // Add events in random order
      pq.addEvent(c);
      pq.addEvent(a);
      pq.addEvent(b);

      // Check the internal heap data to ensure it's not already sorted
      Event[] data = pq.getHeapData();

      // If data[1] is less than data[2], the heap data is already sorted
      if (data[1].getDescription().compareTo(data[2].getDescription()) < 0) {
        System.out.println("ERROR: the heap data is fully sorted");
        return false;
      }

      // Ensure that peeking from the queue returns the first event
      if (!pq.peekNextEvent().equals(a)) {
        System.out.println("ERROR: peeking the queue should equal the first event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the event was marked as complete
      if (!a.isComplete()) {
        System.out.println("ERROR: event wasn't marked as complete");
        return false;
      }

      // Ensure that the size of the queue decrements after completing event
      if (pq.size() != 2) {
        System.out.println("ERROR: size wasn't decremented after event completion");
        return false;
      }

      // Now, peeking the queue should return the second event
      if (!pq.peekNextEvent().equals(b)) {
        System.out.println("ERROR: peeking the queue should equal the second event");
        return false;
      }

      // Ensure that the completed event was added to the completed events array
      Event[] completedEvents = pq.getCompletedEvents();
      if (!completedEvents[0].equals(a)) {
        System.out.println("ERROR: completed event wasn't added to the completed events array");
        return false;
      }


    }


    // Test Scenario 2: tests that the complete event method
    // throws an exception when the queue is empty
    {
      // Create an empty queue
      PriorityEvents pqEmpty = new PriorityEvents(3);
      boolean caught = false;

      try {
        pqEmpty.completeEvent();
      } catch (IllegalStateException e) {
        // If an IllegalStateException was thrown, caught = true
        caught = true;
      }

      // Ensure that an exception was thrown for an empty queue
      if (!caught) {
        System.out.println("ERROR: no exception was thrown when the queue is empty");
        return false;
      }
    }

    // Test Scenario 3: tests that the complete event method throws an exception when
    // completedEvents is full
    {
      PriorityEvents.sortAlphabetically();
      PriorityEvents pq = new PriorityEvents(3);

      // completedEvents array should be 3*2 = 6
      int toFill = pq.getHeapData().length * 2;

      // Fill completedEvents to its capacity
      for (int i = 0; i < toFill; i++) {
        Event e = new Event("Event " + i, 1, 0, 0);
        pq.addEvent(e);
        pq.completeEvent();
      }

      boolean caught = false;

      try {
        pq.completeEvent();
      } catch (IllegalStateException e) {
        // Exception should be thrown
        caught = true;
      }

      // Ensure that an exception is thrown for a full completedEvents
      if (!caught) {
        System.out.println("ERROR: Exception wasn't thrown for a full completedEvents");
        return false;
      }

    }
    return true; // All tests passed
  }

  /**
   * Verifies the peekNextEvent() method. You may wish to break this out into smaller sub-testers.
   * 
   * @return true if all tests pass; false otherwise
   */
  // CITE: ProgramIz for increasing my understanding of peeking from queues
  public static boolean testPeek() {
    // Test Scenario 1: Ensures that testPeek returns true when peeking the elements of
    // the queue
    {
      // Ensure the queue is in chronological mode
      PriorityEvents.sortChronologically();

      // Create a small priority events queue
      PriorityEvents pq = new PriorityEvents(3);

      // Create 3 events
      Event beginning = new Event("A", 1, 2, 30);
      Event middle = new Event("B", 2, 2, 30);
      Event end = new Event("C", 3, 2, 30);

      // Add the events to the priority events queue
      pq.addEvent(end);
      pq.addEvent(beginning);
      pq.addEvent(middle);

      // Ensure that the first "peek" is the beginning event
      if (!pq.peekNextEvent().equals(beginning)) {
        System.out.println("ERROR: first event isn't the beginning event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the second "peek" is the middle event
      if (!pq.peekNextEvent().equals(middle)) {
        System.out.println("ERROR: second event isn't the middle event");
        return false;
      }
      pq.completeEvent();

      // Ensure that the third "peek" is the end event
      if (!pq.peekNextEvent().equals(end)) {
        System.out.println("ERROR: third event isn't the end event");
        return false;
      }
    }

    // Test Scenario 2: Tests that the peek method throws an exception when the heap is empty.
    {
      boolean caught = false;
      // Create a small priority events queue
      PriorityEvents pq = new PriorityEvents(3);
      try {
        // Exception should be thrown when trying to peek the next event
        pq.peekNextEvent();
      } catch (NoSuchElementException e) {
        caught = true;
      }

      if (!caught) {
        System.out.println("ERROR: An exception wasn't thrown");
        return false;
      }

    }


    return true; // All tests passed
  }

  /**
   * Verifies the overloaded PriorityEvents constructor that creates a valid heap from an input
   * array of values. You may wish to break this out into smaller sub-testers.
   * 
   * @return true if all tests pass; false otherwise
   */
  public static boolean testHeapify() {

    // Test Scenario 1: tests that the PriorityEvents constructor correctly creates a valid heap
    // for a chronological priority queue
    {
      // Set to chronological mode
      PriorityEvents.sortChronologically();

      // Build an array with the events to be added to the priority queue
      Event event1 = new Event("A", 1, 3, 0);
      Event event2 = new Event("B", 1, 1, 0);
      Event event3 = new Event("C", 1, 2, 0);

      Event[] events = new Event[5];
      events[0] = event1;
      events[1] = event2;
      events[2] = event3;

      // Call the heapify constructor
      PriorityEvents pq = new PriorityEvents(events, 3);

      // Ensure that the internal heap capacity equals the input length
      Event[] heapData = pq.getHeapData();
      if (heapData.length != events.length) {
        System.out.println("ERROR: heap length doesn't match events length");
        return false;
      }


      // Ensure that a valid min-heap was constructed
      // Peeking the next event should equal event2
      if (!pq.peekNextEvent().equals(event2)) {
        System.out.println("ERROR: the priority queue isn't a valid min-heap");
        return false;
      }

    }

    // Test Scenario 2: tests that the PriorityEvents constructor correctly creates a valid heap
    // for an alphabetical priority queue
    {
      // Set to alphabetical mode
      PriorityEvents.sortAlphabetically();

      // Build an array with the events to be added to the priority queue
      Event event1 = new Event("B", 1, 1, 0);
      Event event2 = new Event("A", 1, 1, 0);
      Event event3 = new Event("C", 1, 1, 0);

      Event[] events = new Event[5];
      events[0] = event1;
      events[1] = event2;
      events[2] = event3;

      // Call the heapify constructor
      PriorityEvents pq = new PriorityEvents(events, 3);

      // Ensure that the internal heap capacity equals the input length
      Event[] heapData = pq.getHeapData();
      if (heapData.length != events.length) {
        System.out.println("ERROR: heap length doesn't match events length");
        return false;
      }


      // Ensure that a valid min-heap was constructed
      // Peeking the next event should equal event2
      if (!pq.peekNextEvent().equals(event2)) {
        System.out.println("ERROR: the priority queue isn't a valid min-heap");
        return false;
      }


    }

    // Test Scenario 3: tests that the PriorityEvents constructor throws an
    // IllegalArgumentException if any of the events in the array are already complete
    {
      PriorityEvents.sortChronologically();

      Event notComplete = new Event("A", 1, 2, 3);
      Event complete = new Event("B", 1, 2, 3);

      // Mark one of the events as complete
      complete.markAsComplete();

      // Create the array with the two events
      Event[] events = new Event[] {notComplete, complete};

      boolean caught = false;

      try {
        PriorityEvents pq = new PriorityEvents(events, 2);
      } catch (IllegalArgumentException e) {
        // Ensure IllegalArgumentException was thrown
        caught = true;
      }

      // If exception wasn't thrown, return false
      if (!caught) {
        System.out.println("ERROR: no exception was thrown for creating a priority queue with"
            + " an already complete event");
        return false;
      }


    }

    // Test Scenario 4: tests the constructor with the capacity parameter correctly constructs
    // a new priority queue of events
    {
      // Construct a priority queue of events with a capacity of 4
      int cap = 4;
      PriorityEvents pq = new PriorityEvents(cap);

      // Ensure that the length of the heap data should equal the input capacity
      if (pq.getHeapData().length != cap) {
        System.out.println("ERROR: the heap data length doesn't equal the capacity");
        return false;
      }


      // Ensure that the queue is empty
      if (!pq.isEmpty()) {
        System.out.println("ERROR: the queue should be empty");
        return false;
      }

      // Ensure that numCompleted equals 0
      if (pq.numCompleted() != 0) {
        System.out.println("ERROR: numCompleted should equal 0");
        return false;
      }
    }

    // Test Scenario 5: tests the constructor with the capacity parameter correctly throws
    // an IllegalArgumentException when the input capacity is 0
    {
      // Construct a priority queue of events with a capacity of 0
      int cap = 0;
      boolean caught = false;

      try {
        PriorityEvents pq = new PriorityEvents(cap);
      } catch (IllegalArgumentException e) {
        // Ensure IllegalArgumentException was thrown
        caught = true;
      }

      // If exception wasn't thrown, return false
      if (!caught) {
        System.out.println("ERROR: no exception was thrown for a capacity being 0");
        return false;
      }

    }
    return true; // All tests passed


  }

  public static void main(String[] args) {
    System.out.println("ADD: " + testAddEvent());
    System.out.println("COMPLETE: " + testCompleteEvent());
    System.out.println("PEEK: " + testPeek());
    System.out.println("HEAPIFY: " + testHeapify());
  }

}
