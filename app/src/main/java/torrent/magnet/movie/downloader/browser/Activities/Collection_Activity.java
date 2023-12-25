package torrent.magnet.movie.downloader.browser.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import torrent.magnet.movie.downloader.browser.Adapters.WishlistAdapter;
import torrent.magnet.movie.downloader.browser.Model.WishListMovieModel;
import torrent.magnet.movie.downloader.browser.R;

public class Collection_Activity extends AppCompatActivity {
    WishlistAdapter adapter;
    DatabaseManager dataBaseManager;
    RelativeLayout emptyWishlayout;
    RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_collection);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ((TextView) findViewById(R.id.title_collection)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.ttf"));
        this.dataBaseManager = new DatabaseManager(this);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view_w);
        this.emptyWishlayout = (RelativeLayout) findViewById(R.id.empty_wish_layout);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setHasFixedSize(true);
        WishlistAdapter wishlistAdapter = new WishlistAdapter(this, getMovieData());
        this.adapter = wishlistAdapter;
        this.recyclerView.setAdapter(wishlistAdapter);
    }

    private List<WishListMovieModel> getMovieData() {
        return this.dataBaseManager.getWishListMovies();
    }

    public void onResume() {
        super.onResume();
        if (this.recyclerView.getAdapter() != null) {
            Log.e("TAG", "recycle view has adapter");
            this.adapter.resetData(getMovieData());
            if (getMovieData().size() == 0) {
                this.emptyWishlayout.setVisibility(0);
            } else {
                this.emptyWishlayout.setVisibility(8);
            }
        }
    }
}
