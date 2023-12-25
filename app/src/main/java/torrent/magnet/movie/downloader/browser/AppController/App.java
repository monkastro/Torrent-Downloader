package torrent.magnet.movie.downloader.browser.AppController;

import android.app.AlertDialog;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



public class App extends Application {
    public static final String TAG = App.class.getSimpleName();
    private static App mInstance;


    Context f102c;
    private RequestQueue mRequestQueue;

    public static synchronized App getInstance() {
        App appController;
        synchronized (App.class) {
            appController = mInstance;
        }
        return appController;
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;

        this.f102c = this;
    }

    public void openMagneturi(String str, final Context context) {
        if (str.startsWith("magnet:")) {
            Log.e("TAG", "url starts with magnet");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                context.startActivity(intent);
                Log.e("TAG", "yes act to handle");
                return;
            }
            Log.e("TAG", "No act to handle");
            new AlertDialog.Builder(context).setTitle("No Torrent Downloader Found !").setMessage("No torrent downloader found to download this movie.\n\nWould you like to download one ?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=torrent.downloader.manager"));
                    intent.addFlags(1208483840);
                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Toast.makeText(context, "install a torrent app from playstore to download this movies", 0).show();
                    }
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).show();
            return;
        }
        Log.e("TAG", "url does not starts with magnet");
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return this.mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String str) {
        if (TextUtils.isEmpty(str)) {
            str = TAG;
        }
        request.setTag(str);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object obj) {
        RequestQueue requestQueue = this.mRequestQueue;
        if (requestQueue != null) {
            requestQueue.cancelAll(obj);
        }
    }
}
