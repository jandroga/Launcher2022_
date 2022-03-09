package com.example.launcher2022.doszeroquatrevuit;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class Graella extends GridLayout {
    public Graella(Context context) {
        super(context);

        this.setOrientation(VERTICAL);
        this.setBackgroundColor(Color.GRAY);


        //Listener des touch DAMUNT sa graella per moure només si mou a dins
//        this.setOnTouchListener(new OnTouchListener() {
//
//            float x;
//            float y;
//            float toX;
//            float toY;
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                switch (motionEvent.getAction()){
//                    //Calculam sa diferència per saber si ha mogut cap a +eix o -eix
//                    case MotionEvent.ACTION_DOWN:
//                        x = motionEvent.getX();
//                        y = motionEvent.getY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        toX = motionEvent.getX()-x;
//                        toY = motionEvent.getY()-y;
//                        if (Math.abs(toX)>Math.abs(toY)) {
//                            if (toX<-5) {
//                                swipeLeft();
//                            }else if (toX>5) {
//                                swipeRight();
//                            }
//                        }else{
//                            if (toY<-5) {
//                                swipeUp();
//                            }else if (toY>5) {
//                                swipeDown();
//                            }
//                        }
//
//                        break;
//                }
//                return true;
//                }
//        });
    }
}
