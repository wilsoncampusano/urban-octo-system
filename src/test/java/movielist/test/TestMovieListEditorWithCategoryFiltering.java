package movielist.test;

import movielist.Category;
import movielist.Movie;
import movielist.MovieList;
import movielist.UnratedException;
import movielist.gui.MovieListEditor;
import movielist.gui.MovieListEditorView;
import org.easymock.EasyMock;
import org.easymock.MockControl;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static movielist.Category.*;

public class TestMovieListEditorWithCategoryFiltering {

  private MockControl control;
  private MovieListEditorView mockView;


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
  private MovieList scifiList;
  private MovieList horrorList;
  private MovieList thrillerList;

  private Vector movies;
  private Vector fantasyMovies;
  private Vector horrorMovies;
  private Vector thrillerMovies;
  private Vector scifiMovies;

  @Before
  public void setUp() throws Exception{
    movieList = new MovieList();

    starWars = new Movie("Star Wars", SCIFI, 5);
    starTrek = new Movie("Star Trek", SCIFI, 3);
    stargate = new Movie("Stargate", SCIFI, -1);
    theShining = new Movie("The Shining", HORROR, 2);
    carrie = new Movie("Carrie", HORROR, 3);
    fotr = new Movie("The Fellowship of The Ring", FANTASY, 5);
    redObtober = new Movie("Then Hunt For Red October", THRILLER, 3);
    congo =  new Movie("Congo", THRILLER, 3);
    princessBride = new Movie("The Princess Bride", FANTASY, 5);

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

    scifiList = new MovieList();
    scifiList.add(starWars);
    scifiList.add(starTrek);
    scifiList.add(stargate);

    horrorList = new MovieList();
    horrorList.add(theShining);
    horrorList.add(carrie);

    thrillerList = new MovieList();
    thrillerList.add(redObtober);
    thrillerList.add(congo);

    movies = new Vector(movieList.getMovies());
    scifiMovies = new Vector(scifiList.getMovies());
    thrillerMovies = new Vector(thrillerList.getMovies());
    horrorMovies =  new Vector(horrorList.getMovies());
    fantasyMovies = new Vector(fantasyList.getMovies());

    control = EasyMock.controlFor(MovieListEditorView.class);
    mockView = (MovieListEditorView) control.getMock();
    mockView.setEditor(null);
    control.setDefaultVoidCallable();
  }

  @Test
  public void testCategoryFiltering() {
    mockView.setMovies(movies);
    control.setVoidCallable(1);

    mockView.setMovies(scifiMovies);
    control.setVoidCallable(1);

    mockView.setMovies(movies);
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.filterOnCategory(Category.SCIFI);
    editor.filterOnCategory(Category.ALL);

    control.verify();
  }

  @Test
  public void testSelecting() throws UnratedException {
    mockView.setMovies(movies);
    control.setVoidCallable(1);
    mockView.setMovies(fantasyMovies);
    control.setVoidCallable(1);

    mockView.setNameField(fotr.getName());
    control.setVoidCallable(1);
    mockView.setRatingField(fotr.getRating());
    control.setVoidCallable(1);
    mockView.setCategoryField(fotr.getCategory());
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.filterOnCategory(Category.FANTASY);
    editor.select(0);

    control.verify();

  }

}
