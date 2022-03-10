package com.example.launcher2022.doszeroquatrevuit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;

import com.example.launcher2022.R;
import com.example.launcher2022.db.DbGestion;
import com.example.launcher2022.db.DbHelper;

import java.util.ArrayList;
import java.util.Random;

public class DosMilQuarantaVuit extends Activity implements GestureDetector.OnGestureListener {
    private int tam = 16;
    private int contador = 0;
    private DbHelper dbHelper;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public TextView scoreView;
    private TextView maxScore;
    private ArrayList<TextView> tauler = new ArrayList<>();
    private GestureDetectorCompat gestureDetectorCompat;

    //Dins aquest tauler s'aniran marcant les caselles buides per generar-hi (o no) nous nums
//    private ArrayList<TextView> espaiEnBlancSlot = (ArrayList<TextView>) tauler.clone();


    //Si no pos constructor buid me tira error
    public DosMilQuarantaVuit(){

    }

    public DosMilQuarantaVuit(Context context){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.launcher2022.R.layout.dosmilayout);

        //Perdó
        tauler.add((TextView) findViewById(R.id.a1));
        tauler.add((TextView) findViewById(R.id.a2));
        tauler.add((TextView) findViewById(R.id.a3));
        tauler.add((TextView) findViewById(R.id.a4));
        tauler.add((TextView) findViewById(R.id.b1));
        tauler.add((TextView) findViewById(R.id.b2));
        tauler.add((TextView) findViewById(R.id.b3));
        tauler.add((TextView) findViewById(R.id.b4));
        tauler.add((TextView) findViewById(R.id.c1));
        tauler.add((TextView) findViewById(R.id.c2));
        tauler.add((TextView) findViewById(R.id.c3));
        tauler.add((TextView) findViewById(R.id.c4));
        tauler.add((TextView) findViewById(R.id.d1));
        tauler.add((TextView) findViewById(R.id.d2));
        tauler.add((TextView) findViewById(R.id.d3));
        tauler.add((TextView) findViewById(R.id.d4));

        scoreView = (TextView) findViewById(R.id.scoreTV);
        maxScore = (TextView) findViewById(R.id.bestScore);

        gestureDetectorCompat = new GestureDetectorCompat(this, this);

        dbHelper = new DbHelper(this);

        Button button = (Button) findViewById(R.id.NewGameButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameReady();
            }
        });
        setGameReady();
    }

    private void setGameReady() {
        roomService();
        spawn();
        spawn();
        setContador(0);
    }


    //Per cada casella
    private void setNewNum(TextView casella, int num){
        casella.setText(String.valueOf(num));
    }

    public void roomService() {
        for (TextView casella : tauler){
            casella.setText(R.string.espaiEnBlanc);
        }
    }

    public void saveScoreInDb(){
        DbGestion dbGestion = new DbGestion(DosMilQuarantaVuit.this);
        long data = dbGestion.addScore(String.valueOf(getContador()));
        if(data > 0){
            Toast.makeText(DosMilQuarantaVuit.this, "S'ha guardat un nou record a la base de dades", Toast.LENGTH_SHORT).show();
        }
    }

    //Aquest merder d'aquí simplement pilla s'string des textview de sa casella que, transformat a int, és més alt que la resta, i el posa a dalt
    public void updateScore(){
        for (TextView casella: tauler){
            String casellaTxt = String.valueOf(casella.getText());
            System.out.println("Casella txt: "+ casellaTxt);

            if(!casellaTxt.equals("") && Integer.parseInt(casellaTxt) > getContador()) {
                setContador(Integer.parseInt(casellaTxt));
                scoreView.setText(String.valueOf(contador));
                saveScoreInDb();
            }
        }
    }
    public void spawnTest(){
        spawn();
    }

    private int getContador() {
        return contador;
    }

    private void setContador(int score){
        this.contador = score;
    }

    private void spawn(){
        Random r = new Random();
        int casella;
        do{
            //Se li dona un valor entre 16, on cada 1 és un textview a l'array de TextViews Tauler
            casella = r.nextInt(tam);
            //Comprova si el texte de la casella NO ÉS un espai en blanc
        } while (tauler.get(casella).getText() != getString(R.string.espaiEnBlanc));
        //Genera un num entre 0 i 4 que forçam ser 2 o 4
        int ranNum = r.nextInt(4);
        setNewNum(tauler.get(casella), (ranNum == 0) ? 4 : 2);
    }

    private void metodePerActualitzarComponents(){
        //TODO MÈTODE X UPDATE
    }

    //Per calcular moviments, s'anirà restant 4 per anar cap a dalt o restant 1 per anar cap a l'esquerra
    //S'aniran mirant les p
//    private boolean movePosible(){
//        for(TextView casella : tauler)
//    }
//    public boolean leftPosible(int num){
//        //Miram si el mòd
//        if(num % 4 == 0)
//    }
    private void inLeftSwipe() {
        if (isLeftValid()) {
            onMove(moveLeft());
        }
    }

    private void onMove(int points) {
        updateScore();
        spawn();
    }

    private void onRightSwipe() {
        if (isRightValid()) {
            onMove(moveRight());
        }
    }

    private void onUpSwipe() {
        if (isUpValid()) {
            onMove(moveUp());
        }
    }

    private void onDownSwipe() {
        if (isDownValid()) {
            onMove(moveDown());
        }
    }

    private boolean isLeftValid() {
        for (int i = 0; i < tauler.size(); i++) {
            if (canMoveLeft(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRightValid() {
        for (int i = 0; i < tauler.size(); i++) {
            if (canMoveRight(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUpValid() {
        for (int i = 0; i < tauler.size(); i++) {
            if (canMoveUp(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDownValid() {
        for (int i = 0; i < tauler.size(); i++) {
            if (canMoveDown(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveLeft(int position) {
        if (position % 4 == 0 || tauler.get(position).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            return false;
        } else {
            int leftPosition = position - 1;
            if (tauler.get(leftPosition).getText().toString().equals(getString(R.string.espaiEnBlanc)) || tauler.get(leftPosition).getText().toString().equals(tauler.get(position).getText().toString())) {
                return true;
            }
            if (canMoveLeft(leftPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveRight(int position) {
        if (position % 4 == 3 || tauler.get(position).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            return false;
        } else {
            int rightPosition = position + 1;
            if (tauler.get(rightPosition).getText().toString().equals(getString(R.string.espaiEnBlanc)) || tauler.get(rightPosition).getText().toString().equals(tauler.get(position).getText().toString())) {
                return true;
            }
            if (canMoveRight(rightPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveUp(int position) {
        if (position <= 3 || tauler.get(position).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            return false;
        } else {
            int upPosition = position - 4;
            if (tauler.get(upPosition).getText().toString().equals(getString(R.string.espaiEnBlanc)) || tauler.get(upPosition).getText().toString().equals(tauler.get(position).getText().toString())) {
                return true;
            }
            if (canMoveUp(upPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveDown(int position) {
        if (position >= 12 || tauler.get(position).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            return false;
        } else {
            int downPosition = position + 4;
            if (tauler.get(downPosition).getText().toString().equals(getString(R.string.espaiEnBlanc)) || tauler.get(downPosition).getText().toString().equals(tauler.get(position).getText().toString())) {
                return true;
            }
            if (canMoveDown(downPosition)) {
                return true;
            }
        }
        return false;
    }

    //Points == dist fins darrera casella lliure
    private int moveLeft() {
        int points = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int position = j * 4 + i;
                points += moveLeft(position);
            }
        }
        return points;
    }

    private int moveLeft(int position) {
        if (!canMoveLeft(position)) {
            return 0;
        }
        String image = tauler.get(position).getText().toString();
        int leftPosition = position - 1;
        if (tauler.get(leftPosition).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(leftPosition).setText(image);
            return moveLeft(leftPosition);
        } else if (tauler.get(leftPosition).getText().toString().equals(image)) {
            int amount = Integer.parseInt(image) * 2;
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(leftPosition).setText(Integer.toString(amount));
            return amount;
        }
        return 0;
    }

    private int moveRight() {
        int points = 0;
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                int position = j * 4 + i;
                points += moveRight(position);
            }
        }
        return points;
    }

    private int moveRight(int position) {
        if (!canMoveRight(position)) {
            return 0;
        }
        String image = tauler.get(position).getText().toString();
        int rightPosition = position + 1;
        if (tauler.get(rightPosition).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(rightPosition).setText(image);
            return moveRight(rightPosition);
        } else if (tauler.get(rightPosition).getText().toString().equals(image)) {
            int amount = Integer.parseInt(image) * 2;
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(rightPosition).setText(Integer.toString(amount));
            return amount;
        }
        return 0;
    }

    private int moveUp() {
        int points = 0;
        for (int i = 0; i < 16; i++) {
            points += moveUp(i);
        }
        return points;
    }

    private int moveUp(int position) {
        if (!canMoveUp(position)) {
            return 0;
        }
        String image = tauler.get(position).getText().toString();
        int upPosition = position - 4;
        if (tauler.get(upPosition).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(upPosition).setText(image);
            return moveUp(upPosition);
        } else if (tauler.get(upPosition).getText().toString().equals(image)) {
            int amount = Integer.parseInt(image) * 2;
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(upPosition).setText(Integer.toString(amount));
            return amount;
        }
        return 0;
    }

    private int moveDown() {
        int points = 0;
        for (int i = 15; i >= 0; i--) {
            points += moveDown(i);
        }
        return points;
    }

    private int moveDown(int position) {
        if (!canMoveDown(position)) {
            return 0;
        }
        String image = tauler.get(position).getText().toString();
        int downPosition = position + 4;
        if (tauler.get(downPosition).getText().toString().equals(getString(R.string.espaiEnBlanc))) {
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(downPosition).setText(image);
            return moveDown(downPosition);
        } else if (tauler.get(downPosition).getText().toString().equals(image)) {
            int amount = Integer.parseInt(image) * 2;
            tauler.get(position).setText(R.string.espaiEnBlanc);
            tauler.get(downPosition).setText(((Integer) amount).toString());
            return amount;
        }
        return 0;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    //Cal especificar el que es considera "moviment" cap als costats i què no
//    @Override
//    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        try {
//            //Si sa distància recorreguda és molt petita, no la comptam
//            if (Math.abs(motionEvent.getY() - motionEvent1.getY()) > 250) {
//                return false;
//            }
//            //de dreta a esquerra
//            if (motionEvent.getX() - motionEvent1.getX() > 120 && Math.abs(v) > 200) {
//                System.out.println("Mogut cap a l'esquerra");
//                inLeftSwipe();
//            }
//            //esquerra a dreta
//            else if (motionEvent1.getX() - motionEvent.getX() > 120 && Math.abs(v) > 200) {
//                onRightSwipe();
//            }
//            //baix a dalt
//            else if (motionEvent.getY() - motionEvent1.getY() > 120 && Math.abs(v1) > 200) {
//                onUpSwipe();
//            }
//            //dalt a baix
//            else if (motionEvent1.getY() - motionEvent.getY() > 120 && Math.abs(v1) > 200) {
//                onDownSwipe();
//            }
//        } catch (Exception e) {
//        }
//        return true;
//    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onRightSwipe();
                    } else {
                        inLeftSwipe();
                    }
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onDownSwipe();
                } else {
                    onUpSwipe();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

}
