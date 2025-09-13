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
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
// - https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2025/chapter/7/section/1
// This helps with our understanding of how recursion works and how it should
// be used.
// - https://www.geeksforgeeks.org/recursion-in-java/
// This gives us more insight into base cases for recursion.
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * A utility class for generating playlists based on different strategies. This class provides
 * methods to generate playlists using simple, permutation-based, and optimal backtracking
 * approaches.
 */
public class PlaylistGenerator {
  /**
   * RECURSIVE method: Generates a simple playlist by adding the first available songs until the
   * maximum duration is reached.
   *
   * @param songs       the list of available songs
   * @param playlist    the current playlist being generated
   * @param maxDuration the maximum allowed duration for the playlist
   * @return a playlist containing songs that fit within the specified duration
   */
  public static Playlist simplePlaylist(ArrayList<Song> songs, Playlist playlist, int maxDuration) {

    if (songs.isEmpty()) { // Base case #1: If there's no more remaining songs to add
      return playlist;
    }



    if (playlist.getTotalDuration() >= maxDuration) {
      return playlist;
    }

    Song nextSong = songs.get(0);

    if (playlist.canAddSong(nextSong, maxDuration)) {
      playlist = playlist.addSong(nextSong);
    }



    return simplePlaylist(new ArrayList<>(songs.subList(1, songs.size())), playlist, maxDuration);

  }

  /**
   * RECURSIVE method: Generates all permutations of the given song list.
   *
   * @param songs  the list of songs to permute
   * @param index  the current index for generating permutations
   * @param result the list to store ALL the generated permutations
   */
  public static void generatePermutations(ArrayList<Song> songs, int index,
      ArrayList<ArrayList<Song>> result) {

    if (songs == null || songs.isEmpty()) {
      return;
    }

    if (index >= songs.size() - 1) {
      result.add(new ArrayList<>(songs));
      return;
    }



    for (int i = index; i < songs.size(); i++) {
      Song temp = songs.get(index);
      songs.set(index, songs.get(i));
      songs.set(i, temp);

      generatePermutations(songs, index + 1, result);

      temp = songs.get(index);
      songs.set(index, songs.get(i));
      songs.set(i, temp);

    }


  }

  /**
   * Generates the best possible playlist by evaluating all permutations of the song list. It
   * selects the permutation that maximizes the total playlist duration without exceeding the limit.
   *
   * @param songs       the list of available songs
   * @param maxDuration the maximum allowed duration for the playlist
   * @return the best possible playlist based on all song permutations
   */
  public static Playlist bestPermutationPlaylist(ArrayList<Song> songs, int maxDuration) {
    ArrayList<ArrayList<Song>> allPerm = new ArrayList<>();
    generatePermutations(new ArrayList<>(songs), 0, allPerm);
    Playlist best = new Playlist();
    for (ArrayList<Song> perm : allPerm) {
      Playlist nextBest = simplePlaylist(perm, new Playlist(), maxDuration);
      if (nextBest.getTotalDuration() > best.getTotalDuration()) {
        best = nextBest;
      }
    }
    return best;
  }


  /**
   * RECURSIVE method: Generates an optimal playlist using a backtracking approach to maximize the
   * total duration while staying within the maximum allowed duration.
   *
   * @param songs       the list of available songs
   * @param playlist    the current playlist being generated
   * @param maxDuration the maximum allowed duration for the playlist
   * @return the optimal playlist with the maximum possible duration based on the backtracking
   *         approach
   */
  public static Playlist optimalPlaylist(ArrayList<Song> songs, Playlist playlist,
      int maxDuration) {

    if (songs.isEmpty()) {
      return playlist;
    }

    Song currentSong = songs.get(0);
    ArrayList<Song> remainingSongs = new ArrayList<>(songs.subList(1, songs.size()));

    Playlist excludePlaylist = optimalPlaylist(remainingSongs, playlist, maxDuration);

    Playlist includePlaylist = playlist;

    if (playlist.canAddSong(currentSong, maxDuration)) {
      includePlaylist = optimalPlaylist(remainingSongs, playlist.addSong(currentSong), maxDuration);
    }

    if (includePlaylist.getTotalDuration() > excludePlaylist.getTotalDuration()) {
      return includePlaylist;
    } else {
      return excludePlaylist;
    }

  }


}
