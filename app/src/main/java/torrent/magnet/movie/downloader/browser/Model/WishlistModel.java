package torrent.magnet.movie.downloader.browser.Model;

import java.util.Date;

public class WishlistModel {
    private Date date;


    private String f106id;
    private String movie;
    private String movieId;

    public void setDate() {
        this.date = new Date();
    }

    public String getMovie() {
        return this.movie;
    }

    public void setMovie(String str) {
        this.movie = str;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String str) {
        this.movieId = str;
    }

    public Date getDate() {
        return this.date;
    }
}
