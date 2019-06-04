package com.funtestic.models;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class Quiz {

    private String grade;
    private String childId;

    public Quiz(DecimalFormat grade,String id)
    {
        this.grade = grade;
        this.childId = id;
    }

    public DecimalFormat getGrade() {
        return grade;
    }

    public void setGrade(DecimalFormat grade) {
        this.grade = grade;
    }


    @Override
    public String toString()
    {
        try {
            JSONObject quiz = new JSONObject();
            quiz.put("grade", getGrade());
            quiz.put("child_id",getChildId());
            return quiz.toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "{}";
        }
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
}
