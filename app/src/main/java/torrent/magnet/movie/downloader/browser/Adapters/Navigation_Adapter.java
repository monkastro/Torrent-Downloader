package torrent.magnet.movie.downloader.browser.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import torrent.magnet.movie.downloader.browser.Model.ListItems;
import torrent.magnet.movie.downloader.browser.R;

public class Navigation_Adapter extends RecyclerView.Adapter<Navigation_Adapter.MyViewHolder> {

    public ClickListener clickListener;

    public Context context;
    List<ListItems> data = Collections.emptyList();
    private LayoutInflater layoutinflater;

    public interface ClickListener {
        void ItemClicked(View view, int i);
    }

    public Navigation_Adapter(Context context2, List<ListItems> list) {
        this.context = context2;
        this.layoutinflater = LayoutInflater.from(context2);
        this.data = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.layoutinflater.inflate(R.layout.navigation_row, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        ListItems listItems = this.data.get(i);
        myViewHolder.title.setText(listItems.title);
        Glide.with(this.context).load(Integer.valueOf(listItems.iconid)).into(myViewHolder.icon);
    }

    public void setClickListenere(ClickListener clickListener2) {
        this.clickListener = clickListener2;
    }

    public int getItemCount() {
        return this.data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title_list);
            this.icon = (ImageView) view.findViewById(R.id.image_list);
            this.title.setTypeface(Typeface.createFromAsset(Navigation_Adapter.this.context.getAssets(), "fonts/Cabin-Regular.ttf"));
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (Navigation_Adapter.this.clickListener != null) {
                Navigation_Adapter.this.clickListener.ItemClicked(view, getAdapterPosition());
            }
        }
    }
}
