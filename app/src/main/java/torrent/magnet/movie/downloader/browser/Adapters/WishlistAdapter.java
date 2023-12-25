package torrent.magnet.movie.downloader.browser.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;

import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import torrent.magnet.movie.downloader.browser.Activities.DownloadActivity;
import torrent.magnet.movie.downloader.browser.Model.WishListMovieModel;
import torrent.magnet.movie.downloader.browser.R;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    public Context mContext;
    private List<WishListMovieModel> mModels;

    public WishlistAdapter(Context context, List<WishListMovieModel> list) {
        this.mContext = context;
        this.mModels = list;
    }

    public void resetData(List<WishListMovieModel> list) {
        this.mModels = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_item, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final WishListMovieModel wishListMovieModel = this.mModels.get((getItemCount() - 1) - i);
        if (!wishListMovieModel.getQuality_one().equals("")) {
            viewHolder.avail720.setVisibility(0);
            viewHolder.avail720.setText(wishListMovieModel.getQuality_one());
        }
        if (!wishListMovieModel.getQuality_two().equals("")) {
            viewHolder.avail1080.setVisibility(0);
            viewHolder.avail1080.setText(wishListMovieModel.getQuality_two());
        }
        if (!wishListMovieModel.getQuality_three().equals("")) {
            viewHolder.avail3D.setVisibility(0);
            viewHolder.avail3D.setText(wishListMovieModel.getQuality_three());
        }
        Typeface.createFromAsset(this.mContext.getAssets(), "fonts/QuattrocentoSans-Regular.ttf");
        Typeface createFromAsset = Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Montserrat-Medium.ttf");
        viewHolder.movieTitle.setTypeface(createFromAsset);
        viewHolder.movieTitle.setText(wishListMovieModel.getTitle());
        viewHolder.tvRating.setText(wishListMovieModel.getRating());
        viewHolder.tvRating.setTypeface(createFromAsset);
        if (wishListMovieModel.getImage_url() != null) {
            viewHolder.moviePoster.setVisibility(0);
            Glide.with(this.mContext).load(wishListMovieModel.getImage_url()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).into(viewHolder.moviePoster);
            Glide.with(this.mContext).asBitmap().load(wishListMovieModel.getImage_url()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).into(new SimpleTarget<Bitmap>(Integer.MIN_VALUE, Integer.MIN_VALUE) {
                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    Log.e("WishlistAdapter", "onResourceReady ");
                    if (viewHolder.viewColor != null) {
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette palette) {
                                viewHolder.viewColor.setBackgroundColor(palette.getDarkVibrantColor(WishlistAdapter.this.mContext.getResources().getColor(R.color.grey700)));
                            }
                        });
                    }
                }
            });
        } else {
            Glide.with(this.mContext).clear((View) viewHolder.moviePoster);
            viewHolder.moviePoster.setVisibility(8);
        }
        viewHolder.f101cv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(WishlistAdapter.this.mContext, DownloadActivity.class);
                intent.setFlags(268435456);
                Pair.create(viewHolder.moviePoster, "poster");
                //Pair.create(viewHolder.movieTitle, SettingsJsonConstants.PROMPT_TITLE_KEY);
                Pair.create(viewHolder.tvRating, "rating");
                intent.putExtra("movie_json", wishListMovieModel.getJson_string());
                WishlistAdapter.this.mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.mModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView avail1080;
        TextView avail3D;
        TextView avail720;


        CardView f101cv;
        ImageView moviePoster;
        TextView movieTitle;
        TextView movieYear;
        ImageView overflow;
        RelativeLayout relativeLayout;
        TextView tvRating;
        View viewColor;

        ViewHolder(View view) {
            super(view);
            this.movieTitle = (TextView) view.findViewById(R.id.m_title);
            this.moviePoster = (ImageView) view.findViewById(R.id.m_poster);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.name_relative);
            this.f101cv = (CardView) view.findViewById(R.id.cv_wish);
            this.movieYear = (TextView) view.findViewById(R.id.movie_year);
            this.tvRating = (TextView) view.findViewById(R.id.m_rate);
            this.viewColor = view.findViewById(R.id.m_color);
            this.avail3D = (TextView) view.findViewById(R.id.avail_3d);
            this.avail720 = (TextView) view.findViewById(R.id.avail_720p);
            this.avail1080 = (TextView) view.findViewById(R.id.avail_1080p);
        }
    }
}
