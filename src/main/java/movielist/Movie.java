package movielist;

import java.util.Objects;

public class Movie {
    private String name;

    public Movie(String title) {
        if(Objects.isNull(title) || title.trim().isEmpty()) throw new IllegalArgumentException("null movie name");

        this.name = title;
    }

    public String getName() {
        return name;
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

    public void rename(String newName) {
        if(Objects.isNull(newName)) throw new IllegalArgumentException("null movie name");
        this.name  =  newName;
    }
}
