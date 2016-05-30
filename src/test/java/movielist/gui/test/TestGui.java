package movielist.gui.test;

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
    public void setUp(){
        starWars = new Movie("star wars");
        starTrek = new Movie("star trek");
        stargate = new Movie("stargate");

        movies = new Vector();
        movies.add(starWars);
        movies.add(starTrek);
        movies.add(stargate);

        movieList =  new MovieList();
        movieList.add(starWars);
        movieList.add(starTrek);
        movieList.add(stargate);

        control = EasyMock.controlFor(MovieListEditorView.class);
        mockView = (MovieListEditorView) control.getMock();
        mockView.setMovies(movies);
    }

    @Test
    public void testList(){
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
}
