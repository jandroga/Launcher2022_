package com.example.launcher2022.menu;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.launcher2022.db.DbHelper;

import java.util.ArrayList;


public class Statistics extends ListActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DbHelper.TABLE_DOS;
    private SQLiteDatabase newDB;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();

        displayResultList();

    }
    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("TEEEEEEEEEEEEEEEEEEEEEEST");
        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }
    private void openAndQueryDatabase() {
        try {
            DbHelper dbHelper = new DbHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor cursor = newDB.rawQuery("SELECT FirstName, Age FROM " +
                    tableName +
                    " where Age > 10 LIMIT 4", null);

            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {

                    }while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }

    }

}
