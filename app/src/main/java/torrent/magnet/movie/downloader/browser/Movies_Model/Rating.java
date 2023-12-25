package torrent.magnet.movie.downloader.browser.Movies_Model;

public class Rating {
    private Integer hated;
    private Integer loved;
    private Integer percentage;
    private Integer votes;
    private Integer watching;

    public float getPercentage() {
        return ((float) this.percentage.intValue()) / 10.0f;
    }

    public void setPercentage(Integer num) {
        this.percentage = num;
    }

    public Integer getWatching() {
        return this.watching;
    }

    public void setWatching(Integer num) {
        this.watching = num;
    }

    public Integer getVotes() {
        return this.votes;
    }

    public void setVotes(Integer num) {
        this.votes = num;
    }

    public Integer getLoved() {
        return this.loved;
    }

    public void setLoved(Integer num) {
        this.loved = num;
    }

    public Integer getHated() {
        return this.hated;
    }

    public void setHated(Integer num) {
        this.hated = num;
    }
}
