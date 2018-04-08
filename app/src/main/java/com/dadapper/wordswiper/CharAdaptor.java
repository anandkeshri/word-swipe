package com.dadapper.wordswiper;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharAdaptor extends BaseAdapter {
    private Context mContext;
    private static List<String> text; //= { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
    // Constructor
    public CharAdaptor(Context c) {
        mContext = c;
        text = new ArrayList<>();
        this.setText();
    }

    public int getCount() {
        return text.size();
    }

    public Object getItem(int position) {
        return text.get(position);
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
            textView.setTextSize(40);
            textView.setBackgroundResource(android.R.color.holo_orange_light);
            textView.setGravity(Gravity.CENTER);
         //   textView.setTextAppearance(android.R.style.TextAppearance);
            textView.setTextColor(Color.rgb(255,255,255));
        }
        else
        {
            textView = (TextView) convertView;
        }
        textView.setText(this.text.get(position));
        return textView;
    }

    private void setText()
    {
        String vowels = "AEIOU";
        String consonants = "BCDFGHJKLMNPQRSTVWXYZ";
        Random rand = new Random();
        for(int i = 0 ; i<16;i++)
        {
            if(rand.nextInt(2)==0)
            {
                int idx = rand.nextInt(5);
                text.add(String.valueOf(vowels.charAt(idx)));
            }
            else
            {
                int idx = rand.nextInt(21);
                text.add(String.valueOf(consonants.charAt(idx)));
            }
        }
        System.out.print("setting text - ");
        print(text);
    }

    private void print(List<String> list)
    {
        for(String object : list)
        {
            System.out.print(object + " ");
        }
        System.out.println();
    }


}
