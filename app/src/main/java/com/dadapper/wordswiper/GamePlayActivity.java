package com.dadapper.wordswiper;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlayActivity extends Activity {

    List<String> word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        word = new ArrayList<>();
        final Map<Integer, Boolean> hash = new HashMap<>();

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        final CharAdaptor charAdaptor = new CharAdaptor(this);
        gridview.setAdapter(charAdaptor);

        //gridview.setOnItemClickListener();
        gridview.setOnTouchListener(new View.OnTouchListener() {
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

    public void print(List<String> list)
    {
        for(String object : list)
        {
            System.out.print(object + " ");
        }
        System.out.println();
    }
}
