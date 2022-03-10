package com.example.launcher2022.pegsolitaire;

import android.view.DragEvent;
import android.view.View;

public class CustomListener {
}

//public class CustomDragListener implements View.OnDragListener{
//    @Override
//    public boolean onDrag(View view, DragEvent dragEvent) {
//        //No he aconseguit passar sa ubicació des view dins s'array on se troba per paràmetres i així calcular, o sigui que aprofitaré
//        //el DragEvent que necessita onDrag()
//        PegLayout vellLayout;
//        PegFicha ficha;
//
//        //Si l'acció que fa l'usuari amb la pobra ficha == amollar-la:
//        if(dragEvent.getAction() == DragEvent.ACTION_DROP){
//            System.out.println("Dropped");
//            //Local ens torna com estava sa ficha just abans de començar a moure-la
//            ficha = (PegFicha) dragEvent.getLocalState();
//            PegLayout nouLayout = (PegLayout) view;
//            //Per agafar el layout antic i saber com operar la diferència, empram l'objecte que bàsicament el composa per treure la classe pare
//            vellLayout = (PegLayout) ficha.getParent();
//            if(ficha.move(vellLayout, nouLayout, getCurrentLayout()));{
//                setContador();
//            }
//        }
//        return false;
//    }
//}