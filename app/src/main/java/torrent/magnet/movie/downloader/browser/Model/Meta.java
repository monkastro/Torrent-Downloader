package torrent.magnet.movie.downloader.browser.Model;

public class Meta {
    private Integer apiVersion;
    private String executionTime;
    private Integer serverTime;
    private String serverTimezone;

    public Integer getServerTime() {
        return this.serverTime;
    }

    public void setServerTime(Integer num) {
        this.serverTime = num;
    }

    public String getServerTimezone() {
        return this.serverTimezone;
    }

    public void setServerTimezone(String str) {
        this.serverTimezone = str;
    }

    public Integer getApiVersion() {
        return this.apiVersion;
    }

    public void setApiVersion(Integer num) {
        this.apiVersion = num;
    }

    public String getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(String str) {
        this.executionTime = str;
    }
}
