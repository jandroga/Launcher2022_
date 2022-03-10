package com.example.launcher2022.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

import com.example.launcher2022.R;

public class DbGestion extends DbHelper{
    Context context;
    DbHelper dbHelper;
    SQLiteDatabase database;

    public DbGestion(Context context) {
        super(context);
        this.context = context;
    }
    public DbGestion open() throws SQLException {
        this.dbHelper = new DbHelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }
//    public SimpleCursorAdapter populateListViewFromDB(){
//        String columns[] = {dbHelper.COLUMN_ID, dbHelper.KEY_NAME, dbHelper.KEY_EMAIL};
//        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns,null, null,null, null, null, null);
//        String[] fromFieldNames = new String[]{
//                dbHelper.KEY_ROWID, dbHelper.KEY_NAME, dbHelper.KEY_EMAIL
//        };
//        int[] toViewIDs = new int[]{R.id.scoreIdDb, R.id.scoreDb};
//        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
//                context,
//                R.layout.statisctable,
//                cursor,
//                fromFieldNames,
//                toViewIDs
//        );
//        return contactAdapter;
//    }

    public Cursor fetch() {
        Cursor cursor = this.database.query(DbHelper.TABLE_DOS, new String[]{dbHelper.COLUMN_ID, dbHelper.COLUMN_SCORE}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public long addScore(String score){
        long data = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
//            values.put(COLUMN_ID, id);
            values.put(COLUMN_SCORE, score);

            data = db.insert(TABLE_DOS, null, values);
        }catch(Exception e){
        }
        return data;
    }
}
