package com.dadapper.wordswiper;

import android.content.Context;
import android.graphics.Color;
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
//        for(int i = 0 ; i<16;i++)
//        {
//            if(rand.nextInt(2)==0)
//            {
//                int idx = rand.nextInt(5);
//                text.add(String.valueOf(vowels.charAt(idx)));
//            }
//            else
//            {
//                int idx = rand.nextInt(21);
//                text.add(String.valueOf(consonants.charAt(idx)));
//            }
//        }
        for(int i = 0; i < 16; i++)
        {
            int idx = rand.nextInt(9408);
            if(idx <= 816)
            {
                text.add("A");
            }
            else if(idx <= 965)
            {
                text.add("B");
            }
            else if(idx <= 1243)
            {
                text.add("C");
            }
            else if(idx <= 1688)
            {
                text.add("D");
            }
            else if(idx <= 2958)
            {
                text.add("E");
            }
            else if(idx <= 3180)
            {
                text.add("F");
            }
            else if(idx <= 3381)
            {
                text.add("G");
            }else if(idx <= 3990)
            {
                text.add("H");
            }else if(idx <= 4086)
            {
                text.add("I");
            }else if(idx <= 4101)
            {
                text.add("J");
            }else if(idx <= 4178)
            {
                text.add("K");
            }else if(idx <= 4580)
            {
                text.add("L");
            }else if(idx <= 4820)
            {
                text.add("M");
            }else if(idx <= 5494)
            {
                text.add("N");
            }else if(idx <= 6244)
            {
                text.add("O");
            }else if(idx <= 6436)
            {
                text.add("P");
            }else if(idx <= 6445)
            {
                text.add("Q");
            }else if(idx <= 7043)
            {
                text.add("R");
            }else if(idx <= 7675)
            {
                text.add("S");
            }else if(idx <= 8580)
            {
                text.add("T");
            }else if(idx <= 8855)
            {
                text.add("U");
            }else if(idx <= 8952)
            {
                text.add("V");
            }else if(idx <= 9188)
            {
                text.add("W");
            }else if(idx <= 9203)
            {
                text.add("X");
            }else if(idx <= 9400)
            {
                text.add("Y");
            }else if(idx <= 9407)
            {
                text.add("Z");
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
