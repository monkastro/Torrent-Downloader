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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import torrent.magnet.movie.downloader.browser.Adapters.Browse_Adapter;
import torrent.magnet.movie.downloader.browser.AppController.App;
import torrent.magnet.movie.downloader.browser.Model.ListModel;
import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.R;

public class Latest_Movies extends Fragment {
    final String TAG = "Latest Movies";
    Activity activity;
    TextView errorText;
    List<Movie> filteredMoviesList;
    Gson gson = new Gson();
    RelativeLayout layout_internet;
    ListModel listModel;
    boolean loading = true;

    public Browse_Adapter mAdapter;
    LinearLayoutManager mLayoutManager;
    int movielimit = 20;
    int page = 1;
    int pastVisiblesItems;
    SwipeRefreshLayout refreshSwipe;
    int totalItemCount;
    int totalPages = 1;

    public String url = ("https://yts.ag/api/v2/list_movies.json?sort_by=date_added&limit=" + this.movielimit);
    int visibleItemCount;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_latest_movies, viewGroup, false);
        this.layout_internet = (RelativeLayout) inflate.findViewById(R.id.layout_internet);
        this.refreshSwipe = (SwipeRefreshLayout) inflate.findViewById(R.id.swiperefresh);
        this.errorText = (TextView) inflate.findViewById(R.id.text_internet);
        this.filteredMoviesList = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.activity, 3);
        this.mLayoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.refreshSwipe.setRefreshing(true);
        theRequest(this.url, true);
        Browse_Adapter browse_Adapter = new Browse_Adapter(this.filteredMoviesList, this.activity);
        this.mAdapter = browse_Adapter;
        recyclerView.setAdapter(browse_Adapter);
        ((Button) inflate.findViewById(R.id.btn_latest_refresh)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Latest_Movies latest_Movies = Latest_Movies.this;
                latest_Movies.theRequest(latest_Movies.url, false);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                Latest_Movies latest_Movies = Latest_Movies.this;
                latest_Movies.visibleItemCount = latest_Movies.mLayoutManager.getChildCount();
                Latest_Movies latest_Movies2 = Latest_Movies.this;
                latest_Movies2.totalItemCount = latest_Movies2.mLayoutManager.getItemCount();
                Latest_Movies latest_Movies3 = Latest_Movies.this;
                latest_Movies3.pastVisiblesItems = latest_Movies3.mLayoutManager.findFirstVisibleItemPosition();
                if (Latest_Movies.this.loading && Latest_Movies.this.visibleItemCount + Latest_Movies.this.pastVisiblesItems >= Latest_Movies.this.totalItemCount) {
                    Latest_Movies.this.loading = false;
                    Log.e("TAGl", "inside loading tot & page = " + Latest_Movies.this.totalPages + " " + Latest_Movies.this.page);
                    if (Latest_Movies.this.page < Latest_Movies.this.totalPages) {
                        Log.e("TAGl", "inside page<=totalPage");
                        Log.e("TAGl", "last inside tot & page = " + Latest_Movies.this.totalPages + " " + Latest_Movies.this.page);
                        Latest_Movies latest_Movies4 = Latest_Movies.this;
                        latest_Movies4.page = latest_Movies4.page + 1;
                        Latest_Movies latest_Movies5 = Latest_Movies.this;
                        latest_Movies5.theRequest(Latest_Movies.this.url + "&page=" + Latest_Movies.this.page, false);
                    }
                }
            }
        });
        this.refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Log.i("TAG", "onRefresh called from SwipeRefreshLayout");
                Latest_Movies latest_Movies = Latest_Movies.this;
                latest_Movies.theRequest(latest_Movies.url, true);
                Latest_Movies.this.refreshSwipe.setRefreshing(true);
            }
        });
        return inflate;
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.activity.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void theRequest(final String str, final Boolean bool) {
        App.getInstance().addToRequestQueue(new JsonObjectRequest(0, str, (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                Log.d("Latest_Movies_Response", jSONObject.toString());
                Log.d("Latest_Movies_URL", str);
                Latest_Movies.this.refreshSwipe.setRefreshing(false);
                Latest_Movies latest_Movies = Latest_Movies.this;
                latest_Movies.listModel = (ListModel) latest_Movies.gson.fromJson(jSONObject.toString(), ListModel.class);
                Latest_Movies.this.loading = true;
                List<Movie> movies = Latest_Movies.this.listModel.getData().getMovies();
                Latest_Movies.this.filteredMoviesList = new ArrayList();
                for (Movie next : movies) {
                    if (next.getRating().doubleValue() > 6.0d && next.getYear().intValue() > 1990) {
                        Latest_Movies.this.filteredMoviesList.add(next);
                    }
                }
                int intValue = Latest_Movies.this.listModel.getData().getMovieCount().intValue();
                int intValue2 = Latest_Movies.this.listModel.getData().getLimit().intValue();
                Latest_Movies.this.totalPages = intValue / intValue2;
                if (intValue % intValue2 != 0) {
                    Latest_Movies.this.totalPages++;
                }
                Log.e("LatestMovies", "movieCount = " + intValue + " movieLimit = " + intValue2 + " totalPage = " + Latest_Movies.this.totalPages);
                if (Latest_Movies.this.filteredMoviesList != null) {
                    Latest_Movies.this.layout_internet.setVisibility(8);
                    if (bool.booleanValue()) {
                        Latest_Movies.this.refreshSwipe.setRefreshing(false);
                        Latest_Movies.this.mAdapter.replaceItems(Latest_Movies.this.filteredMoviesList);
                        return;
                    }
                    Latest_Movies.this.mAdapter.addItems(Latest_Movies.this.filteredMoviesList);
                    return;
                }
                Latest_Movies.this.refreshSwipe.setRefreshing(true);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Latest_Movies.this.refreshSwipe.setRefreshing(false);
                Log.d("Latest Movies", volleyError + "");
                Latest_Movies.this.movielimit = 30;
                Latest_Movies.this.theRequest(str, false);
                Latest_Movies.this.loading = true;
            }
        }), "latest_movie_list");
        App.getInstance().getRequestQueue().getCache().invalidate(str, true);
        App.getInstance().getRequestQueue().getCache().clear();
    }

    public void onResume() {
        super.onResume();
        if (!isNetworkAvailable()) {
            this.layout_internet.setVisibility(0);
        }
    }
}
