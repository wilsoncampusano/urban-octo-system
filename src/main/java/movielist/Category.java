package movielist;

import java.util.Vector;

public class Category {

  private String name;

  private Category(String aName){
    this.name = aName;
    allCategories.add(this);
  }

  private static Vector allCategories = new Vector();

  public static final Category UNCATEGORIZED = new Category("Uncategorized");
  public static final Category SCIFI = new Category("Science Fiction");
  public static final Category HORROR = new Category("Horror");
  public static final Category COMEDY = new Category("Comedy");
  public static final Category FANTASY = new Category("Fantasy");
  public static final Category THRILLER = new Category("Thriller");

  @Override
  public String toString() {
    return name;
  }

  public static Vector categories() {
    return (Vector) allCategories.clone();
  }
}
