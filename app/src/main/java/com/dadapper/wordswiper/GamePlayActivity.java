package com.dadapper.wordswiper;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.dadapper.wordswiper.Dictionary.WordDictionary;
import com.dadapper.wordswiper.EventHandlers.EventHandler;
import com.dadapper.wordswiper.EventHandlers.GamePlayActivityEventHandler;

import java.util.List;

public class GamePlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        final CharAdaptor charAdaptor = new CharAdaptor(this);
        gridview.setAdapter(charAdaptor);
        WordDictionary dictionary = new WordDictionary(this);
        final EventHandler eventHandler = new GamePlayActivityEventHandler(gridview,this,dictionary);

        //gridview.setOnItemClickListener();
        gridview.setOnTouchListener(new View.OnTouchListener() {
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

    public void print(List<String> list)
    {
        for(String object : list)
        {
            System.out.print(object + " ");
        }
        System.out.println();
    }
}
