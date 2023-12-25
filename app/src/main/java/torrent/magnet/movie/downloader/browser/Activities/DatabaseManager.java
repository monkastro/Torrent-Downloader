package torrent.magnet.movie.downloader.browser.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import torrent.magnet.movie.downloader.browser.Model.Movie;
import torrent.magnet.movie.downloader.browser.Model.WishListMovieModel;

public class DatabaseManager extends SQLiteOpenHelper {
    static final String Added_On = "Added_On";
    private static final String DATABASE_MOVIES = "Movies.db";
    private static final int DATABASE_VERSION = 2;
    private static final String ImageUrl = "ImageUrl";
    private static final String JsonString = "JsonString";
    private static final String Movie_Id = "Movie_Id";
    private static final String Provider = "Provider";
    private static final String Qualityone = "Qualityone";
    private static final String Qualitythree = "Qualitythree";
    private static final String Qualitytwo = "Qualitytwo";
    private static final String Rating = "Rating";
    private static final String TABLE_MOVIES = "Movies_table";
    private static final String Title = "Title";

    public DatabaseManager(Context context) {
        super(context, DATABASE_MOVIES, (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Movies_table ( Movie_Id TEXT, Provider TEXT, Rating TEXT, Title TEXT, Qualityone TEXT, Qualitytwo TEXT ,Qualitythree TEXT ,ImageUrl TEXT, JsonString TEXT )");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Movies_table");
        onCreate(sQLiteDatabase);
    }


    public void insertMovie(Movie movie) {
        String str;
        String str2;
        String str3 = "";
        if (movie.getTorrents() != null) {
            String str4 = str3;
            str2 = str4;
            str = str2;
            for (int i = 0; i < movie.getTorrents().size(); i++) {
                if (i == 0) {
                    str4 = movie.getTorrents().get(i).getQuality();
                } else if (i == 1) {
                    str2 = movie.getTorrents().get(i).getQuality();
                } else if (i == 2) {
                    str = movie.getTorrents().get(i).getQuality();
                }
                Log.e("TAG", str3 + movie.getTorrents().get(i).getQuality());
            }
            str3 = str4;
        } else {
            str2 = str3;
            str = str2;
        }
        String json = new Gson().toJson((Object) movie);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Movie_Id, movie.getId());
        contentValues.put(Provider, "yify");
        contentValues.put(Rating, movie.getRating());
        contentValues.put(Title, movie.getTitle());
        contentValues.put(Qualityone, str3);
        contentValues.put(Qualitytwo, str2);
        contentValues.put(Qualitythree, str);
        contentValues.put(ImageUrl, movie.getMediumCoverImage());
        contentValues.put(JsonString, json);
        writableDatabase.insert(TABLE_MOVIES, (String) null, contentValues);
        writableDatabase.close();
    }

    public List<WishListMovieModel> getWishListMovies() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("Select * from Movies_table", (String[]) null);
        if (rawQuery.moveToFirst()) {
            do {
                int columnIndex = rawQuery.getColumnIndex(Movie_Id);
                int columnIndex2 = rawQuery.getColumnIndex(Provider);
                int columnIndex3 = rawQuery.getColumnIndex(Rating);
                int columnIndex4 = rawQuery.getColumnIndex(Title);
                int columnIndex5 = rawQuery.getColumnIndex(Qualityone);
                int columnIndex6 = rawQuery.getColumnIndex(Qualitytwo);
                int columnIndex7 = rawQuery.getColumnIndex(Qualitythree);
                int columnIndex8 = rawQuery.getColumnIndex(ImageUrl);
                int columnIndex9 = rawQuery.getColumnIndex(JsonString);
                WishListMovieModel wishListMovieModel = new WishListMovieModel();
                wishListMovieModel.setMovie_id(rawQuery.getString(columnIndex));
                wishListMovieModel.setProvider(rawQuery.getString(columnIndex2));
                wishListMovieModel.setRating(rawQuery.getString(columnIndex3));
                wishListMovieModel.setTitle(rawQuery.getString(columnIndex4));
                wishListMovieModel.setQuality_one(rawQuery.getString(columnIndex5));
                wishListMovieModel.setQuality_two(rawQuery.getString(columnIndex6));
                wishListMovieModel.setQuality_three(rawQuery.getString(columnIndex7));
                wishListMovieModel.setImage_url(rawQuery.getString(columnIndex8));
                wishListMovieModel.setJson_string(rawQuery.getString(columnIndex9));
                arrayList.add(wishListMovieModel);
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        writableDatabase.close();
        return arrayList;
    }


    public void deleteMovie(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("Delete from Movies_table where Movie_Id = " + str);
        writableDatabase.close();
    }


    public boolean movieExists(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("Select Movie_Id from Movies_table where Movie_Id = " + str, (String[]) null);
        if (rawQuery.getCount() > 0) {
            rawQuery.close();
            writableDatabase.close();
            return true;
        }
        rawQuery.close();
        writableDatabase.close();
        return false;
    }
}
