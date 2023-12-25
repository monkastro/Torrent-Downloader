package torrent.magnet.movie.downloader.browser.Activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import torrent.magnet.movie.downloader.browser.Adapters.Browse_Adapter;
import torrent.magnet.movie.downloader.browser.AppController.App;
import torrent.magnet.movie.downloader.browser.Model.ListModel;
import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.R;


public class Advanced_SearchActivity extends AppCompatActivity {
    private static final String Search_Url = "https://yts.am/api/v2/list_movies.json";

    public Browse_Adapter adapterYts;


    private Context f96c;

    public RelativeLayout containerError;

    public String genreString = "";

    public final Gson gson = new Gson();

    public ListModel listMode;

    public boolean loading = true;

    public List<Movie> mModels;

    public int page = 1;

    public int pastVisiblesItems;
    String popSortString = "";

    public String qualityString = "";

    public String queryString = "";

    public String ratingString = "";

    public RecyclerView recyclerView;

    public SwipeRefreshLayout refreshSwipe;
    private Button searchBtn;
    private EditText searchEdt;

    public int searchProvider = 0;

    public String sortString = "latest";
    private Spinner spinnerGenre;
    private Spinner spinnerQuality;
    private Spinner spinnerRating;
    private Spinner spinnerSort;

    public int totalItemCount;

    public int totalPages = 1;

    public TextView tvErrorMsg;

    public int visibleItemCount;
    private LinearLayout ytsQuaContainer;
    private LinearLayout ytsRatingContainer;
    private LinearLayout ytsSortContainer;

    static /* synthetic */ int access$108(Advanced_SearchActivity advanced_SearchActivity) {
        int i = advanced_SearchActivity.page;
        advanced_SearchActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$908(Advanced_SearchActivity advanced_SearchActivity) {
        int i = advanced_SearchActivity.totalPages;
        advanced_SearchActivity.totalPages = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_advancedsearch);
        this.f96c = this;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.spinnerQuality = (Spinner) findViewById(R.id.spinner_quality);
        this.spinnerGenre = (Spinner) findViewById(R.id.spinner_genre);
        this.spinnerRating = (Spinner) findViewById(R.id.spinner_rating);
        this.spinnerSort = (Spinner) findViewById(R.id.spinner_sort_by);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);
        this.refreshSwipe = (SwipeRefreshLayout) findViewById(R.id.swiperefreshSearch);
        this.searchEdt = (EditText) findViewById(R.id.search_edt);
        this.searchBtn = (Button) findViewById(R.id.search_btn);
        this.tvErrorMsg = (TextView) findViewById(R.id.tag_msg);
        this.containerError = (RelativeLayout) findViewById(R.id.container_error);
        this.ytsQuaContainer = (LinearLayout) findViewById(R.id.yts_qua_container);
        this.ytsRatingContainer = (LinearLayout) findViewById(R.id.yts_rating_container);
        this.ytsSortContainer = (LinearLayout) findViewById(R.id.yts_sort_container);
        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.e("TAG", "fab click searchProvider" + Advanced_SearchActivity.this.searchProvider);
                ((InputMethodManager) Advanced_SearchActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                int unused = Advanced_SearchActivity.this.page = 1;
                Advanced_SearchActivity advanced_SearchActivity = Advanced_SearchActivity.this;
                advanced_SearchActivity.searchRequest(advanced_SearchActivity.generateSearchUrlYTS(), true);
            }
        });
        this.searchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((InputMethodManager) Advanced_SearchActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                int unused = Advanced_SearchActivity.this.page = 1;
                Advanced_SearchActivity advanced_SearchActivity = Advanced_SearchActivity.this;
                advanced_SearchActivity.searchRequest(advanced_SearchActivity.generateSearchUrlYTS(), true);
            }
        });
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, R.array.quality, 17367048);
        createFromResource.setDropDownViewResource(17367048);
        this.spinnerQuality.setAdapter(createFromResource);
        this.searchEdt.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                String unused = Advanced_SearchActivity.this.queryString = String.valueOf(editable);
                Log.e("TAG", "afterTextChanged string " + Advanced_SearchActivity.this.queryString);
            }
        });
        this.searchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3) {
                    return false;
                }
                int unused = Advanced_SearchActivity.this.page = 1;
                Advanced_SearchActivity advanced_SearchActivity = Advanced_SearchActivity.this;
                advanced_SearchActivity.searchRequest(advanced_SearchActivity.generateSearchUrlYTS(), true);
                return true;
            }
        });
        this.spinnerQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    String unused = Advanced_SearchActivity.this.qualityString = "";
                } else if (i == 1) {
                    String unused2 = Advanced_SearchActivity.this.qualityString = "720p";
                } else if (i == 2) {
                    String unused3 = Advanced_SearchActivity.this.qualityString = "1080p";
                } else if (i == 3) {
                    String unused4 = Advanced_SearchActivity.this.qualityString = "3D";
                }
                Log.e("TAG", "onItemClick position " + i + " qualityString = " + Advanced_SearchActivity.this.qualityString);
            }
        });
        ArrayAdapter<CharSequence> createFromResource2 = ArrayAdapter.createFromResource(this, R.array.genre, 17367048);
        createFromResource2.setDropDownViewResource(17367049);
        this.spinnerGenre.setAdapter(createFromResource2);
        this.spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                switch (i) {
                    case 0:
                        String unused = Advanced_SearchActivity.this.genreString = "";
                        break;
                    case 1:
                        String unused2 = Advanced_SearchActivity.this.genreString = "action";
                        break;
                    case 2:
                        String unused3 = Advanced_SearchActivity.this.genreString = "animation";
                        break;
                    case 3:
                        String unused4 = Advanced_SearchActivity.this.genreString = "comedy";
                        break;
                    case 4:
                        String unused5 = Advanced_SearchActivity.this.genreString = "documentary";
                        break;
                    case 5:
                        String unused6 = Advanced_SearchActivity.this.genreString = "family";
                        break;
                    case 6:
                        String unused7 = Advanced_SearchActivity.this.genreString = "film-noir";
                        break;
                    case 7:
                        String unused8 = Advanced_SearchActivity.this.genreString = "horror";
                        break;
                    case 8:
                        String unused9 = Advanced_SearchActivity.this.genreString = "musical";
                        break;
                    case 9:
                        String unused10 = Advanced_SearchActivity.this.genreString = "romance";
                        break;
                    case 10:
                        String unused11 = Advanced_SearchActivity.this.genreString = "sport";
                        break;
                    case 11:
                        String unused12 = Advanced_SearchActivity.this.genreString = "war";
                        break;
                    case 12:
                        String unused13 = Advanced_SearchActivity.this.genreString = "adventure";
                        break;
                    case 13:
                        String unused14 = Advanced_SearchActivity.this.genreString = "biography";
                        break;
                    case 14:
                        String unused15 = Advanced_SearchActivity.this.genreString = "crime";
                        break;
                    case 15:
                        String unused16 = Advanced_SearchActivity.this.genreString = "drama";
                        break;
                    case 16:
                        String unused17 = Advanced_SearchActivity.this.genreString = "fantasy";
                        break;
                    case 17:
                        String unused18 = Advanced_SearchActivity.this.genreString = "history";
                        break;
                    case 18:
                        String unused19 = Advanced_SearchActivity.this.genreString = "music";
                        break;
                    case 19:
                        String unused20 = Advanced_SearchActivity.this.genreString = "mystery";
                        break;
                    case 20:
                        String unused21 = Advanced_SearchActivity.this.genreString = "Sci-Fi";
                        break;
                    case 21:
                        String unused22 = Advanced_SearchActivity.this.genreString = "thriller";
                        break;
                    case 22:
                        String unused23 = Advanced_SearchActivity.this.genreString = "western";
                        break;
                }
                Log.e("TAG", "onItemClick position " + i + " genreString = " + Advanced_SearchActivity.this.genreString);
            }
        });
        ArrayAdapter<CharSequence> createFromResource3 = ArrayAdapter.createFromResource(this, R.array.rating, 17367048);
        createFromResource3.setDropDownViewResource(17367049);
        this.spinnerRating.setAdapter(createFromResource3);
        this.spinnerRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                switch (i) {
                    case 0:
                        String unused = Advanced_SearchActivity.this.ratingString = "";
                        break;
                    case 1:
                        String unused2 = Advanced_SearchActivity.this.ratingString = "9";
                        break;
                    case 2:
                        String unused3 = Advanced_SearchActivity.this.ratingString = "8";
                        break;
                    case 3:
                        String unused4 = Advanced_SearchActivity.this.ratingString = "7";
                        break;
                    case 4:
                        String unused5 = Advanced_SearchActivity.this.ratingString = "6";
                        break;
                    case 5:
                        String unused6 = Advanced_SearchActivity.this.ratingString = "5";
                        break;
                    case 6:
                        String unused7 = Advanced_SearchActivity.this.ratingString = "4";
                        break;
                    case 7:
                        String unused8 = Advanced_SearchActivity.this.ratingString = "3";
                        break;
                    case 8:
                        String unused9 = Advanced_SearchActivity.this.ratingString = "2";
                        break;
                    case 9:
                        String unused10 = Advanced_SearchActivity.this.ratingString = "1";
                        break;
                }
                Log.e("TAG", "onItemClick position " + i + " ratingString = " + Advanced_SearchActivity.this.ratingString);
            }
        });
        ArrayAdapter<CharSequence> createFromResource4 = ArrayAdapter.createFromResource(this, R.array.sort, 17367048);
        createFromResource4.setDropDownViewResource(17367049);
        this.spinnerSort.setAdapter(createFromResource4);
        this.spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                switch (i) {
                    case 0:
                        String unused = Advanced_SearchActivity.this.sortString = "date_added";
                        break;
                    case 1:
                        String unused2 = Advanced_SearchActivity.this.sortString = "seeds";
                        break;
                    case 2:
                        String unused3 = Advanced_SearchActivity.this.sortString = "peers";
                        break;
                    case 3:
                        String unused4 = Advanced_SearchActivity.this.sortString = "year";
                        break;
                    case 4:
                        String unused5 = Advanced_SearchActivity.this.sortString = "rating";
                        break;
                    case 5:
                        String unused6 = Advanced_SearchActivity.this.sortString = "like_count";
                        break;
                    case 6:
//                        String unused7 = Advanced_SearchActivity.this.sortString = SettingsJsonConstants.PROMPT_TITLE_KEY;
                        break;
                    case 7:
                        String unused8 = Advanced_SearchActivity.this.sortString = "download_count";
                        break;
                }
                Log.e("TAG", "onItemClick position " + i + " sortString = " + Advanced_SearchActivity.this.sortString);
            }
        });
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                Log.e("TAGl", "inside onScolled totpages = " + Advanced_SearchActivity.this.totalPages);
                int unused = Advanced_SearchActivity.this.visibleItemCount = gridLayoutManager.getChildCount();
                int unused2 = Advanced_SearchActivity.this.totalItemCount = gridLayoutManager.getItemCount();
                int unused3 = Advanced_SearchActivity.this.pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                if (Advanced_SearchActivity.this.loading && Advanced_SearchActivity.this.visibleItemCount + Advanced_SearchActivity.this.pastVisiblesItems >= Advanced_SearchActivity.this.totalItemCount) {
                    boolean unused4 = Advanced_SearchActivity.this.loading = false;
                    Log.e("TAGl", "inside loading tot & page = " + Advanced_SearchActivity.this.totalPages + " " + Advanced_SearchActivity.this.page);
                    if (Advanced_SearchActivity.this.page < Advanced_SearchActivity.this.totalPages) {
                        Log.e("TAGl", "inside page<=totalPage");
                        Log.e("TAGl", "last inside tot & page = " + Advanced_SearchActivity.this.totalPages + " " + Advanced_SearchActivity.this.page);
                        Advanced_SearchActivity.access$108(Advanced_SearchActivity.this);
                        Advanced_SearchActivity advanced_SearchActivity = Advanced_SearchActivity.this;
                        advanced_SearchActivity.searchRequest(Advanced_SearchActivity.this.generateSearchUrlYTS() + "&page=" + Advanced_SearchActivity.this.page, false);
                    }
                }
            }
        });
        this.mModels = new ArrayList();
        Browse_Adapter browse_Adapter = new Browse_Adapter(this.mModels, this.f96c);
        this.adapterYts = browse_Adapter;
        this.recyclerView.setAdapter(browse_Adapter);
    }

    public void setupYtsProviderView() {
        this.ytsQuaContainer.setVisibility(0);
        this.ytsRatingContainer.setVisibility(0);
        this.ytsSortContainer.setVisibility(0);
        this.recyclerView.setAdapter(this.adapterYts);
    }

    /* access modifiers changed from: private */
    public String generateSearchUrlYTS() {
        String str = "?quality=" + this.qualityString + "&minimum_rating=" + this.ratingString + "&genre=" + this.genreString + "&sort_by=" + this.sortString + "&query_term=" + this.queryString;
        Log.e("TAG", "paramsString = " + str);
        String str2 = Search_Url + str;
        Log.e("TAG", "final url  = " + str2);
        return str2;
    }

    /* access modifiers changed from: private */
    public void searchRequest(String str, final boolean z) {
        Log.e("TAG", "searchRequest final url  = " + str);
        this.searchEdt.clearFocus();
        startLoading();
        App.getInstance().addToRequestQueue(new JsonObjectRequest(0, str, (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                Log.e("TAG", jSONObject.toString());
                Advanced_SearchActivity.this.stopLoading();
                Advanced_SearchActivity advanced_SearchActivity = Advanced_SearchActivity.this;
                ListModel unused = advanced_SearchActivity.listMode = (ListModel) advanced_SearchActivity.gson.fromJson(jSONObject.toString(), ListModel.class);
                Log.e("tag", "response " + Advanced_SearchActivity.this.listMode.getStatus());
                boolean unused2 = Advanced_SearchActivity.this.loading = true;
                Advanced_SearchActivity advanced_SearchActivity2 = Advanced_SearchActivity.this;
                List unused3 = advanced_SearchActivity2.mModels = advanced_SearchActivity2.listMode.getData().getMovies();
                if (Advanced_SearchActivity.this.mModels == null) {
                    Advanced_SearchActivity.this.tvErrorMsg.setText("No Result Found !!");
                    Advanced_SearchActivity.this.containerError.setVisibility(0);
                    Advanced_SearchActivity.this.recyclerView.setVisibility(4);
                } else {
                    Advanced_SearchActivity.this.containerError.setVisibility(4);
                    Advanced_SearchActivity.this.recyclerView.setVisibility(0);
                }
                int intValue = Advanced_SearchActivity.this.listMode.getData().getMovieCount().intValue();
                int intValue2 = Advanced_SearchActivity.this.listMode.getData().getLimit().intValue();
                int unused4 = Advanced_SearchActivity.this.totalPages = intValue / intValue2;
                if (intValue % intValue2 != 0) {
                    Advanced_SearchActivity.access$908(Advanced_SearchActivity.this);
                }
                Log.e("MainActivity", "movieCount = " + intValue + " movieLimit = " + intValue2 + " totalPage = " + Advanced_SearchActivity.this.totalPages);
                Advanced_SearchActivity.this.refreshSwipe.setRefreshing(false);
                if (Advanced_SearchActivity.this.mModels != null) {
                    if (z) {
                        Advanced_SearchActivity.this.adapterYts.replaceItems(Advanced_SearchActivity.this.mModels);
                    } else {
                        Advanced_SearchActivity.this.adapterYts.addItems(Advanced_SearchActivity.this.mModels);
                    }
                    Advanced_SearchActivity.this.recyclerView.setVisibility(0);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                //VolleyLog.m10d("TAG", "Error: " + volleyError.getMessage());
                boolean unused = Advanced_SearchActivity.this.loading = true;
                Advanced_SearchActivity.this.stopLoading();
                Advanced_SearchActivity.this.recyclerView.setVisibility(4);
                Advanced_SearchActivity.this.tvErrorMsg.setText("No Movies Found !");
                Advanced_SearchActivity.this.containerError.setVisibility(0);
            }
        }), "movie_list_search");
        App.getInstance().getRequestQueue().getCache().invalidate(str, true);
    }

    private void startLoading() {
        this.containerError.setVisibility(4);
        this.refreshSwipe.setEnabled(true);
        this.refreshSwipe.setRefreshing(true);
    }


    public void stopLoading() {
        this.refreshSwipe.setRefreshing(false);
        this.refreshSwipe.setEnabled(false);
    }
}
