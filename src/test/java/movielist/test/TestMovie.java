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


    @Test
    public void testRenaming() {
        String newName = "Star Trek";
        Movie aMovie = new Movie("Star Wars");

        aMovie.rename(newName);

        assertEquals("renaming should change the name", newName, aMovie.getName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        new Movie(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        new Movie("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRenaming() {
        Movie aMovie = new Movie("Star Wars");
        aMovie.rename(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyRename() {
        Movie movie = new Movie("Star Wars");
        movie.rename("");
    }
}
