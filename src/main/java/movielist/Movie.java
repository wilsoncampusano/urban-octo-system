package movielist;

import java.util.Objects;

public class Movie {
  private String name;
  private int rating;
  private String category ="Uncategorized";

  public Movie(String title) {
    this(title, -1);
  }

  public Movie(Movie orginal) {
    name = orginal.name;
  }

  public Movie(String title, int rating) {
    checkNull(title);
    checkEmpty(title);
    name = title;
    this.rating = rating;
  }

  public Movie(String aName, String aCategory, int aRating){
    this(aName, aRating);
    this.category = (aCategory != null)? aCategory : "Uncategorized";
  }

  private void checkNull(String title) {
    if (Objects.isNull(title)) throw new IllegalArgumentException("null movie name");
  }

  public String getName() {
    return name;
  }

  public void rename(String newName) {
    checkNull(newName);
    checkEmpty(newName);
    this.name = newName;
  }

  private void checkEmpty(String newName) {
    if (newName.trim().isEmpty()) throw new IllegalArgumentException("empty movie name");
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Movie movie = (Movie) o;

    return name != null ? name.equals(movie.name) : movie.name == null;

  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  public boolean hasRating() {
    return rating > -1;
  }

  public int getRating() throws UnratedException {
    if(hasRating())
      return rating;
    throw new UnratedException(this.name);
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}