package com.example.launcher2022;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items = { getResources().getString(R.string.playFirst),
                getResources().getString(R.string.playSecond),
                getResources().getString(R.string.config),
                getResources().getString(R.string.info),
                getResources().getString(R.string.exit) };
        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.list_item, items);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //Per agafar sa posició de s'element clickat des menú
        listView.setOnItemClickListener((parent, view, position, id) -> {
            int pos = (int) adapter.getItemId(position);
            Log.d("Id pitjada", String.valueOf(pos));

            switch (pos){
                case 0:


                    break;

                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + pos);
            }
        });



    }
}