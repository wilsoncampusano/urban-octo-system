package movielist.gui;

import movielist.Movie;
import movielist.UnratedException;

import javax.swing.*;
import java.awt.*;

public class CustomMovieListRenderer extends Component implements ListCellRenderer {
  private ImageIcon icon;
  private String text;
  private static ImageIcon[] ratingIcons = {
      new ImageIcon("src/images/no-rating.gif"),
      new ImageIcon("src/images/zero-stars.gif"),
      new ImageIcon("src/images/one-star.gif"),
      new ImageIcon("src/images/two-stars.gif"),
      new ImageIcon("src/images/three-stars.gif"),
      new ImageIcon("src/images/four-stars.gif"),
      new ImageIcon("src/images/five-stars.gif")
  };

  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    Movie movieToRender = (Movie) value;
    if(isSelected){
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    }else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }
    setText(movieToRender.getName());
    if (movieToRender.hasRating()) {
      try {
        setIcon(ratingIcons[movieToRender.getRating() + 1]);
      } catch (UnratedException e) {
      }
    } else {
      setIcon(ratingIcons[0]);
    }
    return this;
  }

  public ImageIcon getIcon() {
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
