package movielist.test;

import movielist.Category;
import movielist.Movie;
import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMovieListWithCategoryFilter {


  private Movie starWars;
  private Movie starTrek;
  private Movie stargate;
  private Movie theShining;
  private Movie carrie;
  private Movie fotr;
  private Movie redObtober;
  private Movie congo;
  private Movie princessBride;
  private MovieList movieList ;
  private MovieList fantasyList ;

  @Before
  public void setUp() throws Exception {
    movieList = new MovieList();

    starWars = new Movie("Star Wars", Category.SCIFI, 5);
    starTrek = new Movie("Star Trek", Category.SCIFI, 3);
    stargate = new Movie("Stargate", Category.SCIFI, -1);
    theShining = new Movie("The Shining", Category.HORROR, 2);
    carrie = new Movie("Carrie", Category.HORROR, 3);
    fotr = new Movie("The Fellowship of The Ring", Category.FANTASY, 5);
    redObtober = new Movie("Then Hunt For Red October", Category.THRILLER, 3);
    congo =  new Movie("Congo", Category.THRILLER, 3);
    princessBride = new Movie("The Princess Bride", Category.FANTASY, 5);

    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(stargate);
    movieList.add(theShining);
    movieList.add(carrie);
    movieList.add(fotr);
    movieList.add(redObtober);
    movieList.add(congo);
    movieList.add(princessBride);

    fantasyList = new MovieList();
    fantasyList.add(fotr);
    fantasyList.add(princessBride);


  }

  @Test
  public void testSubsets() {
    assertEquals("wrong FANTASY sublist", fantasyList, movieList.categorySublist(Category.FANTASY));;
  }

}
