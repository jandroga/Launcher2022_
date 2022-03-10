package com.example.launcher2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.launcher2022.db.DbGestion;
import com.example.launcher2022.db.DbHelper;
import com.example.launcher2022.menu.MainActivity;

import java.util.ArrayList;

public class ConfigActivity extends AppCompatActivity {


    private UserSettings ajustes;
    private DbHelper dbHelper;
    private TableLayout statiscTable;
    private ArrayList<String> listScores;
    ArrayAdapter adapter;

    ListView scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ajustes = (UserSettings) getApplication();

        dbHelper = new DbHelper(this);

        listScores = new ArrayList<>();
        viewData();

        DbGestion dbGestion = new DbGestion(ConfigActivity.this);
        dbGestion.open();

        Cursor cursor = dbGestion.fetch();
        cursor.moveToFirst();
        TextView score = findViewById(R.id.textest);
        score.setText(cursor.getString(1));


        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new DbHelper(ConfigActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(ConfigActivity.this, "S'ha creat la base de dades", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ConfigActivity.this, "No s'ha creat la base de dades", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void viewData() {
        Cursor cursor = dbHelper.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No hi ha res a la base de dades", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                //index 1 = score
                listScores.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listScores);
            scoreList.setAdapter(adapter);
        }
    }


    public void getTableReady(){
        statiscTable = (TableLayout) LayoutInflater.from(ConfigActivity.this).inflate(R.layout.statisctable, null, false);

    }

}
//Intent intent = new Intent(this,ConfigActivity.class);