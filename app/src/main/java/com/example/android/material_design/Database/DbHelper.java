package com.example.android.material_design.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import android.database.DatabaseUtils;
import android.widget.Toast;

import com.example.android.material_design.Movie;

/**
 * Created by Ekta on 01-07-2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    Cursor cursor;

    private static final int DATABASE_VERSION = 6;
    public static final String TABLE_MOVIES = "moviestable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";

    private static final String DATABASE_NAME = "movies.db";

    public static final  String id="movieId";
    public static final String  TITLE  ="title";
    public static final String   RELEASEDATE=   "releaseDateTheater";
    public static final String AUDIENCESCORE ="audienceScore";
    public static final String OVERVIEW = "overview";
    public static final String URLTHUMNAIL ="urlThumbnail";
    public static final String URLSELF ="urlSelf";
    public static final String URLCASST ="urlCast";
    public static final String URLREVIEWS ="urlReviews";
    public static final String URLSIMILAR ="urlSimilar";

    SQLiteDatabase db;
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            +TABLE_MOVIES + "( " +COLUMN_ID
            + " integer primary key ,"
            + "title"
            +  " text not null,"
            +OVERVIEW
            +  " text not null,"

            +RELEASEDATE
            + " text not null,"
            +URLSELF
            +  " text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

   /* public ArrayList<Movie> getAllCotacts()
    {
        ArrayList<Movie> array_list = new ArrayList<Movie>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from moviesDb", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex()));
            res.moveToNext();
        }
        return array_list;
    }
    */

   public boolean insertContact  (int id,String title,String overview,String releaseDate,String urlSelf)
   {
Boolean isInDb= isInDatabase(id);
       if(!isInDb) {
           db = this.getWritableDatabase();
           ContentValues contentValues = new ContentValues();
           contentValues.put(COLUMN_ID, id);
           contentValues.put(RELEASEDATE, releaseDate);
           contentValues.put(OVERVIEW, overview);
           contentValues.put("title", title);
           contentValues.put(URLSELF, urlSelf);

           Log.d("database", "inserted");

           db.insert(TABLE_MOVIES, null, contentValues);
       }
       return true;
   }
    public boolean isInDatabase(int id)
    {
        SQLiteDatabase     db= this.getReadableDatabase();
        Cursor c=  db.rawQuery( "select "+ COLUMN_ID+" from " + TABLE_MOVIES +" where " +COLUMN_ID +" = " + id , null );
        c.moveToFirst();
        if(c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;


    }

    public ArrayList<Movie> getAllComments() {
        ArrayList<Movie> movies = new ArrayList<Movie>();


SQLiteDatabase     db= this.getReadableDatabase();
        Cursor c=  db.rawQuery( "select * from " + TABLE_MOVIES, null );
        c.moveToFirst();

        if(c.moveToFirst()){
            do{

                Movie movie = new Movie(
                        c.getInt(c.getColumnIndex(COLUMN_ID)),
                      //  c.getString(c.getColumnIndex(IMAGE)),
                        c.getString(c.getColumnIndex(TITLE)),
                        c.getString(c.getColumnIndex(OVERVIEW)),
//                        c.getString(c.getColumnIndex(TAGLINE)),
                        c.getString(c.getColumnIndex(RELEASEDATE)),
                        c.getString(c.getColumnIndex(URLSELF))
                       // c.getFloat(c.getColumnIndex(RATING))
             //   movie.setDbId(Integer.parseInt(cursor.getLong(0)));
               // movie.setTitle(cursor.getString(1));
                //inventory.setItemQuant(Integer.parseInt(cursor.getString(2)));
                );
                movies.add(movie);
            }while(c.moveToNext());
        }
        c.close();


        return movies;

    }


    public Movie getMovieFromDatabase(long id) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
Movie movie=new Movie();
        SQLiteDatabase     db= this.getReadableDatabase();
        Cursor c=  db.rawQuery( "select * from " + TABLE_MOVIES +" where " +COLUMN_ID +" = " +id, null );
        c.moveToFirst();
if(id== c.getLong(c.getColumnIndex(COLUMN_ID)))
        {
            if (c.moveToFirst()) {
                do {

                    movie = new Movie(
                            c.getInt(c.getColumnIndex(COLUMN_ID)),
                            //  c.getString(c.getColumnIndex(IMAGE)),
                            c.getString(c.getColumnIndex(TITLE)),
                            c.getString(c.getColumnIndex(OVERVIEW)),
//                        c.getString(c.getColumnIndex(TAGLINE)),
                            c.getString(c.getColumnIndex(RELEASEDATE)),
                            c.getString(c.getColumnIndex(URLSELF))
                            // c.getFloat(c.getColumnIndex(RATING))
                            //   movie.setDbId(Integer.parseInt(cursor.getLong(0)));
                            // movie.setTitle(cursor.getString(1));
                            //inventory.setItemQuant(Integer.parseInt(cursor.getString(2)));
                    );

                } while (c.moveToNext());
            }
            c.close();

        }
      return movie;

    }
    public  boolean isMovieInDatabase(Activity activity, int id){

        ArrayList<Movie> list = new ArrayList<Movie>();
        list= getAllComments();
        for(Movie listItem : list){
            if(listItem.getDbId() == id){
                return true;
            }
        }
        return false;
    }
    public Movie cursorToComment(Cursor cursor) {
        // Comment comment = new Comment();
        Movie movie= new Movie();
        movie.setDbId(cursor.getLong(0));
        movie.setTitle(cursor.getString(1));
        movie.setOverview(cursor.getString(2));
        movie.setReleaseDateTheater(cursor.getString(3));
        //movie.getDbId();
        //movie.getTitle();
        //movie.getOverview();
        //movie.getReleaseDateTheater();
        return movie;
    }

}
