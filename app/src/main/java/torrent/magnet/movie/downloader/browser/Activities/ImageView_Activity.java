package torrent.magnet.movie.downloader.browser.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;

import torrent.magnet.movie.downloader.browser.R;


public class ImageView_Activity extends AppCompatActivity {
    String img_url;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_image_view);
        if (getIntent().hasExtra("img_url")) {
            this.img_url = getIntent().getStringExtra("img_url");
        }
        Glide.with((FragmentActivity) this).load(this.img_url).into((ImageView) findViewById(R.id.image_viewer));
    }
}
