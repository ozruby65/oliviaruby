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
// - https://stackoverflow.com/questions/73263561/filter-objects-in-array-with-field-values
// https://stackoverflow.com/questions/7526817/use-of-instanceof-in-java
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * This class models a Team for the CS300 P05 Team Party Hopping project
 */
public class Team extends Object {
  private int color; // The color in which this team's members are
  // drawn in the application window when not active

  private static char idGenerator = 'A';
  // A shared variable containing the identifier character to be used
  // by the next Team that is successfully created; initialized to 'A'.

  private ArrayList<Agent> members; // A list of the current members of this Team

  private final char TEAM_ID; // This Team's unique identifier, set at construction

  /**
   * Attempts to create a new team from the provided list of agents
   * 
   * @param color  -the color for this team's agents
   * @param agents -a list of the agents to be added to this team
   * @return true if this winner equals the input provided object and false otherwise
   * @throws IllegalArgumentException if the agents list is empty
   * @throws IllegalStateException    if the agents list contains more than one Lead
   */
  // CITE: StackOverflow to learn how to filter objects
  // CITE: How to use instance of to get the specific agent you want
  public Team(int color, ArrayList<Agent> agents) {

    if (agents.isEmpty()) {
      throw new IllegalArgumentException("ERROR: List must have at least one agent.");
    }

    int leadCount = 0;

    for (Agent a : agents) {
      if (a instanceof Lead) {
        leadCount++;
        if (leadCount > 1) {
          throw new IllegalStateException("ERROR: List cannot contain more than one lead.");
        }
      }
    }

    this.color = color;
    this.TEAM_ID = idGenerator++;
    this.members = new ArrayList<>(agents);

    for (Agent a : agents) {
      addMember(a);
    }

  }

  /**
   * Accessor method for the color value of this team
   * 
   * @param none
   * @return the color value of this team as an int
   */
  public int getColor() {
    return color;
  }

  /**
   * Accessor method for the team's ID character
   * 
   * @param none
   * @return the team ID value
   */
  public char getTeamID() {
    return TEAM_ID;
  }

  /**
   * Accessor method for the total number of agents on this team
   * 
   * @param none
   * @return the size of this team
   */
  public int getTeamSize() {
    return members.size();
  }

  /**
   * Reports whether this team currently has a Lead member
   * 
   * @param none
   * @return true if this team currently has a Lead member, false otherwise
   */
  public boolean hasLead() {
    for (Agent a : members) {
      if (a instanceof Lead) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adds the given agent to this team's list. If the agent is already present in this team's
   * members list, this method does nothing.
   * 
   * @param a - the agent to add to the list
   * @return none
   * @throws IllegalStateException if the agent is a Lead that isn't present in the list already
   */
  public void addMember(Agent a) throws IllegalStateException {
    if (members.contains(a)) {
      return;
    } else {
      if (a instanceof Lead && hasLead()) {
        throw new IllegalStateException("ERROR: Cannot have more than two leads in a team");
      } else {
        members.add(a);
        a.setTeam(this);
      }
    }

  }

  /**
   * Removes the provided agent from this team
   * 
   * @param a - the agent to remove
   * @return true if the agent was removed from the team successfully, false otherwise
   */
  public boolean removeMember(Agent a) {
    return members.remove(a);
  }

  /**
   * Accessor to determine whether a given agent is a member of this team.
   * 
   * @param a - the agent that may be a member of this team
   * @return true if this agent is in the members list, false otherwise
   */
  public boolean contains(Agent a) {
    return members.contains(a);
  }

  /**
   * Activates ALL members of this team
   * 
   * @param none
   * @return none
   */
  public void selectAll() {
    for (Agent a : members) {
      if (!a.isActive()) {
        a.toggleActive();
      }
    }
  }

  /**
   * Checks whether ALL members of a team have been selected
   * 
   * @param none
   * @return true if ALL members of this team are active, false otherwise
   */
  public boolean isActive() {
    for (Agent a : members) {
      if (a.isActive()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Finds the "center" x-coordinate of this team, shown as being halfway between the leftmost and
   * rightmost agents on this team
   * 
   * @param none
   * @return the center x-coordinate of this team
   */
  public float getCenterX() {
    if (members.isEmpty()) {
      return 0;
    }
    float leftX = members.get(0).getX();
    float rightX = members.get(0).getX();

    for (Agent agent : members) {
      if (agent.getX() < leftX) {
        leftX = agent.getX();
      }
      if (agent.getX() > rightX) {
        rightX = agent.getX();
      }
    }
    return (leftX + rightX) / 2;
  }

  /**
   * Finds the "center" y-coordinate of this team, defined as being halfway between the topmost and
   * bottommost agents on this team.
   * 
   * @param none
   * @return the center y-coordinate of this team
   */
  public float getCenterY() {
    if (members.isEmpty()) {
      return 0;
    }

    float leftY = members.get(0).getY();
    float rightY = members.get(0).getY();

    for (Agent agent : members) {
      if (agent.getY() < leftY) {
        leftY = agent.getY();
      }
      if (agent.getY() > rightY) {
        rightY = agent.getY();
      }
    }
    return (leftY + rightY) / 2;
  }

  /**
   * Updates the destination of all team members so that the current team formation will be
   * maintained, but after movement is completed the team will be centered over the given Party
   * 
   * @param p - the party to move the team to
   * @return none
   */
  public void sendToParty(Party p) {
    if (members.isEmpty() || p == null) {
      return;
    }

    float centerX = getCenterX();
    float centerY = getCenterY();

    float partyX = p.getX();
    float partyY = p.getY();

    for (Agent a : members) {
      if (a != null) {
        float offsetX = a.getX() - centerX;
        float offsetY = a.getY() - centerY;
        a.setDestination(partyX + offsetX, partyY + offsetY);
      }
    }
  }

  /**
   * Updates the destination of all team members so that the team formation becomes a line centered
   * at getCenterX/getCenterY.
   * 
   * @param none
   * @return none
   */
  public void lineUp() {
    int teamSize = members.size();

    if (teamSize == 0) {
      return;
    }

    int diameter = Agent.diameter;

    int width = ((diameter + 3) * (teamSize - 1));

    float startX = getCenterX() - (width / 2);

    for (int i = 0; i < members.size(); i++) {
      float destX = startX + (i * (diameter + 3));

      members.get(i).setDestination(destX, getCenterY());
    }
  }


}
