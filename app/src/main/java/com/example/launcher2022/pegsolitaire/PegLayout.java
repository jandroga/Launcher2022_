package com.example.launcher2022.pegsolitaire;

import android.content.Context;
import android.widget.LinearLayout;

public class PegLayout extends LinearLayout {

    private int fila;
    private int columna;


    public PegLayout(Context context, int x, int y) {
        super(context);
        fila = x;
        columna = y;
    }

    public boolean checkEmpty(){

        //getChildCount torna nombre de views
        if(this.getChildCount() > 0) {
        }
        return false;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
}