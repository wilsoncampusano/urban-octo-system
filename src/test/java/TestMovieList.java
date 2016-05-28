import movielist.Movie;
import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMovieList {

    private Movie starWars;
    private Movie starTrek;
    private MovieList movieList;

    @Before
    public void setUp() throws Exception {
        starWars = new Movie();
        starTrek = new Movie();
        movieList = new MovieList();
    }

    @Test
    public void testEmptyListSize() throws Exception {

        assertEquals("size of empty movie list should be 0.",0, movieList.size());

    }

    @Test
    public void testSizeAfterAddigOne() throws Exception {

        movieList.add(starWars);
        assertEquals("size of one item should be 1.", 1, movieList.size());

    }

    @Test
    public void testSizeAfterAddingTwo() throws Exception {
        movieList.add(starTrek);
        movieList.add(starWars);

        assertEquals("size of two item list should be 2.", 2, movieList.size());
    }


}
