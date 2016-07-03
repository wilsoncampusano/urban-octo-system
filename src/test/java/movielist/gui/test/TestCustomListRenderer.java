package movielist.gui.test;

import movielist.Movie;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestCustomListRenderer {
  private Movie fotr = null;
  private Movie starTrek = null;
  private CustomMovieListRenderer renderer = null;
  private JList list = null;

  @Before
  public void setUp() {
    fotr = new Movie("Fellowship of The Ring", 5);
    starTrek = new Movie("Start Trek", 3);
    renderer = new CustomMovieListRenderer();
    list = new JList();
  }

  @Test
  public void testReturnsSelf() {
    Component result = renderer.getListCellRendererComponent(list, fotr, 1, false, false);
    assertSame("should return self", renderer, result);
  }

  @Test
  public void testContents() {

    renderer.getListCellRendererComponent(list, fotr, 1, false, false);

    assertEquals("Text should be "+fotr.getName(), fotr.getName(), renderer.getText());
    assertSame("icon should be 5 stars", CustomMovieListRenderer.iconForRating(5), renderer.getIcon());

    renderer.getListCellRendererComponent(list, starTrek, 1, true, false);
    assertEquals("text should be"+starTrek.getName(), starTrek.getName(), renderer.getText());
    assertSame("icon should be 3 stars", CustomMovieListRenderer.iconForRating(3), renderer.getIcon());

  }
}
