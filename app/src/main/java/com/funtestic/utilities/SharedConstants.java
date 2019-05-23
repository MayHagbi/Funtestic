package com.funtestic.utilities;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedConstants  {
    public static final Map<String, Integer> scoreMap = new HashMap<>();

    static {
        scoreMap.put("לעולם לא" , 0);
        scoreMap.put("לעיתים נדירות" , 25);
        scoreMap.put("לפעמים" , 50);
        scoreMap.put("לעיתים קרובות" , 75);
        scoreMap.put("לעיתים קרובות מאוד" , 100);
    }

    static public void scorePreferencesInitialization(SharedPreferences table_score_prefs, String answer, String number_of_question){
        int score = SharedConstants.scoreMap.get(answer);
        SharedPreferences.Editor score_editor = table_score_prefs.edit();

        score_editor.putInt(number_of_question, score);

        score_editor.commit();
    }
}
