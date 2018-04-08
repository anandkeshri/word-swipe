package com.dadapper.wordswiper;

import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.dadapper.wordswiper.Dictionary.WordDictionary;
import com.dadapper.wordswiper.EventHandlers.EventHandler;
import com.dadapper.wordswiper.EventHandlers.GamePlayActivityEventHandler;

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
        WordDictionary dictionary = new WordDictionary(this);
        final EventHandler eventHandler = new GamePlayActivityEventHandler(gridview,this,dictionary);

        //gridview.setOnItemClickListener();
        gridview.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch ( action )
                {
                    case MotionEvent.ACTION_DOWN:
                        eventHandler.actionDown(v, event);
                        break;
                    case MotionEvent.ACTION_UP:
                        eventHandler.actionUp(v,event);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        eventHandler.actionMove(v, event);
                        break;
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
