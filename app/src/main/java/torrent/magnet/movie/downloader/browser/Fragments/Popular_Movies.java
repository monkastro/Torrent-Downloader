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

public class Popular_Movies extends Fragment {
    final String TAG = "Popular Movies";
    Activity activity;
    TextView error_text;
    Gson gson = new Gson();
    RelativeLayout layout_internet;
    ListModel listModel;
    boolean loading = true;

    public Browse_Adapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<Movie> movieList;
    int movielimit = 20;
    int page = 1;
    int pastVisiblesItems;
    SwipeRefreshLayout refreshSwipe;
    int totalItemCount;
    int totalPages = 1;

    public String url = ("https://yts.ag/api/v2/list_movies.json?sort_by=download_count&limit=" + this.movielimit);
    int visibleItemCount;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_popular_movies, viewGroup, false);
        this.layout_internet = (RelativeLayout) inflate.findViewById(R.id.pop_layout_internet);
        this.refreshSwipe = (SwipeRefreshLayout) inflate.findViewById(R.id.pop_swiperefresh);
        this.error_text = (TextView) inflate.findViewById(R.id.pop_textinternet);
        this.movieList = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.pop_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        this.mLayoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        theRequest(this.url, true);
        this.refreshSwipe.setRefreshing(true);
        Browse_Adapter browse_Adapter = new Browse_Adapter(this.movieList, this.activity);
        this.mAdapter = browse_Adapter;
        recyclerView.setAdapter(browse_Adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                Popular_Movies popular_Movies = Popular_Movies.this;
                popular_Movies.visibleItemCount = popular_Movies.mLayoutManager.getChildCount();
                Popular_Movies popular_Movies2 = Popular_Movies.this;
                popular_Movies2.totalItemCount = popular_Movies2.mLayoutManager.getItemCount();
                Popular_Movies popular_Movies3 = Popular_Movies.this;
                popular_Movies3.pastVisiblesItems = popular_Movies3.mLayoutManager.findFirstVisibleItemPosition();
                if (Popular_Movies.this.loading && Popular_Movies.this.visibleItemCount + Popular_Movies.this.pastVisiblesItems >= Popular_Movies.this.totalItemCount) {
                    Popular_Movies.this.loading = false;
                    Log.e("TAGl", "inside loading tot & page = " + Popular_Movies.this.totalPages + " " + Popular_Movies.this.page);
                    if (Popular_Movies.this.page < Popular_Movies.this.totalPages) {
                        Log.e("TAGl", "inside page<=totalPage");
                        Log.e("TAGl", "last inside tot & page = " + Popular_Movies.this.totalPages + " " + Popular_Movies.this.page);
                        Popular_Movies popular_Movies4 = Popular_Movies.this;
                        popular_Movies4.page = popular_Movies4.page + 1;
                        Popular_Movies popular_Movies5 = Popular_Movies.this;
                        popular_Movies5.theRequest(Popular_Movies.this.url + "&page=" + Popular_Movies.this.page, false);
                    }
                }
            }
        });
        ((Button) inflate.findViewById(R.id.btn_popular_refresh)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Popular_Movies popular_Movies = Popular_Movies.this;
                popular_Movies.theRequest(popular_Movies.url, false);
            }
        });
        this.refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Log.i("TAG", "onRefresh called from SwipeRefreshLayout");
                Popular_Movies popular_Movies = Popular_Movies.this;
                popular_Movies.theRequest(popular_Movies.url, true);
                Popular_Movies.this.refreshSwipe.setRefreshing(true);
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
                Log.d("Response", jSONObject.toString());
                Popular_Movies.this.refreshSwipe.setRefreshing(false);
                Popular_Movies popular_Movies = Popular_Movies.this;
                popular_Movies.listModel = (ListModel) popular_Movies.gson.fromJson(jSONObject.toString(), ListModel.class);
                Popular_Movies.this.loading = true;
                Popular_Movies popular_Movies2 = Popular_Movies.this;
                popular_Movies2.movieList = popular_Movies2.listModel.getData().getMovies();
                int intValue = Popular_Movies.this.listModel.getData().getMovieCount().intValue();
                int intValue2 = Popular_Movies.this.listModel.getData().getLimit().intValue();
                Popular_Movies.this.totalPages = intValue / intValue2;
                if (intValue % intValue2 != 0) {
                    Popular_Movies.this.totalPages++;
                }
                Log.e("Popular Movies", "movieCount = " + intValue + " movieLimit = " + intValue2 + " totalPage = " + Popular_Movies.this.totalPages);
                if (Popular_Movies.this.movieList != null) {
                    Popular_Movies.this.layout_internet.setVisibility(8);
                    if (bool.booleanValue()) {
                        Popular_Movies.this.refreshSwipe.setRefreshing(false);
                        Popular_Movies.this.mAdapter.replaceItems(Popular_Movies.this.movieList);
                        return;
                    }
                    Popular_Movies.this.mAdapter.addItems(Popular_Movies.this.movieList);
                    return;
                }
                Popular_Movies.this.refreshSwipe.setRefreshing(true);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Popular_Movies.this.refreshSwipe.setRefreshing(true);
                Log.d("Popular Movies", volleyError + "");
                Popular_Movies.this.movielimit = 30;
                Popular_Movies.this.theRequest(str, false);
                Popular_Movies.this.loading = true;
            }
        }), "pop_movie_list");
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
