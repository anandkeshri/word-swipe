package com.dadapper.wordswiper.EventHandlers;

import android.view.MotionEvent;
import android.view.View;

public interface EventHandler {
    void actionDown(View v, MotionEvent event);
    void actionUp(View v, MotionEvent event);
    void actionMove(View v, MotionEvent event);
}
