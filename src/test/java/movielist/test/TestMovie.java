package movielist.test;

import movielist.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMovie {

    private Movie starWars;

    @Before
    public void setUp() {
        this.starWars =  new Movie("star Wars");
    }

    @Test
    public void testMovieName() {
        assertEquals("star wars should have a name", "star Wars", starWars.getName());
    }

    @Test
    public void testToString() {
        assertEquals("starWars should have toString", "star Wars", starWars.toString());
    }

    @Test
    public void testEquals() {
        final Movie a = new Movie("star wars");
        final Movie b = new Movie("star wars");
        final Movie c = new Movie("star trek");
        final Movie d = new Movie("star wars"){};
        assertEquals(a,b);
        assertNotEquals(c,d);

    }
}
