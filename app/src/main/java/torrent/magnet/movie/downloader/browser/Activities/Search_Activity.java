package torrent.magnet.movie.downloader.browser.Activities;

import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import torrent.magnet.movie.downloader.browser.Adapters.Browse_Adapter;
import torrent.magnet.movie.downloader.browser.AppController.App;
import torrent.magnet.movie.downloader.browser.Model.ListModel;
import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.R;

public class Search_Activity extends AppCompatActivity {
    public static final String ListUrl = "https://yts.ag/api/v2/list_movies.json";
    RelativeLayout NoMoviesLayout;
    EditText editsearch;
    String genreString = "";
    Gson gson = new Gson();
    ListModel listModel;
    boolean loading = true;

    public Browse_Adapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<Movie> movieList;
    int page = 1;
    int pastVisiblesItems;
    String popSortString = "";
    ProgressBar progressBar;
    String qualityString = "";
    String queryString = "";
    String ratingString = "";
    Button search;
    RecyclerView searchrecycler;
    String sortString = "latest";
    TextView title;
    int totalItemCount;
    int totalPages = 1;
    int visibleItemCount;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_search);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        this.NoMoviesLayout = (RelativeLayout) findViewById(R.id.search_layout_internet);
        this.title = (TextView) findViewById(R.id.titletext);
        this.title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.ttf"));
        this.editsearch = (EditText) findViewById(R.id.search_query);
        this.searchrecycler = (RecyclerView) findViewById(R.id.search_recycler);
        this.search = (Button) findViewById(R.id.search_button);
        this.progressBar = (ProgressBar) findViewById(R.id.progressbar);
        this.movieList = new ArrayList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        this.mLayoutManager = gridLayoutManager;
        this.searchrecycler.setLayoutManager(gridLayoutManager);
        this.searchrecycler.setItemAnimator(new DefaultItemAnimator());
        this.searchrecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                Log.e("TAGl", "inside onScolled totpages = " + Search_Activity.this.totalPages);
                Search_Activity search_Activity = Search_Activity.this;
                search_Activity.visibleItemCount = search_Activity.mLayoutManager.getChildCount();
                Search_Activity search_Activity2 = Search_Activity.this;
                search_Activity2.totalItemCount = search_Activity2.mLayoutManager.getItemCount();
                Search_Activity search_Activity3 = Search_Activity.this;
                search_Activity3.pastVisiblesItems = search_Activity3.mLayoutManager.findFirstVisibleItemPosition();
                if (Search_Activity.this.loading && Search_Activity.this.visibleItemCount + Search_Activity.this.pastVisiblesItems >= Search_Activity.this.totalItemCount) {
                    Search_Activity.this.loading = false;
                    Log.e("TAGl", "inside loading tot & page = " + Search_Activity.this.totalPages + " " + Search_Activity.this.page);
                    if (Search_Activity.this.page < Search_Activity.this.totalPages) {
                        Log.e("TAGl", "inside page<=totalPage");
                        Log.e("TAGl", "last inside tot & page = " + Search_Activity.this.totalPages + " " + Search_Activity.this.page);
                        Search_Activity search_Activity4 = Search_Activity.this;
                        search_Activity4.page = search_Activity4.page + 1;
                        Search_Activity search_Activity5 = Search_Activity.this;
                        search_Activity5.searchRequest(Search_Activity.this.generateSearchUrlYTS() + "?page=" + Search_Activity.this.page, false);
                    }
                }
            }
        });
        Browse_Adapter browse_Adapter = new Browse_Adapter(this.movieList, getApplicationContext());
        this.mAdapter = browse_Adapter;
        this.searchrecycler.setAdapter(browse_Adapter);
        this.search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((InputMethodManager) Search_Activity.this.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                Search_Activity search_Activity = Search_Activity.this;
                search_Activity.searchRequest(search_Activity.generateSearchUrlYTS(), true);
            }
        });
    }

    public void startLoading() {
        this.progressBar.setVisibility(0);
    }

    public void stopLoading() {
        this.progressBar.setVisibility(4);
    }

    public String generateSearchUrlYTS() {
        URL url;
        try {
            url = new URL((ListUrl + ("?query_term=" + this.editsearch.getText().toString())).replaceAll(" ", "%20"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        Log.e("Search URL  = ", url + "");
        return String.valueOf(url);
    }

    public void searchRequest(String str, final boolean z) {
        this.search.clearFocus();
        startLoading();
        App.getInstance().addToRequestQueue(new JsonObjectRequest(0, str, (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                Log.d("Response", jSONObject.toString());
                Search_Activity.this.stopLoading();
                Search_Activity search_Activity = Search_Activity.this;
                search_Activity.listModel = (ListModel) search_Activity.gson.fromJson(jSONObject.toString(), ListModel.class);
                Log.e("tag", "response " + Search_Activity.this.listModel.getStatus());
                Search_Activity.this.loading = true;
                Search_Activity search_Activity2 = Search_Activity.this;
                search_Activity2.movieList = search_Activity2.listModel.getData().getMovies();
                int intValue = Search_Activity.this.listModel.getData().getMovieCount().intValue();
                int intValue2 = Search_Activity.this.listModel.getData().getLimit().intValue();
                Search_Activity.this.totalPages = intValue / intValue2;
                if (intValue % intValue2 != 0) {
                    Search_Activity.this.totalPages++;
                }
                Log.e("Search Activity", "movieCount = " + intValue + " movieLimit = " + intValue2 + " totalPage = " + Search_Activity.this.totalPages);
                if (Search_Activity.this.movieList != null) {
                    Search_Activity.this.NoMoviesLayout.setVisibility(8);
                    if (z) {
                        Search_Activity.this.mAdapter.replaceItems(Search_Activity.this.movieList);
                    } else {
                        Search_Activity.this.mAdapter.addItems(Search_Activity.this.movieList);
                    }
                    Search_Activity.this.searchrecycler.setVisibility(0);
                    return;
                }
                Search_Activity.this.searchrecycler.setVisibility(8);
                Search_Activity.this.NoMoviesLayout.setVisibility(0);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Search_Activity.this.searchrecycler.setVisibility(8);
                Search_Activity.this.NoMoviesLayout.setVisibility(0);
                Search_Activity.this.loading = true;
                Search_Activity.this.stopLoading();
            }
        }), "movie_list_search");
        App.getInstance().getRequestQueue().getCache().invalidate(str, true);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onResume() {
        super.onResume();
        if (!isNetworkAvailable()) {
            this.NoMoviesLayout.setVisibility(0);
        }
    }
}
