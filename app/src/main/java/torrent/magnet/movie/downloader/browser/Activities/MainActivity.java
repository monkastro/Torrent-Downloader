package torrent.magnet.movie.downloader.browser.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.tapadoo.alerter.Alerter;
import java.util.ArrayList;
import java.util.List;

import torrent.magnet.movie.downloader.browser.Fragments.Latest_Movies;
import torrent.magnet.movie.downloader.browser.Fragments.Navigation_Drawer;
import torrent.magnet.movie.downloader.browser.Fragments.Popular_Movies;
import torrent.magnet.movie.downloader.browser.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TextView title;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        if (!isNetworkAvailable()) {
            showAlert("No Internet!", "Please check your Internet Connection", (View.OnClickListener) null, R.color.RED);
        }

        ((Navigation_Drawer) getSupportFragmentManager().findFragmentById(R.id.mainfragmentdrawer)).setup((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        this.title = (TextView) findViewById(R.id.titletext);
        this.title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.ttf"));
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout = tabLayout2;
        tabLayout2.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Latest_Movies(), "Latest");
        viewPagerAdapter.addFragment(new Popular_Movies(), "Popular");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_search) {
            return super.onOptionsItemSelected(menuItem);
        }
        startActivity(new Intent(this, Search_Activity.class));
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }


        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }

    public void showAlert(String str, String str2, View.OnClickListener onClickListener, int i) {
        Alerter backgroundColorRes = Alerter.create(this).setTitle(str).setText(str2).setBackgroundColorRes(i);
        if (onClickListener != null) {
            backgroundColorRes.setOnClickListener(onClickListener).show();
        } else {
            backgroundColorRes.show();
        }
    }


    public void onDestroy() {

        super.onDestroy();
    }
}
