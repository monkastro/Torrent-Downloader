package torrent.magnet.movie.downloader.browser.Model;

import java.util.List;

public class DBmodelRoot {
    private List<Movie> data;

    public List<Movie> getData() {
        return this.data;
    }

    public void setData(List<Movie> list) {
        this.data = list;
    }
}
