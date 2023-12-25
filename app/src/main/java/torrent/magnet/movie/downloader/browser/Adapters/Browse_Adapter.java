package torrent.magnet.movie.downloader.browser.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
;
import java.util.List;

import torrent.magnet.movie.downloader.browser.Activities.DetailActivity;
import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.R;

public class Browse_Adapter extends RecyclerView.Adapter<Browse_Adapter.MyViewHolder> {
    Context context;
    private List<Movie> moviesList;

    public Browse_Adapter(List<Movie> list, Context context2) {
        this.moviesList = list;
        this.context = context2;
    }

    public void replaceItems(List<Movie> list) {
        this.moviesList = list;
        notifyDataSetChanged();
    }

    public void addItems(List<Movie> list) {
        this.moviesList.addAll(list);
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final Movie movie = this.moviesList.get(i);
        myViewHolder.title.setText(movie.getTitle());
        myViewHolder.year.setText(String.valueOf(movie.getYear()));
        if (movie.getMediumCoverImage() != null) {
            Glide.with(this.context).load(movie.getMediumCoverImage()).apply(RequestOptions.centerInsideTransform().placeholder((int) R.drawable.ic_photo_black_32dp)).into(myViewHolder.poster);
        } else {
            Glide.with(this.context).clear((View) myViewHolder.poster);
        }
        myViewHolder.f99cv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String json = new Gson().toJson((Object) movie);
                Intent intent = new Intent(Browse_Adapter.this.context, DetailActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("movie_json", json);
                Browse_Adapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.moviesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        CardView f99cv;
        ImageView poster;
        RelativeLayout relativeLayout;
        TextView title;
        TextView year;

        MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.movie_title);
            this.year = (TextView) view.findViewById(R.id.movie_year);
            this.poster = (ImageView) view.findViewById(R.id.poster);
            this.f99cv = (CardView) view.findViewById(R.id.card_view);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.name_relative);
        }
    }
}
