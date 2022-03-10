package com.example.launcher2022.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOMBRE = "stadistics.db";
    public static final String TABLE_DOS = "t_stadistics";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SCORE = "SCORE";

    public static final String CREATE_SCORE_TABLE =
            "CREATE TABLE " + TABLE_DOS + " ("+
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_SCORE + " INTEGER" + ")";

    public static final String queryAllScores =
            "SELECT " + "SCORE." + COLUMN_ID + ", " + COLUMN_SCORE +
            " FROM " + TABLE_DOS + " USER" +
            " ORDER BY " + COLUMN_SCORE + " DESC";

    private static DbHelper dbInstance = null;
    private SQLiteDatabase db;



    public DbHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        Fer-ho full strings
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DOS + "(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "score TEXT NOT NULL)");

        sqLiteDatabase.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DOS);
        onCreate(db);
    }

    public boolean insertData(String score){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SCORE, score);

        long result = db.insert(TABLE_DOS, null, cv);

        //Torna es long que determina si s'ha enviat correctament. -1 mplica que guay
        return result != -1;
    }

    public Cursor viewData(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from "+TABLE_DOS;
        Cursor cursor = database.rawQuery(query, null);

        return cursor;
    }

    public static DbHelper getInstance(Context activityContext) {
        if (dbInstance == null) {
            dbInstance = new DbHelper(activityContext.getApplicationContext());
        }
        return dbInstance;
    }

    public void checkDb(){
        if(db == null){
            db = getWritableDatabase();
        }
    }

//    public List getAllScores(){
//        checkDb();
//        List<String[]> scoresStatis = new ArrayList<>();
//        Cursor cursor = db.rawQuery(queryAllScores, null);
//        if (cursor.moveToNext()) {
//            do{
//                String[] newScore = new String[];
//                int id = cursor.getInt(0);
//                Integer score = cursor.getInt(1);
////                ScoreDisplay scoreDisplay = new ScoreDisplay(id, score);
////                scoresStatis.add(scoreDisplay);
//            }
//            while(cursor.moveToNext());
//        }
//        cursor.close();
//        return scoresStatis;
//    }


    //Aquest mètode serveig per afegir scores i ids que passam des de l'ScoreModel
//    public void addScore(ScoreModel scoreModel){
//        checkDb();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_ID, scoreModel.getId());
//        cv.put(COLUMN_SCORE, scoreModel.getScore());
//        db.insert(TABLE_DOS, null, cv);
//    }

    public void updateScore(int id, int score){
        checkDb();

    }

}
