package movielist.gui.test;

import movielist.Category;
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


public class TestMovieListEditor {
  private MockControl control;
  private MovieListEditorView mockView;
  private Vector movies;
  private Movie starWars;
  private Movie starTrek;
  private Movie stargate;
  private Movie theShining;
  private MovieList movieList;

  @Before
  public void setUp() {
    starWars = new Movie("star wars", Category.SCIFI, 5);
    starTrek = new Movie("Star Trek", Category.SCIFI, 3);
    stargate = new Movie("stargate", Category.SCIFI, 1);
    theShining = new Movie("The Shining", Category.HORROR, 2);

    movies = new Vector();
    movies.add(starWars);
    movies.add(starTrek);
    movies.add(stargate);
    movies.add(theShining);

    movieList = new MovieList();

    try {
      allAllMovies();
    } catch (DuplicateMovieException e) {
      e.printStackTrace();
    }

    control = EasyMock.controlFor(MovieListEditorView.class);
    mockView = (MovieListEditorView) control.getMock();
    mockView.setEditor(null);
    control.setDefaultVoidCallable();
  }

  private void allAllMovies() throws DuplicateMovieException {
    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(stargate);
    movieList.add(theShining);
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
    mockView.getNameField();
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

    mockView.setNameField(starTrek.getName());
    control.setVoidCallable(1);
    mockView.setRatingField(6);
    control.setVoidCallable(1);
    mockView.setCategoryField(Category.SCIFI);
    control.setVoidCallable(1);

    mockView.setNameField(starWars.getName());
    control.setVoidCallable(1);
    mockView.setRatingField(4);
    control.setVoidCallable(1);
    mockView.setCategoryField(Category.SCIFI);
    control.setVoidCallable(1);

    mockView.setNameField(stargate.getName());
    control.setVoidCallable(1);
    mockView.setRatingField(2);
    control.setVoidCallable(1);
    mockView.setCategoryField(Category.SCIFI);
    control.setVoidCallable(1);

    mockView.setNameField(theShining.getName());
    control.setVoidCallable(1);
    mockView.setRatingField(3);
    control.setVoidCallable(1);
    mockView.setCategoryField(Category.HORROR);
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(0);
    editor.select(1);
    editor.select(2);
    editor.select(3);

    control.verify();
  }

  @Test
  public void testUpdating() {
    Vector newMovies = new Vector();
    newMovies.add(starWars);
    newMovies.add(new Movie("Star Trek I", 5));
    newMovies.add(stargate);

    mockView.setNameField("Star Trek");
    control.setVoidCallable();
    mockView.setRatingField(4);
    control.setVoidCallable(1);

    mockView.getNameField();
    control.setReturnValue("Star Trek I", 1);
    mockView.getRatingField();
    control.setReturnValue(6,1);

    mockView.setMovies(movies);
    control.setVoidCallable(2);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    control.verify();

  }

  @Test
  public void testDuplicateCausingAdd() {
    mockView.setMovies(movies);
    control.setVoidCallable(1);

    mockView.getNameField();
    control.setReturnValue("star wars", 1);

    mockView.duplicateException("star wars");
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.add();

    control.verify();
  }

  @Test
  public void testDuplicateCausingUpdate() {
    mockView.setMovies(movies);
    control.setVoidCallable(1);

    mockView.setNameField("Star Trek");
    control.setVoidCallable(1);

    mockView.getNameField();
    control.setReturnValue("star wars", 1);

    mockView.duplicateException("star wars");
    control.setVoidCallable(1);

    mockView.setRatingField(4);
    control.setVoidCallable();

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    control.verify();

  }

  @Test
  public void testUpdatingWithSameName() {
    Vector newMovies = new Vector();
    newMovies.add(starWars);
    newMovies.add(new Movie("Star Trek", 5));
    newMovies.add(stargate);

    mockView.setMovies(movies);
    control.setVoidCallable(1);

    mockView.setNameField("Star Trek");
    control.setVoidCallable(1);
    mockView.setRatingField(4);
    control.setVoidCallable();

    mockView.getNameField();
    control.setReturnValue("Star Trek", 1);
    mockView.getRatingField();
    control.setReturnValue(6, 1);

    mockView.setMovies(newMovies);
    control.setVoidCallable(1);

    control.activate();

    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.select(1);
    editor.update();

    control.verify();
  }
}