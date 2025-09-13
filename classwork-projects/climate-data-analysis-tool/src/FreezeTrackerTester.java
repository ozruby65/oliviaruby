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
// -https://www.codecademy.com/learn/linear-data-structures-java/modules/doubly-linked-lists-
// java/cheatsheet
// Helped with our understanding of removing nodes.
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * Tester class for FreezeTracker functionality.
 */
public class FreezeTrackerTester {

  /**
   * Tests adding records to an empty FreezeTracker and the end of a non-empty FreezeTracker.
   * 
   * Ensure that size has been updated correctly, that the first and last winters are correct, and
   * that all added records are present in the correct locations in the list.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testAdd() {
    // Test Scenario 1: Tests adding one lake record to the empty FreezeTracker.
    {
      FreezeTracker tracker = new FreezeTracker();

      // Verifies that the initial list if empty.
      if (!tracker.isEmpty() || tracker.size() != 0) {
        return false;
      }

      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
      tracker.add(record1);

      // Ensures that the size of the list is now one and the head and tail point to record 1.
      if (tracker.isEmpty() || tracker.size() != 1 || tracker.getHead() != tracker.getTail()
          || tracker.getHead().getLakeRecord() != record1) {
        return false;
      }
    }


    // Test Scenario 2: Tests adding a lake record to a non-empty FreezeTracker.
    {
      FreezeTracker tracker = new FreezeTracker();

      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
      tracker.add(record1);

      // Verifies the initial state of the list.
      if (tracker.isEmpty() || tracker.size() != 1) {
        return false;
      }


      LakeRecord record2 = new LakeRecord("2018-20", "December 20", "January 5", 35);
      tracker.add(record2);

      // Ensures that the size of the list increased and the tail node now points to the
      // added record.
      if (tracker.isEmpty() || tracker.size() != 2 || tracker.getHead().getLakeRecord() != record1
          || tracker.getTail().getLakeRecord() != record2
          || tracker.getHead().getNext() != tracker.getTail()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests removing records from different positions (beginning, middle, end). Your initial
   * FreezeTracker list should contain AT LEAST five records; none of these tests will clear out the
   * list (that's a different test, below).
   * 
   * Verify both return values and the list state.
   * 
   * @return true if all cases pass, false otherwise.
   */
  // CITE: Code academy for helping with our understanding of removing nodes from doubly
  // linked lists.
  public static boolean testRemove() {
    // Test Scenario 1: Removing a node from the beginning of the FreezeTracker list.
    {

      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);

      LakeRecord record2 = new LakeRecord("2018-20", "December 20", "January 25", 35);

      LakeRecord record3 = new LakeRecord("2017-20", "December 2", "January 5", 25);

      LakeRecord record4 = new LakeRecord("2016-20", "December 20", "January 5", 35);

      LakeRecord record5 = new LakeRecord("2015-20", "December 10", "January 5", 40);

      ArrayList<LakeRecord> records = new ArrayList<>();
      records.add(record1);
      records.add(record2);
      records.add(record3);
      records.add(record4);
      records.add(record5);

      FreezeTracker tracker = new FreezeTracker(records);


      // Ensures that the first record was removed.
      boolean removed = tracker.remove(record1);
      if (!removed) {
        System.out.println("ERROR: Record hasn't been removed.");
        return false;
      }

      // Checking size after removal.
      if (tracker.size() != 4) {
        return false;
      }

      // Ensure that head and tail are not null.
      if (tracker.getHead() == null || tracker.getTail() == null) {
        return false;
      }

      // Check the head and tail points to the correct records.
      if (tracker.getHead().getLakeRecord() != record2
          || tracker.getTail().getLakeRecord() != record5) {
        return false;
      }

      // Check that each pointer's integrity.
      LinkedNode node1 = tracker.getHead(); // record 2
      LinkedNode node2 = node1.getNext(); // record 3
      LinkedNode node3 = node2.getNext(); // record 4
      LinkedNode node4 = node3.getNext(); // record 5

      // Traverse forward to backward checking each pointer.
      if (node1.getPrev() != null || node1.getNext() != node2 || node2.getPrev() != node1
          || node2.getNext() != node3 || node3.getPrev() != node2 || node3.getNext() != node4
          || node4.getPrev() != node3 || node4.getNext() != null || node4 != tracker.getTail()) {
        return false;
      }

      // Ensure that record 1 isn't still in the list.
      LinkedNode curr = tracker.getHead();
      while (curr != null) {
        if (curr.getLakeRecord() == record1) {
          return false; // Removed record shouldn't be present
        }
        curr = curr.getNext();
      }


    }


    // Test Scenario 2: Removing a node from the middle of the FreezeTracker list.
    {

      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);

      LakeRecord record2 = new LakeRecord("2018-20", "December 20", "January 25", 35);

      LakeRecord record3 = new LakeRecord("2017-20", "December 2", "January 5", 25);

      LakeRecord record4 = new LakeRecord("2016-20", "December 20", "January 5", 35);

      LakeRecord record5 = new LakeRecord("2015-20", "December 10", "January 5", 40);

      ArrayList<LakeRecord> records = new ArrayList<>();
      records.add(record1);
      records.add(record2);
      records.add(record3);
      records.add(record4);
      records.add(record5);

      FreezeTracker tracker = new FreezeTracker(records);


      // Ensures that the middle record was removed.
      boolean removed = tracker.remove(record3);
      if (!removed) {
        System.out.println("ERROR: Record hasn't been removed.");
        return false;
      }

      // Check that size decremented to 4.
      if (tracker.size() != 4) {
        return false;
      }

      // Ensure that head and tail are not null.
      if (tracker.getHead() == null || tracker.getTail() == null) {
        return false;
      }

      // Check that head and tail records remain unchanged.
      if (tracker.getHead().getLakeRecord() != record1
          || tracker.getTail().getLakeRecord() != record5) {
        return false;
      }

      // Check the pointer integrity by traversing through the list forward to backward.
      LinkedNode node1 = tracker.getHead(); // record 1
      LinkedNode node2 = node1.getNext(); // record 2
      LinkedNode node3 = node2.getNext(); // record 4
      LinkedNode node4 = node3.getNext(); // record 5
      if (node1.getPrev() != null || // Head's prev should be null
          node1.getNext() != node2 || node2.getPrev() != node1 || node2.getNext() != node3
          || node3.getPrev() != node2 || node3.getNext() != node4 || node4.getPrev() != node3
          || node4.getNext() != null || // Tail's next should be null
          node4 != tracker.getTail()) {
        return false; // Pointers are incorrect
      }

      // Make sure that record 3 isn't still in the list.
      LinkedNode curr = tracker.getHead();
      while (curr != null) {
        if (curr.getLakeRecord() == record3) {
          return false; // Record shouldn't be present.
        }
        curr = curr.getNext();
      }


    }

    // Test Scenario 3: Removing a node from the end of the FreezeTracker list.

    {

      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);

      LakeRecord record2 = new LakeRecord("2018-20", "December 20", "January 25", 35);

      LakeRecord record3 = new LakeRecord("2017-20", "December 2", "January 5", 25);

      LakeRecord record4 = new LakeRecord("2016-20", "December 20", "January 5", 35);

      LakeRecord record5 = new LakeRecord("2015-20", "December 10", "January 5", 40);

      ArrayList<LakeRecord> records = new ArrayList<>();
      records.add(record1);
      records.add(record2);
      records.add(record3);
      records.add(record4);
      records.add(record5);


      FreezeTracker tracker = new FreezeTracker(records);


      // Verifies that the last record was removed.
      boolean removed = tracker.remove(record5);
      if (!removed) {
        System.out.println("ERROR: Record hasn't been removed.");
        return false;
      }

      // Check that the size decremented to 4.
      if (tracker.size() != 4) {
        return false;
      }

      // Ensure head and tail are not null
      if (tracker.getHead() == null || tracker.getTail() == null) {
        return false;
      }

      // Verify head pointer stays the same and the tail now points to record4.
      if (tracker.getHead().getLakeRecord() != record1
          || tracker.getTail().getLakeRecord() != record4) {
        return false;
      }


      // Check pointer integrity by traversing forward and backward through the list.
      LinkedNode node1 = tracker.getHead(); // record 1
      LinkedNode node2 = node1.getNext(); // record 2
      LinkedNode node3 = node2.getNext(); // record 3
      LinkedNode node4 = node3.getNext(); // record 4
      if (node1.getPrev() != null || // Head's prev should be null
          node1.getNext() != node2 || node2.getPrev() != node1 || node2.getNext() != node3
          || node3.getPrev() != node2 || node3.getNext() != node4 || node4.getPrev() != node3
          || node4.getNext() != null || // Tail's next should be null
          node4 != tracker.getTail()) {
        return false; // Pointers are incorrect
      }

      // Make sure that record 5 isn't still in the list.
      LinkedNode curr = tracker.getHead();
      while (curr != null) {
        if (curr.getLakeRecord() == record5) {
          return false; // Record shouldn't be present.
        }
        curr = curr.getNext();
      }
    }



    return true;
  }

  /**
   * Tests removing the ONLY value from a FreezeTracker.
   * 
   * Ensure all accessor methods behave correctly after removing it.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemoveOnly() {

    FreezeTracker tracker = new FreezeTracker();

    LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
    tracker.add(record1);

    // Verifies the initial state of the list.
    if (tracker.isEmpty() || tracker.size() != 1) {
      return false;
    }

    // Remove the only record.
    boolean removed = tracker.remove(record1);


    if (!removed) {
      System.out.println("ERROR: Record hasn't been removed.");
      return false;
    }

    // Ensures that the list is now empty.
    if (tracker.getHead() != null || tracker.getTail() != null || !tracker.isEmpty()
        || tracker.size() != 0) {
      return false;
    }


    return true;
  }

  /**
   * Tests removing a record from FreezeTracker which is not present in the list.
   * 
   * Verify both the return value and the list state.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemoveDoesNotExist() {

    FreezeTracker tracker = new FreezeTracker();
    LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
    tracker.add(record1);

    LakeRecord record2 = new LakeRecord("2018-20", "December 20", "January 25", 35);
    tracker.add(record2);

    LakeRecord record3 = new LakeRecord("2017-20", "December 2", "January 5", 25);
    tracker.add(record3);

    LinkedNode initialHead = tracker.getHead();
    LinkedNode initialTail = tracker.getTail();

    // Creates a record that wasn't added.
    LakeRecord nonExistentRecord = new LakeRecord("2020-21", "December 10", "January 5", 20);

    if (tracker.isEmpty() || tracker.size() != 3 || tracker.getHead().getLakeRecord() != record1
        || tracker.getTail().getLakeRecord() != record3) {
      return false;
    }
    boolean removed = tracker.remove(nonExistentRecord);

    // Ensures the method returns false.
    if (removed) {
      System.out.println("ERROR: Removing a non-existent record should return false.");
      return false;
    }

    // Ensures that the list remains unchanged.
    if (tracker.size() != 3) {
      return false;
    }
    if (tracker.getHead() != initialHead || tracker.getTail() != initialTail) {
      return false;
    }


    return true;
  }

  /**
   * Tests iterators (forward and backward). For full credit, this test MUST contain at least one
   * enhanced for loop with each type of iterator.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testIterators() {

    FreezeTracker tracker = new FreezeTracker();

    tracker.add(new LakeRecord("2020-21", "December 15", "March 10", 85));
    tracker.add(new LakeRecord("2021-22", "December 10", "March 5", 80));
    tracker.add(new LakeRecord("2022-23", "December 12", "March 8", 82));

    // Forward iteration test
    tracker.setReversed(false); // Forward
    String[] expectedWintersFwd = {"2020-21", "2021-22", "2022-23"}; // Chronological order
    int i = 0;
    for (LakeRecord record : tracker) { // enhanced for loop to check if iterator is working
                                        // properly
      if (!record.getWinter().equals(expectedWintersFwd[i])) {
        return false;
      }
      i++;
    }

    // Backward iteration test
    tracker.setReversed(true); // Backward
    String[] expectedWintersBwd = {"2022-23", "2021-22", "2020-21"}; // Chronological order
    i = 0;
    for (LakeRecord record : tracker) { // enhanced for loop to check if iterator is working
                                        // properly
      if (!record.getWinter().equals(expectedWintersBwd[i])) {
        return false;
      }
      i++;
    }

    return true; // All tests passed
  }

  /**
   * Tests merging multiple freeze records (provided!) for the same winter.
   * 
   * Add these records to a FreezeTracker and verify that merging them results in a list with a
   * single record with the correct values.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMergeWinters() {
    LakeRecord freeze1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
    LakeRecord freeze2 = new LakeRecord("2019-20", "January 20", "March 10", 50);
    LakeRecord freeze3 = new LakeRecord("2019-20", "March 15", "April 5", 20);

    // Add the freezes into the arraylist.
    ArrayList<LakeRecord> records = new ArrayList<>();
    records.add(freeze1);
    records.add(freeze2);
    records.add(freeze3);

    // Creating the freezetracker should automatically merge winters.
    FreezeTracker tracker = new FreezeTracker(records);

    // The linked list should only have 1 merged record.

    if (tracker.size() != 1) {
      System.out.println("ERROR: Unsuccessfully merged winters.");
      return false;
    }

    // Get the merged record.
    LakeRecord mergedRecord = tracker.getHead().getLakeRecord();

    // Check the expected values of the merged record.
    boolean correctWinter = mergedRecord.getWinter().equals("2019-20");
    boolean correctFreezeDate = mergedRecord.getFreezeDate().equals("December 1");
    boolean correctThawDate = mergedRecord.getThawDate().equals("April 5");
    boolean correctDays = mergedRecord.getDaysOfIceCover() == (45 + 50 + 20);

    // Check if any of the merged record values are incorrect.
    if (!correctWinter || !correctFreezeDate || !correctThawDate || !correctDays) {
      System.out.println("ERROR: Merged record has incorrect records.");
      return false;
    }


    return true;
  }

  /**
   * Tests cleaning the dataset (removing incomplete records). Create a FreezeTracker with some
   * valid and invalid records, and verify that all of the invalid records are removed (and none of
   * the valid ones).
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testCleanData() {
    // Test Scenario 1: Tests that lakerecord with a missing date is removed.
    {
      LakeRecord freeze1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
      LakeRecord freeze2 = new LakeRecord("2021-20", "January 20", "March 10", 50);
      LakeRecord missingThaw = new LakeRecord("2020-20", "January 20", null, 50);

      ArrayList<LakeRecord> records = new ArrayList<>();
      records.add(freeze1);
      records.add(freeze2);
      records.add(missingThaw);

      FreezeTracker tracker = new FreezeTracker(records);

      // Ensures that the size is one and the only record in the list is freeze1.
      if (tracker.size() != 2 || tracker.getHead().getLakeRecord() != freeze1
          || tracker.getTail().getLakeRecord() != freeze2) {
        return false;
      }

      // Double checks that the missingThaw lake record isn't in the list.
      LinkedNode curr = tracker.getHead();
      while (curr != null) {
        if (curr.getLakeRecord().equals(missingThaw)) {
          return false;
        }
        curr = curr.getNext();
      }

    }

    // Test Scenario 2: Tests that a lake record with no freeze date is removed.
    {
      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
      LakeRecord missingFreeze = new LakeRecord("2015-20", null, "January 4", 50);

      ArrayList<LakeRecord> records = new ArrayList<>();
      records.add(record1);
      records.add(missingFreeze);

      FreezeTracker tracker = new FreezeTracker(records);

      // Ensures that the size is one and the only record in the list is record1.
      if (tracker.size() != 1 || tracker.getHead().getLakeRecord() != record1
          || tracker.getTail().getLakeRecord() != record1) {
        return false;
      }

      // Double checks that the missingFreeze lake record isn't in the list.
      LinkedNode curr = tracker.getHead();
      while (curr != null) {
        if (curr.getLakeRecord().equals(missingFreeze)) {
          return false;
        }
        curr = curr.getNext();
      }

    }

    // Test Scenario 3: Tests that a lake record with multiple invalid details gets removed.
    {
      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 45);
      LakeRecord record2 = new LakeRecord("2016-20", "December 20", "January 3", 30);
      LakeRecord missingDetails = new LakeRecord("2015-20", null, null, 50);

      ArrayList<LakeRecord> records = new ArrayList<>();
      records.add(record1);
      records.add(record2);
      records.add(missingDetails);

      FreezeTracker tracker = new FreezeTracker(records);

      // Ensures that the size is two and the only records in the list is record1 and record2.
      if (tracker.size() != 2 || tracker.getHead().getLakeRecord() != record1
          || tracker.getTail().getLakeRecord() != record2) {
        return false;
      }

      // Double checks that the missingDetails lake record isn't in the list.
      LinkedNode curr = tracker.getHead();
      while (curr != null) {
        if (curr.getLakeRecord().equals(missingDetails)) {
          return false;
        }
        curr = curr.getNext();
      }
    }

    return true;
  }

  /**
   * Tests computing the average freeze duration.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testAverageFreezeDuration() {
    // Test Scenario 1: Getting a non-integer value average freeze duration of 3 lake records.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 1);
      LakeRecord record2 = new LakeRecord("2016-20", "January 20", "January 3", 2);
      LakeRecord record3 = new LakeRecord("2018-20", "December 1", "January 15", 4);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      float expectedAverage = (1f + 2f + 4f) / 3f;

      float actualAverage = tracker.getAverageFreezeDuration();


      if (Math.abs(expectedAverage - actualAverage) > 0.0001) {
        System.out.println("TEST 1: expected doesn't match with actual average.");
        return false;
      }

    }

    // Test Scenario 2: getting the average freeze duration of an empty list.
    {
      FreezeTracker tracker = new FreezeTracker();

      float expectedAverage = 0f;
      float actualAverage = tracker.getAverageFreezeDuration();


      if (Math.abs(expectedAverage - actualAverage) > 0.0001) {
        System.out.println("TEST 2: expected doesn't match with actual average.");
        return false;
      }
    }

    // Test Scenario 3: getting the average freeze duration of a list with one record.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2019-20", "December 1", "January 15", 15);

      tracker.add(record1);

      float expectedAverage = 15f;

      float actualAverage = tracker.getAverageFreezeDuration();



      if (Math.abs(expectedAverage - actualAverage) > 0.0001) {
        System.out.println("TEST 3: expected doesn't match with actual average.");
        return false;
      }


    }


    return true; // All tests passed
  }

  /**
   * Tests finding the maximum number of days of ice cover in a single winter.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMaxFreezeDuration() {

    // Test Scenario 1: Getting the max freeze duration between two lake records.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 15", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);

      tracker.add(record1);
      tracker.add(record2);

      int expectedMax = 25;

      int actualMax = tracker.getMaxFreezeDuration();

      // Ensure that the max freeze duration equals the expected freeze duration.
      if (expectedMax != actualMax) {
        return false;
      }
    }


    return true;
  }

  /**
   * Tests finding the minimum number of days of ice cover in a single winter.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMinFreezeDuration() {

    // Test Scenario 1: Getting the min freeze duration between two lake records.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 15", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);

      tracker.add(record1);
      tracker.add(record2);

      int expectedMin = 16;

      int actualMin = tracker.getMinFreezeDuration();

      // Ensure that the min freeze duration equals the expected freeze duration.
      if (expectedMin != actualMin) {
        return false;
      }
    }
    return true; // All tests passed
  }

  /**
   * Tests finding the earliest freeze.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testGetEarliestFreeze() {
    // Test Scenario 1: Getting the earliest freeze date between three lake records.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 15", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);
      LakeRecord record3 = new LakeRecord("2016-2", "December 30", "January 2", 20);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      String expectedFreeze = "December 1";

      String actualFreeze = tracker.getEarliestFreeze();

      // Ensure that the freeze date equals the expected freeze date.
      if (!expectedFreeze.equalsIgnoreCase(actualFreeze)) {
        return false;
      }
    }

    return true; // All tests passed
  }

  /**
   * Tests finding the latest thaw.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testGetLatestThaw() {
    // Test Scenario 1: Getting the latest thaw between three lake records.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 5", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);
      LakeRecord record3 = new LakeRecord("2016-2", "December 30", "January 2", 20);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      String expectedThaw = "January 15";

      String actualThaw = tracker.getLatestThaw();

      // Ensure that the thaw date equals the expected thaw date.
      if (!expectedThaw.equalsIgnoreCase(actualThaw)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests filtering freeze records by a range of years.
   * 
   * Ensure that only records between the specified years (inclusive) are present in the result.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testFilterByYear() {
    // Test Scenario 1: Tests that records between the year range are added to a new linked list.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 5", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);
      LakeRecord record3 = new LakeRecord("2013-2", "December 30", "January 2", 20);
      LakeRecord record4 = new LakeRecord("2019-15", "December 30", "January 2", 20);
      LakeRecord record5 = new LakeRecord("2012-2", "December 30", "January 2", 20);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      tracker.add(record4);
      tracker.add(record5);


      FreezeTracker filteredTracker = new FreezeTracker();
      filteredTracker = tracker.filterByYear(2013, 2020);

      LinkedNode curr = filteredTracker.getHead();

      // Ensure that the records are within the year range.
      while (curr != null) {
        String winterYearString = curr.getLakeRecord().getWinter().split("-")[0];

        int winterYear = Integer.parseInt(winterYearString);

        if (winterYear < 2013 || winterYear > 2020) {
          System.out.println("FAILED: years aren't in range");
          return false;
        }
        curr = curr.getNext();
      }

    }


    // Test Scenario 2: Empty list should be returned if there are no lake records in the range.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 5", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);
      LakeRecord record3 = new LakeRecord("2018-2", "December 30", "January 2", 20);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      FreezeTracker filteredTracker = new FreezeTracker();
      filteredTracker = tracker.filterByYear(2019, 2020);



      // Ensure that the filtered list is empty.
      if (filteredTracker.getHead() != null || filteredTracker.size() != 0) {
        return false;
      }
    }

    // Test Scenario 3: Ensure that boundary years are included in the filtered list.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2013-14", "December 1", "January 5", 25);
      // Boundary year 2013
      LakeRecord record2 = new LakeRecord("2015-16", "December 10", "January 15", 16);
      LakeRecord record3 = new LakeRecord("2020-21", "December 30", "January 2", 20);
      // Boundary year 2020

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      FreezeTracker filteredTracker = tracker.filterByYear(2013, 2020);

      boolean has2013 = false;
      boolean has2020 = false;
      LinkedNode curr = filteredTracker.getHead();

      while (curr != null) {
        String winterYearString = curr.getLakeRecord().getWinter().split("-")[0];
        int winterYear = Integer.parseInt(winterYearString);
        if (winterYear == 2013) {
          has2013 = true;
        }
        if (winterYear == 2020) {
          has2020 = true;
        }
        curr = curr.getNext();
      }

      if (!has2013 || !has2020) {
        return false; // Fails because the boundary years weren't included.
      }
    }

    return true; // All tests passed
  }

  /**
   * Tests filtering freeze records by a range of ice cover duration values.
   * 
   * Ensure that only records within the duration range are included in the filtered list.
   * 
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testFilterByDuration() {
    // Test Scenario 1: Tests that records between the day range are added to a new linked list.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 5", 25);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 16);
      LakeRecord record3 = new LakeRecord("2018-2", "December 30", "January 2", 11);
      LakeRecord record4 = new LakeRecord("2018-2", "December 30", "January 2", 20);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);
      tracker.add(record4);

      FreezeTracker filteredTracker = new FreezeTracker();
      filteredTracker = tracker.filterByDuration(10, 15);

      LinkedNode curr = filteredTracker.getHead();

      // Ensure that the records in the filtered list all have days of ice cover within the range.
      while (curr != null) {
        int numDays = curr.getLakeRecord().getDaysOfIceCover();

        if (numDays < 10 || numDays > 15) {
          return false;
        }
        curr = curr.getNext();
      }

    }

    // Test Scenario 2: Empty list should be returned if there are no lake records in the range.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 5", 35);
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 46);
      LakeRecord record3 = new LakeRecord("2018-2", "December 30", "January 2", 41);

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      FreezeTracker filteredTracker = new FreezeTracker();
      filteredTracker = tracker.filterByDuration(20, 30);

      // Ensure that the new list is empty.
      if (filteredTracker.getHead() != null || filteredTracker.size() != 0) {
        return false;
      }
    }

    // Test Scenario 3: Ensure that boundary days are included in the filtered list.
    {
      FreezeTracker tracker = new FreezeTracker();
      LakeRecord record1 = new LakeRecord("2017-20", "December 1", "January 5", 20);
      // Boundary day 20
      LakeRecord record2 = new LakeRecord("2018-20", "December 10", "January 15", 46);
      LakeRecord record3 = new LakeRecord("2018-2", "December 30", "January 2", 50);
      // Boundary day 50

      tracker.add(record1);
      tracker.add(record2);
      tracker.add(record3);

      FreezeTracker filteredTracker = new FreezeTracker();
      filteredTracker = tracker.filterByDuration(20, 50);

      LinkedNode curr = filteredTracker.getHead();
      boolean hasLower = false;
      boolean hasUpper = false;

      while (curr != null) {
        int duration = curr.getLakeRecord().getDaysOfIceCover();

        if (duration == 20) {
          hasLower = true;
        }
        if (duration == 50) {
          hasUpper = true;
        }
        if (duration < 20 || duration > 50) {
          return false; // Days are not within the bounds.
        }
        curr = curr.getNext();
      }

      if (!hasLower || !hasUpper) {
        return false; // Boundary days weren't included.
      }
    }

    return true; // All tests passed
  }

  /**
   * Main Method to Launch the tester methods.
   * 
   * @param args list of inputs if any.
   */
  public static void main(String[] args) {
    System.out.println("Running tests:");
    System.out.println("testAdd(): " + (testAdd() ? "PASSED" : "FAILED"));
    System.out.println("testRemove(): " + (testRemove() ? "PASSED" : "FAILED"));
    System.out.println("testRemoveOnly(): " + (testRemoveOnly() ? "PASSED" : "FAILED"));
    System.out
        .println("testRemoveDoesNotExist(): " + (testRemoveDoesNotExist() ? "PASSED" : "FAILED"));
    System.out.println("testIterators(): " + (testIterators() ? "PASSED" : "FAILED"));
    System.out.println("testMergeWinters(): " + (testMergeWinters() ? "PASSED" : "FAILED"));
    System.out.println("testCleanData(): " + (testCleanData() ? "PASSED" : "FAILED"));
    System.out.println(
        "testAverageFreezeDuration(): " + (testAverageFreezeDuration() ? "PASSED" : "FAILED"));
    System.out
        .println("testMaxFreezeDuration(): " + (testMaxFreezeDuration() ? "PASSED" : "FAILED"));
    System.out
        .println("testMinFreezeDuration(): " + (testMinFreezeDuration() ? "PASSED" : "FAILED"));
    System.out
        .println("testGetEarliestFreeze(): " + (testGetEarliestFreeze() ? "PASSED" : "FAILED"));
    System.out.println("testGetLatestThaw(): " + (testGetLatestThaw() ? "PASSED" : "FAILED"));
    System.out.println("testFilterByYear(): " + (testFilterByYear() ? "PASSED" : "FAILED"));
    System.out.println("testFilterByDuration(): " + (testFilterByDuration() ? "PASSED" : "FAILED"));

    boolean allTestsPassed =
        testAdd() && testRemove() && testRemoveOnly() && testRemoveDoesNotExist() && testIterators()
            && testMergeWinters() && testCleanData() && testAverageFreezeDuration()
            && testMaxFreezeDuration() && testMinFreezeDuration() && testGetEarliestFreeze()
            && testGetLatestThaw() && testFilterByYear() && testFilterByDuration();
    System.out.println("ALL TESTS: " + (allTestsPassed ? "PASSED" : "FAILED"));
  }
}
