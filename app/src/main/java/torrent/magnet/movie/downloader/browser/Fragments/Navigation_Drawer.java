package torrent.magnet.movie.downloader.browser.Fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.BuildConfig;

import java.util.ArrayList;
import java.util.List;

import torrent.magnet.movie.downloader.browser.Activities.Advanced_SearchActivity;
import torrent.magnet.movie.downloader.browser.Activities.Collection_Activity;
import torrent.magnet.movie.downloader.browser.Adapters.Navigation_Adapter;
import torrent.magnet.movie.downloader.browser.Model.ListItems;
import torrent.magnet.movie.downloader.browser.R;

public class Navigation_Drawer extends Fragment implements Navigation_Adapter.ClickListener {
    LinearLayoutManager LinearLayoutManager;
    Activity activity;
    Navigation_Adapter adapter;

    public ActionBarDrawerToggle mtoogle;

    public List<ListItems> getdata() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = {R.drawable.advanced_search, R.drawable.ic_heart, R.drawable.rate, R.drawable.share};
        String[] stringArray = this.activity.getResources().getStringArray(R.array.navigation);
        int i = 0;
        while (i < stringArray.length && i < 5) {
            ListItems listItems = new ListItems();
            listItems.iconid = iArr[i];
            listItems.title = stringArray[i];
            arrayList.add(listItems);
            i++;
        }
        return arrayList;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.activity_navigation_drawer, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.navigationtitle)).setTypeface(Typeface.createFromAsset(this.activity.getAssets(), "fonts/Cabin-Regular.ttf"));
        ((TextView) inflate.findViewById(R.id.version_no)).append(BuildConfig.VERSION_NAME);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.drawer_list);
        Navigation_Adapter navigation_Adapter = new Navigation_Adapter(this.activity, getdata());
        this.adapter = navigation_Adapter;
        navigation_Adapter.setClickListenere(this);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
        return inflate;
    }

    public void setup(DrawerLayout drawerLayout, Toolbar toolbar) {
        /*C10141 r0 = new ActionBarDrawerToggle(this.activity, drawerLayout, toolbar, R.string.DrawerOpen, R.string.DrawerClose) {
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
        };
        this.mtoogle = r0;
        drawerLayout.setDrawerListener(r0);
        drawerLayout.post(new Runnable() {
            public void run() {
                Navigation_Drawer.this.mtoogle.syncState();
            }
        });*/
    }

    public void ItemClicked(View view, int i) {
        if (i == 0) {
            //Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "hariscoding@gmail.com", (String) null));
            //intent.putExtra("android.intent.extra.SUBJECT", "Movie Downloader");
            //intent.putExtra("android.intent.extra.TEXT", "Hi,");
            //startActivity(Intent.createChooser(intent, "Send Movie Request"));
            startActivity(new Intent(this.activity, Advanced_SearchActivity.class));
        } else if (i == 1) {
           // startActivity(new Intent(this.activity, Advanced_SearchActivity.class));
            startActivity(new Intent(this.activity, Collection_Activity.class));

        } else if (i == 2) {

            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.activity.getPackageName()));
            intent2.addFlags(1208483840);
            try {
                startActivity(intent2);
            } catch (ActivityNotFoundException unused) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + this.activity.getPackageName())));
            }

        } else if (i == 3) {
            Intent intent3 = new Intent("android.intent.action.SEND");
            intent3.setType("text/plain");
            intent3.putExtra("android.intent.extra.SUBJECT", "Share App");
            StringBuilder sb = new StringBuilder();
            sb.append("Hey Guys,\n Get all the latest movies through Movie Downloader. \n");
            sb.append(Uri.parse("http://play.google.com/store/apps/details?id=" + this.activity.getPackageName()));
            intent3.putExtra("android.intent.extra.TEXT", sb.toString());
            startActivity(Intent.createChooser(intent3, "Share via"));

        }
    }
}
