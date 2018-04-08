package com.dadapper.wordswiper.Scoring;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private static Map<String,Integer> scoreMap;

    public Score()
    {
        scoreMap = new HashMap<>();
        scoreMap.put("A",2);
        scoreMap.put("B",7);
        scoreMap.put("C",3);
        scoreMap.put("D",3);    //
        scoreMap.put("E",1);
        scoreMap.put("F",7);
        scoreMap.put("G",4);
        scoreMap.put("H",4);
        scoreMap.put("I",1);
        scoreMap.put("J",10);
        scoreMap.put("K",6);
        scoreMap.put("L",3);
        scoreMap.put("M",4);    //
        scoreMap.put("N",2);
        scoreMap.put("O",2);    //
        scoreMap.put("P",4);
        scoreMap.put("Q",10);
        scoreMap.put("R",2);
        scoreMap.put("S",2);
        scoreMap.put("T",2);
        scoreMap.put("U",5);
        scoreMap.put("V",6);
        scoreMap.put("W",6);
        scoreMap.put("X",1);
        scoreMap.put("Y",5);    //
        scoreMap.put("Z",8);
    }

    public static int wordScore(String word)
    {
        int score = 0;
        for(int i =0 ;i < word.length();i++)
        {
            score = score + scoreMap.get(String.valueOf(word.charAt(i)));
        }

        if(word.length()==5)
        {
            score = (int)(((double) score)*1.5);
        }
        else if(word.length() == 6 || word.length()==7)
        {
            score = (int)(((double) score)*2.0);
        }
        else if(word.length() >=8)
        {
            score = (int)(((double) score)*3.0);
        }

        return score;
    }
}
