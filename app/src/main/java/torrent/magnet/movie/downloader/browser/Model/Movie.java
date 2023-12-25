package torrent.magnet.movie.downloader.browser.Model;

import java.util.List;

public class Movie {
    private String backgroundImage;
    private String backgroundImageOriginal;
    private String dateUploaded;
    private Integer dateUploadedUnix;
    private String description_full;
    private List<String> genres = null;


    private Integer f104id;
    private String imdbCode;
    private String language;
    private String large_cover_image;
    private String medium_cover_image;
    private String mpa_rating;
    private Double rating;
    private Integer runtime;
    private String slug;
    private String small_cover_image;
    private String state;
    private String summary;
    private String synopsis;
    private String title;
    private String titleEnglish;
    private String titleLong;
    private List<Torrent> torrents = null;
    private String url;
    private Integer year;
    private String yt_trailer_code;

    public Integer getId() {
        return this.f104id;
    }

    public void setId(Integer num) {
        this.f104id = num;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getImdbCode() {
        return this.imdbCode;
    }

    public void setImdbCode(String str) {
        this.imdbCode = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitleEnglish() {
        return this.titleEnglish;
    }

    public void setTitleEnglish(String str) {
        this.titleEnglish = str;
    }

    public String getTitleLong() {
        return this.titleLong;
    }

    public void setTitleLong(String str) {
        this.titleLong = str;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String str) {
        this.slug = str;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer num) {
        this.year = num;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double d) {
        this.rating = d;
    }

    public Integer getRuntime() {
        return this.runtime;
    }

    public void setRuntime(Integer num) {
        this.runtime = num;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> list) {
        this.genres = list;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
    }

    public String getDescriptionFull() {
        return this.description_full;
    }

    public void setDescriptionFull(String str) {
        this.description_full = str;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String str) {
        this.synopsis = str;
    }

    public String getYtTrailerCode() {
        return this.yt_trailer_code;
    }

    public void setYtTrailerCode(String str) {
        this.yt_trailer_code = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getMpaRating() {
        return this.mpa_rating;
    }

    public void setMpaRating(String str) {
        this.mpa_rating = str;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public String getBackgroundImageOriginal() {
        return this.backgroundImageOriginal;
    }

    public void setBackgroundImageOriginal(String str) {
        this.backgroundImageOriginal = str;
    }

    public String getSmallCoverImage() {
        return this.small_cover_image;
    }

    public void setSmallCoverImage(String str) {
        this.small_cover_image = str;
    }

    public String getMediumCoverImage() {
        return this.medium_cover_image;
    }

    public void setMediumCoverImage(String str) {
        this.medium_cover_image = str;
    }

    public String getLargeCoverImage() {
        return this.large_cover_image;
    }

    public void setLargeCoverImage(String str) {
        this.large_cover_image = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public List<Torrent> getTorrents() {
        return this.torrents;
    }

    public void setTorrents(List<Torrent> list) {
        this.torrents = list;
    }

    public String getDateUploaded() {
        return this.dateUploaded;
    }

    public void setDateUploaded(String str) {
        this.dateUploaded = str;
    }

    public Integer getDateUploadedUnix() {
        return this.dateUploadedUnix;
    }

    public void setDateUploadedUnix(Integer num) {
        this.dateUploadedUnix = num;
    }
}
