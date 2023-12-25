package torrent.magnet.movie.downloader.browser.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity extends AppCompatActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getApplicationContext().getSharedPreferences("MyPref", 0).getBoolean("open_policy", true)) {
            startActivity(new Intent(this, Policy_Activity.class));
            finish();
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
