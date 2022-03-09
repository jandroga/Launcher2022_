package com.example.launcher2022.pegsolitaire;

import android.content.Context;
import android.view.View;

public class PegFicha extends androidx.appcompat.widget.AppCompatImageView {

    private int fila;
    private int columna;
    private boolean empty;

    //Cada ficha tendrà una posició al peglayout. Ja quan tiri si eso això se veurà si tira
    public PegFicha(Context context, int fila, int columna) {
        super(context);
    }

    //Comprova si la casella objectiu és buida. Si ho és,
    public boolean move(PegLayout casellaAntiga, PegLayout casellaObjectiu, PegLayout[][] caselles) {

        int filaNova = casellaObjectiu.getFila();
        int columnaNova = casellaObjectiu.getColumna();
        int filaVella = casellaAntiga.getFila();
        int columnaVella = casellaAntiga.getColumna();



        //removeAllViews > removeAllViewsInLayout, canviar boolean des checkEmpty
        if (casellaObjectiu.checkEmpty()) {
            if (((Math.abs(filaNova - filaVella) == 2) && (columnaNova == columnaVella)) ||
                    (Math.abs(columnaNova - columnaVella) == 2) && (filaNova == filaVella)) {
                if ((columnaVella - columnaNova == -2) && (!caselles[filaNova][columnaNova - 1].checkEmpty())) {
                    caselles[filaNova][columnaNova - 1].removeAllViews();
                } else if ((columnaVella - columnaNova == 2) && (!caselles[filaNova][columnaNova + 1].checkEmpty())) {
                    caselles[filaNova][columnaNova + 1].removeAllViews();
                } else if ((filaVella - filaNova == -2) && (!caselles[filaNova - 1][columnaNova].checkEmpty())) {
                    caselles[filaNova - 1][columnaNova].removeAllViews();
                } else if ((filaVella - filaNova == 2) && (!caselles[filaNova + 1][columnaNova].checkEmpty())) {
                    caselles[filaNova + 1][columnaNova].removeAllViews();
                } else {
                    return false;
                }
                casellaAntiga.removeView(this);
                casellaObjectiu.addView(this);
                this.fila = filaNova;
                this.columna = columnaNova;
                this.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){

        return empty;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}