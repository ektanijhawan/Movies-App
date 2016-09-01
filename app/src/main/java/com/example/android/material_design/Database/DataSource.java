package com.example.android.material_design.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.material_design.Movie;

/**
 * Created by Ekta on 01-07-2016.
 */
public class DataSource {
Cursor cursor;
    // Database fields
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = { DbHelper.COLUMN_ID,
            DbHelper.TITLE,
            DbHelper.OVERVIEW,DbHelper.RELEASEDATE };

    public DataSource(Context context) {
        dbHelper = new DbHelper(context);
        open();
    }

    public void open() throws SQLException {
      // database = this.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

 /*    public Movie createDetails(String title,String overview,String releaseDate) {
       ContentValues values = new ContentValues();
       // values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
       values.put(DbHelper.TITLE, title);
         values.put(DbHelper.OVERVIEW, overview);
         values.put(DbHelper.RELEASEDATE, releaseDate);
        long insertId = database.insert(DbHelper.TABLE_MOVIES, null,
                values);
        Cursor cursor = database.query(DbHelper.TABLE_MOVIES,
                allColumns, DbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Movie newMovie = cursorToComment(cursor);
        cursor.close();
        return newMovie;
    }


    public void deleteComment(Movie movie) {
        long id = movie.getDbId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DbHelper.TABLE_MOVIES, DbHelper.COLUMN_ID
                + " = " + id, null);

                   }



*/


 /*  public ArrayList<Movie> getAllCotacts()
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
}
