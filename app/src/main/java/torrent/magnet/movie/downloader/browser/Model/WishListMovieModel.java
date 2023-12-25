package torrent.magnet.movie.downloader.browser.Model;

import java.util.Date;

public class WishListMovieModel {
    private Date added_on;


    private String f105id;
    private String image_url;
    private String json_string;
    private String movie_id;
    private String provider;
    private String quality_one;
    private String quality_three;
    private String quality_two;
    private String rating;
    private String title;

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public Date getAdded_on() {
        return this.added_on;
    }

    public void setAdded_on(Date date) {
        this.added_on = date;
    }

    public String getMovie_id() {
        return this.movie_id;
    }

    public void setMovie_id(String str) {
        this.movie_id = str;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String str) {
        this.image_url = str;
    }

    public String getQuality_one() {
        return this.quality_one;
    }

    public void setQuality_one(String str) {
        this.quality_one = str;
    }

    public String getQuality_two() {
        return this.quality_two;
    }

    public void setQuality_two(String str) {
        this.quality_two = str;
    }

    public String getQuality_three() {
        return this.quality_three;
    }

    public void setQuality_three(String str) {
        this.quality_three = str;
    }

    public String getJson_string() {
        return this.json_string;
    }

    public void setJson_string(String str) {
        this.json_string = str;
    }
}
