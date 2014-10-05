package ru.RedFraction.androgram.game;

import android.content.Context;
import android.content.SharedPreferences;

/**
* Created by Red Fraction on 03.10.2014.
*/
public class Prefs{

    private static final String PREFERENCES_SETTINGS = "Settings";
    private static final String PREFERENCES_SCORE = "Score";
    private static final String Wordlist = "Wordlist";
    private static final String Score = "Score";

    public static SharedPreferences sPref;

    public static void saveWordlist(Context context, String wordlist){
        sPref = context.getSharedPreferences(PREFERENCES_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(Wordlist, wordlist);
        ed.commit();
    }

    public static String loadWordlist(Context context){
        sPref = context.getSharedPreferences(PREFERENCES_SETTINGS, Context.MODE_PRIVATE);

        String wordlist = sPref.getString(Wordlist, "");
        return wordlist;
    }

    public static void saveScore(Context context, int score){
        sPref = context.getSharedPreferences(PREFERENCES_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(Score, score);
        ed.commit();
    }

    public static int loadScore(Context context){
        sPref = context.getSharedPreferences(PREFERENCES_SETTINGS, Context.MODE_PRIVATE);

        int score = sPref.getInt(Score, 0);
        return score;
    }
}
