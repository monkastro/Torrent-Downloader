package torrent.magnet.movie.downloader.browser.Model;

public class ListModel {
    public Data data;
    public Meta meta;
    public String status;
    public String statusMessage;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta2) {
        this.meta = meta2;
    }
}
