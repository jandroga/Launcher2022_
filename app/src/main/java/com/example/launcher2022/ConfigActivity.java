package com.example.launcher2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.launcher2022.db.DbHelper;
import com.example.launcher2022.menu.MainActivity;

public class ConfigActivity extends AppCompatActivity {


    private UserSettings ajustes;
    private DbHelper dbHelper;
    private TableLayout statiscTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ajustes = (UserSettings) getApplication();


//Intent intent = new Intent(ConfigActivity.this, MainActivity.class);
//startActivity(intent);


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
}
//Intent intent = new Intent(this,ConfigActivity.class);