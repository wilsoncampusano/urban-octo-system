package movielist.test;

import movielist.DuplicateMovieException;
import movielist.Movie;
import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestMovieListWithPopulatedList {
  private MovieList movieList;
  private Movie starWars;
  private Movie starTrek;
  private Movie stargate;

  @Before
  public void setUp() {
    starWars = new Movie("Star Wars");
    starTrek = new Movie("Star Trek");
    stargate = new Movie("Stargate");

    movieList = new MovieList();

    try {
      movieList.add(starWars);
      movieList.add(starTrek);
      movieList.add(stargate);
    } catch (DuplicateMovieException e) {
      e.printStackTrace();
    }

  }

  @Test(expected = DuplicateMovieException.class)
  public void testAddingDuplicate() throws DuplicateMovieException {
    movieList.add(starWars);
    movieList.add(starTrek);
    Movie duplicate = new Movie(starWars.getName());

    try {
      movieList.add(duplicate);
    } catch (DuplicateMovieException e) {
      assertEquals("shouldn't change the list size", 2, movieList.size());
    }

  }

  @Test
  public void testRenaming() {
    final String newName =  "Star Trek I";
    try {
      movieList.rename(starTrek, newName);
    } catch (DuplicateMovieException e) {

    }

    assertEquals("name should be different", newName, starTrek.getName());
  }

  @Test
  public void testRenamingDuplicate() {
    try{
      movieList.rename(starTrek, "Star Wars");
      fail("renaming to a duplicate throw DuplicateMovieException");
    }catch (DuplicateMovieException e){
      assertEquals("renaming should'nt change the lis size", 3, movieList.size());

      assertEquals("failed rename should't change the name", "Star Trek", starTrek.getName());
    }
  }
}