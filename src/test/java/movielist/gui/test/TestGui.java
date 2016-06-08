package movielist.gui.test;

import movielist.DuplicateMovieException;
import movielist.Movie;
import movielist.MovieList;
import movielist.gui.MovieListEditor;
import movielist.gui.MovieListEditorView;
import org.easymock.EasyMock;
import org.easymock.MockControl;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;


public class TestGui {
  private MockControl control;
  private MovieListEditorView mockView;
  private Vector movies;
  private Movie starWars;
  private Movie starTrek;
  private Movie stargate;
  private MovieList movieList;

  @Before
  public void setUp() {
    starWars = new Movie("star wars");
    starTrek = new Movie("star trek");
    stargate = new Movie("stargate");

    movies = new Vector();
    movies.add(starWars);
    movies.add(starTrek);
    movies.add(stargate);

    movieList = new MovieList();

    try {
      movieList.add(starWars);
      movieList.add(starTrek);
      movieList.add(stargate);
    } catch (DuplicateMovieException e) {
      e.printStackTrace();
    }

    control = EasyMock.controlFor(MovieListEditorView.class);
    mockView = (MovieListEditorView) control.getMock();
    mockView.setEditor(null);
    control.setDefaultVoidCallable();
  }

  @Test
  public void testList() {
    mockView.setMovies(movies);
    control.setVoidCallable(1);
    control.activate();
    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    control.verify();
  }

  @Test
  public void testAdding() {
    String LOST_IN_SPACE = "Lost in space";
    Movie lostInSpace = new Movie(LOST_IN_SPACE);
    Vector moviesWithAddition = new Vector(movies);
    moviesWithAddition.add(lostInSpace);
    mockView.setMovies(movies);
    control.setVoidCallable(1);
    mockView.getNewName();
    control.setReturnValue(LOST_IN_SPACE, 1);

    mockView.setMovies(moviesWithAddition);
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.add();
    control.verify();
  }

  @Test
  public void testSelection() {
    mockView.setMovies(movies);
    control.setVoidCallable(1);

    mockView.setNewName("star trek");
    control.setVoidCallable(1);

    mockView.setNewName("star wars");
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.select(0);

    control.verify();
  }

  @Test
  public void testUpdating() {
    Vector newMovies = new Vector();
    newMovies.add(starWars);
    newMovies.add(new Movie("star trek I"));
    newMovies.add(stargate);

    mockView.setMovies(movies);
    control.setVoidCallable(1);

    mockView.setNewName("star trek");
    control.setVoidCallable(1);

    mockView.getNewName();
    control.setReturnValue("star trek I", 1);

    /*mockView.setMovies(newMovies);
    control.setVoidCallable(1);*/


    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    control.verify();

  }
}