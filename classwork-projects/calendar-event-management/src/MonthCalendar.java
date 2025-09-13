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
// Persons: Anish Mantri helped me debug the addEvent() method.
// Online Sources: Write-up of p04 was the source of the getCompletedEventsAsAString() method
// and the toString() method.
//
///////////////////////////////////////////////////////////////////////////////
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The MonthCalendar class represents a monthly calendar that manages events for a specific year and
 * month. It keeps track of scheduled (uncompleted) events and completed events for the month.
 */
public class MonthCalendar {
  private ArrayList<Event> completedEvents; // List that stores all completed events for this month.

  private int daysCount = 0; // The number of days in the specified month.

  private ArrayList<Event>[] events; // An array of lists where each index represents a day of
  // the month, and each list stores the scheduled
  // (uncompleted) events for that day.

  private final YearMonth MONTH; // The year and month represented by this calendar in
  // the format YYYY-MM.

  /**
   * Constructs a MonthCalendar object for the specified year and month & initializes ALL instance
   * fields.
   * 
   * @param year  - The year in YYYY format.
   * @param month - The month represented as an integer (1 for January, 12 for December).
   * @return none
   * @throws DateTimeException - If the year or month values are invalid.
   */
  public MonthCalendar(int year, int month) throws DateTimeException {

    if (month > 12 || month < 1) {
      throw new DateTimeException("ERROR: Month is invalid!");
    }

    if (year < 1 || year > 2025) {
      throw new DateTimeException("ERROR: Year is invalid!");
    }
    this.MONTH = YearMonth.of(year, month);
    this.daysCount = MONTH.lengthOfMonth();
    this.completedEvents = new ArrayList<Event>();
    this.events = new ArrayList[daysCount];

    for (int i = 0; i < daysCount; i++) {
      events[i] = new ArrayList<Event>();
    }
  }

  /**
   * Adds an event to the specified day's list of events in the correct position, maintaining
   * ascending order based on the Event.compareTo(Event) method.
   * 
   * @param day         - The day of the month (1 - number of days in the month).
   * @param description - A brief text description of the event (not null and not blank).
   * @param startHour   - The hour the event starts (0-23, 24-hour format).
   * @param startMin    - The minute the event starts (**0-59**).
   * 
   * @return true if the event is successfully added, otherwise false if: - The day is out of range
   *         ( ≤ 0 or > number of days in the month ). - An IllegalArgumentException is thrown while
   *         creating the event (invalid inputs). - An equivalent event (same description, day, and
   *         start time) already exists in the calendar.
   */
  // CITE: Anish Mantri helped me debug this method.
  public boolean addEvent(int day, String description, int startHour, int startMin) {

    if (day <= 0 || day > MONTH.lengthOfMonth()) {
      return false;
    }
    try {
      Event eventToAdd = new Event(description, day, startHour, startMin);
      ArrayList<Event> dayEvents = events[day - 1];
      for (Event event : dayEvents) {
        if (event.equals(eventToAdd)) {
          return false;
        }
      }
      int position = dayEvents.size();
      for (int i = 0; i < dayEvents.size(); i++) {
        if (dayEvents.get(i).compareTo(eventToAdd) >= 0) {
          position = i;
          break;
        }
      }
      dayEvents.add(position, eventToAdd);
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR: Invalid inputs for creating the event");
      return false;
    }
    return true;
  }

  /**
   * Cancels (removes) an event from the specified day if a matching event exists.
   * 
   * @param - description must not be null or blank.
   * @param - day must be between 1 and the number of days in the month (inclusive).
   * @param - startHour must be between 0-23 (24-hour format).
   * @param - startMin must be between 0-59.
   * @return none
   * @throws IllegalArgumentException - If: - description is null or blank. - day is ≤ 0 or > number
   *                                  of days in the month. - startHour is out of range (0-23). -
   *                                  startMin is out of range (0-59).
   * @throws NoSuchElementException   - with a descriptive error message if no event with the
   *                                  matching description, startHour, and startMin is found on the
   *                                  given day.
   */
  public void cancelEvent(String description, int day, int startHour, int startMin) {

    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("ERROR: Description must not be null or blank.");
    }
    if (day <= 0 || day > MONTH.lengthOfMonth()) {
      throw new IllegalArgumentException("ERROR: Day must be within the range of the month.");
    }
    if (startHour < 0 || startHour > 23) {
      throw new IllegalArgumentException("ERROR: Start hour must be in between 0 and 23.");
    }
    if (startMin < 0 || startMin > 59) {
      throw new IllegalArgumentException("ERROR: Start minute must be in between 0 and 59.");
    }

    Event deleteEvent = new Event(description, day, startHour, startMin);

    ArrayList<Event> dayEvents = events[day - 1];

    for (int i = 0; i < dayEvents.size(); i++) {
      Event event = dayEvents.get(i);
      if (event.equals(deleteEvent)) {
        dayEvents.remove(i);
        return;
      }
    }
    throw new NoSuchElementException("ERROR: Event not found for the given day and time.");
  }

  /**
   * Clears all completed events stored in the completedEvents ArrayList.
   * 
   * @param none
   * @return none
   */
  public void clearAllCompletedEvents() {
    completedEvents.clear();
  }

  /**
   * Returns a deep copy of the completed events ArrayList of this MonthCalendar
   * 
   * @param none
   * @return a deep copy of the list of completed events scheduled for this MonthCalendar
   */
  public ArrayList<Event> getCompletedEvents() {

    ArrayList<Event> completedEventsCopy = new ArrayList<>();

    for (Event event : completedEvents) {
      completedEventsCopy.add(event.copy());
    }

    return completedEventsCopy;
  }


  /**
   * Provided method -- Returns a String representation of all completed events CITE: Write-up of
   * p04 was the source of this method.
   * 
   * @param none
   * @return a String containing all completed events with their completed days on separate lines,
   *         and an empty string if the list of completed events is empty.
   */
  public String getCompletedEventsAsString() {
    String retval = "";
    for (Event e : completedEvents) {
      retval += e.toString() + "\n";
    }
    return retval.strip();
  }

  /**
   * Gets the number of days in the current month calendar
   * 
   * @param none
   * @return the number of days in the current calendar
   */
  public int getDaysCount() {
    return MONTH.lengthOfMonth();
  }

  /**
   * Returns a deep copy of the array of events in this MonthCalendar.
   * 
   * @param none
   * @return a deep copy of the list of uncompleted events scheduled for this MonthCalendar
   */
  public ArrayList<Event>[] getEvents() {
    ArrayList<Event>[] eventsCopy = new ArrayList[events.length];

    for (int i = 0; i < events.length; i++) {
      eventsCopy[i] = new ArrayList<>();
      for (Event event : events[i]) {
        eventsCopy[i].add(event.copy());
      }
    }

    return eventsCopy;
  }

  /**
   * Returns the textual representation of the month in full style format, such as 'February'
   * 
   * @param none
   * @return the text value of the month in full text style length
   */
  public String getMonthName() {
    return MONTH.getMonth().name();
  }


  /**
   * Gets the month-of-year field as on int from 1 to 12 of this MonthCalendar.
   * 
   * @param none
   * @return the month as an int from 1 to 12.
   */
  public int getMonthNumber() {
    return MONTH.getMonthValue();
  }


  /**
   * Marks the event matching the specific input argument event e as complete if a match is found in
   * the list of events.
   * 
   * @param e - a specific event scheduled at a valid day of the month
   * @return none
   * @throws IllegalArgumentException - with a descriptive error message if e is null or if e is
   *                                  scheduled for a non-valid day of the month
   * @throws NoSuchElementException   - with a descriptive error message if no matching event with e
   *                                  is found in the list of events
   */
  public void markEventAsComplete(Event e) {
    if (e == null) {
      throw new IllegalArgumentException("ERROR: Event cannot be null.");
    }
    if (e.getDay() <= 0 || e.getDay() > MONTH.lengthOfMonth()) {
      throw new IllegalArgumentException("ERROR: Event cannot be on a non-valid day.");
    }

    ArrayList<Event> dayEvents = events[e.getDay() - 1];

    if (dayEvents == null || dayEvents.isEmpty()) {
      throw new NoSuchElementException("ERROR: There are no events scheduled for this day.");
    }

    for (int i = 0; i < dayEvents.size(); i++) {
      if (dayEvents.get(i).equals(e)) {
        Event completedEvent = dayEvents.remove(i);
        completedEvent.markAsComplete();
        completedEvents.add(0, completedEvent);
        return;
      }
    }

    throw new NoSuchElementException("ERROR: No matching event was found.");

  }


  /**
   * Provided method -- Returns a String representation of all uncompleted events.
   * 
   * CITE: Write-up of p04 was the source of this method.
   * 
   * @param none
   * @return aString representation of All the events stored in the events list, and an empty string
   *         if the list of events is empty.
   */
  @Override
  public String toString() {
    String retval = "";
    for (int i = 0; i < events.length; i++) {
      if (!events[i].isEmpty()) {
        retval += "Events for Day " + (i + 1) + ":\n";
        for (Event e : events[i]) {
          retval += e.toString() + "\n";
        }
      }
    }
    return retval.strip();
  }

}


