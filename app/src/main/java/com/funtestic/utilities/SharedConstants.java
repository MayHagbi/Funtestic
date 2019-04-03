package com.funtestic.utilities;
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
}
