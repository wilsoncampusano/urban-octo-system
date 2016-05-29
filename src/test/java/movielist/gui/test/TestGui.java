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

/**
 * Created by wilsoncampusano on 5/28/16.
 */
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
    }

    @Test
    public void testList(){
        control = EasyMock.controlFor(MovieListEditorView.class);
        mockView = (MovieListEditorView) control.getMock();
        mockView.setMovies(movies);
        control.setVoidCallable(1);
        control.activate();
        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        control.verify();
    }
}
