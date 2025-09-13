//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Month Calendar Java
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
// Persons: Kevin Ruby helped me debug the testEventCompareTo() method.
// Online Sources:
// - https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2025/chapter/3/section/2
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Utility class that defines tester methods for p04 Monthly Calendar.
 */
public class CalendarTester {

  /**
   * Ensures the correctness of the constructor and getter methods defined in the Event class when
   * no exception is expected to be thrown.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEventConstructorAndGettersValidBehavior() {
    // Test Scenario 1: Tests that the get day method returns the correct day.
    {
      Event dinner = new Event("Going to dinner", 2, 2, 30);
      if (dinner.getDay() != 2) {
        return false;
      }
    }

    // Test Scenario 2: Tests that the get description method returns the correct description.
    {
      Event gym = new Event("Go to gym", 4, 3, 30);
      if (!gym.getDescription().equals("Go to gym")) {
        return false;
      }
    }

    // Test Scenario 3: Tests that the get start time as string method properly returns the start
    // time and in the correct format.
    {
      Event work = new Event("Go to work", 3, 2, 15);
      if (!work.getStartTimeAsString().equals("02:15")) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor of the Event class when it is passed invalid inputs.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  // CITE: Zybooks chapter 3.2 taught me how to handle and throw exceptions.
  public static boolean testEventConstructorThrowingExceptions() {
    // Test Scenario 1: Tests that an exception is thrown if an event is created with a
    // blank description.
    {
      try {
        Event shopping = new Event("", 3, 2, 15);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;
      }
    }


    // Test Scenario 2: Tests that an exception is thrown if an event is created with an
    // invalid day.
    {
      try {
        Event studying = new Event("Study at library", -1, 3, 15);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;
      }
    }


    // Test Scenario 3: Tests that an exception is thrown if an event is created with an invalid
    // start hour.
    {
      try {
        Event gym = new Event("Go to gym", 1, 24, 15);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;
      }
    }

    // Test Scenario 4: Tests that an exception is thrown if an event is created with an invalid
    // start minute.
    {
      try {
        Event lunch = new Event("Lunch with mom", 1, 21, 60);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;
      }
    }

    // Test Scenario 5: Tests that no exceptions are thrown if all input is valid.
    {
      try {
        Event lunch = new Event("School", 1, 2, 30);
      } catch (Exception e) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the Event.compareTo() method.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  // CITE: Kevin Ruby helped me debug this.
  public static boolean testEventCompareTo() {

    // Test Scenario 1: Tests that a negative
    // number is returned when e1 start time is less than e2 start time.
    // e1.compareTo(e2) < 0
    {
      Event e1 = new Event("Dinner", 1, 1, 15);
      Event e2 = new Event("Dinner", 1, 2, 30);
      if (e1.compareTo(e2) > 0) {
        return false;
      }
    }

    // Test Scenario 2: Tests that a positive number
    // is returned when e1 start time is greater than e2 start time.
    // e1.compareTo(e2) > 0
    {
      Event e1 = new Event("Dinner", 1, 2, 15);
      Event e2 = new Event("Dinner", 1, 1, 30);
      if (e1.compareTo(e2) < 0) {
        return false;
      }
    }

    // Test Scenario 3: Tests that 0 is returned when e1 start time equals e2 start time.
    // e1.compareTo(e2) == 0
    {
      Event e1 = new Event("Dinner", 1, 2, 15);
      Event e2 = new Event("Dinner", 1, 2, 15);
      if (e1.compareTo(e2) != 0) {
        return false;
      }

    }

    // Test Scenario 4: Tests that 0 is returned when e1 start time equals e1 start time with the
    // events on different days.
    {
      Event e1 = new Event("Dinner", 4, 2, 15);
      Event e2 = new Event("Dinner", 10, 2, 15);
      if (e1.compareTo(e2) != 0) {
        return false;
      }
    }

    // Test Scenario 5: Tests that 0 is returned when e1 start time equals e2 start time.
    // e2.compareTo(e1) == 0
    {
      Event e1 = new Event("Dinner", 1, 2, 15);
      Event e2 = new Event("Dinner", 1, 2, 15);
      if (e2.compareTo(e1) != 0) {
        return false;
      }
    }



    return true;
  }

  /**
   * Ensures the correctness of the Event.equals() method.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEventEquals() {
    // Test Scenario 1: Tests that two events with the same day, description,
    // and start time are equal.
    {
      Event e1 = new Event("Dinner", 1, 2, 30);
      Event e2 = new Event("Dinner", 1, 2, 30);
      if (!e1.equals(e2)) {
        return false;
      }

    }

    // Test Scenario 2: Tests that comparing an event to its String representation returns false.
    {
      Event e1 = new Event("Dinner", 1, 2, 30);
      String e1ToString = e1.toString();
      if (e1.equals(e1ToString)) {
        return false;
      }
    }

    // Test Scenario 3: Tests that comparing an event to a null reference returns false.
    {
      Event e1 = new Event("Dinner", 1, 2, 30);
      if (e1.equals(null)) {
        return false;
      }
    }

    // Test Scenario 4: Tests that only comparing an an event with it's start time returns false.
    {
      Event e1 = new Event("Lacrosse", 2, 2, 30);
      Event e2 = new Event("Dinner", 1, 2, 30);
      if (e1.equals(e2)) {
        return false;
      }
    }

    // Test Scenario 5: Tests that the instanceof check works properly.

    {
      Event e1 = new Event("Dinner", 1, 2, 30);
      Object notEvent = "N/A";
      if (e1.equals(notEvent)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the MonthCalendar.addEvent() method when valid inputs are provided
   * and no duplicate event exists for the day, ensuring the event is added correctly to the
   * specified day's list.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testSuccessfulAddEvent() {
    MonthCalendar calendar = new MonthCalendar(2024, 4);

    // Test Scenario 1: Tests that adding multiple events to one day with valid inputs, returns
    // true.
    {
      if (!(calendar.addEvent(1, "Gym", 1, 20) && calendar.addEvent(1, "Dinner", 6, 30)
          && calendar.addEvent(1, "Shower", 7, 15) && calendar.addEvent(1, "Brush teeth", 7, 15))) {
        return false;
      }

      ArrayList<Event> events = calendar.getEvents()[0];

      // Checks that the size of the events list is 4.
      if (events.size() != 4) {
        System.out.println("ERROR: Size is incorrect");
        return false;
      }

      // Checks that the events are in ascending order by start time.

      if (!(events.get(0).getDescription().equals("Gym")
          && events.get(1).getDescription().equals("Dinner")
          && events.get(2).getDescription().equals("Shower"))
          && events.get(3).getDescription().equals("Brush teeth")) {
        return false;
      }

      // Checks that the two events with the same start time retain their order of addition.

      /*
       * if (!events.get(2).getDescription().equals("Shower") ||
       * !events.get(3).getDescription().equals("Brush teeth")) { System.out.println("here");
       * 
       * return false; }
       */

    }


    return true;
  }

  /**
   * Ensures the correctness of the MonthCalendar.addEvent() method when called with invalid inputs
   * or an attempt to add a duplicate event.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testUnsuccessfulAddEvents() {
    MonthCalendar calendar = new MonthCalendar(2024, 4);

    // Test Scenario 1: Tests that false is returned when provided an invalid day.
    {
      if (calendar.addEvent(-1, "Work", 1, 20) != false) {
        return false;
      }
      if (calendar.getEvents()[0].size() != 0) {
        return false;
      }
    }

    // Test Scenario 2: Tests that false is returned when provided an invalid description.
    {
      if (calendar.addEvent(1, null, 1, 20) != false) {
        return false;
      }
      if (calendar.getEvents()[0].size() != 0) {
        return false;
      }
    }

    // Test Scenario 3: Tests that false is returned when an invalid start time is provided.
    {
      if (calendar.addEvent(1, "Work", -1, 2) != false) {
        return false;
      }
      if (calendar.getEvents()[0].size() != 0) {
        return false;
      }
    }

    // Test Scenario 4: Tests that false is returned when attempting to add a duplicate event.
    {
      calendar.addEvent(1, "Work", 4, 20);
      if (calendar.addEvent(1, "Work", 4, 20) != false) {
        return false;
      }
      if (calendar.getEvents()[0].size() != 1) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the MonthCalendar.cancelEvent() method when no exceptions are
   * expected.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testCancelEventValid() {
    // Test Scenario 1: Tests that the event is correctly cancelled.
    MonthCalendar calendar = new MonthCalendar(2024, 4);

    calendar.addEvent(1, "Work", 2, 0);
    calendar.addEvent(1, "Dinner", 6, 30);
    calendar.cancelEvent("Work", 1, 2, 0);

    ArrayList<Event> events = calendar.getEvents()[0];

    if (!(events.get(0).getDescription().equals("Dinner"))) {
      return false;
    }


    return true;
  }

  /**
   * Ensures the correctness of the MonthCalendar.cancelEvent() method when exceptions are expected.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  // CITE: Zybooks chapter 3.2 taught me how to handle and throw exceptions.
  public static boolean testCancelEventExceptions() {
    MonthCalendar calendar = new MonthCalendar(2024, 4);

    // Test Scenario 1: Tests that cancelling an event with an invalid day throws an exception.
    {
      try {
        calendar.cancelEvent("Work", -1, 2, 0);
        return false;
      } catch (IllegalArgumentException e) {
        if (e.getMessage() == null || e.getMessage().isBlank()) {
          return false;
        }
      } catch (NoSuchElementException e) {
        return false;
      }
    }
    // Test Scenario 2: Tests that removing an event that doesn't exist
    // throws a NoSuchElementException
    {
      try {
        calendar.cancelEvent("Dinner", 2, 3, 0);
        return false;
      } catch (IllegalArgumentException e) {
        return false;
      } catch (NoSuchElementException e) {
        if (e.getMessage() == null || e.getMessage().isBlank()) {
          return false;
        }

      }
    }

    // Test Scenario 3: Tests that removing an event with an empty description
    // throws an IllegalArgumentException.
    {
      try {
        calendar.cancelEvent("", 2, 3, 0);
        return false;
      } catch (IllegalArgumentException e) {
        if (e.getMessage() == null || e.getMessage().isBlank()) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }

    // Test Scenario 4: Tests that removing an event with a null description
    // throws an IllegalArgumentException.
    {
      try {
        calendar.cancelEvent(null, 2, 3, 0);
        return false;
      } catch (IllegalArgumentException e) {
        if (e.getMessage() == null || e.getMessage().isBlank()) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }


  /**
   * Main method to run the tester methods
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventConstructorAndGettersValidBehavior: "
        + (testEventConstructorAndGettersValidBehavior() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventConstructorThrowingExceptions: "
        + (testEventConstructorThrowingExceptions() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventCompareTo: " + (testEventCompareTo() ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventEquals: " + (testEventEquals() ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------------------");

    System.out
        .println("testSuccessfulAddEvent: " + (testSuccessfulAddEvent() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "testUnsuccessfulAddEvents: " + (testUnsuccessfulAddEvents() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");

    System.out.println("testCancelEventValid(): " + (testCancelEventValid() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out
        .println("testDeleteExceptions: " + (testCancelEventExceptions() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
  }

}
