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
// Online Sources:
// - https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class models a Team Lead agent for the CS300 P05 Team Party Hopping project. Every Team can
 * have at most one Lead, and clicking on that Team's Lead selects ALL members of the Team at the
 * same time.
 */
public class Lead extends Agent {
  /**
   * Constructs a new Lead at the given x,y coordinates.
   * 
   * @param x - x coordinate of this lead
   * @param y - y coordinate of this lead
   * @return none
   */
  public Lead(int x, int y) {
    super(x, y);
  }

  /**
   * Annotates a Lead's agent representation by drawing an inverted black triangle over the circle
   * in the color corresponding to this Lead's selection/team status.
   * 
   * @param none
   * @return none
   */
  // CITE: PApplet javadocs to understand how to implement the triangle method.
  @Override
  public void draw() {
    super.draw();

    processing.fill(0);

    processing.triangle((getX() - diameter / 3), (getY() - diameter / 5), (getX() + diameter / 3),
        (getY() - diameter / 5), getX(), (getY() + diameter / 3));

  }

  /**
   * Defines the specific behavior of this team lead when the mouse is released.
   * 
   * @param none
   * @return none
   */
  @Override
  public void mouseReleased() {
    super.mouseReleased();

    if (this.isActive() && team != null) {

      team.selectAll();
    }
  }
}


