package com.dadapper.wordswiper;

import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class GamePlayActivity extends Activity {
    private static final long START_TIME_IN_MILLIS = 120000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    List<String> word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Timer Code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

        word = new ArrayList<>();
        final Map<Integer, Boolean> hash = new HashMap<>();

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        final CharAdaptor charAdaptor = new CharAdaptor(this);
        gridview.setAdapter(charAdaptor);

        //gridview.setOnItemClickListener();
        gridview.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                float currentXPosition = event.getX();
                float currentYPosition = event.getY();
                int position = gridview.pointToPosition((int) currentXPosition, (int) currentYPosition);
                if (action == MotionEvent.ACTION_DOWN) {
                    System.out.println("position : " + position);
                    if(position < 0 || position > 15)
                    {
                        System.out.println("Array index out of bound");
                    }
                    else {
                        if(!hash.containsKey(position)) {
                            word.add(charAdaptor.getItem(position).toString());
                            System.out.println("Action DOWN - adding item : " + charAdaptor.getItem(position).toString());
                            // System.out.print("action move");
                            hash.put(position,true);
                        }
                    }
                }
                else if(action==MotionEvent.ACTION_UP)
                {
                    System.out.print("action up - current word :  ");
                    print(word);
                    word.clear();
                    hash.clear();
                }
                else if(action == MotionEvent.ACTION_MOVE)
                {
                    System.out.println("position : " + position);
                    if(position < 0 || position > 15)
                    {
                        System.out.println("Array index out of bound");
                    }
                    else {
                        if(!hash.containsKey(position)) {
                            word.add(charAdaptor.getItem(position).toString());
                            System.out.println("Action move - adding item : " + charAdaptor.getItem(position).toString());
                            // System.out.print("action move");
                            hash.put(position,true);
                        }
                    }
                }
                return true;
            }
        });
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000){
            @Override
            public void onTick ( long millisUntilFinished){
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish () {
               mTimerRunning = false;
               mButtonStartPause.setText("Start");
               mButtonStartPause.setVisibility(View.INVISIBLE);
               mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer(){
        mTimeLeftInMillis  = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int minutes= (int)(mTimeLeftInMillis /1000) / 60;
        int seconds= (int)(mTimeLeftInMillis /1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d" , minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void print(List<String> list)
    {
        for(String object : list)
        {
            System.out.print(object + " ");
        }
        System.out.println();
    }
}
