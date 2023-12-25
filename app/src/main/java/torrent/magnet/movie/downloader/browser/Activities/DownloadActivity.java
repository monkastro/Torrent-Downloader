package torrent.magnet.movie.downloader.browser.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import torrent.magnet.movie.downloader.browser.AppController.App;
import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.Model.Torrent;
import torrent.magnet.movie.downloader.browser.R;

public class DownloadActivity extends AppCompatActivity {
    String TAG = "Download_Activity";
    Context context;
    String jsonString;
    Movie movieModel;
    String movieName;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_downloads);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ((TextView) findViewById(R.id.title_collection)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.ttf"));
        TextView textView = (TextView) findViewById(R.id.moviename);
        if (getIntent().hasExtra("movie_json")) {
            Log.e(this.TAG, "activity has extra ");
            this.jsonString = getIntent().getStringExtra("movie_json");
            String str = this.TAG;
            Log.e(str, "activity has extra json =  " + this.jsonString);
            this.movieModel = (Movie) new Gson().fromJson(this.jsonString, Movie.class);
        } else {
            Log.e(this.TAG, "activity has no extra ");
        }
        if (getIntent().hasExtra("movie_jsonfhd")) {
            Log.e(this.TAG, "activity has extra ");
            this.jsonString = getIntent().getStringExtra("movie_jsonfhd");
            String str2 = this.TAG;
            Log.e(str2, "activity has extra json =  " + this.jsonString);
            this.movieModel = (Movie) new Gson().fromJson(this.jsonString, Movie.class);
        } else {
            Log.e(this.TAG, "activity has no extra ");
        }
        if (this.movieModel != null) {
            textView.setText(" ( " + this.movieModel.getTitle() + " ) has started downloading");
            if (this.movieModel.getTitleEnglish() != null) {
                this.movieName = this.movieModel.getTitleEnglish();
            } else {
                this.movieName = this.movieModel.getTitle();
            }
            List<Torrent> torrents = this.movieModel.getTorrents();
            if (torrents != null) {
                showDialogue(torrents, this.context);
            }
        }
    }

    public void showDialogue(List<Torrent> list, Context context2) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getQuality() + " " + list.get(i).getSize();
            try {
                String generateMagneticUrl = generateMagneticUrl(list.get(0).getHash(), this.movieName);
                Log.d("Torrent URL", generateMagneticUrl + "");
                new App().openMagneturi(generateMagneticUrl, this);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String generateMagneticUrl(String str, String str2) throws UnsupportedEncodingException {
        String str3 = ("magnet:?xt=urn:btih:" + str + "&dn=") + URLEncoder.encode(str2, "utf-8").replace("+", "%20");
        Log.e("TAG", "after mName encode = " + str3);
        String[] strArr = {"udp://open.demonii.com:1337/announce", "udp://tracker.openbittorrent.com:80", "udp://tracker.coppersurfer.tk:6969", "udp://glotorrents.pw:6969/announce", "udp://tracker.opentrackr.org:1337/announce", "udp://torrent.gresille.org:80/announce", "udp://p4p.arenabg.com:1337", "udp://tracker.leechers-paradise.org:6969"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            String str4 = strArr[i];
            sb.append("&tr=");
            sb.append(URLEncoder.encode(str4, "utf-8").replace("+", "%20"));
        }
        Log.e("TAG", "after tracker encode = " + sb);
        Log.e("TAG", "final magnetic url  = " + str3 + sb);
        return str3 + sb;
    }
}
