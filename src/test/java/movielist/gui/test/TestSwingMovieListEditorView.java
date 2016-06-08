package movielist.gui.test;

import movielist.DuplicateMovieException;
import movielist.Movie;
import movielist.MovieList;
import movielist.gui.MovieListEditor;
import movielist.gui.MovieListEditorView;
import movielist.gui.SwingMovieListEditorView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.*;

import javax.swing.*;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class TestSwingMovieListEditorView {
  public static final String WINDOW_TITLE = "movie list";
  private JFrameOperator mainWindow;
  private Vector movies;
  private Movie starWars;
  private Movie starTrek;
  private Movie stargate;
  private MovieList movieList;


  @Before
  public void setUp() {
    SwingMovieListEditorView.start();
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

  }

  @After
  public void tearDown() {
    mainWindow.dispose();
  }


  @Test
  public void testListContents() {
    mainWindow = new JFrameOperator(WINDOW_TITLE);
    MovieListEditor editor = new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());
    JListOperator movieList = new JListOperator(mainWindow);
    ListModel listModel = movieList.getModel();

    assertEquals("movie list is the wrong size", movies.size(), listModel.getSize());
    for (int i = 0; i < movies.size(); i++) {
      assertEquals("movie list contains bad movie", movies.get(i), listModel.getElementAt(i));
    }
  }


  @Test
  public void testAdding() {
    String LOST_IN_SPACE = "Lost In Space";
    Movie lostInSpace = new Movie(LOST_IN_SPACE);
    movies.add(lostInSpace);

    mainWindow = new JFrameOperator(WINDOW_TITLE);
    MovieListEditor editor = new MovieListEditor(movieList, (MovieListEditorView) mainWindow.getWindow());

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    newMovieField.enterText(LOST_IN_SPACE);

    JButtonOperator addButton = new JButtonOperator(mainWindow, "Add");
    addButton.doClick();

    JListOperator movieList = new JListOperator(mainWindow);
    ListModel model = movieList.getModel();

    assertEquals("movie list is the wrong size", movies.size(), model.getSize());

    for (int i = 0; i < movies.size(); i++) {
      assertEquals("movie list containts bad movie at index " + i, movies.get(i), model.getElementAt(i));
    }
  }

  @Test
  public void testSelecting() {
    mainWindow = new JFrameOperator(WINDOW_TITLE);
    MovieListEditor editor = new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    movieList.clickOnItem(1, 1);

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);

    assertEquals("wrong text from selection", "star trek", newMovieField.getText());

  }


  @Test
  public void testUpdating() {
    mainWindow = new JFrameOperator(WINDOW_TITLE);
    MovieListEditor editor = new MovieListEditor(movieList,(SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    movieList.clickOnItem(1,1);

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    newMovieField.enterText("Star Trek I");

    JButtonOperator updateButton = new JButtonOperator(mainWindow, "Update");
    updateButton.doClick();

    movieList.clickOnItem(0,1);
    movieList.clickOnItem(1,1);
    assertEquals("movie should have been renamed", "Star Trek I", newMovieField.getText());

  }

  @Test
  public void testDuplicateCausingAdd() {
    mainWindow = new JFrameOperator(WINDOW_TITLE);
    MovieListEditor editor = new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    newMovieField.enterText(starWars.getName());

    JButtonOperator addButton = new JButtonOperator(mainWindow, "Add");
    addButton.pushNoBlock();

    JDialogOperator messageDialog = new JDialogOperator("duplicate movie");
    JLabelOperator message = new JLabelOperator(messageDialog);

    assertEquals("wrong message text", "duplicate movie", messageDialog.getTitle());
    JButtonOperator okButton = new JButtonOperator(messageDialog, "OK");
    okButton.doClick();

    JListOperator movieList = new JListOperator(mainWindow);
    ListModel listModel = movieList.getModel();
    assertEquals("movie list is the wrong size", movies.size(), listModel.getSize());

    for(int i = 0; i < movies.size(); i++){
      assertEquals("movie list contains bad movie at index "+i,movies.get(i), listModel.getElementAt(i));
    }

  }

  private void esperarInterfaz() {
    try {
      Thread.sleep(9000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}