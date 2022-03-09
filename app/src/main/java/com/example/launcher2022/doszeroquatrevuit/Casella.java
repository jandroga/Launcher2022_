package com.example.launcher2022.doszeroquatrevuit;

import android.content.Context;
import android.widget.FrameLayout;

public class Casella {

    private int x;
    private int y;

    public Casella(int x, int y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public float getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }

}
