package torrent.magnet.movie.downloader.browser.Movies_Model;

import java.util.List;

public class PopcornModel {
    private String _id;
    private String certification;
    private List<String> genres = null;
    private Images images;
    private String imdb_id;
    private Rating rating;
    private Integer released;
    private String runtime;
    private String synopsis;
    private String title;
    private Torrents torrents;
    private String trailer;
    private String year;

    public String getId() {
        return this._id;
    }

    public void setId(String str) {
        this._id = str;
    }

    public String getImdbId() {
        return this.imdb_id;
    }

    public void setImdbId(String str) {
        this.imdb_id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String str) {
        this.synopsis = str;
    }

    public String getRuntime() {
        return this.runtime;
    }

    public void setRuntime(String str) {
        this.runtime = str;
    }

    public Integer getReleased() {
        return this.released;
    }

    public void setReleased(Integer num) {
        this.released = num;
    }

    public String getCertification() {
        return this.certification;
    }

    public void setCertification(String str) {
        this.certification = str;
    }

    public Torrents getTorrents() {
        return this.torrents;
    }

    public void setTorrents(Torrents torrents2) {
        this.torrents = torrents2;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String str) {
        this.trailer = str;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> list) {
        this.genres = list;
    }

    public Images getImages() {
        return this.images;
    }

    public void setImages(Images images2) {
        this.images = images2;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating2) {
        this.rating = rating2;
    }
}
