package movielist.test;

import movielist.Movie;
import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMovieListWithTwoMovies {
  private Movie starWars;
  private Movie starTrek;
  private MovieList movieList;

  @Before
  public void setUp() throws Exception {
    starWars = new Movie("star wars");
    starTrek = new Movie("star trek");
    movieList = new MovieList();
    movieList.add(starTrek);
    movieList.add(starWars);
  }

  @Test
  public void testSizeAfterAddingTwo() throws Exception {
    assertEquals("size of two item list should be 2.", 2, movieList.size());
  }

  @Test
  public void testContents() throws Exception {

    assertTrue("list should contains startrek", movieList.contains(starTrek));
    assertTrue("list should contains starwars", movieList.contains(starWars));

  }
}