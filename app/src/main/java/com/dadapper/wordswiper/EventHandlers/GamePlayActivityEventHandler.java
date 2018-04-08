package com.dadapper.wordswiper.EventHandlers;


import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.dadapper.wordswiper.CharAdaptor;
import com.dadapper.wordswiper.Dictionary.WordDictionary;
import com.dadapper.wordswiper.Scoring.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlayActivityEventHandler implements EventHandler {

    private List<String> word;
    private List<String> wordList;
    private Map<Integer, Boolean> hash;
    private GridView gridview;
    private CharAdaptor charAdaptor;
    private WordDictionary dictionary;
    private int totalScore;

    public GamePlayActivityEventHandler(GridView gridview, Context context, WordDictionary dictionary)
    {
        this.word = new ArrayList<>();
        this.hash = new HashMap<>();
        this.gridview = gridview;
        this.charAdaptor = new CharAdaptor(context);
        this.wordList = new ArrayList<>();
        this.dictionary = dictionary;
        this.totalScore = 0;
    }
    @Override
    public void actionDown(View v, MotionEvent event)
    {
        float currentXPosition = event.getX();
        float currentYPosition = event.getY();
        int position = gridview.pointToPosition((int) currentXPosition, (int) currentYPosition);
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

    @Override
    public void actionUp(View v, MotionEvent event)
    {
        System.out.print("action up - current word :  ");
        print(word);
        if(dictionary.containsWord(word) && !wordList.contains(createWordString(word))) {
            wordList.add(createWordString(word));
            int score = (new Score()).wordScore(createWordString(word));
            this.totalScore = this.totalScore + score;
        }

        System.out.println("Found word List: ");
        print(wordList);
        System.out.println("current Score: " + totalScore);
        word.clear();
        hash.clear();
    }

    @Override
    public void actionMove(View v, MotionEvent event)
    {
        float currentXPosition = event.getX();
        float currentYPosition = event.getY();
        int position = gridview.pointToPosition((int) currentXPosition, (int) currentYPosition);

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

    private String createWordString(List<String> letters)
    {
        String constructedWord = "";
        for(String letter : letters)
        {
            constructedWord = constructedWord + letter.toUpperCase().trim();
        }
        return constructedWord.trim();
    }
    public void print(List<String> list)
    {
        for(String object : list)
        {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public void printListOfList(List<List<String>> lists)
    {
        for( List<String> list : lists)
        {
            print(list);
        }
        System.out.println();
    }
}
