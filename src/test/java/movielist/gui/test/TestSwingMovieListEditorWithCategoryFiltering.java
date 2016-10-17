package movielist.gui.test;

import movielist.Category;
import movielist.Movie;
import movielist.MovieList;
import movielist.gui.MovieListEditor;
import movielist.gui.MovieListEditorView;
import movielist.gui.SwingMovieListEditorView;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JListOperator;

import javax.swing.*;
import java.util.Vector;

import static movielist.Category.*;
import static movielist.Category.FANTASY;
import static movielist.Category.THRILLER;
import static org.junit.Assert.assertEquals;

public class TestSwingMovieListEditorWithCategoryFiltering {

  public static final String WINDOW_TITLE = "movie list";
  private JFrameOperator mainWindow;

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

  private void setUpWindow() {
    mainWindow = new JFrameOperator(WINDOW_TITLE);
    MovieListEditor editor = new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());
  }


  @Before
  public void setUp() throws Exception{

    SwingMovieListEditorView.start();

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

    setUpWindow();
  }

  @Test
  public void testCategoryFiltering() {
    JListOperator movieList = new JListOperator(mainWindow);
    JComboBoxOperator categoryCombo = new JComboBoxOperator(mainWindow, Category.ALL.toString());

    categoryCombo.setSelectedItem(Category.FANTASY);
    ListModel fantasyListModel  = movieList.getModel();


    assertEquals("fantasy list is the wrong size", fantasyMovies.size(), fantasyListModel.getSize());

    for(int i =0; i < fantasyMovies.size(); i++){
      assertEquals("fantasy list contains bad movie", fantasyMovies.get(i), fantasyListModel.getElementAt(i));
    }

    categoryCombo.setSelectedItem(Category.THRILLER);
    ListModel thrillerListModel = movieList.getModel();

    assertEquals("Thriller list is the wrong size", thrillerMovies.size(), thrillerListModel.getSize());

    for(int i = 0; i < thrillerMovies.size(); i++){
      assertEquals("thriller list contains bad movie",thrillerMovies.get(i), thrillerListModel.getElementAt(i));
    }

    categoryCombo.setSelectedItem(Category.ALL);

    ListModel allListModel = movieList.getModel();

    assertEquals("movie list is the wrong size", movies.size(), allListModel.getSize());

    for(int i = 0 ; i< movies.size(); i++){
      assertEquals("movie list contains bad movie at index "+i, movies.get(i), allListModel.getElementAt(i) );
    }
  }
}
