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
// - https://stackoverflow.com/questions/17183587/convert-integer-color-value-to-rgb
// This helped us learn how to create colors in Java.
// - https://processing.github.io/processing-javadocs/core/processing/core/PApplet.
// html#dist-float-float-float-float-
// This helped us figure out how to use the PApplet dist method.
///////////////////////////////////////////////////////////////////////////////
/**
 * This class models an Agent for the CS300 P05 Team Party Hopping project
 */
public class Agent extends Object implements Clickable {

  private float destX; // This will store the x-coordinate that an Agent is actively moving to.

  private float destY; // This will store the y-coordinate that an Agent is actively moving to.

  protected static int diameter = 20; // The standard diameter of all Agent representations,
  // protected for access in the child class.

  private boolean isActive; // Indicator of whether this Agent is currently active

  protected boolean isDragging; // Indicator of whether this Agent is currently being dragged

  private int oldMouseX; // Storage for the previous x-position of the mouse,
  // for use in dragging Agents around the window

  private int oldMouseY; // Storage for the previous y-position of the mouse,
  // for use in dragging Agents around the window

  private float originalX; // This will store the original x-coordinate of an agent when the mouse
  // is pressed, for determining whether the agent was dragged at all when the mouse is released.

  private float originalY; // This will store the original y-coordinate of an agent when the mouse
  // is pressed, for determining whether the agent was dragged at all when the mouse is released.

  protected static processing.core.PApplet processing; // A reference to the application window,
  // for creating visual representations

  protected Team team; // A reference to this Agent's assigned team,
  // or null if this Agent does not yet have a team

  private float xPos; // The x-position of the center of this Agent

  private float yPos; // The y-position of the center of this Agent


  /**
   * Constructs a new agent at the given x, y coordinates.
   * 
   * @param x - x coordinate of this agent
   * @param y - y coordinate of this agent
   * @return nothing
   */
  public Agent(int x, int y) {
    this.xPos = x;
    this.yPos = y;
    this.destX = -1;
    this.destY = -1;
    this.isActive = false;
    this.isDragging = false;
    this.oldMouseX = 0;
    this.oldMouseY = 0;
    this.originalX = -1;
    this.originalY = -1;
    this.team = null;

  }


  /**
   * Accesses the value of the class variable diameter.
   * 
   * @param none
   * @return the value of the class variable diameter
   */
  public static int diameter() {
    return diameter;
  }


  /**
   * Helper method containing the logic to update this agent's position correctly while being
   * dragged.
   * 
   * @param none
   * @return none
   */
  protected void drag() {
    if (isDragging) {
      float dx = processing.mouseX - oldMouseX;
      float dy = processing.mouseY - oldMouseY;

      xPos += dx;
      yPos += dy;

      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }


  }

  /**
   * Renders this agent to the application window after making any required updates to its position
   * 
   * @param none
   * @return none
   */
  // CITE: PApplet java docs
  @Override
  public void draw() {
    if (isDragging) {
      drag();
    }
    if (isMoving()) {
      move();
    }
    processing.fill(getColor());
    processing.circle(xPos, yPos, diameter);
  }

  /**
   * Helper method to determine the color to use for drawing this agent
   * 
   * @param none
   * @return an integer representing the color that this agent should be drawn in
   */
  // CITE: StackOverflow: Color class
  protected int getColor() {
    if (isActive) {
      return processing.color(0, 255, 0);
    } else if (team != null) {
      return team.getColor();
    } else {
      return processing.color(255, 255, 0);
    }

  }


  /**
   * Accessor method for the Team reference of this Agent
   * 
   * @param none
   * @return a direct reference to the team that this agent is a member of or null if this agent is
   *         not a member of any team
   */
  public Team getTeam() {
    if (team != null) {
      if (team.contains(this)) {
        return this.team;
      }
    }

    return null;
  }


  /**
   * Accessor method for the current y-coordinate of this Agent
   * 
   * @param none
   * @return the current x-coordinate of this agent
   */
  public float getX() {
    return xPos;
  }

  /**
   * Accessor method for the current y-coordinate of this Agent
   * 
   * @param none
   * @return the current y-coordinate of this agent
   */
  public float getY() {
    return yPos;
  }

  /**
   * Reports whether this Agent has been selected
   * 
   * @param o an object
   * @return true if this agent is active, false otherwise
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Determines whether the mouse is over this agent.
   * 
   * @param none
   * @return true if the mouse is over this agent, false otherwise
   */
  // CITE: PApplet javadocs to figure out how to use the dist method.
  @Override
  public boolean isMouseOver() {
    float deltaX = processing.mouseX - xPos;
    float deltaY = processing.mouseY - yPos;



    float radius = diameter / 2.0f;

    float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

    return distance <= radius;

  }

  /**
   * Helper method, reports whether this Agent is currently moving.
   * 
   * @param none
   * @return true if this agent is moving, false otherwise
   */
  protected boolean isMoving() {
    if (destX != -1 && destY != -1) {
      return true;
    }
    return false;
  }

  /**
   * Defines the behavior of this agent when it is clicked on. This method is called only when the
   * mouse is over the agent.
   * 
   * @param none
   * @return none
   */
  @Override
  public void mousePressed() {
    if (!isMoving()) {
      startDragging();

      originalX = xPos;
      originalY = yPos;
    }

  }

  /**
   * Defines the behavior of this agent when the mouse is released.
   * 
   * @param none
   * @return none
   */
  @Override
  public void mouseReleased() {
    stopDragging();

    if (xPos == originalX && yPos == originalY) {
      toggleActive();
    }
    originalX = -1;
    originalY = -1;
  }

  /**
   * Helper method to move an agent 3 units toward its destination, if one is set; if the agent is
   * within 3 units of its destination, moves the agent directly to its destination and resets the
   * destination coordinates to (-1,-1).
   * 
   * @param none
   * @return none
   */
  protected void move() {
    if (destX == -1 && destY == -1) {
      return;
    }


    float deltaX = destX - xPos;
    float deltaY = destY - yPos;

    float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

    if (distance <= 3) {
      xPos = destX;
      yPos = destY;
      destX = -1;
      destY = -1;
    } else {
      xPos += 3 * (deltaX / distance);
      yPos += 3 * (deltaY / distance);
    }

  }

  /**
   * Sets the destination coordinates of this agent to be the provided values and deactivates the
   * agent.
   * 
   * @param x - x coordinate of the destination
   * @param y - y coordiante of the destination
   * @return none
   */
  public void setDestination(float x, float y) {
    this.destX = x;
    this.destY = y;
    this.isActive = false;

  }

  /**
   * Initializes the class PApplet reference to the provided value.
   * 
   * @param processing - the class PApplet reference
   * @return none
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Agent.processing = processing;
  }

  /**
   * Sets the team of this agent to be the provided value. If this agent was already a member of a
   * team, this method should ALSO remove them from their previous team.
   * 
   * @param t - Team to add agent to
   * @return none
   */
  public void setTeam(Team t) {
    if (team != null) {
      team.removeMember(this);
    }
    team = t;
    if (team != null) {
      team.addMember(this);
    }
  }

  /**
   * Helper method, sets this agent to be dragging and initializes the oldMouseX and oldMouseY
   * fields
   * 
   * @param none
   * @return none
   */
  // CITE: PApplet javadocs to learn how to find the x and y coordinates of the mouse.
  protected void startDragging() {
    isDragging = true;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }

  /**
   * Helper method, sets this agent to no longer be dragging.
   * 
   * @param none
   * @return none
   */
  protected void stopDragging() {
    isDragging = false;
  }

  /**
   * Switches the active status of this agent to its opposite - if false, makes it true; if true,
   * makes it false.
   * 
   * @param none
   * @return none
   */
  public void toggleActive() {
    isActive = !isActive;

  }

}
