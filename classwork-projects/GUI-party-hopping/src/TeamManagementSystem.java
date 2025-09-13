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
// https://stackoverflow.com/questions/10431981/remove-elements-from-collection-while-iterating
// This helped us with logic on how to remove the teams when they were not being used.
// We searched up how to remove an instance of something without effecting the array list
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;

/**
 * This class implements GUI program.
 */
public class TeamManagementSystem extends PApplet {

  // General data fields for this program
  private Random randGen; // A random number generator for creating Team colors
  private ArrayList<Clickable> objects; // Storage for the interactive components of the program
  private ArrayList<Team> teams; // Storage for all Teams with at least one member
  private int bgColor; // The background color of the application window

  // Selection-related fields:
  private boolean isSelecting; // Indicates whether the user is currently creating a selection box
  private int selectionStartX; // The x-coordinate where the user began creating a selection box
  private int selectionStartY; // The y-coordinate where the user began creating a selection box

  /**
   * Implements the team management system.
   * 
   * @param args
   * 
   */
  public static void main(String[] args) {
    PApplet.main("TeamManagementSystem"); // PROVIDED
  }

  /**
   * Calls PApplet's size() method to set the width and height of screen.
   * 
   * 
   */
  @Override
  public void settings() {
    size(800, 600);
    // #1 call PApplet's size() method giving it 800 as the width and 600 as the height
  }

  /**
   * Adds setProcessingcalls and initializes the party objects, randGen, arrayLists. The party
   * objects are added to the screen.
   * 
   */
  @Override
  public void setup() {
    // #2 add setProcessing calls (see writeup)
    Agent.setProcessing(this);
    Party.setProcessing(this);

    // #3 set the imageMode so the x,y coordinates indicate the center of an object
    imageMode(CENTER);
    // #4 initialize randGen and the ArrayLists
    randGen = new Random();
    teams = new ArrayList<Team>();
    objects = new ArrayList<Clickable>();

    // #5 initialize the bgColor with R = 81, G = 125, B = 168
    bgColor = color(81, 125, 168);


    // #7 add the party objects (see writeup for suggested locations, but feel free to change)
    Party cup = new Party(200, 200, loadImage("images (1)" + File.separator + "cup.png"));
    Party dice = new Party(650, 200, loadImage("images (1)" + File.separator + "dice.png"));
    Party ball = new Party(450, 450, loadImage("images (1)" + File.separator + "ball.png"));


    objects.add(new Agent(400, 300));


    // #8 add one agent at the center of the screen
    objects.add(cup);
    objects.add(dice);
    objects.add(ball);
  }

  /**
   * Draws the background, selection box, and objects and also clears the empty teams and prints to
   * the screen the team names.
   * 
   */
  @Override
  public void draw() {
    // #6 draw the background using the bgColor value
    background(bgColor);
    // #14 draw the selection box if the user is currently selecting (see helper method below)
    if (isSelecting) {
      drawSelectionBox();
    }
    // #9 draw all Clickables in the objects list to the application window in the order they
    // appear
    for (Clickable obj : objects) {
      if (obj instanceof Agent) {
        ((Agent) obj).move();
      }
      obj.draw();
    }
    // #11 use your helper method below to clear all empty teams from the teams list

    clearEmptyTeams();
    // #12 if there are any teams left, do the following:

    if (teams.size() > 0) {
      textSize(16);
      float yCoord = 20;

      for (int i = 0; i < teams.size(); i++) {
        if (teams.get(i).isActive()) {
          fill(0, 255, 0);

        } else {
          fill(0, 255, 0);
        }
        text("Team " + teams.get(i).getTeamID(), 10, yCoord);
        yCoord += 20;
      }
    }
    // (1) begin with a y-coordinate of 20 and a text size of 16
    // (2) set PApplet's fill to (0,255,0) if the team is active, or just (255) if it is not
    // fill(0, 255, 0);
    // (3) print "Team " and the team's ID letter at x=10 and the current y-coordinate
    // (4) move the y-coordinate down by 20 and repeat if there are any other teams
  }


  /**
   * This removes all teams with no members from the teams list.
   * 
   * 
   * 
   */
  // CITE: This is where we used the logic from the stack over flow
  // on how to remove something from a list without it changing
  public void clearEmptyTeams() {
    for (int i = teams.size() - 1; i >= 0; i--) {
      if (teams.get(i).getTeamSize() == 0) {
        teams.remove(i);
      }
    }

    // #10 implement this method - remove all teams with NO members from the teams list
  }

  /**
   * This method draws the selection box by filling a rectangle.
   * 
   * 
   * 
   */
  public void drawSelectionBox() {
    // #13 implement this method, see below for details:

    int x1;
    int y1;
    if (mouseX < selectionStartX) {
      x1 = mouseX;
    } else {
      x1 = selectionStartX;
    }

    if (mouseY < selectionStartY) {
      y1 = mouseY;
    } else {
      y1 = selectionStartY;
    }

    int boxW = mouseX - selectionStartX;
    int boxH = mouseY - selectionStartY;

    // The user's selection box is defined by the selectionStartX/selectionStartY coordinates and
    // the current position of the mouse. However, PApplet's rectangle drawing method requires the
    // coordinates of the upper left corner of the rectangle(which may not correspond to either of
    // those points) and the width/height of the rectangle to draw.



    // To draw this rectangle correctly:
    // (1) set PApplet's fill() to R=135, G=185, B=201
    fill(135, 185, 201);
    // call PApplet's rect() method using the upper left corner coordinates and the width/height
    rect(x1, y1, boxW, boxH);
  }

  /**
   * 
   * This method calls the object's mousePressed method if the mouse is pressed on the object.
   * 
   * 
   */
  @Override
  public void mousePressed() {
    // TODO #15 if the mouse is over any of the Clickable objects, call only that object's
    // mousePressed method and end the method

    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break;
      } else {
        isSelecting = true;
        selectionStartX = mouseX;
        selectionStartY = mouseY;
      }
    }


    // #16 if the mouse is NOT over any of the Clickable objects, set isSelecting to true and
    // initialize the selectionStartX and selectionStartY values to the current mouse location
  }

  /**
   * 
   * This method creates a team out of the selected agents and calls mouseReleased() on all
   * Clickable objects.
   * 
   * 
   */
  @Override
  public void mouseReleased() {
    if (isSelecting) {
      Team selectedTeam = detectTeam();


      if (selectedTeam == null) {
        ArrayList<Agent> selectedAgents = getAllSelectedAgents();
        if (!selectedAgents.isEmpty()) {
          createTeam(selectedAgents);
          Team newTeam = teams.get(teams.size() - 1);
          newTeam.selectAll();
        }
      } else {
        selectedTeam.selectAll();
      }
      isSelecting = false;
    }
    clearEmptyTeams();

    for (Clickable obj : objects) {
      obj.mouseReleased();
    }
    // (1) determine whether all selected agents belong to a single team (see helper method below)
    // (2) if they do not, create a team out of the selected agents (see helper method below)
    // (3) either way, set isSelecting back to false

    // #18 clear any empty teams (see helper method above)
    // #19 call mouseReleased() on all Clickable objects
  }

  /**
   * 
   * This method finds all agents within the selection box and if they are all on the same team, it
   * returs the reference to that team.
   * 
   * 
   */
  public Team detectTeam() {
    // #20 find all agents within the selection box (this method will only be called when the
    // user was creating a selection box -- see helper method below)
    ArrayList<Agent> selectedAgents = getAllSelectedAgents();

    if (selectedAgents.isEmpty()) {
      return null;
    }

    Team firstTeam = selectedAgents.get(0).getTeam();
    if (firstTeam == null) {
      return null;
    }
    for (Agent agent : selectedAgents) {
      if (agent.getTeam() != firstTeam) {
        return null;
      }
    }
    // #21 if no agents were selected, return null
    // #22 if all selected agents are on the same (non-null) team, return a reference to that
    // team, otherwise return null
    return firstTeam;
  }

  /**
   * This method adds the agents within the bounds of the selection box to an arraylist.
   * 
   * @return an ArrayList of the selected agents.
   * 
   */
  public ArrayList<Agent> getAllSelectedAgents() {
    float maxX = Math.max(selectionStartX, mouseX);
    float minX = Math.min(selectionStartX, mouseX);
    float maxY = Math.max(selectionStartY, mouseY);
    float minY = Math.min(selectionStartY, mouseY);
    ArrayList<Agent> agents = new ArrayList<>();

    for (Clickable obj : objects) {
      if (obj instanceof Agent) {
        Agent agent = (Agent) obj;
        if (agent.getX() > minX && agent.getX() < maxX && agent.getY() > minY
            && agent.getY() < maxY) {
          agents.add(agent);
        }
      }

    }


    // #23 find the bounds of the selection box described by the selectionStart coordinates and
    // the current mouse location

    // #24 add to this list all agents whose center (x,y) coordinate is within the bounds of
    // the selection box:
    return agents;
  }

  /**
   * This method creates a team of the selected agents and adds it to the team list. It also
   * generates a random color for this team.
   * 
   * @param selected - ArrayList of the selected agents
   */
  public void createTeam(ArrayList<Agent> selected) {
    if (selected.isEmpty() || selected == null) {
      return;
    }

    int teamColor = color(randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256));
    Team newTeam = new Team(teamColor, selected);

    teams.add(newTeam);
    // #25 if no agents were selected, end the method
    // #26 generate a random color for this team with R, G, and B values between 0 and 255
    // #27 attempt to create a new team using the selected agents and this color
    // #28 if the team is created successfully, add it to the teams list; otherwise do nothing
  }

  /**
   * 
   * This method either adds an agent, adds a team, removes an agent, or lines the team members up
   * based on which key is pressed.
   */
  @Override
  public void keyPressed() {
    // #29 if the key is a '.', add a normal agent at the mouse's current location
    if (key == '.') {
      objects.add(new Agent(mouseX, mouseY));
    }
    // #30 if the key is a ',', add a team lead at the mouse's current location
    else if (key == ',') {
      objects.add(new Lead(mouseX, mouseY));
    }
    // #31 if the key is an 'r' and the mouse is over an agent, remove that agent
    else if (key == 'r') {
      for (int i = objects.size(); i >= 0; i--) {
        Clickable object = objects.get(i);
        if (object instanceof Agent && object.isMouseOver()) {
          Agent agent = (Agent) object;
          Team team = agent.getTeam();
          if (team != null) {
            team.removeMember(agent);
          }
          objects.remove(i);
          break;
        }
      }

    } else if (Character.isLowerCase(key)) {
      char teamID = Character.toUpperCase(key);
      for (Team team : teams) {
        if (team.getTeamID() == teamID) {
          team.lineUp();
          break;
        }
      }
    }
    // #32 if the key is the lower-case version of any of the existing Team IDs, have that
    // Team's members line up
  }

  /**
   * This method accesses teh first active team in the teams list.
   * 
   * 
   * @returns the first team in the teams list with all members active or null if no team has all
   *          members active
   */
  public Team getActiveTeam() {
    for (Team team : teams) {
      if (team.isActive()) {
        return team;
      }
    }
    return null;
    // #33 find the first team in the teams list with all members active and return it
    // #34 if no team has all members active, return null
  }

}
