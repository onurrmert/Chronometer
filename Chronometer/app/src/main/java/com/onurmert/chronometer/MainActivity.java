package com.onurmert.chronometer;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.onurmert.chronometer.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Timer timer;
    private  int counter0;
    private Handler handler;
    private int counter1 = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        buttons();
    }
    private void buttons(){
        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeStart();
            }
        });
        binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continue1();
            }
        });
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void continue1(){
        if (counter1 % 2 == 0){
            timeStop();
            counter1++;
            binding.buttonContinue.setText("Continue");
        }else {
            timeStart();
            counter1++;
            binding.buttonContinue.setText("Stop");
        }
    }
    private void stop(){
        timeStop();
        counter0 = 0;
        binding.textView.setText(String.valueOf(counter0));
    }
    private void timeStop(){
        if (timer != null){
            timer.cancel();
        }
        timer = null;
        handler = null;
    }
    private void timeStart(){
        timeStop();
        handler = new Handler();
        timer = new Timer();

        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                counter0++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.textView.setText(String.valueOf(counter0));
                    }
                });
            }
        };
        timer.schedule(timerTask, 0,1000);
    }

}