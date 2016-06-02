package movielist;

import java.util.Objects;

public class Movie {
    private String name;

    public Movie(String title) {
        checkNull(title);
        if( title.trim().isEmpty()) throw new IllegalArgumentException("empty movie name");

        this.name = title;
    }

    private void checkNull(String title) {
        if(Objects.isNull(title)) throw new IllegalArgumentException("null movie name");
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        checkNull(newName);
        if(newName.trim().isEmpty()) throw new IllegalArgumentException("empty movie name");
        this.name  =  newName;
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
}
