package movielist.test;

import movielist.Movie;
import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMovieListWithOneMovie {

    private Movie starWars;
    private MovieList movieList;

    @Before
    public void setUp() throws Exception {
        starWars = new Movie("star wars");
        movieList = new MovieList();
        movieList.add(starWars);
    }

    @Test
    public void testSizeAfterAddigOne() throws Exception {
        assertEquals("size of one item should be 1.", 1, movieList.size());
    }

}
