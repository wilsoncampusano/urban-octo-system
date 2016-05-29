package movielist.test;

import movielist.MovieList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEmptyMovieList {

    private MovieList movieList;

    @Before
    public void setUp() throws Exception {
        movieList = new MovieList();
    }

    @Test
    public void testEmptyListSize() throws Exception {

        assertEquals("size of empty movie list should be 0.",0, movieList.size());

    }

}
