package com.example.assignement2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {



    private Button rest;
    private Button start_pause;
    private TextView t1;
    private boolean Run;
    private CountDownTimer timer;
    private long timerleftinmillis=START_TIME;
    private static final long START_TIME=600000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=(TextView)findViewById(R.id.t1);
        start_pause=(Button)findViewById(R.id.start_pause) ;
        rest=(Button)findViewById(R.id.rest) ;
        start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Run) {
                    Pause();
                }
                else {
                    Start();

                }
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });
    }



    private void Pause() {
        timer.cancel();
        Run=false;
        start_pause.setText("Start");
        rest.setVisibility(View.VISIBLE);
    }


    private void Start() {
        timer   = new CountDownTimer(timerleftinmillis, 1000) {
            @Override
            public void onTick(long millisUntilfinished) {
                timerleftinmillis = millisUntilfinished;
                UpdateCountDownText();

            }



            @Override
            public void onFinish() {

                Run=false;
                start_pause.setText("Start");
                start_pause.setVisibility(View.INVISIBLE);
                rest.setVisibility(View.VISIBLE);
            }

            public void onFinished() {
            }
        }.start();
        Run=true;
        start_pause.setText("pause");
        rest.setVisibility(View.INVISIBLE);
    }

    private void UpdateCountDownText() {
        int minutes =(int) (timerleftinmillis /1000) / 60;
        int seconds =(int) (timerleftinmillis /1000) % 60;
        String timeleftformatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        t1.setText(timeleftformatted);
    }


    private void Reset() {
        timerleftinmillis =START_TIME;
        UpdateCountDownText();
        rest.setVisibility(View.INVISIBLE);
        start_pause.setVisibility(View.VISIBLE);
    }

}