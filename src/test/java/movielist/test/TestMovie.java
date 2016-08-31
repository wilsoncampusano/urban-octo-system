package movielist.test;

import movielist.Category;
import movielist.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMovie {

  private Movie starWars;

  @Before
  public void setUp() {
    this.starWars = new Movie("star Wars", Category.UNCATEGORIZED, -1);
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
    final Movie a = new Movie("star wars", Category.UNCATEGORIZED, -1);
    final Movie b = new Movie("star wars", Category.UNCATEGORIZED, -1);
    final Movie c = new Movie("star trek", Category.UNCATEGORIZED, -1);
    final Movie d = new Movie("star wars", Category.UNCATEGORIZED, -1);
    assertEquals(a, b);
    assertNotEquals(c, d);

  }

  @Test
  public void testRenaming() {
    String newName = "Star Trek";
    starWars.rename(newName);

    assertEquals("renaming should change the name", newName, starWars.getName());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {

    String nullSring = null;
    new Movie(nullSring,Category.UNCATEGORIZED, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    new Movie("", Category.UNCATEGORIZED, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRenaming() {
    starWars.rename(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyRename() {
    starWars.rename("");
  }

  @Test
  public void testCopyConstructor() {
    Movie copyOfStarWars = new Movie(starWars);
    assertNotSame("a copy should not be the same as the original", starWars, copyOfStarWars);
    assertEquals("a copy should be equals to the original ", starWars, copyOfStarWars);
  }


  @Test
  public void testUncategorized() {
    assertEquals("starWars shoul dbe uncategorized. ", Category.UNCATEGORIZED, starWars.getCategory());
  }

  @Test
  public void testScienceFiction() {
    Movie alien = new Movie("Alien", Category.SCIFI, 1);
    assertEquals("alien should be science fiction", Category.SCIFI, alien.getCategory());
  }


}