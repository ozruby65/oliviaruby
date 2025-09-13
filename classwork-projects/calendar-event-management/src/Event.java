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
// Persons: Kevin Ruby helped me debug the isComplete() and markAsComplete() methods.
// Online Sources:
// - Write-up of p04 was the source of the toString() method.
// - https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
//
///////////////////////////////////////////////////////////////////////////////
/**
 * The Event class represents a scheduled event with a description, date, and start time. It allows
 * tracking whether an event is complete and provides a way to compare events based on their
 * scheduled start time.
 */
public class Event implements java.lang.Comparable<Event> {
  private int day = 0; // Scheduled day of the event in the month (valid range: 1-31).

  private String description = ""; // A brief text description of this event, cannot be null or
  // blank

  private boolean isComplete; // Completion status of this event.

  private int startHour = 0; // Start hour of this event in 24-hour format (0-23),
  // EX: 6pm = 18, Midnight = 0

  private int startMin = 0; // Start minute of this event (0-59).

  private int startTime = 0; // The start time of this event as a single integer


  /**
   * Constructs a new Event object with the specified description, scheduled day, and start time and
   * sets all its instance fields.
   * 
   * @param description - A brief text description of this event.
   * @param day         - The day of the month this event is scheduled for (1-31).
   * @param startHour   - The hour this event starts (0-23, 24-hour format).
   * @param startMin    - The minute this event starts (0-59).
   * @return none
   * @throws IllegalArgumentException - If any parameter is null, blank, or out of range.
   */
  public Event(String description, int day, int startHour, int startMin) {

    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("ERROR: description CANNOT be null or blank.");
    }
    if (day < 1 || day > 31) {
      throw new IllegalArgumentException("ERROR: day MUST be within range.");
    }
    if (startHour < 0 || startHour > 23) {
      throw new IllegalArgumentException("ERROR: start hour MUST be within range.");
    }
    if (startMin < 0 || startMin > 59) {
      throw new IllegalArgumentException("ERROR: start min MUST be within range.");
    }

    this.description = description;
    this.day = day;
    this.startHour = startHour;
    this.startMin = startMin;
    this.startTime = startHour * 100 + startMin;
  }

  /**
   * Compares this Event with the specified other event for order.
   * 
   * @param otherEvent - the object to be compared.
   * @return returns a negative integer, zero, or a positive integer as this event's start time is
   *         less than, equal to, or greater than the specified event's start time.
   * @throws NullPointerException - if the specified object is null
   */
  public int compareTo(Event otherEvent) {

    int comparison = 0;
    if (otherEvent == null) {
      throw new NullPointerException("ERROR: object CANNOT be null.");
    }
    if (this.startHour < otherEvent.startHour) {
      comparison = -1;
    }
    if (this.startHour > otherEvent.startHour) {
      comparison = 1;
    }
    if (this.startHour == otherEvent.startHour) {
      if (this.startMin < otherEvent.startMin) {
        comparison = -1;
      }
      if (this.startMin > otherEvent.startMin) {
        comparison = 1;
      } else {
        comparison = 0;
      }
    }

    return comparison;


  }

  /**
   * Gets a deep copy of this event.
   * 
   * @param none
   * @return a new event with the exact same description, day, start hour, start min, and completion
   *         status as this event.
   */
  public Event copy() {
    Event copy = new Event(this.description, this.day, this.startHour, this.startMin);

    if (this.isComplete()) {
      copy.markAsComplete();
    }

    return copy;

  }

  /**
   * Determines whether some other object is "equal to" this event.
   * 
   * @param o an object
   * @return true of this event is the same as the object argument, false otherwise.
   */
  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof Event)) {
      return false;
    }
    Event otherEvent = (Event) o;


    return this.day == otherEvent.day && this.description == otherEvent.description
        && this.startHour == otherEvent.startHour && this.startMin == otherEvent.startMin;
  }


  /**
   * Gets the day of this event
   * 
   * @param none
   * @return the day of this event
   */
  public int getDay() {
    return day;
  }

  /**
   * Accessor method for this event's description
   * 
   * @param none
   * @return the event's description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Accessor method for this event's start hour
   * 
   * @param none
   * @return the event's start hour
   */
  private int getStartHour() {
    return startHour;
  }

  /**
   * Accessor method for this event's start minute
   * 
   * @param none
   * @return the event's start minute
   */
  private int getStartMin() {
    return startMin;
  }

  /**
   * Returns a String representation of the start time of this event in the format HH:MM
   * 
   * @param none
   * @return a String representation of the start time of this event in the format HH:MM
   */
  // CITE: StringBuilder class - GeeksforGeeks
  // Learned how to use StringBuilder to make concatenating strings easier.
  public String getStartTimeAsString() {
    StringBuilder time = new StringBuilder("");


    String startHourString = String.format("%02d", startHour);
    String startMinString = String.format("%02d", startMin);

    time.append(startHourString);
    time.append(":");
    time.append(startMinString);

    String formattedTime = time.toString();
    return formattedTime;
  }

  /**
   * Reports whether this event has been completed
   * 
   * @param o an object
   * @return true if this event has been completed, false otherwise
   */
  // CITE: Kevin Ruby helped me debug this.
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Marks this event as having been completed
   * 
   * @param none
   * @return none
   */
  // CITE: Kevin Ruby helped me debug this.
  public void markAsComplete() {
    this.isComplete = true;
  }

  /**
   * Provided method: returns a String representation of this Event CITE: Write-up of p04 was the
   * source of this method.
   * 
   * @param none
   * @return a String representation of this event
   */
  @Override
  public String toString() {
    return this.description + " at " + this.startHour + ":" + this.startMin
        + (isComplete ? " completed on Day " + day : "");
  }


}
