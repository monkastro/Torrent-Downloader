package torrent.magnet.movie.downloader.browser.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.google.gson.Gson;

import com.tapadoo.alerter.Alerter;

import torrent.magnet.movie.downloader.browser.AppController.App;
import torrent.magnet.movie.downloader.browser.Movies_Model.PopcornModel;
import torrent.magnet.movie.downloader.browser.R;

public class Movies_DetailActivity extends AppCompatActivity {
    static final  boolean $assertionsDisabled = false;
    Typeface CabinRegular;
    TextView Date;
    LinearLayout Main3d;
    LinearLayout Mainfhd;
    LinearLayout Mainhd;
    LinearLayout Mpa_RatingLayout;
    TextView Peers;
    Typeface QuattrocentoSans_Regular;
    TextView Rating;
    TextView Seeds;
    TextView Size;
    TextView Synopsis;
    String TAG = "Details_Activity";
    TextView Title;

    Typeface aleoregular;
    Typeface boldtype;


    Context f98c;
    LinearLayout collectionLayout;
    LinearLayout copylayout3D;
    LinearLayout copylayoutFHD;
    LinearLayout copylayoutHD;
    TextView dPeers;
    TextView dSeeds;
    TextView dSize;
    DatabaseManager dataBaseManager;
    RelativeLayout dlinear;
    TextView fPeers;
    TextView fSeeds;
    TextView fSize;
    ImageView fabFav;
    boolean fabKey = false;
    RelativeLayout fhdlinear;
    TextView fhdurl;
    RelativeLayout hdlinear;
    TextView hdurl;

    String jsonString;
    ImageView largerposter;
    PopcornModel movie;
    String movieName;
    TextView qualityFHD;
    TextView qualityHD;
    TextView qualitythreeD;
    TextView tdurl;
    TextView textfavourites;
    TextView time_duration;
    TextView title_synopsis;
    TextView tv_mpaRating;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.f98c = this;

        this.dataBaseManager = new DatabaseManager(this.f98c);
        this.QuattrocentoSans_Regular = Typeface.createFromAsset(getAssets(), "fonts/QuattrocentoSans-Regular.ttf");
        this.aleoregular = Typeface.createFromAsset(getAssets(), "fonts/aleo-regular-webfont.ttf");
        this.CabinRegular = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.ttf");
        this.boldtype = Typeface.createFromAsset(getAssets(), "fonts/aleo-bold-webfont.ttf");
        this.fabFav = (ImageView) findViewById(R.id.heart);
        if (getIntent().hasExtra("movie_json")) {
            Log.e(this.TAG, "activity has extra ");
            this.jsonString = getIntent().getStringExtra("movie_json");
            String str = this.TAG;
            Log.e(str, "activity has extra json =  " + this.jsonString);
            this.movie = (PopcornModel) new Gson().fromJson(this.jsonString, PopcornModel.class);
        } else {
            Log.e(this.TAG, "activity has no extra ");
        }
        this.Mpa_RatingLayout = (LinearLayout) findViewById(R.id.layout_mpRating);
        this.movie = (PopcornModel) new Gson().fromJson(this.jsonString, PopcornModel.class);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.favouriteslayout);
        this.collectionLayout = linearLayout;
        linearLayout.setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.textfavourites);
        this.textfavourites = textView;
        textView.setTypeface(this.QuattrocentoSans_Regular);
        this.tv_mpaRating = (TextView) findViewById(R.id.mpa_rated);
        TextView textView2 = (TextView) findViewById(R.id.qualityhd);
        this.qualityHD = textView2;
        textView2.setTypeface(this.boldtype);
        TextView textView3 = (TextView) findViewById(R.id.qualityfhd);
        this.qualityFHD = textView3;
        textView3.setTypeface(this.boldtype);
        TextView textView4 = (TextView) findViewById(R.id.qualitythreed);
        this.qualitythreeD = textView4;
        textView4.setTypeface(this.boldtype);
        TextView textView5 = (TextView) findViewById(R.id.hdseeds);
        this.Seeds = textView5;
        textView5.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView6 = (TextView) findViewById(R.id.hdpeers);
        this.Peers = textView6;
        textView6.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView7 = (TextView) findViewById(R.id.hdsize);
        this.Size = textView7;
        textView7.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView8 = (TextView) findViewById(R.id.dseeds);
        this.dSeeds = textView8;
        textView8.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView9 = (TextView) findViewById(R.id.dpeers);
        this.dPeers = textView9;
        textView9.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView10 = (TextView) findViewById(R.id.dsize);
        this.dSize = textView10;
        textView10.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView11 = (TextView) findViewById(R.id.fhdseeds);
        this.fSeeds = textView11;
        textView11.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView12 = (TextView) findViewById(R.id.fhdpeers);
        this.fPeers = textView12;
        textView12.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView13 = (TextView) findViewById(R.id.fhdsize);
        this.fSize = textView13;
        textView13.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView14 = (TextView) findViewById(R.id.hdcopyurl);
        this.hdurl = textView14;
        textView14.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView15 = (TextView) findViewById(R.id.fhdcopyurl);
        this.fhdurl = textView15;
        textView15.setTypeface(this.QuattrocentoSans_Regular);
        TextView textView16 = (TextView) findViewById(R.id.threedcopyurl);
        this.tdurl = textView16;
        textView16.setTypeface(this.QuattrocentoSans_Regular);
        this.copylayoutHD = (LinearLayout) findViewById(R.id.subimagelayoutone);
        this.copylayoutFHD = (LinearLayout) findViewById(R.id.subimagelayouttwo);
        this.copylayout3D = (LinearLayout) findViewById(R.id.subimagelayoutthree);
        this.hdlinear = (RelativeLayout) findViewById(R.id.hdlinear);
        this.fhdlinear = (RelativeLayout) findViewById(R.id.fhdlinear);
        this.dlinear = (RelativeLayout) findViewById(R.id.dlinear);
        this.Mainhd = (LinearLayout) findViewById(R.id.mainhd);
        this.Mainfhd = (LinearLayout) findViewById(R.id.mainfhd);
        this.Main3d = (LinearLayout) findViewById(R.id.main3d);
        this.largerposter = (ImageView) findViewById(R.id.large_poster);
        if (this.movie.getImages() != null) {
            Glide.with((FragmentActivity) this).load(this.movie.getImages().getPoster()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).apply(RequestOptions.centerCropTransform().dontAnimate()).into(this.largerposter);
        }
        TextView textView17 = (TextView) findViewById(R.id.MainTitle);
        this.Title = textView17;
        textView17.setTypeface(this.CabinRegular);
        TextView textView18 = (TextView) findViewById(R.id.Maindate);
        this.Date = textView18;
        textView18.setTypeface(this.aleoregular);
        TextView textView19 = (TextView) findViewById(R.id.rating);
        this.Rating = textView19;
        textView19.setTypeface(this.aleoregular);
        TextView textView20 = (TextView) findViewById(R.id.movie_time);
        this.time_duration = textView20;
        textView20.setTypeface(this.aleoregular);
        TextView textView21 = (TextView) findViewById(R.id.title_synopsis);
        this.title_synopsis = textView21;
        textView21.setTypeface(this.CabinRegular);
        TextView textView22 = (TextView) findViewById(R.id.synopsis);
        this.Synopsis = textView22;
        textView22.setTypeface(this.QuattrocentoSans_Regular);
        PopcornModel popcornModel = this.movie;
        if (popcornModel != null) {
            if (popcornModel.getCertification() != null) {
                this.Mpa_RatingLayout.setVisibility(0);
                this.tv_mpaRating.setText(this.movie.getCertification());
            } else {
                this.Mpa_RatingLayout.setVisibility(8);
            }
            this.Date.setText(String.valueOf(this.movie.getYear()));
            int parseInt = Integer.parseInt(this.movie.getRuntime());
            TextView textView23 = this.time_duration;
            textView23.setText((parseInt / 60) + "h : " + (parseInt % 60) + " m");
            TextView textView24 = this.Rating;
            StringBuilder sb = new StringBuilder();
            sb.append(this.movie.getRating().getPercentage());
            sb.append("");
            textView24.setText(sb.toString());
            this.Synopsis.setText(this.movie.getSynopsis());
            String title = this.movie.getTitle();
            this.movieName = title;
            this.Title.setText(title);
        }
        ((CardView) findViewById(R.id.card_poster)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Movies_DetailActivity.this.f98c, ImageView_Activity.class);
                if (Movies_DetailActivity.this.movie.getImages() != null) {
                    intent.putExtra("img_url", Movies_DetailActivity.this.movie.getImages().getBanner());
                }
                Movies_DetailActivity.this.startActivity(intent);
            }
        });
        if (this.movie.getTorrents().getEn().get720p() != null) {
            final String url = this.movie.getTorrents().getEn().get720p().getUrl();
            TextView textView25 = this.Seeds;
            textView25.setText("Seeds : " + this.movie.getTorrents().getEn().get720p().getSeed() + "");
            TextView textView26 = this.Peers;
            textView26.setText("Peers : " + this.movie.getTorrents().getEn().get720p().getPeer() + "");
            TextView textView27 = this.Size;
            textView27.setText("Size : " + this.movie.getTorrents().getEn().get720p().getFilesize() + "");
            this.Mainhd.setVisibility(0);
            this.Mainhd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new App().openMagneturi(url, Movies_DetailActivity.this.f98c);
                }
            });
            this.copylayoutHD.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Movies_DetailActivity.this.copyText(url);
                    Movies_DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                }
            });
        }
        if (this.movie.getTorrents().getEn().get1080p() != null) {
            final String url2 = this.movie.getTorrents().getEn().get1080p().getUrl();
            TextView textView28 = this.fSeeds;
            textView28.setText("Seeds : " + this.movie.getTorrents().getEn().get1080p().getSeed() + "");
            TextView textView29 = this.fPeers;
            textView29.setText("Peers : " + this.movie.getTorrents().getEn().get1080p().getPeer() + "");
            TextView textView30 = this.fSize;
            textView30.setText("Size : " + this.movie.getTorrents().getEn().get1080p().getFilesize() + "");
            this.Mainfhd.setVisibility(0);
            this.Mainfhd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.e("TAG", "1080 magnet url = " + url2);
                    new App().openMagneturi(url2, Movies_DetailActivity.this.f98c);
                }
            });
            this.copylayoutFHD.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.e("TAG", "1080p magneturl = " + url2);
                    Movies_DetailActivity.this.copyText(url2);
                    Movies_DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                }
            });
        }
    }

    public void copyText(String str) {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("yify", str));
    }

    public void showAlert(String str, String str2, View.OnClickListener onClickListener, int i) {
        Alerter backgroundColorRes = Alerter.create(this).setTitle(str).setText(str2).setBackgroundColorRes(i);
        if (onClickListener != null) {
            backgroundColorRes.setOnClickListener(onClickListener).show();
        } else {
            backgroundColorRes.show();
        }
    }


    public void onDestroy() {
        super.onDestroy();

    }
}
