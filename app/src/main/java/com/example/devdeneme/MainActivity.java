package com.example.devdeneme;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView mTextViewCountDown;
    private TextView mTextViewCountDown2;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button mButtonStartPause2;

    private CountDownTimer mCountDownTimer;
    private CountDownTimer mCountDownTimer2;
    private boolean mTimerRunning;
    private boolean mTimerRunning2;
    private boolean firstClick=true;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis2 = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.countdown_text);
        mTextViewCountDown2=findViewById(R.id.countdown_text2);
        mButtonStartPause2= findViewById(R.id.startpause_button2);
        mButtonStartPause = findViewById(R.id.startpause_button);
        mButtonReset = findViewById(R.id.reset_button);

        mButtonStartPause.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if(firstClick)
                {
                    if (mTimerRunning)
                    {
                        pauseTimer2();
                    }
                    else
                    {
                        startTimer2();
                    }
                }
                else
                {
                    if (mTimerRunning)
                    {
                        pauseTimer();
                        startTimer2();
                    }
                    else
                    {
                        startTimer();
                        pauseTimer2();
                    }
                }
                firstClick=false;
            }
        });
        mButtonStartPause2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(firstClick)
                {
                    if (mTimerRunning2)
                    {
                        pauseTimer();
                    }
                    else
                    {
                        startTimer();
                    }
                }
                else
                {
                    if (mTimerRunning2)
                    {
                        pauseTimer2();
                        startTimer();
                    }
                    else
                    {
                        startTimer2();
                        pauseTimer();
                    }
                }
                firstClick=false;

            }
        });


        mButtonReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resetTimer();
                resetTimer2();
            }
        });

        }


    private void startTimer()
    {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish()
            {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void startTimer2()
    {
        mCountDownTimer2 = new CountDownTimer(mTimeLeftInMillis2, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                mTimeLeftInMillis2 = millisUntilFinished;
                updateCountDownText2();
            }

            @Override
            public void onFinish()
            {
                mTimerRunning2 = false;
                mButtonStartPause2.setText("Start");
                mButtonStartPause2.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning2 = true;
        mButtonStartPause2.setText("pause");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void pauseTimer()
    {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void pauseTimer2()
    {
        mCountDownTimer2.cancel();
        mTimerRunning2 = false;
        mButtonStartPause2.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer()
    {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        pauseTimer();
        firstClick=true; //otherwise it will continue like not starting from beginning
    }

    private void resetTimer2()
    {
        mTimeLeftInMillis2 = START_TIME_IN_MILLIS;
        updateCountDownText2();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause2.setVisibility(View.VISIBLE);
        pauseTimer2();
        firstClick=true; //otherwise it will continue like not starting from beginning
    }

    private void updateCountDownText()
    {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateCountDownText2()
    {
        int minutes = (int) (mTimeLeftInMillis2 / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis2 / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown2.setText(timeLeftFormatted);
    }



}