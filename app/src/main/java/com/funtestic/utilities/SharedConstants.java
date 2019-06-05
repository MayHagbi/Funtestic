package com.funtestic.utilities;
import android.content.SharedPreferences;

import com.funtestic.R;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

public class SharedConstants  {
    public static final Map<String, Integer> scoreMap = new HashMap<>();
    public static final String PREFS_NAME = "PrivateData";


    static {
        scoreMap.put("Never" , 0);
        scoreMap.put("Rarely" , 25);
        scoreMap.put("Sometimes" , 50);
        scoreMap.put("Often" , 75);
        scoreMap.put("Very often" , 100);
    }

    static public void scorePreferencesInitialization(SharedPreferences table_score_prefs, String answer, String number_of_question){
        int score = SharedConstants.scoreMap.get(answer);
        SharedPreferences.Editor score_editor = table_score_prefs.edit();

        score_editor.putInt(number_of_question, score);

        score_editor.commit();
    }
}
