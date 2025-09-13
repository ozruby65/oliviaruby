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
// Online Sources: https://www.geeksforgeeks.org/java-sqrt-method-examples/
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * A short tester class for verifying some of the Agent and Team behaviors in P05.
 */
public class TeamTester {

  /**
   * Verifies that an Agent’s initial position is set correctly upon creation.
   * 
   * This test should: - Create two agents at different (x,y) coordinates - Verify that their getX()
   * and getY() methods return the expected values - Verify that their initial positions match the
   * coordinates provided to their constructors
   * 
   * @return true if both agents are created with correct coordinates; false otherwise
   */
  public static boolean testAgentInitialPosition() {
    Agent agent1 = new Agent(200, 400);
    Agent agent2 = new Agent(300, 200);

    if (agent1.getX() != 200 && agent1.getY() != 400) {
      return false;
    }
    if (agent2.getX() != 300 && agent2.getY() != 200) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that an Agent moves correctly when given a destination.
   * 
   * This test should: - Create an agent at a known position - Set a destination that is at least 10
   * pixels away in both x and y directions - Call the move() method once - Verify that the agent
   * has moved closer to the destination but has not reached it - Verify that the movement follows
   * the expected trajectory
   * 
   * @return true if agent movement behavior is correct; false otherwise
   */
  public static boolean testAgentMovement() {
    Agent agent = new Agent(0, 0);


    agent.setDestination(100, 100);
    agent.move();
    // CITE: This is where we used the geeks for geeks link on how to use the sqrt function
    float initialDist = (float) Math.sqrt(100 * 100 + 100 * 100);

    float stepSize = 3.0f;



    float moveX = (100 / initialDist) * stepSize;
    float moveY = (100 / initialDist) * stepSize;

    boolean correctX = Math.abs(agent.getX() - moveX) < 0.1;
    boolean correctY = Math.abs(agent.getY() - moveY) < 0.1;
    boolean stillMove = agent.isMoving();

    if (!correctX || !correctY || !stillMove) {
      return false;
    }

    return true;

  }

  /**
   * Verifies that an Agent without a destination remains stationary.
   * 
   * This test should: - Create an agent at a specific position - Record its initial position - Call
   * the move() method - Verify that the agent’s position has not changed
   * 
   * @return true if agent remains stationary when no destination is set; false otherwise
   */
  public static boolean testAgentStationary() {
    Agent agent = new Agent(100, 100);

    float startingX = agent.getX();
    float startingY = agent.getY();

    agent.move();

    if (agent.getX() != startingX || agent.getY() != startingY) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that creating a Team with multiple Leads throws an IllegalArgumentException.
   * 
   * This test should: - Create an ArrayList of Agents that includes multiple Lead instances -
   * Attempt to create a Team with this ArrayList - Verify that an IllegalStateException is thrown
   * 
   * @return true if the correct exception is thrown; false otherwise
   */
  public static boolean testMultipleLeadsException() {
    ArrayList<Agent> team1 = new ArrayList<>();
    team1.add(new Lead(100, 100));
    team1.add(new Lead(200, 200));
    try {
      new Team(0, team1);
      System.out.println("ERROR: Team creation did not throw exception with multiple leads!");
      return false;
    } catch (IllegalStateException e) {
      return true;
    } catch (Exception e) {
      System.out.println("ERROR: Wrong exception type: " + e.getClass().getName());
      return false;
    }

  }

  /**
   * Verifies behavior around empty teams.
   * 
   * This test should: - Create an empty ArrayList - Attempt to create a Team with this ArrayList -
   * Verify that an IllegalArgumentException is thrown - Add at least one Agent to the ArrayList and
   * create a valid team - Remove all agents from the team - Verify that the Team's size is now zero
   * 
   * @return
   */
  public static boolean testEmptyTeam() {
    ArrayList<Agent> agents = new ArrayList<>();
    try {
      Team team1 = new Team(0, agents);
      return false;
    } catch (IllegalArgumentException e) {

    }
    Agent agent = new Agent(100, 100);
    agents.add(agent);
    Team team2 = new Team(0, agents);
    team2.removeMember(agent);
    if (team2.getTeamSize() != 0) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that a Team can be created successfully with exactly one Lead.
   * 
   * This test should: - Create an ArrayList with one Lead and multiple regular Agents - Create a
   * Team with this ArrayList - Verify that the Team is created successfully - Verify that Team size
   * matches the ArrayList size - Verify that all Agents are properly added to the Team - Verify
   * that the hasLead method correctly reports that this team has a Lead
   * 
   * @return true if Team creation succeeds with correct composition; false otherwise
   */
  public static boolean testValidTeamCreation() {
    Team b;
    ArrayList<Agent> team1 = new ArrayList<>();
    team1.add(new Lead(100, 100));
    team1.add(new Agent(200, 200));
    try {
      b = new Team(0, team1);
    } catch (Exception e) {
      System.out.println("ERROR: throws an exception when there is one lead");
      return false;
    }
    if (b.getTeamSize() != 2) {
      System.out.println("ERROR: Team size should be 2");
      return false;
    }
    if (!b.hasLead()) {
      System.out.println("ERROR: hasLead method does not work when there is one added");
      return false;
    }
    for (Agent a : team1) {
      if (!b.contains(a)) {
        System.out.println("ERROR: Team does not contain all added agents");
        return false;
      }
    }
    return true;
  }

  /**
   * Verifies that a new Agent can be added to an existing Team.
   * 
   * This test should: - Create a valid Team with one Lead and at least one Agent - Create a new
   * Agent - Add the new Agent to the Team using addAgent() - Verify that the Team size has
   * increased - Verify that the new Agent is now a member of the Team
   * 
   * @return true if Agent is successfully added to Team; false otherwise
   */
  public static boolean testAddAgentToTeam() {
    ArrayList<Agent> team1 = new ArrayList<>();
    team1.add(new Lead(100, 100));
    team1.add(new Agent(10, 10));
    Team a;
    try {
      a = new Team(0, team1);
    } catch (Exception e) {
      System.out.println("ERROR: while creating the team " + e.getMessage());
      return false;
    }
    int size = a.getTeamSize();
    Agent b = new Agent(20, 20);
    try {
      a.addMember(b);
    } catch (Exception e) {
      System.out.println("ERROR: while adding a new agent: " + e.getMessage());
      return false;
    }
    if (a.getTeamSize() != size + 1) {
      System.out.println("ERROR: Team size did not increase after adding agent.");
      return false;
    }

    if (!a.contains(b)) {
      System.out.println("ERROR: Team does not contain the added agent");
      return false;
    }
    return true;
  }

  /**
   * Verifies that Team’s center coordinates are calculated correctly.
   * 
   * This test should: - Create a Team with at least three Agents at known positions - Calculate the
   * expected center coordinates manually - Compare the expected values with getCenterX() and
   * getCenterY() results -Verify that adding a new Agent updates the center coordinates correctly
   * 
   * @return true if center coordinates are calculated correctly; false otherwise
   */
  public static boolean testTeamCenter() {
    Team b;
    ArrayList<Agent> team1 = new ArrayList<>();
    team1.add(new Agent(100, 100));
    team1.add(new Agent(200, 200));
    team1.add(new Agent(300, 300));

    try {
      b = new Team(0, team1);
    } catch (Exception e) {
      System.out.println("ERROR: Should not fail with 3 normal Agents: " + e.getMessage());
      return false;
    }
    int expectedX = (100 + 200 + 300) / 3;
    int expectedY = (100 + 200 + 300) / 3;

    if (b.getCenterX() != expectedX && b.getCenterY() != expectedY) {
      System.out.println("ERROR: Team center mismatch");
      return false;
    }
    Agent a = new Agent(400, 400);
    b.addMember(a);
    int newExpectedX = (400 + 300 + 200 + 100) / 4;
    int newExpectedY = (400 + 300 + 200 + 100) / 4;

    if (b.getCenterX() != newExpectedX && b.getCenterY() != newExpectedY) {
      System.out.println("ERROR: Team center mismatch after adding agent.");
      return false;
    }
    return true;
  }

  /**
   * Runs all tests and displays results
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------------------");
    System.out
        .println("testAgentInitialPosition: " + (testAgentInitialPosition() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentMovement: " + (testAgentMovement() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentStationary: " + (testAgentStationary() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "testMultipleLeadsException: " + (testMultipleLeadsException() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEmptyTeam: " + (testEmptyTeam() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testValidTeamCreation: " + (testValidTeamCreation() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAddAgentToTeam: " + (testAddAgentToTeam() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testTeamCenter: " + (testTeamCenter() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
  }

}
