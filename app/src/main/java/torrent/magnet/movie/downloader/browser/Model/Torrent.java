package torrent.magnet.movie.downloader.browser.Model;

public class Torrent {
    private String dateUploaded;
    private Integer dateUploadedUnix;
    private String hash;
    private Integer peers;
    private String quality;
    private Integer seeds;
    private String size;
    private Integer sizeBytes;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public Integer getSeeds() {
        return this.seeds;
    }

    public void setSeeds(Integer num) {
        this.seeds = num;
    }

    public Integer getPeers() {
        return this.peers;
    }

    public void setPeers(Integer num) {
        this.peers = num;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public Integer getSizeBytes() {
        return this.sizeBytes;
    }

    public void setSizeBytes(Integer num) {
        this.sizeBytes = num;
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
