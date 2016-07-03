package movielist.gui.test;

import movielist.Movie;

import javax.swing.*;
import java.awt.*;

public class CustomMovieListRenderer extends Component {
  private Object icon;
  private String text;
  private static ImageIcon[] ratingIcons = {
      new ImageIcon("/images/no-rating.gif"),
      new ImageIcon("/images/zero-stars.gif"),
      new ImageIcon("/images/one-star.gif"),
      new ImageIcon("/images/two-stars.gif"),
      new ImageIcon("/images/three-stars.gif"),
      new ImageIcon("/images/four-stars.gif"),
      new ImageIcon("/images/five-stars.gif")
  };

  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    Movie movieToRender = (Movie) value;
    setText(movieToRender.getName());
    if (movieToRender.hasRating()) {
      try {
        setIcon(ratingIcons[movieToRender.getRating() + 1]);
      } catch (Exception e) {
      }
    } else {
      setIcon(ratingIcons[0]);
    }
    return this;
  }

  public Object getIcon() {
    return icon;
  }

  public static ImageIcon iconForRating(int rating) {
    return ratingIcons[++rating];
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setIcon(ImageIcon icon) {
    this.icon = icon;
  }
}
