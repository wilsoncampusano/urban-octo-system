package movielist.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses(value = {
    TestEmptyMovieList.class,
    TestMovieListWithOneMovie.class,
    TestMovieListWithTwoMovies.class,
    TestMovie.class
})
@RunWith(Suite.class)
public class AllTest {

}