package torrent.magnet.movie.downloader.browser.Fragments;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import torrent.magnet.movie.downloader.browser.Adapters.Movies_Adapter;
import torrent.magnet.movie.downloader.browser.AppController.App;
import torrent.magnet.movie.downloader.browser.Movies_Model.ListPopcorn;
import torrent.magnet.movie.downloader.browser.Movies_Model.PopcornModel;
import torrent.magnet.movie.downloader.browser.R;

public class Popcorn_Movies extends Fragment {
    public static final String ListMoviePopcorn = "https://tv-v2.api-fetch.website/movies/";
    final String TAG = "Popcorn Movies";
    Activity activity;
    TextView error_text;
    Gson gson = new Gson();
    RelativeLayout layout_internet;
    ListPopcorn listModel;
    boolean loading = true;

    public Movies_Adapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<PopcornModel> movieList;
    int page = 1;
    String params = "?sort=last%20added&order=-1";
    int pastVisiblesItems;
    SwipeRefreshLayout refreshSwipe;
    int totalItemCount;
    int totalPages = 10;
    int visibleItemCount;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_popcorn_movies, viewGroup, false);
        this.layout_internet = (RelativeLayout) inflate.findViewById(R.id.pop_layout_internet);
        this.refreshSwipe = (SwipeRefreshLayout) inflate.findViewById(R.id.pop_swiperefresh);
        this.error_text = (TextView) inflate.findViewById(R.id.pop_textinternet);
        this.movieList = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.pop_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        this.mLayoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        theRequest("https://tv-v2.api-fetch.website/movies/1" + this.params, false);
        reqPages(ListMoviePopcorn);
        this.refreshSwipe.setRefreshing(true);
        Movies_Adapter popcorn_Adapter = new Movies_Adapter(this.movieList, this.activity);
        this.mAdapter = popcorn_Adapter;
        recyclerView.setAdapter(popcorn_Adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                Popcorn_Movies popcorn_Movies = Popcorn_Movies.this;
                popcorn_Movies.visibleItemCount = popcorn_Movies.mLayoutManager.getChildCount();
                Popcorn_Movies popcorn_Movies2 = Popcorn_Movies.this;
                popcorn_Movies2.totalItemCount = popcorn_Movies2.mLayoutManager.getItemCount();
                Popcorn_Movies popcorn_Movies3 = Popcorn_Movies.this;
                popcorn_Movies3.pastVisiblesItems = popcorn_Movies3.mLayoutManager.findFirstVisibleItemPosition();
                if (Popcorn_Movies.this.loading && Popcorn_Movies.this.visibleItemCount + Popcorn_Movies.this.pastVisiblesItems >= Popcorn_Movies.this.totalItemCount) {
                    Popcorn_Movies.this.loading = false;
                    Log.e("TAGl", "inside loading tot & page = " + Popcorn_Movies.this.totalPages + " " + Popcorn_Movies.this.page);
                    if (Popcorn_Movies.this.page < Popcorn_Movies.this.totalPages) {
                        Log.e("TAGl", "inside page<=totalPage");
                        Log.e("TAGl", "last inside tot & page = " + Popcorn_Movies.this.totalPages + " " + Popcorn_Movies.this.page);
                        Popcorn_Movies popcorn_Movies4 = Popcorn_Movies.this;
                        popcorn_Movies4.page = popcorn_Movies4.page + 1;
                        Popcorn_Movies popcorn_Movies5 = Popcorn_Movies.this;
                        popcorn_Movies5.theRequest(Popcorn_Movies.ListMoviePopcorn + Popcorn_Movies.this.page + Popcorn_Movies.this.params, false);
                    }
                }
            }
        });
        ((Button) inflate.findViewById(R.id.btn_popular_refresh)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Popcorn_Movies popcorn_Movies = Popcorn_Movies.this;
                popcorn_Movies.theRequest("https://tv-v2.api-fetch.website/movies/1" + Popcorn_Movies.this.params, false);
            }
        });
        this.refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Log.i("TAG", "onRefresh called from SwipeRefreshLayout");
                Popcorn_Movies popcorn_Movies = Popcorn_Movies.this;
                popcorn_Movies.theRequest("https://tv-v2.api-fetch.website/movies/1" + Popcorn_Movies.this.params, true);
                Popcorn_Movies.this.refreshSwipe.setRefreshing(true);
            }
        });
        return inflate;
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.activity.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void theRequest(final String str, final Boolean bool) {
        App.getInstance().addToRequestQueue(new StringRequest(0, str, new Response.Listener<String>() {
            public void onResponse(String str) {
                Log.d("Popcorn_Response", str);
                Log.d("Popcorn_Movies_URL", str);
                Popcorn_Movies.this.refreshSwipe.setRefreshing(false);
                Popcorn_Movies popcorn_Movies = Popcorn_Movies.this;
                Gson gson = popcorn_Movies.gson;
                popcorn_Movies.listModel = (ListPopcorn) gson.fromJson("{\"popcornmodel\":" + str + "}", ListPopcorn.class);
                Popcorn_Movies.this.loading = true;
                Popcorn_Movies popcorn_Movies2 = Popcorn_Movies.this;
                popcorn_Movies2.movieList = popcorn_Movies2.listModel.getPopcornModel();
                Log.e("Popcorn: ", "movieCount = " + 10 + " movieLimit = " + 10 + " totalPage = " + Popcorn_Movies.this.totalPages);
                if (Popcorn_Movies.this.movieList != null) {
                    Popcorn_Movies.this.layout_internet.setVisibility(8);
                    if (bool.booleanValue()) {
                        Popcorn_Movies.this.refreshSwipe.setRefreshing(false);
                        Popcorn_Movies.this.mAdapter.replaceItems(Popcorn_Movies.this.movieList);
                        return;
                    }
                    Popcorn_Movies.this.mAdapter.addItems(Popcorn_Movies.this.movieList);
                    return;
                }
                Popcorn_Movies.this.refreshSwipe.setRefreshing(true);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Popcorn_Movies.this.refreshSwipe.setRefreshing(false);
                Log.d("Popcorn Movies", volleyError + "");
                Toast.makeText(Popcorn_Movies.this.activity, "Kindly make sure you have a stable internet connection ..", 1).show();
                Popcorn_Movies.this.loading = true;
            }
        }), "popcorn_movie_list");
        App.getInstance().getRequestQueue().getCache().invalidate(str, true);
        App.getInstance().getRequestQueue().getCache().clear();
    }

    public void reqPages(String str) {
        App.getInstance().addToRequestQueue(new StringRequest(0, str, new Response.Listener<String>() {
            public void onResponse(String str) {
                JsonObject jsonObject = (JsonObject) Popcorn_Movies.this.gson.fromJson("{\"data\":" + str + "}", JsonObject.class);
                StringBuilder sb = new StringBuilder();
                sb.append("size  = ");
                sb.append(jsonObject.get("data").getAsJsonArray().size());
                Log.e("TAG", sb.toString());
                Popcorn_Movies.this.totalPages = jsonObject.get("data").getAsJsonArray().size();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        }), "movie_pages");
        App.getInstance().getRequestQueue().getCache().invalidate(str, true);
    }

    public void onResume() {
        super.onResume();
        if (!isNetworkAvailable()) {
            this.layout_internet.setVisibility(0);
        }
    }
}
