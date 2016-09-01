package movielist;

public class Category {

  private String name;

  private Category(String aName){
    this.name = aName;
  }

  public static final Category UNCATEGORIZED = new Category("Uncategorized");
  public static final Category HORROR = new Category("Horror");
  public static final Category SCIFI = new Category("Science Fiction");


  @Override
  public String toString() {
    return name;
  }
}
