package torrent.magnet.movie.downloader.browser.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import torrent.magnet.movie.downloader.browser.R;


public class Policy_Activity extends AppCompatActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_policy_);
        TextView textView = (TextView) findViewById(R.id.tv_terms);
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView textView2 = (TextView) findViewById(R.id.tv_conditions);
        if (textView2 != null) {
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
        }
        ((LinearLayout) findViewById(R.id.layout_accept)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences.Editor edit = Policy_Activity.this.getApplicationContext().getSharedPreferences("MyPref", 0).edit();
                edit.putBoolean("open_policy", false);
                edit.apply();
                Policy_Activity.this.startActivity(new Intent(Policy_Activity.this, MainActivity.class));
            }
        });
    }
}
