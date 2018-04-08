package com.dadapper.wordswiper;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class CharAdaptor extends BaseAdapter {
    private Context mContext;

    // Constructor
    public CharAdaptor(Context c) {
        mContext = c;
    }

    public int getCount() {
        return text.length;
    }

    public Object getItem(int position) {
        return text[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView textView;
        if(convertView == null)
        {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(200,200));
            textView.setPadding(25,10,8,8);
            textView.setTextSize(20);
            textView.setBackgroundResource(android.R.color.holo_orange_light);
            textView.setGravity(Gravity.CENTER);

        }
        else
        {
            textView = (TextView) convertView;
        }
        textView.setText(text[position]);
        return textView;
    }

    String[] text = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
}
