package com.dadapper.wordswiper;

import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;
public class GamePlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new CharAdaptor(this));
    }
}
