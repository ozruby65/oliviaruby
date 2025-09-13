//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Team Party Hopping Java
// Course: CS 300 Spring 2025
//
// Author: Olivia Ruby & Anish Mantri
// Email: oruby@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Anish Mantri
// Partner Email: agmantri@wisc.edu
// Partner Lecturer's Name: Legault Hobbes
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: Referenced the PO5 Specification for how to implement clickable
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This interface models Clickable objects in a graphic application
 */
public interface Clickable {
  /**
   * Draws image or object to screen.
   */
  void draw();

  /**
   * Called when the mouse is over the object.
   * 
   */
  boolean isMouseOver();

  /**
   * Called when the mouse is pressed over the object.
   * 
   */
  void mousePressed();

  /**
   * Called when the mouse is released.
   */
  void mouseReleased();

}
