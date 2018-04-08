package com.dadapper.wordswiper.Dictionary;

import android.content.Context;

import com.dadapper.wordswiper.Dictionary.Trie.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class WordDictionary {
     private static Trie dict = null;
     private Context context;
     public WordDictionary(Context context)
     {
         this.context = context;
         if(dict==null) {
             constructTrie();
         }
     }

     public boolean containsWord(List<String> letters)
     {
         String constructedWord = "";
         for(String letter : letters)
         {
             constructedWord = constructedWord + letter.toUpperCase().trim();
         }
         return containsWord(constructedWord.trim());
     }
     public boolean containsWord(String word)
     {
         return dict.search(word.toUpperCase());
     }
     private void constructTrie()
     {
         Trie trie =new Trie();
         BufferedReader reader = null;
         try {
             reader = new BufferedReader(
                     new InputStreamReader(context.getAssets().open("wordDictionaries/english_word_list_v1.txt")));

             String line;
             while ((line = reader.readLine()) != null)
             {
                 trie.insert(line.toUpperCase());
             }
         } catch (IOException e) {
             //log the exception
             System.out.println("while creating dictionary could not read from file - " + e.getMessage());
         } finally {
             if (reader != null) {
                 try {
                     reader.close();
                 } catch (IOException e) {
                     System.out.println("Error while closing buffer reader - " + e.getMessage());
                 }
             }
         }
         this.dict = trie;
     }
}
