package com.example.administrator.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView score;
    TextView score2;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        score = (TextView) findViewById(R.id.score);
        score2 = (TextView) findViewById(R.id.score2);
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String scorea =((TextView)findViewById(R.id.score)).getText().toString();
        String scoreb =((TextView)findViewById(R.id.score2)).getText().toString();
        Log.i(TAG,"onSaveInstanceState:");
        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }  //切换屏幕时保留数据

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String scorea =savedInstanceState.getString("teama_score");
        String scoreb =savedInstanceState.getString("teamb_score");
        Log.i(TAG,"onRestoreInstanceState:");
        ((TextView)findViewById(R.id.score)).setText(scorea);
        ((TextView)findViewById(R.id.score2)).setText(scoreb);
    }
    public void  btnAdd1(View btn) {
        if (btn.getId() == R.id.btn_1) {//处理多个按钮
            showScore(1);
        }else {
        showScore2(1);
        }
    }

    public void  btnAdd2(View btn){
        if (btn.getId() == R.id.btn_2) {//处理多个按钮
            showScore(2);
        }else {
            showScore2(2);
        }
    }

    public void  btnAdd3(View btn){
        if (btn.getId() == R.id.btn_3) {//处理多个按钮
            showScore(3);
        }else {
            showScore2(3);
        }
    }

    public void  btnReset(View btn){
        score.setText("0");
        score2.setText("0");
    }

    private void showScore(int inc){
        Log.i("show","inc="+inc);
        String oldScore = (String) score.getText();
        int newScore = Integer.parseInt(oldScore)+inc;
        score.setText("" + newScore);
    }
    private void showScore2(int inc){
        Log.i("show","inc="+inc);
        String oldScore = (String) score2.getText();
        int newScore = Integer.parseInt(oldScore)+inc;
        score2.setText("" + newScore);
    }
}
