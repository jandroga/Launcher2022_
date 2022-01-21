package com.example.launcher2022;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String HolaProva = "whatdefok";
    private UserSettings ajustes;
    private SwitchMaterial themeSwitch;
    private View parentView;
    private String theme;
    private ListView listView;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_Launcher2022);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ajustes = (UserSettings) getApplication();

        themeSwitch = findViewById(R.id.themeSwitch);
        listView = findViewById(R.id.listView);
        //Ajustes


        initListView();
        carregarConfig();
        initSwitchListener();
        updateView();



        //Buttons
        //ItemList, menú que surt a nes principi fàcil d'ampliar
        String[] items = { getResources().getString(R.string.playFirst),
                getResources().getString(R.string.playSecond),
                getResources().getString(R.string.config),
                getResources().getString(R.string.info),
                getResources().getString(R.string.exit) };

        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.list_item, items);

        listView.setAdapter(adapter);

        //Per agafar sa posició de s'element clickat des menú
        listView.setOnItemClickListener((parent, view, position, id) -> {
            int pos = (int) adapter.getItemId(position);
            Log.d("Id pitjada", String.valueOf(pos));


            //Switch dels botons del menú
            switch (pos){
                case 0:

//                    Intent intent = new Intent(this,NomDeS'activity.class);
//                    startActivity(intent);

                    break;

                case 1:

//                    Intent intent = new Intent(this,NomDeS'activity.class);
//                    startActivity(intent);

                    break;

                case 2:

                    Intent intent = new Intent(this,ConfigActivity.class);
                    startActivity(intent);

                    break;

                case 3:

//                    Intent intent = new Intent(this,NomDeS'activity.class);
//                    startActivity(intent);

                    break;

                case 4:

//                    Intent intent = new Intent(this,NomDeS'activity.class);
//                    startActivity(intent);

                    break;


                default:
                    throw new IllegalStateException("Unexpected value: " + pos);
            }
        });


    }

    private void initListView() {
    }

    private void initSwitchListener() {

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked)
                    ajustes.setCustomTheme(UserSettings.DARK_THEME);
                else
                    ajustes.setCustomTheme(UserSettings.NORMAL_THEME);

                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.AJUSTES,MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME, ajustes.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }

    private void updateView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(ajustes.getCustomTheme().equals(UserSettings.DARK_THEME)) {
            listView.setBackgroundColor(black);
            themeSwitch.setChecked(true);
        }
        else{
            listView.setBackgroundColor(white);
            themeSwitch.setChecked(false);

        }
    }

    


    public void carregarConfig(){

        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.AJUSTES, MODE_PRIVATE);
        String theme = sharedPreferences.getString(UserSettings.NORMAL_THEME, UserSettings.DARK_THEME);
        ajustes.setCustomTheme(theme);
    }


    //Turtle de prova
    public void missatgeInfo(){

        Toast.makeText(this,"Ara ets a" + getApplicationContext(),Toast.LENGTH_SHORT).show();
    }
}