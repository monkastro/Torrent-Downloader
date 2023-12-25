package torrent.magnet.movie.downloader.browser.Model;

import java.util.List;

public class Data {
    private Integer limit;
    private Integer movie_count;
    private List<Movie> movies = null;
    private Integer page_number;

    public Integer getMovieCount() {
        return this.movie_count;
    }

    public void setMovieCount(Integer num) {
        this.movie_count = num;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public Integer getPageNumber() {
        return this.page_number;
    }

    public void setPageNumber(Integer num) {
        this.page_number = num;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(List<Movie> list) {
        this.movies = list;
    }
}
