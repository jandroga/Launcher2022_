package com.example.launcher2022.pegsolitaire;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.launcher2022.R;

import java.util.List;


public class PegGame extends AppCompatActivity implements View.OnTouchListener {

    //Un TableLayout té X files i Y columnes, que li especificarem empleant TableRow
    private PegLayout[][] caselles = new PegLayout[7][7];
    private TableLayout tableLayout;
    private TableRow[] tableRow = new TableRow[7];
    private PegFicha[][] fiches = new PegFicha[7][7];
    private TextView txtScore;
    private Drawable pegicon;
    private boolean pitjant;
    private int height = 150;
    private int width = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.launcher2022.R.layout.peglayout);

        //Funciona sense cast a (TableLayout)!!!!!!!!!!!!!!!
        tableLayout = findViewById(R.id.tableLayout);



/*
TODO mètode per agafar es num de files, columnes, i els valors que ara són "0,1,5,6" d'una llista de mapes on cada un marca un límit des mapa.
 */
        for (int x = 0; x < 7; x++) {
            tableRow[x] = new TableRow(this);
            for (int y = 0; y < 7; y++) {
                //En tots els llocs menys en aquests:
                if(!(
                    (y == 0 || y == 1 || y == 5 || y == 6)
                    &&
                    (x == 0 || x == 1 || x == 5 || x == 6))) {
                    caselles[x][y] = new PegLayout(this, x, y);
                    caselles[x][y].setBackgroundResource(R.drawable.pegicon);

                    //El 3 representa el centre (0,1,2, *3*  4,5,6) de la graella
                    if(!((x == 3) && (y == 3))){
                        fiches[x][y] = new PegFicha(this, x, y);
                        fiches[x][y].setImageResource(R.drawable.pegficha);
                        fiches[x][y].setLayoutParams(new LinearLayout.LayoutParams(height,width));
                        fiches[x][y].setClickable(true);
                        fiches[x][y].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                view.getLeft();
                                System.out.println(view);
                                changeColorOnClick(view);
                            }
                        });
                        caselles[x][y].addView(fiches[x][y]);
                    }
                    tableRow[x].addView(caselles[x][y]);
                    TableRow.LayoutParams p = (TableRow.LayoutParams)caselles[x][y].getLayoutParams();
                    p.column = y;
                    p.height = height;
                    p.width = width;
                    caselles[x][y].setLayoutParams(p);

//TODO MIRAR COM FER QUE ES PUTA PEGFICHA TENGUI UN TOUCH LISTENER QUE ACTIVI SES CASELLES QUE NO ESTIGUIN BUIDES DES COSTATS (x+1, y+1, x-1 i y-1). Si no està buida, comprovar si sa següent ho està i, si ho està, resaltar-la d'un altre color
//                    caselles[x][y].setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                            return false;
//                        }
//                    });
                }
            }
            tableLayout.addView(tableRow[x], new TableRow.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        }

        //Falta afegir s'score i tal
    }

    private void setId(int i){

    }


    private void changeColorOnClick(View v){
        PegFicha ficha;
        PegLayout vellLayout;

        if(!pitjant){
            setPitjada(true);
            v.setBackgroundColor(Color.GREEN);
            System.out.println("Ps ara miram què passa aquí");
            System.out.println(v.getLeft());
        }else {
            System.out.println("Joder què cony passa");
            v.setBackgroundColor(0);
            setPitjada(false);
        }
    }
    public void setPitjada(boolean pitjada) {
        this.pitjant = pitjada;
    }

    public boolean isPitjada() {
        return pitjant;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        System.out.println("HASDJKASDSADJPJASDJSADSA");
        changeColorOnClick(view);
        return false;
    }

    //TOUCH LISTENER WAY
}

