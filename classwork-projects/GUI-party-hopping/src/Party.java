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
// Online Sources: Lecture on how to use implicit parameters like "this" during Zybooks
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class models a Party for CS300 P05 Team Party Hopping, similarly to P02.
 */
public class Party implements Clickable {

  private processing.core.PImage image;

  private static TeamManagementSystem tms;

  private float x;

  private float y;

  public Party(int x, int y, processing.core.PImage image) {
    this.x = x;
    this.y = y;
    this.image = image;

  }

  /**
   * Initializes the class TeamManagementSystem reference to the provided value.
   * 
   * @param processing a reference to the TeamManagementSystem object representing this program's
   *                   application window
   * @return none
   */
  public static void setProcessing(TeamManagementSystem processing) {
    tms = processing;
  }

  /**
   * Accesses the x coordinate of the Party object
   * 
   * @param none
   * @return the x coordinate of the Party object as a float
   */
  public float getX() {
    return x;
  }

  /**
   * Accesses the y coordinate of the Party object
   * 
   * @param none
   * @return the y coordinate of the Party object as a float
   */
  public float getY() {
    return y;
  }

  /**
   * Draws the image associated with this party to its (x,y) location
   * 
   * @param none
   * @return none
   */
  public void draw() {
    if (image != null && tms != null) {
      tms.image(image, x - image.width / 2, y - image.height / 2);
    }
  }

  /**
   * This method is required by the Clickable interface, but does nothing
   * 
   * @param none
   * @return none
   */
  @Override
  public void mousePressed() {
    // “This method is intentionally left empty”

  }

  /**
   * Defines the behavior of this Party when the mouse is released.
   * 
   * @param none
   * @return none
   */
  @Override
  public void mouseReleased() {
    if (isMouseOver()) {
      Team activeTeam = tms.getActiveTeam();
      if (activeTeam != null) {
        activeTeam.sendToParty(this);
      }
    }
  }

  /**
   * Determines whether the mouse is over this party.
   * 
   * @param none
   * @return true if the mouse is anywhere over the image associated with this party, false
   *         otherwise.
   */
  @Override
  public boolean isMouseOver() {
    float picW = image.width;
    float picH = image.height;

    float left = x - picW / 2;
    float right = x + picW / 2;
    float top = y - picH / 2;
    float bottom = y + picH / 2;

    return tms.mouseX >= left && tms.mouseX <= right && tms.mouseY >= top && tms.mouseY <= bottom;



  }

}
