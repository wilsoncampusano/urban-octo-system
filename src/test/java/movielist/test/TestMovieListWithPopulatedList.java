package movielist.test;

import movielist.DuplicateMovieException;
import movielist.Movie;
import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
