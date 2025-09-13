//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Playlist Generator Java
// Course: CS 300 Spring 2025
//
// Author: Olivia Ruby
// Email: oruby@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Anish Mantri
// Partner Email: agmantri@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X__We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources:
// - https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2025/chapter/7/section/1
// Referenced zybooks for more information on recursion.
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * A class to test the functionality of the {@code Playlist} and {@code PlaylistGenerator} classes.
 * It includes tests for simple, permutation-based, and optimal playlist generation methods.
 */
public class PlaylistTester {
  /**
   * Tests the base cases for the simple playlist generator. Ensures that an empty song list and a
   * playlist already at max duration are handled correctly.
   *
   * @return true if all base cases pass, false otherwise
   */
  public static boolean simpleGeneratorBaseCaseTest() {
    // Base Case 1: Empty song list
    {
      ArrayList<Song> emptySongs = new ArrayList<>();
      Playlist playlist = new Playlist();

      playlist = playlist.addSong(new Song("Olivia", "Rock", 50));


      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(emptySongs, playlist, 100);
      if (!newPlaylist.equals(playlist)) {
        return false;
      }
      if (newPlaylist.getTotalDuration() != 50) {
        return false;
      }

    }

    // Base Case 2: Existing playlist already meets max duration

    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("Ruby", "Jazz", 110);
      songs.add(song1);

      Playlist fullPlaylist = new Playlist();
      Song song2 = new Song("Olivia", "Jazz", 100);
      fullPlaylist = fullPlaylist.addSong(song2);


      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, fullPlaylist, 100);

      int maxDuration = 100;

      if (!newPlaylist.equals(fullPlaylist)) {
        return false;
      }
      if (newPlaylist.getTotalDuration() != maxDuration || newPlaylist.size() != 1) {
        return false;
      }

      for (Song song : newPlaylist.getSongs()) {
        if (song.equals(song1)) {
          return false;
        }
      }

    }

    return true;
  }

  /**
   * Tests the simple playlist generator with one song adding to a non-empty playlist. Ensures that
   * a song fitting within the duration limit is added, and one exceeding the limit is not added.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean simpleGeneratorOneStepTest() {
    // Case 1: One song that fits
    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("Ruby", "Jazz", 50);
      songs.add(song1);

      Playlist playlist = new Playlist();
      Song song2 = new Song("Olivia", "Jazz", 40);
      playlist = playlist.addSong(song2);

      int maxDuration = 100;

      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, playlist, maxDuration);

      Playlist expectedPlaylist = new Playlist();
      expectedPlaylist = expectedPlaylist.addSong(song2);
      expectedPlaylist = expectedPlaylist.addSong(song1);


      if (!newPlaylist.equals(expectedPlaylist) || newPlaylist.getTotalDuration() != 90
          || newPlaylist.size() != 2) {
        return false;
      }

    }

    // Case 2: One song that does not fit

    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("Ruby", "Jazz", 50);
      songs.add(song1);

      Playlist playlist = new Playlist();
      Song song2 = new Song("Olivia", "Jazz", 90);
      playlist = playlist.addSong(song2);

      int maxDuration = 100;

      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, playlist, maxDuration);


      Playlist expectedPlaylist = new Playlist();
      expectedPlaylist = expectedPlaylist.addSong(song2);

      if (!newPlaylist.equals(expectedPlaylist) || newPlaylist.size() != 1
          || newPlaylist.getTotalDuration() != 90) {
        return false;
      }

    }

    return true;
  }

  /**
   * Tests the recursive functionality of the simple playlist generator. Ensures that multiple songs
   * are added without exceeding the duration limit.
   *
   * @return true if the recursive addition works correctly, false otherwise
   */
  public static boolean simpleGeneratorRecursiveTest() {

    // Test Case 1: Can add multiple songs, ensuring correct recursive addition
    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("A", "B", 20);
      Song song2 = new Song("C", "D", 30);
      songs.add(song1);
      songs.add(song2);

      Playlist playlist = new Playlist();
      Song song3 = new Song("O", "L", 10);
      playlist = playlist.addSong(song3);

      int maxDuration = 100;

      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, playlist, maxDuration);

      Playlist expectedPlaylist = new Playlist();
      expectedPlaylist = expectedPlaylist.addSong(song3);
      expectedPlaylist = expectedPlaylist.addSong(song1);
      expectedPlaylist = expectedPlaylist.addSong(song2);


      if (!newPlaylist.equals(expectedPlaylist) || newPlaylist.getTotalDuration() != 60
          || newPlaylist.size() != 3) {
        return false;
      }

      ArrayList<Song> resultSongs = newPlaylist.getSongs();


      if (!resultSongs.get(0).equals(song3) || !resultSongs.get(1).equals(song1)
          || !resultSongs.get(2).equals(song2)) {
        return false;
      }


    }

    // Test Case 2: Duration limit gets exceeded
    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("C", "D", 30);
      Song song2 = new Song("A", "B", 80);

      songs.add(song1);
      songs.add(song2);

      Playlist playlist = new Playlist();
      Song song3 = new Song("O", "L", 10);
      playlist = playlist.addSong(song3);

      int maxDuration = 50;

      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, playlist, maxDuration);

      Playlist expectedPlaylist = new Playlist();
      expectedPlaylist = expectedPlaylist.addSong(song3);
      expectedPlaylist = expectedPlaylist.addSong(song1);


      if (!newPlaylist.equals(expectedPlaylist) || newPlaylist.getTotalDuration() != 40
          || newPlaylist.size() != 2) {
        return false;
      }


      ArrayList<Song> resultSongs = newPlaylist.getSongs();

      if (!resultSongs.get(0).equals(song3) || !resultSongs.get(1).equals(song1)) {
        return false;
      }



    }

    // Test Case 3: Adding multiple songs to an empty playlist
    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("A", "B", 20);
      Song song2 = new Song("C", "D", 30);
      songs.add(song1);
      songs.add(song2);

      Playlist playlist = new Playlist();

      int maxDuration = 60;


      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, playlist, maxDuration);


      Playlist expectedPlaylist = new Playlist();
      expectedPlaylist = expectedPlaylist.addSong(song1);
      expectedPlaylist = expectedPlaylist.addSong(song2);



      if (!newPlaylist.equals(expectedPlaylist) || newPlaylist.getTotalDuration() != 50
          || newPlaylist.size() != 2) {
        return false;
      }

      ArrayList<Song> resultSongs = newPlaylist.getSongs();


      if (!resultSongs.get(0).equals(song1) || !resultSongs.get(1).equals(song2)) {
        return false;
      }


    }

    // Test Case 4: Max duration is 0
    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("A", "B", 20);
      Song song2 = new Song("C", "D", 30);
      songs.add(song1);
      songs.add(song2);

      Playlist playlist = new Playlist();

      int maxDuration = 0;


      Playlist newPlaylist = PlaylistGenerator.simplePlaylist(songs, playlist, maxDuration);

      Playlist expectedPlaylist = new Playlist();


      if (!newPlaylist.equals(expectedPlaylist) || newPlaylist.size() != 0) {
        return false;
      }

    }


    return true;
  }

  /**
   * Tests the permutation generation method for song lists. Verifies that all permutations are
   * generated correctly. You may consider checking the size of results and the size of each
   * permutation in results.
   *
   * @return true if this tester verifies a correct functionality, false otherwise
   */
  public static boolean generatePermutationsTest() {
    // Case 1: Empty song list
    {
      ArrayList<Song> songs = new ArrayList<>();
      ArrayList<ArrayList<Song>> actual = new ArrayList<>();
      ArrayList<ArrayList<Song>> expected = new ArrayList<>();

      PlaylistGenerator.generatePermutations(songs, 0, actual);

      if (!actual.equals(expected)) {
        return false;
      }

    }



    // Case 2: Normal case (not an empty song list)
    {
      ArrayList<Song> songs = new ArrayList<>();
      Song song1 = new Song("A", "B", 10);
      Song song2 = new Song("C", "D", 10);
      songs.add(song1);
      songs.add(song2);

      ArrayList<ArrayList<Song>> actual = new ArrayList<>();
      PlaylistGenerator.generatePermutations(songs, 0, actual);


      ArrayList<ArrayList<Song>> expected = new ArrayList<>();
      ArrayList<Song> perm1 = new ArrayList<>();
      perm1.add(song1);
      perm1.add(song2);
      ArrayList<Song> perm2 = new ArrayList<>();
      perm2.add(song2);
      perm2.add(song1);

      expected.add(perm1);
      expected.add(perm2);


      if (!actual.equals(expected)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests the permutation-based playlist generation method. Ensures that multiple songs (at least
   * three) are permuted and the best playlist is selected without exceeding the maximum duration.
   *
   * @return true if the permutation-based playlist is generated correctly, false otherwise
   */
  public static boolean bestPermutationPlaylistRecursiveTest() {
    // Base case: Empty song list
    ArrayList<Song> emptySongs = new ArrayList<>();
    Playlist emptyResult = PlaylistGenerator.bestPermutationPlaylist(emptySongs, 10);
    if (emptyResult.getTotalDuration() != 0) { // Should return an empty playlist
      return false;
    }
    ArrayList<Song> songs = new ArrayList<>();
    songs.add(new Song("Song A", "Rock", 3));
    songs.add(new Song("Song B", "Jazz", 4));
    songs.add(new Song("Song C", "Classical", 5));
    int maxDuration = 10;
    Playlist result = PlaylistGenerator.bestPermutationPlaylist(songs, maxDuration);
    int duration = result.getTotalDuration();
    if (duration <= 10) {
      return true;
    }
    return false;
    // TODO Case: Multiple songs, ensuring permutations are checked

  }

  /**
   * Tests the optimal playlist generation with base cases. Ensures correct handling of empty song
   * lists and playlists already at max duration.
   *
   * @return true if base cases are handled correctly, false otherwise
   */
  public static boolean optimalPlaylistBaseCaseTest() {
    ArrayList<Song> emptyList = new ArrayList<>();
    Playlist empty = new Playlist();
    int maxDur = 10;
    Playlist res1 = PlaylistGenerator.optimalPlaylist(emptyList, empty, maxDur);
    if (!res1.equals(empty)) {
      System.out.println("ERROR: optimal playlist should be empty");
      return false;
    }

    Song longSong = new Song("Strange fruit", "Jazz", 10);
    Playlist full = empty.addSong(longSong);
    ArrayList<Song> newList = new ArrayList<>();
    newList.add(new Song("Birds of a feather", "Pop", 5));
    Playlist newList2 = PlaylistGenerator.optimalPlaylist(newList, full, 10);
    if (!newList2.equals(full)) {
      System.out.println("ERROR: optimalPlaylist method is wrong.");
      return false;
    }
    return true;
  }

  /**
   * Tests the optimal playlist generation method with one-step cases adding to a nonempty playlist.
   * Ensures that a song fitting within the maximum duration is added, and a song exceeding the
   * limit is not added.
   *
   * @return true if the optimal playlist handles one-step cases correctly, false otherwise
   */
  public static boolean optimalPlaylistOneStepTest() {
    Playlist emptyPlaylist = new Playlist();
    int maxDuration = 140;
    Playlist resultEmpty =
        PlaylistGenerator.simplePlaylist(new ArrayList<>(), emptyPlaylist, maxDuration);
    if (resultEmpty.getTotalDuration() != 0) { // Should remain empty
      return false;
    }

    Playlist newList = new Playlist();
    newList = newList.addSong(new Song("Stairway to heaven", "Rock", 80));
    ArrayList<Song> newSong1 = new ArrayList<>();
    newSong1.add(new Song("Smells Like Teen Spirit", "Rock", 40));
    Playlist playList1 = PlaylistGenerator.optimalPlaylist(newSong1, newList, maxDuration);
    if (playList1.getTotalDuration() != 120) {
      System.out.println("ERROR: optimalPlaylist failed to add a song in one step");
      return false;
    }
    ArrayList<Song> newSong2 = new ArrayList<>();
    newSong2.add(new Song("Sure Thing", "Pop", 70));
    Playlist playList2 = PlaylistGenerator.optimalPlaylist(newSong2, newList, maxDuration);
    if (playList2.getTotalDuration() != 80) {
      System.out.println("ERROR: optimalPlaylist incorrectly exceeded time limit with one step");
      return false;
    }

    return true;
  }

  /**
   * Tests the optimal playlist generation method with multiple songs. Ensures that recursive
   * backtracking selects the best playlist without exceeding the maximum duration.
   *
   * @return true if the optimal playlist is generated correctly, false otherwise
   */
  public static boolean optimalPlaylistRecursiveTest() {
    Playlist emptyResult = PlaylistGenerator.bestPermutationPlaylist(new ArrayList<>(), 10);
    if (emptyResult.getTotalDuration() != 0) { // Should return an empty playlist
      return false;
    }
    int maxDuration = 190;
    ArrayList<Song> songs = new ArrayList<>();
    songs.add(new Song("Drivers License", "Pop", 100));
    songs.add(new Song("Party in the USA", "Pop", 70));
    songs.add(new Song("Work it", "Pop", 30));
    songs.add(new Song("Bye Bye Bye", "Pop", 10));
    Playlist playList1 = new Playlist();
    Playlist best = PlaylistGenerator.optimalPlaylist(songs, playList1, maxDuration);
    if (best.getTotalDuration() != 180) {
      System.out.println("ERROR: 180 was the optimalPlaylist time with the "
          + "max duration of 190 and the optimalPlaylist method found " + best.getTotalDuration());
      return false;
    }

    return true;


  }

  /**
   * The main method runs all test cases for the playlist generator.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "simpleGeneratorBaseCaseTest: " + (simpleGeneratorBaseCaseTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "simpleGeneratorOneStepTest: " + (simpleGeneratorOneStepTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "simpleGeneratorRecursiveTest: " + (simpleGeneratorRecursiveTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out
        .println("generatePermutationsTest: " + (generatePermutationsTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("bestPermutationPlaylistRecursiveTest: "
        + (bestPermutationPlaylistRecursiveTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "optimalPlaylistBaseCaseTest: " + (optimalPlaylistBaseCaseTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "optimalPlaylistOneStepTest: " + (optimalPlaylistOneStepTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "optimalPlaylistRecursiveTest: " + (optimalPlaylistRecursiveTest() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
  }


}
