package com.funtestic.models;

import org.json.JSONObject;

public class Quiz {

    private String grade;
    private Child child;

    public Quiz(String grade,Child chld)
    {
        this.grade = grade;
        child = new Child(chld);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString()
    {
        try {
            JSONObject quiz = new JSONObject();
            quiz.put("grade", getGrade());
            quiz.put("child",child.toString());
            return quiz.toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "{}";
        }
    }
}
