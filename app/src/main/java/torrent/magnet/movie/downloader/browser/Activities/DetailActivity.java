package torrent.magnet.movie.downloader.browser.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.google.gson.Gson;
import torrent.magnet.movie.downloader.browser.AppController.App;

import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.Model.Torrent;
import torrent.magnet.movie.downloader.browser.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
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
    //private AdView adView;
    Typeface aleoregular;
    Typeface boldtype;


    Context f97c;
    LinearLayout collectionLayout;
    LinearLayout copylayout3D;
    LinearLayout copylayoutFHD;
    LinearLayout copylayoutHD;
    TextView dPeers;
    TextView dSeeds;
    TextView dSize;
    DatabaseManager dataBaseManager;
    TextView fPeers;
    TextView fSeeds;
    TextView fSize;
    ImageView fabFav;
    boolean fabKey = false;
    TextView fhdurl;
    TextView hdurl;

    String jsonString;
    ImageView largerposter;
    Movie movie;
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
        this.f97c = this;

        this.dataBaseManager = new DatabaseManager(this.f97c);
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
            this.movie = (Movie) new Gson().fromJson(this.jsonString, Movie.class);
        } else {
            Log.e(this.TAG, "activity has no extra ");
        }
        this.movie = (Movie) new Gson().fromJson(this.jsonString, Movie.class);
        this.Mpa_RatingLayout = (LinearLayout) findViewById(R.id.layout_mpRating);
        TextView textView = (TextView) findViewById(R.id.textfavourites);
        this.textfavourites = textView;
        textView.setTypeface(this.QuattrocentoSans_Regular);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.favouriteslayout);
        this.collectionLayout = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DetailActivity.this.fabKey) {
                    DetailActivity.this.dataBaseManager.deleteMovie(DetailActivity.this.movie.getId().toString());
                    DetailActivity.this.fabFav.setImageDrawable(DetailActivity.this.f97c.getResources().getDrawable(R.drawable.ic_heart_empty));
                    DetailActivity.this.textfavourites.setText("Add to Favourites");
                    DetailActivity.this.fabKey = false;
                    alert();
                    //DetailActivity.this.showAlert("Removed", "Movie removed from Favourites", (View.OnClickListener) null, R.color.RED);
                    return;
                }
                DetailActivity.this.dataBaseManager.insertMovie(DetailActivity.this.movie);
                DetailActivity.this.fabFav.setImageDrawable(DetailActivity.this.f97c.getResources().getDrawable(R.drawable.ic_heart_filled));
                DetailActivity.this.textfavourites.setText("Added to Favourites");
                DetailActivity.this.fabKey = true;
                alert();
                /*DetailActivity.this.showAlert("Added", "Movie added to Favourites", new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.e("TAG", "added click listener ");
                    }
                }, R.color.colorPrimary);*/
            }
        });
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
        this.Mainhd = (LinearLayout) findViewById(R.id.mainhd);
        this.Mainfhd = (LinearLayout) findViewById(R.id.mainfhd);
        this.Main3d = (LinearLayout) findViewById(R.id.main3d);
        this.largerposter = (ImageView) findViewById(R.id.large_poster);
        Glide.with((FragmentActivity) this).load(this.movie.getMediumCoverImage()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).apply(RequestOptions.centerCropTransform().dontAnimate()).into(this.largerposter);
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
        Movie movie2 = this.movie;
        if (movie2 != null) {
            this.Date.setText(String.valueOf(movie2.getYear()));
            this.Rating.setText(String.valueOf(this.movie.getRating()));
            if (!this.movie.getMpaRating().isEmpty()) {
                this.Mpa_RatingLayout.setVisibility(0);
                this.tv_mpaRating.setText(this.movie.getMpaRating());
            } else {
                this.Mpa_RatingLayout.setVisibility(8);
            }
            int intValue = this.movie.getRuntime().intValue();
            TextView textView23 = this.time_duration;
            textView23.setText((intValue / 60) + "h : " + (intValue % 60) + " m");
            this.Synopsis.setText(this.movie.getSynopsis());
            this.Title.setText(this.movie.getTitle());
            if (this.movie.getTitleEnglish() != null) {
                this.movieName = this.movie.getTitleEnglish();
            } else {
                this.movieName = this.movie.getTitle();
            }
        }
        ((CardView) findViewById(R.id.card_poster)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this.f97c, ImageView_Activity.class);
                if (DetailActivity.this.movie.getLargeCoverImage() == null) {
                    intent.putExtra("img_url", DetailActivity.this.movie.getMediumCoverImage());
                } else {
                    intent.putExtra("img_url", DetailActivity.this.movie.getLargeCoverImage());
                }
                DetailActivity.this.startActivity(intent);
            }
        });
        if (this.movie.getTorrents() != null) {
            List<Torrent> torrents = this.movie.getTorrents();
            if (torrents.size() > 0) {
                for (int i = 0; i < torrents.size(); i++) {
                    if (i == 0) {
                        final Torrent torrent = torrents.get(i);
                        if (torrent.getQuality().equalsIgnoreCase("3D")) {
                            TextView textView24 = this.dSeeds;
                            textView24.setText("Seeds : " + torrent.getSeeds() + "");
                            TextView textView25 = this.dPeers;
                            textView25.setText("Peers : " + torrent.getPeers() + "");
                            TextView textView26 = this.dSize;
                            textView26.setText("Size : " + torrent.getSize() + "");
                            this.Main3d.setVisibility(0);
                            this.Main3d.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "3D magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayout3D.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "3D magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        if (DetailActivity.this.f97c != null) {

                                            alert1();
                                            //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        } else if (torrent.getQuality().equalsIgnoreCase("720p")) {
                            TextView textView27 = this.Seeds;
                            textView27.setText("Seeds : " + torrent.getSeeds() + "");
                            TextView textView28 = this.Peers;
                            textView28.setText("Peers : " + torrent.getPeers() + "");
                            TextView textView29 = this.Size;
                            textView29.setText("Size : " + torrent.getSize() + "");
                            this.Mainhd.setVisibility(0);
                            this.Mainhd.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "720p magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayoutHD.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "720p magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        alert1();
                                        //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        } else if (torrent.getQuality().equalsIgnoreCase("1080p")) {
                            TextView textView30 = this.fSeeds;
                            textView30.setText("Seeds : " + torrent.getSeeds() + "");
                            TextView textView31 = this.fPeers;
                            textView31.setText("Peers : " + torrent.getPeers() + "");
                            TextView textView32 = this.fSize;
                            textView32.setText("Size : " + torrent.getSize() + "");
                            this.Mainfhd.setVisibility(0);
                            this.Mainfhd.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "1080p magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayoutFHD.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "1080p magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        alert1();
                                        //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        }
                    } else if (i == 1) {
                        final Torrent torrent2 = torrents.get(i);
                        if (torrent2.getQuality().equalsIgnoreCase("3D")) {
                            TextView textView33 = this.dSeeds;
                            textView33.setText("Seeds : " + torrent2.getSeeds() + "");
                            TextView textView34 = this.dPeers;
                            textView34.setText("Peers : " + torrent2.getPeers() + "");
                            TextView textView35 = this.dSize;
                            textView35.setText("Size : " + torrent2.getSize() + "");
                            this.Main3d.setVisibility(0);
                            this.Main3d.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent2.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "3D magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayout3D.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent2.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "3D magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        if (DetailActivity.this.f97c != null) {

                                            alert1();
                                            //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        } else if (torrent2.getQuality().equalsIgnoreCase("720p")) {
                            TextView textView36 = this.Seeds;
                            textView36.setText("Seeds : " + torrent2.getSeeds() + "");
                            TextView textView37 = this.Peers;
                            textView37.setText("Peers : " + torrent2.getPeers() + "");
                            TextView textView38 = this.Size;
                            textView38.setText("Size : " + torrent2.getSize() + "");
                            this.Mainhd.setVisibility(0);
                            this.Mainhd.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent2.getHash(), DetailActivity.this.movieName);
                                        Log.e("TAG", "720p magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayoutHD.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent2.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "720p magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        alert1();
                                        //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        } else if (torrent2.getQuality().equalsIgnoreCase("1080p")) {
                            TextView textView39 = this.fSeeds;
                            textView39.setText("Seeds : " + torrent2.getSeeds() + "");
                            TextView textView40 = this.fPeers;
                            textView40.setText("Peers : " + torrent2.getPeers() + "");
                            TextView textView41 = this.fSize;
                            textView41.setText("Size : " + torrent2.getSize() + "");
                            this.Mainfhd.setVisibility(0);
                            this.Mainfhd.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent2.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "1080p magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayoutFHD.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent2.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "1080p magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        alert1();
                                       // DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        }
                    } else if (i == 2) {
                        final Torrent torrent3 = torrents.get(i);
                        if (torrent3.getQuality().equalsIgnoreCase("3D")) {
                            TextView textView42 = this.dSeeds;
                            textView42.setText("Seeds : " + torrent3.getSeeds() + "");
                            TextView textView43 = this.dPeers;
                            textView43.setText("Peers : " + torrent3.getPeers() + "");
                            TextView textView44 = this.dSize;
                            textView44.setText("Size : " + torrent3.getSize() + "");
                            this.Main3d.setVisibility(0);
                            this.Main3d.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent3.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "3D magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayout3D.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent3.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "3D magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        if (DetailActivity.this.f97c != null) {

                                            alert1();
                                            //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                        }
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        } else if (torrent3.getQuality().equalsIgnoreCase("720p")) {
                            TextView textView45 = this.Seeds;
                            textView45.setText("Seeds : " + torrent3.getSeeds() + "");
                            TextView textView46 = this.Peers;
                            textView46.setText("Peers : " + torrent3.getPeers() + "");
                            TextView textView47 = this.Size;
                            textView47.setText("Size : " + torrent3.getSize() + "");
                            this.Mainhd.setVisibility(0);
                            this.Mainhd.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent3.getHash(), DetailActivity.this.movieName);
                                        Log.e("TAG", "720p magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayoutHD.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent3.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "720p magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        alert1();
                                        //DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        } else if (torrent3.getQuality().equalsIgnoreCase("1080p")) {
                            TextView textView48 = this.fSeeds;
                            textView48.setText("Seeds : " + torrent3.getSeeds() + "");
                            TextView textView49 = this.fPeers;
                            textView49.setText("Peers : " + torrent3.getPeers() + "");
                            TextView textView50 = this.fSize;
                            textView50.setText("Size : " + torrent3.getSize() + "");
                            this.Mainfhd.setVisibility(0);
                            this.Mainfhd.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent3.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "1080p magnet url = " + generateMagneticUrl);
                                        new App().openMagneturi(generateMagneticUrl, DetailActivity.this.f97c);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            this.copylayoutFHD.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String generateMagneticUrl = DetailActivity.this.generateMagneticUrl(torrent3.getHash(), DetailActivity.this.movieName);
                                        String str = DetailActivity.this.TAG;
                                        Log.e(str, "1080p magneturl = " + generateMagneticUrl);
                                        DetailActivity.this.copyText(generateMagneticUrl);
                                        alert1();
                                       // DetailActivity.this.showAlert("Copied", "Magnetic url copied", (View.OnClickListener) null, R.color.colorPrimary);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                        Log.e(DetailActivity.this.TAG, "try catch error");
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public void copyText(String str) {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("yify", str));
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


 public void alert(){
     AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
     dialog.setCancelable(false);
     dialog.setTitle("Added To Favorite");
     dialog.setMessage("Movie is added to Your collection" );
     dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
             //Action for "Delete".
             dialog.dismiss();

         }
     })
             .setNegativeButton("See collection ", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     //Action for "Cancel".
                     Intent myIntent = new Intent(DetailActivity.this, Collection_Activity.class);
                     //myIntent.putExtra("key", value); //Optional parameters
                     DetailActivity.this.startActivity(myIntent);
                 }
             });

     final AlertDialog alert = dialog.create();
     alert.show();
 }

    public void alert1(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("Link Copied");
        dialog.setMessage("Your movies Link copied" );
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                dialog.dismiss();

            }
        });


        final AlertDialog alert = dialog.create();
        alert.show();
    }

    public void onDestroy() {

        super.onDestroy();
    }
}