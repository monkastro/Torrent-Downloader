package torrent.magnet.movie.downloader.browser.Movies_Model;

public class _720p {
    private String filesize;
    private double peer;
    private String provider;
    private double seed;
    private double size;
    private String url;

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getFilesize() {
        return this.filesize;
    }

    public void setFilesize(String str) {
        this.filesize = str;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double d) {
        this.size = d;
    }

    public double getPeer() {
        return this.peer;
    }

    public void setPeer(double d) {
        this.peer = d;
    }

    public double getSeed() {
        return this.seed;
    }

    public void setSeed(double d) {
        this.seed = d;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
