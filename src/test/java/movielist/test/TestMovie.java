package movielist.test;

import movielist.Movie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMovie {

    @Test
    public void testMovieName() {
        Movie starWars = new Movie("star Wars");
        assertEquals("star wars should have a name", "star Wars", starWars.getName());

    }
}
